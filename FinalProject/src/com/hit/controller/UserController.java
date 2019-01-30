package com.hit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.Local;

import com.hit.model.HibernateGymDAO;
import com.hit.model.User;
import com.hit.model.UserDBException;

public class UserController {
	private String userName;
	private String password;
	private PrintWriter printWriter;
	private HibernateGymDAO hibernateGymDAO;
	private User user;

	public UserController() {
		this.userName = null;
		this.password = null;
		this.printWriter = null;
		this.hibernateGymDAO = HibernateGymDAO.getInstance();
		this.user = null;
	}

	public void register(HttpServletRequest request, HttpServletResponse response) {
		printWriter = response.getWriter();
		userName = request.getParameter("username");
		password = request.getParameter("password");
		System.out.printf("%s %s: Register user - userName = %s, password = %s", getTime(), getMethodName(), userName,
				password);

		if (inputValidation()) {
			try {
				user = hibernateGymDAO.getUser(userName);

				if (user == null) {
					System.out.printf("%s %s: User does not exist at DB. Going to create new one...", getTime(),
							getMethodName());
					if (hibernateGymDAO.addUser(new User(userName, password))) {
						System.out.printf("%s %s: User added succesfully.", getTime(), getMethodName());
						printWriter.println("Registration completed successfully");
					} else {
						System.out.printf("%s %s: User was not added!", getTime(), getMethodName());
						printWriter.println("Registration failed");
					}
				} else {
					System.out.printf("%s %s: Username already taken.", getTime(), getMethodName());
					printWriter.println("Username already taken, Please choose another one.");
				}
			} catch (UserDBException userDBException) {
				System.out.printf("%s %s: Error! UserDBException!", getTime(), getMethodName());
				userDBException.printStackTrace();
			} catch (IOException ioException) {
				System.out.printf("%s %s: Error! IOException!", getTime(), getMethodName());
			} catch (Exception exception) {
				System.out.printf("%s %s: Error! unknown Exception!", getTime(), getMethodName());
				exception.printStackTrace();
			} finally {
				printWriter.close();
			}
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response) {
		printWriter = response.getWriter();
		userName = request.getParameter("username");
		password = request.getParameter("password");
		System.out.printf("%s %s: Login user - userName = %s, password = %s", getTime(), getMethodName(), userName,
				password);

		if (inputValidation()) {
			try {
				user = hibernateGymDAO.getUser(userName);

				if (user != null) {
					System.out.printf("%s %s: User exist, going to check password.", getTime(), getMethodName());
					if (user.getPassword().equals(password)) {
						System.out.printf("%s %s: password correct, going to login...", getTime(), getMethodName());
						HttpSession session = request.getSession();
						session.setAttribute("username", userName);
						session.setMaxInactiveInterval(300); // 5 min timeout - 300 sec
						printWriter.println("Login successfully");
						System.out.printf("%s %s: Login successfully", getTime(), getMethodName());
					} else {
						System.out.printf("%s %s: password is not correct!", getTime(), getMethodName());
						printWriter.println("Wrong password, please retry");
					}
				} else {
					System.out.printf("%s %s: User does not exist at DB.", getTime(), getMethodName());
					printWriter.println("User dosen't exist");
				}
			} catch (UserDBException userDBException) {
				System.out.printf("%s %s: Error! UserDBException!", getTime(), getMethodName());
				userDBException.printStackTrace();
			} catch (IOException ioException) {
				System.out.printf("%s %s: Error! IOException!", getTime(), getMethodName());
			} catch (Exception exception) {
				System.out.printf("%s %s: Error! unknown Exception!", getTime(), getMethodName());
				exception.printStackTrace();
			} finally {
				printWriter.close();
			}
		}
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		try {
			printWriter = response.getWriter();
			if (session.getAttribute("userName") != null) { // check if user connected to the system
				System.out.printf("%s %s: User was connected", getTime(), getMethodName());
				session.invalidate();
				System.out.printf("%s %s: User logout", getTime(), getMethodName());
				printWriter.println("Logout successfully");
			} else {
				System.out.printf("%s %s: User was NOT connected", getTime(), getMethodName());
				printWriter.println("you are not connected!");
			}
		} catch (IOException ioException) {
			System.out.printf("%s %s: Error! IOException!", getTime(), getMethodName());
		} catch (Exception exception) {
			System.out.printf("%s %s: Error! unknown Exception!", getTime(), getMethodName());
			exception.printStackTrace();
		} finally {
			printWriter.close();
		}
	}

	public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			PrintWriter writer = response.getWriter();
			if (session.getAttribute("username") != null) {
				System.out.printf("%s %s: User was connected, going to delete", getTime(), getMethodName());
				hibernateGymDAO.deleteUser(userName);
				session.invalidate();
				System.out.printf("%s %s: User was successfully deleted", getTime(), getMethodName());
				writer.println("The user was deleted successfully");

			} else {
				System.out.printf("%s %s: User was NOT connected", getTime(), getMethodName());
				writer.println("you are not connected!");
			}
		} catch (UserDBException userDBException) {
			System.out.printf("%s %s: Error! UserDBException!", getTime(), getMethodName());
			userDBException.printStackTrace();
		} catch (IOException ioException) {
			System.out.printf("%s %s: Error! IOException!", getTime(), getMethodName());
		} catch (Exception exception) {
			System.out.printf("%s %s: Error! unknown Exception!", getTime(), getMethodName());
			exception.printStackTrace();
		} finally {
			printWriter.close();
		}
	}

	private String getTime() {
		return LocalTime.now().toString();
	}

	private String getMethodName() {
		return Local.class.getEnclosingMethod().getName();
	}

	private Boolean inputValidation(boolean function) {
		boolean isValid = false;
		if (function) {
			System.out.printf("%s %s: Valid input.", getTime(), getMethodName());
			isValid = true;
		} else {
			System.out.printf("%s %s: Wrong input!", getTime(), getMethodName());
			printWriter.println("Wrong input.");
		}

		return isValid;
	}
}
