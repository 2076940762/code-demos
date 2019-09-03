package testing;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import domain.Customer;
import domain.Linkman;
import utils.HibernateUtils;

public class One2MutilTableTesting {

	/**
	 * 双向关联保存多表数据,没有配置级联
	 */
	@Test
	public void f1() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Customer customer = new Customer();
		customer.setCust_name("马云云");

		Linkman l1 = new Linkman();
		l1.setLkm_name("王建立");

		Linkman l2 = new Linkman();
		l2.setLkm_name("熊大");

		customer.getLinkmans().add(l1);
		customer.getLinkmans().add(l2);

		l1.setCustomer(customer);
		l2.setCustomer(customer);

		session.save(customer);
		session.save(l1);
		session.save(l2);

		transaction.commit();

//		insert into cst_customer (version, cust_name, cust_user_id, cust_create_id, cust_source, cust_industry, cust_level, cust_linkman, cust_phone, cust_mobile) 
//		values (0, '马云云', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
//			
//		insert into cst_linkman (version, lkm_name, lkm_gender, lkm_phone, lkm_mobile, lkm_email, lkm_qq, lkm_position, lkm_memo, lkm_cust_id) 
//		values (0, '王建立', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9)
//			
//		insert into cst_linkman (version, lkm_name, lkm_gender, lkm_phone, lkm_mobile, lkm_email, lkm_qq, lkm_position, lkm_memo, lkm_cust_id)
//     values (0, '熊大', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9)
//			
//		update cst_linkman set lkm_cust_id=9 where lkm_id=20
//			
//		update cst_linkman set lkm_cust_id=9 where lkm_id=19

	}

	/**
	 * 
	 * 双向级联 保存数据
	 */
	@Test
	public void f2() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Customer customer = new Customer();
		customer.setCust_name("韦小宝");

		Linkman l1 = new Linkman();
		l1.setLkm_name("令狐冲");

		Linkman l2 = new Linkman();
		l2.setLkm_name("杨过");

		l2.setCustomer(customer);
		customer.getLinkmans().add(l1);

		session.save(l2);

		transaction.commit();

//		insert into cst_customer (version, cust_name, cust_user_id, cust_create_id, cust_source, cust_industry, cust_level, cust_linkman, cust_phone, cust_mobile)
//		values (0, '韦小宝', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
//			
//		insert into cst_customer (version, cust_name, cust_user_id, cust_create_id, cust_source, cust_industry, cust_level, cust_linkman, cust_phone, cust_mobile)
//		values (0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
//			
//		insert into cst_linkman (version, lkm_name, lkm_gender, lkm_phone, lkm_mobile, lkm_email, lkm_qq, lkm_position, lkm_memo, lkm_cust_id) 
//		values (0, '令狐冲', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 11)
//			
//		insert into cst_linkman (version, lkm_name, lkm_gender, lkm_phone, lkm_mobile, lkm_email, lkm_qq, lkm_position, lkm_memo, lkm_cust_id) 
//		values (0, '杨过', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 10)
//			
//		update cst_linkman set lkm_cust_id=10 where lkm_id=21

//		lkm_id  lkm_name   lkm_cust_id  lkm_gender  lkm_phone  lkm_mobile  lkm_email  lkm_qq  lkm_position  lkm_memo  version  
//		------  ---------  -----------  ----------  ---------  ----------  ---------  ------  ------------  --------  ---------
//		    21  令狐冲                 10  (NULL)      (NULL)     (NULL)      (NULL)     (NULL)  (NULL)        (NULL)            0
//		    22  杨过                  10  (NULL)      (NULL)     (NULL)      (NULL)     (NULL)  (NULL)        (NULL)            0

//		cust_id  cust_name  cust_user_id  cust_create_id  cust_source  cust_industry  cust_level  cust_linkman  cust_phone  cust_mobile  cust_address  cust_zip  cust_fax  cust_website  version  
//		-------  ---------  ------------  --------------  -----------  -------------  ----------  ------------  ----------  -----------  ------------  --------  --------  ------------  ---------
//		     10  韦小宝              (NULL)          (NULL)  (NULL)       (NULL)         (NULL)      (NULL)        (NULL)      (NULL)       (NULL)        (NULL)    (NULL)    (NULL)                0
//		     11  (NULL)           (NULL)          (NULL)  (NULL)       (NULL)         (NULL)      (NULL)        (NULL)      (NULL)       (NULL)        (NULL)    (NULL)    (NULL)                0
	}

	/**
	 * 测试级联删除
	 * 无论主表是否维护外键，级联的记录都会删除，
	 * 维护外键时，先将从表的外键设为null,再删除所有数据
	 */
	@Test
	public void f5() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Customer customer = session.get(Customer.class, 7L);
		session.delete(customer);

		transaction.commit();
		
