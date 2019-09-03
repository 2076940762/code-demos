package query;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import domain.Customer;
import domain.Linkman;
import utils.HibernateUtils;

public class query_demo {

//	SELECT cust_id ,cust_name,lkm_id,lkm_name FROM cst_customer A,cst_linkman B WHERE A.`cust_id`=B.`lkm_cust_id`;
//	cust_id  cust_name  lkm_id  lkm_name   
//	-------  ---------  ------  -----------
//	     11  马云云             7  熊大     
//	     11  马云云             8  王建立  
//	     12  韦小宝             9  令狐冲  
//	     12  韦小宝            10  杨过
	@Test
	public void f1() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

//		通过oid方式查询
		Customer customer = session.get(Customer.class, 11L);

//		对象导航方式查询
		System.out.println(customer.getLinkmans());

		transaction.commit();

//		select customer0_.cust_id as cust_id1_0_0_, customer0_.version as version2_0_0_, customer0_.cust_name as cust_nam3_0_0_, customer0_.cust_user_id as cust_use4_0_0_, customer0_.cust_create_id as cust_cre5_0_0_, customer0_.cust_source as cust_sou6_0_0_, customer0_.cust_industry as cust_ind7_0_0_, customer0_.cust_level as cust_lev8_0_0_, customer0_.cust_linkman as cust_lin9_0_0_, customer0_.cust_phone as cust_ph10_0_0_, customer0_.cust_mobile as cust_mo11_0_0_ 
//		from cst_customer customer0_ where customer0_.cust_id=11
//			
//		select linkmans0_.lkm_cust_id as lkm_cus11_1_0_, linkmans0_.lkm_id as lkm_id1_1_0_, linkmans0_.lkm_id as lkm_id1_1_1_, linkmans0_.version as version2_1_1_, linkmans0_.lkm_name as lkm_name3_1_1_, linkmans0_.lkm_gender as lkm_gend4_1_1_, linkmans0_.lkm_phone as lkm_phon5_1_1_, linkmans0_.lkm_mobile as lkm_mobi6_1_1_, linkmans0_.lkm_email as lkm_emai7_1_1_, linkmans0_.lkm_qq as lkm_qq8_1_1_, linkmans0_.lkm_position as lkm_posi9_1_1_, linkmans0_.lkm_memo as lkm_mem10_1_1_, linkmans0_.lkm_cust_id as lkm_cus11_1_1_ 
//		from cst_linkman linkmans0_ where linkmans0_.lkm_cust_id=11
//			

	}

	/*
	 * HQL
	 */

	/**
	 * 基本HQL查询
	 */
	@Test
	public void f2() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("from Customer");
		List<Customer> list = query.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}

		transaction.commit();
	}

	/**
	 * 排序
	 */
	@Test
	public void f3() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		List<Linkman> list = session.createQuery(" from Linkman order by lkm_id desc ").list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}
//		Linkman [lkm_id=10, lkm_name=杨过, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=9, lkm_name=令狐冲, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=7, lkm_name=熊大, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]

		transaction.commit();
	}

	/**
	 * 分页查询 每页两条数据，从第一条开始
	 */
	@Test
	public void f4() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery(" from Linkman order by lkm_id asc ");
		query.setFirstResult(0);
		query.setMaxResults(2);

		List<Linkman> list = null;
		list = query.list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}
//		Linkman [lkm_id=7, lkm_name=熊大, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]

		query.setFirstResult(2);
		list = query.list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}
//		Linkman [lkm_id=9, lkm_name=令狐冲, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=10, lkm_name=杨过, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]

		transaction.commit();
	}

	/**
	 * 条件查询
	 */
	@Test
	public void f5() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery(" from Linkman A  where  A.customer.cust_id = ? order by lkm_id desc ");
//		query.setLong(0, 11L);
		query.setParameter(0, 11L);

		List<Linkman> list = query.list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}

//		Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=7, lkm_name=熊大, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]

		transaction.commit();
	}

	/**
	 * 简单投影查询
	 */
	@Test
	public void f6() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("select A.lkm_id ,A.lkm_name from Linkman A");
		List<Object[]> list = query.list();
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
		}

		transaction.commit();

//		Hibernate: 
//		    select
//		        linkman0_.lkm_id as col_0_0_,
//		        linkman0_.lkm_name as col_1_0_ 
//		    from
//		        cst_linkman linkman0_
//		[7, 熊大]
//		[8, 王建立]
//		[9, 令狐冲]
//		[10, 杨过]
	}

	/**
	 * 投影查询结果封装为对象 需要提供只有所查询字段的构造函数
	 */
	@Test
	public void f7() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery(" select new Linkman(A.lkm_id ,A.lkm_name) from Linkman A ");
		List<Linkman> list = query.list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}

		transaction.commit();

//		Linkman [lkm_id=7, lkm_name=熊大, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=null]
//		Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=null]
//		Linkman [lkm_id=9, lkm_name=令狐冲, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=null]
//		Linkman [lkm_id=10, lkm_name=杨过, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=null]

	}

	/**
	 * 聚合函数
	 */
	@Test
	public void f8() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("select count(*) from Linkman a");
		List<Number> list = query.list();

		if (list != null && list.size() != 0) {
			System.out.println("count(*)  " + list.get(0).intValue());
		}
		transaction.commit();
	}

	/**
	 * QBC查询
	 */

	/*
	 * 基本的Criteria查询
	 */
	@Test
	public void f9() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Criteria criteria = session.createCriteria(Linkman.class);
		List<Linkman> list = criteria.list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}

		transaction.commit();

//		Linkman [lkm_id=7, lkm_name=熊大, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=9, lkm_name=令狐冲, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=10, lkm_name=杨过, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]

	}

	/**
	 * 排序查询
	 */
	@Test
	public void f10() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Criteria criteria = session.createCriteria(Linkman.class);

//		降序排列
		criteria.addOrder(Order.desc("lkm_id"));

		List<Linkman> list = criteria.list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}

		transaction.commit();

//		Linkman [lkm_id=10, lkm_name=杨过, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=9, lkm_name=令狐冲, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=7, lkm_name=熊大, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]

	}

	/**
	 * 分页查询
	 */
	@Test
	public void f11() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Criteria criteria = session.createCriteria(Linkman.class);

//		降序排列
		criteria.addOrder(Order.desc("lkm_id"));

//		分页
		criteria.setFirstResult(1);
		criteria.setMaxResults(3);

		List<Linkman> list = criteria.list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}

		transaction.commit();

//		Linkman [lkm_id=9, lkm_name=令狐冲, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=7, lkm_name=熊大, lkm_gender=null, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]

	}

//	条件查询（Criterion是查询条件的接口，Restrictions类是Hibernate框架提供的工具类，使用该工具类来设置查询条件）
//    * 条件查询使用Criteria接口的add方法，用来传入条件。
//    * 使用Restrictions的添加条件的方法，来添加条件，例如：
//        * Restrictions.eq           -- 相等
//        * Restrictions.gt           -- 大于号
//        * Restrictions.ge           -- 大于等于
//        * Restrictions.lt           -- 小于
//        * Restrictions.le           -- 小于等于
//        * Restrictions.between      -- 在之间
//        * Restrictions.like         -- 模糊查询
//        * Restrictions.in           -- 范围
//        * Restrictions.and          -- 并且
//        * Restrictions.or           -- 或者

	/**
	 * 基本条件查询
	 */
	@Test
	public void f12() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Criteria criteria = session.createCriteria(Linkman.class);

		criteria.add(Restrictions.ilike("lkm_name", "%许%"));

		List<Linkman> list = criteria.list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}

		transaction.commit();

