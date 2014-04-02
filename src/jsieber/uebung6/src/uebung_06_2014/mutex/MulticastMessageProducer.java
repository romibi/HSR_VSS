package uebung_06_2014.mutex;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Das Programm sendet alle 2 Sekunden ein DatagramPacket mit einer
 * Message als Multicast.
 */

public class MulticastMessageProducer {

	private final static String MULTICAST_HOSTNAME = "224.0.224.0";
	private final static int PORT = 2048;
	private final static String HOST = "localhost";

	@SuppressWarnings("resource")
	public MulticastMessageProducer() {

		try {

			//Multicast Socket erstellen
			InetAddress ia = InetAddress.getByName(MULTICAST_HOSTNAME);
			MulticastSocket ms = new MulticastSocket(PORT);

			// Alle zwei Sekunden eine Meldung erzeugen und verschicken
			int counter = 0;
			while (true) {

				String message = "This is message " + (++counter)
						+ " from Host " + HOST;

				// Kopieren des Strings nach byte[]
				byte[] data = message.getBytes("UTF-8");

				// DatagramPacket erstellen und versenden
				DatagramPacket dp = new DatagramPacket(data, data.length, ia,
						PORT);
				ms.send(dp);
				System.out.println("message sent: " + message);

				// zwei sekunden warten, bevor nächste Meldung verschickt wird
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public static void main(String[] args) {
		new MulticastMessageProducer();
	}
}