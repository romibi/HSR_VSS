package skanon.uebung02.aufgabe02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class HttpGetClient {

	public static void main(String[] args) throws IOException {
		String host = "motherfuckingwebsite.com";
		int port = 80;
		
		Socket server = new Socket();
		server.connect(new InetSocketAddress(host, port), 500);
		
		
		System.out.println("Client > Connected to " + server.getInetAddress());
		
		PrintWriter pw = new PrintWriter(server.getOutputStream());
		pw.println("GET / HTTP/1.1");
		pw.println("Host: motherfuckingwebsite.com");
		pw.println("");
		pw.flush();
		
		
		BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
		
		String out;
		while ((out = in.readLine()) != null) {
			System.out.println(out);	
		}
		
		server.close();
		
		
	}

}
