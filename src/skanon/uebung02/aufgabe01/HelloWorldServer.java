package skanon.uebung02.aufgabe01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HelloWorldServer {

	public static void main(String[] args) throws IOException, InterruptedException {
		int port = 2342;
		
		ServerSocket server = new ServerSocket(port);
		
		
		for (int i=0; i<Integer.MAX_VALUE;i++) {
			System.out.println("Server > Waiting for client...");
			Socket client = server.accept();
			
			System.out.println("Server > Client connected from " + client.getInetAddress());
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String token = in.readLine();
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			out.println(token+" (myAddress=" + server.getInetAddress()+"/"+server.getLocalPort()+", yourAddress="+client.getInetAddress()+"/"+client.getLocalPort()+")" );
			
			token = in.readLine();
			
			client.close();
		
		}
		System.out.println("Server > terminated");
		server.close();
		
	}

}
