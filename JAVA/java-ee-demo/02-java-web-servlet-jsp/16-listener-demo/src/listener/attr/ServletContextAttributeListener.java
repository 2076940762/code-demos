package listener.attr;

import javax.servlet.ServletContextAttributeEvent;

public class ServletContextAttributeListener implements javax.servlet.ServletContextAttributeListener
    {

        @Override
        public void attributeAdded(ServletContextAttributeEvent scae) {
            System.out.println("name:" + scae.getName() + "; value:" + scae.getValue() + "创建");
        }

        @Override
        public void attributeRemoved(ServletContextAttributeEvent scae) {
            System.out.println("name:" + scae.getName() + "; value:" + scae.getValue() + "删除");
        }

        @Override
        public void attributeReplaced(ServletContextAttributeEvent scae) {
            System.out.println("name:" + scae.getName() + "; value:" + scae.getValue() + "更新");
        }

    }
