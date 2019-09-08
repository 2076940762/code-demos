package helloJdbcTemplate;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mchange.v2.c3p0.DriverManagerDataSource;

public class HelloSpringJdbcTemplate {

	@Test
	public void hello() { 
		// 数据库连接池
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		// 数据库连接参数 dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/spring");
		dataSource.setUser("root");
		dataSource.setPassword("root");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("INSERT INTO t_account VALUES(NULL,?,?) ", "张三", 10000);
	}

}
