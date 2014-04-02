package uebung_06_2014.logicalClock_RMIBank;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.Random;

public class BankServer implements BankServerInterface {

	private int localLC = 0;
	private int remoteLC = 0;
	private int kontostand = 100;
	private int remoteKontostand = 100;
	private LinkedList<Integer> lcList = new LinkedList<Integer>();

	public static void main(String[] args) {
		Random rnd = new Random(200);
		int sleep = 0;
		try {
			BankServer server = new BankServer();
			BankServerInterface stub = (BankServerInterface) UnicastRemoteObject
					.exportObject(server, 0);

			Registry registry = LocateRegistry.createRegistry(1099);
			registry = LocateRegistry.getRegistry();
			registry.rebind("timeserver", stub);

			System.out.println("TimeServer laeuft...");
			while (true) {
				try {
					sleep = rnd.nextInt(1000);
					System.out.println("Server sleeps for " + sleep
							+ " Milliseconds.");
					Thread.sleep(sleep);
					server.localLC++;
					System.out.println("[Server]logical clock: "
							+ server.localLC);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public synchronized int[] getKontostand() throws RemoteException {
		// logical clock updating
		localLC++;
		System.out.println("[Server]logical clock: " + localLC);
		int[] msg = new int[2];
		msg[0] = kontostand;
		msg[1] = localLC;
		/*
		 * eintragen der Sendezeit in die Tokenliste
		 */
		lcList.add(localLC);
		return msg;
	}

	public synchronized void setKontostand(int[] msg) throws RemoteException,
			LC_SequenceException {
		// logical clock updating
		localLC++;
		System.out.println("[Server]logical clock: " + localLC);
		// Message aufteilen
		remoteKontostand = msg[0];
		remoteLC = msg[1];
		//
		// Kontostand aktualisieren
		//
		if (lcList.getFirst() == remoteLC) {
			kontostand = remoteKontostand;
			lcList.removeFirst();
			System.out.println("Neuer Kontostand: " + kontostand);
		} else {
			System.err.println("Update kann nicht durchgeführt werden:");
			System.err.println("     Transaktion mit logischer Zeit "
					+ remoteLC);
			System.err.println("     kann nicht ausgeführt werden!");
			System.err.println("     Gesuchte Transaktion: mit LC = "
					+ lcList.getFirst());

			// jetzt müssen wir diese Transaction aus der Liste löschen!
			lcList.remove(new Integer(remoteLC));
			System.err.println("Transaktion " + remoteLC + " wurde gelöscht!");
			throw new LC_SequenceException();
		}
	}

}
