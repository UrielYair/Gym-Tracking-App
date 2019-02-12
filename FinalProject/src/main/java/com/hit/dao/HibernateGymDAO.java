package com.hit.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.hit.model.Activity;
import com.hit.model.ActivityId;
import com.hit.model.User;

public class HibernateGymDAO implements IGymDAO {
	private static final Logger LOGGER = Logger.getLogger(HibernateGymDAO.class.getSimpleName());

	private static HibernateGymDAO gymDAO;
	private SessionFactory factory;

	// creating factory for getting sessions
	private HibernateGymDAO() {
		LOGGER.info("Creating factory for sessions");
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
	}

	public static HibernateGymDAO getInstance() {
		if (gymDAO == null) {
			LOGGER.info("Going to create new instanse of Hibernate");
			gymDAO = new HibernateGymDAO();
		} else {
			LOGGER.info("Hibernate instance already created");
		}
		return gymDAO;
	}

	@Override
	public boolean activityExist(Activity activity) {
		boolean isExist = false;
		if (getInstance().getActivity(activity.getUserName()) != null) {
			isExist = true;
			LOGGER.info("Activity exist");
		} else {
			LOGGER.info("Activity NOT exist");
		}

		return isExist;
	}

	@Override
	public boolean addActivity(Activity activity) {
		Session session = (Session) factory.openSession();
		boolean wasAdded = false;
		try {
			((org.hibernate.Session) session).beginTransaction();
			if (!getInstance().activityExist(activity)) {
				((org.hibernate.Session) session).save(activity);
				((org.hibernate.Session) session).getTransaction().commit();
				LOGGER.info("Activity was added");
				wasAdded = true;
			} else {
				LOGGER.info("Activity was NOT added");
			}
			session.close();
		} catch (HibernateException hibernateException) {
			LOGGER.fatal("DB error!");
		} finally {
			LOGGER.fatal("Going to close Session");
			session.close();
		}

		return wasAdded;
	}

	// Integer ID changed to String username by Vadim 31.01.19
	@Override
	public boolean deleteActivity(String userName, String activityName) {
		Session session = (Session) factory.openSession();
		Activity activity = null;
		try {
			((org.hibernate.Session) session).beginTransaction();
			activity = (Activity) session.get(Activity.class, new ActivityId(activityName));
			if (activity != null) {
				((org.hibernate.Session) session).delete(activity);
				((org.hibernate.Session) session).getTransaction().commit();
				LOGGER.info("Activity was deleted");
			}
			session.close();
		} catch (HibernateException hibernateException) {
			LOGGER.fatal("DB error!");
		} finally {
			LOGGER.fatal("Going to close Session");
			session.close();
		}
		return true;
	}

	@Override
	public boolean updateActivity(Activity activity) {
		Session session = (Session) factory.openSession();
		boolean wasUpdated = false;
		try {
			((org.hibernate.Session) session).beginTransaction();
			((org.hibernate.Session) session).update(activity);
			((org.hibernate.Session) session).getTransaction().commit();
			LOGGER.info("Activity updated");
			session.close();
		} catch (HibernateException hibernateException) {
			LOGGER.fatal("DB error!");
		} finally {
			LOGGER.fatal("Going to close Session");
			session.close();
		}
		return wasUpdated;
	}

	@Override
	public Activity getActivity(String activityName) {
		Session session = (Session) factory.openSession();
		Activity activity = null;
		try {
			((org.hibernate.Session) session).beginTransaction();

			// Activity activity = session.createQuery("from activities").;
			activity = (Activity) session.get(Activity.class, new ActivityId(activityName));
			LOGGER.info("Activity found");
			session.close();
		} catch (HibernateException hibernateException) {
			LOGGER.fatal("DB error!");
		} finally {
			LOGGER.fatal("Going to close Session");
			session.close();
		}
		return activity;
	}

	@Override
	public boolean userExist(User user) {
		boolean isExist = false;
		if (getInstance().getUser(user.getName()) != null) {
			isExist = true;
			LOGGER.info("User exist");
		} else {
			LOGGER.info("User NOT exist");
		}

		return isExist;
	}

	@Override
	public boolean addUser(User user) {
		Session session = (Session) factory.openSession();
		boolean wasAdded = false;
		try {
			session.beginTransaction();
			if (!getInstance().userExist(user)) {
				session.save(user);
				session.getTransaction().commit();
				wasAdded = true;
				LOGGER.info("User was added");
			}
		} catch (HibernateException hibernateException) {
			LOGGER.fatal("DB error!");
			LOGGER.fatal(hibernateException.getMessage());
		} catch (Exception exception) {
			LOGGER.fatal("Error!, unknown exception");
			LOGGER.fatal(exception.getMessage());
		} finally {
			LOGGER.info("Closing Session");
			session.close();
		}
		return wasAdded;
	}

	@Override
	public User getUser(String userName) {
		Session session = (Session) factory.openSession();
		User user = new User();
		try {
			session.beginTransaction();
			user = (User) session.get(User.class, 8);
			if (user != null) {
				LOGGER.info("User was found");
			} else {
				LOGGER.info("User was NOT found");
			}
		} catch (HibernateException hibernateException) {
			LOGGER.fatal("DB error!");
			LOGGER.fatal(hibernateException.getMessage());
		} catch (Exception exception) {
			LOGGER.fatal("Error!, unknown exception");
			LOGGER.fatal(exception.getMessage());
		} finally {
			LOGGER.info("Closing Session");
			session.close();
		}
		return user;
	}

	@Override
	public boolean deleteUser(String userName) {
		Session session = (Session) factory.openSession();
		User user = null;
		boolean wasDeleted = false;
		try {
			((org.hibernate.Session) session).beginTransaction();
			user = (User) session.get(User.class, userName);
			if (user != null) {
				LOGGER.info("User was found, preparing to delete");
				((org.hibernate.Session) session).delete(user);
				((org.hibernate.Session) session).getTransaction().commit();
				wasDeleted = true;
			} else {
				LOGGER.info("User was NOT found");
			}
		} catch (HibernateException hibernateException) {
			LOGGER.fatal("DB Error!");
		} finally {
			LOGGER.info("Closing Session");
			session.close();
		}
		return wasDeleted;
	}

}
