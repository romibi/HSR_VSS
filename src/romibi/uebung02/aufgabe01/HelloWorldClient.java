package romibi.uebung02.aufgabe01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class HelloWorldClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		String host = "localhost";
		int port = 4242;
		
		Socket server = new Socket(host, port);
		System.out.println("client > Connected to " + server.getInetAddress());
		
		PrintWriter out = new PrintWriter(server.getOutputStream(), true);
		out.println("Hello Server");
		server.close();
	}
}
