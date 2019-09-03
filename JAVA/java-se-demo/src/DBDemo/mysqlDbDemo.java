package DBDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class mysqlDbDemo {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		//DriverManager.registerDriver(new Driver()); //驱动被注册两次
		
		//1.注册驱动程序
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//2.获取数据库连接
		String strUrl = "jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC";	
		Connection con = DriverManager.getConnection(strUrl, "root", "root");
		
		//执行SQL语句
		Statement stat = con.createStatement();
		int result = stat.executeUpdate("insert into `users1` ( `name`, `tel`, `address`) values('sadf','345','dsfgd');");
		
		System.out.println(result);
		
		con.close();
		stat.close();
	}

}











