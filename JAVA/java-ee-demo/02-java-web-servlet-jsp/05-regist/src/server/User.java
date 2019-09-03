package server;

public class User {

	public User() {
		username = " ";
		password = " ";
		email = " ";
		name = " ";
		sex = " ";
		birthday = " ";
		hobby="eat";
	}

	// id INT PRIMARY KEY AUTO_INCREMENT,
	// username VARCHAR(20),
	// PASSWORD VARCHAR(20),
	// email VARCHAR(20),

	// NAME VARCHAR(20),
	// sex VARCHAR(10),
	// birthday DATE,
	// hobby VARCHAR(50)

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	private String username;
	private String password;
	private String email;

	private String name;
	private String birthday;
	private String sex;
	private String hobby;

}
