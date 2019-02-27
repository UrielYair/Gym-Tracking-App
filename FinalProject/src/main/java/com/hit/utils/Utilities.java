package com.hit.utils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.Local;

import com.hit.model.Activity;
import com.hit.model.User;

public class Utilities {

	public static String getUserNameByID(Integer userIdToFind) {
		// TODO: implement by querying the Users table in the DataBase.
		return null;
	}

	public static User getUserByUserName(String userName) {
		// TODO: implement.
		return null;
	}

	private static User getUserById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static User getCurrentlyConnectedUser(HttpServletRequest request) {
		String userId = request.getSession().getAttribute("id").toString();
		System.out.printf("%s %s: userId = %s", LocalTime.now().toString(), Local.class.getEnclosingMethod().getName(),
				userId);
		return getUserById(userId);
	}

	public static ArrayList<Activity> getSpecificWorkout(Date dayOfWorkoutToBeChanged, String userToUpdate) {
		// TODO: implement!
		return null;
	}

	public Activity createActivityFromRequest(HttpServletRequest request){
		HttpSession session = request.getSession();
		Activity activity = null;
		String userName = (String) session.getAttribute("userName");
		String exercise_name = request.getParameter("exercise_name");
		//Date workoutDate = new Date();
		String workoutDate = dateToString();
		Integer amountOfSets = Integer.parseInt(request.getParameter("amount_of_sets"));
		Integer amountOfRepeatition = Integer.parseInt(request.getParameter("repeats"));
		float weight = Float.parseFloat(request.getParameter("weight"));
		float duration = Float.parseFloat(request.getParameter("duration"));
		String type = request.getParameter("type");
		activity = new Activity(userName, exercise_name, workoutDate, amountOfSets, amountOfRepeatition, weight,
				duration, type);
		return activity;

	}
	
	private String dateToString()
	{
		Date date = new Date();
		String year = Integer.toString(date.getYear());
		String month = Integer.toString(date.getMonth());
		String day = Integer.toString(date.getDate());
		
		/*StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(day);
		stringBuilder.append("/");
		stringBuilder.append(month);
		stringBuilder.append("/");
		stringBuilder.append(year);*/
		
		return day + "/" + month + "/" + year;
	}

}
