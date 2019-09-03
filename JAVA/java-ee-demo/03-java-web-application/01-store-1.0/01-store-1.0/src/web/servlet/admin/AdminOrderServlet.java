package web.servlet.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Order;
import domain.OrderItem;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import service.OrderService;
import service.impl.OrderServiceImpl;
import web.servlet.BaseServlet;

public class AdminOrderServlet extends BaseServlet {

	/**
	 * 分页查询订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	//adminOrder?method=findAll
	public String findByConditions(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> map = new HashMap<String, String>();

		// 订单编号
		String oid = request.getParameter("oid");
		if (oid != null && !oid.trim().isEmpty()) {
			map.put("oid", oid);
		}

		// 订单状态
		String state = request.getParameter("state");
		if (state != null && !state.trim().isEmpty()) {
			map.put("state", state);
		}

		// 用户名
		String uname = request.getParameter("uname");
		if (uname != null && !uname.trim().isEmpty()) {
			map.put("uname", uname);
		}

		// 收货人姓名
		String name = request.getParameter("name");
		if (name != null && !name.trim().isEmpty()) {
			map.put("name", name);
		}

		// 收货人电话
		String telephone = request.getParameter("telephone");
		if (telephone != null && !telephone.trim().isEmpty()) {
			map.put("telephone", telephone);
		}

		// 调用service查询数据
		OrderService service = new OrderServiceImpl();
		List<Order> list = service.findByConditions(map);

		request.setAttribute("olist", list);
		System.out.println(list);

		return "/admin/order/search_result.jsp";
	}

	/**
	 * 返回查询界面地址
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String findUI(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.sendRedirect(request.getContextPath() + "/admin/order/fram_set.jsp");
		return null;
	}
	
	/**
	 * 获取指定订单的详情，结果以json格式写回
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String getDetailByoid(HttpServletRequest request , HttpServletResponse response) throws Exception {
		//获取参数
		String oid = request.getParameter("oid");
		
		//调用service层完成查询
		OrderService service = new OrderServiceImpl();
		List<OrderItem> list = service.getOrderDetailsById(oid).getItems();
		
		//结果转json字符串
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String [] {"itemid" ,"order"  ,"pdate","pflag","category","is_hot" ,"market_price" } );//排除掉不需要的字段
		JSONArray json = JSONArray.fromObject(list,jsonConfig);

		//数据写回
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(json);
		
		System.out.println(json);
		
		return null;
	}

}