//		CREATE TABLE `cst_linkman` (                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
//	               `lkm_id` bigint(20) NOT NULL AUTO_INCREMENT,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
//	               `version` int(11) NOT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
//	               `lkm_name` varchar(255) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
//	               `lkm_gender` varchar(255) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
//	               `lkm_phone` varchar(255) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
//	               `lkm_mobile` varchar(255) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
//	               `lkm_email` varchar(255) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
//	               `lkm_qq` varchar(255) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
//	               `lkm_position` varchar(255) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
//	               `lkm_memo` varchar(255) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
//	               `lkm_cust_id` bigint(20) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
//	               PRIMARY KEY (`lkm_id`),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
//	               KEY `FKh9yp1nql5227xxcopuxqx2e7q` (`lkm_cust_id`),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
//	               CONSTRAINT `FKh9yp1nql5227xxcopuxqx2e7q` FOREIGN KEY (`lkm_cust_id`) REFERENCES `cst_customer` (`cust_id`)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
//	             ) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8   
		
//		CREATE TABLE `cst_customer` (                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
//                `cust_id` bigint(20) NOT NULL AUTO_INCREMENT,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
//                `version` int(11) NOT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
//                `cust_name` varchar(255) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
//                `cust_user_id` bigint(20) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
//                `cust_create_id` bigint(20) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
//                `cust_source` varchar(255) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
//                `cust_industry` varchar(255) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
//                `cust_level` varchar(255) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
//                `cust_linkman` varchar(255) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
//                `cust_phone` varchar(255) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
//                `cust_mobile` varchar(255) DEFAULT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
//                PRIMARY KEY (`cust_id`)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
//              ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8     
		
		
//		cust_id  cust_name  lkm_id  lkm_name   
//		-------  ---------  ------  -----------
//		      7  韦小宝             1  令狐冲  
		
		/* session.delete(customer); */

		/*
		 * inverse="true"  不维护外键
		 */

//		select customer0_.cust_id as cust_id1_0_0_, customer0_.version as version2_0_0_, customer0_.cust_name as cust_nam3_0_0_, customer0_.cust_user_id as cust_use4_0_0_, customer0_.cust_create_id as cust_cre5_0_0_, customer0_.cust_source as cust_sou6_0_0_, customer0_.cust_industry as cust_ind7_0_0_, customer0_.cust_level as cust_lev8_0_0_, customer0_.cust_linkman as cust_lin9_0_0_, customer0_.cust_phone as cust_ph10_0_0_, customer0_.cust_mobile as cust_mo11_0_0_
//		from cst_customer customer0_ 
//		where customer0_.cust_id=4
//			
//		select linkmans0_.lkm_cust_id as lkm_cus11_1_0_, linkmans0_.lkm_id as lkm_id1_1_0_, linkmans0_.lkm_id as lkm_id1_1_1_, linkmans0_.version as version2_1_1_, linkmans0_.lkm_name as lkm_name3_1_1_, linkmans0_.lkm_gender as lkm_gend4_1_1_, linkmans0_.lkm_phone as lkm_phon5_1_1_, linkmans0_.lkm_mobile as lkm_mobi6_1_1_, linkmans0_.lkm_email as lkm_emai7_1_1_, linkmans0_.lkm_qq as lkm_qq8_1_1_, linkmans0_.lkm_position as lkm_posi9_1_1_, linkmans0_.lkm_memo as lkm_mem10_1_1_, linkmans0_.lkm_cust_id as lkm_cus11_1_1_ 
//		from cst_linkman linkmans0_ 
//		where linkmans0_.lkm_cust_id=4
//			
//		delete from cst_linkman where lkm_id=4 and version=0
//			
//		delete from cst_linkman where lkm_id=3 and version=0
//			
//		delete from cst_linkman where lkm_id=15 and version=2
//			
//		delete from cst_customer where cust_id=4 and version=2

		/*
		 * inverse="false"  维护外键
		 */

