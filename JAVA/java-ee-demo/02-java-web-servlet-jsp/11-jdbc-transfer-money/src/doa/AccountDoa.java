package doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AccountDoa
    {
        public int transferIn(String usr, double money) throws Exception {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");

            String sql = "UPDATE accounts SET money=money+? WHERE `name`=?;";

            PreparedStatement prepareStatement = connection.prepareStatement(sql);

            prepareStatement.setDouble(1, money);
            prepareStatement.setString(2, usr);

            return prepareStatement.executeUpdate();

        }

        public int transferOut(String usr, double money) throws Exception {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");

            String sql = "UPDATE accounts SET money=money-? WHERE `name`=?;";

            PreparedStatement prepareStatement = connection.prepareStatement(sql);

            prepareStatement.setDouble(1, money);
            prepareStatement.setString(2, usr);

            return prepareStatement.executeUpdate();
        }

    }
