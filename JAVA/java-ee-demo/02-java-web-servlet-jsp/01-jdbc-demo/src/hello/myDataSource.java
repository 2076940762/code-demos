package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class myDataSource {
	private static LinkedList<Connection> pool = new LinkedList<Connection>();
	private static String DriverPath = null;
	private static String strUrl = null;
	private static String strUsr = null;
	private static String strPsswd = null;

	private static int num = 10;

	static {
		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
		DriverPath = bundle.getString("driverPath");
		strUrl = bundle.getString("url");
		strUsr = bundle.getString("usrname");
		strPsswd = bundle.getString("passwd");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < num; i++) {
			Connection con=null;
			try {
				con = DriverManager.getConnection(strUrl, strUsr, strPsswd);
			} catch (SQLException e) {
				e.printStackTrace();
				con=null;
			}
			if (con != null) {
				pool.addLast(con);
			}
		}
	}//static

	public static Connection getConnection() {
		if (pool.isEmpty()) {
			for (int i = 0; i < 5; i++) {
				Connection con=null;
				try {
					con = DriverManager.getConnection(strUrl, strUsr, strPsswd);
				} catch (SQLException e) {
					e.printStackTrace();
					con=null;
				}
				if (con != null) {
					pool.addLast(con);
				}
			}
		}
		return  pool.removeFirst();
	}
		
	public static void releaseConnectino(Connection con) {
		if (con != null) {
			pool.addLast(con);
		}
	}
	

}
