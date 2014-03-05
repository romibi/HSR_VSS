package jsieber.uebung03;

import java.io.IOException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;


public class Ex03_RabbitMqProducer {
	
    private final static String QUEUE_NAME = "hello_queue";


	public static void main(String[] argv) throws IOException {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		
	Connection connection = factory.newConnection();
	Channel channel = connection.createChannel();
	
	boolean durable = false;
	boolean exclusive = false;
	boolean autoDelete = false;
	
	channel.queueDeclare(QUEUE_NAME, durable, exclusive, autoDelete, null);
	
	String message = "Was geht";
	for (int i = 0; i < 100; i++) {
	channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
	}
	
	channel.close();
	connection.close();
	
	
	

	}

}