//		Linkman [lkm_id=11, lkm_name=许仙, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]h

	}

	/**
	 * in
	 */
	@Test
	public void f13() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Criteria criteria = session.createCriteria(Linkman.class);

		criteria.add(Restrictions.in("lkm_id", new Long[] { 8L, 10L }));

		List<Linkman> list = criteria.list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}

		transaction.commit();

//		Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=10, lkm_name=杨过, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]

	}

	/**
	 * or
	 */
	@Test
	public void f14() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Criteria criteria = session.createCriteria(Linkman.class);

		criteria.add(Restrictions.or(Restrictions.gt("lkm_id", 8L), Restrictions.eq("lkm_gender", "女")));

		List<Linkman> list = criteria.list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}

		transaction.commit();

//		Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=9, lkm_name=令狐冲, lkm_gender=男, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=10, lkm_name=杨过, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=11, lkm_name=许仙, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]

	}

	/**
	 * 聚合函数
	 */
	@Test
	public void f15() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Criteria criteria = session.createCriteria(Linkman.class);
		criteria.add(Restrictions.ilike("lkm_gender", "女"));

//		聚合函数
		criteria.setProjection(Projections.rowCount());
		List list2 = criteria.list();
		if (list2 == null || list2.isEmpty()) {
			System.out.println("没有性别为女的联系人");
		} else {
			System.out.println("性别为女的联系人有" + list2.get(0) + "个");
		}

		criteria.setProjection(null);
		List<Linkman> list = criteria.list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}

		transaction.commit();
//		性别为女的联系人有3个
//		Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=10, lkm_name=杨过, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=11, lkm_name=许仙, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]

	}

	/**
	 * 离线查询
	 */
	@Test
	public void f16() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
		criteria.add(Restrictions.eq("lkm_gender", "女"));

		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		List<Linkman> list = criteria.getExecutableCriteria(session).list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}

		transaction.commit();

//		Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=10, lkm_name=杨过, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=11, lkm_name=许仙, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]

	}

	/*
	 * SQL查询
	 */
	@Test
	public void f17() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		SQLQuery sqlQuery = session.createSQLQuery(" select * from cst_linkman ");
		List<Object[]> list = sqlQuery.list();
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
		}

		transaction.commit();

//		[7, 0, 熊大, 男, null, null, null, null, null, null, 11]
//		[8, 0, 王建立, 女, null, null, null, null, null, null, 11]
//		[9, 0, 令狐冲, 男, null, null, null, null, null, null, 12]
//		[10, 0, 杨过, 女, null, null, null, null, null, null, 12]
//		[11, 0, 许仙, 女, null, null, null, null, null, null, null]
	}

	/**
	 * sql查询结果封装为对象
	 */
	@Test
	public void f18() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		SQLQuery sqlQuery = session.createSQLQuery("select * from cst_linkman where lkm_gender like ? ");
		sqlQuery.setParameter(0, "女");
		sqlQuery.addEntity(Linkman.class);

		List<Linkman> list = sqlQuery.list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}

		transaction.commit();

//		Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=10, lkm_name=杨过, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=11, lkm_name=许仙, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]

	}

	/*
	 * 多表查询
	 */

	/**
	 * 默认情况下List<Object[]>
	 */
	@Test
	public void f19() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("from Customer C inner join C.linkmans");
		List<Object[]> list = query.list();
		for (Object[] objects : list) {
			System.out.println(Arrays.deepToString(objects));
		}

		transaction.commit();

//		[Customer [cust_id=11, cust_name=马云云, cust_user_id=null, cust_create_id=null, cust_source=null, cust_industry=null, cust_level=null, cust_linkman=null, cust_phone=null, cust_mobile=null, cust_address=null, cust_zip=null, cust_fax=null, cust_website=null, version=0], Linkman [lkm_id=7, lkm_name=熊大, lkm_gender=男, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]]
//		[Customer [cust_id=11, cust_name=马云云, cust_user_id=null, cust_create_id=null, cust_source=null, cust_industry=null, cust_level=null, cust_linkman=null, cust_phone=null, cust_mobile=null, cust_address=null, cust_zip=null, cust_fax=null, cust_website=null, version=0], Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]]
//		[Customer [cust_id=12, cust_name=韦小宝, cust_user_id=null, cust_create_id=null, cust_source=null, cust_industry=null, cust_level=null, cust_linkman=null, cust_phone=null, cust_mobile=null, cust_address=null, cust_zip=null, cust_fax=null, cust_website=null, version=0], Linkman [lkm_id=9, lkm_name=令狐冲, lkm_gender=男, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]]
//		[Customer [cust_id=12, cust_name=韦小宝, cust_user_id=null, cust_create_id=null, cust_source=null, cust_industry=null, cust_level=null, cust_linkman=null, cust_phone=null, cust_mobile=null, cust_address=null, cust_zip=null, cust_fax=null, cust_website=null, version=0], Linkman [lkm_id=10, lkm_name=杨过, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]]

	}

	/**
	 * 使用 fetch 获取对象 不使用fetch会报错
	 */
	@Test
	public void f20() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("from Customer C inner join fetch C.linkmans");
		List<Customer> list = query.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}

		transaction.commit();

//		Customer [cust_id=11, cust_name=马云云, cust_user_id=null, cust_create_id=null, cust_source=null, cust_industry=null, cust_level=null, cust_linkman=null, cust_phone=null, cust_mobile=null, cust_address=null, cust_zip=null, cust_fax=null, cust_website=null, version=0, linkmans=[Linkman [lkm_id=7, lkm_name=熊大, lkm_gender=男, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0], Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]]]
//		Customer [cust_id=11, cust_name=马云云, cust_user_id=null, cust_create_id=null, cust_source=null, cust_industry=null, cust_level=null, cust_linkman=null, cust_phone=null, cust_mobile=null, cust_address=null, cust_zip=null, cust_fax=null, cust_website=null, version=0, linkmans=[Linkman [lkm_id=7, lkm_name=熊大, lkm_gender=男, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0], Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]]]
//		Customer [cust_id=12, cust_name=韦小宝, cust_user_id=null, cust_create_id=null, cust_source=null, cust_industry=null, cust_level=null, cust_linkman=null, cust_phone=null, cust_mobile=null, cust_address=null, cust_zip=null, cust_fax=null, cust_website=null, version=0, linkmans=[Linkman [lkm_id=9, lkm_name=令狐冲, lkm_gender=男, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0], Linkman [lkm_id=10, lkm_name=杨过, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]]]
//		Customer [cust_id=12, cust_name=韦小宝, cust_user_id=null, cust_create_id=null, cust_source=null, cust_industry=null, cust_level=null, cust_linkman=null, cust_phone=null, cust_mobile=null, cust_address=null, cust_zip=null, cust_fax=null, cust_website=null, version=0, linkmans=[Linkman [lkm_id=9, lkm_name=令狐冲, lkm_gender=男, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0], Linkman [lkm_id=10, lkm_name=杨过, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]]]

	}

	/**
	 * 使用 fetch 获取对象 不使用fetch会报错
	 */
	@Test
	public void f21() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("from Customer C inner join fetch C.linkmans");
		List<Customer> list = query.list();

		HashSet<Customer> set = new HashSet<Customer>(list);
		for (Customer customer : set) {
			System.out.println(customer);
		}

		transaction.commit();

//		cust_id  cust_name  lkm_id  lkm_name   
//		-------  ---------  ------  -----------
//		     11  马云云             7  熊大     
//		     11  马云云             8  王建立  
//		     12  韦小宝             9  令狐冲  
//		     12  韦小宝            10  杨过    

