package spring.DependencyInjection;

public class CustomerServiceImpl {

	private CustomerDaoImpl customerDao;

	public void setCustomerDao(CustomerDaoImpl customerDao) {
		this.customerDao = customerDao;
	}

	public CustomerDaoImpl getCustomerDao() {
		return customerDao;
	}

	public void save() {
		System.out.println("CustomerServiceImpl save");
		customerDao.save();
	}

}
