package web.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter implements Filter
    {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
        }

        @Override
        public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
                throws IOException, ServletException {
//            类型转换
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;

            HttpServletRequest requestProxy = (HttpServletRequest) Proxy.newProxyInstance(
                    request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {

                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                    对getParameter方法增强处理
                            if ("getParameter".equalsIgnoreCase(method.getName())) {
                                if ("post".equalsIgnoreCase(request.getMethod())) {
//                            post
                                    request.setCharacterEncoding("UTF-8");

                                    return method.invoke(request, args);
                                } else if ("get".equalsIgnoreCase(request.getMethod())) {
//                            get
                                    /*
                                     * String s = (String) method.invoke(request, args); return new
                                     * String(s.getBytes("iso8859-1"), "utf-8");
                                     */
                                    return method.invoke(request, args);
                                }

                            }

                            return method.invoke(request, args);
                        }
                    });

            chain.doFilter(requestProxy, response);
        }

        @Override
        public void destroy() {
        }

    }
