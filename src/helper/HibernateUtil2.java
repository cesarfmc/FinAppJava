package helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil2 {
	private static SessionFactory factory = null;
	private static Configuration conf;

	private static SessionFactory buildSessionFactory() {
		try {
			conf = new Configuration();
			conf.configure("hibernate.cfg.xml");

			System.out.println("configurou");

			factory = conf.buildSessionFactory();

			System.out.println("construiu");

			return factory;
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {

		if (factory == null) {
			factory = buildSessionFactory();
		}
		return factory;
	}
}
