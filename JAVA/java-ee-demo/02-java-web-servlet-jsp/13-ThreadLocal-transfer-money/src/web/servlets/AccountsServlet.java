package web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.accountService;

public class AccountsServlet extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            
//            Overrides the name of the character encoding used in the body of this request.
//            This method must be called prior to reading request parametersor reading input using getReader().
            
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            
            PrintWriter writer = response.getWriter();
            
            String fromUser=request.getParameter("fromusr");
            String toUser=request.getParameter("tousr");
            String money=request.getParameter("money");
            
            try {
                new accountService().transfer(fromUser,toUser,money);
            } catch (Exception e) {
                e.printStackTrace();
                writer.println("转账失败");
                return ;
            }
            
            writer.println("转账成功");
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

    }
