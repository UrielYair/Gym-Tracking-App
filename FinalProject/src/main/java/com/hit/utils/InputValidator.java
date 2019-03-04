package com.hit.utils;

import java.io.PrintStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.hit.model.Activity;

public class InputValidator {
	private static final Logger LOGGER = Logger.getLogger(InputValidator.class.getSimpleName());
	private PrintStream printWriter;

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
		for(String button : legalButtons) {
			if(button.equals(buttonName)) {
				return true;
			}
		}
		return false;
	}
	
	// To do
	public boolean activityInputValidation(HttpServletRequest request){
		return true;
	}

}
