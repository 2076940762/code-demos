package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.product;
import service.ProductService;

public class ShowProducts extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            // 查询所有商品信息
            List<product> productList =null;
            try {
                productList = new ProductService().findAll();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // 查询结果放入request域中
            request.setAttribute("productList", productList);

            // 请求转发
            request.getRequestDispatcher("/product-details.jsp").forward(request, response);

        }

    }
