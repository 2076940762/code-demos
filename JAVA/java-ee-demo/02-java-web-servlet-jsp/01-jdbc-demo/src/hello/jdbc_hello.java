package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class jdbc_hello {

	@Test
	public void f1() {
		System.out.println("hello");
	}

	@Test
	public void f2() throws Exception {
		// 注册驱动
		Class.forName("com.mysql.jdbc.Driver");

		// 获取连接
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mystore", "root", "root");

		// Sql
		String str_sql = "select * from orders;";

		// 创建语句执行者
		PreparedStatement st = con.prepareStatement(str_sql);

		// 执行SQL
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
		}

		rs.close();
		st.close();
		con.close();
	}

	@Test
	public void f3() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mystore", "root", "root");
		Statement st = con.createStatement();
		ResultSet res = st.executeQuery("select * from orders");
		while (res.next()) {
			System.out.println(res.getString(1) + "\t" + res.getString(2));
		}

		res.close();
		st.close();
		con.close();
	}

	@Test
	public void f4() throws SQLException {
		/**
		 * 获取连接
		 */
		Connection con = myJdbcUtil.getCon();
		if (con == null) {
			return;
		}

		String strSql = "INSERT INTO orders VALUES(?,?,?)";

		// 预编译SQL
		PreparedStatement preSt = con.prepareStatement(strSql);

		// 设置参数
		preSt.setInt(1, 10);
		preSt.setDouble(2, 23.65);
		preSt.setInt(3, 3);

		int i = preSt.executeUpdate();
		if (i == 1) {
			System.out.println("sucess");
		} else {
			System.out.println("fail");
		}

		myJdbcUtil.releaseRes(con, preSt, null);
	}

	@Test
	public void f5() throws Exception {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = myJdbcUtil2.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String strSql = "select * from orders";

		try {
			st = con.prepareStatement(strSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ResultSet resSet = st.executeQuery(strSql);

		while (resSet.next()) {
			System.out.println(resSet.getString(1) + "\t" + resSet.getString(1) + "\t" + resSet.getString(3));
		}

		myJdbcUtil2.releaseRes(con, st, resSet);
	}

	@Test
	public void testMyDataSource() throws SQLException {
		Connection con = myDataSource.getConnection();
		String strSql = "INSERT INTO usrs VALUES(?,?);";
		PreparedStatement prest = con.prepareStatement(strSql);
		prest.setInt(1, 12);
		prest.setString(2, "周杰伦");

		int i = prest.executeUpdate();
		if (i == 1) {
			System.out.println("success");
		} else {
			System.out.println("failed");
		}

		myDataSource.releaseConnectino(con);
	}

	@Test
	public  void f7() throws SQLException {
		myDataSource2java ds = new myDataSource2java();
		myConnection con = ds.getConnection();

		String strSql = "select * from orders";
		PreparedStatement prest = con.prepareStatement(strSql);

		ResultSet res = prest.executeQuery();
		while (res.next()) {
			System.out.println(res.getString(1) + "\t" + res.getString(2));
		}

		res.close();
		prest.close();
		con.close();
	}

}
