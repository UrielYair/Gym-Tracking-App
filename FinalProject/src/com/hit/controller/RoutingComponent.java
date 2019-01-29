package com.hit.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RoutingComponent
 */
@WebServlet("/controller/*")
public class RoutingComponent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RoutingComponent() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		String path = request.getPathInfo();
		// response.getWriter().append(path);

		String[] parts = path.split("/");
		if (parts.length<3) {
			response.getWriter().append("ERROR: Wrong URL");
			return;
		}
	
		String controllerName = parts[1];
		String actionName = parts[2];
		String strAfterAction = null;
		if (parts.length >= 4) {
			strAfterAction = path.substring(parts[1].length()+parts[2].length()+2);
		}
		//response.getWriter().append(controllerName + " " + actionName);
		try {
			Class controllerClass = Class.forName("com.hit.controller."+controllerName+"Controller");
			Object controller = controllerClass.newInstance();
			Method method = controllerClass.getMethod(actionName, HttpServletRequest.class, HttpServletResponse.class, String.class);
			method.invoke(controller, request, response, strAfterAction);
				
				
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
