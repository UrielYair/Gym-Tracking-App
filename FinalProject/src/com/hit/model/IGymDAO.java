package com.hit.model;

public interface IGymDAO {

	public boolean activityExist(Activity activity) throws ActivityDBException;
	public boolean addActivity(Activity activity) throws ActivityDBException;
	public boolean deleteActivity(Integer id, String name) throws ActivityDBException;
	public boolean updateActivity(Activity activity) throws ActivityDBException;
	public Activity getActivity(Integer id, String name) throws ActivityDBException;
	
	public boolean userExist(User user) throws UserDBException;
	public boolean addUser(User user) throws UserDBException;
	public User getUser(Integer id) throws UserDBException;
	public boolean deleteUser(Integer id) throws UserDBException;
}
