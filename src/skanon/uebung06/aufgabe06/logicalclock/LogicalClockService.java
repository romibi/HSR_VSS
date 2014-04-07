package skanon.uebung06.aufgabe06.logicalclock;

import java.util.Random;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
/*
 * die JMS jars aus dem Libs Verzeichnis m�ssen im Projektpfad sein
 */
public class LogicalClockService {
	private String user = LogicalClock_Parameter.USER;
	private String password = LogicalClock_Parameter.PASSWORD;
	private String url = LogicalClock_Parameter.URL;
	private boolean transacted = LogicalClock_Parameter.TRANSACTED;
	private String queueName = LogicalClock_Parameter.QUEUE_NAME;

	private int lc = 0;
	private boolean loop = true;

	public static void main(String[] args) {
		Random rnd = new Random(200);
		int sleep = 0;
		System.out.println("[LogicalClockService]");
		LogicalClockService lcs = new LogicalClockService();

		/*
		 * B R O K E R
		 */
		// System.out.println("LogicalClockService]start broker");
		// BrokerService broker = new BrokerService();
		// broker.setUseJmx(true);
		// ...

		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				lcs.user, lcs.password, lcs.url);
		Connection connection;
		try {
			System.out.println("[LogicalClockService]createConnection");
			connection = connectionFactory.createConnection();
			connection.start();

			System.out.println("[LogicalClockService]createSession");
			Session session = connection.createSession(lcs.transacted,
					Session.AUTO_ACKNOWLEDGE);

			System.out.println("[LogicalClockService]createQueue");
			Destination destination = session.createQueue(lcs.queueName);

			System.out.println("[LogicalClockService]createConsumer");
			MessageConsumer consumer = session.createConsumer(destination);

			System.out.println("[LogicalClockService]createproducer");
			MessageProducer producer = session.createProducer(destination);

			// logical clock service
			LogicalClock logicalClock = new LogicalClock();

			while (lcs.loop) {
				System.out.println("[LogicalClockService]receiveMessage");
				TextMessage text = (TextMessage) consumer.receive();
				if (text.getText().equalsIgnoreCase("STOP")) {
					lcs.loop = false;
					System.out
							.println("[LogicalClockService]receiveMessage - STOP Service");
					break;
				}
				logicalClock.receiveAction();

				try {
					sleep = rnd.nextInt(1000);
					System.out.println("[LogicalClockServer]sleeps for "
							+ sleep + " Milliseconds.");
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				logicalClock.tick();
				lcs.lc = logicalClock.getValue();
				System.out.println("[LogicalClockServer]logical clock: "
						+ lcs.lc);
				TextMessage outMsg = session.createTextMessage(new Integer(
						lcs.lc).toString());
				System.out
						.println("[LogicalClockService]sendMessage: logicalClock = "
								+ outMsg.getText());
				producer.send(outMsg);
				logicalClock.sendAction();
			}

			consumer.close();
			session.close();
			connection.close();
			System.out.println("[LogicalClockService]close");

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}