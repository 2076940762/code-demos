package service;

import java.sql.SQLException;

import doa.UserDoa;
import domain.User;

public class UserService
    {

        public User login(String username, String password) throws SQLException {
            return new UserDoa().findUserByNameAndPwd(username, password);
        }

    }
