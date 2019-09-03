package web.servlet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.ProductPageBean;
import domain.Product;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.test.JSONAssert;
import service.ProductService;
import service.impl.ProductServiceImpl;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends BaseServlet {
	/**
	 * 根据商品id查询商品详细信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// http://localhost:8080/01-store-1.0/product?method=geProductById&pid=1
	public String geProductById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid = request.getParameter("pid");

		ProductService service = new ProductServiceImpl();
		Product p = service.geProductById(pid);

		request.setAttribute("product", p);

		// 浏览记录
		{
			List<String> history = null;
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if ("ids".equals(c.getName())) {
						String[] arrIds = c.getValue().split("-");
						history = new LinkedList<String>(Arrays.asList(arrIds));
					}
				}
			}

			if (history == null) {
//						如果目前还没有历史记录,t添加当前id
				history = new LinkedList<String>();
				history.add(pid);
			} else {
				if (history.contains(pid)) {
//							如果历史记录中包含当前商品id,则将当前商品id移动到最前面
					history.remove(pid);
					history.add(0, pid);
				} else {
					history.add(0, pid);
				}
			}

			if (history.size() > 3) {
				history = new LinkedList<String>(history.subList(0, 3));
			}

			String ids = "";
			for (int i = 0; i < history.size(); i++) {
				ids += (history.get(i) + "-");
			}
			ids = ids.substring(0, ids.length() - 1);

			Cookie cookie = new Cookie("ids", ids);
			cookie.setPath(request.getContextPath() + "/");
			cookie.setMaxAge(3600);
			response.addCookie(cookie);
		}

		return "/jsp/product_info.jsp";
	}

	/**
	 * 获取当前页商品数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	// /product?method=findByPageid&cid="+this.cid+"&currPage=1'>
	public String findByPageid(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		获取参数
		String cid = request.getParameter("cid");
		int currPage = Integer.parseInt(request.getParameter("currPage"));

//		获取pageban
		ProductService service = new ProductServiceImpl();
		ProductPageBean pageBean = service.findByPageid(cid, currPage, ProductPageBean.getPageSize());

		request.setAttribute("pageBean", pageBean);

		return "/jsp/product_list.jsp";
	}

	/**
	 * 根据商品id查询商品详细信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// http://localhost:8080/01-store-1.0/product?method=geProductById4ajax&pid=1
	public String geProductById4ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid = request.getParameter("pid");

		ProductService service = new ProductServiceImpl();
		Product p = service.geProductById(pid);

		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(p.toString());
//		System.out.println(JSONObject.fromObject(p).toString());
//		response.getWriter().println();

		return null;
	}

}
