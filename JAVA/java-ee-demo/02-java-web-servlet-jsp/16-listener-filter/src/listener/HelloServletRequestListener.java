package listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class HelloServletRequestListener implements ServletRequestListener
    {

        @Override
        public void requestDestroyed(ServletRequestEvent sre) {
            System.out.println("requet 已销毁");
        }

        @Override
        public void requestInitialized(ServletRequestEvent sre) {
            System.out.println("requet 已创建");
        }

    }
