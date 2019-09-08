package service;

import org.springframework.transaction.annotation.Transactional;

import dao.CustomerDao;
import domain.Customer;

@Transactional // 开启事务
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerdao;

	public void setCustomerdao(CustomerDao customerdao) {
		this.customerdao = customerdao;
	}

	@Override
	public void add(Customer customer) {
		System.out.println("CustomerServiceImpl");
		customerdao.add(customer);
	}

	@Override
	public Customer find(long l) {
		return customerdao.find(l);
	}

	@Override
	public void update(Customer customer) {
		customerdao.update(customer);
	}

}
