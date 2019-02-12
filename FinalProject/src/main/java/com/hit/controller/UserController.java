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

	private String userName;
	private String password;
	private PrintWriter printWriter;
	private HibernateGymDAO hibernateGymDAO;
	private User user;
	private InputValidator inputValidator;

	public UserController() {
		LOGGER.debug("Creating userController instance");
		this.userName = null;
		this.password = null;
		this.printWriter = null;
		this.inputValidator = new InputValidator();
		this.hibernateGymDAO = HibernateGymDAO.getInstance();
		this.user = null;
	}

	public void register(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Register request");
		userName = request.getParameter("username");
		password = request.getParameter("password");
		LOGGER.info("Going to reginster new user: username = " + userName + "password = " + password);
		if (inputValidator.usernameValidation(userName) && inputValidator.passwordValidation(password)) {
			LOGGER.info("Input is valid");
			try {
				printWriter = response.getWriter();
				user = hibernateGymDAO.getUser(userName);
				if (user == null) {
					LOGGER.info("User does not exist at DB. Going to create new one...");
					if (hibernateGymDAO.addUser(new User(userName, password))) {
						LOGGER.info("User added succesfully");
						printWriter.println("Registration completed successfully");
					} else {
						LOGGER.info("User was not added");
						printWriter.println("Registration failed");
					}
				} else {
					LOGGER.info("Username already taken");
					printWriter.println("Username already taken, Please choose another one.");
				}
			} catch (IOException ioException) {
				LOGGER.fatal("Error, IOException!");
				ioException.printStackTrace();
			} catch (Exception exception) {
				LOGGER.fatal("Error, unknown Exception!");
				exception.printStackTrace();
			} finally {
				LOGGER.fatal("Going to close writer");
				printWriter.close();
			}
		} else {
			LOGGER.info("Input is not valid");
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response) {
		userName = request.getParameter("username");
		password = request.getParameter("password");
		LOGGER.info("Login user - userName = " + userName + " password = " + password);
		try {
			if (inputValidator.usernameValidation(userName) && inputValidator.passwordValidation(password)) {
				LOGGER.info("Input is valid");
				printWriter = response.getWriter();
				user = hibernateGymDAO.getUser(userName);
				if (user != null) {
					LOGGER.info("User exist, going to check password");
					if (user.getPassword().equals(password)) {
						LOGGER.info("password correct, going to login...");
						printWriter.println("Login successfully");
						LOGGER.info("Login successfully");
					} else {
						LOGGER.info("password is not correct!");
						//printWriter.println("Wrong password, please retry");
					}
				} else {
					LOGGER.debug("User does not exist at DB");
					printWriter.println("User dosen't exist, please register before login");
				}
			} else {
				LOGGER.info("Input is not valid");
			}
		} catch (IOException ioException) {
			LOGGER.fatal("Error, IOException!");
			ioException.printStackTrace();
		} catch (Exception exception) {
			LOGGER.fatal("Error, unknown Exception!");
			exception.printStackTrace();
		} finally {
			LOGGER.fatal("Going to close writer");
			printWriter.close();
		}
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			printWriter = response.getWriter();
			if (session.getAttribute("userName") != null) { // check if user connected to the system
				session.invalidate();
				LOGGER.info("Loguot successfully");
				printWriter.println("Logout successfully");
			} else {
				LOGGER.info("you are not connected!");
				printWriter.println("you are not connected!");
			}
		} catch (IOException ioException) {
			LOGGER.fatal("Error, IOException!");
			ioException.printStackTrace();
		} catch (Exception exception) {
			LOGGER.fatal("Error, unknown Exception!");
			exception.printStackTrace();
		} finally {
			LOGGER.fatal("Going to close writer");
			printWriter.close();
		}
	}

	public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			PrintWriter writer = response.getWriter();
			if (session.getAttribute("username") != null) {
				hibernateGymDAO.deleteUser(userName);
				session.invalidate();
				writer.println("The user was deleted successfully");

			} else {
				writer.println("you are not connected!");
			}
		} catch (IOException ioException) {
			LOGGER.fatal("Error, IOException!");
			ioException.printStackTrace();
		} catch (Exception exception) {
			LOGGER.fatal("Error, unknown Exception!");
			exception.printStackTrace();
		} finally {
			LOGGER.fatal("Going to close writer");
			printWriter.close();
		}
	}
}