//		Customer [cust_id=12, cust_name=韦小宝, cust_user_id=null, cust_create_id=null, cust_source=null, cust_industry=null, cust_level=null, cust_linkman=null, cust_phone=null, cust_mobile=null, cust_address=null, cust_zip=null, cust_fax=null, cust_website=null, version=0,
//		linkmans=[Linkman [lkm_id=9, lkm_name=令狐冲, lkm_gender=男, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0], 
//		               Linkman [lkm_id=10, lkm_name=杨过, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]]]

//		Customer [cust_id=11, cust_name=马云云, cust_user_id=null, cust_create_id=null, cust_source=null, cust_industry=null, cust_level=null, cust_linkman=null, cust_phone=null, cust_mobile=null, cust_address=null, cust_zip=null, cust_fax=null, cust_website=null, version=0,
//		linkmans=[Linkman [lkm_id=7, lkm_name=熊大, lkm_gender=男, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0], 
//		                Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]]]

	}

	/**
	 * 延迟加载
	 */

	/**
	 * load 方法默认延迟加载
	 */
	@Test
	public void f22() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("***********************************************");
		Customer customer = session.load(Customer.class, 11L); // 在这里并没有查询数据库
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(customer.getCust_name()); // 在用到数据时才去查询数据库
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

		System.out.println("-------------------------------------");
		Linkman linkman = session.get(Linkman.class, 11L); // 在这里访问数据库
		System.out.println("+++++++++++++++++++++++");
		System.out.println(linkman.getLkm_name());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");

		transaction.commit();
	}

//	配置延时加载
//	<class name="domain.Customer" table="cst_customer" lazy="true">

	/**
	 * 级联查询
	 */
	@Test
	public void f23() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		Customer customer = session.load(Customer.class, 12L);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(customer.getLinkmans()); // 只在这里才查询数据库
		System.out.println("<><><><><><><><><><><><><><><><>");

		transaction.commit();
	}

	@Test
	public void f24() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		Customer customer = session.get(Customer.class, 12L); // sql
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(customer.getLinkmans()); // sql
		System.out.println("<><><><><><><><><><><><><><><><>");

		transaction.commit();
	}

//	<class name="domain.Customer" table="cst_customer" lazy="false">
	@Test
	public void f25() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		Customer customer = session.load(Customer.class, 12L); // sql
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(customer.getLinkmans()); // sql
		System.out.println("<><><><><><><><><><><><><><><><>");

		transaction.commit();
	}

	@Test
	public void f26() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		Customer customer = session.get(Customer.class, 12L); // sql
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(customer.getLinkmans()); // sql
		System.out.println("<><><><><><><><><><><><><><><><>");

		transaction.commit();
	}

	/*********************************************************************************************************************/
//	                                                    set 标签
	/*********************************************************************************************************************/
	/* fetch && lazy */
	/* 默认值 select && true */

	/**
	 * fetch:select lazy:true
	 * <set name="linkmans" inverse="false" cascade="save-update,delete" fetch=
	 * "select" lazy="true" >
	 */
	@Test
	public void f27() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("************************start************************");
		Customer customer = session.get(Customer.class, 11L);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Set<Linkman> set = customer.getLinkmans();
		System.out.println("=================================");
		for (Linkman linkman : set) {
			System.out.println(linkman);
		}
		transaction.commit();
		System.out.println("************************end************************");

//		************************start************************
//		Hibernate: 
//		    select
//		        customer0_.cust_id as cust_id1_0_0_,
//		        customer0_.version as version2_0_0_,
//		        customer0_.cust_name as cust_nam3_0_0_,
//		        customer0_.cust_user_id as cust_use4_0_0_,
//		        customer0_.cust_create_id as cust_cre5_0_0_,
//		        customer0_.cust_source as cust_sou6_0_0_,
//		        customer0_.cust_industry as cust_ind7_0_0_,
//		        customer0_.cust_level as cust_lev8_0_0_,
//		        customer0_.cust_linkman as cust_lin9_0_0_,
//		        customer0_.cust_phone as cust_ph10_0_0_,
//		        customer0_.cust_mobile as cust_mo11_0_0_ 
//		    from
//		        cst_customer customer0_ 
//		    where
//		        customer0_.cust_id=?
//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		=================================
//		Hibernate: 
//		    select
//		        linkmans0_.lkm_cust_id as lkm_cus11_1_0_,
//		        linkmans0_.lkm_id as lkm_id1_1_0_,
//		        linkmans0_.lkm_id as lkm_id1_1_1_,
//		        linkmans0_.version as version2_1_1_,
//		        linkmans0_.lkm_name as lkm_name3_1_1_,
//		        linkmans0_.lkm_gender as lkm_gend4_1_1_,
//		        linkmans0_.lkm_phone as lkm_phon5_1_1_,
//		        linkmans0_.lkm_mobile as lkm_mobi6_1_1_,
//		        linkmans0_.lkm_email as lkm_emai7_1_1_,
//		        linkmans0_.lkm_qq as lkm_qq8_1_1_,
//		        linkmans0_.lkm_position as lkm_posi9_1_1_,
//		        linkmans0_.lkm_memo as lkm_mem10_1_1_,
//		        linkmans0_.lkm_cust_id as lkm_cus11_1_1_ 
//		    from
//		        cst_linkman linkmans0_ 
//		    where
//		        linkmans0_.lkm_cust_id=?
//		Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=7, lkm_name=熊大, lkm_gender=男, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		************************end************************
	}

	/**
	 * fetch: select lazy: false
	 * <set name="linkmans" inverse="false" cascade="save-update,delete" fetch=
	 * "select" lazy="false" >
	 */
	@Test
	public void f28() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("************************start************************");
		Customer customer = session.get(Customer.class, 11L);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Set<Linkman> set = customer.getLinkmans();
		System.out.println("=================================");
		for (Linkman linkman : set) {
			System.out.println(linkman);
		}
		transaction.commit();
		System.out.println("************************end************************");

//		************************start************************
//		Hibernate: 
//		    select
//		        customer0_.cust_id as cust_id1_0_0_,
//		        customer0_.version as version2_0_0_,
//		        customer0_.cust_name as cust_nam3_0_0_,
//		        customer0_.cust_user_id as cust_use4_0_0_,
//		        customer0_.cust_create_id as cust_cre5_0_0_,
//		        customer0_.cust_source as cust_sou6_0_0_,
//		        customer0_.cust_industry as cust_ind7_0_0_,
//		        customer0_.cust_level as cust_lev8_0_0_,
//		        customer0_.cust_linkman as cust_lin9_0_0_,
//		        customer0_.cust_phone as cust_ph10_0_0_,
//		        customer0_.cust_mobile as cust_mo11_0_0_ 
//		    from
//		        cst_customer customer0_ 
//		    where
//		        customer0_.cust_id=?
//		Hibernate: 
//		    select
//		        linkmans0_.lkm_cust_id as lkm_cus11_1_0_,
//		        linkmans0_.lkm_id as lkm_id1_1_0_,
//		        linkmans0_.lkm_id as lkm_id1_1_1_,
//		        linkmans0_.version as version2_1_1_,
//		        linkmans0_.lkm_name as lkm_name3_1_1_,
//		        linkmans0_.lkm_gender as lkm_gend4_1_1_,
//		        linkmans0_.lkm_phone as lkm_phon5_1_1_,
//		        linkmans0_.lkm_mobile as lkm_mobi6_1_1_,
//		        linkmans0_.lkm_email as lkm_emai7_1_1_,
//		        linkmans0_.lkm_qq as lkm_qq8_1_1_,
//		        linkmans0_.lkm_position as lkm_posi9_1_1_,
//		        linkmans0_.lkm_memo as lkm_mem10_1_1_,
//		        linkmans0_.lkm_cust_id as lkm_cus11_1_1_ 
//		    from
//		        cst_linkman linkmans0_ 
//		    where
//		        linkmans0_.lkm_cust_id=?
//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		=================================
//		Linkman [lkm_id=7, lkm_name=熊大, lkm_gender=男, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		************************end************************
	}

	/**
	 * fetch: select lazy: false
	 * <set name="linkmans" inverse="false" cascade="save-update,delete" fetch=
	 * "select" lazy="false" >
	 */
	@Test
	public void f28a() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("************************start************************");
		Customer customer = session.get(Customer.class, 11L);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(customer.getLinkmans().size());
		transaction.commit();
		System.out.println("************************end************************");

