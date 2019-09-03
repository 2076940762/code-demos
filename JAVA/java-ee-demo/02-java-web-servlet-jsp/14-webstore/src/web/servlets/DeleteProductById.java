package web.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.ProductServices;

public class DeleteProductById extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");

            String pid = request.getParameter("pid");

            try {
                new ProductServices().deleteProductById(pid);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("msg", "删除失败");
                
                request.getRequestDispatcher(request.getContextPath()+"/msg.jsp");
                return;
            }

            response.sendRedirect(request.getContextPath()+"/findAll");

        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

    }
