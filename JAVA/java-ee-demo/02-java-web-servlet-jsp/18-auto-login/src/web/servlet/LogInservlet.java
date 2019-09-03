package web.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Constant;
import domain.User;
import service.UserService;

public class LogInservlet extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
//            设置编码
            response.setContentType("text/html;charset=utf-8");
            request.setCharacterEncoding("utf-8");

//            获取参数
            String username = request.getParameter("username");
            String password = request.getParameter("password");

//            查询用户
            User usr = null;
            try {
                usr = new UserService().login(username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (usr == null) {
                request.setAttribute("msg", "用户名不存在");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }

//            登录成功将用户从到session中
            request.getSession().setAttribute("user", usr);

//            自动登录
            if (Constant.AUTO_LOGIN_VALUE.equals(request.getParameter("autologin"))) {
//                cookie 中文
                Cookie c = new Cookie("autologin", URLEncoder.encode( username + "-" + password, "utf-8"));
                c.setPath(request.getContextPath() + "/");
                c.setMaxAge(3600 * 12);

                response.addCookie(c);
            }

            response.sendRedirect(request.getContextPath() + "/success.jsp");
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

    }
