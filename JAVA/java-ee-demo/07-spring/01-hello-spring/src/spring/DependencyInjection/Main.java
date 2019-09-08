package spring.DependencyInjection;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	/**
	 * 依赖注入入门 setter
	 */
	@SuppressWarnings("resource")
	@Test
	public void f1() {
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");

		CustomerServiceImpl customerService = (CustomerServiceImpl) factory.getBean("customerService");

		customerService.save();
	}

	/**
	 * 依赖注入 构造函数方式
	 */
	@Test
	public void f2() {
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		Car car = (Car) factory.getBean("qqCar");
		System.out.println(car);// Car [name=qq飞车, price=1000000.0]
	}

	/**
	 * 依赖注入，构造函数2
	 */
	@Test
	public void f3() {
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		Person person = (Person) factory.getBean("person");
		System.out.println(person);
	}

	/**
	 * 注入集合
	 */
	@Test
	public void f4() {
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		Collectons c = (Collectons) factory.getBean("collections");
		System.out.println(c);
		
	}

}
