package uebung_06_2014.logicalClock_RMIBank;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class BankClient {

	private int localLC = 0;
	private int remoteLC = 0;
	private int kontostand;
	private int remoteKontostand;

	public static void main(String[] args) {
		Random rnd = new Random(2000);
		int sleep = 0;
		int[] msg = new int[2];
		int mode = 1;

		try {
			BankClient client = new BankClient();
			Registry registry;
			registry = LocateRegistry.getRegistry("localhost");
			BankServerInterface server = (BankServerInterface) registry
					.lookup("timeserver");
			System.out.println("TimeClient gestartet...");

			while (true) {
				try {
					sleep = rnd.nextInt(1000);
					System.out.println("Client sleeps for " + sleep
							+ " Milliseconds.");
					Thread.sleep(sleep);
					client.localLC++;
					System.out.println("[Client]logical clock: "
							+ client.localLC);

					// Client Server interaction
					msg = server.getKontostand();
					client.remoteKontostand = msg[0];
					client.remoteLC = msg[1];

					// check local clock
					// if local clock is behind server: update
					if (client.localLC < client.remoteLC) {
						client.localLC = client.remoteLC + 1;
						System.out.println("[Client]logical clock updated: "
								+ client.localLC);
					}

					// local update
					switch (mode) {
					case 1:
						client.kontostand = client.remoteKontostand + 10;
						break;
					case 2:
						client.kontostand = client.remoteKontostand - 10;
						break;
					default:
						client.kontostand = client.remoteKontostand;
					}

					// remote update?
					msg[0] = client.kontostand;
					// next line is not neede: still valid (from server)
					msg[1] = client.remoteLC;
					try {
						server.setKontostand(msg);
						System.out.println("Update erfolgreich");
					} catch (LC_SequenceException e) {
						System.err.println("Update abgebrochen");
						// Kontostand erneut abfragen
						// und Änderung erneut machen
						// und erneut probieren: lassen wir weg
						// In DB Systemen: before und after image
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}
