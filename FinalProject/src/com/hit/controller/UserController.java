package com.hit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hit.model.HibernateGymDAO;
import com.hit.model.User;
import com.hit.model.UserDBException;

public class UserController {

	public void register(HttpServletRequest request, HttpServletResponse response, String str) throws IOException {
		/*
		 * if (str != null) { response.getWriter().append(str);
		 * 
		 * }
		 */
		PrintWriter writer = response.getWriter();

		String id = request.getParameter("userId");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		if (id.chars().allMatch(Character::isDigit) && userName != "" && password != "") {
			Integer userId = Integer.parseInt(id);

			// writer.println(userId);
			// writer.println(userName);
			// writer.println(password);

			HibernateGymDAO dao = HibernateGymDAO.getInstance();

			try {
				User user = dao.getUser(userId);
				if (user == null) {
					if (dao.addUser(new User(userId, userName, password))) {
						// return success

						writer.println("registration completed successfully");
					} else {
						// return action fail
						writer.println("registration fail");
					}
				} else {
					// return that user id is taken
					writer.println("this id is already taken!");
				}
			} catch (UserDBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			writer.println("Worng input");
		}

	}

	public void login(HttpServletRequest request, HttpServletResponse response, String str) throws IOException {

		PrintWriter writer = response.getWriter();

		String id = request.getParameter("userId");
		String password = request.getParameter("password");
		if (id.chars().allMatch(Character::isDigit) && password != "") {
			Integer userId = Integer.parseInt(id);

			// writer.println(userId);
			// writer.println(password);

			HibernateGymDAO dao = HibernateGymDAO.getInstance();

			try {
				User user = dao.getUser(userId);

				if (user != null) {
					if (user.getPassword().equals(password)) {
						HttpSession session = request.getSession();
						session.setAttribute("id", userId);
						session.setMaxInactiveInterval(300); // 5 min timeout - 300 sec
						writer.println("Login successfully");
					} else {
						writer.println("incorrect password");
					}
				} else {
					writer.println("User dosen't exist");
				}
			} catch (UserDBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			writer.println("Worng input");
		}
	}

	public void logout(HttpServletRequest request, HttpServletResponse response, String str) throws IOException {

		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();

		if (session.getAttribute("id") != null) { // check if user connected to the system
			session.invalidate();
			writer.println("Logout successfully");
		} else {
			writer.println("you are not connected!");
		}
	}

	public void deleteUser(HttpServletRequest request, HttpServletResponse response, String str) throws IOException {

		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();

		if (session.getAttribute("id") != null) { // check if user connected to the system
			Integer id = (Integer) session.getAttribute("id");
			
			HibernateGymDAO dao = HibernateGymDAO.getInstance();
			try {
				dao.deleteUser(id);
			} catch (UserDBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			session.invalidate();
			writer.println("The user was deleted successfully");
			
		} else {
			writer.println("you are not connected!");
		}
	}
}
