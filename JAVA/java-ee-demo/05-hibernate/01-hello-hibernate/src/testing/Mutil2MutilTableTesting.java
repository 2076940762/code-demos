package testing;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import domain.Course;
import domain.Student;
import utils.HibernateUtils;

public class Mutil2MutilTableTesting {

//	SHOW CREATE TABLE t_cousrse;
//	CREATE TABLE `t_cousrse` (                                                                                                                                              
//	`cid` BIGINT(20) NOT NULL AUTO_INCREMENT,                                                                                                                             
//	`cname` VARCHAR(255) DEFAULT NULL,                                                                                                                                    
//	PRIMARY KEY (`cid`)                                                                                                                                                   
//	) ENGINE=INNODB DEFAULT CHARSET=utf8
//
//	SHOW CREATE TABLE  t_cousrse;
//	CREATE TABLE `t_cousrse` (                                                                                                                                              
//	`cid` BIGINT(20) NOT NULL AUTO_INCREMENT,                                                                                                                             
//	`cname` VARCHAR(255) DEFAULT NULL,                                                                                                                                    
//	PRIMARY KEY (`cid`)                                                                                                                                                   
//	) ENGINE=INNODB DEFAULT CHARSET=utf8 
//	           
//	SHOW CREATE TABLE  elective_list;
//	CREATE TABLE `elective_list` (                                                                                                                                                                                                                                                                                                                                                                        
//	`cid` BIGINT(20) NOT NULL,                                                                                                                                                                                                                                                                                                                                                                          
//	`sid` BIGINT(20) NOT NULL,                                                                                                                                                                                                                                                                                                                                                                          
//	PRIMARY KEY (`sid`,`cid`),                                                                                                                                                                                                                                                                                                                                                                          
//	KEY `FK716xwi4f3nf8ikel4r253w6pk` (`cid`),                                                                                                                                                                                                                                                                                                                                                          
//	CONSTRAINT `FK716xwi4f3nf8ikel4r253w6pk` FOREIGN KEY (`cid`) REFERENCES `t_cousrse` (`cid`),                                                                                                                                                                                                                                                                                                        
//	CONSTRAINT `FKjw67x5rqx4ttkgmg75qxkrbdc` FOREIGN KEY (`sid`) REFERENCES `t_student` (`sid`)                                                                                                                                                                                                                                                                                                         
//	) ENGINE=INNODB DEFAULT CHARSET=utf8    

	/**
	 * 默认情况下inverse="false"，双向关联无法保存数据； 事务因为向中间表中插入两条完全相同的记录与主键冲突，而导致回滚；
	 */
	@Test
	public void f1() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Course course = new Course();
		course.setCname("高等数学");

		Student student = new Student();
		student.setSname("小明");

//		双向关联
		course.getStudents().add(student);
		student.getCourses().add(course);

		session.save(student);
		session.save(course);

		transaction.commit();

//		insert into t_student (sname) values ('小明')
//			
//		insert into t_cousrse (cname) values ('高等数学')
//			
//		insert into elective_list (sid, cid) values (1, 1)
//			
//		insert into elective_list (cid, sid) values (1, 1)

	}

	/**
	 * 如果只设置单向关联，则只保存被保存的对象，关联的对象，映射关系都不会存到数据库中
	 */
	@Test
	public void f2() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Course course = new Course();
		course.setCname("高等数学");

		Student student = new Student();
		student.setSname("小明");

//		双向关联
		course.getStudents().add(student);
//		student.getCourses().add(course);

		session.save(student);
//		session.save(course);

		transaction.commit();

//		insert into t_student (sname) values ('小明')
	}

	/**
	 * 让student不维护外键 <set name="courses" table="elective_list" inverse="true" >
	 * 
	 */
	@Test
	public void f3() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();

		Course course = new Course();
		course.setCname("高等数学");

		Student student = new Student();
		student.setSname("小明");

//		双向关联
		course.getStudents().add(student);
		student.getCourses().add(course);

		session.save(student);
		session.save(course);

		transaction.commit();

//		insert into t_student (sname) values ('小明')
//			
//		insert into t_cousrse (cname) values ('高等数学')
//			
//		insert into elective_list (cid, sid) values (2, 3)

//		SELECT * FROM  t_cousrse A ,elective_list B ,t_student C WHERE A.cid=B.cid AND B.sid=C.sid;
//		
//		   cid  cname            cid     sid     sid  sname   
//		------  ------------  ------  ------  ------  --------
//		     2  高等数学               2       3       3  小明  
	}

