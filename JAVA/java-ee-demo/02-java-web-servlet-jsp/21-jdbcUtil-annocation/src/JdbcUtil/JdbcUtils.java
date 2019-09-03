package JdbcUtil;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtils
    {
        @JdbcInfo(url = "jdbc:mysql://localhost:3306/webstore")
        public static Connection getConnection() throws Exception {
//            获取字节码文件
            Class clazz = JdbcUtils.class;

//            获取getConnection方法
            Method method = clazz.getMethod("getConnection");
            if (method == null) {
                throw new RuntimeException("method == null");
            }

            if (method.isAnnotationPresent(JdbcInfo.class)) {
//              获取注解
                JdbcInfo annotation = method.getAnnotation(JdbcInfo.class);

                String driver = annotation.driverClass();
                String password = annotation.password();
                String username = annotation.username();
                String url = annotation.url();

                Class.forName(driver);
                return DriverManager.getConnection(url, username, password);
            }
            return null;
        }

        public static void main(String[] args) throws Exception {
            
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
        }
        
    }