//		************************start************************
//		Hibernate: 
//		    select
//		        customer0_.cust_id as cust_id1_0_0_,
//		        customer0_.version as version2_0_0_,
//		        customer0_.cust_name as cust_nam3_0_0_,
//		        customer0_.cust_user_id as cust_use4_0_0_,
//		        customer0_.cust_create_id as cust_cre5_0_0_,
//		        customer0_.cust_source as cust_sou6_0_0_,
//		        customer0_.cust_industry as cust_ind7_0_0_,
//		        customer0_.cust_level as cust_lev8_0_0_,
//		        customer0_.cust_linkman as cust_lin9_0_0_,
//		        customer0_.cust_phone as cust_ph10_0_0_,
//		        customer0_.cust_mobile as cust_mo11_0_0_ 
//		    from
//		        cst_customer customer0_ 
//		    where
//		        customer0_.cust_id=?
//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		Hibernate: 
//		    select
//		        count(lkm_id) 
//		    from
//		        cst_linkman 
//		    where
//		        lkm_cust_id =?
//		2
//		************************end************************
	}

	/**
	 * fetch: join ; lazy: true
	 * <set name="linkmans" inverse="false" cascade="save-update,delete" fetch=
	 * "join" lazy="true" >
	 */
	@Test
	public void f29() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("************************start************************");
		Customer customer = session.get(Customer.class, 11L);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Set<Linkman> set = customer.getLinkmans();
		System.out.println("=================================");
		for (Linkman linkman : set) {
			System.out.println(linkman);
		}
		transaction.commit();
		System.out.println("************************end************************");

//		************************start************************
//		Hibernate: 
//		    select
//		        customer0_.cust_id as cust_id1_0_0_,
//		        customer0_.version as version2_0_0_,
//		        customer0_.cust_name as cust_nam3_0_0_,
//		        customer0_.cust_user_id as cust_use4_0_0_,
//		        customer0_.cust_create_id as cust_cre5_0_0_,
//		        customer0_.cust_source as cust_sou6_0_0_,
//		        customer0_.cust_industry as cust_ind7_0_0_,
//		        customer0_.cust_level as cust_lev8_0_0_,
//		        customer0_.cust_linkman as cust_lin9_0_0_,
//		        customer0_.cust_phone as cust_ph10_0_0_,
//		        customer0_.cust_mobile as cust_mo11_0_0_,
//		        linkmans1_.lkm_cust_id as lkm_cus11_1_1_,
//		        linkmans1_.lkm_id as lkm_id1_1_1_,
//		        linkmans1_.lkm_id as lkm_id1_1_2_,
//		        linkmans1_.version as version2_1_2_,
//		        linkmans1_.lkm_name as lkm_name3_1_2_,
//		        linkmans1_.lkm_gender as lkm_gend4_1_2_,
//		        linkmans1_.lkm_phone as lkm_phon5_1_2_,
//		        linkmans1_.lkm_mobile as lkm_mobi6_1_2_,
//		        linkmans1_.lkm_email as lkm_emai7_1_2_,
//		        linkmans1_.lkm_qq as lkm_qq8_1_2_,
//		        linkmans1_.lkm_position as lkm_posi9_1_2_,
//		        linkmans1_.lkm_memo as lkm_mem10_1_2_,
//		        linkmans1_.lkm_cust_id as lkm_cus11_1_2_ 
//		    from
//		        cst_customer customer0_ 
//		    left outer join
//		        cst_linkman linkmans1_ 
//		            on customer0_.cust_id=linkmans1_.lkm_cust_id 
//		    where
//		        customer0_.cust_id=?
//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		=================================
//		Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=7, lkm_name=熊大, lkm_gender=男, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		************************end************************
	}

	/**
	 * fetch: join ; lazy: false
	 * <set name="linkmans" inverse="false" cascade="save-update,delete" fetch=
	 * "join" lazy="false" >
	 */
	@Test
	public void f30() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("************************start************************");
		Customer customer = session.get(Customer.class, 11L);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Set<Linkman> set = customer.getLinkmans();
		System.out.println("=================================");
		for (Linkman linkman : set) {
			System.out.println(linkman);
		}
		transaction.commit();
		System.out.println("************************end************************");

//		************************start************************
//		Hibernate: 
//		    select
//		        customer0_.cust_id as cust_id1_0_0_,
//		        customer0_.version as version2_0_0_,
//		        customer0_.cust_name as cust_nam3_0_0_,
//		        customer0_.cust_user_id as cust_use4_0_0_,
//		        customer0_.cust_create_id as cust_cre5_0_0_,
//		        customer0_.cust_source as cust_sou6_0_0_,
//		        customer0_.cust_industry as cust_ind7_0_0_,
//		        customer0_.cust_level as cust_lev8_0_0_,
//		        customer0_.cust_linkman as cust_lin9_0_0_,
//		        customer0_.cust_phone as cust_ph10_0_0_,
//		        customer0_.cust_mobile as cust_mo11_0_0_,
//		        linkmans1_.lkm_cust_id as lkm_cus11_1_1_,
//		        linkmans1_.lkm_id as lkm_id1_1_1_,
//		        linkmans1_.lkm_id as lkm_id1_1_2_,
//		        linkmans1_.version as version2_1_2_,
//		        linkmans1_.lkm_name as lkm_name3_1_2_,
//		        linkmans1_.lkm_gender as lkm_gend4_1_2_,
//		        linkmans1_.lkm_phone as lkm_phon5_1_2_,
//		        linkmans1_.lkm_mobile as lkm_mobi6_1_2_,
//		        linkmans1_.lkm_email as lkm_emai7_1_2_,
//		        linkmans1_.lkm_qq as lkm_qq8_1_2_,
//		        linkmans1_.lkm_position as lkm_posi9_1_2_,
//		        linkmans1_.lkm_memo as lkm_mem10_1_2_,
//		        linkmans1_.lkm_cust_id as lkm_cus11_1_2_ 
//		    from
//		        cst_customer customer0_ 
//		    left outer join
//		        cst_linkman linkmans1_ 
//		            on customer0_.cust_id=linkmans1_.lkm_cust_id 
//		    where
//		        customer0_.cust_id=?
//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		=================================
//		Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=7, lkm_name=熊大, lkm_gender=男, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		************************end************************
	}

	/**
	 * fetch: join ; lazy: extra
	 * <set name="linkmans" inverse="false" cascade="save-update,delete" fetch=
	 * "join" lazy="extra" >
	 */
	@Test
	public void f31() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("************************start************************");
		Customer customer = session.get(Customer.class, 11L);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Set<Linkman> set = customer.getLinkmans();
		System.out.println("=================================");
		for (Linkman linkman : set) {
			System.out.println(linkman);
		}
		transaction.commit();
		System.out.println("************************end************************");

