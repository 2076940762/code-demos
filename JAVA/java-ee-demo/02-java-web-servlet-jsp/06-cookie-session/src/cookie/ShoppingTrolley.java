package cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingTrolley extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		response.setContentType("text/html;charset=utf-8");

		// 获取商品名
		String name = request.getParameter("name");

		HttpSession session = request.getSession();
		LinkedHashMap<String, Integer> trolley = (LinkedHashMap<String, Integer>) session.getAttribute("trolley");

		// 如果 购物车不存在，则创建购物车
		if (trolley == null) {
			trolley = new LinkedHashMap<String, Integer>();
			session.setAttribute("trolley", trolley);
		}

		int count = 0;
		if (trolley.keySet().contains(name)) {
			count = trolley.get(name);
		}
		count++;

		trolley.put(name, count);

		System.out.println(trolley);

		// 转换中文编码为ut8
//		name = new String(name.getBytes("iso-8859-1"), "utf-8");// iso8859-1

		PrintWriter writer = response.getWriter();
		writer.println("已经将<b>" + name + "</b>添加到购物车");
		writer.print("<br/>");
		writer.println("<a href=\"" + request.getContextPath() + "/product_list.jsp" + "\"> 继续购物</a>");
		writer.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		writer.println("<a href=\"" + request.getContextPath() + "/cart.jsp" + "\"> 查看购物车</a>");
	}

}
