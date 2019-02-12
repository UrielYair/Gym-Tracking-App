package com.hit.exceptions;

public class UserDBException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserDBException(String msg)
	{
		super(msg);
	}
	
	public UserDBException(String msg, Throwable throwable)
	{
		super(msg,throwable);
	}
}
