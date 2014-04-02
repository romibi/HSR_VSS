package uebung_06_2014.mutex;

import java.net.*;

/**
 * Das Programm empfängt DatagramPacket, welche über
 * Multicast versandt wurden.
 */

public class MulticastReceiver {

	private final static String MULTICAST_HOSTNAME = "224.0.224.0";
	private final static int PORT = 2048;
	private final static int BUFFER = 65509;

	@SuppressWarnings("resource")
	public MulticastReceiver() {

		byte[] buffer = new byte[BUFFER];
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);

		try {
			MulticastSocket ms = new MulticastSocket(PORT);
			InetAddress ia = InetAddress.getByName(MULTICAST_HOSTNAME);
			ms.joinGroup(ia);

			System.out.println("waiting for messages...");

			while (true) {
				ms.receive(dp);
				String s = new String(dp.getData(), 0, dp.getLength());
				System.out.println("message received: " + s);
			}
		}

		catch (Exception e) {
			System.err.println(e);
		}
	}

	public static void main(String[] args) {
		new MulticastReceiver();
	}
}