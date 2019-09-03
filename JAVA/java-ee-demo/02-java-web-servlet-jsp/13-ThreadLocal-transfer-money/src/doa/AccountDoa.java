package doa;

import java.sql.Connection;
import java.sql.PreparedStatement;

import utils.DataSourceUtils;

public class AccountDoa
    {

        public void transferOut(String fromUser, String money) throws Exception {

            Connection connection = DataSourceUtils.getConnection();

            String sql = "UPDATE accounts SET money=money-? WHERE `name`= ? ;";

            PreparedStatement prepareStatement = connection.prepareStatement(sql);

            prepareStatement.setDouble(1, Double.parseDouble(money));
            prepareStatement.setString(2, fromUser);

            prepareStatement.executeUpdate();
        }

        public void transferIn(String toUser, String money) throws Exception {
            Connection connection = DataSourceUtils.getConnection();

            String sql = "UPDATE accounts SET money=money+? WHERE `name`= ? ;";

            PreparedStatement prepareStatement = connection.prepareStatement(sql);

            prepareStatement.setDouble(1, Double.parseDouble(money));
            prepareStatement.setString(2, toUser);

            prepareStatement.executeUpdate();
        }

    }
