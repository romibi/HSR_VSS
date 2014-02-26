package romibi.uebung02.aufgabe02;

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
		server.connect(new InetSocketAddress(host,port));
		System.out.println("client > Connected to " + server.getInetAddress());
		
		PrintWriter out = new PrintWriter(server.getOutputStream(),true);
		out.println("GET / HTTP/1.1");
        out.println("Host: "+host);
        out.println("");
		
		
		
		BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
		
		StringBuffer page = new StringBuffer("");
		String line = "";
		while((line = in.readLine()) != null) {
			page.append(line+"\n");
		}
		
		server.close();
		System.out.println("finished");
		System.out.println("-----------");
		System.out.println(page);
	}
}
