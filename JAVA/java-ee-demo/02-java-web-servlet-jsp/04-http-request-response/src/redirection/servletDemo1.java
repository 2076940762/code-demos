package redirection;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class servletDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("taobao");

////		方式一
////		1.设置重定向状态码302
//		response.setStatus(302);
//		
////		2设置响应头
//		response.setHeader("location", "/04-http-request-response/servletDemo2");
		
//		方式二
		response.sendRedirect("http://localhost:8080/04-http-request-response/servletDemo2");
		
		
		//请求转发与重定向的区别
//		1.转发的地址栏不变的.重定向的地址栏发生变化的.
//		2.转发是一次请求一次响应,重定向是两次请求两次响应.
//		3.request域对象存取的值在转发中是有效的,在重定向无效的.
//		4.转发的路径不需要加工程名.重定向的路径需要加工程名.

	}

}
