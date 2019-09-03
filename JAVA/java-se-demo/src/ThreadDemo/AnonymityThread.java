package ThreadDemo;

public class AnonymityThread {
	public static void main(String[] args) {
		
		new Thread() {
			public void run() {
				for(int i =0  ;i<1000000;i++) {
					System.out.println("[threadid = "+this.getId()+"]****************************");
				}
			}
		}.start();
	
		Runnable run =new Runnable() {
			public void run () {
				for(int i=0;i<999999;i++) {
					System.out.println("[threadid = "+Thread.currentThread().getId()+"]###############################");
				}
			}
		};
		
		new Thread(run).start();
		
		new Thread( new Runnable() {
			public void run() {
				for(int i = 0;i < 1234;i++) {
					System.out.println("[threadid = "+Thread.currentThread().getId()+"]================================");
				}
			}
			
		}
				).start();
		
	}
		
}
