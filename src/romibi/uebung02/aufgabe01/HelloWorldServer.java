package romibi.uebung02.aufgabe01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class HelloWorldServer {
	public static void main(String[] args) throws IOException {
		int port = 4242;
		ServerSocket server = new ServerSocket(port);

		int i=0;
		while (i<Integer.MAX_VALUE) {
			System.out.println("server > Waiting for client...");
			Socket client = server.accept();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String msg = in.readLine();
			System.out.println("Hallo Client! I got: "+msg+" (myAdress:="
					+ server.getInetAddress().getHostAddress() + ":"
					+ server.getLocalPort() + ", "
					+ client.getInetAddress().getHostAddress() + ":"
					+ client.getPort() + ")");
			client.close();
			i++;
		}

		server.close();

	}
}
