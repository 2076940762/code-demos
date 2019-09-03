package actions;

import com.opensymphony.xwork2.ActionSupport;

public class Customer extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String save() {
		System.out.println("save Customer success");
		return "saveOK";
	}

	public String delete() {

		System.out.println("delete success");
		return "deleteOK";
	}

}
