var modifyingSid;

$(document).ready(function() {
	$.messager.model = {
		ok:{ 
			text: "确定", 
			classed: "btn-danger" 
		},
		cancel: { 
			text: "取消", 
			classed: "btn-default" 
		}
	};
	
	checkAdminSession(function() {
		loadScenics();
	});

	$("#add-scenic-submit").click(function() {
		var sname=$("#add-scenic-sname").val();
		var location=$("#add-scenic-location").val();
		var price=$("#add-scenic-price").val();
		var description=$("#add-scenic-description").val();
		var validate=true;
		if(sname==""||sname==null) {
			validate=false;
			$("#add-scenic-sname").parent().addClass("has-error");
		} else {
			$("#add-scenic-sname").parent().removeClass("has-error");
		}
		if(location==""||location==null) {
			validate=false;
			$("#add-scenic-location").parent().addClass("has-error");
		} else {
			$("#add-scenic-location").parent().removeClass("has-error");
		}
		if(price==""||price==null||!isNum(price)) {
			validate=false;
			$("#add-scenic-price").parent().addClass("has-error");
		} else {
			$("#add-scenic-price").parent().removeClass("has-error");
		}
		if(description==""||description==null) {
			validate=false;
			$("#add-scenic-description").parent().addClass("has-error");
		} else {
			$("#add-scenic-description").parent().removeClass("has-error");
		}
		if(validate) {
			ScenicManager.addScenic(sname, location, price, description, function(sid) {
				$("#add-scenic-modal").modal("hide");
				loadScenics();
			});
		}
	});

	$("#add-scenic-modal").on("hidden.bs.modal", function() {
		$("#add-scenic-form input").val("");
		$("#add-scenic-form textarea").val("");
	});

	//修改景点
	$("#modify-scenic-submit").click(function() {
		var sname=$("#modify-scenic-sname").val();
		var location=$("#modify-scenic-location").val();
		var price=$("#modify-scenic-price").val();
		var description=$("#modify-scenic-description").val();
		var validate=true;
		if(sname==""||sname==null) {
			validate=false;
			$("#modify-scenic-sname").parent().addClass("has-error");
		} else {
			$("#modify-scenic-sname").parent().removeClass("has-error");
		}
		if(location==""||location==null) {
			validate=false;
			$("#modify-scenic-location").parent().addClass("has-error");
		} else {
			$("#modify-scenic-location").parent().removeClass("has-error");
		}
		if(price==""||price==null||!isNum(price)) {
			validate=false;
			$("#modify-scenic-price").parent().addClass("has-error");
		} else {
			$("#modify-scenic-price").parent().removeClass("has-error");
		}
		if(description==""||description==null) {
			validate=false;
			$("#modify-scenic-description").parent().addClass("has-error");
		} else {
			$("#modify-scenic-description").parent().removeClass("has-error");
		}
		if(validate) {
			ScenicManager.modifyScenic(modifyingSid, sname, location, price, description, function(sid) {
				$("#modify-scenic-modal").modal("hide");
				loadScenics();
			});
		}
	});

});

/**
 * 加载景点
 */
function loadScenics() {
	ScenicManager.getAll(function(scenics) {
		$("#scenic-list tbody").mengularClear();
		for(var i in scenics) {
			$("#scenic-list tbody").mengular(".scenic-list-template", {
				sid: scenics[i].sid,
				createDate: scenics[i].createDate.format(DATE_HOUR_MINUTE_SECOND_FORMAT),
				sname: scenics[i].sname,
				price: scenics[i].price
			});

			//可用状态
			$("#"+scenics[i].sid+" .scenic-list-enable input").bootstrapSwitch({
				state: scenics[i].enable
			}).on('switchChange.bootstrapSwitch', function(event, state) {
				var sid=$(this).parent().parent().parent().parent().attr("id");
				ScenicManager.enable(sid, state);
			});

			//管理景点
			$("#"+scenics[i].sid+" .scenic-list-manage").click(function() {
				modifyingSid=$(this).parent().attr("id");
				ScenicManager.getScenic(modifyingSid, function(scenic) {
					fillValue({
						"modify-scenic-sname": scenic.sname,
						"modify-scenic-location": scenic.location,
						"modify-scenic-price": scenic.price,
						"modify-scenic-description": scenic.description
					});
					$("#modify-scenic-modal").modal("show");
				})
			});

			//删除景点
			$("#"+scenics[i].sid+" .scenic-list-delete").click(function() {
				var sid=$(this).parent().attr("id");
				var sname=$("#"+sid+" .scenic-list-sname").text();
				$.messager.confirm("提示", "确认删除景点："+sname+"吗？", function() {
					ScenicManager.removeScenic(sid, function(success) {
						if(success) {
							$("#"+sid).remove();
						} else {
							$.messager.popup("该景区已经售出门票，无法删除！");
						}
					});
				});
			});
		}
	});
}