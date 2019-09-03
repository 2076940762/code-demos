package doa;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import domain.Order;
import domain.User;

public interface OrderDoa {

	void add(Connection connection, Order order) throws Exception;

	List<Order> findAllOrdersCurrPage(User usr, int currPage) throws Exception;

	int getTotalRecordsNum(User usr) throws Exception;

	Order getOrderDetailsById(String oid) throws Exception;

	List<Order> findByConditions(Map<String, String> map) throws Exception;

}
