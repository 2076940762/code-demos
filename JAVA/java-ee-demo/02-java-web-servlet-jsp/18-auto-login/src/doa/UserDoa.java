package doa;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import domain.User;

public class UserDoa
    {

        public User findUserByNameAndPwd(String username, String password) throws SQLException {
            QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
            String sql = "SELECT * FROM `user` WHERE username=? AND `password`=? LIMIT 1;";
            return qr.query(sql, new BeanHandler<User>(User.class), username, password);
        }

    }
