package uebung_06_2014.logicalClock_RMIBank;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BankServerInterface extends Remote {

	public int[] getKontostand() throws RemoteException;

	public void setKontostand(int[] msg) throws RemoteException,
			LC_SequenceException;

}
