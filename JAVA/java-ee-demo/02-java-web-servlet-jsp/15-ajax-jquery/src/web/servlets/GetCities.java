package web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.City;
import net.sf.json.JSONArray;
import services.CityService;

public class GetCities extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            // 设置编码
            response.setContentType("text/html;charset=utf-8");

            PrintWriter writer = response.getWriter();

            // 获取参数
            String id = request.getParameter("provinceId");
            int pid = Integer.parseInt(id);

            // 查询
            List<City> list = null;
            try {
                list = new CityService().findCityiesBypid(pid);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (list != null && !list.isEmpty()) {
                writer.println(JSONArray.fromObject(list));
            }

        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

    }
