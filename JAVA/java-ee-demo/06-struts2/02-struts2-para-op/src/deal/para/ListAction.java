package deal.para;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class ListAction extends ActionSupport {
	private List<User> list;

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}
	
	public String execute() {
		for (User user : list) {
			System.out.println(user);
		}
		return NONE;
	}
}
