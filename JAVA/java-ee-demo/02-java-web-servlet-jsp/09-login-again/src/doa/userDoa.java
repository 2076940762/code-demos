package doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.User;

public class userDoa
    {

        public User findUserByNameAndPasswd(String username, String passwd) throws Exception {
            // 注册数据库驱动程序
            Class.forName("com.mysql.jdbc.Driver");

            // 获取数据库连接
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");

            String sql = "SELECT username , `password` FROM usrs;";
            PreparedStatement prepareStatement = connection.prepareStatement(sql);

            ResultSet result = prepareStatement.executeQuery();

            User usr = new User();

            while (result.next()) {
                usr.setName(result.getString(1));
                usr.setPasswd(result.getString(2));
            }

            result.close();
            prepareStatement.close();
            connection.close();
            return usr;

        }

    }
