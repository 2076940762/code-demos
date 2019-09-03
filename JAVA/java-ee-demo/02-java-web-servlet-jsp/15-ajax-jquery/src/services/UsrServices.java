package services;

import java.sql.SQLException;

import doa.UserDoa;
import domain.User;

public class UsrServices
    {

    public User getUsrByName(String username) throws SQLException {
        return new UserDoa().getUsrByName( username);
    }



    }
