package web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Province;
import net.sf.json.JSONArray;
import services.ProvinceService;

/**
 * Servlet implementation class GetProvinces
 */
public class GetProvinces extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
         *      response)
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("txt/html;charset=utf-8");
            PrintWriter writer = response.getWriter();

            List<Province> list = null;
            try {
                list = new ProvinceService().findAllProvinces();
            } catch (SQLException e) {
                e.printStackTrace();
                return ;
            }
            
            if(list != null && !list.isEmpty()) {
//                writer.println(list);
                JSONArray jsonList = JSONArray.fromObject(list);
                writer.println(jsonList);
            }

        }

        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
         *      response)
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

    }
