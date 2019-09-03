package web.servlet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import domain.Linkman;
import web.service.LinkmanService;

public class LinkmanServlet extends BaseServlet {

	/**
	 * 添加联系人
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) {
		try {
			String custid = request.getParameter("cust_id");
			if (custid == null || custid.trim().isEmpty()) {
				throw new RuntimeException();
			}
			Long cust_id = Long.parseLong(custid);

			Map<String, String[]> map = request.getParameterMap();
			Linkman linkman = new Linkman();
			BeanUtils.populate(linkman, map);

			new LinkmanService().add(cust_id, linkman);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 跳转到联系人添加界面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) {

		return "jsp/linkman/add.jsp";
	}

	/**
	 * 查询所有的联系人
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) {
		return "/jsp/linkman/list.jsp";
	}

}
