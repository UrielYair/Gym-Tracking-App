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
		Integer userId = (Integer) session.getAttribute("id");
		String userName = getUserNameByID(userId); // querying the users table in the database.
		String activityName = request.getParameter("activityName");
		Date workoutDate = new Date();
		Integer amountOfSets = Integer.parseInt(request.getParameter("numberOfSets"));
		Integer amountOfRepeatition = Integer.parseInt(request.getParameter("activityRepeatitions"));
		float weight = Float.parseFloat(request.getParameter("weight"));
		float duration = Float.parseFloat(request.getParameter("activityDuration"));
		String type = request.getParameter("activityType");
		activity = new Activity(userName, activityName, workoutDate, amountOfSets, amountOfRepeatition, weight,
				duration, type);
		return activity;

	}

}
