package action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import domain.Customer;
import service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private Customer customer = new Customer();

	@Override
	public Customer getModel() {
		return customer;
	}

	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	/**
	 * 方式一WebApplicationContext
	 * 
	 * @return
	 */
//	public String add() {
//		System.out.println("action" + customer);
//
//		WebApplicationContext applicationContext = WebApplicationContextUtils
//				.getWebApplicationContext(ServletActionContext.getServletContext());
//
//		CustomerService customerService = (CustomerService) applicationContext.getBean("customerService");
//
//		customerService.add(customer);
//		return NONE;
//	}

	public String add() {
		System.out.println("action" + customer);

		customerService.add(customer);

		return NONE;
	}
}
