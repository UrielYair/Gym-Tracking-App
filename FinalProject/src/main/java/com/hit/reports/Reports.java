package com.hit.reports;


import java.time.LocalDate;

import org.hibernate.*;
import org.hibernate.mapping.List;

import com.hit.dao.HibernateGymDAO;


public class Reports {
	public static List getWorkOutsInPastXDays(int amountOfDays, String userName) {

		LocalDate dateBeforeAmountOfDays = LocalDate.now().minusDays(amountOfDays);
		Session session = HibernateGymDAO.getInstance().getFactory().openSession();
		
		
		String hql = "SELECT distinct(workoutDate) "
				+ "FROM Activity "
				+ "WHERE username= :Username And WorkoutDate >= :date ";
		
		Query query = session.createQuery(hql);
		
		query.setParameter("Username", userName); 
		query.setParameter("date", dateBeforeAmountOfDays);
		
		return (List) query.list();
		
	
	
	}
	
	public static List howManyWorkoutsEveryClientDid() {

		/**
		 * Method to calculating how many workouts every client did.
		 * */
		
		Session session = HibernateGymDAO.getInstance().getFactory().openSession();
		String hql = "SELECT Username, count(distinct (workouts_date)) as number_of_workouts "
				+ "FROM Activity "
				+ "GROUP By Username;";
		Query query = session.createQuery(hql);
		return (List) query.list();
	}
	

}
