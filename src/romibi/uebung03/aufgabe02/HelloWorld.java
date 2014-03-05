package romibi.uebung03.aufgabe02;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class HelloWorld {
	public static void main(String[] args) throws IOException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		// Queue setup
		String queueName = "hello_queue";
		boolean durable = false;
		channel.queueDeclare(queueName,durable,false, false,null);

		String message = "hello world";
		channel.basicPublish("", "hello_queue", null, message.getBytes());
		channel.close();
		connection.close();
		
	}
}
