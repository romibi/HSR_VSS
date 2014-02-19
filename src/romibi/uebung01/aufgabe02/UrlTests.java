package romibi.uebung01.aufgabe02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
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
			
			printContent(a);
			printContent(b);
			printContent(c);
			printContent(d);
			
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
	
	public static void printContent(URL url) {
		System.out.println("=====================================");
		System.out.println("URL: "+url.toString());
		System.out.println("===");
		try {
			InputStream stream = url.openStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
			String string = "";
			while ((string = bufferedReader.readLine())!=null) {
				System.out.println(string);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("=====================================");
	}
	
}