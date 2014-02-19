package romibi.uebung01.aufgabe02;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlTests {
	
	public static void main(String[] args) {
		try {
			URL a = new URL("http://www.hsr.ch");
			URL b = new URL("http://de.selfhtml.org/html/tabellen/aufbau.htm#definieren");
			URL c = new URL("https://unterricht.hsr.ch:443");
			URL d = new URL("http://www.google.ch/#aq=1&aqi=g5&aql=&hl=en&q=hsr+rapperswil");
			
			
			

			printInfos(a);
			printInfos(b);
			printInfos(c);
			printInfos(d);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}	
	}
	
	public static void printInfos(URL url) {
		System.out.println("-----");
		System.out.println("URL:	"+url.toString());
		System.out.println("HOST:	"+url.getHost());
		System.out.println("PORT:	"+url.getPort());
		System.out.println("PATH:	"+url.getPath());
		System.out.println("FILE:	"+url.getFile());
		System.out.println("REF:	"+url.getRef());
		System.out.println("-----");
	}
	
}