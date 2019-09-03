package ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class postmsgServlet extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            //设置编码
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            
            //获取参数
            String usrname=request.getParameter("username");
            String sex=request.getParameter("sex");
            
            PrintWriter writer = response.getWriter();
            writer.println("hello " +usrname+"  "+sex);
            
        }

    }
