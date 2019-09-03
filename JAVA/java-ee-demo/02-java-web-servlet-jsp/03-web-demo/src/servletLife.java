import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class servletLife extends HttpServlet {
	
	//	 serlvet是单实例多线程
	//	 默认第一次访问的时候,服务器创建servlet,并调用init实现初始化操作.并调用一次service方法
	//	 每当请求来的时候,服务器创建一个线程,调用service方法执行自己的业务逻辑
	//	 当serlvet被移除的时候、服务器正常关闭的时候,服务器调用servlet的destroy方法实现销毁操作.
	
	@Override
	public void init() throws ServletException {
		System.out.println("init....");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service....");
	}

	@Override
	public void destroy() {
		System.out.println("destroy...");
	}
}
