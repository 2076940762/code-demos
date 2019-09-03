package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UserDao {

	public int addUser(User usr) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");

		String sql = "insert  into `usrs`(`username`,`password`,`email`,`name`,`sex`,`birthday`,`hobby`) values "
				+ "	(?,?,?,?, ?,?,?); ";
//		`id`,`username`,`password`,`email`,`name`,`sex`,`birthday`,`hobby`
		
//		INSERT  INTO `usrs`(`username`,`password`,`email`,`name`,`sex`,`birthday`,`hobby`) VALUES 
//		                              ('eat','ssss','ajakldf@qq.com','adfa', '男',** NOT SPECIFIED **,** NOT SPECIFIED **,); 
		
		PreparedStatement prepareStatement = connection.prepareStatement(sql);

//		Field     Type         Null    Key     Default  Extra           
//		--------  -----------  ------  ------  -------  ----------------
//		id        int(11)      NO      PRI     (NULL)   auto_increment  
//		username  varchar(20)  YES             (NULL)                   
//		password  varchar(20)  YES             (NULL)                   
//		email     varchar(20)  YES             (NULL)                   
//		name      varchar(20)  YES             (NULL)                   
//		sex       varchar(10)  YES             (NULL)                   
//		birthday  varchar(20)  YES             (NULL)                   
//		hobby     varchar(50)  YES             (NULL)     
		
		prepareStatement.setString(1, usr.getUsername());
		prepareStatement.setString(2, usr.getPassword());
		prepareStatement.setString(3, usr.getEmail());
		prepareStatement.setString(4, usr.getName());

		prepareStatement.setString(5, usr.getSex());
		prepareStatement.setString(6, usr.getBirthday());
		prepareStatement.setString(7, usr.getHobby());

		return prepareStatement.executeUpdate();

	}

}
