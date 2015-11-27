package com.xwkj.ticket.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alipay.service.AlipaySubmit;
import com.xwkj.common.util.DateTool;
import com.xwkj.ticket.domain.Ticket;
import com.xwkj.ticket.service.TicketManager;
import com.xwkj.ticket.service.util.ManagerTemplate;

@WebServlet("/AlipayServlet")
public class AlipayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private String task;
    
    public AlipayServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		task=request.getParameter("task");
		switch (task) {
		case "pay":
			pay(request,response);
			break;

		default:
			break;
		}
	}

	private void pay(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tno=request.getParameter("tno");
		WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		TicketManager ticketManager=(TicketManager)context.getBean("ticketManager");
		ManagerTemplate manager=(ManagerTemplate)context.getBean("managerTemplate");
		Ticket ticket=manager.getTicketDao().findByTno(tno);
		AlipaySubmit alipaySubmit=(AlipaySubmit)context.getBean("AlipaySubmit");
		//订单描述
		String body="预定日期"+DateTool.formatDate(ticket.getDate(), DateTool.YEAR_MONTH_DATE_FORMAT_CN)+"，共"
				+ticket.getCount()+"张，单价"+ticket.getPrice()+"元，合计"+ticket.getAmount()+"元";
		//超时时长
		int minutes=ticketManager.getPayTimeOut()-DateTool.minutesBetween(ticket.getCreateDate(), new Date());
		//建立请求
		Map<String, Object> data= alipaySubmit.buildRequest(minutes, ticket.getTno(), ticket.getScenic().getSname(), ticket.getAmount(), body, ticket.getScenic().getSid());
		System.out.println(data.get("sbHtml"));
		//跳转页面
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");  
		response.getWriter().print(data.get("sbHtml"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
