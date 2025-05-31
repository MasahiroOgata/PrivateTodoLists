window.onload = function() {
	if (flashMsg) {
		  $("#msg-modal").fadeIn(200);
		  $(".modal").fadeIn(200);
	}
	if (flashMsg == "login") {
		showLoginMsg()
	}
	
	var isHidingFinishedTodo = "isHidingFinishedTodo" in settingMap ? settingMap.isHidingFinishedTodo : '0';
	
	if (todoList.length == 0) {
		$("#todo-table-all, #todo-table-unfinished").hide();
		$("#no-task-msg").text("“作業登録”からタスクを登録してください");
		$("#no-task-msg").show();
	} else {
		$("#finishedCheck").prop("checked", isHidingFinishedTodo == '1');
		selectShowingTable(isHidingFinishedTodo == '1');
	}
	
	$("i").each(function(){
		$(this).css('color', $(this).data("tag-color"));
	});
	
	const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
	const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))
	
//	if ($("#todo-table-unfinished tbody tr").length > 0) {
//		$(".badge").text($("#todo-table-unfinished tbody tr").length);
//		$(".badge").show();
//	} else {
//		$(".badge").hide();
//	}
//	layoutSetting();
	
//	$(".navbar").css({'cssText': 'background-color:' + settingMap.headerBgColor + '!important;'});
//	$(".navbar-brand").css({'cssText': 'color:' + settingMap.headerFontColor + '!important;'});
	
	
	$('.table').DataTable();
	
	
	
	
	
}
            
$("#close-modal, .btn-close").click(function () {
	$("#msg-modal").hide();
});

function toggleShowFinishedTodo() {
	var state = $("#finishedCheck").prop("checked");
	
	selectShowingTable(state);
	
	$.ajax({
            url: "/todo/toggle", // 取得するHTMLのURL
            method: "PUT",
            data: { state: state },
            success: function () {
            },
            error: function () {
                alert("データの更新に失敗しました");
            }
        });
}

function selectShowingTable(state) {
	$("#no-task-msg, #todo-table-all, #todo-table-unfinished" ).hide();
	if(state) {	
		if ($("#todo-table-unfinished tbody td").length == 0) {
			$("#no-task-msg").text("未完了のタスクはありません")
			$("#no-task-msg").show();
		} else {
			$("#todo-table-unfinished").show();
		} 
	} else {
		$("#todo-table-all").show();
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
		todo.expireDate == today.substr(0, 10) && todo.finishedDate == null
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
