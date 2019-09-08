package spring.ioc.annotation;

import org.springframework.stereotype.Repository;

/**
 * <bean id="customerDao" class="dao.CustomerDaoImpl" />
 */
@Repository(value = "customerDao")
public class CustomerDaoImpl implements CustomerDao {

	@Override
	public void save() {
		System.out.println("dao");
	}

}
