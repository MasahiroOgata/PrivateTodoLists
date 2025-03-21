window.onload = function() {
	if (flashMsg) {
		  $("#msg-modal").fadeIn(200);
		  $(".modal").fadeIn(200);
	}
	toggleShowFinishedTodo();
}
            
$("#close-modal, .btn-close").click(function () {
	$("#msg-modal").hide();
});

function toggleShowFinishedTodo() {
	var state = $("#finishedCheck").prop("checked");
	selectShowingTable(state);
	
	console.log(window.sessionStorage.getItem("isShowingFinishedTodo"));
}

function selectShowingTable(state) {
	if(state) {
		$("#todo-table-all").show();
		$("#todo-table-finished").hide();
		window.sessionStorage.setItem("isShowingFinishedTodo", true)		
	} else {
		$("#todo-table-all").hide();
		$("#todo-table-finished").show();
		window.sessionStorage.setItem("isShowingFinishedTodo", false)
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
