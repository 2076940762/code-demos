package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static Configuration CONFIG;
	private static SessionFactory FACTORY;
	private static ThreadLocal<Session> LOCAL = new ThreadLocal<Session>() ;

	static {
		CONFIG = new Configuration();
		CONFIG.configure();

		FACTORY = CONFIG.buildSessionFactory();
	}

	/**
	 * 获取session
	 * 
	 * @return
	 */
	public static Session getSession() {
		return FACTORY.openSession();
	}

	public static Session getCurrSession() {
		Session session = LOCAL.get();
		if (session == null) {
			session = FACTORY.openSession();
			LOCAL.set(session);
		}
		return session;
	}

	public static void main(String[] args) {
		getCurrSession();
		
	}
}
