package ThreadDemo;

public class SubThread extends Thread {
	public void  run() {
		for(int i =0;i<100000;i++) {
			System.out.println(getId() + "*************");
		}
	}

}
