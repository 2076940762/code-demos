package testing;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import domain.Customer;
import utils.HibernateUtils;

public class hello_hibernate_test {

	@Test
	public void f1() {
		// 加载配置文件
		Configuration configuration = new Configuration();
		configuration.configure();

		// 如果使用properties文件需要手动加载配置文件
//		configuration.addResource("hibernate.cfg.xml")

//          创建sessionfactory
		SessionFactory sessionFactory = configuration.buildSessionFactory();

//          创建session
		Session session = sessionFactory.openSession();

//          开启事务
		Transaction transaction = session.beginTransaction();

//		修改表数据
		Customer customer = new Customer();
//		customer.setCust_create_id((long) 1111111111);
//		customer.setCust_industry("it");
//		customer.setCust_mobile("145643312");
		customer.setCust_name("王麻子");

//		保存数据
		session.save(customer);

//          提交事务
		transaction.commit();
		session.close();
	}

	@Test
	public void f2() {

		Session session = HibernateUtils.getSession();
		session.beginTransaction();
		Customer customer = new Customer();
		customer.setCust_name("李自成");
		session.save(customer);
		session.close();

	}

	@Test
	public void testGet() {
		Session session = HibernateUtils.getSession();
		session.beginTransaction();

		Customer customer = session.get(Customer.class, 1L);
		System.out.println(customer);

		session.close();

	}

	@Test
	public void testDel() {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		Customer customer = session.get(Customer.class, 1L);
		session.delete(customer);
		transaction.commit();
		session.close();
	}

	@Test
	public void testModify() {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		Customer customer = session.get(Customer.class, 2L);
		customer.setCust_name("唐僧");
		transaction.commit();
		session.close();

	}

	@Test
	public void testSaveOrUpdate() {
		Session session =HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setCust_name("如来佛祖");
		
		session.saveOrUpdate(customer);
		
		Customer customer2 = session.get(Customer.class, 2L);
		customer2.setCust_phone("123654987");

		session.saveOrUpdate(customer2);
		 
		transaction.commit();
		session.close();
	}

	
	
}
