package com.hit.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.Local;

/**
 * Servlet implementation class RoutingComponent
 */
@WebServlet("/controller/*")
public class RoutingComponent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String path;
	private String[] parts;
	private String controllerName;
	private String actionName;
	private String strAfterAction;

	public RoutingComponent() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		path = request.getPathInfo();
		// System.out.printf("%s %s: Request from: %s", getTime(), getMethodName(),
		// path);
		parts = path.split("/");
		try {
			if (parts.length > 2) {
				controllerName = parts[1];
				actionName = parts[2];
				strAfterAction = null;
				if (parts.length >= 4) {
					strAfterAction = path.substring(parts[1].length() + parts[2].length() + 2);
					System.out.printf("%s %s: strAfterAction = %s", getTime(), getMethodName(), strAfterAction);
				} else {
					Class controllerClass = Class.forName("com.hit.controller." + controllerName + "Controller");
					Object controller = controllerClass.newInstance();
					Method method = controllerClass.getMethod(actionName, HttpServletRequest.class, HttpServletResponse.class);
					method.invoke(controller, request, response);
				}
			} else {
				response.getWriter().append("ERROR: Wrong URL");
			}
		} catch (IllegalAccessException illegalAccessException) {
			System.out.printf("%s %s: Error, IllegalAccessException", getTime(), getMethodName());
			illegalAccessException.printStackTrace();
		} catch (InvocationTargetException invocationTargetException) {
			System.out.printf("%s %s: Error, InvocationTargetException", getTime(), getMethodName());
			invocationTargetException.printStackTrace();
		} catch (IllegalArgumentException illegalArgumentException) {
			System.out.printf("%s %s: Error, IllegalArgumentException", getTime(), getMethodName());
			illegalArgumentException.printStackTrace();
		} catch (IOException ioException) {
			System.out.printf("%s %s: Error, IOException", getTime(), getMethodName());
			ioException.printStackTrace();
		} catch (Exception exception) {
			System.out.printf("%s %s: Error, Exception", getTime(), getMethodName());
			exception.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

	private String getTime() {
		return LocalTime.now().toString();
	}

	private String getMethodName() {
		return Local.class.getEnclosingMethod().getName();
	}

}
