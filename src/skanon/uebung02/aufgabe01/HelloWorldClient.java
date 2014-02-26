package skanon.uebung02.aufgabe01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class HelloWorldClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		String host = "localhost";
		int port = 2342;
		
		Socket server = new Socket();
		server.connect(new InetSocketAddress(host, port), 500);
		
		System.out.println("Client > Connected to " + server.getInetAddress());
		
		PrintWriter out = new PrintWriter(server.getOutputStream(), true);
		out.println("Hello World");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
		System.out.println(in.readLine());
		
		server.close();
		System.out.println("Client > terminated");
	}
	

}
