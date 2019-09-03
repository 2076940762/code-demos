package cookie;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BrowsingHistory<E> extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获取商品id
		String id = request.getParameter("id");

		String history = "";

		// 获取已有的历史记录
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("history")) {
					history = cookie.getValue();
				}
			}
		}

		if (history == "") {
			// 历史记录为空
			history = id;
		} else {
			String[] arrHistory = history.split("-");
			List<String> asList = Arrays.asList(arrHistory);
			LinkedList<String> listHisstory = new LinkedList<String>(asList);

			// 如果是第二次浏览
			if (listHisstory.contains(id)) {
				listHisstory.remove(id);
				listHisstory.addFirst(id);
			} else {
				listHisstory.addFirst(id);
			}

			int len = 3 < listHisstory.size() ? 3 : listHisstory.size();
			history = "";
			for (int i = 0; i < len; i++) {
				history += listHisstory.removeFirst();
				if (i != len - 1) {
					history += "-";
				}
			}
		}

		Cookie cookie1 = new Cookie("history", history);
		
		//cookie保存时间
		cookie1.setMaxAge(3600*24*365);
		
		//设置路径
		cookie1.setPath(request.getContextPath()+"/");
		response.addCookie(cookie1);

		// 重定向
		response.sendRedirect("http://localhost:8080/06-cookie-session/product_info" + id + ".htm");
	}
}
