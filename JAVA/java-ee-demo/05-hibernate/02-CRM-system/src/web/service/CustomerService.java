package web.service;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.CustomerDao;
import domain.Customer;
import utils.HibernateUtils;

public class CustomerService {

	public void add(Customer customer) {
		new CustomerDao().add(customer);
	}

	public List<Customer> findAll() {

		return new CustomerDao().findAll();
	}

	public void delete(Long cust_user_id) {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();
		try {
			new CustomerDao().delete(cust_user_id);
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
	}

	public Customer findById(Long cust_user_id) {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Customer customer = new CustomerDao().findById(cust_user_id);

		transaction.commit();
		return customer;
	}

	public void update(Map<String, String[]> map) throws Exception {
		new CustomerDao().update(map);
	}

}
