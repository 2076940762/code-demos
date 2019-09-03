package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static Configuration CONFIG;
	private static SessionFactory FACTORY;

	static {
		CONFIG = new Configuration();
		CONFIG.configure();
		FACTORY = CONFIG.buildSessionFactory();
	}

	public static Session getSession() {
		return FACTORY.openSession();
	}

	public static Session getCurrSession() {
		return FACTORY.getCurrentSession();
	}
}
