package servletapi;

import com.opensymphony.xwork2.ActionSupport;

public class Customer extends ActionSupport{

	public String save() {
		
		System.out.println("save~~~");
		return SUCCESS;
	}
	
	public String findAll() {
		
		System.out.println("findAll~~~~");
		return NONE;
	}
}
