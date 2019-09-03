package servletapi;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogIn1 extends ActionSupport {
//	JSP 九大内置对象
//	内置对象名		类型
//	request			HttpServletRequest
//	response		HttpServletResponse
//	config			ServletConfig
//	application		ServletContext
//	session			HttpSession
//	exception		Throwable
//	page				Object(this)
//	out				JspWriter
//	pageContext	PageContext
	
//	JSP四大域对象
//	ServletContext    	 context域  
//	HttpServletRequet 	 request域  
//	HttpSession       		 session域     --前三种在学习Servlet时就能接触到  
//	PageContext        	 page域     --jsp学习的  

//	page域： 只能在当前jsp页面中使用（当前页面）
//
//	request域： 只能在同一个请求中使用（转发）
//
//	session域： 只能在同一个会话（session对象）中使用（私有的）
//
//	context域： 只能在同一个web应用中使用。（全局的）
	/**
	 * 完全解耦合的方式处理请求参数
	 */
	@Override
	public String execute() throws Exception {
		ActionContext context = ActionContext.getContext();

		// 获取所有的请求参数
//	     * Returns a Map of the HttpServletRequest parameters when in a servlet environment or a generic Map of
//	     * parameters otherwise.
		Map<String, Object> map = context.getParameters();
		Set<String> keySet = map.keySet();
		for (String k : keySet) {
			String[] vals = (String[]) map.get(k); 
			System.out.println(k + "\t:\t" + Arrays.toString(vals));
		}

		Map<String, Object> mapSession = context.getSession();//Gets the Map of HttpSession values when in a servlet environment or a generic session map otherwise.
		mapSession.put("aaa", 1111);
		mapSession.put("bbb", 222);

		// 如果向request对象中存入值
		context.put("msg", "哈哈哈");
		
		// 获取其他map集合
		context.getSession().put("msg", "呵呵");
		context.getApplication().put("msg", "哇哦");//

		return SUCCESS;

	}

}
