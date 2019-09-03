package login_demo.doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import login_demo.domain.User;

public class UserDoa {
	public User getUserByNameAndPasswd(String name, String passwd) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
		String strSql = "SELECT username,`password` FROM usrs WHERE username=? AND `password`=? ; ";
		PreparedStatement prepareStatement = connection.prepareStatement(strSql);
		prepareStatement.setString(1, name);
		prepareStatement.setString(2, passwd);
		ResultSet result = prepareStatement.executeQuery();
		User usr = null;
		if (result.next()) {
			usr = new User();
			usr.setUsername(result.getString(1));
			usr.setPasswd(result.getString(2));
		} else {
			usr = null;
		}

		{
			result.close();
			prepareStatement.close();
			connection.close();
		}

		return usr;

	}
}
