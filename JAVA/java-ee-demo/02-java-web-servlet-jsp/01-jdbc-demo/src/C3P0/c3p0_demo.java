package C3P0;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.Test;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class c3p0_demo {

	@Test
	public void f1() throws PropertyVetoException, SQLException {
		ComboPooledDataSource ds = new ComboPooledDataSource();

		ds.setDriverClass("com.mysql.jdbc.Driver");
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/mystore");
		ds.setUser("root");
		ds.setPassword("root");

		Connection con = ds.getConnection();

		PreparedStatement st = con.prepareStatement("INSERT INTO orders VALUES(?,?,?)");
		st.setInt(1, 12);
		st.setDouble(2, 234.564);
		st.setInt(3, 5);

		int i = st.executeUpdate();
		if (i == 1) {
			System.out.println("sucessed");
		} else {
			System.out.println("falied");
		}

		st.close();
		con.close();
		ds.close();
	}

	@Test
	public void f2() throws SQLException {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		Connection con = ds.getConnection();
		PreparedStatement st = con.prepareStatement("INSERT INTO orders VALUES(?,?,?)");

		st.setInt(1, 13);
		st.setDouble(2, 48.64);
		st.setInt(3, 7);

		int i = st.executeUpdate();
		if (i == 1) {
			System.out.println("sucessed");
		} else {
			System.out.println("falied");
		}

		st.close();
		con.close();
		ds.close();
	}

}
