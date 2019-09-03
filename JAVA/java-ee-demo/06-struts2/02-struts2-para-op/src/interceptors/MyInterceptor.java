package interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class MyInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		System.out.println("*****before******");
		
		String result = actionInvocation.invoke();
		
		System.out.println("*****end******");
		return result;
	}

}
