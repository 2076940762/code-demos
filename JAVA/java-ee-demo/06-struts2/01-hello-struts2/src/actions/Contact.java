package actions;

import com.opensymphony.xwork2.ActionSupport;

public class Contact extends ActionSupport {

	public String save() {
		System.out.println("save");
		return "saveOK";
	}

	public String update() {
		System.out.println("update");
		return "updateOK";
	}
}
