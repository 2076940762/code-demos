package ThreadDemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolDemo {
	public static void main(String[] args) {
		function1();	
		
		function2();
	}

	private static void function2() {
		ExecutorService es =  Executors.newFixedThreadPool(10);
		 Future<String> f = es.submit(new Callabledemo());
		 
		 try {
			System.out.println(f.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	private static void function1() {
		ExecutorService es = Executors.newFixedThreadPool(100);
		for(int i = 0;i<1000;i++) {
			es.submit(new RunObj());
		}
	}

}
