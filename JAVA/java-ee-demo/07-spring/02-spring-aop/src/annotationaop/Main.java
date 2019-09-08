package annotationaop;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.annotation.aop.xml")
public class Main {

	@Resource(name = "customerService")
	private CustomerService service;

	@Test
	public void f1() {
		service.save();
		
		System.out.println("==============");
		
		service.update();
	}

}
