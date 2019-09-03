package web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serices.Accountservice;

public class AccountServlet extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");

            PrintWriter writer = response.getWriter();

            String fromusr = request.getParameter("fromusr");
            String tousr = request.getParameter("tousr");
            String money = request.getParameter("money");

            // 转账
            try {
                new Accountservice().transfer(fromusr, tousr, money);
            } catch (Exception e) {
                e.printStackTrace();

                writer.println("转账失败");

                return;
            }

            writer.println("转账成功");
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

    }
