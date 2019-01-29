package com.hit.utils;

import static com.hit.utils.Utilities.activityInputValidation;
import static com.hit.utils.Utilities.getUserNameByID;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hit.model.Activity;

public class Utilities {
	public static String getUserNameByID(Integer userIdToFind) {
		//TODO: implement by querying the Users table in the DataBase.
		return "";
	}
	
	
	public static boolean activityInputValidation(
													Integer userId, 
													String userName, 
													String activityName, 
													Date workoutDate,
													Integer amountOfSets, 
													Integer amountOfRepeatition, 
													float weight, 
													float duration, 
													String type) throws Exception
	{
		//TODO: implement while using exception.
				return true;
	}
	
	public static Activity createActivityfromRequest(HttpServletRequest request) throws Exception
	{
		HttpSession session = request.getSession();
		
		Integer userId = (Integer) session.getAttribute("id");
		String userName = getUserNameByID(userId); //querying the users table in the database.
		String activityName = request.getParameter("activityName");
		Date workoutDate = new Date();
		Integer amountOfSets = Integer.parseInt(request.getParameter("numberOfSets"));
		Integer amountOfRepeatition = Integer.parseInt(request.getParameter("activityRepeatitions"));
		float weight = Float.parseFloat(request.getParameter("weight"));
		float duration= Float.parseFloat(request.getParameter("activityDuration"));
		String type = request.getParameter("activityType");
		
		activityInputValidation(userId, userName, activityName, workoutDate, amountOfSets, amountOfRepeatition, weight, duration, type);//will throw exception if needed.
		
		Activity activity = new Activity(userName,activityName,workoutDate,amountOfSets,amountOfRepeatition,weight,duration,type);
		return activity;
	}

}
