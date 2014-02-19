package skanon.uebung01.aufgabe02;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.imageio.ImageIO;

public class URLTest {

	private static URL url;
	private static BufferedReader br = new BufferedReader(
			new InputStreamReader(System.in));

	public static void a() {
		System.out.println("a)");
		System.out.println("Protokol: " + url.getProtocol());
		System.out.println("Host: " + url.getHost());
		System.out.println("Port: " + url.getPort());
		System.out.println("Pfad: " + url.getPath());
		System.out.println("Datei: " + url.getFile());
		System.out.println("Referenz: " + url.getRef());
	}

	public static void b() throws IOException {
		System.out.println("\nb)");

		br = new BufferedReader(new InputStreamReader(url.openStream()));
		String out;

		while ((out = br.readLine()) != null) {
			System.out.println(out);
		}
	}

	private static void c() throws IOException {
		System.out.println("\nc");
		BufferedImage image = null;
		try {
			image = ImageIO.read(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 ImageIO.write(image, "jpg",new File("./out.jpg"));

	}

	public static void main(String[] args) throws IOException {
		System.out.println("Please enter URL:");
		String input = br.readLine();
		url = new URL(input);

		a();
		b();
		c();

		

	}

}
