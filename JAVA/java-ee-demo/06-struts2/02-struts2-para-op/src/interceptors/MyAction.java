package interceptors;

import com.opensymphony.xwork2.ActionSupport;

public class MyAction extends ActionSupport {
	
	public String execute() {
		
		System.out.println("my action~~~");
		
		return NONE;
	}

}
