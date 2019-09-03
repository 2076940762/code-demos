package web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class EncodingFilter
 */
public class EncodingFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//类型转换
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		
		chain.doFilter(new MyRequest(req), resp);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}

/**
 * 静态代理
 * 
 * @author qingtian
 */
class MyRequest extends HttpServletRequestWrapper {
	HttpServletRequest request;
	boolean isNeedReencoding = true;

	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public String getParameter(String name) {
		if (name == null || name.trim().isEmpty()) {
			return null;
		}
		String[] values = getParameterValues(name);
		if (values == null && values.length == 0) {
			return null;
		}
		return values[0];
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		// 获取请求方法
		String method = request.getMethod();

//		if ("post".equalsIgnoreCase(method)) {// post请求
//			try {
//				request.setCharacterEncoding("UTF-8");
//				return request.getParameterMap();
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//
//		} else 
			if ("get".equalsIgnoreCase(method) || "post".equalsIgnoreCase(method)) { // get请求
			Map<String, String[]> map = request.getParameterMap();

			if (isNeedReencoding) {
				for (String key : map.keySet()) {
					String[] arr = map.get(key);
					for (int i = 0; i < arr.length; i++) {
						try {
							arr[i] = new String(arr[i].getBytes("ISO-8859-1"), "UTF-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
				}
				isNeedReencoding = false;
			}
			return map;
		}

		return super.getParameterMap();
	}

	@Override
	public String[] getParameterValues(String name) {
		if (name == null || name.trim().isEmpty()) {
			return null;
		}

		Map<String, String[]> map = request.getParameterMap();
		if (map != null && map.size() != 0) {
			return map.get(name);
		}
		return null;
	}

}
