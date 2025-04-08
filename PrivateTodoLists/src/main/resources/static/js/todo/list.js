window.onload = function() {
	if (flashMsg) {
		  $("#msg-modal").fadeIn(200);
		  $(".modal").fadeIn(200);
	}
	if (flashMsg == "login") {
		showLoginMsg()
	}
	
	if (todoList.length == 0) {
		$("#todo-table-all").hide();
		$("#todo-table-unfinished").hide();
		$("#no-task-msg").text("“作業登録”からタスクを登録してください");
		$("#no-task-msg").show();
	} else {
		$("#no-task-msg").hide();
		selectShowingTable($("#finishedCheck").prop("checked"));
	}
	if ($("#todo-table-unfinished tbody tr").length > 0) {
		$(".badge").text($("#todo-table-unfinished tbody tr").length);
		$(".badge").show();
	} else {
		$(".badge").hide();
	}
//	layoutSetting();
	
//	$(".navbar").css({'cssText': 'background-color:' + settingMap.headerBgColor + '!important;'});
//	$(".navbar-brand").css({'cssText': 'color:' + settingMap.headerFontColor + '!important;'});
	
	
	
}
            
$("#close-modal, .btn-close").click(function () {
	$("#msg-modal").hide();
});

function toggleShowFinishedTodo() {
	var state = $("#finishedCheck").prop("checked");
	
	selectShowingTable(state);
	
	$.ajax({
            url: "/todo/list", // 取得するHTMLのURL
            method: "POST",
            data: { state: state },
            //contentType: "json",
            success: function (data) {
				
				//$("#finishedCheck").prop("checked", !state);
 
            },
            error: function () {
                alert("データの取得に失敗しました");
            }
        });
	
	
	
}

function selectShowingTable(state) {
	if(state) {
		$("#todo-table-all").hide();		
		if ($("#todo-table-unfinished tbody td").length == 0) {
			$("#no-task-msg").text("未完了のタスクはありません")
			$("#no-task-msg").show();
			$("#todo-table-unfinished").hide();
		} else {
			$("#no-task-msg").hide();
			$("#todo-table-unfinished").show();
		} 
	} else {
		$("#no-task-msg").hide();
		$("#todo-table-all").show();
		$("#todo-table-unfinished").hide();
	}
	
}

function showLoginMsg() {
	var unfinishedTodoCount = $("#todo-table-unfinished tbody tr").length;
	if (unfinishedTodoCount != 0) {
		$(".modal-body p").text("未完了のタスクが"+ unfinishedTodoCount + "件あります");
	} else {
		$(".modal-body p").text("ようこそ");
	}	
	var todaysTodo = todoList.filter(todo => 		
		todo.expireDate == today.substr(0, 10) 
	)
	if (todaysTodo.length != 0) {
		$(".modal-body p").text("本日期限のタスクが"+ todaysTodo.length + "件あります");
	}
}
	
//	$.ajax({
//            url: "/todo/list", // 取得するHTMLのURL
//            method: "POST",
//            data: { state: state },
//            //contentType: "json",
//            success: function (data) {
//				
//				$(".form-check-input").prop("checked", !state);
//                //$("table").html($(data).find("table").html()); // 指定部分のみ更新
//                //$("table").load("/todo/list table");
//                //location.reload();
//                
//                var $newTable = $(data).find("#todo-table").html();
//                console.log($newTable);
//                $("#todo-table").replaceWith($newTable);
//            },
//            error: function () {
//                alert("データの取得に失敗しました");
//            }
//        });
