package romibi.uebung02.aufgabe04.romibi2013;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * idea: open a socket connection to host on ports
 * if connection succeeds: port is open
 * else: port is closed
 */
public class PortScanner {

	public static void main(String[] args) {

		// TODO : you should know that!
		int minPort = 100;
		int maxPort = 200;
		
		String host = "home.romibi.ch";
		
		int i = minPort;
		while(i<=maxPort) {
			
			System.out.println(i+": "+scan(host, i));
			
			i++;
		}
	}
	
	public static boolean scan(String host, int port) {
		try {
			Socket socket = new Socket(host, port);
			
			return true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return false;
	}
}