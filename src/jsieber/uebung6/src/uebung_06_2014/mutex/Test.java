package uebung_06_2014.mutex;

public class Test {

	static int PID;

	Test() {
		PID++;
		System.out.println("PID="+PID);
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		Test test1 = new Test();
		Test test2 = new Test();

	}

}
