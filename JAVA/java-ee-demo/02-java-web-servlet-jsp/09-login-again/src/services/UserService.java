package services;

import doa.userDoa;
import domain.User;

public class UserService
    {

        public User findUserByNameAndPasswd(String username, String passwd) throws Exception {

            return new userDoa().findUserByNameAndPasswd(username, passwd);
        }

    }
