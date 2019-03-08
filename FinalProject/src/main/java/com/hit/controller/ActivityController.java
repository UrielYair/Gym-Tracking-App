package com.hit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hit.dao.HibernateGymDAO;
import com.hit.model.Activity;
import com.hit.utils.InputValidator;
import com.hit.utils.Utilities;

public class ActivityController {
	private static final Logger LOGGER = Logger.getLogger(ActivityController.class.getSimpleName());
	private InputValidator inputValidator;
	private PrintWriter printWriter;
	private HibernateGymDAO hibernateGymDAO;
	private Activity activity; // TODO: consider removing. should not be a member of a controller. should be
								// instantiated separately each time needed.
	private Utilities utilities;

	public ActivityController() {
		this.printWriter = null;
		this.hibernateGymDAO = HibernateGymDAO.getInstance();
		this.activity = null;
		this.inputValidator = new InputValidator();
	}

	public void addCardio(HttpServletRequest request, HttpServletResponse response, String str) {
		add(request, response, "Cardio");
	}

	public void addAnaerobic(HttpServletRequest request, HttpServletResponse response, String str) {
		add(request, response, "Anaerobic");
	}
	
	// TODO: Done, need to check
	private void add(HttpServletRequest request, HttpServletResponse response, String type) {

		/*
		 * * The method is responsible for getting input parameters and validate them.
		 * Right after validation succeeded, an Activity object will be created and then
		 * will be saved into the DB using Hibernate.
		 * 
		 * Notes: - In any phase the program will write logs for further inspection. -
		 * In case of an error, adding will be canceled.
		 */

		try {
			printWriter = response.getWriter();

			HttpSession session = request.getSession();
			if (session.getAttribute("userName") == null) {

				LOGGER.info("user is not logged in");
				printWriter.println("you are not logged in");
				response.sendRedirect(request.getContextPath() + "/login.jsp");
			} else {

				LOGGER.info("user is logged in");
				if (inputValidator.activityInputValidation(request)) {
					// Validation succeeded, adding attempt:
					activity = Utilities.createActivityFromRequest(request, type);
					LOGGER.info("Input is valid, going to add");

					if (hibernateGymDAO.addActivity(activity)) {
						LOGGER.info("Activity has been added");
						// printWriter.println("added successfully");
						response.sendRedirect(request.getContextPath() + "/activities.jsp");
					} else {
						LOGGER.info("Activity already exist");
						// printWriter.println("activity already exist");
						response.sendRedirect(request.getContextPath() + "/activities.jsp");
					}

				} else {
					// Validation fails:
					LOGGER.info("Activity is not valid");
					// printWriter.println("activity is not valid");
					response.sendRedirect(request.getContextPath() + "/addActivities.jsp");
				}

			}

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			printWriter.close();
			activity = null;
		}
	}

	// TODO: Done, need to check
	public void update(HttpServletRequest request, HttpServletResponse response, String str) {

		/*
		 * * Method for update existing activity in DB. The method will validate
		 * parameters, create Activity object and then, try to update the existing one
		 * in the DB using Hibernate, if exist.
		 * 
		 * Notes: - In any phase the program will write logs for further inspection. -
		 * In case of an error, updating will be canceled.
		 */

		LOGGER.info("Trying to update username");

		Activity activity = null;

		try {
			printWriter = response.getWriter();

			HttpSession session = request.getSession();
			if (session.getAttribute("userName") == null) {

				LOGGER.info("user is not logged in");
				printWriter.println("you are not logged in");

			} else {

				LOGGER.info("user is logged in");

				String activityName = request.getParameter("activityName");
				String activityDate = request.getParameter("activityDate");

				String userName = (String) session.getAttribute("userName");

				// the validation should be for the parameters no activity
				// if (inputValidator.inputValidation(activity)) { // TODO: Verify where
				// activity comes from, maybe
				// [activity =
				// utilities.createActivityFromRequest(request);]
				// needed.

				if (true) {// for now until the validation function will be made
					activity = hibernateGymDAO.getActivity(userName, activityName, activityDate);

					if (request.getParameter("amount_of_sets") != "") {
						activity.setAmountOfSets(Integer.parseInt(request.getParameter("amount_of_sets")));
					}

					if (request.getParameter("repeats") != "") {
						activity.setAmountOfRepeatition(Integer.parseInt(request.getParameter("repeats")));
					}

					if (request.getParameter("weight") != "") {
						activity.setWeight(Float.parseFloat(request.getParameter("weight")));
					}

					if (request.getParameter("duration") != "") {
						activity.setDuration(Float.parseFloat(request.getParameter("duration")));
					}

					if (request.getParameter("type") != "") {
						activity.setType(request.getParameter("type"));
					}

					// Validation succeeded, updating attempt:
					LOGGER.info("Input is valid, trying to update.");
					if (hibernateGymDAO.updateActivity(activity)) {
						LOGGER.info("Activity has been updated");
						printWriter.println("added successfully");
					} else {
						LOGGER.info("Activity cannot be updated");
						printWriter.println("activity cannot be updated");
					}

				} else {
					// Validation fails:
					LOGGER.info("Activity is not valid");
					printWriter.println("activity is not valid");
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			printWriter.close();
			activity = null;
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response, String str) throws Exception {

		/*
		 * * Method for delete existing activity in DB. The method will validate
		 * parameters, create Activity object and then, try to remove the existing one
		 * in the DB using Hibernate, if exist.
		 * 
		 * Notes: - In any phase the program will write logs for further inspection. -
		 * In case of an error, deleting will be canceled.
		 */

		try {

			printWriter = response.getWriter();

			HttpSession session = request.getSession();
			if (session.getAttribute("userName") == null) {

				LOGGER.info("user is not logged in");
				printWriter.println("you are not logged in");

			} else {

				LOGGER.info("user is logged in");

				String activityName = request.getParameter("activityName");
				String activityDate = request.getParameter("activityDate");

				String userName = (String) session.getAttribute("userName");

				// the validation should be for the parameters no activity
				// if (inputValidator.inputValidation(activity)) { // TODO: verify where
				// activity comes from.
				if (true) {// for now until the validation function will be made

					// Validation succeeded, deleting attempt:
					LOGGER.info("Input is valid, going to update");
					if (hibernateGymDAO.deleteActivity(userName, activityName, activityDate)) {
						LOGGER.info("Activity has been deleted");
						printWriter.println("deleted successfully");
					} else {
						LOGGER.info("Activity cannot be deleted");
						printWriter.println("activity cannot be deleted");
					}

				} else {
					// Validation fails:
					LOGGER.info("Activity is not valid");
					printWriter.println("activity is not valid");
				}
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			printWriter.close();
			activity = null;
		}

	}

	public void get(HttpServletRequest request, HttpServletResponse response, String str) throws IOException {

		// TODO: Check the followings:
		// is 'str' variable really needed?

		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");

		if (username == null) {
			// TODO: is it even possible? - maybe should be removed.
			writer.println("you are not connected!");
			return;
		}
		// check if user connected to the system - TODO: what does that means?

		String activityName = request.getParameter("exercise_name");
		String activityDate = request.getParameter("exerciseDate");

		// input validation for exercise name:
		if (!(activityName.equals("legs") || activityName.equals("chest") || activityName.equals("back"))) {
			writer.println("wrong activity name");
			return;
		}

		Activity activity = hibernateGymDAO.getActivity(username, activityName, activityDate); // TODO: check if
																								// activity name is
																								// enough for retrieving
																								// a specific activity
																								// from the DB.
		// in other words, check the key of activity table.
		writer.println(activity.toString());

	}

}
