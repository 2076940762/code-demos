package InnerClasss;

public class anonymityClass {
	public static void main(String[] args) {
		
		//�̳�Thread��������
		Thread t = new Thread() {
			public void run() {
				System.out.println("new thread 1");
			}
		};
		
		t.run();
		
		//ʵ�ֽӿ�������
		Runnable r =new Runnable() {
			public void run() {
				System.out.println("new thread 2");
			}
		};
		
		r.run();
	}
}





