package ThreadDemo;

public class RunObjTest {
	public static void main(String[] args) {
		for(int j =0 ;j<100;j++) {
			new Thread(	new RunObj()).start();
		}
	}

}
