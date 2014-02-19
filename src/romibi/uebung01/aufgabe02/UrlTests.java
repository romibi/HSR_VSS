package romibi.uebung01.aufgabe02;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class UrlTests {
	
	public static void main(String[] args) {
		try {
			URL a = new URL("http://www.hsr.ch");
			URL b = new URL("http://de.selfhtml.org/html/tabellen/aufbau.htm#definieren");
			URL c = new URL("https://unterricht.hsr.ch:443");
			URL d = new URL("http://www.google.ch/#aq=1&aqi=g5&aql=&hl=en&q=hsr+rapperswil");
			URL e = new URL("http://home.romibi.ch/sig/ip.jpg");
			
			

			printInfos(a);
			printInfos(b);
			printInfos(c);
			printInfos(d);
			
			printContent(a);
			printContent(b);
			printContent(c);
			printContent(d);
			
			saveImageToDisk(e);
			
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
	
	public static void saveImageToDisk(URL url) {
		System.out.println("=====================================");
		System.out.println("Saving Image");
		System.out.println("URL: "+url.toString());
		System.out.println("=");
		try {
			ImageIO.write(ImageIO.read(url), "jpg", new File("./img.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done");
		System.out.println("=====================================");
	}
}