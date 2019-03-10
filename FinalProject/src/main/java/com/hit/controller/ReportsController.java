package com.hit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.Table;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hit.dao.HibernateGymDAO;
import com.hit.model.Activity;

public class ReportsController {
	private static final Logger LOGGER = Logger.getLogger(NavigationController.class.getSimpleName());

	private HibernateGymDAO hibernateGymDAO;

	public ReportsController() {
		this.hibernateGymDAO = HibernateGymDAO.getInstance();
	}

	public void getAllMyActivities(HttpServletRequest request, HttpServletResponse response, String str) {

		try {

			PrintWriter printWriter = response.getWriter();
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
					
					for(Activity activity: listOfActivities)
					{
						printWriter.println(activity.toString());
					}
				}

			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	public void getWorkOutsInPastXDays(HttpServletRequest request, HttpServletResponse response, String str) {
		/**
		 * retrieve all dates of workouts in the past X days for a specific username.
		 */

	}
}
