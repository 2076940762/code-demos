package doa;

import java.sql.Connection;

import domain.OrderItem;

public interface OrderItemDoa {

	void add(Connection connection, OrderItem item) throws Exception;

}
