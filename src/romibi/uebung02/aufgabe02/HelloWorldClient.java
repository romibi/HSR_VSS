package romibi.uebung02.aufgabe02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class HelloWorldClient {
	public static void main(String[] args) throws IOException {
		String host = "127.0.0.1";
		int port = 4242;
		
		Socket server = new Socket();
		server.connect(new InetSocketAddress(host,port));
		System.out.println("client > Connected to " + server.getInetAddress());
		
		PrintWriter out = new PrintWriter(server.getOutputStream(), true);
		out.println("Hello Server");
		out.close();
		if(server.isConnected()) {
			System.out.println("connected"); //Unexpected Behaviour
		} else {
			System.out.println("not connected");
		}
		server.close();
		System.out.println("finished");
	}
}
