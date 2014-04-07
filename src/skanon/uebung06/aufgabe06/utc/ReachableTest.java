package skanon.uebung06.aufgabe06.utc;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ReachableTest {
	// Eingabe der IP Adresse des Zielhosts
	// oder Angabe des Host Namens
	private static final String IP_ADDR = "165.193.126.229";

	public static void main(String args[]) {
		try {
			InetAddress address = InetAddress.getByName(IP_ADDR);
			System.out.println("Name: " + address.getHostName());
			System.out.println("Addr: " + address.getHostAddress());
			/*
			 * reachability mit timeout (millis) 
			 * mithilfe von ICMP ECHO REQUESTs
			 * sofern die Plattform ICMP zul�sst
			 * Falls der Host nicht erreichbar "scheint"
			 * dann kann es auch am Timeout liegen.
			 * Timeout erh�hen und noch einmal probieren!
			 */
			System.out.println("Reach: " + address.isReachable(2000));
		} catch (UnknownHostException e) {
			System.err.println("Lookup " + IP_ADDR + " schlug fehl!");
		} catch (IOException e) {
			System.err.println(IP_ADDR + " ist nicht erreichbar!");
		}
	}
}
