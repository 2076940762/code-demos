package dbutils;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class dbutils_demo {
	private static String sql = "SELECT * FROM orders;";

	// 内部类
	public static class myOrder {
		int oid;
		double totalprice;
		int uid;

		public int getOid() {
			return oid;
		}

		public void setOid(int oid) {
			this.oid = oid;
		}

		public double getTotalprice() {
			return totalprice;
		}

		public void setTotalprice(double totalprice) {
			this.totalprice = totalprice;
		}

		public int getUid() {
			return uid;
		}

		public void setUid(int uid) {
			this.uid = uid;
		}

		@Override
		public String toString() {
			return "order [oid=" + oid + ", totalprice=" + totalprice + ", uid=" + uid + "]";
		}

	}

//	QueryRunner:类名
//	作用:操作sql语句
//	构造器:
//		new QueryRunner(Datasource ds);
//	注意:
//		底层帮我们创建连接,创建语句执行者 ,释放资源.
//	常用方法:
//		query(..):
//		update(..):
//
//DbUtils:释放资源,控制事务 类
//	closeQuietly(conn):内部处理了异常
//	commitAndClose(Connection conn):提交事务并释放连接
//	....
//
//ResultSetHandler:封装结果集 接口
//	
//	实现类 ArrayHandler, ArrayListHandler, BeanHandler, BeanListHandler, ColumnListHandler, KeyedHandler, MapHandler, MapListHandler, ScalarHandler
//	 
//	 (了解)ArrayHandler, 将查询结果的第一条记录封装成数组,返回
//	 (了解)ArrayListHandler, 将查询结果的每一条记录封装成数组,将每一个数组放入list中返回
//	 ★★BeanHandler, 将查询结果的第一条记录封装成指定的bean对象,返回
//	 ★★BeanListHandler, 将查询结果的每一条记录封装成指定的bean对象,将每一个bean对象放入list中 返回.
//	 (了解)ColumnListHandler, 将查询结果的指定一列放入list中返回 
//	 (了解)MapHandler, 将查询结果的第一条记录封装成map,字段名作为key,值为value 返回
//	 ★MapListHandler, 将查询结果的每一条记录封装map集合,将每一个map集合放入list中返回
//	 ★ScalarHandler,针对于聚合函数 例如:count(*) 返回的是一个Long值
//	 
	@Test
//	(了解)ArrayHandler, 将查询结果的第一条记录封装成数组,返回
	public void f1() throws SQLException {
		QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
		Object[] result = qr.query("SELECT * FROM orders;", new ArrayHandler());
		System.out.println(Arrays.toString(result));
		System.out.println("================");
	}

	@Test
//	(了解)ArrayListHandler, 将查询结果的每一条记录封装成数组,将每一个数组放入list中返回
	public void f2() throws SQLException {
		QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
		List<Object[]> query = qr.query("SELECT * FROM orders;", new ArrayListHandler());
		System.out.println("query.size:" + query.size());
		for (Object[] objects : query) {
			System.out.println(Arrays.toString(objects));
		}
		System.out.println("================");
	}

	@Test
//	★★BeanHandler, 将查询结果的第一条记录封装成指定的bean对象,返回
	public void f3() throws SQLException {
		QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
		myOrder bean = qr.query(sql, new BeanHandler<myOrder>(myOrder.class));
		System.out.println(bean);
		System.out.println("================");
	}

	@Test
//	 ★★BeanListHandler, 将查询结果的每一条记录封装成指定的bean对象,将每一个bean对象放入list中 返回.
	public void f4() throws SQLException {
		QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
		List<myOrder> query = qr.query(sql, new BeanListHandler<myOrder>(myOrder.class));
		for (myOrder myOrder : query) {
			System.out.println(myOrder);
		}

	}

	@Test
	// (了解)ColumnListHandler, 将查询结果的指定一列放入list中返回
	public void f5() throws SQLException {
		QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
		List<Object> query = qr.query(sql, new ColumnListHandler("totalprice"));
		for (Object object : query) {
			System.out.println(object.toString());
		}
	}

	@Test
//	 ★MapListHandler, 将查询结果的每一条记录封装map集合,将每一个map集合放入list中返回
	public void f6() throws SQLException {
		QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
		List<Map<String, Object>> query = qr.query(sql, new MapListHandler());

		for (Map<String, Object> map : query) {
			System.out.println(map);
		}
	}

	@Test
//	 ★ScalarHandler,针对于聚合函数 例如:count(*) 返回的是一个Long值
	public void f7() throws SQLException {
		QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
		Object query = qr.query("SELECT COUNT(*) FROM orders;", new ScalarHandler());
		System.out.println(query);
		
	}

}
