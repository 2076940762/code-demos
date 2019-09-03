package service;

import java.util.List;
import java.util.Map;

import domain.Order;
import domain.OrderPageBean;
import domain.User;

public interface OrderService {

	void add(Order order) throws Exception;

	OrderPageBean findAllOrdersCurrPage(User usr, int currPage) throws Exception;

	Order getOrderDetailsById(String oid) throws Exception;

	List<Order> findByConditions(Map<String, String> map) throws Exception;

}
