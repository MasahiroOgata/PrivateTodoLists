window.onload = function() {
	if (flashMsg) {
		  $("#msg-modal").fadeIn(200);
		  $(".modal").fadeIn(200);
	}
	if (todoList.length == 0) {
		$("#todo-table-all").hide();
		$("#todo-table-finished").hide();
		$("#no-task-msg").text("“作業登録”からタスクを登録してください");
		$("#no-task-msg").show();
	} else {
		$("#no-task-msg").hide();
		selectShowingTable($("#finishedCheck").prop("checked"));
	}
	
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
		if ($("#todo-table-finished tbody td").length == 0) {
			$("#no-task-msg").text("未完了のタスクはありません")
			$("#no-task-msg").show();
			$("#todo-table-finished").hide();
		} else {
			$("#no-task-msg").hide();
			$("#todo-table-finished").show();
		} 
	} else {
		$("#no-task-msg").hide();
		$("#todo-table-all").show();
		$("#todo-table-finished").hide();
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