//		************************start************************
//		Hibernate: 
//		    select
//		        customer0_.cust_id as cust_id1_0_0_,
//		        customer0_.version as version2_0_0_,
//		        customer0_.cust_name as cust_nam3_0_0_,
//		        customer0_.cust_user_id as cust_use4_0_0_,
//		        customer0_.cust_create_id as cust_cre5_0_0_,
//		        customer0_.cust_source as cust_sou6_0_0_,
//		        customer0_.cust_industry as cust_ind7_0_0_,
//		        customer0_.cust_level as cust_lev8_0_0_,
//		        customer0_.cust_linkman as cust_lin9_0_0_,
//		        customer0_.cust_phone as cust_ph10_0_0_,
//		        customer0_.cust_mobile as cust_mo11_0_0_,
//		        linkmans1_.lkm_cust_id as lkm_cus11_1_1_,
//		        linkmans1_.lkm_id as lkm_id1_1_1_,
//		        linkmans1_.lkm_id as lkm_id1_1_2_,
//		        linkmans1_.version as version2_1_2_,
//		        linkmans1_.lkm_name as lkm_name3_1_2_,
//		        linkmans1_.lkm_gender as lkm_gend4_1_2_,
//		        linkmans1_.lkm_phone as lkm_phon5_1_2_,
//		        linkmans1_.lkm_mobile as lkm_mobi6_1_2_,
//		        linkmans1_.lkm_email as lkm_emai7_1_2_,
//		        linkmans1_.lkm_qq as lkm_qq8_1_2_,
//		        linkmans1_.lkm_position as lkm_posi9_1_2_,
//		        linkmans1_.lkm_memo as lkm_mem10_1_2_,
//		        linkmans1_.lkm_cust_id as lkm_cus11_1_2_ 
//		    from
//		        cst_customer customer0_ 
//		    left outer join
//		        cst_linkman linkmans1_ 
//		            on customer0_.cust_id=linkmans1_.lkm_cust_id 
//		    where
//		        customer0_.cust_id=?
//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		=================================
//		Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=7, lkm_name=熊大, lkm_gender=男, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		************************end************************
	}

	/**
	 * fetch: subselect ; lazy: false
	 * <set name="linkmans" inverse="false" cascade="save-update,delete" fetch=
	 * "subselect" lazy="false" >
	 */
	@Test
	public void f32() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("************************start************************");
		Customer customer = session.get(Customer.class, 11L);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Set<Linkman> set = customer.getLinkmans();
		System.out.println("=================================");
		for (Linkman linkman : set) {
			System.out.println(linkman);
		}
		transaction.commit();
		System.out.println("************************end************************");

//		************************start************************
//		Hibernate: 
//		    select
//		        customer0_.cust_id as cust_id1_0_0_,
//		        customer0_.version as version2_0_0_,
//		        customer0_.cust_name as cust_nam3_0_0_,
//		        customer0_.cust_user_id as cust_use4_0_0_,
//		        customer0_.cust_create_id as cust_cre5_0_0_,
//		        customer0_.cust_source as cust_sou6_0_0_,
//		        customer0_.cust_industry as cust_ind7_0_0_,
//		        customer0_.cust_level as cust_lev8_0_0_,
//		        customer0_.cust_linkman as cust_lin9_0_0_,
//		        customer0_.cust_phone as cust_ph10_0_0_,
//		        customer0_.cust_mobile as cust_mo11_0_0_ 
//		    from
//		        cst_customer customer0_ 
//		    where
//		        customer0_.cust_id=?
//		Hibernate: 
//		    select
//		        linkmans0_.lkm_cust_id as lkm_cus11_1_0_,
//		        linkmans0_.lkm_id as lkm_id1_1_0_,
//		        linkmans0_.lkm_id as lkm_id1_1_1_,
//		        linkmans0_.version as version2_1_1_,
//		        linkmans0_.lkm_name as lkm_name3_1_1_,
//		        linkmans0_.lkm_gender as lkm_gend4_1_1_,
//		        linkmans0_.lkm_phone as lkm_phon5_1_1_,
//		        linkmans0_.lkm_mobile as lkm_mobi6_1_1_,
//		        linkmans0_.lkm_email as lkm_emai7_1_1_,
//		        linkmans0_.lkm_qq as lkm_qq8_1_1_,
//		        linkmans0_.lkm_position as lkm_posi9_1_1_,
//		        linkmans0_.lkm_memo as lkm_mem10_1_1_,
//		        linkmans0_.lkm_cust_id as lkm_cus11_1_1_ 
//		    from
//		        cst_linkman linkmans0_ 
//		    where
//		        linkmans0_.lkm_cust_id=?
//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		=================================
//		Linkman [lkm_id=7, lkm_name=熊大, lkm_gender=男, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		************************end************************
	}

	/**
	 * fetch: subselect ; lazy: true
	 * <set name="linkmans" inverse="false" cascade="save-update,delete" fetch=
	 * "subselect" lazy="true" >
	 */
	@Test
	public void f33() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("************************start************************");
		Customer customer = session.get(Customer.class, 11L);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Set<Linkman> set = customer.getLinkmans();
		System.out.println("=================================");
		for (Linkman linkman : set) {
			System.out.println(linkman);
		}
		transaction.commit();
		System.out.println("************************end************************");

//		************************start************************
//		Hibernate: 
//		    select
//		        customer0_.cust_id as cust_id1_0_0_,
//		        customer0_.version as version2_0_0_,
//		        customer0_.cust_name as cust_nam3_0_0_,
//		        customer0_.cust_user_id as cust_use4_0_0_,
//		        customer0_.cust_create_id as cust_cre5_0_0_,
//		        customer0_.cust_source as cust_sou6_0_0_,
//		        customer0_.cust_industry as cust_ind7_0_0_,
//		        customer0_.cust_level as cust_lev8_0_0_,
//		        customer0_.cust_linkman as cust_lin9_0_0_,
//		        customer0_.cust_phone as cust_ph10_0_0_,
//		        customer0_.cust_mobile as cust_mo11_0_0_ 
//		    from
//		        cst_customer customer0_ 
//		    where
//		        customer0_.cust_id=?
//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		=================================
//		Hibernate: 
//		    select
//		        linkmans0_.lkm_cust_id as lkm_cus11_1_0_,
//		        linkmans0_.lkm_id as lkm_id1_1_0_,
//		        linkmans0_.lkm_id as lkm_id1_1_1_,
//		        linkmans0_.version as version2_1_1_,
//		        linkmans0_.lkm_name as lkm_name3_1_1_,
//		        linkmans0_.lkm_gender as lkm_gend4_1_1_,
//		        linkmans0_.lkm_phone as lkm_phon5_1_1_,
//		        linkmans0_.lkm_mobile as lkm_mobi6_1_1_,
//		        linkmans0_.lkm_email as lkm_emai7_1_1_,
//		        linkmans0_.lkm_qq as lkm_qq8_1_1_,
//		        linkmans0_.lkm_position as lkm_posi9_1_1_,
//		        linkmans0_.lkm_memo as lkm_mem10_1_1_,
//		        linkmans0_.lkm_cust_id as lkm_cus11_1_1_ 
//		    from
//		        cst_linkman linkmans0_ 
//		    where
//		        linkmans0_.lkm_cust_id=?
//		Linkman [lkm_id=7, lkm_name=熊大, lkm_gender=男, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		************************end************************
	}

	/**
	 * fetch: subselect ; lazy: extra
	 * <set name="linkmans" inverse="false" cascade="save-update,delete" fetch=
	 * "subselect" lazy="extra" >
	 */
	@Test
	public void f34() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("************************start************************");
		Customer customer = session.get(Customer.class, 11L);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Set<Linkman> set = customer.getLinkmans();
		System.out.println("=================================");
		for (Linkman linkman : set) {
			System.out.println(linkman);
		}
		transaction.commit();
		System.out.println("************************end************************");

