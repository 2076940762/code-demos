package web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import constant.Constant;
import domain.User;
import service.UserService;
import service.impl.UserServiceImpl;

public class UserServlet extends BaseServlet {
	public String registerUI(HttpServletRequest q, HttpServletResponse p) {

		return "/jsp/register.jsp";
	}

	public String loginUI(HttpServletRequest q, HttpServletResponse p) {

		return "/jsp/login.jsp";
	}

	/**
	 * 完成用户注册
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public String register(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 注册自定义的类型转换器
//	     * @param converter Converter to be registered
//	     * @param clazz Destination class for conversions performed by this
//	     *  Converter

		// 处理时间格式
		DateConverter dateConverter = new DateConverter();
		// 设置日期格式
		dateConverter.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
		// 注册格式
		ConvertUtils.register(dateConverter, Date.class);

		// 1数据封装
		User user = new User();
		BeanUtils.populate(user, request.getParameterMap());

		// 加密密码
		byte[] digest = MessageDigest.getInstance("md5").digest(user.getPassword().getBytes());
		user.setPassword(new BigInteger(digest).toString());

		// 1.1设置用户id
		user.setUid(UUID.randomUUID().toString().replace("-", "").toUpperCase());

		// 1.2设置激活码
		user.setCode(UUID.randomUUID().toString().replace("-", "").toLowerCase());

		// 调用service完成注册
		UserService us = new UserServiceImpl();
		us.register(user);

		request.setAttribute("msg", "注册成功,请及时激活");

		return "/jsp/msg.jsp";
	}

	/**
	 * 激活用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
//	http://localhost:8080/01-store-1.0/user?method=active&code=3660408c25e34027acf79aaa508ea753
	public String active(HttpServletRequest request, HttpServletResponse response) {
		// 获取激活码
		String code = request.getParameter("code");
		if (code == null) {
			request.setAttribute("msg", "获取不到激活码，请手动输入激活码");
			return "/jsp/msg.jsp";
		}

		UserService s = new UserServiceImpl();
		boolean res = false;

		try {
			res = s.active(code);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "激活失败");
		}

		if (res == false) {
			request.setAttribute("msg", "激活失败");
		} else {
			request.setAttribute("msg", "激活成功");
		}

		return "/jsp/msg.jsp";
	}

	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");

//		加密密码
		byte[] digest = MessageDigest.getInstance("md5").digest(password.getBytes());
		password = new BigInteger(digest).toString();

		// 登录
		UserService service = new UserServiceImpl();
		User usr = service.login(username, password);

		if (usr == null) {
			request.setAttribute("msg", "用户名密码不匹配");
			return "/jsp/msg.jsp";
		} else {
			// 未激活
			if (Constant.USER_UNACTIVED == usr.getState()) {
				request.setAttribute("msg", "你好，该用户尚未激活，请使用激活邮件激活");
				return "/jsp/msg.jsp";
			}
		}

		// 将用户放入session中
		request.getSession().setAttribute("user", usr);
//		request.getRequestDispatcher(request.getContextPath()+"/").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/");

		return null;
	}

	public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/");

		return null;
	}

}
