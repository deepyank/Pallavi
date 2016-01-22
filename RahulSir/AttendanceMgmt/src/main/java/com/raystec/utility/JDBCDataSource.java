package com.raystec.utility;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.omg.CORBA.portable.ApplicationException;

import com.mchange.v2.c3p0.ComboPooledDataSource;



/**
 * JDBC DataSource is a Data Connection Pool
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */
public class JDBCDataSource {
	 /**
     * JDBC Database connection pool ( DCP )
     */
	
	private static JDBCDataSource dataSource;
	
	private JDBCDataSource() {
		// TODO Auto-generated constructor stub
	}
	
	private ComboPooledDataSource cpds=null;
	
	 /**
     * Create instance of Connection Pool
     * 
     * @return
     */
	
	public static JDBCDataSource getInstance(){
		if(dataSource==null){
			ResourceBundle rb=ResourceBundle.getBundle("com.raystec.properties.system");
			dataSource=new JDBCDataSource();
			dataSource.cpds=new ComboPooledDataSource();
			try{
				dataSource.cpds.setDriverClass(rb.getString("driver"));
				System.out.println("Test Try");
			}catch(Exception e){
				e.printStackTrace();
			}
			dataSource.cpds.setJdbcUrl(rb.getString("url"));
			dataSource.cpds.setUser(rb.getString("username"));
			dataSource.cpds.setPassword(rb.getString("password"));
			dataSource.cpds.setInitialPoolSize(new Integer((String)rb.getString("initialPoolSize")));
			dataSource.cpds.setAcquireIncrement(new Integer((String)rb.getString("acquireIncrement")));
			dataSource.cpds.setMaxPoolSize(new Integer((String)rb.getString("maxPoolSize")));
			dataSource.cpds.setMinPoolSize(new Integer((String)rb.getString("minPoolSize")));			
		}
		return dataSource;
	}
	
	   /**
     * Gets the connection from ComboPooledDataSource
     * 
     * @return connection
     */
	
	public static Connection getConnection()throws Exception{
		return getInstance().cpds.getConnection();
	}
	
	/**
     * Closes a connection
     * 
     * @param connection
     * @throws Exception
     */
	
	public static void closeConnection(Connection connection){
		if(connection!=null){
			try{
				connection.close();
			}catch(Exception e){}
		}
	}	
}
