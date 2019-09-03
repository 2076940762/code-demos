package dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import domain.Customer;
import utils.HibernateUtils;

public class CustomerDao {

	public void add(Customer customer) {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();

		session.save(customer);

		transaction.commit();
		session.close();
	}

	public List<Customer> findAll() {
//		Session session = HibernateUtils.getCurrSession();
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		
		Query query = session.createQuery("from Customer");
		
		List list = query.list();
		transaction.commit();
		return list;
	}

	public void delete(Long cust_user_id) {
		Session session = HibernateUtils.getCurrSession();

		Query query = session.createQuery(" from Customer where cust_id= ? ");
		query.setLong(0, cust_user_id);
		List list = query.list();

		if (list == null || list.size() == 0) {
			throw new RuntimeException();
		}

		Customer customer = (Customer) list.get(0);
		session.delete(customer);
	}

	public Customer findById(Long cust_user_id) {
		Session session = HibernateUtils.getCurrSession();

		Criteria criteria = session.createCriteria(Customer.class);
		criteria.add(Restrictions.eq("cust_id", cust_user_id));
		List<Customer> list = criteria.list();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	public void update(Map<String, String[]> map)  {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getCurrSession();
			transaction = session.beginTransaction();

			Customer customer = findById(Long.parseLong((String) map.get("cust_id")[0]));
			BeanUtils.populate(customer, map);
			session.update(customer);

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
//			session.close();
		}
	}

}
