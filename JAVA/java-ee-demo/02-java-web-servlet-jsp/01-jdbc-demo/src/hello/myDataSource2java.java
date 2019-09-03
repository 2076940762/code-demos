package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class myDataSource2java {
	// 连接池
	private LinkedList<myConnection> pool = new LinkedList<myConnection>();

	// 获取连接需要的参数
	private static final String STRDRIVERPATH;
	private static final String STRURL;
	private static final String STRUSRNAME;
	private static final String STRPASSWD;

	// 连接池初始大小
	private static int num = 10;

	// 连接耗光后，每次新增的连接数
	private static int increasement = 5;

	static {
		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
		STRDRIVERPATH = bundle.getString("driverPath");
		STRURL = bundle.getString("url");
		STRUSRNAME = bundle.getString("usrname");
		STRPASSWD = bundle.getString("passwd");
	}

	public myDataSource2java() {
		try {
			Class.forName(STRDRIVERPATH);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		for (int i = 0; i < num; i++) {
			Connection con = null;
			try {
				con = DriverManager.getConnection(STRURL, STRUSRNAME, STRPASSWD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				con =null;
			}
			
			if (con != null) {
				pool.addLast(new myConnection(con, pool));
			}
		}
	}
	
	public myConnection getConnection() {
		if(pool.isEmpty()) {
			for (int i = 0; i < increasement; i++) {
				Connection con = null;
				try {
					con = DriverManager.getConnection(STRURL, STRUSRNAME, STRPASSWD);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					con =null;
				}
				
				if (con != null) {
					pool.addLast(new myConnection(con, pool));
				}
			}
		}
		
		return pool.removeFirst();
	}
	
	
	
}
