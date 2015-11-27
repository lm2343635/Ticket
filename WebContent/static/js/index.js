$(document).ready(function() {
	
	$("#head").load("head.html");
	$("#foot").load("foot.html");

	//加载景点
	ScenicManager.getAll(false, function(scenics) {
		for(var i in scenics) {
			var src="static/images/noImage.jpg";
			if(scenics[i].cover!=null) {
				src="upload/"+scenics[i].sid+"/"+scenics[i].cover.filename;
			}
			$("#scenic-list").mengular(".scenic-list-template", {
				sid: scenics[i].sid,
				sname: scenics[i].sname,
				location: scenics[i].location,
				price: scenics[i].price,
				src: src,
				sold: scenics[i].sold
			});
		}

	});

	//搜索订单
	$("#search-ticket-submit").click(function() {
		var tno=$("#search-ticket-tno").val();
		TicketManager.getTicketByTno(tno, function(ticket) {
			if(ticket==null) {
				$.messager.popup("不存在该订单，请确认订单号无误");
				return;
			}
			location.href="pay.html?tno="+tno;
		});
	});
	
});
