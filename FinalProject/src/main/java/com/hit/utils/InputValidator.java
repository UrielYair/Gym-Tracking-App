package com.hit.utils;

import java.io.PrintStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.hit.model.Activity;

public class InputValidator {
	private static final Logger LOGGER = Logger.getLogger(InputValidator.class.getSimpleName());
	private PrintStream printWriter;
	private final String[] legalAnaerobicExerciseNames = { "abs", "back", "chest", "legs", "shoulders" };

	// To finish
	public boolean inputValidation(Activity activity) {
		boolean isValid = false;
		if (activity != null) {
			isValid = true;
		} else {
			printWriter.println("Wrong input.");
		}

		return isValid;
	}

	// To finish
	public boolean usernameValidation(String username) {
		boolean isValid = true;
		char[] usernameCharArray = null;
		if (username != null && username.length() < 10) {
			usernameCharArray = username.toCharArray();
			for (int i = 0; i < username.length(); i++) {
				if (!((usernameCharArray[i] >= '0' && usernameCharArray[i] <= '9')
						|| (usernameCharArray[i] >= 'a' && usernameCharArray[i] <= 'z')
						|| (usernameCharArray[i] >= 'A' && usernameCharArray[i] <= 'Z'))) {
					isValid = false;
					break;
				}
			}
		} else {
			LOGGER.info("Wrong input.");
		}
		return isValid;
	}

	// To finish
	public boolean passwordValidation(String password) {
		boolean isValid = true;
		char[] passwordCharArray = null;
		if (password != null && password.length() < 10) {
			passwordCharArray = password.toCharArray();
			for (int i = 0; i < password.length(); i++) {
				if (!((passwordCharArray[i] >= '0' && passwordCharArray[i] <= '9')
						|| (passwordCharArray[i] >= 'a' && passwordCharArray[i] <= 'z')
						|| (passwordCharArray[i] >= 'A' && passwordCharArray[i] <= 'Z'))) {
					isValid = false;
					break;
				}
			}
		} else {
			LOGGER.info("Wrong input.");
		}
		return isValid;
	}

	public boolean buttonValidator(String[] legalButtons, String buttonName) {
		for (String button : legalButtons) {
			if (button.equals(buttonName)) {
				return true;
			}
		}
		return false;
	}

	public boolean activityInputValidation(HttpServletRequest request, String type) {
		boolean isValid = true;

		try {

			if (type.equals("Cardio")) {
				if (request.getParameter("duration") != "") {
					Float.parseFloat(request.getParameter("duration"));
				}
			} else if (type.equals("Anaerobic")) {
				String exercise_name = request.getParameter("exercise_name");
				if (!anaerobicExerciseNameValidation(exercise_name)) {
					isValid = false;
				}

				if (request.getParameter("amount_of_sets") != "") {
					Integer.parseInt(request.getParameter("amount_of_sets"));
				}

				if (request.getParameter("repeats") != "") {
					Integer.parseInt(request.getParameter("repeats"));
				}

				if (request.getParameter("weight") != "") {
					Float.parseFloat(request.getParameter("weight"));
				}
			} else {
				isValid = false;
			}

		} catch (Exception exception) {
			LOGGER.info("Wrong input.");
			isValid = false;
		}

		return isValid;
	}

	public boolean anaerobicExerciseNameValidation(String exerciseName) {
		boolean isValid = false;

		for (String name : legalAnaerobicExerciseNames) {
			if (name.equals(exerciseName)) {
				isValid = true;
			}
		}
		return isValid;
	}

	public boolean activityUpdateValidation(HttpServletRequest request) {
		boolean isValid = true;

		if (!activityDeleteValidation(request))// Validation of exercise_name and date
		{
			isValid = false;
		} else if (request.getParameter("activityName").equals("cardio")) {
			if (!activityInputValidation(request, "Cardio")) {
				isValid = false;
			}
		} else {
			if (!activityInputValidation(request, "Anaerobic")) {
				isValid = false;
			}
		}
		return isValid;
	}

	public boolean activityDeleteValidation(HttpServletRequest request) {
		boolean isValid = true;

		String exercise_name = request.getParameter("activityName");
		String date = request.getParameter("activityDate");

		if (!anaerobicExerciseNameValidation(exercise_name)) {
			if (!(exercise_name.equals("Cardio"))) {
				isValid = false;
			}
		}

		if (!dateValidation(date)) {
			isValid = false;
		}

		return isValid;
	}

	// to do
	public boolean dateValidation(String date) {
		boolean isValid = true;

		return isValid;
	}
}
