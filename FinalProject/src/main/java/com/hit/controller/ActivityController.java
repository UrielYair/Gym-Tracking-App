package com.hit.controller;

import static com.hit.utils.Utilities.getSpecificWorkout;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.Local;

import com.hit.model.Activity;
import com.hit.model.ActivityDBException;
import com.hit.model.HibernateGymDAO;
import com.hit.model.User;
import com.hit.utils.InputValidator;

public class ActivityController {
	private InputValidator inputValidator;
	private PrintWriter printWriter;
	private HibernateGymDAO hibernateGymDAO;
	private HttpSession httpSession;
	private Activity activity;
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
		printWriter = response.getWriter();
		httpSession = request.getSession();
		activity = createActivity(request); // to implement createActivity()

		try {
			// to implement inputValidation()
			if (inputValidator.inputValidation(activity)) {
				System.out.printf("%s %s: Input is valid, going to add.", getTime(), getMethodName());
				if (hibernateGymDAO.addActivity(activity)) {
					System.out.printf("%s %s: Activity has been added", getTime(), getMethodName());
					printWriter.println("added successfully");
				} else {
					System.out.printf("%s %s: Activity already exist", getTime(), getMethodName());
					printWriter.println("activity already exist");
				}
			} else {
				System.out.printf("%s %s: Activity is not valid", getTime(), getMethodName());
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
		System.out.printf("%s %s: Going to update, username = %s", getTime(), getMethodName(), userName);

		try {
			printWriter = response.getWriter();
			if (inputValidator.inputValidation(activity)) {
				System.out.printf("%s %s: Input is valid, going to update.", getTime(), getMethodName());
				if (hibernateGymDAO.updateActivity(activity)) {
					System.out.printf("%s %s: Activity has been updated", getTime(), getMethodName());
					printWriter.println("added successfully");
				} else {
					System.out.printf("%s %s: Activity cannot be updated", getTime(), getMethodName());
					printWriter.println("activity cannot be updated");
				}
			} else {
				System.out.printf("%s %s: Activity is not valid", getTime(), getMethodName());
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
				System.out.printf("%s %s: Input is valid, going to update.", getTime(), getMethodName());
				if (hibernateGymDAO.deleteActivity(userName, activityName)) {
					System.out.printf("%s %s: Activity has been deleted", getTime(), getMethodName());
					printWriter.println("deleted successfully");
				} else {
					System.out.printf("%s %s: Activity cannot be deleted", getTime(), getMethodName());
					printWriter.println("activity cannot be deleted");
				}
			} else {
				System.out.printf("%s %s: Activity is not valid", getTime(), getMethodName());
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

		try {
			Activity activity = dao.getActivity(userId, activityName);
			writer.println(activity.toString());

		} catch (ActivityDBException e) {
			e.printStackTrace();
		}
	}

	private String getTime() {
		return LocalTime.now().toString();
	}

	private String getMethodName() {
		return Local.class.getEnclosingMethod().getName();
	}
}
