package skanon.uebung03.aufgabe02;

import java.util.Map;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class RabbitMqConsumer {
	public static void main(String[] argv) throws Exception {
		// Connection and Channel
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		// Queue setup
		String queueName = "hello_queue";
		boolean durable = false;
		boolean exclusive = false;
		boolean autoDelete = false;
		Map<String, Object> props = null;
		channel.queueDeclare(queueName, durable, exclusive, autoDelete, props);
		// Consume messages until user aborts
		System.out.println("[*] Waiting for messages.To exit press CTRL+C.");
		QueueingConsumer consumer = new QueueingConsumer(channel);
		boolean autoAck = true;
		channel.basicConsume(queueName, autoAck, consumer);
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody(), "UTF-8");
			System.out.printf(" [x] Received '%s'\n", message);
		}
	}
}
