package doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AccountDoa
    {
        public int transferIn(Connection connection, String usr, double money) throws Exception {

            String sql = "UPDATE accounts SET money=money+? WHERE `name`=?;";

            PreparedStatement prepareStatement = connection.prepareStatement(sql);

            prepareStatement.setDouble(1, money);
            prepareStatement.setString(2, usr);

            int i = prepareStatement.executeUpdate();
            prepareStatement.close();

            return i;
        }

        public int transferOut(Connection connection, String usr, double money) throws Exception {

            String sql = "UPDATE accounts SET money=money-? WHERE `name`=?;";

            PreparedStatement prepareStatement = connection.prepareStatement(sql);

            prepareStatement.setDouble(1, money);
            prepareStatement.setString(2, usr);

            int i = prepareStatement.executeUpdate();
            prepareStatement.close();
            return i;
        }

    }
