package domain;

public class CartItem {
	private Product product;// 商品
	private Double subtotal;// 小计金额
	private Integer number;// 购买数量
	

	public CartItem() {
		super();
	}

	public CartItem(Product product, Integer number) {
		super();
		this.product = product;
		this.number = number;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getSubtotal() {
		return product.getShop_price() * number;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
