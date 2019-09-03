package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HelloServletContextListener implements ServletContextListener
    {

        @Override
        public void contextInitialized(ServletContextEvent sce) {
            System.out.println("servletcontext 创建成功");
        }

        @Override
        public void contextDestroyed(ServletContextEvent sce) {
            System.out.println("servletcontext 销毁");
        }

    }
