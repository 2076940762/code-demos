package doa;

import java.sql.SQLException;

import domain.User;

public interface UserDoa {

	void add(User user) throws Exception;

	User getUserByCode(String code) throws Exception;

	void update(User usr) throws SQLException;

	User getUserByUsernameAndPassword(String username, String password) throws Exception;

}
