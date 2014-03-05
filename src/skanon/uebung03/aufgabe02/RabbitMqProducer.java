package skanon.uebung03.aufgabe02;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMqProducer {
	
	private static final String TASK_QUEUE_NAME = "hello_queue";

	public static void main(String[] args) throws IOException {
		 ConnectionFactory factory = new ConnectionFactory();
	        factory.setHost("localhost");
	        Connection connection = factory.newConnection();
	        Channel channel = connection.createChannel();
	        boolean durable = false;
	        channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);
	        
	        String message = "Hello World!";
	        channel.basicPublish("", TASK_QUEUE_NAME, null, message.getBytes());
	        System.out.println(" [x] Sent '" + message + "'");
	        
	        channel.close();
	        connection.close();
	        
	}

}
