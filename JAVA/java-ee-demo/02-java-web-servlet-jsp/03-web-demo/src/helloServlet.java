import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class helloServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
//		https://blog.csdn.net/qq_36371449/article/details/80314024
//		ServletContext官方叫servlet上下文。服务器会为每一个工程创建一个对象，这个对象就是ServletContext对象。
//		这个对象全局唯一，而且工程内部的所有servlet都共享这个对象。所以叫全局应用程序共享对象。
		ServletContext context = this.getServletContext();

		context.setAttribute("longinCounter", 0);
	}

	@Override
//	http://localhost:8080/03-web-demo/myServelt
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("hello servelt");
		System.out.println(req.getParameter("usrname"));

		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().write("你好");
	}
}
