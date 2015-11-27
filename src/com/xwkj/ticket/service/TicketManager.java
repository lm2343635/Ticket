package com.xwkj.ticket.service;

import com.xwkj.ticket.bean.TicketBean;

public interface TicketManager {
	
	public int getPayTimeOut();
	
	public int getPaySMSTemplate();
	
	public String getMailSubject();

	public String getPayMail();
	
	/**
	 * 预定门票
	 * @param sid
	 * @param count 张数
	 * @param date 日期
	 * @param name 预定者姓名
	 * @param telephone 电话号码
	 * @param email 电子邮箱
	 * @return
	 */
	String orderTicket(String sid, int count, String date, String name, String telephone, String email);
	
	/**
	 * 获取门票
	 * @param tid
	 * @return
	 */
	TicketBean getTicket(String tid);
	
	/**
	 * 根据订单号获取门票
	 * @param tno
	 * @return
	 */
	TicketBean getTicketByTno(String tno);
}
