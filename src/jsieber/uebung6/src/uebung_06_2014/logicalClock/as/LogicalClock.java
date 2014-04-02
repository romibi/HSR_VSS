package uebung_06_2014.logicalClock.as;

import java.util.Random;

public class LogicalClock {
	private int c; // clock

	Random rnd = new Random(500);
	int inc = rnd.nextInt(10);

	public LogicalClock() {
		c = 1;
	}

	public int getValue() {
		return c;
	}

	public void tick() { 
		// internal events
		c = c + inc;
		inc = rnd.nextInt(10);
	}

	public void sendAction() {
		c = c + 1;
	}

	public void receiveAction() {
		c = c + 1;
	}
}
