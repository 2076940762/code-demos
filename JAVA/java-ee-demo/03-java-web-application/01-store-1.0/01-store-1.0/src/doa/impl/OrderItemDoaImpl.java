package doa.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import doa.OrderItemDoa;
import domain.OrderItem;

public class OrderItemDoaImpl implements OrderItemDoa {

	@Override
	public void add(Connection connection, OrderItem item) throws Exception {
		/**
		 * `itemid` varchar(32) NOT NULL, `count` int(11) DEFAULT NULL, `subtotal`
		 * double DEFAULT NULL, `pid` varchar(32) DEFAULT NULL, `oid` varchar(32)
		 * DEFAULT NULL,
		 */

		QueryRunner runner = new QueryRunner();
		String sql = "INSERT INTO orderitem (itemid,`count`,subtotal,pid,oid) VALUES(?,?,?, ?,?);";
		runner.update(connection, sql, item.getItemid(), item.getCount(), item.getSubTotal(),
				item.getProduct().getPid(), item.getOrder().getOid());
	}

}
