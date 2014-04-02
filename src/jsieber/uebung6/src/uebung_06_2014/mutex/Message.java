package uebung_06_2014.mutex;

public class Message {
	private MessageType mt;
	private int procID;
	private int lc;
	
	Message() {
		
	}
	Message(String str) {
		Message m = this.fromString(str);
		mt = m.getMt();
		procID = m.getProcID();
		lc = m.getLc();
	}
	Message(MessageType mtype, int proc, int time) {
		mt = mtype;
		procID = proc;
		lc = time;
	}
	
	public String toString() {
		return this.mt.name() + ";procID=" + this.procID + ";lc=" + this.lc;
	}

	public Message fromString(String msg) {
		String str = msg;
		// Message
		Message.MessageType msgCode;
		int procID;
		int lc;

		String[] tmp = str.split(";");
		
		// ACC;procID=...;lc=...
		msgCode = getType(tmp[0]);
		// procID
		String[] temp1 = tmp[1].split("=");
		procID = new Integer(temp1[1]);
		// lc
		String[] temp2 = tmp[2].split("=");
		lc = new Integer(temp2[1]);
		//
		return new Message(msgCode, procID, lc);
	}
	
	public MessageType getMt() {
		return mt;
	}

	public void setMt(MessageType mt) {
		this.mt = mt;
	}

	public int getProcID() {
		return procID;
	}

	public void setProcID(int procID) {
		this.procID = procID;
	}
	private Message.MessageType getType(String strg) {
		return Message.MessageType.valueOf(strg);
	}
	
	public static enum MessageType {
		REQ, ACC, REL, ACK; 
	}
	public int getLc() {
		return lc;
	}
	public void setLc(int lc) {
		this.lc = lc;
	}

	public static void main(String[] args) {
		System.out.println("main");
		String st="ACC"+";procID=121;lc=12221";
		Message msg = new Message(MessageType.valueOf("ACC"), 1212, 1313);
		
		System.out.println(msg.toString());
		System.out.println(new Message(st).toString());
	}


}
