package service.impl;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import doa.UserDoa;
import doa.impl.UserDoaImpl;
import domain.User;
import service.UserService;

public class UserServiceImpl implements UserService {

	public void register(User user) throws Exception {
		// 调用doa向数据库中添加用户
		UserDoa doa = new UserDoaImpl();
		doa.add(user);

		// 发送邮件验证码
		sendCodeMail(user.getEmail(), user.getCode());
	}

	/**
	 * 发送验证码
	 * 
	 * @param tomail
	 * @param code
	 * @throws Exception
	 */
	private void sendCodeMail(String tomail, String code) throws Exception {
//		连接服务器的参数
		Properties pro = new Properties();
		pro.setProperty("mail.transport.protocol", "smtp");// 协议
		pro.setProperty("mail.smtp.host", "localhost");// 主机
		pro.setProperty("mail.smtp.auth", "true");// 需要认证

		// 验证器
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("qingtian@mymail.com", "qingtian");
			}
		};

		// 根据配置创建会话对象, 用于和邮件服务器交互
		Session session = Session.getInstance(pro, auth);

		// 创建邮件
		MimeMessage msg = new MimeMessage(session);

		// 发件人地址
		msg.setFrom(new InternetAddress("qingtian@mymail.com"));

		// 收件人
		msg.setRecipients(MimeMessage.RecipientType.TO, tomail);

		// 主题
		msg.setSubject("【激活码】", "UTF-8");

		// 内容
		msg.setContent("<h2>你好请点击此处<a href=\"http://localhost:8080/01-store-1.0/user?method=active&code=" + code
				+ "\">激活</a>账号。", "text/html;charset=utf-8");

		// 设置发送时间
		msg.setSentDate(new Date());

		// 保存
		msg.saveChanges();

		// 获取传输对象
		Transport transport = session.getTransport();

		// 连接自己邮件服务器
		transport.connect("qingtian@mymail.com", "qingtian");

		// msg.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
		transport.sendMessage(msg, msg.getAllRecipients());

		transport.close();
	}

	/**
	 * 激活用户
	 */
	@Override
	public boolean active(String code) throws Exception {
		User usr = null;
		// 查找用户
		UserDoa doa = new UserDoaImpl();
		usr = doa.getUserByCode(code);

		if (usr == null) {
			throw new RuntimeException("用户不存在");
		}

		usr.setState(1);
		usr.setCode(null);

		// 更新用户
		try {
			doa.update(usr);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public User login(String username, String password) throws Exception {
		UserDoa doa = new UserDoaImpl();

		return doa.getUserByUsernameAndPassword(username, password);
	}

}
