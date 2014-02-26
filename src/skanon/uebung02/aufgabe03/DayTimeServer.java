package skanon.uebung02.aufgabe03;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DayTimeServer {

	public static void main(String[] args) throws IOException {
		int port = 4242;
		
		ServerSocket server = new ServerSocket(port);
		for (int i=0; i<Integer.MAX_VALUE;i++) {
			System.out.println("Server > Waiting for client...");
			Socket client = server.accept();
			new DayTimeServerThread(client).start();	
		}
		
		
		server.close();
	}

}
