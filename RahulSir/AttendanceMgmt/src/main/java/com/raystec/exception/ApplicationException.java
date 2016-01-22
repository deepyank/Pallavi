package com.raystec.exception;

public class ApplicationException extends Exception{
	/**
	 * ApplicationException is propogated from Service classes when an business
	 * logic exception occurered.
	 * 
	 * @author Priynak
	 * @version 1.0
	 * @Copyright (c) Priyank
	 * 
	 */
	public ApplicationException(String msg) {		

	    /**
	     * @param msg
	     *            : Error message
	     */
		super(msg);
	}
}
