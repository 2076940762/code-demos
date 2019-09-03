package web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Category;
import net.sf.json.JSONArray;
import service.CategoryService;
import service.impl.CategoryServiceImpl;

/**
 * Servlet implementation class CategoryServlet
 */
public class CategoryServlet extends BaseServlet {

	/**
	 * 查找所有商品分类
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 调用service查询 数据库
		CategoryService cs = new CategoryServiceImpl();
		List<Category> clist = cs.findAllCategories();

//		request.setAttribute("clist", clist);
		JSONArray json = JSONArray.fromObject(clist);//list 转json
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(json.toString());
		return null;
	}
}







