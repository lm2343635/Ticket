package com.xwkj.ticket.service;

import java.util.Date;

public interface TicketManager {

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
	String orderTicket(String sid, int count, Date date, String name, String telephone, String email);
}
