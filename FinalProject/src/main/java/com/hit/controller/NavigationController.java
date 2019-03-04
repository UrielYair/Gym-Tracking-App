package com.hit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class NavigationController {
	private static final Logger LOGGER = Logger.getLogger(NavigationController.class.getSimpleName());
	private PrintWriter printWriter;


	public void goToLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String str) {
		try {
			this.printWriter = httpServletResponse.getWriter();
			LOGGER.info("Redirect to Login page");
			httpServletRequest.getRequestDispatcher("/login.jsp").forward(httpServletRequest, httpServletResponse);
		} catch (IOException ioException) {
			LOGGER.fatal("IOExseprion");
			printWriter.println("IOExseprion");
		}catch(ServletException servletException) {
			LOGGER.fatal("ServletException");
			printWriter.println("ServletException");
		}catch(Exception exception) {
			LOGGER.fatal("Unknown exception");
			printWriter.println("Unknown exception");
		} finally {
			printWriter = null;
		}
	}

	public void goToRegister(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String str) {
		try {
			this.printWriter = httpServletResponse.getWriter();
			LOGGER.info("Redirect to Login page");
			httpServletRequest.getRequestDispatcher("/register.jsp").forward(httpServletRequest, httpServletResponse);
		} catch (IOException ioException) {
			LOGGER.fatal("IOExseprion");
			printWriter.println("IOExseprion");
		}catch(ServletException servletException) {
			LOGGER.fatal("ServletException");
			printWriter.println("ServletException");
		}catch(Exception exception) {
			LOGGER.fatal("Unknown exception");
			printWriter.println("Unknown exception");
		} finally {
			printWriter = null;
		}
	}
	
	public void goToHome(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String str) {
		try {
			this.printWriter = httpServletResponse.getWriter();
			LOGGER.info("Redirect to Login page");
			httpServletRequest.getRequestDispatcher("/home.jsp").forward(httpServletRequest, httpServletResponse);
		} catch (IOException ioException) {
			LOGGER.fatal("IOExseprion");
			printWriter.println("IOExseprion");
		}catch(ServletException servletException) {
			LOGGER.fatal("ServletException");
			printWriter.println("ServletException");
		}catch(Exception exception) {
			LOGGER.fatal("Unknown exception");
			printWriter.println("Unknown exception");
		} finally {
			printWriter = null;
		}
	}
}
