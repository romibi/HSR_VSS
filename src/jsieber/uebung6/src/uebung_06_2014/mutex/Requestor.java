package uebung_06_2014.mutex;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.PriorityQueue;

public class Requestor {
	// unique ID per object
	static int PID;

	// multicast
	private final static String MULTICAST_HOSTNAME = "224.0.224.0";
	private final static int PORT = 2048;
	// private final static String HOST = "localhost";

	InetAddress iaddr;
	MulticastSocket msock;
	byte[] buffer = new byte[65509];
	byte[] data;

	// local private queue
	@SuppressWarnings("unused")
	private PriorityQueue<Entry> pq;

	// Entry
	private int lc;
	private int procID;

	Requestor() {
		// local Queue
		pq = new PriorityQueue<Entry>(10, new EntryComparator());
		lc = 1;

		// multicast group
		try {
			iaddr = InetAddress.getByName(MULTICAST_HOSTNAME);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		try {
			msock = new MulticastSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			msock.joinGroup(iaddr);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// local process ID
		procID = PID++;

	}

	// send message for CS to all members of the group
	public void sendMessage(Message.MessageType mt) {
		Message msg = new Message(mt, procID, lc);

		String message = msg.toString();

		// Kopieren des Strings nach byte[]
		try {
			data = message.getBytes("UTF-8");
			System.out.println();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		// DatagramPacket erstellen und versenden
		DatagramPacket dpack = new DatagramPacket(data, data.length, iaddr,
				PORT);
		try {
			msock.send(dpack);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(procID + " message sent: " + message);

		// zwei sekunden warten, bevor nächste Meldung verschickt wird
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}

	// receive messages from all other members of the group
	public void receiveMsg() {

		// es können beliebige Meldungen sein
		// REQ, ACK, REL, ...
		// also mus man auch die lokale Queue updaten.

		// receive message
		buffer = new byte[65509];
		DatagramPacket dpack = new DatagramPacket(buffer, buffer.length);
		try {
			msock.receive(dpack);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String rMsg = new String(dpack.getData(), 0, dpack.getLength());
		System.out.println(procID + " message received: " + rMsg);

		// analyse message
		Message msg = new Message(rMsg);
		// REQ, ACC, REL, ACK;
		switch (msg.getMt()) {
		case REQ:
			System.out.println("received message of type REC");
			break;
		case ACC:
			System.out.println("received message of type ACC");
			break;
		case REL:
			System.out.println("received message of type REL");
			break;
		default:
			System.out.println("received message of type ACK");
		}

	}

	public static void main(String[] args) {
		int nr_req = 10;
		Requestor[] requestor = new Requestor[nr_req];
		for (int i = 0; i < nr_req; i++)
			requestor[i] = new Requestor();
		// send a request to access CR
		requestor[0].sendMessage(Message.MessageType.REQ);
		int nr_responses = 0;
		do {
			// empfangen
			requestor[nr_responses].receiveMsg();
			// überlegen
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// antworten
			requestor[nr_responses].sendMessage(Message.MessageType.ACK);
			nr_responses++;
		} while(nr_responses<10);
	}

}
