package romibi.uebung02.aufgabe04.loesung2013;

import java.awt.Toolkit;
import java.net.Socket;

public class PortScanner {

  public static void main(String[] args) {
	  @SuppressWarnings("unused")
		Socket socket;
	    Toolkit.getDefaultToolkit().beep();
	    for ( int i=0; i<1024; i++) {
	      try {
	        socket = new Socket("locahost", i );
	        System.out.println("Server auf Port " + i);
	      } catch (Exception e) {
	        System.out.println("Port " + i + " nicht belegt");
	      }
	    }
	  }
}