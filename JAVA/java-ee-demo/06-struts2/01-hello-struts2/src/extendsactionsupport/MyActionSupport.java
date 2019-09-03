package extendsactionsupport;

import com.opensymphony.xwork2.ActionSupport;

public class MyActionSupport extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		System.out.println("extends from ActionSupport");
		return NONE;
	}

}
