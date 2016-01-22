package com.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.dto.Address;
import com.dto.Student;

public class StudentTest {

	public static void testAdd(){
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		
		Student student=new Student();
		student.setStudentName("Priyank");
		
		Address address=new Address();
		address.setCity("Indore");
		address.setState("MP");
		address.setStudent(student);
		session.save(address);
		tx.commit();
		session.close();
		System.out.println("testAdd successful");
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testAdd();
	}
}
