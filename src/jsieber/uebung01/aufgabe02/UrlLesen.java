package jsieber.uebung01.aufgabe02;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlLesen {

	public static void main(String[] args) throws MalformedURLException {

		if (args.length != 1) {
			args = new String[2];

			args[0] = "http://www.google.com";
		}

		URL url = new URL(args[0]);

		System.out.println("Protokoll: " + url.getProtocol());
		System.out.println("Host: " + url.getHost());
		System.out.println("Port: " + url.getPort());
		System.out.println("Pfad: " + url.getPath());
		System.out.println("Datei: " + url.getFile());
		System.out.println("Referenz: " + url.getRef());
	}

}
