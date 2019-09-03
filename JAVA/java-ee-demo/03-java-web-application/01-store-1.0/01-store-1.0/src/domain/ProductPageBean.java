package domain;

import java.util.List;

public class ProductPageBean {
	
	public ProductPageBean(List<Product> list, Integer currPage, Integer totalrecodes) {
		super();
		this.list = list;
		this.currPage = currPage;
		this.totalrecodes = totalrecodes;
	}

	private List<Product> list;
	private static Integer pageSize = 12;
	private Integer currPage;
	private Integer totalrecodes;

	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}

	public static Integer getPageSize() {
		return pageSize;
	}

	public static void setPageSize(Integer pageSize) {
		ProductPageBean.pageSize = pageSize;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public Integer getTotalrecodes() {
		return totalrecodes;
	}

	public void setTotalrecodes(Integer totalrecodes) {
		this.totalrecodes = totalrecodes;
	}

	public Integer getTotalPage() {
		//Returns the smallest (closest to negative infinity) double value that is greater than or equal to theargument 
		//and is equal to a mathematical integer. Special cases: 
		return (int) Math.ceil(totalrecodes * 1.0 / pageSize);
	}

}