//	<set name="students" table="elective_list"  cascade="save-update,delete">
//	<set name="courses" table="elective_list"  inverse="true"  cascade="save-update,delete">

	/**
	 * 更新数据 改名字
	 */
	@Test
	public void f4() {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();

		Student student = session.get(Student.class, 3L);
		student.setSname("大黄蜂");

		Course course = session.get(Course.class, 2L);
		course.setCname("线性代数");

		session.save(student);
		session.save(course);

		transaction.commit();

//		select student0_.sid as sid1_4_0_, student0_.sname as sname2_4_0_ from t_student student0_ where student0_.sid=3
//			
//		select course0_.cid as cid1_3_0_, course0_.cname as cname2_3_0_ from t_cousrse course0_ where course0_.cid=2
//			
//		update t_student set sname='大黄蜂' where sid=3
//			
//		update t_cousrse set cname='线性代数' where cid=2
	}

	/**
	 * 通过Course更新关联关系
	 */
	@Test
	public void f5() {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();

		Student student = session.get(Student.class, 3L);
		student.setSname("大黄蜂");

		Course course = session.get(Course.class, 2L);
		course.setCname("线性代数");

		course.getStudents().remove(student);

		session.save(course);

		transaction.commit();

//		select student0_.sid as sid1_4_0_, student0_.sname as sname2_4_0_ from 
//		t_student student0_ where student0_.sid=3
//
//		select course0_.cid as cid1_3_0_, course0_.cname as cname2_3_0_ from
//		t_cousrse course0_ where course0_.cid=2
//			
//		select students0_.cid as cid1_2_0_, students0_.sid as sid2_2_0_, student1_.sid as sid1_4_1_, student1_.sname as sname2_4_1_ 
//		from elective_list students0_ inner join t_student student1_ on students0_.sid=student1_.sid where students0_.cid=2
//			
//		delete from elective_list where cid=2

	}

	/**
	 * Student 不再维护外键所以，如下方式回复映射是达不到效果的
	 */
	@Test
	public void f6() {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();

		Student student = session.get(Student.class, 3L);
		Course course = session.get(Course.class, 2L);

		student.getCourses().add(course);

		session.save(student);

		transaction.commit();

//		select student0_.sid as sid1_4_0_, student0_.sname as sname2_4_0_ from t_student student0_ where student0_.sid=3
//			
//		select course0_.cid as cid1_3_0_, course0_.cname as cname2_3_0_ from t_cousrse course0_ where course0_.cid=2
//			
//		select courses0_.sid as sid2_2_0_, courses0_.cid as cid1_2_0_, course1_.cid as cid1_3_1_, course1_.cname as cname2_3_1_ 
//		from elective_list courses0_ inner join t_cousrse course1_ on courses0_.cid=course1_.cid where courses0_.sid=3
	}

	/**
	 * 通过course恢复映射关系
	 */
	@Test
	public void f7() {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();

		Student student = session.get(Student.class, 3L);
		Course course = session.get(Course.class, 2L);

		course.getStudents().add(student);
		session.save(student);

		transaction.commit();

//		select student0_.sid as sid1_4_0_, student0_.sname as sname2_4_0_ from t_student student0_ where student0_.sid=3
//
//		select course0_.cid as cid1_3_0_, course0_.cname as cname2_3_0_ from t_cousrse course0_ where course0_.cid=2
//			
//		select students0_.cid as cid1_2_0_, students0_.sid as sid2_2_0_, student1_.sid as sid1_4_1_, student1_.sname as sname2_4_1_ 
//		from elective_list students0_ inner join t_student student1_ on students0_.sid=student1_.sid where students0_.cid=2
//			
//		insert into elective_list (cid, sid) values (2, 3)

//		SELECT * FROM  t_cousrse A ,elective_list B ,t_student C WHERE A.cid=B.cid AND B.sid=C.sid;
//		   cid  cname            cid     sid     sid  sname      
//		   ------  ------------  ------  ------  ------  -----------
//		        2  线性代数               2       3       3  大黄蜂  

	}

	/**
	 * 在添加一个选线性代数的学生
	 */
	@Test
	public void f8() {
		Session session = HibernateUtils.getCurrSession();
		Transaction transaction = session.beginTransaction();
		
		Course course = session.get(Course.class, 2L);
		
		Student student = new Student();
		student.setSname("顾眉生");
		
		course.getStudents().add(student);
		
		session.save(course);

		transaction.commit();
		
//		select course0_.cid as cid1_3_0_, course0_.cname as cname2_3_0_ from t_cousrse course0_ where course0_.cid=2
//			
//		select students0_.cid as cid1_2_0_, students0_.sid as sid2_2_0_, student1_.sid as sid1_4_1_, student1_.sname as sname2_4_1_ 
//		from elective_list students0_ inner join t_student student1_ on students0_.sid=student1_.sid where students0_.cid=2
//			
//		insert into t_student (sname) values ('顾眉生')
//			
//		insert into elective_list (cid, sid) values (2, 4)

//		SELECT * FROM  t_cousrse A ,elective_list B ,t_student C WHERE A.cid=B.cid AND B.sid=C.sid;
//		   cid  cname            cid     sid     sid  sname      
//		   ------  ------------  ------  ------  ------  -----------
//		        2  线性代数               2       3       3  大黄蜂  
//		        2  线性代数               2       4       4  顾眉生  
	}

}
