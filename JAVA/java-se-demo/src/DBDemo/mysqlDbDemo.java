package DBDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class mysqlDbDemo {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		//DriverManager.registerDriver(new Driver()); //������ע������
		
		//1.ע����������
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//2.��ȡ���ݿ�����
		String strUrl = "jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC";	
		Connection con = DriverManager.getConnection(strUrl, "root", "root");
		
		//ִ��SQL���
		Statement stat = con.createStatement();
		int result = stat.executeUpdate("insert into `users1` ( `name`, `tel`, `address`) values('sadf','345','dsfgd');");
		
		System.out.println(result);
		
		con.close();
		stat.close();
	}

}











