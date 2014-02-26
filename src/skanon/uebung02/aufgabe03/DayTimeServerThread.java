package skanon.uebung02.aufgabe03;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class DayTimeServerThread extends Thread {
	
	private Socket client;
	
	public DayTimeServerThread(final Socket client) {
		this.client = client;
	}
	
	

	@Override public void run() {
		PrintWriter out;
		try {
			out = new PrintWriter(client.getOutputStream(), true);
			out.println(new Date().toString());
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
