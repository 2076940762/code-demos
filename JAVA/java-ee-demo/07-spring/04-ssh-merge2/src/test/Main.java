package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Customer;
import service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext2.xml")
public class Main {

	@Resource(name = "customerService")
	private CustomerService customerService;

	/**
	 * 添加
	 */
	@Test
	public void f1() {
		Customer customer = new Customer();
		customer.setCust_name("嘿~^^~嘿");

		customerService.add(customer);
	}

	/**
	 * 查询
	 */
	@Test
	public void f2() {
		Customer customer = customerService.find(1L);
		System.out.println(customer);
	}

	/**
	 * 修改
	 */
	@Test
	public void f3(){
		Customer customer = customerService.find(2L);
		customer.setCust_name("海绵宝宝");
		customerService.update(customer);
	}
	
	
	
	
	
	
	
	
	
	
}
