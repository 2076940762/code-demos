package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.Customer;
import domain.Linkman;
import utils.HibernateUtils;

public class linkmanDao {

	public void add(Long cust_id, Linkman linkman) {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Customer customer = session.get(Customer.class, cust_id);
		linkman.setCustomer(customer);

		session.save(linkman);
		transaction.commit();

	}

}
