package annotationaop;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * <!-- 配置目标对象 -->
 * <bean id="customerService" class="annotationaop.CustomerService" />
 */
@Service(value = "customerService")
public class CustomerService {

	public void save() {
		System.out.println("save customer~~~~");
	}

	public void update() {
		System.out.println("update customer\\*/*\\*/*\\*/*\\*/*\\*/*\\*/*\\*/*\\*");
	}
}
