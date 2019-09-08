package spring.ioc.annotation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * <bean id="customerService" class="service.CustomerServiceImpl">
 *         <property name="customerDao" ref="customerDao" /> 
 * 
 * </bean>
 *
 */
@Component(value = "customerService")
@Scope(value = "prototype")  //单例
public class CustomerServiceImpl implements CustomerService {

//	@Autowired                                     //按类型自动装配
//	@Qualifier(value = "customerDao")
	
	// 是Java的注解，Spring框架支持该注解
	@Resource(name="customerDao")
	private CustomerDao customerDao;

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void save() {
		System.out.println("service");

		customerDao.save();
	}

	@PostConstruct  //相当于init-method
	public void init() {
		System.out.println("init~~~~~~~~~");
	}
	
	@PreDestroy    //相当于destroy-method
	public void destory() {
		System.out.println("destory~~~~~");
	}
}
