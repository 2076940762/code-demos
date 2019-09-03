package service;

import domain.User;

public interface UserService {
	//完成用户注册
	public void register(User user) throws Exception;

	public boolean active(String code) throws Exception;

	public User login(String username, String password) throws Exception;
}
