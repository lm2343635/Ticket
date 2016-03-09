package com.xwkj.ticket.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xwkj.common.util.DateTool;
import com.xwkj.common.util.MailService;
import com.xwkj.common.util.SMSService;
import com.xwkj.ticket.dao.ScenicDao;
import com.xwkj.ticket.dao.TicketDao;
import com.xwkj.ticket.domain.Scenic;
import com.xwkj.ticket.domain.Ticket;
import com.xwkj.ticket.service.TicketManager;
import com.xwkj.ticket.service.util.ManagerTemplate;

@WebServlet("/WeChatPayedServlet")
public class WeChatPayedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WeChatPayedServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result="";
		BufferedReader reader=new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
        String line = null;
        while((line = reader.readLine())!=null){
            result+=line;
        }
        Document document=null;
		try {
			document=DocumentHelper.parseText(result);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		if(document==null) {
			response.getWriter().print("<xml><return_code>FAIL</return_code></xml>");
			return;
		}
		Element root=document.getRootElement();
		String nonce_str=root.elementText("nonce_str");
		String return_code=root.elementText("return_code");
		String result_code=root.elementText("result_code");
		String out_trade_no=root.elementText("out_trade_no");
		String time_end=root.elementText("time_end");
		
		System.out.println(out_trade_no+" has been paeded at "+new Date());
		WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ManagerTemplate managerTemplate=(ManagerTemplate)context.getBean("managerTemplate");
		TicketDao ticketDao=managerTemplate.getTicketDao();
		ScenicDao scenicDao=managerTemplate.getScenicDao();
		Ticket ticket=ticketDao.findByTno(out_trade_no);
		//查询不到订单
		if(ticket==null) {
			response.getWriter().print("<xml><return_code>FAIL</return_code></xml>");
			return;
		}
		//返回码存在失败信息
		if(return_code.equals("FAIL")||result_code.equals("FAIL")) {
			response.getWriter().print("<xml><return_code>FAIL</return_code></xml>");
			return;
		}
		//比对随机字符串
		if(nonce_str.equals(ticket.getWechatNonce())) {
			//更新订单支付信息
			ticket.setPay(true);
			ticket.setPayDate(DateTool.transferDate(time_end, "yyyyMMddhhmmss"));
			ticketDao.update(ticket);
			//增加商品销售量
			Scenic scenic=ticket.getScenic();
			scenic.setSold(scenic.getSold()+ticket.getCount());
			scenicDao.update(scenic);
			//准备发邮件和短信提醒用户已支付
			String value="#tno#="+ticket.getTno()+"&#date#="+DateTool.formatDate(ticket.getDate(), DateTool.YEAR_MONTH_DATE_FORMAT_CN)
				+"&#password#="+ticket.getPassword()+"&#sname#="+scenic.getSname()+"&#location#="+scenic.getLocation()+"&#count#="+ticket.getCount();
			//发送短息通知用户支付成功
			SMSService sms=(SMSService)context.getBean("SMSService");
			TicketManager ticketManager=(TicketManager)context.getBean("ticketManager");
			sms.send(ticket.getTelephone(), ticketManager.getPaySMSTemplate(), value);
			//发邮件
			if(ticket.getEmail()!=null&&!ticket.getEmail().equals("")) {
				MailService mail=(MailService)context.getBean("MailService");
				mail.setTo(ticket.getEmail());
				mail.setSubject(ticketManager.getMailSubject());
				mail.setContent(MailService.replaceWithParameters(ticketManager.getPayMail(), value));
				mail.send();
			}
			response.getWriter().print("<xml><return_code>SUCCESS</return_code></xml>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
