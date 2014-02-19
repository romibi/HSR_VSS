package skanon.uebung01.aufgabe01;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class InetAddressTest {

	
	public static void main(String[] args) throws UnknownHostException {
		System.out.println("a) " + InetAddress.getLocalHost());
		System.out.println("b) " + InetAddress.getByName("sidv0012.hsr.ch").getHostAddress());
		System.out.println("c) " + InetAddress.getByName("152.96.36.121").getHostName());
		System.out.println("d) google IPs:");
		InetAddress[] adresses = InetAddress.getAllByName("www.google.com");
		
		for (InetAddress adr : adresses) {
			System.out.println(adr.getHostAddress());
		}
		
		
	}
}
