package romibi.uebung01.aufgabe01;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTests {
	public static void main(String[] args) {
		try {
			System.out.println("a) "+InetAddress.getLocalHost().getHostName() + " = "+InetAddress.getLocalHost().getHostAddress());
			System.out.println("b) "+InetAddress.getByName("sidv0012.hsr.ch").getHostAddress());
			System.out.println("c) "+InetAddress.getByAddress(new byte[]{(byte) 152, (byte) 96, (byte) 36, (byte) 121}).getHostName());
			System.out.println("d)");
			
			InetAddress[] google = InetAddress.getAllByName("www.google.com");
			for (InetAddress adr : google) {
				System.out.println(adr.getHostAddress());
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
