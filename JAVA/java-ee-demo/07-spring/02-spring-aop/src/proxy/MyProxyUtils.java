package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxyUtils {

	public static UserService getProxy(final UserService service) {

		Object proxy = Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(),
				new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

						if ("save".equals(method.getName())) {
							System.out.println("save 开始执行");
						}

						// 执行原来的方法
						Object res = method.invoke(service, args);

						if ("save".equals(method.getName())) {
							System.out.println("save 执行结束");
						}

						return res;
					}
				});

		return (UserService) proxy;
	}

}
