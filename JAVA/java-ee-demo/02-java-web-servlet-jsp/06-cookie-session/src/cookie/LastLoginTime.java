package cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LastLoginTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		response.setContentType("text/html;charset=utf-8");

		PrintWriter writer = response.getWriter();

		//
		Cookie[] cookies = request.getCookies();
		boolean isEmpty = true;
		String lastTime = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("lastTime")) {
					isEmpty = false;
					lastTime = cookie.getValue();
				}
			}
		}

		if (isEmpty) {
			writer.println("这是您第一次登陆");
		} else {
			long parseLong = Long.parseLong(lastTime);
			Date lastDate = new Date(parseLong);
			writer.println("您上次登陆系统的时间是：" + lastDate.toLocaleString());

		}

		// 设置新的访问时间
		Cookie c = new Cookie("lastTime", new java.util.Date().getTime() + "");

		// 持久化cookie
		c.setMaxAge(3600 * 24 * 365);

		// 设置路径
		c.setPath(request.getContextPath()+"/");
		
		response.addCookie(c);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
