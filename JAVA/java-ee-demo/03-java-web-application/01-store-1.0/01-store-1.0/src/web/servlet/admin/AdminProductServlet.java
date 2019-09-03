package web.servlet.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Category;
import domain.Product;
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;
import web.servlet.BaseServlet;

public class AdminProductServlet extends BaseServlet {

	/**
	 * 查询所有商品信息
	 * 
	 * @param Request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String findAll(HttpServletRequest Request, HttpServletResponse response) throws Exception {
		ProductService service = new ProductServiceImpl();
		List<Product> list =service.findAll();
		
		Request.setAttribute("list", list);
		
		return "/admin/product/list.jsp";
	}
	
	/**
	 * 跳转到商品添加页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String addUI(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		//查询所有分类信息
		CategoryService service= new CategoryServiceImpl();
		List<Category> list = service.findAllCategories();
		
		request.setAttribute("list", list);
		
		//转发
		return "/admin/product/add.jsp";
	}

}
