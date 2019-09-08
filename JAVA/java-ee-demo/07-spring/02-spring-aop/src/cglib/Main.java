package cglib;

import org.junit.Test;

public class Main {

	/**
	 * Cglib 代理
	 */
	@Test
	public void f1() {
		CustomerDao proxy = CglibProxyUtils.getProxy();
		proxy.save();
	}
	
	
}
