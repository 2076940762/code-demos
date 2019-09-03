package web.servlet.admin;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Category;
import service.CategoryService;
import service.impl.CategoryServiceImpl;
import web.servlet.BaseServlet;

public class AdminCategoryServlet extends BaseServlet {

	/**
	 * 查询所有的商品分类
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// http://localhost:8080/01-store-1.0/adminCategoryServlet?method=findAll
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {

		CategoryService service = new CategoryServiceImpl();
		List<Category> list = service.findAllCategories();

		request.setAttribute("list", list);

		return "/admin/category/list.jsp";
	}

	/**
	 * 跳转到添加分类信息界面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) {
		return "/admin/category/add.jsp";
	}

	/**
	 * 添加分类
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取参数
		String cname = request.getParameter("cname");

		// 封装数据
		Category category = new Category();
		category.setCname(cname);
		category.setCid(UUID.randomUUID().toString().replace("-", "").substring(0, 32).toUpperCase());

		CategoryService service = new CategoryServiceImpl();
		service.add(category);

		// 页面重定向
		response.sendRedirect(request.getContextPath() + "/adminCategory?method=findAll");

		return "";
	}

	public String getByCid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取cid
		String cid = request.getParameter("cid");

		CategoryService service = new CategoryServiceImpl();
		Category category = service.getByCid(cid);

		request.setAttribute("category", category);
		return "/admin/category/edit.jsp";
	}

	/**
	 * 更新分类信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取参数
		String cname = request.getParameter("cname");
		String cid = request.getParameter("cid");

		// 封装数据
		Category category = new Category(cid, cname);
		category.setCid(cid);
		category.setCname(cname);

		CategoryService service = new CategoryServiceImpl();
		service.update(category);

		response.sendRedirect(request.getContextPath() + "/adminCategory?method=findAll");

		return null;
	}

	// http://localhost:8080/01-store-1.0/adminCategory?method=delete&cid=F27C676A840045D09062300CC1FE3317
	public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid = request.getParameter("cid");
		
		//调用service层完成删除
		CategoryService service =new CategoryServiceImpl();
		service.delete(cid);
		
		/*
		 * //重定向 response.sendRedirect("/adminCategory?method=findAll");
		 */
		
		//请求转发
		return 	"/adminCategory?method=findAll";
	}

}
