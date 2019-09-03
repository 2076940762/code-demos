package web.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import doa.ProductDoa;
import services.ProductServices;

public class DeleteSelectedServlet extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");
            
            //删除数据库中数据
            String[] ids = request.getParameterValues("pid");
            try {
                new ProductServices().deleteProductByIds(ids);
            } catch (SQLException e) {
                e.printStackTrace();
                
                request.setAttribute("msg", "删除数据时出错");
                request.getRequestDispatcher("/msg.jsp").forward(request, response);
                return;
            }
            
            request.getRequestDispatcher("/findAll").forward(request, response);
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

    }
