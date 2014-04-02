package uebung_06_2014.logicalClock_RMIBank;

public class LC_SequenceException extends Exception {

	private static final long serialVersionUID = 844678915247600616L;
	Exception ex = new Exception("[LC_SequenceException]Update Reihenfolge ist nicht korrekt!"    ) ;
}
