var tno=request("tno");

$(document).ready(function() {
	
	$("#head").load("head.html");
	$("#foot").load("foot.html");

	TicketManager.getTicketByTno(tno, function(ticket) {
		if(ticket==null) {
			location.href="urlError.html";
			return;
		}

		fillText({
			"ticket-tno": ticket.tno,
			"ticket-sname": ticket.scenic.sname,
			"ticket-location": ticket.scenic.location,
			"ticket-price": ticket.price,
			"ticket-count": ticket.count,
			"ticket-amount": ticket.amount,
		});

		$("#ticket-href").attr("href", "scenic.html?sid="+ticket.scenic.sid);
		$("#ticket-cover").attr("src", ticket.scenic.cover==null? "static/image/noImage.jpg": "upload/"+ticket.scenic.sid+"/"+ticket.scenic.cover.filename);

		if(ticket.pay&&!ticket.timeout) {
			$("#pay-info").addClass("alert-success");
			$("#pay-info .order-payed").show();
		} else if(!ticket.pay&&!ticket.timeout) {
			$("#pay-info").addClass("alert-warning");
			$("#pay-info .order-wait").show();
			$("#pay-panel").show();
			$("#alipay-submit").attr("href", "AlipayServlet?task=pay&tno="+tno);
			$("#wechat-submit").attr("href", "wechatNativePay.html?tno="+tno);
		} else if(!ticket.pay&&ticket.timeout) {
			$("#pay-info").addClass("alert-danger");
			$("#pay-info .order-close").show();
		}
	});

});
