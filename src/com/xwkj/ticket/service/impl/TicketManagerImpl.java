package com.xwkj.ticket.service.impl;

import java.util.Date;

import com.xwkj.common.util.DateTool;
import com.xwkj.common.util.MathTool;
import com.xwkj.ticket.domain.Scenic;
import com.xwkj.ticket.domain.Ticket;
import com.xwkj.ticket.service.TicketManager;
import com.xwkj.ticket.service.util.ManagerTemplate;

public class TicketManagerImpl extends ManagerTemplate implements TicketManager {

	@Override
	public String orderTicket(String sid, int count, Date date, String name, String telephone, String email) {
		Scenic scenic=scenicDao.get(sid);
		Ticket ticket=new Ticket();
		do {
			ticket.setTno("T"+DateTool.formatDate(date, "yyyyMMdd")+MathTool.getRandomStr(6));
		} while(ticketDao.findByTno(ticket.getTno())==null);
		ticket.setPassword(MathTool.getRandomStr(6));
		ticket.setPrice(scenic.getPrice());
		ticket.setCount(count);
		ticket.setAmount(count*scenic.getPrice());
		ticket.setDate(date);
		ticket.setCreateDate(new Date());
		ticket.setName(name);
		ticket.setTelephone(telephone);
		ticket.setEmail(email);
		ticket.setPay(false);
		ticket.setScenic(scenic);
		ticketDao.save(ticket);
		//发短信
		
		//发邮件
		return ticket.getTno();
	}

}
