package transaction3;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public void transfer(String outName, String inName, Double money) {

		// 转出
		accountDao.transferOut(outName, money);
		
//		int a=1/0;

		// 转入
		accountDao.transferIn(inName, money);

	}

}
