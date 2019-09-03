package ThreadDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
//有返回值线程
//1. 创建 Callable 接口的实现类，并实现 call() 方法，该 call() 方法将作为线程执行体，并且有返回值。
//2. 创建 Callable 实现类的实例，使用 FutureTask 类来包装 Callable 对象，该 FutureTask 对象封装了该 Callable 对象的 call() 方法的返回值。
//3. 使用 FutureTask 对象作为 Thread 对象的 target 创建并启动新线程。
//4. 调用 FutureTask 对象的 get() 方法来获得子线程执行结束后的返回值。

public class CallableTest implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		return 0;
	}
	
	public static void main(String[] args) {
		CallableTest c =new CallableTest();
		FutureTask<Integer> f =new FutureTask<Integer>(c);
		Thread t =new Thread(f);
		t.start();

		try {
			System.out.println("resualt :"+f.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
