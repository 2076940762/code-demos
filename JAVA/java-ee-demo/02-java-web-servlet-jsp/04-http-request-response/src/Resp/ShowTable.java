package Resp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		处理响应中文乱码:
//			方式1:★
//				response.setContentType("text/html;charset=utf-8");
//			方式2:理解
//				response.setHeader("content-type", "text/html;charset=utf-8");
		
		response.setContentType("text/html;charset=utf-8");
		String strTable="<table border=\"1\" height=\"500px\" width=\"100%\">\r\n" + 
				"      <tr>\r\n" + 
				"        <td>1</td>\r\n" + 
				"        <td>2</td>\r\n" + 
				"        <td>3</td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td>4</td>\r\n" + 
				"        <td>5</td>\r\n" + 
				"        <td>6</td>\r\n" + 
				"      </tr>\r\n" + 
				"      <tr>\r\n" + 
				"        <td>7</td>\r\n" + 
				"        <td>8</td>\r\n" + 
				"        <td>9</td>\r\n" + 
				"      </tr>\r\n" + 
				"    </table>";
		response.getWriter().print(strTable);
		
	}

}
