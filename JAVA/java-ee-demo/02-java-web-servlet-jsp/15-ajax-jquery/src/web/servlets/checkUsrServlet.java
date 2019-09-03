package web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import services.UsrServices;

public class checkUsrServlet extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
//            设置响应编码
            response.setContentType("text/html; charset=utf-8");

            PrintWriter writer = response.getWriter();

//            获取参数
            String username = request.getParameter("username");
            System.out.println(username);
//            username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
//            System.out.println(username);

//            查询数据库
            User usr = null;
            try {
                usr = new UsrServices().getUsrByName(username);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (usr == null) {
//                用户名可以使用
                writer.println("1");
            } else {
//                用户名已经被注册
                writer.println("0");
            }

        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

    }
