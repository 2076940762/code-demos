package web.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import domain.Product;
import services.ProductServices;

public class AddProductServlet extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");

            // 获取用户传来的和服务端保存的code
            String pcode = request.getParameter("pcode");
            String scode = (String) request.getSession().getAttribute("scode");

            // 移除服务器端的code
            request.getSession().removeAttribute("scode");

            if (pcode == null || !pcode.equals(scode)) {
                request.setAttribute("msg", "商品已经存在，请勿重复添加！");
                request.getRequestDispatcher("/msg.jsp").forward(request, response);
                return;
            }

            Product p = new Product();
            try {
                BeanUtils.populate(p, request.getParameterMap());

            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                request.setAttribute("msg", "设置参数错误");
                request.getRequestDispatcher("/msg.jsp").forward(request, response);
                return;
            }

            p.setPid(UUID.randomUUID().toString());

            p.setPdate(new Date());

            try {
                new ProductServices().addProduct(p);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("msg", "向数据库中添加商品信息时出错！");
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