//		select customer0_.cust_id as cust_id1_0_0_, customer0_.version as version2_0_0_, customer0_.cust_name as cust_nam3_0_0_, customer0_.cust_user_id as cust_use4_0_0_, customer0_.cust_create_id as cust_cre5_0_0_, customer0_.cust_source as cust_sou6_0_0_, customer0_.cust_industry as cust_ind7_0_0_, customer0_.cust_level as cust_lev8_0_0_, customer0_.cust_linkman as cust_lin9_0_0_, customer0_.cust_phone as cust_ph10_0_0_, customer0_.cust_mobile as cust_mo11_0_0_ from cst_customer customer0_ where customer0_.cust_id=7
//			
//		select linkmans0_.lkm_cust_id as lkm_cus11_1_0_, linkmans0_.lkm_id as lkm_id1_1_0_, linkmans0_.lkm_id as lkm_id1_1_1_, linkmans0_.version as version2_1_1_, linkmans0_.lkm_name as lkm_name3_1_1_, linkmans0_.lkm_gender as lkm_gend4_1_1_, linkmans0_.lkm_phone as lkm_phon5_1_1_, linkmans0_.lkm_mobile as lkm_mobi6_1_1_, linkmans0_.lkm_email as lkm_emai7_1_1_, linkmans0_.lkm_qq as lkm_qq8_1_1_, linkmans0_.lkm_position as lkm_posi9_1_1_, linkmans0_.lkm_memo as lkm_mem10_1_1_, linkmans0_.lkm_cust_id as lkm_cus11_1_1_ from cst_linkman linkmans0_ where linkmans0_.lkm_cust_id=7
//			
//		update cst_linkman set 
//		lkm_cust_id=null
//				where lkm_cust_id=7
//			
//		delete from cst_linkman where lkm_id=1 and version=0
//			
//		delete from cst_customer where cust_id=7 and version=0
					
	}

	/**
	 * 修改客户与联系人的关联关系 当主表放弃对外键的维护后，少了一条更新外键的空操作SQL; 维护外键和保存数据时两回事；
	 * 放弃维护外键在一对多种并不影响数据的完整性
	 */
	@Test
	public void f6() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Customer customer = session.get(Customer.class, 4L);
		Linkman linkman = session.get(Linkman.class, 15L);

		linkman.setCustomer(customer);
		customer.getLinkmans().add(linkman);

		session.save(linkman);
//		session.save(customer);

//		SELECT cust_id ,cust_name,lkm_id,lkm_name FROM cst_customer A,cst_linkman B WHERE A.`cust_id`=B.`lkm_cust_id`;

		// 修改前
//		cust_id  cust_name  lkm_id  lkm_name   
//		-------  ---------  ------  -----------
//		      4  马云云             3  王建立  
//		      4  马云云             4  熊大     
//		      7  韦小宝             7  令狐冲  
//		      7  韦小宝            15  杨过   

		// 修改后
//		cust_id  cust_name  lkm_id  lkm_name   
//		-------  ---------  ------  -----------
//		      4  马云云             3  王建立  
//		      4  马云云             4  熊大     
//		      7  韦小宝             7  令狐冲  
//		      4  马云云            15  杨过   

//		UPDATE cst_customer SET VERSION=2, cust_name='马云云', cust_user_id=NULL, cust_create_id=NULL, cust_source=NULL, cust_industry=NULL, cust_level=NULL, cust_linkman=NULL, cust_phone=NULL, cust_mobile=NULL 
//		WHERE cust_id=4 AND VERSION=1
//			
//		UPDATE cst_linkman SET VERSION=2, lkm_name='杨过', lkm_gender=NULL, lkm_phone=NULL, lkm_mobile=NULL, lkm_email=NULL, lkm_qq=NULL, lkm_position=NULL, lkm_memo=NULL, 
//		lkm_cust_id=4 
//		WHERE lkm_id=15 AND VERSION=1
//			
//		UPDATE cst_linkman SET lkm_cust_id=4 WHERE lkm_id=15  -- 可以看出这个更新外键的SQL是没用的

		/*
		 * 让主表放弃外键的维护 inverse="true"
		 */

		/* session.save(customer); */

//		update cst_customer set version=1, cust_name='马云云', cust_user_id=NULL, cust_create_id=NULL, cust_source=NULL, cust_industry=NULL, cust_level=NULL, cust_linkman=NULL, cust_phone=NULL, cust_mobile=NULL where cust_id=4 and version=0
//			
//		update cst_linkman set version=1, lkm_name='杨过', lkm_gender=NULL, lkm_phone=NULL, lkm_mobile=NULL, lkm_email=NULL, lkm_qq=NULL, lkm_position=NULL, lkm_memo=NULL, 
//		lkm_cust_id=4
//		where lkm_id=15 and version=0
//			

		/* session.save(linkman); */

//		update cst_customer set version=2, cust_name='马云云', cust_user_id=NULL, cust_create_id=NULL, cust_source=NULL, cust_industry=NULL, cust_level=NULL, cust_linkman=NULL, cust_phone=NULL, cust_mobile=NULL 
//		where cust_id=4 and version=1
//			
//		update cst_linkman set version=2, lkm_name='杨过', lkm_gender=NULL, lkm_phone=NULL, lkm_mobile=NULL, lkm_email=NULL, lkm_qq=NULL, lkm_position=NULL, lkm_memo=NULL, 
//		lkm_cust_id=4
//		where lkm_id=15 and version=1

		transaction.commit();
	}

}
