package com.xwkj.ticket.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alipay.service.AlipaySubmit;
import com.xwkj.common.util.DateTool;
import com.xwkj.common.util.MailService;
import com.xwkj.common.util.SMSService;
import com.xwkj.ticket.domain.Scenic;
import com.xwkj.ticket.domain.Ticket;
import com.xwkj.ticket.service.TicketManager;
import com.xwkj.ticket.service.util.ManagerTemplate;

@WebServlet("/AlipayPayedServlet")
public class AlipayPayedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AlipayPayedServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tno=request.getParameter("out_trade_no");
		String notify_id=request.getParameter("notify_id");
		System.out.println(tno+" has been paeded at "+new Date());
		WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ManagerTemplate manager=(ManagerTemplate)context.getBean("managerTemplate");
		Ticket ticket=manager.getTicketDao().findByTno(tno);
		AlipaySubmit alipaySubmit=(AlipaySubmit)context.getBean("AlipaySubmit");
		//支付完成更新支付信息、支付宝服务器可能会发出多次请求、如果已经接受一次请求之后将屏蔽支付宝的请求
		if(alipaySubmit.notifyVertify(notify_id)&&!ticket.getPay()) {
			TicketManager ticketManager=(TicketManager)context.getBean("ticketManager");
			ticket.setPay(true);
			ticket.setPayDate(new Date());
			manager.getTicketDao().update(ticket);
			//支付成功后销售数量加1
			Scenic scenic=ticket.getScenic();
			scenic.setSold(scenic.getSold()+ticket.getCount());
			manager.getScenicDao().update(scenic);
			//准备发邮件和短信提醒用户已支付
			String value="#tno#="+ticket.getTno()+"&#date#="+DateTool.formatDate(ticket.getDate(), DateTool.YEAR_MONTH_DATE_FORMAT_CN)
				+"&#password#="+ticket.getPassword()+"&#sname#="+scenic.getSname()+"&#location#="+scenic.getLocation()+"&#count#="+ticket.getCount();
			//发送短息通知用户和管理员支付成功
			SMSService sms=(SMSService)context.getBean("SMSService");
			sms.send(ticket.getTelephone(), ticketManager.getPaySMSTemplate(), value);
			sms.send(ticketManager.getAdminTelephone(), ticketManager.getPaySMSTemplate(), value);
			//发邮件
			if(ticket.getEmail()!=null&&!ticket.getEmail().equals("")) {
				MailService mail=(MailService)context.getBean("MailService");
				mail.setTo(ticket.getEmail());
				mail.setSubject(ticketManager.getMailSubject());
				mail.setContent(MailService.replaceWithParameters(ticketManager.getPayMail(), value));
				mail.send();
			}
			response.getWriter().println("success");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
