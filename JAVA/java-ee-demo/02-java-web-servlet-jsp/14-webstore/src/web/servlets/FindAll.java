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

public class FindAll extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            List<Product> plist = null;
            try {
                plist = new ProductServices().findAll();
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }

            request.setAttribute("list", plist);

            request.getRequestDispatcher("/products_list.jsp").forward(request, response);
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

    }
