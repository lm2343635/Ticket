package com.xwkj.ticket.service;

import javax.servlet.http.HttpSession;

public interface AdminManager 
{
	public static final String ADMIN_FLAG="5b0a0bf25123c9f8015123d533f70003";
	
	/**
	 * 管理员登陆
	 * @param username
	 * @param password
	 * @return
	 */
	boolean login(String username,String password, HttpSession session);
	
	/**
	 * 
	 * @param session
	 * @return
	 */
	String checkSession(HttpSession session);
}
