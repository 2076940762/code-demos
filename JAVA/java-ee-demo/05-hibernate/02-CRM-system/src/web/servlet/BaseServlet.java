package web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {

	/**
	 * 实现根据前台界面传入的method参数来决定调用哪个方法
	 * http://localhost:8080/02-CRM-system/customerServlet?method=add
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 获取要调用的方法名称
			String methodName = request.getParameter("method");
			if (methodName == null || methodName.trim().isEmpty()) {
				methodName = "index";
			}

			// 获取servlet的class对象
			Class<? extends BaseServlet> class1 = this.getClass();

			// 获取方法对象
			Method method = class1.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

			// 执行
			String uri = (String) method.invoke(this, request, response);

			// 请求转发
			if (uri != null && !uri.trim().isEmpty()) {
				request.getRequestDispatcher(uri).forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
