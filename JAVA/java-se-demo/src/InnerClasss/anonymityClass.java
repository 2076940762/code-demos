package InnerClasss;

public class anonymityClass {
	public static void main(String[] args) {
		
		//继承Thread的匿名类
		Thread t = new Thread() {
			public void run() {
				System.out.println("new thread 1");
			}
		};
		
		t.run();
		
		//实现接口匿名类
		Runnable r =new Runnable() {
			public void run() {
				System.out.println("new thread 2");
			}
		};
		
		r.run();
	}
}





