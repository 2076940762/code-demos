package spring.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Main {

	/**
	 * sping ioc入门
	 */
	@Test
	public void f1() {
		// 创建工厂，加载核心配置文件
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");

		// 从工厂获取对象
		Service service = (Service) factory.getBean("service1");
		service.hello();
	}

	/**
	 * 旧工厂
	 */
	@Test
	public void f2() {
		BeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		Service service = (Service) factory.getBean("service1");
		service.hello();
	}
}
