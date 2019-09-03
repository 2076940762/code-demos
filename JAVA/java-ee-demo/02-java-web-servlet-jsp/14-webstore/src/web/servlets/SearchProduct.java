package web.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import doa.ProductDoa;
import domain.Product;
import services.ProductServices;

public class SearchProduct extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");

            // 获取参数
            String name = request.getParameter("name");
            String Kword = request.getParameter("kw");

            List<Product> list = null;
            try {
                list = new ProductServices().searchProduct(name, Kword);
            } catch (SQLException e) {
                e.printStackTrace();

                request.setAttribute("msg", "查找商品时出错");
                request.getRequestDispatcher(request.getContextPath() + "/products_list.jsp").forward(request,
                        response);
            }

            if (list == null) {
                request.setAttribute("msg", "查找商品时出错");
                request.getRequestDispatcher(request.getContextPath() + "/products_list.jsp").forward(request,
                        response);
            }

            request.setAttribute("list", list);
            request.getRequestDispatcher("/products_list.jsp").forward(request, response);
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

    }
