package com.hit.utils;

import java.io.PrintStream;
import java.time.LocalTime;

import org.apache.tomcat.jni.Local;

import com.hit.model.Activity;

public class InputValidator {
	private PrintStream printWriter;

	
	//To finish
	public boolean inputValidation(Activity activity) {
		boolean isValid = false;
		if (activity) {
			System.out.printf("%s %s: Valid input.", getTime(), getMethodName());
			isValid = true;
		} else {
			System.out.printf("%s %s: Wrong input!", getTime(), getMethodName());
			printWriter.println("Wrong input.");
		}

		return isValid;
	}
	
	//To finish
	public boolean usernameValidation(String username) {
		boolean isValid = false;
		if (username) {
			System.out.printf("%s %s: Valid input.", getTime(), getMethodName());
			isValid = true;
		} else {
			System.out.printf("%s %s: Wrong input!", getTime(), getMethodName());
			printWriter.println("Wrong input.");
		}

		return isValid;
	}
	
	//To finish
	public boolean passwordValidation(String password) {
		boolean isValid = false;
		if (password) {
			System.out.printf("%s %s: Valid input.", getTime(), getMethodName());
			isValid = true;
		} else {
			System.out.printf("%s %s: Wrong input!", getTime(), getMethodName());
			printWriter.println("Wrong input.");
		}

		return isValid;
	}
	
	private String getTime() {
		return LocalTime.now().toString();
	}

	private String getMethodName() {
		return Local.class.getEnclosingMethod().getName();
	}

}
