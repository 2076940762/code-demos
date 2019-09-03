package web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import domain.Customer;
import net.sf.json.JSONArray;
import web.service.CustomerService;

public class CustomerServlet extends BaseServlet {

	/**
	 * 添加客户
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(request.getCharacterEncoding());
		// 获取参数
		Map<String, String[]> map = request.getParameterMap();

		// 封装数据
		Customer customer = new Customer();
		BeanUtils.populate(customer, map);
		System.out.println(customer);

		// 调用service
		CustomerService service = new CustomerService();
		service.add(customer);

//		return "/customerServlet?method=findAll" ;
		response.sendRedirect(request.getContextPath() + "/customerServlet?method=findAll");
		return null;
	}

	/**
	 * 查询所有的用户并展示到界面上
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) {

		List<Customer> list = new CustomerService().findAll();

		request.setAttribute("cust_list", list);

		// 转发
		return "/jsp/customer/list.jsp";
	}

	/**
	 * 处理ajax请求
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
//	http://localhost:8080/02-CRM-system/customerServlet?method=findAll2Json
	public String findAll2Json(HttpServletRequest request, HttpServletResponse response) throws IOException {

		List<Customer> list = new CustomerService().findAll();
		
		for (Customer customer : list) {
			customer.setLinkmans(null);
		}

		//[Customer [cust_id=4, cust_name=马云云, cust_user_id=null, cust_create_id=null, cust_source=null, cust_industry=null, cust_level=null, cust_linkman=null, cust_phone=null, cust_mobile=null], Customer [cust_id=7, cust_name=韦小宝, cust_user_id=null, cust_create_id=null, cust_source=null, cust_industry=null, cust_level=null, cust_linkman=null, cust_phone=null, cust_mobile=null]]
		System.out.println(list);
		
		// list转json
		JSONArray jsonArray = JSONArray.fromObject(list);

		response.setContentType("txt/html;charset=utf-8");
		response.getWriter().println(jsonArray.toString());

		return null;
	}

	public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取参数
		String uid = request.getParameter("cust_user_id");
		if (uid == null) {
			throw new RuntimeException("参数为空");
		}

		Long cust_user_id = Long.parseLong(uid);

		new CustomerService().delete(cust_user_id);

		response.sendRedirect(request.getContextPath() + "/customerServlet?method=findAll");
		return null;
	}

//	http://localhost:8080/02-CRM-system/customerServlet?method=edit&cust_user_id=3
	public String edit(HttpServletRequest request, HttpServletResponse response) {
		String uid = request.getParameter("cust_user_id");
		if (uid == null || uid.trim().isEmpty()) {
			System.out.println("cust_user_id " + uid);
			return null;
		}

		Long cust_user_id = Long.parseLong(uid);
		Customer customer = new CustomerService().findById(cust_user_id);

		request.setAttribute("customer", customer);
		return "/jsp/customer/edit.jsp";
	}

//	http://localhost:8080/02-CRM-system/customerServlet?method=update
	public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {

		new CustomerService().update(request.getParameterMap());

		response.sendRedirect(request.getContextPath() + "/customerServlet?method=findAll");
		return null;
	}

}
