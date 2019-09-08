package proxy;

import org.junit.Test;


public class Main {

	/**
	 * spring aop 底层技术 jdk 的代理技术
	 */
	@Test
	public void f1() {
		UserService service = new UserServiceImpl();

		UserService proxy = MyProxyUtils.getProxy(service);
		proxy.save();
		proxy.update();
	}

}
