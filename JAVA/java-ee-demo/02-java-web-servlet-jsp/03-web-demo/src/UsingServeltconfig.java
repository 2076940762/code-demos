
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UsingServeltconfig
 */
public class UsingServeltconfig extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
//	ServletConfig:(了解)
//	servlet配置对象
//	作用:
//		1.获取当前servlet的名称
//		2.获取当前servlet的初始化参数
//		3.获取全局管理者
//	方法:
//		String getServletName():获取当前servlet的名称(web.xml配置的servlet-name)
//		
//		String  getInitParameter(String key):通过名称获取指定的参数值
//		Enumeration getInitParameterNames() :获取所有的参数名称
//			初始化参数是放在 web.xml文件 
//				servlet标签下子标签 init-param
//				
//		★getServletContext():获取全局管理者
//	servletconfig是由服务器创建的,在创建servlet的同时也创建了它,通过servlet的init(ServletConfig config)将config对象
//	传递给servlet,由servlet的getServletConfig方法获取
	
	//	http://localhost:8080/03-web-demo/UsingServeltconfig
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletConfig config = this.getServletConfig();
		
		System.out.println("name"+config.getServletName());
		
		System.out.println("usr"+config.getInitParameter("usr"));
		
		System.out.println("----------------------------");
		
		Enumeration<String> names = config.getInitParameterNames();
		while(names.hasMoreElements()) {
			System.out.println(names.nextElement());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
