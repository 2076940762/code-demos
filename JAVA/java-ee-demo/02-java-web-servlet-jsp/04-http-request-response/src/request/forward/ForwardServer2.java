package request.forward;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardServer2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ForwardServer2");
		
		String money=request.getParameter("money");
		System.out.println("money:="+money);
		
		System.out.println("暗号:"+request.getAttribute("暗号"));
	}

}
