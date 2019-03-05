package com.hit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		try {
			HttpSession session = httpServletRequest.getSession();
			buttonValue = httpServletRequest.getParameter("button");
			if (!buttonValue.equals("login") && !buttonValue.equals("register")) {
				if (session.getAttribute("userName") != null) {
					if (inputValidator.buttonValidator(legalButtons, buttonValue)) {
						LOGGER.info(buttonValue + "button has been pressed");
						httpServletResponse
								.sendRedirect(httpServletRequest.getContextPath() + "/" + buttonValue + ".jsp");
					} else {
						LOGGER.info("button was not pressed");
					}
				} else {
					LOGGER.info("you are not connected!"); // if user is not connected he should not reach logout button
					// printWriter.println("you are not connected!");
				}
			} else {
				if (inputValidator.buttonValidator(legalButtons, buttonValue)) {
					LOGGER.info(buttonValue + "button has been pressed");
					httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/" + buttonValue + ".jsp");
				} else {
					LOGGER.info("button was not pressed");
				}
			}
		} catch (Exception exception) {
			LOGGER.fatal("Unknown message");
		} finally {
		}
	}

}
