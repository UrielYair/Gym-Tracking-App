package com.hit.controller;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hit.dao.HibernateGymDAO;
import com.hit.model.Activity;
import com.hit.utils.InputValidator;

public class ReportsController {
	private static final Logger LOGGER = Logger.getLogger(NavigationController.class.getSimpleName());

	private HibernateGymDAO hibernateGymDAO;

	private InputValidator inputValidator;

	public ReportsController() {
		this.hibernateGymDAO = HibernateGymDAO.getInstance();
		this.inputValidator = new InputValidator();
	}

	public void getAllMyActivities(HttpServletRequest request, HttpServletResponse response, String str) {

		PrintWriter printWriter = null;

		try {

			printWriter = response.getWriter();
			List<Activity> listOfActivities = null;

			HttpSession session = request.getSession();
			if (session.getAttribute("userName") == null) {

				LOGGER.info("user is not logged in");

				request.setAttribute("message", "You are not logged in");
				request.getRequestDispatcher("/login.jsp").forward(request, response);

			} else {

				LOGGER.info("user is logged in");
				String userName = (String) session.getAttribute("userName");
				listOfActivities = hibernateGymDAO.getAllUserActivities(userName);
				if (listOfActivities.isEmpty()) {
					printWriter.println("No avtivities");
				} else {
					printWriter.println("All your activities:\n");
					for (Activity activity : listOfActivities) {
						printWriter.println(activity.toString() + "\n");
					}
				}

			}

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			printWriter.close();
		}

	}

	public void getWorkOutsInPastXDays(HttpServletRequest request, HttpServletResponse response, String str) {
		/**
		 * retrieve all dates of workouts in the past X days for a specific username.
		 */

		PrintWriter printWriter = null;

		try {

			printWriter = response.getWriter();
			List<Activity> listOfActivities = null;

			HttpSession session = request.getSession();
			if (session.getAttribute("userName") == null) {

				LOGGER.info("user is not logged in");

				request.setAttribute("message", "You are not logged in");
				request.getRequestDispatcher("/login.jsp").forward(request, response);

			} else {

				LOGGER.info("user is logged in");
				String userName = (String) session.getAttribute("userName");
				String amountOfDays = request.getParameter("amountOfDays");

				if (inputValidator.longNambersOnly(amountOfDays)) {
					Long days = Long.valueOf(amountOfDays);

					listOfActivities = hibernateGymDAO.getAllUserActivities(userName);
					if (listOfActivities.isEmpty()) {
						printWriter.println("No avtivities");
					} else {
						printWriter.println("Your last " + amountOfDays + " days activities:\n\n");
						for (Activity activity : listOfActivities) {

							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

							LocalDate date1 = LocalDate.parse(activity.getWorkoutDate(), formatter);

							LocalDate now = LocalDate.now();
							LocalDate nowMinusX = now.minusDays(days);

							if (date1.isAfter(nowMinusX)) {
								printWriter.println(activity.toString() + "\n");
							}
						}
					}
				} else {
					request.setAttribute("message", "please type digits only!");
					request.getRequestDispatcher("/allWorkoutsInPastXDays.jsp").forward(request, response);
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			printWriter.close();
		}
	}

	public void howManyWorkoutsEveryClientDid(HttpServletRequest request, HttpServletResponse response, String str) {
		/**
		 * Method to calculating how many workouts every client did.
		 */
		PrintWriter printWriter = null;

		try {

			printWriter = response.getWriter();
			List<Activity> listOfActivities = null;

			HttpSession session = request.getSession();
			if (session.getAttribute("userName") == null) {

				LOGGER.info("user is not logged in");

				request.setAttribute("message", "You are not logged in");
				request.getRequestDispatcher("/login.jsp").forward(request, response);

			} else {

				LOGGER.info("user is logged in");
				String userName = (String) session.getAttribute("userName");

				listOfActivities = hibernateGymDAO.getAllActivities();
				if (listOfActivities.isEmpty()) {
					printWriter.println("No avtivities");
				} else {
					printWriter.println("All users activities activities:\n");
					for (Activity activity : listOfActivities) {
						printWriter.println(activity.toString() + "\n");
					}
				}
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			printWriter.close();
		}

	}
}
