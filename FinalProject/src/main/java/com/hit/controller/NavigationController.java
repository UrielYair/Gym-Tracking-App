package com.hit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hit.utils.InputValidator;

public class NavigationController {
	private static final Logger LOGGER = Logger.getLogger(NavigationController.class.getSimpleName());
	private final String[] legalButtons = { "login", "register", "acountManagement", "activities", "addActivity",
			"deleteActivity", "updateActivity" };

	private InputValidator inputValidator;
	private String buttonValue;

	public NavigationController() {
		this.inputValidator = new InputValidator();
	}

	public void goTo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String str) {
		boolean illegalButton = false;
		try {
			buttonValue = httpServletRequest.getParameter("button");
			if (inputValidator.buttonValidator(legalButtons, buttonValue)) {
				LOGGER.info(buttonValue + "button has been pressed");
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/" + buttonValue + ".jsp");
			} else {
				LOGGER.info("button was not pressed");
			}
		} catch (Exception exception) {
			LOGGER.fatal("Unknown message");
		} finally {
		}
	}

}
