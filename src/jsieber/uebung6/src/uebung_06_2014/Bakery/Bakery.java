package uebung_06_2014.Bakery;

class Ticket {
	public volatile int value = 0;
}

public class Bakery {

	private int numNodes = 0; // Lamport's bakery ticket algorithm.
	private Ticket[] ticket = null;

	public Bakery(int numNodes) {
		this.numNodes = numNodes;
		ticket = new Ticket[numNodes];
		for (int i = 0; i < numNodes; i++)
			ticket[i] = new Ticket();
	}

	private int maxx(Ticket[] ticket) {
		int mx = ticket[0].value;
		for (int i = 1; i < ticket.length; i++)
			if (ticket[i].value > mx)
				mx = ticket[i].value;
		return mx;
	}

	public void wantToEnterCS(int i) { // pre-protocol
		ticket[i].value = 1;
		ticket[i].value = 1 + maxx(ticket); // compute next ticket
		for (int j = 0; j < numNodes; j++)
			if (j != i)
				while (!(ticket[j].value == 0
						|| ticket[i].value < ticket[j].value || // break a tie
				(ticket[i].value == ticket[j].value && i < j))) {
					Thread.currentThread();
					// busy wait
					Thread.yield();
				}
	}

	public void finishedInCS(int i) { // post-protocol
		ticket[i].value = 0;
	}
}

/*
 * ............... Example compile and run(s)
 * 
 * D:\>javac *.java
 * 
 * D:\>java MutualExclusion -R15 -n4 1 1 1 1 8 3 8 3 MutualExclusion: Java
 * version=1.6.0_25, Java vendor=Sun Microsystems Inc. OS name=Windows 7, OS
 * arch=amd64, OS version=6.1 Thu May 12 20:34:04 CEST 2011 MutualExclusion:
 * numNodes=4, runTime=15 Node 0 is alive, napOutsideCS=1000, napInsideCS=1000
 * Node 1 is alive, napOutsideCS=1000, napInsideCS=1000 age()=0, Node 0 napping
 * outside CS for 136 ms Node 2 is alive, napOutsideCS=8000, napInsideCS=3000
 * Node 3 is alive, napOutsideCS=8000, napInsideCS=3000 All Node threads started
 * age()=0, Node 2 napping outside CS for 3774 ms age()=0, Node 3 napping
 * outside CS for 1727 ms age()=0, Node 1 napping outside CS for 483 ms
 * age()=136, Node 0 wants to enter its CS age()=136, Node 0 napping inside CS
 * for 807 ms age()=483, Node 1 wants to enter its CS age()=943, Node 0 napping
 * outside CS for 67 ms age()=943, Node 1 napping inside CS for 844 ms
 * age()=1010, Node 0 wants to enter its CS age()=1727, Node 3 wants to enter
 * its CS age()=1787, Node 0 napping inside CS for 163 ms age()=1787, Node 1
 * napping outside CS for 431 ms age()=1950, Node 3 napping inside CS for 448 ms
 * age()=1950, Node 0 napping outside CS for 329 ms age()=2218, Node 1 wants to
 * enter its CS age()=2279, Node 0 wants to enter its CS age()=2398, Node 1
 * napping inside CS for 468 ms age()=2398, Node 3 napping outside CS for 7194
 * ms age()=2866, Node 1 napping outside CS for 977 ms age()=2866, Node 0
 * napping inside CS for 601 ms age()=3467, Node 0 napping outside CS for 212 ms
 * age()=3679, Node 0 wants to enter its CS age()=3679, Node 0 napping inside CS
 * for 402 ms age()=3774, Node 2 wants to enter its CS age()=3843, Node 1 wants
 * to enter its CS age()=4081, Node 2 napping inside CS for 389 ms age()=4081,
 * Node 0 napping outside CS for 983 ms age()=4470, Node 2 napping outside CS
 * for 7041 ms age()=4470, Node 1 napping inside CS for 472 ms age()=4942, Node
 * 1 napping outside CS for 795 ms age()=5064, Node 0 wants to enter its CS
 * age()=5064, Node 0 napping inside CS for 98 ms age()=5162, Node 0 napping
 * outside CS for 448 ms age()=5610, Node 0 wants to enter its CS age()=5610,
 * Node 0 napping inside CS for 570 ms age()=5737, Node 1 wants to enter its CS
 * age()=6180, Node 1 napping inside CS for 883 ms age()=6180, Node 0 napping
 * outside CS for 664 ms age()=6844, Node 0 wants to enter its CS age()=7063,
 * Node 0 napping inside CS for 979 ms age()=7063, Node 1 napping outside CS for
 * 360 ms age()=7423, Node 1 wants to enter its CS age()=8042, Node 1 napping
 * inside CS for 243 ms age()=8042, Node 0 napping outside CS for 135 ms
 * age()=8177, Node 0 wants to enter its CS age()=8285, Node 0 napping inside CS
 * for 498 ms age()=8285, Node 1 napping outside CS for 380 ms ...
 */
