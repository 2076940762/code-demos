package domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Order {

	public Order() {
		super();
	}

	public Order(String oid, Date ordertime, Double total, Integer state, String address, String name, String telephone,
			User usr) {
		super();
		this.oid = oid;
		this.ordertime = ordertime;
		this.total = total;
		this.state = state;
		this.address = address;
		this.name = name;
		this.telephone = telephone;
		this.user = usr;
	}

	/*
	 * CREATE TABLE `orders` ( `oid` VARCHAR(32) NOT NULL, `ordertime` DATETIME
	 * DEFAULT NULL, `total` DOUBLE DEFAULT NULL,
	 * 
	 * `state` INT(11) DEFAULT NULL, `address` VARCHAR(30) DEFAULT NULL, `name`
	 * VARCHAR(20) DEFAULT NULL,
	 * 
	 * `telephone` VARCHAR(20) DEFAULT NULL, `uid` VARCHAR(32) DEFAULT NULL, PRIMARY
	 * KEY (`oid`) ) ENGINE=INNODB DEFAULT CHARSET=utf8;
	 */

	private String oid;
	private Date ordertime;
	private Double total;

	private Integer state = 0;//0 代付款，1 已付款，待发货 ，2 已发货  ，3已收货
	private String address;
	private String name;
	private String telephone;

	private User user;// 用户
	private List<OrderItem> items = new LinkedList<OrderItem>();

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User usr) {
		this.user = usr;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime + ", total=" + total + ", state=" + state + ", address="
				+ address + ", name=" + name + ", telephone=" + telephone + ", user=" + user + ", items=" + items + "]";
	}

}
