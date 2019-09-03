package utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils
    {
        private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
        private static ComboPooledDataSource ds = new ComboPooledDataSource();

        /**
         * 
         * @return
         * @throws Exception
         */
        public static Connection getConnection() throws Exception {
            Connection con = tl.get();
            if (con == null) {
                con = ds.getConnection();
                tl.set(con);
            }

            return con;
        }

        public static void stratTransaction() throws Exception {
            getConnection().setAutoCommit(false);
        }

        public static void closeAndCommit() throws Exception {
            Connection connection = getConnection();
            connection.commit();
            connection.close();
        }

        public static void rollbackAndClose() {
            try {
                Connection con = getConnection();
                con.rollback();
                con.close();
                tl.remove();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
