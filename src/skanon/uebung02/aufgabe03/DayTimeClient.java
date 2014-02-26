package skanon.uebung02.aufgabe03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class DayTimeClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		String host = "localhost";
		int port = 4242;
		
		Socket server = new Socket();
		server.connect(new InetSocketAddress(host, port), 500);
		
		System.out.println("Client > Connected to " + server.getInetAddress());
		
		BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
		System.out.println(in.readLine());
		
		server.close();
		System.out.println("Client > terminated");
	}
	

}
