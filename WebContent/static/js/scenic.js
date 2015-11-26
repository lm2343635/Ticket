var sid=request("sid");
var _scenic;

$(document).ready(function() {

	$.messager.model = {
		ok:{ 
			text: "我已确认无误，马上预定", 
			classed: "btn-success" 
		},
		cancel: { 
			text: "我要修改", 
			classed: "btn-default" 
		}
	};
	
	$("#head").load("head.html");
	$("#foot").load("foot.html");

	$("#booking-scenic-date").datetimepicker({
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
    }).change(function() {
		var date=$(this).val();
		if(date!=""&&date!=null) {
			if(getDaysBetweenDates(new Date((new Date().format(YEAR_MONTH_DATE_FORMAT))), new Date(date))<1) {
				$(this).parent().addClass("has-error");
				$.messager.popup("预定日期必须在今日以后！");
				$(this).val("");
				return;
			} else {
				$(this).parent().removeClass("has-error");
			}
		}
	});

	ScenicManager.getScenic(sid, function(scenic) {
		if(scenic==null) {
			location.href="urlError.html";
			return;
		}

		_scenic=scenic;

		fillText({
			"scenic-sname": scenic.sname,
			"scenic-location": scenic.location,
			"scenic-description": scenic.description,
			"scenic-price": scenic.price
		});

		PhotoManager.getPhotosBySid(sid, function(photos) {
			if(photos.length==0) {
				$("#scenic-photo-list").mengular(".scenic-photo-list-template", {
					src: "static/images/noImage.jpg"
				});
			}
			
			for(var i in photos) {
				var indicator=$("<li>").attr("data-target", "#scenic-photo-list")
					.attr("data-slide-to", i)
					.attr("style","background-image: url(upload/"+sid+"/"+photos[i].filename+")");
				if(i==0)
					indicator.addClass("active");
				$("#scenic-photo-list .carousel-indicators").append(indicator);
			}
			
			for(var i in photos) {
				$("#scenic-photo-list .carousel-inner").mengular(".scenic-photo-list-template", {
					pid: photos[i].pid,
					src: "upload/"+sid+"/"+photos[i].filename,
					sname: scenic.sname
				});
				if(i==0) {
					$("#"+photos[i].pid).addClass("active");
				}
			}
			
			$("#scenic-photo-list .mengular-template").remove();
		});
	});

	//预定门票
	$("#booking-scenic-submit").click(function() {
		var date=$("#booking-scenic-date").val();
		var count=$("#booking-scenic-count").val();
		var name=$("#booking-scenic-name").val();
		var telephone=$("#booking-scenic-telephone").val();
		var email=$("#booking-scenic-email").val();
		var validate=true;
		if(date==""||date==null) {
			validate=false;
			$("#booking-scenic-date").parent().addClass("has-error");
		} else {
			$("#booking-scenic-date").parent().removeClass("has-error");
		}
		if(count==""||count==null||!isInteger(count)||count<=0) {
			validate=false;
			$("#booking-scenic-count").parent().addClass("has-error");
		} else {
			$("#booking-scenic-count").parent().removeClass("has-error");
		}
		if(name==""||name==null) {
			validate=false;
			$("#booking-scenic-name").parent().addClass("has-error");
		} else {
			$("#booking-scenic-name").parent().removeClass("has-error");
		}
		if(telephone==""||telephone==null) {
			validate=false;
			$("#booking-scenic-telephone").parent().addClass("has-error");
		} else {
			$("#booking-scenic-telephone").parent().removeClass("has-error");
		}
		if(validate) {
			var message="<p class='text-muted'>您预定了<strong>"+date
				+"</strong>日期的<strong>"+count
				+"</strong>张票，合计<strong>"+count*_scenic.price
				+"</strong>元。<br>您的姓名是<strong>"+name
				+"</strong>，<br>电话号码是<strong>"+telephone
				+"</strong>，<br>电子邮箱是<strong>"+(email==""? "未填写": email)+"</strong>。</p>";
			$.messager.confirm("请再次确认您的预定日期、数量、姓名和联系方式", message, function() {
				TicketManager.orderTicket(sid, count, date, name, telephone, email, function(tno) {
					location.href="pay.html?tno="+tno;
				});
			});
		}
	});

});