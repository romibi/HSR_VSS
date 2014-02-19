package skanon.uebung01.aufgabe01;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class UriTester {

	
	public static void main(String[] args) throws UnknownHostException {
		
		System.out.println("a) " + InetAddress.getLocalHost());
		System.out.println("b) " + InetAddress.getByName("sidv0012.hsr.ch").getHostAddress());
		
	}
}
