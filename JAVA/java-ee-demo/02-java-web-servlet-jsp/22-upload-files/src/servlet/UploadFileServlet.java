package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig
public class UploadFileServlet extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");

            String username = request.getParameter("username");
            String filename = request.getParameter("filename");

            System.out.println("username:" + username);// username:张三
            System.out.println("filename:" + filename);// filename:null

            Part part = request.getPart("filename");
            System.out.println("part:" + part);// part:org.apache.catalina.core.ApplicationPart@635145ef

//            * Obtain the name of the field in the multipart form corresponding to this  part.
            System.out.println(part.getName());// filename

            Collection<String> names = part.getHeaderNames();
            System.out.println(names);// [content-disposition, content-type]

            String header = part.getHeader("content-disposition");
            System.out.println(header);// form-data; name="filename"; filename="下载.jpg"

            String fName = header.substring(header.lastIndexOf("=") + 2, header.length() - 1);
            System.out.println(fName); // 下载.jpg

            InputStream in = part.getInputStream();
            FileOutputStream out = new FileOutputStream("f:/" + Math.random()+fName);
            byte[] buffer = new byte[1024];
            int len = 0;

            while ((len = in.read(buffer,0,1024)) != -1) {
                out.write(buffer, 0, len);
            }

            out.close();
            in.close();
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            // TODO Auto-generated method stub
            doGet(request, response);
        }

    }
