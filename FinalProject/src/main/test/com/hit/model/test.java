package com.hit.model;

import com.hit.dao.HibernateGymDAO;
import com.hit.exceptions.DBException;

public class test {

	public static void main(String[] args)
	{
		HibernateGymDAO dao = HibernateGymDAO.getInstance();
		
		//Activity Test
		dao.addActivity(new Activity("userName", "legs", "29/02/2000",  2,
				 3,  (float)5.2,  (float)2.0 , "anerobic"));
		
		Activity activity = null;
		try {
			activity = dao.getActivity("userName", "legs", "29/02/2000");
			System.out.println("addActivity and getActivity works");
		} catch (DBException e) {
			System.out.println("addActivity or getActivity don't work");
			e.printStackTrace();
		}
		
		dao.updateActivity(new Activity("userName", "legs", "29/02/2000",  4,
				 4,  (float)4,  (float)4 , "anerobic"));
		
		try {
			activity = dao.getActivity("userName", "legs", "29/02/2000");
			System.out.println("updateActivity works");
		} catch (DBException e) {
			System.out.println("updateActivity doesn't work");
			e.printStackTrace();
		}
		
		dao.deleteActivity("userName", "legs", "29/02/2000");
		
		try {
			activity = dao.getActivity("userName", "legs", "29/02/2000");
			System.out.println("deleteActivity doesn't work");
		} catch (DBException e) {
			System.out.println("deleteActivity works null value Exception");
			e.printStackTrace();
		}
		
		//User Test
		dao.addUser(new User("bbba", "1234"));
		
		User user = dao.getUser("bbba");
		
		if(user != null) {
		System.out.println("addUser and getUser works");
		}
		else
		{
			System.out.println("addUser and getUser don't work");
		}
		
		dao.deleteUser("bbba");
		
		user = dao.getUser("bbba");
		
		if(user == null) {
			System.out.println("deleteUser works");
			}
			else
			{
				System.out.println("deleteUser doesn't work");
			}
	}
}
