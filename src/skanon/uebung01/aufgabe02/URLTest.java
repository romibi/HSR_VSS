package skanon.uebung01.aufgabe02;

import java.net.MalformedURLException;
import java.net.URL;

public class URLTest {
	
	private static URL url;
	
	
	public static void main(String[] args) throws MalformedURLException {
		System.out.println("Bitte geben Sie die gewünschte URL ein");
		String input = System.console().readLine();
		url = new URL(input);
		//Protokoll, Host, Port, Pfad, Datei, und Referenz
		System.out.println("Protokol: "+  url.getProtocol());
	}
}
