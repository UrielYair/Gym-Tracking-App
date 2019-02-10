package com.hit.model;

public class ActivityDBException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ActivityDBException(String msg)
	{
		super(msg);
	}
	
	public ActivityDBException(String msg, Throwable throwable)
	{
		super(msg,throwable);
	}
}
