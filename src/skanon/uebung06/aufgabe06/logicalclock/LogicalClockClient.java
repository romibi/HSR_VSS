package skanon.uebung06.aufgabe06.logicalclock;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class LogicalClockClient {
	private String user = LogicalClock_Parameter.USER;
	private String password = LogicalClock_Parameter.PASSWORD;
	private String url = LogicalClock_Parameter.URL;
	private boolean transacted = LogicalClock_Parameter.TRANSACTED;
	private String queueName = LogicalClock_Parameter.QUEUE_NAME;

	public static void main(String[] args) {
		System.out.println("[LogicalClockClient]");
		LogicalClockClient lcc = new LogicalClockClient();
		try {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					lcc.user, lcc.password, lcc.url);
			Connection connection;
			System.out.println("[LogicalClockClient]createConnection");
			connection = connectionFactory.createConnection(lcc.user,
					lcc.password);
			connection.start();

			System.out.println("[LogicalClockClient]createSession");
			Session session = connection.createSession(lcc.transacted,
					Session.AUTO_ACKNOWLEDGE);

			System.out.println("[LogicalClockClient]createQueue");
			Destination destination = session.createQueue(lcc.queueName);

			System.out.println("[LogicalClockClient]createProducer");
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);

			System.out.println("[LogicalClockClient]createConsumer");
			// unused gilt nur in der Vorlage
			@SuppressWarnings("unused")
			MessageConsumer consumer = session.createConsumer(destination);

			// TODO
			// kreieren sie beispielsweise 10 Messages (LC Meldungen / Abfragen)
			for (int i = 0; i < 10; i++) {

				System.out.println("[LogicalClockClient]createMessage");
				// TextMessage

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// TODO Ausgabe der empfangenen Zeit
				System.out
						.println("[LogicalClockClient]received logical time: ...");
			}
			// System.out.println("[LogicalClockClient]createMessage: STOP (-service)");
			// TextMessage stopMsg = session.createTextMessage("STOP");
			// producer.send(stopMsg);

			producer.close();
			session.close();
			connection.close();
			System.out.println("[LogicalClockClient]close");

		} catch (JMSException e) {
			e.printStackTrace();
		} catch (Exception e) {
		}

	}
}