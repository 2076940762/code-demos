package deal.para;

import com.opensymphony.xwork2.ActionSupport;

public class AttrAction extends ActionSupport {

	private String username;
	private String password;
	private String age;
	private String gender;

	public String getName() {
		return username;
	}

	public void setName(String name) {
		this.username = name;
	}

	public String getPasswd() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPasswd(String passwd) {
		this.password = passwd;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	public String execute() {
		
		System.out.println(username+"\t"+password+"\t"+age+"\t"+gender);
		
		return NONE;
	}

}