//		************************start************************
//		Hibernate: 
//		    select
//		        customer0_.cust_id as cust_id1_0_0_,
//		        customer0_.version as version2_0_0_,
//		        customer0_.cust_name as cust_nam3_0_0_,
//		        customer0_.cust_user_id as cust_use4_0_0_,
//		        customer0_.cust_create_id as cust_cre5_0_0_,
//		        customer0_.cust_source as cust_sou6_0_0_,
//		        customer0_.cust_industry as cust_ind7_0_0_,
//		        customer0_.cust_level as cust_lev8_0_0_,
//		        customer0_.cust_linkman as cust_lin9_0_0_,
//		        customer0_.cust_phone as cust_ph10_0_0_,
//		        customer0_.cust_mobile as cust_mo11_0_0_ 
//		    from
//		        cst_customer customer0_ 
//		    where
//		        customer0_.cust_id=?
//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		=================================
//		Hibernate: 
//		    select
//		        linkmans0_.lkm_cust_id as lkm_cus11_1_0_,
//		        linkmans0_.lkm_id as lkm_id1_1_0_,
//		        linkmans0_.lkm_id as lkm_id1_1_1_,
//		        linkmans0_.version as version2_1_1_,
//		        linkmans0_.lkm_name as lkm_name3_1_1_,
//		        linkmans0_.lkm_gender as lkm_gend4_1_1_,
//		        linkmans0_.lkm_phone as lkm_phon5_1_1_,
//		        linkmans0_.lkm_mobile as lkm_mobi6_1_1_,
//		        linkmans0_.lkm_email as lkm_emai7_1_1_,
//		        linkmans0_.lkm_qq as lkm_qq8_1_1_,
//		        linkmans0_.lkm_position as lkm_posi9_1_1_,
//		        linkmans0_.lkm_memo as lkm_mem10_1_1_,
//		        linkmans0_.lkm_cust_id as lkm_cus11_1_1_ 
//		    from
//		        cst_linkman linkmans0_ 
//		    where
//		        linkmans0_.lkm_cust_id=?
//		Linkman [lkm_id=8, lkm_name=王建立, lkm_gender=女, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		Linkman [lkm_id=7, lkm_name=熊大, lkm_gender=男, lkm_phone=null, lkm_mobile=null, lkm_email=null, lkm_qq=null, lkm_position=null, lkm_memo=null, version=0]
//		************************end************************
	}

	/**
	 * fetch: subselect ; lazy: extra
	 * <set name="linkmans" inverse="false" cascade="save-update,delete" fetch=
	 * "subselect" lazy="extra" >
	 */
	@Test
	public void f35() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("************************start************************");
		Query query = session.createQuery(" from Customer ");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		List<Customer> list = query.list();
		System.out.println("=================================");
		for (Customer customer : list) {
			System.out.println(customer.getLinkmans().size());
		}
		transaction.commit();
		System.out.println("************************end************************");

//		************************start************************
//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		Hibernate: 
//		    select
//		        customer0_.cust_id as cust_id1_0_,
//		        customer0_.version as version2_0_,
//		        customer0_.cust_name as cust_nam3_0_,
//		        customer0_.cust_user_id as cust_use4_0_,
//		        customer0_.cust_create_id as cust_cre5_0_,
//		        customer0_.cust_source as cust_sou6_0_,
//		        customer0_.cust_industry as cust_ind7_0_,
//		        customer0_.cust_level as cust_lev8_0_,
//		        customer0_.cust_linkman as cust_lin9_0_,
//		        customer0_.cust_phone as cust_ph10_0_,
//		        customer0_.cust_mobile as cust_mo11_0_ 
//		    from
//		        cst_customer customer0_
//		=================================
//		Hibernate: 
//		    select
//		        count(lkm_id) 
//		    from
//		        cst_linkman 
//		    where
//		        lkm_cust_id =?
//		2
//		Hibernate: 
//		    select
//		        count(lkm_id) 
//		    from
//		        cst_linkman 
//		    where
//		        lkm_cust_id =?
//		2
//		Hibernate: 
//		    select
//		        count(lkm_id) 
//		    from
//		        cst_linkman 
//		    where
//		        lkm_cust_id =?
//		0
//		************************end************************
	}

	/**
	 * fetch: subselect ; lazy: false
	 * <set name="linkmans" inverse="false" cascade="save-update,delete" fetch=
	 * "subselect" lazy="false" >
	 */
	@Test
	public void f36() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("************************start************************");
		Query query = session.createQuery(" from Customer ");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		List<Customer> list = query.list();
		System.out.println("=================================");
		for (Customer customer : list) {
			System.out.println(customer.getLinkmans().size());
		}
		transaction.commit();
		System.out.println("************************end************************");

//		************************start************************
//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		Hibernate: 
//		    select
//		        customer0_.cust_id as cust_id1_0_,
//		        customer0_.version as version2_0_,
//		        customer0_.cust_name as cust_nam3_0_,
//		        customer0_.cust_user_id as cust_use4_0_,
//		        customer0_.cust_create_id as cust_cre5_0_,
//		        customer0_.cust_source as cust_sou6_0_,
//		        customer0_.cust_industry as cust_ind7_0_,
//		        customer0_.cust_level as cust_lev8_0_,
//		        customer0_.cust_linkman as cust_lin9_0_,
//		        customer0_.cust_phone as cust_ph10_0_,
//		        customer0_.cust_mobile as cust_mo11_0_ 
//		    from
//		        cst_customer customer0_
//		Hibernate: 
//		    select
//		        linkmans0_.lkm_cust_id as lkm_cus11_1_1_,
//		        linkmans0_.lkm_id as lkm_id1_1_1_,
//		        linkmans0_.lkm_id as lkm_id1_1_0_,
//		        linkmans0_.version as version2_1_0_,
//		        linkmans0_.lkm_name as lkm_name3_1_0_,
//		        linkmans0_.lkm_gender as lkm_gend4_1_0_,
//		        linkmans0_.lkm_phone as lkm_phon5_1_0_,
//		        linkmans0_.lkm_mobile as lkm_mobi6_1_0_,
//		        linkmans0_.lkm_email as lkm_emai7_1_0_,
//		        linkmans0_.lkm_qq as lkm_qq8_1_0_,
//		        linkmans0_.lkm_position as lkm_posi9_1_0_,
//		        linkmans0_.lkm_memo as lkm_mem10_1_0_,
//		        linkmans0_.lkm_cust_id as lkm_cus11_1_0_ 
//		    from
//		        cst_linkman linkmans0_ 
//		    where
//		        linkmans0_.lkm_cust_id in (
//		            select
//		                customer0_.cust_id 
//		            from
//		                cst_customer customer0_
//		        )
//		=================================
//		2
//		2
//		0
//		************************end************************
	}

	/**
	 * fetch: subselect ; lazy: true
	 * <set name="linkmans" inverse="false" cascade="save-update,delete" fetch=
	 * "subselect" lazy="true" >
	 */
	@Test
	public void f37() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("************************start************************");
		Query query = session.createQuery(" from Customer ");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		List<Customer> list = query.list();
		System.out.println("=================================");
		for (Customer customer : list) {
			System.out.println(customer.getLinkmans().size());
		}
		transaction.commit();
		System.out.println("************************end************************");

