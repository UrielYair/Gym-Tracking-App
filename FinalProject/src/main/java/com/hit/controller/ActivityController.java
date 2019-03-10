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
	private HibernateGymDAO hibernateGymDAO;

	public ActivityController() {
		this.hibernateGymDAO = HibernateGymDAO.getInstance();
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

			Activity activity = null;

			HttpSession session = request.getSession();
			if (session.getAttribute("userName") == null) {

				LOGGER.info("user is not logged in");
				request.setAttribute("message", "You are not logged in");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			} else {

				LOGGER.info("user is logged in");
				if (inputValidator.activityInputValidation(request, type)) {
					// Validation succeeded, adding attempt:
					activity = Utilities.createActivityFromRequest(request, type);
					LOGGER.info("Input is valid, going to add");

					if (hibernateGymDAO.addActivity(activity)) {
						LOGGER.info("Activity has been added");
						request.setAttribute("message", "Activity added successfully");
						request.getRequestDispatcher("/activities.jsp").forward(request, response);

					} else {
						LOGGER.info("Activity already exist");
						request.setAttribute("message", "Activity already exist");
						request.getRequestDispatcher("/add" + type + ".jsp").forward(request, response);
					}

				} else {
					// Validation fails:
					LOGGER.info("Activity is not valid");
					request.setAttribute("message", "Activity is not valid");
					request.getRequestDispatcher("/add" + type + ".jsp").forward(request, response);
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
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

		LOGGER.info("Trying to update activity");

		Activity activity = null;

		try {

			HttpSession session = request.getSession();
			if (session.getAttribute("userName") == null) {

				LOGGER.info("user is not logged in");
				request.setAttribute("message", "You are not logged in");
				request.getRequestDispatcher("/login.jsp").forward(request, response);

			} else {

				LOGGER.info("user is logged in");

				String activityName = request.getParameter("exercise_name");
				String activityDate = request.getParameter("activityDate");

				String userName = (String) session.getAttribute("userName");

				if (inputValidator.activityUpdateValidation(request)) {
					activity = hibernateGymDAO.getActivity(userName, activityName, activityDate);

					if (activity != null) {

						if (activityName.equals("cardio")) {
							if (request.getParameter("amount_of_sets") != "" || request.getParameter("repeats") != ""
									|| request.getParameter("weight") != "") {
								request.setAttribute("message",
										"You cannot change amount of sets, repeats or weight in cardio activity");
								request.getRequestDispatcher("/updateActivity.jsp").forward(request, response);
								return;
							}
							if (request.getParameter("duration") != "") {
								activity.setDuration(Float.parseFloat(request.getParameter("duration")));
							}
						} else {
							if (request.getParameter("duration") != "") {
								request.setAttribute("message", "You cannot change duration in anaerobic activity");
								request.getRequestDispatcher("/updateActivity.jsp").forward(request, response);
								return;
							}
							if (request.getParameter("amount_of_sets") != "") {
								activity.setAmountOfSets(Integer.parseInt(request.getParameter("amount_of_sets")));
							}

							if (request.getParameter("repeats") != "") {
								activity.setAmountOfRepeatition(Integer.parseInt(request.getParameter("repeats")));
							}

							if (request.getParameter("weight") != "") {
								activity.setWeight(Float.parseFloat(request.getParameter("weight")));
							}
						}

						// Validation succeeded, updating attempt:
						LOGGER.info("Input is valid, trying to update.");
						if (hibernateGymDAO.updateActivity(activity)) {
							LOGGER.info("Activity has been updated");
							request.setAttribute("message", "Activity has been updated successfully");
							request.getRequestDispatcher("/activities.jsp").forward(request, response);

						} else {
							LOGGER.info("Activity cannot be updated");
							request.setAttribute("message", "Activity cannot be updated");
							request.getRequestDispatcher("/updateActivity.jsp").forward(request, response);
						}
					} else {
						request.setAttribute("message", "Activity dose not exit");
						request.getRequestDispatcher("/updateActivity.jsp").forward(request, response);
					}
				} else {
					// Validation fails:
					LOGGER.info("Activity is not valid");
					request.setAttribute("message", "Activity is not valid");
					request.getRequestDispatcher("/updateActivity.jsp").forward(request, response);
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
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

			HttpSession session = request.getSession();
			if (session.getAttribute("userName") == null) {

				LOGGER.info("user is not logged in");
				request.setAttribute("message", "You are not logged in");
				request.getRequestDispatcher("/login.jsp").forward(request, response);

			} else {

				LOGGER.info("user is logged in");

				String activityName = request.getParameter("exercise_name");
				String activityDate = request.getParameter("activityDate");

				String userName = (String) session.getAttribute("userName");

				if (inputValidator.activityDeleteValidation(request)) {

					// Validation succeeded, deleting attempt:
					LOGGER.info("Input is valid, going to update");

					Activity activity = hibernateGymDAO.getActivity(userName, activityName, activityDate);

					if (activity != null) {

						if (hibernateGymDAO.deleteActivity(userName, activityName, activityDate)) {
							LOGGER.info("Activity has been deleted");
							request.setAttribute("message", "Activity deleted successfully");
							request.getRequestDispatcher("/activities.jsp").forward(request, response);

						} else {
							LOGGER.info("Activity cannot be deleted");
							request.setAttribute("message", "Activity cannot be deleted");
							request.getRequestDispatcher("/deleteActivity.jsp").forward(request, response);
						}

					} else {
						request.setAttribute("message", "Activity dose not exit");
						request.getRequestDispatcher("/deleteActivity.jsp").forward(request, response);
					}
				} else {
					// Validation fails:
					LOGGER.info("Activity is not valid");
					request.setAttribute("message", "Activity is not valid");
					request.getRequestDispatcher("/deleteActivity.jsp").forward(request, response);
				}
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
