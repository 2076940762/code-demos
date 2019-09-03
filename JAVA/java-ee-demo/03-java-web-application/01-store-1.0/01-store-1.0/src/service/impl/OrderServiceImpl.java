package service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import doa.OrderDoa;
import doa.OrderItemDoa;
import doa.impl.OrderDoaImpl;
import doa.impl.OrderItemDoaImpl;
import domain.Order;
import domain.OrderItem;
import domain.OrderPageBean;
import domain.User;
import service.OrderService;

public class OrderServiceImpl implements OrderService {

	@Override
	public void add(Order order) throws Exception {
		Connection connection = null;
		try {
			// 开启事务
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);

			// 调用doa层
			OrderDoa odoa = new OrderDoaImpl();
			odoa.add(connection, order);

			OrderItemDoa idoa = new OrderItemDoaImpl();
			for (OrderItem item : order.getItems()) {
				idoa.add(connection, item);
			}

			// 事务提交
			connection.commit();

			// 关闭连接
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();

			// 回滚
			connection.rollback();
			connection.close();
		}

	}

	/**
	 * 查询当前页的所有订单
	 */
	@Override
	public OrderPageBean findAllOrdersCurrPage(User usr, int currPage) throws Exception {
		OrderDoa doa = new OrderDoaImpl();

		// 查询所有订单
		List<Order> orders = doa.findAllOrdersCurrPage(usr, currPage);

		// 查询总记录条数
		Integer totalRecords = doa.getTotalRecordsNum(usr);

		// public OrderPageBean(Integer currPage, Integer totalRecords, List<Order>
		// orders)
		return new OrderPageBean(currPage, totalRecords, orders);
	}

	/*
	 * 获取指定订单详细信息
	 */
	@Override
	public Order getOrderDetailsById(String oid) throws Exception {
		OrderDoa doa = new OrderDoaImpl();

		return doa.getOrderDetailsById(oid);
	}

	/**
	 * 根据指定条件查询订单
	 */
	@Override
	public List<Order> findByConditions(Map<String, String> map) throws Exception {
		OrderDoa dao = new OrderDoaImpl();
		return dao.findByConditions(map);
	}

}
