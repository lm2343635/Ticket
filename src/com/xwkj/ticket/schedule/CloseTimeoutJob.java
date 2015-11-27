package com.xwkj.ticket.schedule;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.xwkj.common.util.DateTool;
import com.xwkj.ticket.dao.TicketDao;
import com.xwkj.ticket.domain.Ticket;
import com.xwkj.ticket.service.TicketManager;

public class CloseTimeoutJob extends QuartzJobBean {
	
	private TicketDao ticketDao;
	private TicketManager ticketManager;

	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}

	public void setTicketManager(TicketManager ticketManager) {
		this.ticketManager = ticketManager;
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		Date time=DateTool.nextMinute(new Date(), -ticketManager.getPayTimeOut());
		for(Ticket ticket: ticketDao.findWillTimeout(time)) {
			System.out.println(ticket.getTno()+" created at "+ticket.getCreateDate()+" is timeout.");
			ticket.setTimeout(true);
			ticketDao.update(ticket);
		}
	}

}
