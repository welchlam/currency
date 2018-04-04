package com.welch.exception;

@SuppressWarnings("serial")
public class CriticalException extends Exception {

	public CriticalException(String msg)
	{
		super("CRITICAL ERROR : " + msg + ", exiting with a status of 1");
	}
}
