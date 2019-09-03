package ThreadDemo;

public class RunObj implements Runnable{
	public void run() {
		
		for(int i=0 ;i<1000;i++) {
			System.out.println(Thread.currentThread().getId()+" : *-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*");
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
	public static void main(String[] args) {
		Thread td1 =new Thread(new RunObj() );
		td1.start();
		try {
			td1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
////		td1.start();
//		Exception in thread "main" java.lang.IllegalThreadStateException
//		at java.lang.Thread.start(Unknown Source)
//		at ThreadDemo.RunObj.main(RunObj.java:30)
		
	}

}
