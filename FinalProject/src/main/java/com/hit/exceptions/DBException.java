package com.hit.exceptions;

public class DBException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DBException(String msg)
	{
		super(msg);
	}
	
	public DBException(String msg, Throwable throwable)
	{
		super(msg,throwable);
	}
}
