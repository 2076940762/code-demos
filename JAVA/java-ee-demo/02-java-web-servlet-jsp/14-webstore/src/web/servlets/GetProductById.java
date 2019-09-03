package web.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Product;
import services.ProductServices;

public class GetProductById extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");

            String pid = request.getParameter("pid");
            Product p = null;
            try {
                p = new ProductServices().getProductByPid(pid);
            } catch (SQLException e) {
                e.printStackTrace();
                
                request.setAttribute("msg", "获取商品信息失败");
                request.getRequestDispatcher("msg.jsp").forward(request, response);
                return;
            }

            if (p == null) {
                request.setAttribute("msg", "获取商品信息失败");
                request.getRequestDispatcher("msg.jsp").forward(request, response);
                return;                
            }
            
            request.setAttribute("bean", p);
            request.getRequestDispatcher("/edit.jsp").forward(request, response);

        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

    }
