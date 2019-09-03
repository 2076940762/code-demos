package web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Category;
import domain.Product;
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;

public class IndexServlet extends BaseServlet {
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductService service =new ProductServiceImpl();
		
//		查询最新商品
		List<Product> nlist=   service.getNewProduct();
		
//		查询热门商品
		List<Product> hlist=service.getHotProduct();
		
		request.setAttribute("nlist", nlist);
		request.setAttribute("hlist", hlist);
		
		return "/jsp/index.jsp";
	}
}
