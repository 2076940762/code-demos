package serices;

import java.sql.Connection;
import java.sql.DriverManager;

import doa.AccountDoa;

public class Accountservice
    {

        public boolean transfer(String fromusr, String tousr, String money) throws NumberFormatException, Exception {
            AccountDoa doa = new AccountDoa();

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
            connection.setAutoCommit(false);

            try {
                // 转出
                int i = doa.transferOut(connection, fromusr, Double.parseDouble(money));
                
//                int k=1/0;

                // 转入
                int j = doa.transferIn(connection, tousr, Double.parseDouble(money));
                
                if (i != j) {
                    connection.close();
                    return false;
                }
                
                //提交事务
                connection.commit();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
                
                //回滚
                connection.rollback();
                connection.close();

                throw e;
            }

            return true;
        }

    }
