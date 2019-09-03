package filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter
    {

        @Override
        public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
                throws IOException, ServletException {
//            类型转换
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;
            
            chain.doFilter(new MyRequest(request), response);

        }

    }

/**
 * 
 * @author qingtian
 *
 */
class MyRequest extends HttpServletRequestWrapper
    {
        private boolean flag = true;

        @Override
        public String getParameter(String name) {
            if (name == null || name.trim().isEmpty()) {
                return null;
            }

            String[] arr = getParameterValues(name);
            if (arr == null || arr.length == 0) {
                return null;
            }
            return arr[0];
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            String method = request.getMethod();

            if ("post".equals(method)) {
//                post请求
                try {
                    request.setCharacterEncoding("ut-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            } else if ("get".equals(method)) {
//                get请求
                Map<String, String[]> map = request.getParameterMap();
                if (flag) {
                    for (String key : map.keySet()) {
                        String[] arr = map.get(key);
                        for (String s : arr) {
                            try {
                                s = new String(s.getBytes("iso-8859-1"), "utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                flag = false;
                return map;
            }
            return request.getParameterMap();
        }

        @Override
        public String[] getParameterValues(String name) {
            if (name == null || name.trim().isEmpty()) {
                return null;
            }

            Map<String, String[]> map = getParameterMap();
            if (map == null || map.isEmpty()) {
                return null;
            }

            return map.get(name);
        }

        private HttpServletRequest request;

        public MyRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }

    }