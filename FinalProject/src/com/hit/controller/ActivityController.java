package com.hit.controller;

import java.io.IOException;
import java.io.PrintWriter;
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



public class ActivityController {

	public void add(HttpServletRequest request, HttpServletResponse response, String str) throws IOException {

		try { // maybe need to take move to a different area, or change declaration of the method to throw exception.
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
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	




	public void update(HttpServletRequest request, HttpServletResponse response, String str) throws IOException {

		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();

		Integer userId = (Integer) session.getAttribute("id");
		
		if (userId==null) {
			writer.println("you are not connected!");
			return;
		}
		 
		// check if user connected to the system

		String activityName = request.getParameter("activityName");
		String numberOfSets = request.getParameter("numberOfSets");
		String numberOfRepeat = request.getParameter("numberOfRepeat");

		if (!(activityName != "" && numberOfSets.chars().allMatch(Character::isDigit)
				&& numberOfRepeat.chars().allMatch(Character::isDigit))) {
			writer.println("wrong input");
			return;
		}
		

		Integer sets = Integer.parseInt(numberOfSets);
		Integer repeat = Integer.parseInt(numberOfRepeat);

		//TODO: eliminate hard coded text.	
		if (!(activityName.equals("legs") || activityName.equals("chest") || activityName.equals("back"))) {
			writer.println("wrong activity name");
			return;
		}
		
		HibernateGymDAO dao = HibernateGymDAO.getInstance();
		Activity activity = new Activity(userId, activityName, sets, repeat);
		try {
			if (dao.activityExist(activity)) {
				dao.updateActivity(activity);
				writer.println("updated successfully");
			} else {
				writer.println("activity doesn't exist");
			}

		} catch (ActivityDBException e) {
			e.printStackTrace();
		}

	} 
	
	public void delete(HttpServletRequest request, HttpServletResponse response, String str) throws IOException {

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
			dao.deleteActivity(userId, activityName);
			writer.println("activity " + activityName + " deleted successfully");
		} 
		catch (ActivityDBException e) {
			e.printStackTrace();
		}
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
