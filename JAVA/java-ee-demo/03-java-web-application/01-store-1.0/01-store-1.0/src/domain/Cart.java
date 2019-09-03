package domain;

import java.util.Collection;
import java.util.LinkedHashMap;

public class Cart {
	private LinkedHashMap<String, CartItem> map;// key 为商品id
	private Double toatlAmount=0.0;// 总金额

	public LinkedHashMap<String, CartItem> getMap() {
		return map;
	}

	public void setMap(LinkedHashMap<String, CartItem> map) {
		this.map = map;
	}

	public Double getToatlAmount() {
		return toatlAmount;
	}

	public void setToatlAmount(Double toatlAmount) {
		this.toatlAmount = toatlAmount;
	}

	/**
	 * 向购物车中添加cartitem
	 */
	public void add(CartItem item) {
//		获取商品的id
		String pid = item.getProduct().getPid();
		
		

		if (map.containsKey(pid)) {
//			更新商品数量
			map.get(pid).setNumber(map.get(pid).getNumber() + item.getNumber());
		} else {
			map.put(pid, item);
		}

//		更新总金额
		toatlAmount += item.getSubtotal();
	}

	public void remove(String pid) {
		CartItem item = map.remove(pid);

		toatlAmount -= item.getSubtotal();
	}

	public void clear() {

		map.clear();

		toatlAmount = 0.0;
	}

	public Cart(LinkedHashMap<String, CartItem> map, Double toatlAmount) {
		super();
		this.map = map;
		this.toatlAmount = toatlAmount;
	}

	public Cart() {
		super();
		map=new LinkedHashMap<String, CartItem>();
		
	}

	
	/**
	 * 获取购物车项
	 * @return
	 */
	public Collection<CartItem> getItems() {
		return map.values();
	}
	
	
	
}
