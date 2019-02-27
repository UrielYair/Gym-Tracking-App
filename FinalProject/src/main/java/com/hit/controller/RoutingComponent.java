package com.hit.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class RoutingComponent
 */

@WebServlet("/controller/*")
public class RoutingComponent extends HttpServlet {

	/*
	 * * Responsible for routing requests to the appropriate controller class based
	 * on the MVC design pattern. The algorithm will make decisions by the URL path
	 * using split on the '/' delimiter.
	 */

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(RoutingComponent.class.getSimpleName());

	// TODO: uncomment if needed:

	// private String path;
	// private String[] parts;
	// private String controllerName;
	// private String actionName;
	// private String strAfterAction;

	public RoutingComponent() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		String path = request.getPathInfo();
		LOGGER.info("Message from: " + path);
		String[] parts = path.split("/");

		try {

			if (parts.length >= 3) {
				LOGGER.info("URL contain exactly 3 parts separated by '/'");
				String controllerName = parts[1];
				String actionName = parts[2];

				String strAfterAction = null;
				if (parts.length >= 4) {
					strAfterAction = path.substring(parts[1].length() + parts[2].length() + 2);
				}

				
				Class<?> controllerClass = Class.forName("com.hit.controller." + controllerName + "Controller");
				Object controller = controllerClass.newInstance();
				Method method = controllerClass.getMethod(actionName, HttpServletRequest.class,
						HttpServletResponse.class, String.class);
				method.invoke(controller, request, response, strAfterAction);
			}

			else {
				// Invalid URL: URL can not contain other than 3 parts.
				LOGGER.info("Invalid URL");
				response.getWriter().append("ERROR: Invalid URL");
			}
		} catch (IllegalAccessException illegalAccessException) {
			LOGGER.fatal("Error, illegalAccessException");
			illegalAccessException.printStackTrace();
		} catch (InvocationTargetException invocationTargetException) {
			LOGGER.fatal("Error, InvocationTargetExceptio");
			invocationTargetException.printStackTrace();
		} catch (IllegalArgumentException illegalArgumentException) {
			LOGGER.fatal("Error, IllegalArgumentException");
			illegalArgumentException.printStackTrace();
		} catch (IOException ioException) {
			LOGGER.fatal("Error, IOException");
			ioException.printStackTrace();
		} catch (Exception exception) {
			LOGGER.fatal("Error, unknown Exception");
			exception.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Post request, do get");
		doGet(request, response);
	}

}
