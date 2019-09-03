package DBCP;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

public class DBCP_demo {

	@Test
	public void f1() throws Exception {
		BasicDataSource ds = new BasicDataSource();

		// 设置参数
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/mystore");
		ds.setUsername("root");
		ds.setPassword("root");

		// 获取连接
		Connection con = ds.getConnection();

		// 获取语句执行者
		java.sql.Statement st = con.createStatement();

		java.sql.ResultSet rs = st.executeQuery("select * from orders");
		while (rs.next()) {
			System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
		}

		rs.close();
		st.close();
		con.close();
	}

	@Test
	public void f2() throws Exception {
		Properties pro = new Properties();
		pro.load(new FileInputStream("src/DBCP/DBCP.properties"));

		BasicDataSourceFactory dsFactory = new BasicDataSourceFactory();
		DataSource ds = dsFactory.createDataSource(pro);
		
		Connection con= ds.getConnection();
		
		String sql="INSERT INTO orders VALUES(?,?,?)";
		
		PreparedStatement st=con.prepareStatement(sql);
		
		st.setInt(1, 11);
		st.setDouble(2, 23.66);
		st.setInt(3, 2);
		
		int i =st.executeUpdate();
		System.out.println(i);
		
		st.close();
		con.close();

	}

}
