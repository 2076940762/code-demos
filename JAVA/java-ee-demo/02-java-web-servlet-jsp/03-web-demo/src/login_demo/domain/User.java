package login_demo.domain;

public class User {
	// id INT PRIMARY KEY AUTO_INCREMENT,
	// username VARCHAR(20),
	// PASSWORD VARCHAR(20),
	// email VARCHAR(20),
	
	// NAME VARCHAR(20),
	// sex VARCHAR(10),
	// birthday DATE,
	// hobby VARCHAR(50)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getEmil() {
		return emil;
	}

	public void setEmil(String emil) {
		this.emil = emil;
	}

	private String username;
	private String passwd;
	private String emil;
}
