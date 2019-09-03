package domain;

import java.io.Serializable;

public class Category implements Serializable {
	

	public Category() {
		super();
	}

	public Category(String cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	
	
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + "]";
	}



	private String cid;
	private String cname;

}
