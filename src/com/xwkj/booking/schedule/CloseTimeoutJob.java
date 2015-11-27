package com.xwkj.booking.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.xwkj.ticket.dao.TicketDao;
import com.xwkj.ticket.domain.Ticket;
import com.xwkj.ticket.service.TicketManager;
import com.xwkj.ticket.service.util.ManagerTemplate;

public class CloseTimeoutJob extends QuartzJobBean {
	
	private ManagerTemplate managerTemplate;
	private TicketManager ticketManager;

	public void setManagerTemplate(ManagerTemplate managerTemplate) {
		this.managerTemplate = managerTemplate;
	}

	public void setTicketManager(TicketManager ticketManager) {
		this.ticketManager = ticketManager;
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		TicketDao ticketDao=managerTemplate.getTicketDao();

	}

}
