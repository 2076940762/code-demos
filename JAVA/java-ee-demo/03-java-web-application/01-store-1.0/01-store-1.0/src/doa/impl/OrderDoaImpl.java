package doa.impl;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import doa.OrderDoa;
import domain.Order;
import domain.OrderItem;
import domain.OrderPageBean;
import domain.Product;
import domain.User;

public class OrderDoaImpl implements OrderDoa {

	@Override
	public void add(Connection connection, Order order) throws Exception {

		/*
		 * `oid` varchar(32) NOT NULL, `ordertime` datetime DEFAULT NULL, `total` double
		 * DEFAULT NULL,
		 * 
		 * `state` int(11) DEFAULT NULL, `address` varchar(30) DEFAULT NULL, `name`
		 * varchar(20) DEFAULT NULL,
		 * 
		 * `telephone` varchar(20) DEFAULT NULL, `uid` varchar(32) DEFAULT NULL,
		 */
		QueryRunner runner = new QueryRunner();
		String sql = "INSERT INTO orders (oid,ordertime,total,          state,address,`name`,         telephone,uid) "
				+ "VALUES (?,?,?,?,      ?,?,?,?)	";
		runner.update(connection, sql, order.getOid(), order.getOrdertime().toLocaleString(), order.getTotal(),
				order.getState(), order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid());
	}

	/**
	 * 查询当前页的所有订单
	 * 
	 * @throws Exception
	 */
	@Override
	public List<Order> findAllOrdersCurrPage(User usr, int currPage) throws Exception {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());

		// 查询用户的所有订单
		String sql = "SELECT * FROM orders WHERE uid=? limit ? , ? ;";
		List<Order> orderList = runner.query(sql, new BeanListHandler<Order>(Order.class), usr.getUid(),
				(currPage - 1) * OrderPageBean.getPageSize(), OrderPageBean.getPageSize());
		System.out.println(orderList);

		// 遍历所有订单查询订单对应的订单项
		sql = "SELECT * FROM orderitem WHERE oid = ? ;";
		for (Order order : orderList) {
			// 每个order中可能有多个订单项

			// 查询order中的所有订单项
			List<Map<String, Object>> maplist = runner.query(sql, new MapListHandler(), order.getOid());

			for (Map<String, Object> map : maplist) {

				System.out.println(map);

				// 每个map 为一个 OrderItem
				OrderItem orderItem = new OrderItem();
				System.out.println(orderItem);
				BeanUtils.populate(orderItem, map);
				System.out.println(orderItem);

				orderItem.setSubTotal((Double) map.get("subtotal"));

				// 将订单项添加到订单中
				order.getItems().add(orderItem);

				/*
				 * 查询订单项中对应的商品信息
				 */
				String sql2 = "SELECT * FROM product  WHERE pid =? ";
				Product product = runner.query(sql2, new BeanHandler<>(Product.class), map.get("pid"));
				orderItem.setProduct(product);
			}

		}

		System.out.println(orderList);
		return orderList;
	}

	/*
	 * 获取用户的订单数
	 */
	@Override
	public int getTotalRecordsNum(User usr) throws Exception {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "SELECT COUNT(*) FROM orders WHERE uid= ?  ; ";
		return ((Long) runner.query(sql, new ScalarHandler(), usr.getUid())).intValue();
	}

	/*
	 * 获取指定订单详情
	 */
	@Override
	public Order getOrderDetailsById(String oid) throws Exception {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());

		// 查询订单表
		String sql = "SELECT * FROM orders WHERE oid= ?  ;";
		Order order = runner.query(sql, new BeanHandler<Order>(Order.class), oid);

		// 查询订单项
		sql = " SELECT * FROM orderitem A, product B WHERE A.pid=B.pid  AND A.oid = ?  ORDER BY itemid ; ";
		List<Map<String, Object>> itemslist = runner.query(sql, new MapListHandler(), oid);

		for (Map<String, Object> map : itemslist) {
			// 封装订单项
			OrderItem orderItem = new OrderItem();
			BeanUtils.populate(orderItem, map);

			// 小计
			orderItem.setSubTotal((Double) map.get("subTotal"));

			// 封装product
			Product product = new Product();
			// @param bean JavaBean whose properties are being populated
			BeanUtils.populate(product, map);

			// 订单项关联product
			orderItem.setProduct(product);

			// 订单项插入order中
			order.getItems().add(orderItem);
		}

		return order;
	}

	/**
	 * 根据指定条件查询订单信息
	 */
	@Override
	public List<Order> findByConditions(Map<String, String> map) throws Exception {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());

		// 查询结果集
		List<Order> orderlist = null;

		// 将用名替换为用户id
		{
			String uname = map.get("uname");
			if (uname != null && !uname.trim().isEmpty()) {
				String sql = " SELECT uid FROM `user` WHERE username = ? ; ";
				String uid = (String) runner.query(sql, new ScalarHandler(),uname);

				map.put("uid", uid);
				map.remove("uname");
			}
		}

		// 组装SQL
		String sql = "SELECT * FROM orders WHERE 1=1  ";
		for (String key : map.keySet()) {
			sql += (" AND " + key + " = '" + map.get(key) + "' ");
		}
		sql = sql.substring(0, sql.length() - 1);
		
		System.out.println(sql);

		//开始查询订单详情
		orderlist = runner.query(sql, new BeanListHandler<Order>(Order.class));
		System.out.println(orderlist);

		// 遍历所有订单查询订单对应的订单项
		sql = "SELECT * FROM orderitem WHERE oid = ? ;";
		for (Order order : orderlist) {
			// 每个order中可能有多个订单项

			// 查询order中的所有订单项
			List<Map<String, Object>> maplist = runner.query(sql, new MapListHandler(), order.getOid());

			for (Map<String, Object> map1 : maplist) {

				// 每个map 为一个 OrderItem
				OrderItem orderItem = new OrderItem();
				BeanUtils.populate(orderItem, map1);
				orderItem.setSubTotal((Double) map1.get("subtotal"));

				// 将订单项添加到订单中
				order.getItems().add(orderItem);

				/*
				 * 查询订单项中对应的商品信息
				 */
				String sql2 = "SELECT * FROM product  WHERE pid =? ";
				Product product = runner.query(sql2, new BeanHandler<>(Product.class), map1.get("pid"));
				orderItem.setProduct(product);
			} // for

		} // for
		return orderlist;
	}

}
