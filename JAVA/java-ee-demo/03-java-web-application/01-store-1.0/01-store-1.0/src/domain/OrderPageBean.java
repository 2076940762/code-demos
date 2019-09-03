package domain;

import java.util.LinkedList;
import java.util.List;

public class OrderPageBean {
	private Integer currPage;// 当前页
	private Integer totalRecords;// 总记录条数
	private static Integer pageSize = 3;

	private List<Order> orders = new LinkedList<Order>();
	

	public OrderPageBean(Integer currPage, Integer totalRecords, List<Order> orders) {
		super();
		this.currPage = currPage;
		this.totalRecords = totalRecords;
		this.orders = orders;
	}

	public OrderPageBean() {
		super();
	}

	

	public List<Order> getOrders() {
		return orders;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public static Integer getPageSize() {
		return pageSize;
	}

	public static void setPageSize(Integer pageSize) {
		OrderPageBean.pageSize = pageSize;
	}

	public int getTotalPages() {
		return (int) Math.ceil(totalRecords*1.0/pageSize);
	}


}
