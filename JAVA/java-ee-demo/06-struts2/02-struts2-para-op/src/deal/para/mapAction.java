package deal.para;

import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class mapAction extends ActionSupport {

	private Map<String, User> map;

	public Map<String, User> getMap() {
		return map;
	}

	public void setMap(Map<String, User> map) {
		this.map = map;
	}
	
	public String execute() {
		System.out.println(map);
		return NONE;
	}

}
