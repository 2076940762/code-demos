package spring.ioc.annotation;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.annotation.xml")
public class Main {
	
	@Resource(name = "customerDao")
	private CustomerDao customerDao;

	/**
	 * 注解方式依赖注入
	 */
	@Test
	public void f1() {
		ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.annotation.xml");
		CustomerService customerService = (CustomerService) factory.getBean("customerService");
		customerService.save();
	}

	/**
	 * spring 与junit整合
	 */
	@Test
	public void f2() {
		customerDao.save();
	}
}
