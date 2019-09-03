package request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormPara extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("====");
		
		String usrname=request.getParameter("username");
		String passwd=request.getParameter("password");
		
		System.out.println(usrname+"::"+passwd);
		
		//		中文乱码   https://www.jianshu.com/p/363f40ced830
		//浏览器发送的字符串为utf8编码，Tomcat 使用 iso-8859-1编码
		//usrname.getBytes("iso-8859-1") 将本来是utf8编码而用 iso-8859-1解释的字符串还原为utf8
		System.out.println(new String(usrname.getBytes("iso-8859-1") ,"utf-8") );
		System.out.println(new String(passwd.getBytes("iso-8859-1") ,"utf-8") );
	}

}
