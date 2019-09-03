package server;

public class UserService {

	public int regist(User usr) throws Exception {
		
		UserDao dao=new UserDao();
		return dao.addUser(usr);
	}
	

}
