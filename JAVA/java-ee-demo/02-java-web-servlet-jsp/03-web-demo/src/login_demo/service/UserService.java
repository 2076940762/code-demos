package login_demo.service;

import java.sql.SQLException;

import login_demo.doa.UserDoa;
import login_demo.domain.User;

public class UserService {
	public User login( String strUsername,String strPasswd) throws ClassNotFoundException, SQLException {
		UserDoa usrDoa=new UserDoa();
		return usrDoa.getUserByNameAndPasswd(strUsername, strPasswd);
	}
}
