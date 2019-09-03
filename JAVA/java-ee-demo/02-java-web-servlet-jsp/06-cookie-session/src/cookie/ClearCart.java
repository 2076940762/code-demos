package cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClearCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)			throws ServletException, IOException {
		//Invalidates this session then unbinds any objects bound to it.
		request.getSession().invalidate();
		
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}

}
