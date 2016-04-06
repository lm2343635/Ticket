package com.xwkj.ticket.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.directwebremoting.WebContextFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xwkj.common.util.DateTool;
import com.xwkj.common.util.MailService;
import com.xwkj.common.util.MathTool;
import com.xwkj.common.util.SMSService;
import com.xwkj.ticket.bean.TicketBean;
import com.xwkj.ticket.domain.Scenic;
import com.xwkj.ticket.domain.Ticket;
import com.xwkj.ticket.service.TicketManager;
import com.xwkj.ticket.service.util.ManagerTemplate;

public class TicketManagerImpl extends ManagerTemplate implements TicketManager {
	//支付超时时长
	private int payTimeOut;
	private String createSMSTemplate;
	private String paySMSTemplate;
	private String adminTelephone;
	private String mailSubject;
	private String createMail;
	private String payMail;

	public int getPayTimeOut() {
		return payTimeOut;
	}

	public void setPayTimeOut(int payTimeOut) {
		this.payTimeOut = payTimeOut;
	}

	public String getCreateSMSTemplate() {
		return createSMSTemplate;
	}

	public void setCreateSMSTemplate(String createSMSTemplate) {
		this.createSMSTemplate = createSMSTemplate;
	}

	public String getPaySMSTemplate() {
		return paySMSTemplate;
	}

	public void setPaySMSTemplate(String paySMSTemplate) {
		this.paySMSTemplate = paySMSTemplate;
	}

	public String getAdminTelephone() {
		return adminTelephone;
	}

	public void setAdminTelephone(String adminTelephone) {
		this.adminTelephone = adminTelephone;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getCreateMail() {
		return createMail;
	}

	public void setCreateMail(String createMail) {
		this.createMail = createMail;
	}

	public String getPayMail() {
		return payMail;
	}

	public void setPayMail(String payMail) {
		this.payMail = payMail;
	}

	@Override
	public String orderTicket(String sid, int count, String date, String name, String telephone, String email) {
		Scenic scenic=scenicDao.get(sid);
		Ticket ticket=new Ticket();
		Date jDate=DateTool.transferDate(date, DateTool.YEAR_MONTH_DATE_FORMAT);
		do {
			ticket.setTno(DateTool.formatDate(jDate, "yyyyMMdd")+MathTool.getRandomStr(6));
		} while(ticketDao.findByTno(ticket.getTno())!=null);
		ticket.setPassword(MathTool.getRandomStr(6));
		ticket.setPrice(scenic.getPrice());
		ticket.setCount(count);
		ticket.setAmount(count*scenic.getPrice());
		ticket.setDate(jDate);
		ticket.setCreateDate(new Date());
		ticket.setName(name);
		ticket.setTelephone(telephone);
		ticket.setEmail(email);
		ticket.setPay(false);
		ticket.setTimeout(false);
		ticket.setCheckin(false);
		ticket.setScenic(scenic);
		ticketDao.save(ticket);
		//准备发邮件和短信提醒用户已下单
		WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(WebContextFactory.get().getServletContext());
		String value="#tno#="+ticket.getTno()+"&#sname#="+scenic.getSname()+"&#date#="+DateTool.formatDate(jDate, DateTool.YEAR_MONTH_DATE_FORMAT_CN)
			+"&#price#="+ticket.getPrice()+"&#count#="+ticket.getCount()+"&#amount#="+ticket.getAmount()+"&#hour#="+(payTimeOut/60);
		//发短信
		SMSService sms=(SMSService)context.getBean("SMSService");
		sms.send(telephone, createSMSTemplate, value);
		//发邮件
		if(email!=null&&!email.equals("")) {
			MailService mail=(MailService)context.getBean("MailService");
			mail.setTo(email);
			mail.setSubject(mailSubject);
			mail.setContent(MailService.replaceWithParameters(createMail, value));
			mail.send();
		}
		return ticket.getTno();
	}

	@Override
	public TicketBean getTicket(String tid) {
		Ticket ticket=ticketDao.get(tid);
		if(ticket==null)
			return null;
		return new TicketBean(ticket);
	}

	@Override
	public TicketBean getTicketByTno(String tno) {
		Ticket ticket=ticketDao.findByTno(tno);
		if(ticket==null)
			return null;
		return new TicketBean(ticket);
	}

	@Override
	public List<TicketBean> searchPayedTickets(String date, String tno, String telephone, String sid) {
		List<TicketBean> tickets=new ArrayList<>();
		Scenic scenic=scenicDao.get(sid);
		for(Ticket ticket: ticketDao.findPayed(DateTool.transferDate(date, DateTool.YEAR_MONTH_DATE_FORMAT), tno, telephone, scenic)) {
			tickets.add(new TicketBean(ticket));
		}
		return tickets;
	}

	@Override
	public void setCheckin(String tid, boolean checkin) {
		Ticket ticket=ticketDao.get(tid);
		ticket.setCheckin(checkin);
		ticketDao.update(ticket);
	}

	@Override
	public boolean checkPayState(String tno) {
		Ticket ticket=ticketDao.findByTno(tno);
		if(ticket==null)
			return false;
		return ticket.getPay();
	}

}
