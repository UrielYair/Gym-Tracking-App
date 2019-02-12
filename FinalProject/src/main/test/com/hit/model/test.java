package com.hit.model;

import com.hit.dao.HibernateGymDAO;
import com.hit.exceptions.ActivityDBException;
import com.hit.exceptions.UserDBException;

public class test {

	public static void main(String[] args) throws UserDBException, ActivityDBException 
	{
		HibernateGymDAO dao = HibernateGymDAO.getInstance();
		
		dao.addActivity(new Activity(111, "legs", 3, 4));
		Activity activity = dao.getActivity(111, "legs");
		System.out.println(activity.toString());
		
		dao.updateActivity(new Activity(111, "legs", 5, 5));
		
		dao.addUser(new User(1 , "bbba", "1234"));
		User user = dao.getUser(1);
		System.out.println(user.toString());
		
		dao.deleteUser(1);
		dao.deleteActivity(111, "legs");
		
	}
}
