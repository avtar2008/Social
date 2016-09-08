package main.services.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionInit {
	
	private static SessionFactory factory;
	
	/**
	 * private Constructor.
	 */
	private SessionInit(){
		
	}
	
	static {
		factory = new Configuration().configure().buildSessionFactory();
	}
	
	public static Session getNewSession(){
		return factory.openSession();
	}
	
	public static Session getCurrentSession(){
		return factory.getCurrentSession();	
	}
	
	public static Session getSession(){
		Session session = null;
		try {
			session = SessionInit.getCurrentSession();
		} catch (HibernateException he) {
			session = SessionInit.getNewSession();
		}
		return session;
	}
}
