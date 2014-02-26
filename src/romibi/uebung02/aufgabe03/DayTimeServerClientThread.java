package romibi.uebung02.aufgabe03;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class DayTimeServerClientThread extends Thread {
	private Socket client;
	
	DayTimeServerClientThread(final Socket client) {
		this.client = client;
	}
	
	public void run() {
		try {
			
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			out.println((new Date()).toString());
			
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
