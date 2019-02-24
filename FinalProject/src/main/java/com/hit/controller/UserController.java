package com.hit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hit.dao.HibernateGymDAO;
import com.hit.model.User;
import com.hit.utils.InputValidator;

public class UserController{
	private static final Logger LOGGER = Logger.getLogger(UserController.class.getSimpleName());
	/**
	 * The class will be responsible for receiving information regarding users in the system.
	 * The class will forward the information to the Model classes which handle them by needs.
	 **/

	private InputValidator inputValidator;

	public UserController() {
		this.inputValidator = new InputValidator();
	}

	public void register(HttpServletRequest request, HttpServletResponse response) {
		/**
		 * Method for registering users in the system.
		 * Existence check will be taken prior registration.
		 * */
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		LOGGER.info("Register request for new user: username = " + userName + "password = " + password);
		
		if (!inputValidator.usernameValidation(userName) || !inputValidator.passwordValidation(password)) {
			LOGGER.info("Input is not valid");
			return;
		}
		
		LOGGER.info("Input is valid");
		PrintWriter printWriter = null;
		
		try {
			
			printWriter = response.getWriter();
			User user = HibernateGymDAO.getInstance().getUser(userName);
			if (user == null) {
				
				LOGGER.info("User does not exist at DB. Going to create new one...");
				
				if (HibernateGymDAO.getInstance().addUser(new User(userName, password))) {
					LOGGER.info("User added succesfully");
					printWriter.println("Registration completed successfully");
				} 
				else {
					LOGGER.info("User was not added");
					printWriter.println("Registration failed");
				}
			} 
			else {
				LOGGER.info("Username already taken");
				printWriter.println("Username already taken, Please choose another one.");
			}
		} 
		
		catch (IOException ioException) {
			LOGGER.fatal("Error, IOException!");
			ioException.printStackTrace();
		} 
		catch (Exception exception) {
			LOGGER.fatal("Error, unknown Exception!");
			exception.printStackTrace();
		} 
		
		finally {
			printWriter.close();
		}
	} 
	
	public void login(HttpServletRequest request, HttpServletResponse response) {
		/**
		 * Method is used to verify user while trying to login
		 * */
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		LOGGER.info("Login user - userName = " + userName + " password = " + password);
		PrintWriter printWriter = null;
		
		try {

			if (!inputValidator.usernameValidation(userName) || !inputValidator.passwordValidation(password)) {
				LOGGER.info("Input is not valid");
				return;
			}
				
			printWriter = response.getWriter();
			User user = HibernateGymDAO.getInstance().getUser(userName);
			
			if (user != null) {
				
				if (user.getPassword().equals(password)) {
					LOGGER.info(userName + " Logged on successfully.");
					printWriter.println("Login successfully");
				} 
				else {
					LOGGER.info(userName + " entered wrong password.");
					printWriter.println("Wrong password, please retry");
				}
			} 
			
			else {
				LOGGER.debug("Loggin attempt by not existing user: " + userName);
				printWriter.println("User dosen't exist, please register before login");
			}
			 
		} catch (IOException ioException) {
			LOGGER.fatal("Error, IOException!");
			ioException.printStackTrace();
		} catch (Exception exception) {
			LOGGER.fatal("Error, unknown Exception!");
			exception.printStackTrace();
		} finally {
			printWriter.close();
		}
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		PrintWriter printWriter = null;
		
		try {
			printWriter = response.getWriter();
			
			if (session.getAttribute("userName") != null) { // TODO: implement checking if user connected to the system here.
				session.invalidate();
				LOGGER.info("Logout successfully"); // TODO: fix string.
				printWriter.println("Logout successfully");
			} 
			else {
				LOGGER.info("you are not connected!");
				printWriter.println("you are not connected!");
			}
		} 
		
		catch (IOException ioException) {
			LOGGER.fatal("Error, IOException!");
			ioException.printStackTrace();
		} 
		catch (Exception exception) {
			LOGGER.fatal("Error, unknown Exception!");
			exception.printStackTrace();
		}
		
		finally {
			printWriter.close();
		}
	}

	public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		PrintWriter printWriter =null;
		
		try {
			printWriter = response.getWriter();
			if (session.getAttribute("username") != null) {
				HibernateGymDAO.getInstance().deleteUser(userName); // TODO: check where should userName should come from. 
				session.invalidate();
				printWriter.println("The user was deleted successfully");

			} 
			else {
				printWriter.println("you are not connected!");
			}
			
		} 
		
		catch (IOException ioException) {
			LOGGER.fatal("Error, IOException!");
			ioException.printStackTrace();
		} 
		catch (Exception exception) {
			LOGGER.fatal("Error, unknown Exception!");
			exception.printStackTrace();
		} 
		
		finally {
			LOGGER.fatal("Going to close writer");
			printWriter.close();
		}
	}
}
