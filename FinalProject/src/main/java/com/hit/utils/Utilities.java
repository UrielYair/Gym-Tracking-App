package com.hit.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.Local;

import com.hit.model.Activity;
import com.hit.model.User;

public class Utilities {

	public static Activity createActivityFromRequest(HttpServletRequest request, String type) {
		HttpSession session = request.getSession();
		Activity activity = null;

		String userName = (String) session.getAttribute("userName");
		String exercise_name = null;
		String workoutDate = dateToString();

		Integer amountOfSets = null;
		Integer amountOfRepeatition = null;
		Float weight = null;
		Float duration = null;

		if (type.equals("Anaerobic")) {
			exercise_name = request.getParameter("exercise_name");
			
			if (request.getParameter("amount_of_sets") != "") {
				amountOfSets = Integer.parseInt(request.getParameter("amount_of_sets"));
			}

			if (request.getParameter("repeats") != "") {
				amountOfRepeatition = Integer.parseInt(request.getParameter("repeats"));
			}

			if (request.getParameter("weight") != "") {
				weight = Float.parseFloat(request.getParameter("weight"));
			}
		} else if (type.equals("Cardio")) {
			exercise_name = "cardio";
			
			if (request.getParameter("duration") != "") {
				duration = Float.parseFloat(request.getParameter("duration"));
			}
		}
	
		activity = new Activity(userName, exercise_name, workoutDate, amountOfSets, amountOfRepeatition, weight,
				duration, type);
		return activity;

	}

	private static String dateToString() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
		return dateFormat.format(date).toString();
	}

}