//		************************start************************
//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		Hibernate: 
//		    select
//		        customer0_.cust_id as cust_id1_0_,
//		        customer0_.version as version2_0_,
//		        customer0_.cust_name as cust_nam3_0_,
//		        customer0_.cust_user_id as cust_use4_0_,
//		        customer0_.cust_create_id as cust_cre5_0_,
//		        customer0_.cust_source as cust_sou6_0_,
//		        customer0_.cust_industry as cust_ind7_0_,
//		        customer0_.cust_level as cust_lev8_0_,
//		        customer0_.cust_linkman as cust_lin9_0_,
//		        customer0_.cust_phone as cust_ph10_0_,
//		        customer0_.cust_mobile as cust_mo11_0_ 
//		    from
//		        cst_customer customer0_
//		=================================
//		Hibernate: 
//		    select
//		        linkmans0_.lkm_cust_id as lkm_cus11_1_1_,
//		        linkmans0_.lkm_id as lkm_id1_1_1_,
//		        linkmans0_.lkm_id as lkm_id1_1_0_,
//		        linkmans0_.version as version2_1_0_,
//		        linkmans0_.lkm_name as lkm_name3_1_0_,
//		        linkmans0_.lkm_gender as lkm_gend4_1_0_,
//		        linkmans0_.lkm_phone as lkm_phon5_1_0_,
//		        linkmans0_.lkm_mobile as lkm_mobi6_1_0_,
//		        linkmans0_.lkm_email as lkm_emai7_1_0_,
//		        linkmans0_.lkm_qq as lkm_qq8_1_0_,
//		        linkmans0_.lkm_position as lkm_posi9_1_0_,
//		        linkmans0_.lkm_memo as lkm_mem10_1_0_,
//		        linkmans0_.lkm_cust_id as lkm_cus11_1_0_ 
//		    from
//		        cst_linkman linkmans0_ 
//		    where
//		        linkmans0_.lkm_cust_id in (
//		            select
//		                customer0_.cust_id 
//		            from
//		                cst_customer customer0_
//		        )
//		2
//		2
//		0
//		************************end************************
	}

	/*********************************************************************************/
	/****************************** <many-to-one/> ********************************/
	/*********************************************************************************/

	/**
	 * 默认值 fetch : select lazy : false fetch="select" lazy="false"
	 */
	@Test
	public void f38() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("************************start************************");
		Linkman linkman = session.get(Linkman.class, 7L);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(linkman.getCustomer().getCust_name());
		System.out.println("************************end************************");

		transaction.commit();

//		************************start************************
//		Hibernate: 
//		    select
//		        linkman0_.lkm_id as lkm_id1_1_0_,
//		        linkman0_.version as version2_1_0_,
//		        linkman0_.lkm_name as lkm_name3_1_0_,
//		        linkman0_.lkm_gender as lkm_gend4_1_0_,
//		        linkman0_.lkm_phone as lkm_phon5_1_0_,
//		        linkman0_.lkm_mobile as lkm_mobi6_1_0_,
//		        linkman0_.lkm_email as lkm_emai7_1_0_,
//		        linkman0_.lkm_qq as lkm_qq8_1_0_,
//		        linkman0_.lkm_position as lkm_posi9_1_0_,
//		        linkman0_.lkm_memo as lkm_mem10_1_0_,
//		        linkman0_.lkm_cust_id as lkm_cus11_1_0_ 
//		    from
//		        cst_linkman linkman0_ 
//		    where
//		        linkman0_.lkm_id=?
//		Hibernate: 
//		    select
//		        customer0_.cust_id as cust_id1_0_0_,
//		        customer0_.version as version2_0_0_,
//		        customer0_.cust_name as cust_nam3_0_0_,
//		        customer0_.cust_user_id as cust_use4_0_0_,
//		        customer0_.cust_create_id as cust_cre5_0_0_,
//		        customer0_.cust_source as cust_sou6_0_0_,
//		        customer0_.cust_industry as cust_ind7_0_0_,
//		        customer0_.cust_level as cust_lev8_0_0_,
//		        customer0_.cust_linkman as cust_lin9_0_0_,
//		        customer0_.cust_phone as cust_ph10_0_0_,
//		        customer0_.cust_mobile as cust_mo11_0_0_ 
//		    from
//		        cst_customer customer0_ 
//		    where
//		        customer0_.cust_id=?
//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		马云云
//		************************end************************
	}

	/**
	 * 默认值 fetch : join lazy : false fetch="select" lazy="false"
	 */
	@Test
	public void f39() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("************************start************************");
		Linkman linkman = session.get(Linkman.class, 7L);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(linkman.getCustomer().getCust_name());
		System.out.println("************************end************************");

		transaction.commit();

//		************************start************************
//		Hibernate: 
//		    select
//		        linkman0_.lkm_id as lkm_id1_1_0_,
//		        linkman0_.version as version2_1_0_,
//		        linkman0_.lkm_name as lkm_name3_1_0_,
//		        linkman0_.lkm_gender as lkm_gend4_1_0_,
//		        linkman0_.lkm_phone as lkm_phon5_1_0_,
//		        linkman0_.lkm_mobile as lkm_mobi6_1_0_,
//		        linkman0_.lkm_email as lkm_emai7_1_0_,
//		        linkman0_.lkm_qq as lkm_qq8_1_0_,
//		        linkman0_.lkm_position as lkm_posi9_1_0_,
//		        linkman0_.lkm_memo as lkm_mem10_1_0_,
//		        linkman0_.lkm_cust_id as lkm_cus11_1_0_,
//		        customer1_.cust_id as cust_id1_0_1_,
//		        customer1_.version as version2_0_1_,
//		        customer1_.cust_name as cust_nam3_0_1_,
//		        customer1_.cust_user_id as cust_use4_0_1_,
//		        customer1_.cust_create_id as cust_cre5_0_1_,
//		        customer1_.cust_source as cust_sou6_0_1_,
//		        customer1_.cust_industry as cust_ind7_0_1_,
//		        customer1_.cust_level as cust_lev8_0_1_,
//		        customer1_.cust_linkman as cust_lin9_0_1_,
//		        customer1_.cust_phone as cust_ph10_0_1_,
//		        customer1_.cust_mobile as cust_mo11_0_1_ 
//		    from
//		        cst_linkman linkman0_ 
//		    left outer join
//		        cst_customer customer1_ 
//		            on linkman0_.lkm_cust_id=customer1_.cust_id 
//		    where
//		        linkman0_.lkm_id=?
//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		马云云
//		************************end************************
	}

	/**
	 *  fetch : join lazy : proxy 
	 * 	<class name="domain.Customer" table="cst_customer" lazy="false">
	 * fetch="join" lazy="proxy" 
	 */
	@Test
	public void f39a() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("************************start************************");
		Linkman linkman = session.get(Linkman.class, 7L);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(linkman.getCustomer().getCust_name());
		System.out.println("************************end************************");

		transaction.commit();
		
