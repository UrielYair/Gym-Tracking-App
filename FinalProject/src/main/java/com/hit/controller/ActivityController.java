package com.hit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hit.dao.HibernateGymDAO;
import com.hit.exceptions.ActivityDBException;
import com.hit.model.Activity;
import com.hit.model.User;
import com.hit.utils.InputValidator;
import com.hit.utils.Utilities;

public class ActivityController {
	private static final Logger LOGGER = Logger.getLogger(ActivityController.class.getSimpleName());

	private InputValidator inputValidator;
	private PrintWriter printWriter;
	private HibernateGymDAO hibernateGymDAO;
	private HttpSession httpSession;
	private Activity activity;
	private Utilities utilities;
	private User user;

	public ActivityController() {
		this.printWriter = null;
		this.hibernateGymDAO = HibernateGymDAO.getInstance();
		this.httpSession = null;
		this.activity = null;
		this.user = null;
	}

	// Done, need to check
	public void add(HttpServletRequest request, HttpServletResponse response) {
		httpSession = request.getSession();
		try {
			printWriter = response.getWriter();
			if (inputValidator.activityInputValidation(request)) {
				activity = utilities.createActivityFromRequest(request);
				LOGGER.info("Input is valid, going to add");
				if (hibernateGymDAO.addActivity(activity)) {
					LOGGER.info("Activity has been added");
					printWriter.println("added successfully");
				} else {
					LOGGER.info("Activity already exist");
					printWriter.println("activity already exist");
				}
			} else {
				LOGGER.info("Activity is not valid");
				printWriter.println("activity is not valid");
			}
		} catch (ActivityDBException activityDBException) {
			activityDBException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			printWriter.close();
			activity = null;
		}
	}

	// Done, need to check
	public void update(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("username");
		LOGGER.info("Going to update username");

		try {
			printWriter = response.getWriter();
			if (inputValidator.inputValidation(activity)) {
				LOGGER.info("Input is valid, going to update.");
				if (hibernateGymDAO.updateActivity(activity)) {
					LOGGER.info("Activity has been updated");
					printWriter.println("added successfully");
				} else {
					LOGGER.info("Activity cannot be updated");
					printWriter.println("activity cannot be updated");
				}
			} else {
				LOGGER.info("Activity is not valid");
				printWriter.println("activity is not valid");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			printWriter.close();
			activity = null;
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

		printWriter = response.getWriter();
		HttpSession session = request.getSession();
		String activityName = request.getParameter("activityName");
		String userName = request.getParameter("username");

		// activities(id, user_name, exercise_name, workout_date, amount_of_sets,
		// repeats, weight, duration, type)
		try {
			// to implement inputValidation()
			if (inputValidator.inputValidation(activity)) {
				LOGGER.info("Input is valid, going to update");
				if (hibernateGymDAO.deleteActivity(userName, activityName)) {
					LOGGER.info("Activity has been deleted");
					printWriter.println("deleted successfully");
				} else {
					LOGGER.info("Activity cannot be deleted");
					printWriter.println("activity cannot be deleted");
				}
			} else {
				LOGGER.info("Activity is not valid");
				printWriter.println("activity is not valid");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			printWriter.close();
			activity = null;
		}

	}

	public void get(HttpServletRequest request, HttpServletResponse response, String str) throws IOException {

		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();

		Integer userId = (Integer) session.getAttribute("id");
		if (userId == null) {
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
		Activity activity = dao.getActivity(activityName);
		writer.println(activity.toString());

	}

}
