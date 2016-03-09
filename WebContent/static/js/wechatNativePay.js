var tno=request("tno");

$(document).ready(function() {
	
	$("#head").load("head.html");
	$("#foot").load("foot.html");
	
	fillText({
		"ticket-tno": tno,
	});
	

	//加载微信支付code
	WeChatPayManager.createNative(tno, function(data) {
		console.log(data);
		if(data.codeUrl==null) {
			$.messager.alert("提示", "该订单已超时或已被支付，请求支付二维码失败！");
			return;
		}
		$("#qrcode").html("").qrcode(data.codeUrl);
		
		//加载成功后每隔5秒查询一次支付状态
		var checkState=setInterval(function() {
			TicketManager.checkPayState(tno, function(pay) {
				if(pay) {
					$.messager.popup("支付成功！");
					setTimeout(function() {
						location.href="pay.html?tno="+tno;
					}, 2000);
					clearInterval(checkState);
					return;
				} 
			});
		}, 5000);
	});
});