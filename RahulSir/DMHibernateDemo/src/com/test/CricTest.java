package com.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.dto.Actor;
import com.dto.Cars;
import com.dto.Crickter;
import com.dto.Singer;

public class CricTest {
	
	public static void add() {
		Crickter msd=new Crickter();
		
		msd.setName("MSDhoni");
		msd.setNoOfRuns(10000);
		msd.setNoOfmatchs(223);
		
		Singer sing=new Singer();
		
		sing.setNoOfSongs(5);
		msd.setSinger(sing);  //association
		
		Actor act=new Actor();
		
		act.setNoOfAds(25);
		msd.setActor(act);//o2o
		
		Cars bmw=new Cars();
		bmw.setName("X1");
		
		Cars audi=new Cars();
		audi.setName("A4");
		
		Cars alto=new Cars();
		alto.setName("alto");
		
		List<Cars> cars=new ArrayList<Cars>();
		cars.add(alto);
		cars.add(audi);
		cars.add(bmw);
		msd.setList(cars);//o2m		
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		long l=(Long)session.save(msd);
		System.out.println(l);
		tx.commit();
		session.close();
		
		/*System.out.println(msd.getSinger().getNoOfSongs());//multiplicity
		System.out.println(msd.getActor().getNoOfAds());
		
		Crickter nehra=new Crickter();
		nehra.setJersyNo(16);
		nehra.setName("Ashish Nehra");
		nehra.setNoOfRuns(120);
		nehra.setNoOfmatchs(210);				
		System.out.println(nehra.getSinger().getNoOfSongs());	*/
		
	}
	
	public static Crickter get(){
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		Crickter crickter=(Crickter)session.get(Crickter.class, 1L);
		crickter.getList().size();
		session.close();
		return crickter;
	}
	
	public static void testGet(){
		Crickter crickter=get();
		System.out.println(crickter.getJersyNo()+" "+crickter.getName());
		List<Cars>list=crickter.getList();
		Cars cars=list.get(0);
		System.out.println(cars.getName());
	}
	
	public static List<Crickter> list(){
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		Criteria criteria=session.createCriteria(Crickter.class);
		criteria.createAlias("actor", "a");
		criteria.add(Restrictions.eq("noOfAds", 25));
		List<Crickter>	list=criteria.list();
		session.close();
		return list;
	}
	
	public static void testList(){
		List<Crickter>crickters=list();
		Iterator iterator=crickters.iterator();
		while(iterator.hasNext()){
			Crickter crickter=(Crickter)iterator.next();
			System.out.println(crickter.getJersyNo()+" "+crickter.getName());
		}
	}
	public static void main(String[] args) {
		testList();
	}
}
