package uebung_06_2014.utc.as;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

public class AsynchronousNISTClient {
	static boolean completed = false;

	public static void main(String[] args) {

		final int SERVER_PORT = 13; // daytime
		// public UTC Server: siehe http://tf.nist.gov/tf-cgi/servers.cgi
		// eventuell testen, ob der Host erreichbar ist
		final String SERVER_IP = "165.193.126.229";

		ByteBuffer receivingBuffer = ByteBuffer.allocateDirect(1024);
		try (final AsynchronousSocketChannel asynchronousSocketChannel = AsynchronousSocketChannel
				.open()) {
			if (asynchronousSocketChannel.isOpen()) {
				// verbinden: 
				Void connect = asynchronousSocketChannel.connect(
						new InetSocketAddress(SERVER_IP, SERVER_PORT)).get();
				if (connect == null) {
					System.out.println("Local address: "
							+ asynchronousSocketChannel.getLocalAddress());
					asynchronousSocketChannel.read(receivingBuffer,
							receivingBuffer,
							new CompletionHandler<Integer, ByteBuffer>() {
								public void completed(Integer result,
										ByteBuffer buffer) {
									buffer.flip();
									// TODO
									// Ausgabe der UTC und lokalen Zeit
								}

								public void failed(Throwable exc,
										ByteBuffer buffer) {
									throw new UnsupportedOperationException(
											"lesen ging schief!");
								}
							});

					while (!completed) { // loop wegen async future
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
						}
					}

				} else {
					System.out
							.println("Verbindung kann nicht aufgebaut werden!");
				}
			} else {
				System.out
						.println("Asynchronous Socket Channel kann nicht geöffnet werden!");
			}
		} catch (IOException | InterruptedException | ExecutionException ex) {
			System.err.println(ex);
		}

	}
}
