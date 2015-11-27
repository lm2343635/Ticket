$(document).ready(function() {
	checkAdminSession(function() {
		searchTickets((new Date()).format(YEAR_MONTH_DATE_FORMAT), "", "", "");
	});

	$("#search-ticket-date").datetimepicker({
        format: 'yyyy-mm-dd',
        weekcheckin: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		checkinView: 2,
		minView: 2,
		forceParse: 0,
        showMeridian: 1,
        language: 'zh-CN'
    }).val((new Date()).format(YEAR_MONTH_DATE_FORMAT));

    ScenicManager.getAll(false, function(scenics) {
    	for(var i in scenics) {
    		$("<option>").val(scenics[i].sid).text(scenics[i].sname).appendTo("#search-ticket-sid");
    	}
    });


	$("#search-ticket-submit").click(function() {
		var date=$("#search-ticket-date").val();
		var tno=$("#search-ticket-tno").val();
		var telephone=$("#search-ticket-telephone").val();
		var sid=$("#search-ticket-sid").val();
		searchTickets(date, tno, telephone, sid);
	});

	$("#search-ticket-reset").click(function() {
		var date=$("#search-ticket-date").val();
		$("#search-ticket-tno").val("");
		$("#search-ticket-telephone").val("");
		$("#search-ticket-sid").val("");
		searchTickets(date, "", "", "");
	});
});

function searchTickets(date, tno, telephone, sid) {
	$("#tickets-date").text(date);
	TicketManager.searchPayedTickets(date, tno, telephone, sid, function(tickets) {
		$("#ticket-list tbody").mengularClear();
		for(var i in tickets) {
			$("#ticket-list tbody").mengular(".ticket-list-template", {
				tid: tickets[i].tid,
				createDate: tickets[i].createDate.format(DATE_HOUR_MINUTE_FORMAT),
				tno: tickets[i].tno,
				sname: tickets[i].scenic.sname,
				count: tickets[i].count,
				price: tickets[i].price,
				amount: tickets[i].amount,
				name: tickets[i].name,
				telephone: tickets[i].telephone,
				email: tickets[i].email,
				password: tickets[i].password
			});

			//是否领票
			$("#"+tickets[i].tid+" .ticket-list-checkin input").bootstrapSwitch({
				state: tickets[i].checkin
			}).on('switchChange.bootstrapSwitch', function(event, state) {
				var tid=$(this).parent().parent().parent().parent().attr("id");
				TicketManager.setCheckin(tid, state);
			});
		}		
	});
}