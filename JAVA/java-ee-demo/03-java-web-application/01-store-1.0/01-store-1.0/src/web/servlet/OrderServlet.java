package web.servlet;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Cart;
import domain.CartItem;
import domain.Order;
import domain.OrderItem;
import domain.OrderPageBean;
import domain.User;
import service.OrderService;
import service.impl.OrderServiceImpl;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends BaseServlet {

	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		判断用户是否登录
		User usr = (User) request.getSession().getAttribute("user");
		if (usr == null) {
			request.setAttribute("msg", "请先登录");
			request.getRequestDispatcher("/jsp/msg.jsp").forward(request, response);
			return null;
		}

//		创建新订单
		Order order = new Order();

//		设置订单id
		order.setOid(UUID.randomUUID().toString().substring(0, 32));

//		设置订单创建时间
		order.setOrdertime(new Date());

//		用户
		order.setUser(usr);

		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			request.setAttribute("msg", "亲，购物车空空如也~~~~~~，先去逛逛吧！");
			request.getRequestDispatcher("/jsp/msg.jsp").forward(request, response);
			return null;
		}

//		订单项
		Collection<CartItem> items = cart.getItems();
		for (CartItem it : items) {
			OrderItem orderItem = new OrderItem();

//			数量
			orderItem.setCount(it.getNumber());

//			product
			orderItem.setProduct(it.getProduct());

//			小计 金额
			orderItem.setSubTotal(it.getSubtotal());

//			设置订单项id
			orderItem.setItemid(UUID.randomUUID().toString().substring(0, 32));

			orderItem.setOrder(order);

//			将订单项添加到订单中
			order.getItems().add(orderItem);
		}

//		总金额
		order.setTotal(cart.getToatlAmount());

//		调用service层，将订单数据存入数据
		OrderService service = new OrderServiceImpl();
		service.add(order);

//		将order放入request域中
		request.setAttribute("order", order);

//		移除session中的cart
		request.getSession().removeAttribute("cart");

//		请求转发
		return "/jsp/order_info.jsp";
	}

	/**
	 * 查询当前页所有订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
//	http://localhost:8080/01-store-1.0/order?method=findAllByPage&currPage=1
	public String findAllByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		从session中获取已登录用户
		User usr = (User) request.getSession().getAttribute("user");
		if (usr == null) {
			request.setAttribute("msg",
					"<h2>亲，请先 <a href='" + request.getContextPath() + "/jsp/login.jsp'>登录</a></h2>");
			return "/jsp/msg.jsp";
		}

//		获取当前页码
		int currPage = Integer.parseInt(request.getParameter("currPage"));

//		调用service完成数据封装
		OrderService service = new OrderServiceImpl();
		OrderPageBean pageBean = service.findAllOrdersCurrPage(usr, currPage);

		request.setAttribute("pageBean", pageBean);
		return "/jsp/order_list.jsp";
	}

	//http://localhost:8080/01-store-1.0/order?method=getOrderDetailsById&oid=1608c776-4032-4b74-b5fe-3d2b9e68
	public String getOrderDetailsById(HttpServletRequest request , HttpServletResponse response) throws Exception {
		//是否已登录
		User usr = (User) request.getSession().getAttribute("user");
		if (usr == null) {
			request.setAttribute("msg",
					"<h2>亲，请先 <a href='" + request.getContextPath() + "/jsp/login.jsp'>登录</a></h2>");
			return "/jsp/msg.jsp";
		}
		
		//获取订单编号
		String oid = request.getParameter("oid");
		
		//调用service层
		OrderService service =new OrderServiceImpl();
		Order order=service.getOrderDetailsById(oid);
		
		//请求转发
		request.setAttribute("order", order);
		return "/jsp/order_info.jsp";
	}
	
	
}
