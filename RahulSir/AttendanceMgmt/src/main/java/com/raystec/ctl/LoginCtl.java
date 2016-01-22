package com.raystec.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.raystec.utility.DataValidator;
import com.raystec.utility.PropertyReader;

/**
 * Login functionality Controller. Performs operation for Login
 * 
 * @author Priyank
 * @version 1.0
 * @Copyright (c) Priyank
 */
@WebServlet(name="LoginCtl",urlPatterns={"/LoginCtl"})
public class LoginCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_REGISTER = "Register";
	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_LOG_OUT = "logout";
   
	private static Logger log=Logger.getLogger(LoginCtl.class);
	
	
    @Override
	protected boolean validate(HttpServletRequest request) {
		// TODO Auto-generated method stub
    	log.debug("LoginCtl method validate started");
    	boolean pass=true;
    	String op=request.getParameter("operation");
    	if(OP_SIGN_UP.equals(op)|| OP_LOG_OUT.equals(op)){
    		return pass;
    	}
    	
    	String login=request.getParameter("login");
    	if(DataValidator.isNull(login)){
    		request.setAttribute("login", PropertyReader.getValue("error.require","Login ID"));
    		pass=false;
    	}else if(!DataValidator.isEmail(login)){
    		request.setAttribute("error.email", "Login");
    		pass=false;
    	}
    	if(DataValidator.isNull(request.getParameter("password"))){
    		request.setAttribute("password", arg1);
    	}
		return super.validate(request);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
