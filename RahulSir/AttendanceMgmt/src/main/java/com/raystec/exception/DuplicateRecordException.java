package com.raystec.exception;

/**
 * DuplicateRecordException thrown when a duplicate record occurred
 * 
 * @author Priyank
 * @version 1.0
 * @Copyright (c) Priyank
 * 
 */
public class DuplicateRecordException extends Exception {
	/**
     * @param msg
     *            error message
     */
	public DuplicateRecordException(String msg) {
		super(msg);
	}
}
