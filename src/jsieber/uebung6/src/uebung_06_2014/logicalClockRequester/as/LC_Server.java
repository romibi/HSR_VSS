package uebung_06_2014.logicalClockRequester.as;

import java.util.Random;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
 
public class LC_Server implements MessageListener {
	private String user = ActiveMQConnection.DEFAULT_USER;
	private String password = ActiveMQConnection.DEFAULT_PASSWORD;
	private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	private boolean transacted = false;
	private String queueName = "queue/logicalClock";

	QueueConnection connection = null;
	QueueSession session = null;
	Queue queue = null;
	QueueReceiver receiver = null;
	QueueSender replySnd = null;

	// Zufallsverzögerung
	private int sleep = 0;
	private static Random rnd = new Random(2000);
	// logical clock
	private static LogicalClock logicalClock = new LogicalClock();
	private static int lc = 0;

	public static void main(String[] args) {
		/*
		 * BROKER
		 */
		BrokerService broker = new BrokerService();
		broker.setUseJmx(true);
		try {
			broker.addConnector("tcp://localhost:61616");
			System.out.println("[LC_Server]Start Broker");
			broker.start();

			new LC_Server();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public LC_Server() throws Exception {
		try {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					user, password, url);
			System.out.println("[LC_Server]createConnection");
			connection = (QueueConnection) connectionFactory.createConnection(
					user, password);
			connection.start();

			System.out.println("[LC_Server]createSession");
			session = (QueueSession) connection.createSession(transacted,
					Session.AUTO_ACKNOWLEDGE);

			System.out.println("[LC_Server]createQueue");
			queue = session.createQueue(queueName);

			System.out.println("[LC_Server]createMessage");
			receiver = session.createReceiver(queue);
			receiver.setMessageListener(this);
			connection.start();

			try {
				sleep = rnd.nextInt(1000);
				System.out.println("[LC_Server]sleeps for " + sleep
						+ " Milliseconds.");
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			logicalClock.tick();
			lc = logicalClock.getValue();
			System.out.println("[LC_Server]logical clock: " + lc);

			boolean loop = true;
			while (loop) {
				Thread.sleep(1000);
				logicalClock.tick();
				lc = logicalClock.getValue();
				System.out.println("[LC_Server]logical clock tick: " + lc);
			}
		} finally {
			try {
				if (null != replySnd)
					replySnd.close();
			} catch (Exception ex) {/* ok */
			}
			try {
				if (null != receiver)
					receiver.close();
			} catch (Exception ex) {/* ok */
			}
			try {
				if (null != session)
					session.close();
			} catch (Exception ex) {/* ok */
			}
			try {
				if (null != connection)
					connection.close();
			} catch (Exception ex) {/* ok */
			}
		}
	}

	public void onMessage(Message message) {
		try {
			logicalClock.receiveAction();
			lc = logicalClock.getValue();
			System.out.println("[LC_Server]logical clock receiveAction: " + lc);

			TextMessage txtMsg = (TextMessage) message;
			System.out.println("Received:  " + txtMsg.getText());
			
			TextMessage answer = session.createTextMessage();
			answer.setText("Echo '" + txtMsg.getText() + "'.");
			System.out.println("Answering: " + answer.getText());
			Queue reply = (Queue) txtMsg.getJMSReplyTo();
			replySnd = session.createSender(reply);
			logicalClock.sendAction();
			lc = logicalClock.getValue();
			System.out.println("[LC_Server]logical clock sendAction: " + lc);

			answer.setStringProperty("LC", lc + "");
			replySnd.send(answer);
			replySnd.close();
			replySnd = null;
			message.acknowledge();
		} catch (JMSException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
