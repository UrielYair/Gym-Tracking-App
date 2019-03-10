package com.hit.dao;

import java.util.List;

import com.hit.model.Activity;
import com.hit.model.User;


public interface IGymDAO {

	public boolean activityExist(Activity activity);
	public boolean addActivity(Activity activity);
	public boolean deleteActivity(String userName, String exerciseName, String workoutDate);
	public boolean updateActivity(Activity activity);
	public Activity getActivity(String userName, String exerciseName, String workoutDate);
	
	public List<Activity> getAllUserActivities(String userName);
	
	public boolean userExist(User user);
	public boolean addUser(User user);
	public User getUser(String id);
	public boolean deleteUser(String userName);
}
