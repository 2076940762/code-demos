package web.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import domain.Product;
import services.ProductServices;

public class updateProductServlet extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            // 设置编码
            request.setCharacterEncoding("utf-8");

            try {
                // 获取参数
                Product p = new Product();

                // 封装bean数据
                BeanUtils.populate(p, request.getParameterMap());

                new ProductServices().updateProductById(p);

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("msg", "更新失败");
                request.getRequestDispatcher("/msg.jsp").forward(request, response);
                return;
            }

            response.sendRedirect(request.getContextPath()+"/findAll");
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

    }
