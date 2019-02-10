package com.hit.utils;

import java.io.PrintStream;

import com.hit.model.Activity;

public class InputValidator {
	private PrintStream printWriter;

	
	//To finish
	public boolean inputValidation(Activity activity) {
		boolean isValid = false;
		if (activity != null) {
			isValid = true;
		} else {
			printWriter.println("Wrong input.");
		}

		return isValid;
	}
	
	//To finish
	public boolean usernameValidation(String username) {
		boolean isValid = false;
		if (username != null) {
			isValid = true;
		} else {
			printWriter.println("Wrong input.");
		}

		return isValid;
	}
	
	//To finish
	public boolean passwordValidation(String password) {
		boolean isValid = false;
		if (password != null) {
			isValid = true;
		} else {
			printWriter.println("Wrong input.");
		}

		return isValid;
	}

}
