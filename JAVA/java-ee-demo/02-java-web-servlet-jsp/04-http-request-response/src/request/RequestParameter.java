package request;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestParameter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		操作请求参数
//		String getParameter(String key):
//		String[] getParameterValues(String key):
//		Map<String ,String[]> getParameterMap();
		
		System.out.println(request.getParameter("usrname"));
		
		System.out.println(Arrays.toString(request.getParameterValues("hobby")));
		
		System.out.println("=====map=====");
		
		Map<String, String[]> parameterMap = request.getParameterMap();
		for (String key:parameterMap.keySet()) {
			System.out.println(Arrays.toString(parameterMap.get(key)));
		}
		
	}

}
