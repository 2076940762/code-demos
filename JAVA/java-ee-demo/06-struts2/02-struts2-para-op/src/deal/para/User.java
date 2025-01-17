package deal.para;

public class User {
	
	

	public User() {
		super();
	}

	public User(String username, String password, String age, String gender) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
		this.gender = gender;
	}

	private String username;
	private String password;
	private String age;
	private String gender;

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

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", age=" + age + ", gender=" + gender + "]";
	}

}
