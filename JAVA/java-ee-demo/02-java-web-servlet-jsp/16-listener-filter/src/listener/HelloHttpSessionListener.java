package listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class HelloHttpSessionListener implements HttpSessionListener
    {

        @Override
        public void sessionCreated(HttpSessionEvent se) {
            System.out.println("session 已经创建");
        }

        @Override
        public void sessionDestroyed(HttpSessionEvent se) {
            System.out.println("session 已经销毁");
        }

    }
