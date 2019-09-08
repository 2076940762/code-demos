package transaction3;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AccountDaoimpl extends JdbcDaoSupport implements AccountDao {

	/*
	 * 转入
	 */
	@Override
	public void transferIn(String name, Double money) {
		this.getJdbcTemplate().update("update t_account set money =money+? where name =? ", money, name);
	}

	@Override
	public void transferOut(String name, Double money) {
		this.getJdbcTemplate().update("update t_account set money =money-? where name =? ", money, name);
	}

}
