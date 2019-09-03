package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class myJdbcUtil2 {
	static final String STRDRIVER;
	static final String strUrl;
	static final String strUsr;
	static final String strPasswd;

	static {
		ResourceBundle resBundle = ResourceBundle.getBundle("jdbc");

		STRDRIVER = resBundle.getString("driverPath");
		strUrl = resBundle.getString("url");
		strUsr = resBundle.getString("usrname");
		strPasswd = resBundle.getString("passwd");
	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(STRDRIVER);

		return DriverManager.getConnection(strUrl, strUsr,  strPasswd);
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
