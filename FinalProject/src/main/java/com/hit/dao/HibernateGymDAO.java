package com.hit.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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

	private HibernateGymDAO() {
		/**
		 * private constructor for HiberateGymDAO - Singleton based.
		 * */
		//
		LOGGER.info("Creating factory for sessions"); // TODO: delete.
		// Session Factory creation for later operation.
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
	}

	public static HibernateGymDAO getInstance() {
		if (gymDAO == null) {
			gymDAO = new HibernateGymDAO();
			LOGGER.info("HibernateGymDAO instance created.");
			return gymDAO;
		} else { // TODO: delete the whole else statement.
			LOGGER.info("Hibernate instance already created"); // why should we care in every call to get instance to know if we already have an instance.
			return gymDAO;
		}
		
	}

	@Override
	public boolean activityExist(Activity activity) {
		// boolean isExist = false;
		
		return (getInstance().getActivity(activity.getUserName(), activity.getExerciseName(), activity.getWorkoutDate()) != null);
		
		// TODO: why should we log this results?
		
		/* 
		
		if (getInstance().getActivity(activity.getUserName()) != null) {
			isExist = true;
			LOGGER.info("Activity exist");
		} 
		else {
			LOGGER.info("Activity NOT exist");
		}

		return isExist;
		
		*/
	}

	@Override
	public boolean addActivity(Activity activity) {
		Session session = (Session) factory.openSession();
		boolean hasAdded = false;
		
		try {
						
			if (!getInstance().activityExist(activity)) {
				
				((org.hibernate.Session) session).beginTransaction();
				((org.hibernate.Session) session).save(activity);
				((org.hibernate.Session) session).getTransaction().commit();
				LOGGER.info("Activity was added"); // TODO: make it more descriptive.
				hasAdded = true;
			} 
			else {
				LOGGER.info("Activity was NOT added"); // TODO: check if is it an activity or certain exercise.
			}

		} 
		catch (HibernateException hibernateException) {
			LOGGER.fatal("ERROR: DB error while adding activity!");
		} 
		
		finally {
			session.close();
		}

		return hasAdded;
	}



	@Override
	public boolean deleteActivity(String userName, String activityName) {
		
		Session session = (Session) factory.openSession();
		Activity activity = null;
		boolean deletionResult = false;
		
		try {
			((org.hibernate.Session) session).beginTransaction();
			activity = (Activity) session.get(Activity.class, new ActivityId(activityName)); // TODO: is activityName is enough to differentiate between activities.
			// ^^ maybe get function should get more inputs.
			
			if (activity != null) {
				((org.hibernate.Session) session).delete(activity);
				((org.hibernate.Session) session).getTransaction().commit();
				LOGGER.info("Activity was deleted"); // TODO: make it more descriptive. which activity was deleted exactly?
			}
			deletionResult = true;
			
		} 
		catch (HibernateException hibernateException) {
			LOGGER.fatal("ERROR: DB error while activity deletion command.");
		} 
		
		finally {
			session.close();
		}
		
		return deletionResult;
	}

	@Override
	public boolean updateActivity(Activity activity) {
		Session session = (Session) factory.openSession();
		boolean wasUpdated = false;
		
		try {
			((org.hibernate.Session) session).beginTransaction();
			((org.hibernate.Session) session).update(activity);
			((org.hibernate.Session) session).getTransaction().commit();
			LOGGER.info("Activity updated"); // TODO: Make it descriptive. for instance: which activity was updated.
		} 
		catch (HibernateException hibernateException) {
			LOGGER.fatal("DB error while activity update!");
		} 
		
		finally {
			session.close();
		}
		
		return wasUpdated;
	}

	@Override
	public Activity getActivity(String userName, String exerciseName, String workoutDate) {
		Session session = (Session) factory.openSession();
		Activity activity = null;
		
		try {
			((org.hibernate.Session) session).beginTransaction();
			//activity = (Activity) session.get(Activity.class, new ActivityId(activityName)); // TODO: check if activityName is enough to differentiate between activities.
		
			Query query = session.createQuery("from Activity where userName = :un and exerciseName = :en and workoutDate = :d ");
	        query.setParameter("un", userName);
	        query.setParameter("en", exerciseName);
	        query.setParameter("d", workoutDate);
	        activity = (Activity) query.uniqueResult();
	        
			if(activity != null)
			{
				LOGGER.info("Activity was found");
			} 
			else {
				LOGGER.info("Activity was NOT found");
			}
	        
		} 
		catch (HibernateException hibernateException) {
			LOGGER.fatal("DB error while activity lookup!");
		} 
		catch (Exception exception) {
			LOGGER.fatal("ERROR: " + exception.getMessage());
		}
		finally {
			session.close();
		}
		
		return activity;
	}

	@Override
	public boolean userExist(User user) {
		
		return (getInstance().getUser(user.getName()) != null);
		
		/*
		TODO: delete the whole comment! the logging should be in the function which called the userExist().
		
		boolean isExist = false;
		if (getInstance().getUser(user.getName()) != null) {
			isExist = true;
			LOGGER.info("User exist");
		} else {
			LOGGER.info("User NOT exist");
		}

		return isExist;
		*/
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
				LOGGER.info("User was added"); // TODO: make descriptive.
			}
		} 
		
		catch (HibernateException hibernateException) {
			LOGGER.fatal("ERROR: DB error while adding user! " + hibernateException.getMessage());
		} 
		catch (Exception exception) {
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
			
			// TODO: consider deleting this logging operations. 
			if (user != null) {
				LOGGER.info("User was found");
			} 
			else {
				LOGGER.info("User was NOT found");
			}
		} 

		catch (HibernateException hibernateException) {
			LOGGER.fatal("ERROR: DB error while finding user! " + hibernateException.getMessage());
		} 
		catch (Exception exception) {
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
			user = (User) session.get(User.class, userName);
			
			if (user != null) {
				((org.hibernate.Session) session).beginTransaction();
				((org.hibernate.Session) session).delete(user);
				((org.hibernate.Session) session).getTransaction().commit();
				LOGGER.info("User: " + userName + " deleted");
				wasDeleted = true;
			} 
			else {
				LOGGER.info("User " + userName + " was NOT found, Therfore not deleted.");
			}
		} 
		catch (HibernateException hibernateException) {
			LOGGER.fatal("DB Error!");
		} 
		
		finally {
			session.close();
		}
		
		return wasDeleted;
	}

}
