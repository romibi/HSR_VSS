package uebung_06_2014.logicalClock.as;

import org.apache.activemq.ActiveMQConnection;

public interface LogicalClock_Parameter {
	public final String USER = ActiveMQConnection.DEFAULT_USER;
	public final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	public final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;
	public final boolean TRANSACTED = false;
	public final String QUEUE_NAME = "uebung_06_3";
}
