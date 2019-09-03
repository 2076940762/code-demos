package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class myJdbcUtil {

	public static java.sql.Connection getCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mystore", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return con;
	}

	public static void releaseRes(Connection con, PreparedStatement st, ResultSet resSet) {
		if (resSet != null) {
			try {
				resSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resSet = null;
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (Exception e) {
				// TODO: handle exception
				st = null;
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				con = null;
			}
		}
	}

}
