package main.services.db.Util;

import main.services.db.SessionInit;

import org.hibernate.Session;
import org.hibernate.Transaction;

import resources.db.LoginDetails;

public class LoginUtil {

	public static boolean saveLoginDetails(LoginDetails details){
		
		Session session = SessionInit.getSession();
		Transaction transaction = session.beginTransaction();
		boolean flag = false;
		
		try {
			session.save(details);
			transaction.commit();
			flag  = true;
		} catch (Exception e) {
			transaction.rollback();
			flag = false;
		}
		session.close();
		return flag;
	}
	
	
}
