package dao;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import domain.Customer;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	/*
	 * private HibernateTemplate hibernateTemplate; public void
	 * setHibernateTemplate(HibernateTemplate hibernateTemplate) {
	 * this.hibernateTemplate = hibernateTemplate; }
	 */

	@Override
	public void add(Customer customer) {
		System.out.println("CustomerDaoImpl");
		getHibernateTemplate().save(customer);
	}

	@Override
	public Customer find(long l) {
		return getHibernateTemplate().get(Customer.class, l);
	}

	@Override
	public void update(Customer customer) {
		getHibernateTemplate().update(customer);
	}

}
