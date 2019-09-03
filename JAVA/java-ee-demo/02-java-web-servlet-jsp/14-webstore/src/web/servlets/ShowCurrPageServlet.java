package web.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.PageBean;
import domain.Product;
import services.ProductServices;

public class ShowCurrPageServlet extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            // 设置编码
            request.setCharacterEncoding("utf-8");

            // 获取当前页码
            int currentPage = Integer.parseInt(request.getParameter("currPage"));

            // 查询
            PageBean<Product> pBean = null;
            try {
                pBean = new ProductServices().findCurrPageProducts(currentPage);
            } catch (SQLException e) {
                e.printStackTrace();

                request.setAttribute("msg", "查询当前页商品信息出错");
                request.getRequestDispatcher("/msg.jsp").forward(request, response);
                return;
            }

            if (pBean == null) {
                request.setAttribute("msg", "查询当前页商品信息出错");
                request.getRequestDispatcher("/msg.jsp").forward(request, response);
                return;
            }
            
            request.setAttribute("pBean", pBean);
            request.getRequestDispatcher("/productsPages.jsp").forward(request, response);

        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

    }
