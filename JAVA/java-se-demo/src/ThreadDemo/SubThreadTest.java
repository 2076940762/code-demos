package ThreadDemo;

public class SubThreadTest {
	public static void main(String[] args) {
		for(int i =0 ;i<100;i++) {
			new SubThread().start();
		}
	}

}
