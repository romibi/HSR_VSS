package uebung_06_2014.mutex;

public class Entry {
	private int procID;
	private int logicalClock;

	Entry(int proc, int time) {
		procID = proc;
		logicalClock = time;
	}

	public int getProcID() {
		return procID;
	}

	public void setProcID(int procID) {
		this.procID = procID;
	}

	public int getLogicalClock() {
		return logicalClock;
	}

	public void setLogicalClock(int logicalClock) {
		this.logicalClock = logicalClock;
	}
}
