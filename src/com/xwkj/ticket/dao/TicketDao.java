package com.xwkj.ticket.dao;

import java.util.Date;
import java.util.List;

import com.xwkj.ticket.domain.Scenic;
import com.xwkj.ticket.domain.Ticket;

public interface TicketDao {
	Ticket get(String tid);
	String save(Ticket ticket);
	void update(Ticket ticket);
	void delete(Ticket ticket);
	
	/**
	 * 按门票订单号查询
	 * @param tno
	 * @return
	 */
	Ticket findByTno(String tno);
	
	/**
	 * 查询支付门票
	 * @param date
	 * @param tno
	 * @param telephone
	 * @param scenic
	 * @return
	 */
	List<Ticket> findPayed(Date date, String tno, String telephone, Scenic scenic);
}
