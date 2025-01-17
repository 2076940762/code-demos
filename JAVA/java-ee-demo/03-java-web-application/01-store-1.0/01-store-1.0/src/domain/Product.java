package domain;

import java.util.Date;

public class Product {
//	CREATE TABLE `product` (
//			  `pid` VARCHAR(32) NOT NULL,
//			  `pname` VARCHAR(50) DEFAULT NULL,
//			  `market_price` DOUBLE DEFAULT NULL,

//			  `shop_price` DOUBLE DEFAULT NULL,
//			  `pimage` VARCHAR(200) DEFAULT NULL,
//			  `pdate` DATE DEFAULT NULL,

//			  `is_hot` INT(11) DEFAULT NULL,
//			  `pdesc` VARCHAR(255) DEFAULT NULL,
//			  `pflag` INT(11) DEFAULT NULL,

//			  `cid` VARCHAR(32) DEFAULT NULL,
//			  PRIMARY KEY (`pid`),
//			  KEY `sfk_0001` (`cid`),
//			  CONSTRAINT `sfk_0001` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
//			) ENGINE=INNODB DEFAULT CHARSET=utf8;	

	private String pid;
	private String pname;
	private Double market_price;

	private Double shop_price;
	private String pimage;
	private Date pdate;

	private Integer is_hot = 0; // 是否热门商品
	private String pdesc;
	private Integer pflag = 0;// 是否下架

	private Category category;// 商品分类

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Double getMarket_price() {
		return market_price;
	}

	public void setMarket_price(Double market_price) {
		this.market_price = market_price;
	}

	public Double getShop_price() {
		return shop_price;
	}

	public void setShop_price(Double shop_price) {
		this.shop_price = shop_price;
	}

	public String getPimage() {
		return pimage;
	}

	public void setPimage(String pimage) {
		this.pimage = pimage;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public Integer getIs_hot() {
		return is_hot;
	}

	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public Integer getPflag() {
		return pflag;
	}

	public void setPflag(Integer pflag) {
		this.pflag = pflag;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", market_price=" + market_price + ", shop_price="
				+ shop_price + ", pimage=" + pimage + ", pdate=" + pdate + ", is_hot=" + is_hot + ", pdesc=" + pdesc
				+ ", pflag=" + pflag + ", category=" + category + "]";
	}

	public String toJsonString() {
//		格式1:value可以为任意值		{"key":value,"key1":value1}
//		格式2:e可以为任意值		[e1,e2]

		return "{\"pid\":" + pid + ", \"pname\":" + pname + ", \"market_price\":" + market_price + ", \"shop_price\":"
				+ shop_price + ", \"pimage\":" + pimage + ", \"pdate\":" + pdate + ", \"is_hot\":" + is_hot
				+ ", \"pdesc\":" + pdesc + ", \"pflag\":" + pflag + "}";
	}

}
