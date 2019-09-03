package deal.para;

import com.opensymphony.xwork2.ActionSupport;

public class javaBeanAction extends ActionSupport {

	private User user = new User();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String execute() {
		System.out.println(user);
		return NONE;
	}
}
