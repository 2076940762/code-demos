package server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		// * Overrides the name of the character encoding used in the body of this
		// * request. This method must be called prior to reading request parameters
		// * or reading input using getReader().
		request.setCharacterEncoding("utf-8");

		// 封装数据
		User usr = new User();

		// public static void populate(Object bean, Map properties)
		// Populate the JavaBeans properties of the specified bean, based on
		// * the specified name/value pairs.
		try {
			BeanUtils.populate(usr, request.getParameterMap());
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
//		{
////			username=tom
////			password=123
////			email=13%40qq.com
////			name=12
////			sex=%E5%A5%B3
////			birthday=2019-07-04
//			String username = request.getParameter("username");
//			String password = request.getParameter("password");
//			String email = request.getParameter("email");
//			String name = request.getParameter("name");
//
//			String sex = request.getParameter("sex");
//			String birthday = request.getParameter("birthday");
//
//			usr.setUsername(username);
//			usr.setPassword(password);
//			usr.setEmail(email);
//			usr.setName(name);
//
//			usr.setSex(sex);
//			usr.setBirthday(birthday);
//		}

		// 调用userserivce . regist(user) 返回值:int
		int i = -1;
		try {
			i = new UserService().regist(usr);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		if (i == 1) {
			System.out.println("success");
			request.setAttribute("msg", "success");
			request.getRequestDispatcher("/msg").forward(request, response);
		} else {
			System.out.println("failed");
			request.setAttribute("msg", "failed");
			request.getRequestDispatcher("/msg").forward(request, response);
		}
	}

}