//		************************start************************
//		Hibernate: 
//		    select
//		        linkman0_.lkm_id as lkm_id1_1_0_,
//		        linkman0_.version as version2_1_0_,
//		        linkman0_.lkm_name as lkm_name3_1_0_,
//		        linkman0_.lkm_gender as lkm_gend4_1_0_,
//		        linkman0_.lkm_phone as lkm_phon5_1_0_,
//		        linkman0_.lkm_mobile as lkm_mobi6_1_0_,
//		        linkman0_.lkm_email as lkm_emai7_1_0_,
//		        linkman0_.lkm_qq as lkm_qq8_1_0_,
//		        linkman0_.lkm_position as lkm_posi9_1_0_,
//		        linkman0_.lkm_memo as lkm_mem10_1_0_,
//		        linkman0_.lkm_cust_id as lkm_cus11_1_0_,
//		        customer1_.cust_id as cust_id1_0_1_,
//		        customer1_.version as version2_0_1_,
//		        customer1_.cust_name as cust_nam3_0_1_,
//		        customer1_.cust_user_id as cust_use4_0_1_,
//		        customer1_.cust_create_id as cust_cre5_0_1_,
//		        customer1_.cust_source as cust_sou6_0_1_,
//		        customer1_.cust_industry as cust_ind7_0_1_,
//		        customer1_.cust_level as cust_lev8_0_1_,
//		        customer1_.cust_linkman as cust_lin9_0_1_,
//		        customer1_.cust_phone as cust_ph10_0_1_,
//		        customer1_.cust_mobile as cust_mo11_0_1_ 
//		    from
//		        cst_linkman linkman0_ 
//		    left outer join
//		        cst_customer customer1_ 
//		            on linkman0_.lkm_cust_id=customer1_.cust_id 
//		    where
//		        linkman0_.lkm_id=?
//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		马云云
//		************************end************************

	}
	
	/**
	 *  fetch : join lazy : proxy 
	 * 	<class name="domain.Customer" table="cst_customer" lazy="true">
	 * fetch="join" lazy="proxy" 
	 */
	@Test
	public void f39b() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("************************start************************");
		Linkman linkman = session.get(Linkman.class, 7L);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(linkman.getCustomer().getCust_name());
		System.out.println("************************end************************");

		transaction.commit();
		
//		************************start************************
//		Hibernate: 
//		    select
//		        linkman0_.lkm_id as lkm_id1_1_0_,
//		        linkman0_.version as version2_1_0_,
//		        linkman0_.lkm_name as lkm_name3_1_0_,
//		        linkman0_.lkm_gender as lkm_gend4_1_0_,
//		        linkman0_.lkm_phone as lkm_phon5_1_0_,
//		        linkman0_.lkm_mobile as lkm_mobi6_1_0_,
//		        linkman0_.lkm_email as lkm_emai7_1_0_,
//		        linkman0_.lkm_qq as lkm_qq8_1_0_,
//		        linkman0_.lkm_position as lkm_posi9_1_0_,
//		        linkman0_.lkm_memo as lkm_mem10_1_0_,
//		        linkman0_.lkm_cust_id as lkm_cus11_1_0_,
//		        customer1_.cust_id as cust_id1_0_1_,
//		        customer1_.version as version2_0_1_,
//		        customer1_.cust_name as cust_nam3_0_1_,
//		        customer1_.cust_user_id as cust_use4_0_1_,
//		        customer1_.cust_create_id as cust_cre5_0_1_,
//		        customer1_.cust_source as cust_sou6_0_1_,
//		        customer1_.cust_industry as cust_ind7_0_1_,
//		        customer1_.cust_level as cust_lev8_0_1_,
//		        customer1_.cust_linkman as cust_lin9_0_1_,
//		        customer1_.cust_phone as cust_ph10_0_1_,
//		        customer1_.cust_mobile as cust_mo11_0_1_ 
//		    from
//		        cst_linkman linkman0_ 
//		    left outer join
//		        cst_customer customer1_ 
//		            on linkman0_.lkm_cust_id=customer1_.cust_id 
//		    where
//		        linkman0_.lkm_id=?
//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		马云云
//		************************end************************
	}
	

	/**
	 *  fetch : join lazy : proxy fetch="join" lazy="proxy" proxy 由主表的class 属性控制
	 */
	@Test
	public void f40() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("************************start************************");
		Linkman linkman = session.get(Linkman.class, 7L);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(linkman.getCustomer().getCust_name());
		System.out.println("************************end************************");

		transaction.commit();

//		<class name="domain.Customer" table="cst_customer" lazy="true">

//		************************start************************
//		Hibernate: 
//		    select
//		        linkman0_.lkm_id as lkm_id1_1_0_,
//		        linkman0_.version as version2_1_0_,
//		        linkman0_.lkm_name as lkm_name3_1_0_,
//		        linkman0_.lkm_gender as lkm_gend4_1_0_,
//		        linkman0_.lkm_phone as lkm_phon5_1_0_,
//		        linkman0_.lkm_mobile as lkm_mobi6_1_0_,
//		        linkman0_.lkm_email as lkm_emai7_1_0_,
//		        linkman0_.lkm_qq as lkm_qq8_1_0_,
//		        linkman0_.lkm_position as lkm_posi9_1_0_,
//		        linkman0_.lkm_memo as lkm_mem10_1_0_,
//		        linkman0_.lkm_cust_id as lkm_cus11_1_0_,
//		        customer1_.cust_id as cust_id1_0_1_,
//		        customer1_.version as version2_0_1_,
//		        customer1_.cust_name as cust_nam3_0_1_,
//		        customer1_.cust_user_id as cust_use4_0_1_,
//		        customer1_.cust_create_id as cust_cre5_0_1_,
//		        customer1_.cust_source as cust_sou6_0_1_,
//		        customer1_.cust_industry as cust_ind7_0_1_,
//		        customer1_.cust_level as cust_lev8_0_1_,
//		        customer1_.cust_linkman as cust_lin9_0_1_,
//		        customer1_.cust_phone as cust_ph10_0_1_,
//		        customer1_.cust_mobile as cust_mo11_0_1_ 
//		    from
//		        cst_linkman linkman0_ 
//		    left outer join
//		        cst_customer customer1_ 
//		            on linkman0_.lkm_cust_id=customer1_.cust_id 
//		    where
//		        linkman0_.lkm_id=?
//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		马云云
//		************************end************************

//		<class name="domain.Customer" table="cst_customer" lazy="false">

//		************************start************************
//		Hibernate: 
//		    select
//		        linkman0_.lkm_id as lkm_id1_1_0_,
//		        linkman0_.version as version2_1_0_,
//		        linkman0_.lkm_name as lkm_name3_1_0_,
//		        linkman0_.lkm_gender as lkm_gend4_1_0_,
//		        linkman0_.lkm_phone as lkm_phon5_1_0_,
//		        linkman0_.lkm_mobile as lkm_mobi6_1_0_,
//		        linkman0_.lkm_email as lkm_emai7_1_0_,
//		        linkman0_.lkm_qq as lkm_qq8_1_0_,
//		        linkman0_.lkm_position as lkm_posi9_1_0_,
//		        linkman0_.lkm_memo as lkm_mem10_1_0_,
//		        linkman0_.lkm_cust_id as lkm_cus11_1_0_,
//		        customer1_.cust_id as cust_id1_0_1_,
//		        customer1_.version as version2_0_1_,
//		        customer1_.cust_name as cust_nam3_0_1_,
//		        customer1_.cust_user_id as cust_use4_0_1_,
//		        customer1_.cust_create_id as cust_cre5_0_1_,
//		        customer1_.cust_source as cust_sou6_0_1_,
//		        customer1_.cust_industry as cust_ind7_0_1_,
//		        customer1_.cust_level as cust_lev8_0_1_,
//		        customer1_.cust_linkman as cust_lin9_0_1_,
//		        customer1_.cust_phone as cust_ph10_0_1_,
//		        customer1_.cust_mobile as cust_mo11_0_1_ 
//		    from
//		        cst_linkman linkman0_ 
//		    left outer join
//		        cst_customer customer1_ 
//		            on linkman0_.lkm_cust_id=customer1_.cust_id 
//		    where
//		        linkman0_.lkm_id=?
//		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		马云云
//		************************end************************

	}
}
