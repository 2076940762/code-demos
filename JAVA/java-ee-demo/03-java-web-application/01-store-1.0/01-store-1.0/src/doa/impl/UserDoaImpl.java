package doa.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import doa.UserDoa;
import domain.User;

public class UserDoaImpl implements UserDoa {

	@Override
	public void add(User user) throws Exception {
		QueryRunner qr = new QueryRunner(new ComboPooledDataSource());

		String sql = "INSERT INTO USER (uid,username,`password`,`name`,                 email,telephone,birthday,         sex, state,`code`) "
				+ "VALUES(?,?,?,?,        ?,?,?,?   ,?,?);";
		qr.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
				user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode());

	}

	@Override
	public User getUserByCode(String code) throws Exception {
		QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
		String sql = "SELECT * FROM USER WHERE `code` =	?  LIMIT 1	";
		return qr.query(sql, new BeanHandler<User>(User.class), code);
	}

	/**
	 * 更新用户数据
	 * 
	 * @throws SQLException
	 */
	// uid, username , password , name , email ,telephone , birthday,sex,state,code
	@Override
	public void update(User usr) throws SQLException {
		QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
		/*
		 * String sql = "UPDATE USER SET username=? , `password`=? , `name`=?  ," +
		 * "  email=?   , telephone=? ," + "sex=?,state=?,`code`=? WHERE uid =?  ; ";
		 * qr.update(sql, usr.getUsername(), usr.getPassword(), usr.getName(),
		 * usr.getEmail(), usr.getTelephone(),
		 * usr.getSex(),usr.getState(),usr.getCode(),usr.getUid());
		 */

		String sql = "UPDATE USER SET username=? , `password`=? , `name`=?  ,"
				+ "  email=?   ,telephone=? , birthday=?," + "sex=?,state=?,`code`=? WHERE uid =? ;";
		qr.update(sql, usr.getUsername(), usr.getPassword(), usr.getName(), usr.getEmail(), usr.getTelephone(),
				usr.getBirthday(), usr.getSex(), usr.getState(), usr.getCode(), usr.getUid());

	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) throws Exception {
		QueryRunner qr =new QueryRunner(new ComboPooledDataSource());
		String sql="SELECT * FROM USER WHERE username=? AND `password`=? ; ";

		return  qr.query(sql, new BeanHandler<User>(User.class),username,password);
	}

}
