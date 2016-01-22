package com.raystec.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.raystec.bean.UserBean;
import com.raystec.exception.ApplicationException;
import com.raystec.exception.DuplicateRecordException;
import com.raystec.model.UserModel;

/**
 * User Model Test classes
 * 
 * @author Priyank
 * @version 1.0
 * @Copyright (c) Priyank
 * 
 */
public class UserModelTest {
	/**
     * Model object to test
     */
	private static UserModel model=new UserModel();
	
	/**
     * Tests add a User
	 * @throws ApplicationException 
     * 
     * @throws ParseException
     * @throws DuplicateRecordException
     */
	public static void testAdd() {
		try{
		UserBean bean=new UserBean();
		SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yy");
		
		bean.setFirstName("Amar");
		bean.setLastName("Singh");
		bean.setLogin("amar@gmail.com");
		bean.setPassword("abc123");
		bean.setConfirmPassword("abc123");
		bean.setDob(sdf.parse("10-07-1988"));
		bean.setRoleId(1L);
		bean.setLock("Yes");
		bean.setGender("Male");
		bean.setCreatedBy("priyank");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setMobileNo("97710189332");
		bean.setModifiedBy("priyank");
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		long pk=model.add(bean);
		UserBean addBean=model.findByPK(pk);
		if(addBean==null){
			System.out.println("Test Add fail");
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	 /**
     * Main method to call test methods.
     * 
     * @param args
     * @throws ParseException
     * @throws DuplicateRecordException
     */
	public static void main(String[] args) {
		testAdd();
}
}
