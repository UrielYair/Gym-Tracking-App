package com.hit.model;

public interface IGymDAO {

	public boolean activityExist(Activity activity);
	public boolean addActivity(Activity activity);
	public boolean deleteActivity(String userName, String activityName);
	public boolean updateActivity(Activity activity);
	public Activity getActivity(String activityName);
	
	public boolean userExist(User user);
	public boolean addUser(User user);
	public User getUser(String id);
	public boolean deleteUser(String userName);
}
