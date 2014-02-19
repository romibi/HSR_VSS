package jsieber.uebung01.aufgabe01;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class DNS {

	public static void main(String[] args) throws UnknownHostException {

		InetAddress inet = InetAddress.getLocalHost();
			
		System.out.println(inet); 
		
		
		
		InetAddress inet2 = InetAddress.getByName("sidv0012.hsr.ch");
		System.out.println("Adresse "+inet2.getHostAddress()+" Name: " + inet2.getHostName());
		
		int[] intIp = {152, 96, 233, 145};
		byte[] byteIp = new byte[intIp.length];
		
		for (int i = 0; i < byteIp.length; i++) {
			byteIp[i] = (byte) intIp[i];
		}
		
		
		InetAddress inet3 = InetAddress.getByAddress(byteIp);
		System.out.println("Name: " + inet3.getHostName());
		System.out.println("Adresse: " + inet3.getHostAddress());
		
		
		InetAddress[] inet4 = InetAddress.getAllByName("www.google.com");
		System.out.println("Google IP Adressen");
		
		for ( int i = 0; i < inet4.length;i++) {
			System.out.println("IP: " + inet4[i].getHostAddress());
		}
	}
	

}
