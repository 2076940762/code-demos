package cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibProxyUtils {

	public static CustomerDao getProxy() {

		Enhancer enhancer = new Enhancer();

		// 设置父类
		enhancer.setSuperclass(CustomerDao.class);

		// 回调函数
		enhancer.setCallback(new MethodInterceptor() {

			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy)
					throws Throwable {

				if ("save".equals(method.getName())) {
					System.out.println(" before save");
				}
				// 执行原来的方法
				Object res = methodProxy.invokeSuper(obj, args);

				if ("save".equals(method.getName())) {
					System.out.println(" after save");
				}
				return res;
			}
		});

		return (CustomerDao) enhancer.create();
	}

}
