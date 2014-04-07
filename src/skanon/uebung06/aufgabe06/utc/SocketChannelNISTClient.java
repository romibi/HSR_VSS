package skanon.uebung06.aufgabe06.utc;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class SocketChannelNISTClient {

	// standard daytime port
	private static final int DEFAULT_DAYTIME_PORT = 13;

	// aktueller port
	private static int port = DEFAULT_DAYTIME_PORT;

	/*
	 * Liste der UTC Server vom US Government
	 */
	// daytime UTC Server: siehe http://tf.nist.gov/tf-cgi/servers.cgi
	// der folgende UTC Server funktionierte 
	final String SERVER_IP = "165.193.126.229";

	// mit dem Host Namen geht es eventuell schneller
	private static final String DEFAULT_HOST = "165.193.126.229";

	// aktueller daytime host
	private static String host = DEFAULT_HOST;

	// Charset / decoder : z.B. US-ASCII (US UTC Server)
	private static Charset charset = Charset.forName("US-ASCII");
	private static CharsetDecoder decoder = charset.newDecoder();

	// byte buffer (lesen)
	private static ByteBuffer dbuf = ByteBuffer.allocateDirect(1024);

	// Zeitabfrage
	private static void query(String host) throws IOException {
		System.out.println("[TimeQuery]query()");
		System.out.println("[TimeQuery]Host: "+InetAddress.getByName(host));
		InetSocketAddress isa = new InetSocketAddress(
				InetAddress.getByName(host), port);
		SocketChannel sc = null;

		try {

			// Connect
			Socket server = new Socket();
			server.connect(new InetSocketAddress(host, port));

			// Lesen der Zeit
			// Annahme: die Zeit Message ben�tigt nur ein Packet
			//TODO
			
			
			server.close();
			// ... und Ausgabe
			dbuf.flip();
			CharBuffer cb = decoder.decode(dbuf);
			System.out.print(isa + " : " + cb);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// aufr�umen
			if (sc != null)
				sc.close();
		}
	}

	public static void main(String[] args) {
		System.out.println("[TimeQuery]Welcome!");
		if (args.length < 1) {
			System.err.println("Usage: java TimeQuery [port] host...");
			System.err.println("Using default port " + DEFAULT_DAYTIME_PORT
					+ " on default host " + DEFAULT_HOST);
		}
		// int firstArg = 0;

		// erstes Argument: port 
		// if (Pattern.matches("[0-9]+", args[0])) {
		// port = Integer.parseInt(args[0]);
		// firstArg = 1;
		// }
		// weitere Argumente: host
		// for (int i = firstArg; i < args.length; i++) {
		// String host = args[i];
		try {
			query(host);
		} catch (IOException x) {
			System.err.println(host + ": " + x);
		}
		// }
	}
}