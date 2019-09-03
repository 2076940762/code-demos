package ImplementAction;

import com.opensymphony.xwork2.Action;

public class MyAction implements Action {

	public String execute() throws Exception {

		System.out.println("implement Action");
		return SUCCESS;
	}

}
