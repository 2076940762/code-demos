package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
//            设置编码
            response.setContentType("text/html;charset=utf-8");

            PrintWriter writer = response.getWriter();

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            System.out.println("username" + username);
            System.out.println("password" + password);

            writer.write("username" + username + "\n" + "password" + password);
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

    }
