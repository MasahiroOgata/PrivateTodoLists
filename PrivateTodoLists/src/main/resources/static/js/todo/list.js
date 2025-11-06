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
	
}

            
$("#close-modal, .btn-close").click(function () {
	$("#msg-modal").hide();
});


function toggleShowFinishedTodo() {
	var state = $("#finishedCheck").prop("checked");
	
	selectShowingTable(state);
	
    // メタタグに埋め込んだ情報を取得する
    let token = $("meta[name='_csrf']").attr("content"); 
    let header = $("meta[name='_csrf_header']").attr("content"); 
    
    // Ajax通信時に、リクエストヘッダにCSRFトークンを埋め込むよう記述
    $(document).ajaxSend(function(e, xhr, options){
        xhr.setRequestHeader(header, token);
    });
	
	$.ajax({
            url: "/todo/tabletoggle", // 取得するHTMLのURL
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
	console.log(state);
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
		todo.expireDate.substr(0, 10) == today.substr(0, 10) && todo.finishedDate == null
	)
	if (todaysTodo.length != 0) {
		$(".modal-body p").text("本日期限のタスクが"+ todaysTodo.length + "件あります");
	}	

}
	
