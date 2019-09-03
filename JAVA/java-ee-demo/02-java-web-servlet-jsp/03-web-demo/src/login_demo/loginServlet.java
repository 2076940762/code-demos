package login_demo;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login_demo.domain.User;
import login_demo.service.UserService;

public class loginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置编码
		resp.setContentType("text/html;charset=utf-8");

		// 获取用户名和密码
		String strUsername = req.getParameter("username");
		String strPasswd = req.getParameter("password");

		// 2.调用userservice 里的login(username,password) 返回值:User user
		User usr = null;
		try {
			usr = new UserService().login(strUsername, strPasswd);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		if (usr == null) {
			resp.getWriter().println("failed to login ,3秒后自动刷新");

//			案例2-当我们登录失败,提示"用户名密码不匹配",3秒以后跳转到登录页面
//			技术分析:
//				定时刷新
//			常见的响应头-refresh
//				响应头格式:
//					refresh:秒数;url=跳转的路径
//				设置响应头:
//					response.setHeader(String key,String value);设置字符串形式的响应头
//					response.addHeader(String key,String value);追加响应头, 若之前设置设置过这个头,则追加;若没有设置过,则设置
//				设置定时刷新:
//					response.setHeader("refresh","3;url=/day0901/login.htm");
//			步骤分析:
//				登录失败之后,修改业务逻辑
//					打印之后添加一个头信息即可

			resp.addHeader("refresh", "3;url=/03-web-demo/login.htm");

		} else {
			resp.getWriter().println("welcome " + strUsername + " back");
			
			int i = (int) this.getServletContext().getAttribute("longinCounter");
			i++;
			this.getServletContext().setAttribute("longinCounter", i);
		}

	}
}
