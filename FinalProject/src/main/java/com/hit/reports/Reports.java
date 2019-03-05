package com.hit.reports;

import java.time.LocalDate;

import org.hibernate.*;

import com.hit.dao.HibernateGymDAO;



public class Reports {
	
	
	
	public static void getWorkOutsInPastXDays(int amountOfDays, String userName) {
		/**
		 * retrieve all dates of workouts in the past X days for a specific username.
		 * */
		LocalDate dateBeforeAmountOfDays = LocalDate.now().minusDays(amountOfDays);
		Session session = HibernateGymDAO.getInstance().getFactory().openSession();
		
		String hql = "SELECT distinct(workout_date) as date "
				+ "FROM Activity "
				+ "WHERE username= :Username And WorkoutDate >= :date ";
		
		Query query = session.createQuery(hql);
		
		query.setParameter("Username", userName); 
		query.setParameter("date", dateBeforeAmountOfDays);
		

		ScrollableResults result = query.scroll();
		
		String[] columnsNames = query.getReturnAliases();
		String format = "%-20s \n";
		// Printing column name:
		System.out.format(format, columnsNames[0]);

		// Printing workouts dates:
		while (result.next()) 
			System.out.format(format, result.get(0));
		
	}

		
	
	public static void howManyWorkoutsEveryClientDid() {
		/**
		 * Method to calculating how many workouts every client did.
		 * */
		
		Session session = HibernateGymDAO.getInstance().getFactory().openSession();
		String hql = "SELECT user_name, count(distinct (workout_date)) as number_of_workouts "
				+ "FROM Activity "
				+ "GROUP By Username;";
		Query query = session.createQuery(hql);
		ScrollableResults result = query.scroll();
		
		String[] columnsNames = query.getReturnAliases();
		String format = "%-20s %-20s \n";
		// Printing names of columns:
		System.out.format(format, columnsNames[0], columnsNames[1]);

		// Printing results perfectly aligned:
		while (result.next()) 
			System.out.format(format, result.get(0), result.get(1));
		}

}
