package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloFilter implements Filter
    {

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {
            System.out.println("filter 收到请求");
            
            chain.doFilter(request, response);
            
            System.out.println("filter 响应已经生成");

        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            System.out.println("init~~~~~~~~~~");
        }

        @Override
        public void destroy() {
            System.out.println("destory~~~~~");
        }

    }
