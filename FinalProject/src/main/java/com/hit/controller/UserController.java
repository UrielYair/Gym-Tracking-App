package com.hit.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hit.dao.HibernateGymDAO;
import com.hit.model.User;
import com.hit.utils.InputValidator;

public class UserController {
	/**
	 * The class will be responsible for receiving information regarding users in
	 * the system. The class will forward the information to the Model classes which
	 * handle them by needs.
	 **/

	private static final Logger LOGGER = Logger.getLogger(UserController.class.getSimpleName());
	
	private InputValidator inputValidator;

	public UserController() {
		this.inputValidator = new InputValidator();
	}

	public void register(HttpServletRequest request, HttpServletResponse response, String str) {
		/**
		 * Method for registering users in the system. Existence check will be taken
		 * prior registration.
		 */

		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		LOGGER.info("Register request for new user: username = " + userName + "password = " + password);
		try {
			if (!inputValidator.usernameValidation(userName) || !inputValidator.passwordValidation(password)) {
				LOGGER.info("Input is not valid");
				request.setAttribute("message", "Input is not valid");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			} else {

				LOGGER.info("Input is valid");

				User user = HibernateGymDAO.getInstance().getUser(userName);
				if (user == null) {

					LOGGER.info("User does not exist at DB. Going to create new one...");

					if (HibernateGymDAO.getInstance().addUser(new User(userName, password))) {
						LOGGER.info("User added succesfully");
						request.setAttribute("message", "Registration completed successfully");
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					} else {
						LOGGER.info("User was not added");
						request.setAttribute("message", "rgistration failed");
						request.getRequestDispatcher("/register.jsp").forward(request, response);
					}
				} else {
					LOGGER.info("Username already taken");
					request.setAttribute("message", "Username already taken, Please choose another one");
					request.getRequestDispatcher("/register.jsp").forward(request, response);
				}
			}
		}

		catch (IOException ioException) {
			LOGGER.fatal("Error, IOException!");
			ioException.printStackTrace();
		} catch (Exception exception) {
			LOGGER.fatal("Error, unknown Exception!");
			exception.printStackTrace();
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response, String str) {
		/**
		 * Method is used to verify user while trying to login,
		 * and add userName attribute to the session.
		 */

		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		LOGGER.info("Login user - userName = " + userName + " password = " + password);

		try {
			HttpSession session = request.getSession();
			
			if (!inputValidator.usernameValidation(userName) || !inputValidator.passwordValidation(password)) {
				LOGGER.info("Input is not valid");
				request.setAttribute("message", "Input is not valid");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			} else {

				User user = HibernateGymDAO.getInstance().getUser(userName);

				if (user != null) {
					
					if (user.getPassword().equals(password)) {
						
						session.setAttribute("userName", userName);
						session.setMaxInactiveInterval(300); // 5 min timeout - 300 sec

						LOGGER.info(userName + " Logged on successfully.");
						request.setAttribute("message", "Login successfully");
						request.getRequestDispatcher("/home.jsp").forward(request, response);
					} else {
						LOGGER.info(userName + " entered wrong password.");
						request.setAttribute("message", "Wrong password, please retry");
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					}
				}

				else {
					LOGGER.debug("Loggin attempt by not existing user: " + userName);
					request.setAttribute("message", "User dosen't exist, please register before login");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			}
		} catch (IOException ioException) {
			LOGGER.fatal("Error, IOException!");
			ioException.printStackTrace();
		} catch (Exception exception) {
			LOGGER.fatal("Error, unknown Exception!");
			exception.printStackTrace();
		}
	}

	public void logout(HttpServletRequest request, HttpServletResponse response, String str) {
		/**
		 * Method is used to clear the session while logout
		 */
		
		HttpSession session = request.getSession();

		try {

			if (session.getAttribute("userName") != null) { 
				session.invalidate();
				LOGGER.info("Logout successfully"); 
				request.setAttribute("message", "Logout successfully");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			} else {
				LOGGER.info("you are not connected!");
				request.setAttribute("message", "You are not connected");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}

		catch (IOException ioException) {
			LOGGER.fatal("Error, IOException!");
			ioException.printStackTrace();
		} catch (Exception exception) {
			LOGGER.fatal("Error, unknown Exception!");
			exception.printStackTrace();
		}
	}

	public void deleteUser(HttpServletRequest request, HttpServletResponse response, String str) {

		HttpSession session = request.getSession();

		try {
			if (session.getAttribute("userName") != null) {
				String userName = (String) session.getAttribute("userName");
				HibernateGymDAO.getInstance().deleteUser(userName);
				session.invalidate();
				LOGGER.info("The user was deleted successfully");
				request.setAttribute("message", "The user was deleted successfully");
				request.getRequestDispatcher("/index.jsp").forward(request, response);

			} else {
				LOGGER.info("user is not connected!");
				request.setAttribute("message", "You are not connected");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}

		}

		catch (IOException ioException) {
			LOGGER.fatal("Error, IOException!");
			ioException.printStackTrace();
		} catch (Exception exception) {
			LOGGER.fatal("Error, unknown Exception!");
			exception.printStackTrace();
		}
	}
}
