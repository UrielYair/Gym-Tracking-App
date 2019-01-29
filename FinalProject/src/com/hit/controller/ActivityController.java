package com.hit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hit.model.Activity;
import com.hit.model.ActivityDBException;
import com.hit.model.HibernateGymDAO;

public class ActivityController {

	public void add(HttpServletRequest request, HttpServletResponse response, String str) throws IOException {

		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();

		Integer userId = (Integer) session.getAttribute("id");
		if (userId != null) { // check if user connected to the system

			String activityName = request.getParameter("activityName");
			String numberOfSets = request.getParameter("numberOfSets");
			String numberOfRepeat = request.getParameter("numberOfRepeat");

			if (activityName != "" && numberOfSets.chars().allMatch(Character::isDigit)
					&& numberOfRepeat.chars().allMatch(Character::isDigit)) {

				Integer sets = Integer.parseInt(numberOfSets);
				Integer repeat = Integer.parseInt(numberOfRepeat);

				if (activityName.equals("legs") || activityName.equals("chest") || activityName.equals("back")) {

					HibernateGymDAO dao = HibernateGymDAO.getInstance();
					Activity activity = new Activity(userId, activityName, sets, repeat);
					try {
						if (dao.addActivity(activity)) {
							writer.println("added successfully");
						} else {
							writer.println("activity already exist");
						}

					} catch (ActivityDBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					writer.println("wrong activity name");
				}

			} else {
				writer.println("wrong input");
			}

		} else {
			writer.println("you are not connected!");
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response, String str) throws IOException {

		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();

		Integer userId = (Integer) session.getAttribute("id");
		if (userId != null) { // check if user connected to the system

			String activityName = request.getParameter("activityName");
			String numberOfSets = request.getParameter("numberOfSets");
			String numberOfRepeat = request.getParameter("numberOfRepeat");

			if (activityName != "" && numberOfSets.chars().allMatch(Character::isDigit)
					&& numberOfRepeat.chars().allMatch(Character::isDigit)) {

				Integer sets = Integer.parseInt(numberOfSets);
				Integer repeat = Integer.parseInt(numberOfRepeat);

				if (activityName.equals("legs") || activityName.equals("chest") || activityName.equals("back")) {

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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					writer.println("wrong activity name");
				}

			} else {
				writer.println("wrong input");
			}

		} else {
			writer.println("you are not connected!");
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response, String str) throws IOException {

		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();

		Integer userId = (Integer) session.getAttribute("id");
		if (userId != null) { // check if user connected to the system

			String activityName = request.getParameter("activityName");

			if (activityName.equals("legs") || activityName.equals("chest") || activityName.equals("back")) {

				HibernateGymDAO dao = HibernateGymDAO.getInstance();
				
				try {
					dao.deleteActivity(userId, activityName);
					writer.println("activity " + activityName + " deleted successfully");

				} catch (ActivityDBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				writer.println("wrong activity name");
			}

		} else {
			writer.println("you are not connected!");
		}
	}

	public void get(HttpServletRequest request, HttpServletResponse response, String str) throws IOException {

		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();

		Integer userId = (Integer) session.getAttribute("id");
		if (userId != null) { // check if user connected to the system

			String activityName = request.getParameter("activityName");

			if (activityName.equals("legs") || activityName.equals("chest") || activityName.equals("back")) {

				HibernateGymDAO dao = HibernateGymDAO.getInstance();
				
				try {
					Activity activity = dao.getActivity(userId, activityName);
					writer.println(activity.toString());

				} catch (ActivityDBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				writer.println("wrong activity name");
			}

		} else {
			writer.println("you are not connected!");
		}
	}
}
