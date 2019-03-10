package com.hit.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.hit.exceptions.DBException;
import com.hit.model.Activity;
import com.hit.model.User;

public class HibernateGymDAO implements IGymDAO {
	private static final Logger LOGGER = Logger.getLogger(HibernateGymDAO.class.getSimpleName());

	private static HibernateGymDAO gymDAO;
	private SessionFactory factory;

	private HibernateGymDAO() {
		/**
		 * private constructor for HiberateGymDAO - Singleton based.
		 */
		
		LOGGER.info("Creating factory for sessions"); 
		// Session Factory creation for later operation.
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
	}

	public static HibernateGymDAO getInstance() {
		if (gymDAO == null) {
			gymDAO = new HibernateGymDAO();
			LOGGER.info("HibernateGymDAO instance created.");
			return gymDAO;
		} else {
			LOGGER.info("Hibernate instance already created"); 
																
			return gymDAO;
		}

	}

	public SessionFactory getFactory() {
		return factory;
	}

	@Override
	public boolean activityExist(Activity activity) {
		boolean isExist = false;

		try {
			getInstance().getActivity(activity.getUserName(), activity.getExerciseName(), activity.getWorkoutDate());
			isExist = true;
			LOGGER.info("Activity exist");
		} catch (DBException e) {
			LOGGER.info("Activity NOT exist");
		} finally {
			return isExist;
		}
	}

	@Override
	public boolean addActivity(Activity activity) {
		Session session = (Session) factory.openSession();
		boolean wasAdded = false;

		try {

			if (!getInstance().activityExist(activity)) {

				((org.hibernate.Session) session).beginTransaction();
				((org.hibernate.Session) session).save(activity);
				((org.hibernate.Session) session).getTransaction().commit();
				LOGGER.info("Activity was added");
				wasAdded = true;
			} else {
				LOGGER.info("Activity was NOT added");
			}

		} catch (HibernateException hibernateException) {
			LOGGER.fatal("ERROR: DB error while adding activity!");
		}

		finally {
			session.close();
			return wasAdded;
		}

	}

	@Override
	public boolean deleteActivity(String userName, String exerciseName, String workoutDate) {

		Session session = (Session) factory.openSession();
		Activity activity = null;
		boolean wasDeleted = false;

		try {
			activity = getActivity(userName, exerciseName, workoutDate);

			((org.hibernate.Session) session).beginTransaction();
			((org.hibernate.Session) session).delete(activity);
			((org.hibernate.Session) session).getTransaction().commit();
			LOGGER.info("Activity was deleted");

			wasDeleted = true;

		} catch (HibernateException hibernateException) {
			LOGGER.fatal("ERROR: DB error while activity deletion command.");
		} catch (DBException e) {
			e.printStackTrace();
		}

		finally {
			session.close();
			return wasDeleted;
		}
	}

	@Override
	public boolean updateActivity(Activity activity) {
		Session session = (Session) factory.openSession();
		boolean wasUpdated = false;

		try {
			((org.hibernate.Session) session).beginTransaction();
			((org.hibernate.Session) session).update(activity);
			((org.hibernate.Session) session).getTransaction().commit();
			LOGGER.info("Activity updated: " + activity.toString());
			wasUpdated = true;
		} catch (HibernateException hibernateException) {
			LOGGER.fatal("DB error while activity update!");
		}

		finally {
			session.close();
		}

		return wasUpdated;
	}

	@Override
	public Activity getActivity(String userName, String exerciseName, String workoutDate) throws DBException {
		Session session = (Session) factory.openSession();
		Activity activity = null;

		try {
			((org.hibernate.Session) session).beginTransaction();

			Query query = session
					.createQuery("from Activity where userName = :un and exerciseName = :en and workoutDate = :d ");
			query.setParameter("un", userName);
			query.setParameter("en", exerciseName);
			query.setParameter("d", workoutDate);
			activity = (Activity) query.uniqueResult();

			if (activity != null) {
				LOGGER.info("Activity was found");
			} else {
				LOGGER.info("Activity was NOT found");
				throw new DBException("Activity was NOT found");
			}

		} catch (HibernateException hibernateException) {
			LOGGER.fatal("DB error while activity lookup!");
		} finally {
			session.close();
		}

		return activity;
	}

	@Override
	public boolean userExist(User user) {

		return (getInstance().getUser(user.getName()) != null);

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
		}

		catch (HibernateException hibernateException) {
			LOGGER.fatal("ERROR: DB error while adding user! " + hibernateException.getMessage());
		} catch (Exception exception) {
			LOGGER.fatal("ERROR: " + exception.getMessage());
		}

		finally {
			session.close();
		}

		return wasAdded;
	}

	@Override
	public User getUser(String userName) {
		Session session = (Session) factory.openSession();
		User user = null;

		try {
			session.beginTransaction();

			Query query = session.createQuery("from User where username = :n");
			query.setParameter("n", userName);
			user = (User) query.uniqueResult();

			if (user != null) {
				LOGGER.info("User was found");
			} else {
				LOGGER.info("User was NOT found");
			}
		}

		catch (HibernateException hibernateException) {
			LOGGER.fatal("ERROR: DB error while finding user! " + hibernateException.getMessage());
		} catch (Exception exception) {
			LOGGER.fatal("ERROR: " + exception.getMessage());
		}

		finally {
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
			user = getUser(userName);

			if (user != null) {
				((org.hibernate.Session) session).beginTransaction();
				((org.hibernate.Session) session).delete(user);
				((org.hibernate.Session) session).getTransaction().commit();
				LOGGER.info("User: " + userName + " deleted");
				wasDeleted = true;
			} else {
				LOGGER.info("User " + userName + " was NOT found, Therfore not deleted.");
			}
		} catch (HibernateException hibernateException) {
			LOGGER.fatal("DB Error!");
		}

		finally {
			session.close();
		}

		return wasDeleted;
	}

	@Override
	public List<Activity> getAllUserActivities(String userName) {

		List<Activity> list = null;

		Session session = (Session) factory.openSession();

		try {

			((org.hibernate.Session) session).beginTransaction();

			Query query = session.createQuery("from Activity where userName = :un ");
			query.setParameter("un", userName);
			list = (List<Activity>) query.list();

			if (list.isEmpty()) {
				LOGGER.info("Activities were not founded");
			} else {
				LOGGER.info("Activities were founded");
			}

		} catch (HibernateException hibernateException) {
			LOGGER.fatal("DB error while activities lookup!");
		} catch (Exception exception) {
			LOGGER.fatal("ERROR: " + exception.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Activity> getAllActivities() {// return activities of all users

		List<Activity> list = null;

		Session session = (Session) factory.openSession();

		try {

			((org.hibernate.Session) session).beginTransaction();

			Query query = session.createQuery("from Activity");
			list = (List<Activity>) query.list();

			if (list.isEmpty()) {
				LOGGER.info("Activities were not founded");
			} else {
				LOGGER.info("Activities were founded");
			}

		} catch (HibernateException hibernateException) {
			LOGGER.fatal("DB error while activities lookup!");
		} catch (Exception exception) {
			LOGGER.fatal("ERROR: " + exception.getMessage());
		} finally {
			session.close();
		}
		return list;
	}
}
