package com.hit.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class InputValidator {
	private static final Logger LOGGER = Logger.getLogger(InputValidator.class.getSimpleName());
	private final String[] legalAnaerobicExerciseNames = { "abs", "back", "chest", "legs", "shoulders" };

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
		} finally

		{
			return isValid;
		}
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
		} else if (request.getParameter("exercise_name").equals("cardio")) {
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

		String exercise_name = request.getParameter("exercise_name");
		String date = request.getParameter("activityDate");

		if (!anaerobicExerciseNameValidation(exercise_name)) {
			if (!(exercise_name.equals("cardio"))) {
				isValid = false;
			}
		}

		if (!dateValidation(date)) {
			isValid = false;
		}

		return isValid;
	}

	public boolean dateValidation(String date) {
		boolean isValid = true;
		try {
			Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
			if ((date1.getYear() + 1900) < 2019 || (date1.getYear() + 1900) > 9999) {
				return false;
			}
			if (date1.getMonth() < 0 || date1.getMonth() > 11) {
				return false;
			}
			if (date1.getDate() < 1 || date1.getDate() > 31) {
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			isValid = false;
		}
		return isValid;
	}

	public boolean longNambersOnly(String number) {
		boolean isValid = true;

		try {
			Long.valueOf(number);

		} catch (Exception exception) {
			LOGGER.info("Wrong input.");
			isValid = false;
		} finally {
			return isValid;
		}
	}
}
