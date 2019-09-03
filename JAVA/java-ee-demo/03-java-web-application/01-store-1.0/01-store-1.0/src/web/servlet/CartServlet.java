package web.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Cart;
import domain.CartItem;
import domain.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;

public class CartServlet extends BaseServlet {
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		获取参数
		String pid = request.getParameter("pid");
		int num = Integer.parseInt(request.getParameter("quantity"));
		System.out.println("商品数量：" + num);

//		根据pid从数据库中查询商品
		ProductService service = new ProductServiceImpl();
		Product product = service.geProductById(pid);

//		添加购物车
		CartItem item = new CartItem(product, num);
		this.getCart(request).add(item);

//		页面重定向
		response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");

		return null;
	}
	
	/**
	 * 删除购物车项
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
//	http://localhost:8080/01-store-1.0/cart?method=remove&pid=33
	public String remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		获取pid
		String pid = request.getParameter("pid");

		this.getCart(request).remove(pid);
		
//		页面重定向
		response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");

		return null;
	}
	
	
	/**
	 * 清空购物车
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public String clear(HttpServletRequest request,HttpServletResponse response) throws IOException {
//		清空购物车
		getCart(request).clear();
		
//		重定向
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		
		return null;
	}
	

	/**
	 * 从session中获取购物车，如果session为没有，则创建
	 * 
	 * @param request
	 * @return
	 */
	protected Cart getCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");

		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}

}
