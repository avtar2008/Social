package main.services.db.Util;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import main.services.db.SessionInit;
import resources.db.Profile;

public class ProfileUtils {

	final static Logger logger = Logger.getLogger(ProfileUtils.class);

	public static Boolean SaveProfile(Profile profile) {
		Boolean flag = false;
		Session session = SessionInit.getSession();
		Transaction transaction = session.beginTransaction();

		try {
			session.save(profile);
			transaction.commit();
			flag = true;
		} catch (Exception e) {
			transaction.rollback();
			flag = false;
		}

		session.close();
		return flag;
	}

	public static int SaveProfileList(List<Profile> profiles) {
		int response = 0;
		Session session = SessionInit.getSession();
		Transaction transaction = session.beginTransaction();

		for (Profile profile : profiles) {
			try {
				session.save(profile);
			} catch (HibernateException he) {
				response = profile.getId();
				transaction.rollback();
				break;
			}
		}
		if (response == 0) {
			transaction.commit();
		}
		session.close();

		return response;
	}

	public static Profile GetProfileById(int id) {
		Session session = SessionInit.getNewSession();
		session.beginTransaction();
		Profile profile = null;

		try {
			profile = (Profile) session.get(Profile.class, id);
		} catch (HibernateException he) {
			logger.debug("Cannot get profile for id = " + id);
		}
		session.getTransaction().commit();
		session.close();
		return profile;
	}

	public static Profile GetProfileByName(String name) {
		Session session = SessionInit.getNewSession();
		session.beginTransaction();
		Profile profile = null;

		try {
			Criteria criteria = session.createCriteria(Profile.class);
			criteria.add(Restrictions.eq("name", name));

			profile = (Profile) criteria.list().get(0);

		} catch (HibernateException he) {
			logger.debug("Cannot get profile for name = " + name);
		} catch (Exception e) {
			logger.debug("General exception occured in fetching profile");
		}

		return profile;
	}

	public static void deleteProfile(String input) {
		logger.info("Start of deleteProfile");
		Session session = SessionInit.getSession();
		session.beginTransaction();
		Profile profile = new Profile();
		try {

			if (Pattern.matches("[0-9]*", input)) {
				profile.setId(Integer.valueOf(input));
//				logger.debug("calling delete");
				session.delete(profile);
			} else {
//				logger.debug("calling get");
				profile = (Profile) session
						.createCriteria(Profile.class).add(Restrictions.eq("name", input))
						.list().get(0);
//				logger.debug(profile.getName());
//				logger.debug("calling delete");
				session.delete(profile);
			}
		} catch (HibernateException he) {
			logger.info("Can't delete profile = " + input);
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		session.close();
		logger.info("End of deleteProfile");
	}
	
	public static void updateProfile(Profile profile){
		Session session = SessionInit.getSession();
		session.beginTransaction();
		
		session.update(profile);
	}
	
}
