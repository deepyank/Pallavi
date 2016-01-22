package com.raystec.utility;

import java.util.Date;

/**
 * This class validates input data
 * 
 * @author priyank
 * @version 1.0
 * @Copyright (c) priyank
 */
public class DataValidator {
	/**
	 * Checks if value is Null
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNull(String val) {
		if (val == null || val.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is NOT Null
	 * 
	 * @param val
	 * @return
	 */

	public static boolean isNotNull(String val) {
		return !isNull(val);
	}

	/**
	 * Checks if value is an Integer
	 * 
	 * @param val
	 * @return
	 */

	public static boolean isInteger(String val) {
		if (isNotNull(val)) {
			try {
				int i = Integer.parseInt(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Long
	 * 
	 * @param val
	 * @return
	 */

	public static boolean isLong(String val) {
		if (isNotNull(val)) {
			try {
				long i = Long.parseLong(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid Email ID
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isEmail(String val) {
		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);
			} catch (NumberFormatException e) {
				// TODO: handle exception
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Date
	 * 
	 * @param val
	 * @return
	 */

	public static boolean isDate(String val) {
		Date d = null;
		if (isNotNull(val)) {
			d = DataUtility.getDate(val);
		}
		return d != null;
	}

	// validate first name
	public static boolean isFirstName(String val) {
		if (isNotNull(val)) {
			try {
				return val.matches("[A-Z][a-zA-Z]*");
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	// validate last name
	public static boolean isLastName(String val) {
		if (isNotNull(val)) {
			try {
				return val.matches("[a-zA-z]+([ '-][a-zA-Z]+)*");
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Test above methods
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Not Null 2 " + isNotNull("ABC"));
		System.out.println("Not Null 3 " + isNotNull(null));
		System.out.println("Not Null 4" + isNull("123"));			
	}
}