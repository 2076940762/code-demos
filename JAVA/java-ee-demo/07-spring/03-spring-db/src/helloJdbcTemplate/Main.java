package helloJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Main {

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	/*
	 * 插入
	 */
	@Test
	public void f1() {
		jdbcTemplate.update("INSERT INTO t_account VALUES(NULL,?,?) ", "哈哈", 10000);
	}

	/**
	 * 更新
	 */
	@Test
	public void f2() {
		jdbcTemplate.update("update t_account set money =? where id =? ", 123456, 3);
	}

	/*
	 * 删除
	 */
	@Test
	public void f3() {
		jdbcTemplate.update("delete from t_account where id = ? ", 4);
	}

	/*
	 * 查询单行数据
	 */
	@Test
	public void f4() {
		Account account = jdbcTemplate.queryForObject("select * from t_account where id=? ", new RowMapper<Account>() {

			@Override
			public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
				Account account2 = new Account();
				account2.setId(rs.getInt("id"));
				account2.setName(rs.getString("name"));
				account2.setMoney(rs.getDouble("money"));
				return account2;
			}
			
		}, 3);
		
		System.out.println(account);
	}

	/*
	 * 查询所有的数据
	 */
	@Test
	public void f5() {
		List<Account> list = jdbcTemplate.query("select * from t_account ", new RowMapper<Account>() {

			@Override
			public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
				Account account2 = new Account();
				account2.setId(rs.getInt("id"));
				account2.setName(rs.getString("name"));
				account2.setMoney(rs.getDouble("money"));
				return account2;
			}
			
		});
		
		System.out.println(list);
	}
	
}
