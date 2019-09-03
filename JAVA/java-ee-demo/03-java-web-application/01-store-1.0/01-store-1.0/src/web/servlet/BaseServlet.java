package web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class BaseServlet extends HttpServlet {

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//		获取要调用的方法名 http://localhost:8080/01-store-1.0/user?method=add
			String funName = request.getParameter("method");
			if (funName == null) {
				funName = "index";
			}

//		获取字节码对象
			Class<? extends BaseServlet> class1 = this.getClass();

//			获取对应大方法对象
			Method method = class1.getDeclaredMethod(funName, HttpServletRequest.class, HttpServletResponse.class);

//			所有方法返回请求转发的路径
			String url = (String) method.invoke(this, request, response);

			if (url != null &&  !url.trim().equals("")) {
//			转发
				request.getRequestDispatcher(url).forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
//			throw new RuntimeException();
		}

	}

	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}

}
