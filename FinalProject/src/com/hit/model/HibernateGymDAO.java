package com.hit.model;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateGymDAO implements IGymDAO {

	private static HibernateGymDAO gymDAO;
	private SessionFactory factory;

	private HibernateGymDAO() {
		// creating factory for getting sessions
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
	}

	public static HibernateGymDAO getInstance() {
		if (gymDAO == null) {
			gymDAO = new HibernateGymDAO();
		}
		return gymDAO;
	}

	@Override
	public boolean activityExist(Activity activity) throws ActivityDBException {
		if(this.gymDAO.getActivity(activity.getId().getId(), activity.getId().getName()) == null)
		{
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean addActivity(Activity activity) throws ActivityDBException {

		// creating a new session for adding products
		Session session = (Session) factory.openSession();
		((org.hibernate.Session) session).beginTransaction();

		if(this.gymDAO.activityExist(activity))
		{
			return false;
		}
		
		((org.hibernate.Session) session).save(activity);
		((org.hibernate.Session) session).getTransaction().commit();

		session.close();

		return true;
	}

	@Override
	public boolean deleteActivity(Integer id, String name) throws ActivityDBException {

		// creating a new session for adding products
		Session session = (Session) factory.openSession();
		((org.hibernate.Session) session).beginTransaction();

		Activity activity = (Activity) session.get(Activity.class, new ActivityId(id, name));

		if (activity != null) {
			((org.hibernate.Session) session).delete(activity);
			((org.hibernate.Session) session).getTransaction().commit();
		}
		session.close();

		return true;
	}

	@Override
	public boolean updateActivity(Activity activity) throws ActivityDBException {

		// creating a new session for adding products
		Session session = (Session) factory.openSession();
		((org.hibernate.Session) session).beginTransaction();

		((org.hibernate.Session) session).update(activity);
		((org.hibernate.Session) session).getTransaction().commit();

		session.close();

		return true;
	}

	@Override
	public Activity getActivity(Integer id, String name) throws ActivityDBException {
		// creating a new session for adding products
		Session session = (Session) factory.openSession();
		((org.hibernate.Session) session).beginTransaction();

		// Activity activity = session.createQuery("from activities").;
		Activity activity = (Activity) session.get(Activity.class, new ActivityId(id, name));

		session.close();

		return activity;
	}

	@Override
	public boolean userExist(User user) throws UserDBException {
		if(this.gymDAO.getUser(user.getId()) == null)
		{
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean addUser(User user) throws UserDBException {

		// creating a new session for adding products
		Session session = (Session) factory.openSession();
		((org.hibernate.Session) session).beginTransaction();

		if(this.gymDAO.userExist(user))
		{
			return false;
		}
		((org.hibernate.Session) session).save(user);
		((org.hibernate.Session) session).getTransaction().commit();

		session.close();

		return true;
	}

	@Override
	public User getUser(Integer id) throws UserDBException {
		// creating a new session for adding products
		Session session = (Session) factory.openSession();
		((org.hibernate.Session) session).beginTransaction();

		User user = (User) session.get(User.class, id);

		session.close();

		return user;

	}

	@Override
	public boolean deleteUser(Integer id) throws UserDBException {

		// creating a new session for adding products
		Session session = (Session) factory.openSession();
		((org.hibernate.Session) session).beginTransaction();

		User user = (User) session.get(User.class, id);

		if (user != null) {
			((org.hibernate.Session) session).delete(user);
			((org.hibernate.Session) session).getTransaction().commit();
		}
		session.close();

		return true;
	}

}
