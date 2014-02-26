package romibi.uebung02.aufgabe03;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DayTimeServer{
	public static void main(String[] args) throws IOException {
		int port = 4242;
		ServerSocket server = new ServerSocket(port);

		int i=0;
		while (i<Integer.MAX_VALUE) {
			System.out.println("server > Waiting for client...");
			Socket client = server.accept();
			
			new DayTimeServerClientThread(client).start();
			i++;
		}

		server.close();

	}
}
