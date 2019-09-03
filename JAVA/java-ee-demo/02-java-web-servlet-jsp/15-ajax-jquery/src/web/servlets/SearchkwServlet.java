package web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import doa.SearchKwDoa;
import services.SearchKwService;

public class SearchkwServlet extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("text/html;charset=utf-8");

//            指 定后可以通过request.getParameter()获取自己想要的字符串,如果没有提前指定，则会按照服务器端默认的“iso-8859-1”来 进行编码；
//            该方法只对post请求有效，对get请求无效；对于get请求，应该在server.xml中指定：URIEncoding=utf-8；
//           注意：在执行request.setCharacterEncoding()之前不能执行request.getParameter()方法；
//
//            原因：应 该是在执行第一个getParameter()的时候，java将会按照编码分析所有的提交内容，而后续的getParameter()不再进行分析，所 以setCharacterEncoding()无效。而对于GET方法提交表单是，提交的内容在URL中，一开始就已经按照编码分析提交内 容，setCharacterEncoding()自然就无效。
//            request.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();

            String kword = request.getParameter("keyword");
            List<String> list=null;
            try {
                list = new SearchKwService().search(kword);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            if (list != null && !list.isEmpty()) {
                String res = list.toString();
                res = res.substring(1, res.length() - 1);
                writer.println(res);
            }
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

    }
