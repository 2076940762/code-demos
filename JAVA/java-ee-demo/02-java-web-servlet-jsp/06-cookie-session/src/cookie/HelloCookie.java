package cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		Cookie c1 = new Cookie("k1", "v1");
		Cookie c2 = new Cookie("k2", "v2");
		Cookie c3 = new Cookie("k3", "v3");
		
		response.addCookie(c1);
		response.addCookie(c2);
		response.addCookie(c3);
		
		System.out.println("O(∩_∩)O哈哈~");
		
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			String value = cookie.getValue();
			System.out.println(name +"::"+value);
		}
	}

}
