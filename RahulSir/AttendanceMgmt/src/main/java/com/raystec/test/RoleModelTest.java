package com.raystec.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.raystec.bean.RoleBean;
import com.raystec.exception.ApplicationException;
import com.raystec.exception.DuplicateRecordException;
import com.raystec.model.RoleModel;

public class RoleModelTest {
    /**
     * Model object to test
     */
	public static RoleModel model=new RoleModel();
	
	  /**
     * Main method to call test methods.
     * 
     * @param args
	 * @throws ApplicationException 
     * @throws ParseException
     */
	public static void main(String[] args) throws ApplicationException {
		//testAdd();
		//testFindByName();
		//testUpdate();

	}
	
	 private static void testUpdate() {
		// TODO Auto-generated method stub
		 try {
			RoleBean bean=new RoleBean();
			 long pk=1L;
			 bean.setId(pk);
			 model.delete(bean);
			 RoleBean deleteBean=model.findByPK(pk);
			 if(deleteBean!=null){
				 System.out.println("test delete fail");
			 }
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void testFindByName() throws ApplicationException {
		// TODO Auto-generated method stub
		 RoleBean bean=model.findByName("Priyank");
		 System.out.println(bean);
	}

	/**
     * Tests add a Role
     * 
     * @throws ParseException
     */
 
	private static void testAdd() {
		// TODO Auto-generated method stub
		try{
			RoleBean bean=new RoleBean();
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			//bean.setId(1L);
			bean.setName("Admin");
			bean.setDescription("Created by Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			long pk=model.add(bean);
			RoleBean addBean=model.findByPK(pk);
			if(addBean==null){
				System.out.println("Test add fail");
			}
		}catch(ApplicationException e){
			e.printStackTrace();
		}catch (DuplicateRecordException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
}
