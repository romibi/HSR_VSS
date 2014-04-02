package uebung_06_2014.mutex;


import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class SharedResource {
	private final static String MULTICAST_HOSTNAME = "224.0.224.0";
	private final static int PORT = 2048;
	private final static int BUFFER = 65509;
	
	byte[] buffer = new byte[BUFFER];

	@SuppressWarnings({ "unused", "resource" })
	SharedResource() {
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		try {
			MulticastSocket ms = new MulticastSocket(PORT);
			InetAddress ia = InetAddress.getByName(MULTICAST_HOSTNAME);
			ms.joinGroup(ia);

			System.out.println("[SharedResource]waiting for customers...");

			while (true) {
				ms.receive(dp);
				String s = new String(dp.getData(), 0, dp.getLength());
				// only MessageType.ACC will be processed
//				if (!s.startsWith(MessageType.ACC.name())) {
//				//	System.err.println("not a legal message type");
//					continue;
//				}
				Message msg = new Message(s);
				
				System.out.println("[Resource]access request received from: " + dp.getAddress().getHostName());
				
			}
		}

		catch (Exception e) {
			System.err.println(e);
		}
		
	}
	
	
	
	DatagramPacket dp = new DatagramPacket(buffer, buffer.length);

	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		SharedResource res = new SharedResource();
	}

}

