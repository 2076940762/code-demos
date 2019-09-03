package filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Constant;
import domain.User;
import service.UserService;

public class AutoLoginFilter implements Filter
    {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
        }

        @Override
        public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
                throws IOException, ServletException {
//            类型转换
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;

//            访问登录注册界面不需要自动登录
            String uriPath = request.getRequestURI();
            if (uriPath.contains("/index.jsp") || uriPath.contains("/login")) {
                chain.doFilter(request, response);
                return;
            } 

//            查找服务器为该浏览器保存的session中有无用户
                User usr = (User) request.getSession().getAttribute("user");

                String username = null;
                String password = null;

                // 获取cookie
                Cookie[] cookies = request.getCookies();
                for (Cookie c : cookies) {
                    if ("autologin".equals(c.getName())) {
                        String value = c.getValue();
                        username = value.split("-")[0];
                        password = value.split("-")[1];
                    }
                }

//            System.out.println("URI:" + request.getRequestURI() + "\r\n" + "URL:" + request.getRequestURL());
                
//                 * URI:/18-auto-login/success.jsp
//                 * URL:http://localhost:8080/18-auto-login/success.jsp
                 

//            如果请求的cookie中没有用户名和密码，且不是登录界面则跳转到登录界面
                if ((username == null || password == null)) {
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                    return;
                }

//            如果session中没有这个用户则登录；
                if ((usr == null) || (!usr.getUsername().equals(username) || !usr.getPassword().equals(password))) {

                    try {
                        usr = new UserService().login(username, password);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        request.setAttribute("msg", "用户不存在");
                        request.getRequestDispatcher("/error.jsp").forward(request, response);
                        return;
                    }

//                将用户保存到session中
                    if (usr != null) {
                        request.getSession().setAttribute("user", usr);
                    }
                }

                chain.doFilter(request, response);
            }

        @Override
        public void destroy() {
        }

    }
