package skanon.uebung06.aufgabe06.utc;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

public class PseudoPing {
	// Eingabe der IP Adresse des Zielhosts
	// oder Angabe des Host Namens
	private static final String IP_ADDR = "165.193.126.229";
	public static void main(String args[]) {
		try {
			InetAddress address = InetAddress.getByName(IP_ADDR);
			System.out.println("Name: " + address.getHostName());
			System.out.println("Addr: " + address.getHostAddress());
			// Echo: Port 7
			Socket t = new Socket(address.getHostName(), 7);
			DataInputStream dis = new DataInputStream(t.getInputStream());
			PrintStream ps = new PrintStream(t.getOutputStream());
			ps.println("Hello");
			@SuppressWarnings("deprecation")
			// DataInputStream readLine ist deprecated
			String str = dis.readLine();
			if (str.equals("Hello"))
				System.out.println("Alive!");
			else
				System.out.println("Der Server ist entweder tot oder er gibt kein Echo!");
			t.close();
		} catch (ConnectException e) {
			System.err.println("Kein Echo!");
		} catch (IOException e) {
			System.err.println("IO Exception: "+ e.getMessage());
		}
	}
}
