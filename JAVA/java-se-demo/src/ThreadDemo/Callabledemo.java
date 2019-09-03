package ThreadDemo;

import java.util.concurrent.Callable;

public class Callabledemo implements Callable<String>{

	@Override
	public String  call() {
		return "hello";
	}

}
