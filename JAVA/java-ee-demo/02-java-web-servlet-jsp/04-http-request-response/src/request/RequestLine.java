package request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestLine extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		操作请求行
//		getMethod():请求方式
//		getContextPath():获取项目名称
//		getRemoteAddr():获取请求的ip地址
//		
//	操作请求头
//		String getHeader(String key)
//		常见的头信息:
//			user-agent:获取浏览器内核
//			referer:页面从那里跳转过来的
//	操作请求参数
//		String getParameter(String key):
//		String[] getParameterValues(String key):
//		Map<String ,String[]> getParameterMap();

		// 请求方式
		System.out.println("method:" + request.getMethod());

		// 项目名称
		System.out.println("项目名称:"+request.getContextPath());
		
		//ip地址
		System.out.println(request.getRemoteAddr());
		
		//协议
		System.out.println("协议:"+request.getProtocol());
		
		//请求资源
		System.out.println("uri:"+request.getRequestURI());
		System.out.println("url:"+request.getRequestURL());
		
		//请求参数
		System.out.println("请求参数:"+request.getQueryString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

	}

}







