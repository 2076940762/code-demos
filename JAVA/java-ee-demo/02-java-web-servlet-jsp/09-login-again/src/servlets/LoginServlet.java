package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import services.UserService;

public class LoginServlet extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            // 设置编码
//            * Overrides the name of the character encoding used in the body of this
//            * request. This method must be called prior to reading request parameters
//            * or reading input using getReader().
            request.setCharacterEncoding("utf-8");

            // 获取验证码
            String userCheckcode = request.getParameter("checkcode");
            String serverCheckcode = (String) request.getSession().getAttribute("checkcode");

            // 验证码为空
            if (userCheckcode == null || userCheckcode.trim().equals("") || serverCheckcode == null
                    || serverCheckcode.trim().equals("")) {
                request.setAttribute("msg", "验证码不能为空");

//                Defines an object that receives requests from the client and sends them to any resource (such as a servlet, HTML file, or JSP file) on the server. 
//                The servlet container creates the RequestDispatcher object, which isused as a wrapper around a server resource located at a particular path 
//                or given by a particular name. 
//                This interface is intended to wrap servlets, but a servlet container can create RequestDispatcher objects to wrap any type of resource.
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }

            // 验证码输入错误
            if (!userCheckcode.equals(serverCheckcode)) {
                request.setAttribute("msg", "验证码输入错误，请核对后重新输入！");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }

            // 获取用户名和密码
            String username = request.getParameter("username");
            String passwd = request.getParameter("password");

            if (username == null || username.trim().equals("") || passwd == null || passwd.trim().equals("")) {
                request.setAttribute("msg", "用户名或密码不能为空！");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }

            User usr = null;
            try {
                usr = new UserService().findUserByNameAndPasswd(username, passwd);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            if (usr == null) {
                request.setAttribute("msg", "用户不存在");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
            
            //记住用户名
            String isSavename=request.getParameter("savename");
            if (isSavename!=null && isSavename.equals("yes")) {
                Cookie c=new Cookie("savedname", username);
                c.setPath(request.getContextPath()+"/");
                c.setMaxAge(3600);
                
                response.addCookie(c);
            }
            
            //将user对象存到session中
            request.getSession().setAttribute("user", usr); 
            
            response.sendRedirect(request.getContextPath()+"/index.jsp");
            
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

    }
