package com.hit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hit.model.Activity;
import com.hit.model.ActivityDBException;
import com.hit.model.HibernateGymDAO;
import static com.hit.utils.Utilities.getUserNameByID;
import static com.hit.utils.Utilities.activityInputValidation;
import static com.hit.utils.Utilities.createActivityfromRequest;
import static com.hit.utils.Utilities.getSpecificWorkout;



public class ActivityController {

	public void add(HttpServletRequest request, HttpServletResponse response, String str) throws Exception {

			PrintWriter writer = response.getWriter();
			HttpSession session = request.getSession();
	
			Activity activity = createActivityfromRequest(request);
			HibernateGymDAO dao = HibernateGymDAO.getInstance();	
			
			if (dao.addActivity(activity)) {
				writer.println("added successfully"); 
			} 
			else {
				writer.println("activity already exist");
			}

	}

	public void update(HttpServletRequest request, HttpServletResponse response, String str) throws Exception {

		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();

		HibernateGymDAO dao = HibernateGymDAO.getInstance();
		
		//Assuming the method will update specific exercise out of specific workout in specific date!!!
		
		//activities(id, user_name, exercise_name, workout_date, amount_of_sets, repeats, weight, duration, type)
		
		Date dayOfWorkoutToBeChanged = DateFormat.parse(request.getParameter("day"));
		ArrayList<Activity> workout = getSpecificWorkout(dayOfWorkoutToBeChanged,userToUpdate); // all activities(exercise/cardio) from specific workout.
		
		//createActivityfromRequest(request);

		if (dao.activityExist(activity)) {
			dao.updateActivity(activity);
			writer.println("updated successfully");
		} else {
			writer.println("activity doesn't exist");
		}

	} 
	
	public void delete(HttpServletRequest request, HttpServletResponse response, String str) throws Exception {

		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		HibernateGymDAO dao = HibernateGymDAO.getInstance();
		
		String activityName = request.getParameter("activityName");

		
		//activities(id, user_name, exercise_name, workout_date, amount_of_sets, repeats, weight, duration, type)
		dao.deleteActivity(userId, activityName);
		writer.println("activity " + activityName + " deleted successfully");
	}

	public void get(HttpServletRequest request, HttpServletResponse response, String str) throws IOException {

		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();

		Integer userId = (Integer) session.getAttribute("id");
		if (userId==null) {
			writer.println("you are not connected!");
			return;
		}
		// check if user connected to the system

		String activityName = request.getParameter("activityName");

		if (!(activityName.equals("legs") || activityName.equals("chest") || activityName.equals("back"))) {
			writer.println("wrong activity name");
			return;
		}

		HibernateGymDAO dao = HibernateGymDAO.getInstance();
		
		try {
			Activity activity = dao.getActivity(userId, activityName);
			writer.println(activity.toString());

		} catch (ActivityDBException e) {
			e.printStackTrace();
		}
	}
}
