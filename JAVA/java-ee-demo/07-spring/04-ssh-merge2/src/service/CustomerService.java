package service;

import domain.Customer;

public interface CustomerService {

	public void add(Customer customer);

	public Customer find(long l);

	public void update(Customer customer);

}
