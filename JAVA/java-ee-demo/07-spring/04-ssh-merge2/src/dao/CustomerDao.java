package dao;

import domain.Customer;

public interface CustomerDao {

	public void add(Customer customer);

	public Customer find(long l);

	public void update(Customer customer);
}
