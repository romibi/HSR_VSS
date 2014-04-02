package uebung_06_2014.logicalClockRequester.as;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueRequestor;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class LC_Client {
	/*
	 * die JMS Bibliotheken aus dem Libs Verzeichnis
	 * müssen im Projektpfad sein!
	 */

	public static void main(String[] args) {
		String user = ActiveMQConnection.DEFAULT_USER;
		String password = ActiveMQConnection.DEFAULT_PASSWORD;
		String url = ActiveMQConnection.DEFAULT_BROKER_URL;
		String queueName = "queue/logicalClock";

		QueueConnection connect = null;
		QueueSession session = null;
		Queue queue = null;
		QueueRequestor sender = null;

		try {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					user, password, url);
			connect = connectionFactory.createQueueConnection();
			session = connect.createQueueSession(false,
					Session.AUTO_ACKNOWLEDGE);
			queue = session.createQueue(queueName);

			// Requester
			sender = new QueueRequestor(session, queue);
			connect.start();
			for (int i = 0; i < 10; i++) {
				// TextMessage 
				// TODO
			}
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != sender)
					sender.close();
			} catch (Exception ex) {/* ok */
			}
			try {
				if (null != session)
					session.close();
			} catch (Exception ex) {/* ok */
			}
			try {
				if (null != connect)
					connect.close();
			} catch (Exception ex) {/* ok */
			}
		}
	}
}