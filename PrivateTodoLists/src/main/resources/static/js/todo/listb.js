window.onload = function() {
	if (flashMsg) {
		  $("#msg-modal").fadeIn(200);
		  $(".modal").fadeIn(200);
	}
	if (flashMsg == "login") {
		$(".modal-body p").text("ようこそ");
		//showLoginMsg()
	}
	
	var isHidingFinishedTodo = "isHidingFinishedTodo" in settingMap ? settingMap.isHidingFinishedTodo : '0';
	
//	if (todoList.length == 0) {
//		$("#todo-table-all, #todo-table-unfinished").hide();
//		$("#no-task-msg").text("“作業登録”からタスクを登録してください");
//		$("#no-task-msg").show();
//	} else {
//		$("#finishedCheck").prop("checked", isHidingFinishedTodo == '1');
//
//	}
	
	
	getTodoList();
	
	/** 検索ボタンを押した時の処理. */
	$('#btn-search').click(function (event) {
		getTodoList();
	});
}

var todoData = null;
var table = null;

function createDataTables() {
	if (table != null) {
		table.destroy();
	}
	
	DataTable.type('date', 'className', ' ');

	table = $('#rest-table').DataTable({
		"info": true,
		language: {
    		url: 'https://cdn.datatables.net/plug-ins/2.3.1/i18n/ja.json',
    	},
//    	scrollY: "90vh",
//        scrollCollapse: true,
//    	fixedHeader: true,
    	data: todoData,
    	order: [],

    	columns:[
			{ 
				width: '5%',
				data: null,
				render: function(data, type, row){					
					if (row.tag) {
						var html = '<span data-bs-toggle="tooltip" data-bs-placement="top" '
						+ 'data-bs-title="'
						+ row.tag.tagName
						+ '"><i class="fa-xl '
						+ row.tag.tagIcon 
						+ '" data-tag-color="'
						+ row.tag.tagColor
						+'"></i></span> '
						return html;
					}
					return "";
				}
			},
			{ 
				width: '40%',
				data: 'itemName',
				render: function(data, type, row) {	
					return $('<div>').text(data).html();
				}
			},
			{ 
				width: '15%',
				data: 'expireDate',
				render: function(data, type, row) {
					var date = new Date(data);
					var year = date.getFullYear();
					var month = date.getMonth() + 1;
					var day = date.getDate();
					return year + "/" + month + "/" + day;
				}
			},
			{ 
				width: '15%',
				data: 'finishedDate',
				render: function(data, type, row) {
					if (!data) {
						let color = 'green';
						var daysRemain = Math.floor((Date.parse(row['expireDate']) - Date.now()) / (1000 * 60 * 60 * 24)) + 1
						var remainingDays = "";
						if (daysRemain < 0) {
							remainingDays = "期限切れ";
							color = "red";
						} else if (daysRemain == 0) {
							remainingDays = "本日まで";
							color = "orange";
						} else {
							remainingDays = "残り" + daysRemain + "日";
						}
						return `<span style="color:${color}">${remainingDays}</span>`;
					} else {
						var date = new Date(data);
						var year = date.getFullYear();
						var month = date.getMonth() + 1;
						var day = date.getDate();
						return year + "/" + month + "/" + day;
					}
				}
			},
			{ 
				width: '25%',
				data: null,
				render: function(data, type, row) {
					var url = '<div class="row">';
					const id = row.id;
					const finishedDate = row.finishedDate;
					
					if (finishedDate==null) {
						url +='<button class="btn btn-outline-primary btn-sm rounded-pill col mx-1" onclick="finishTodo('
						+ 'this)">完了する</button>';						
					} else {
						url +='<button class="btn btn-outline-danger btn-sm rounded-pill col mx-1" onclick="finishTodo('
						+ 'this)">未完了にする</button>';						
					}	
					
					url += '<a class="btn btn-outline-success btn-sm rounded-pill col me-2" href="/todo/detail/'
					+ id + '">詳細</a></div>'					
					return url;				
				}				
			},
		],
		drawCallback: function(){
			setIconsData()		
		}
	});
	
	$("th:nth-of-type(1)").prop('disabled', true);
	
}

function setIconsData() {
	$("i").each(function(){
		$(this).css('color', $(this).data("tag-color"));
	});	
	const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
	const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))
}
            
$("#close-modal, .btn-close").click(function () {
	$("#msg-modal").hide();
});

function getTodoList() {
	var state = $("#finishedCheck").prop("checked");
	
	var search = document.forms[1].search.value;
	console.log(search);
	
	$.ajax({
			type: "GET", 
			url: "/todo/get/list",
			data: { state: state, search: search },
			dataType: "json",
			contentType: "application/json; charset=UTF-8",
			cache: false,
			timeout: 5000,
	}).done(function(data){
			console.log(data);
			todoData = data;
			createDataTables();					
	}).fail(function(){
		alert("データの取得に失敗しました");
	});
}

function toggleShowFinishedTodo() {
	var state = $("#finishedCheck").prop("checked");
	
	var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

	$.ajax({
		url: "/todo/tabletoggle",
		method: "PUT",
		data: { state: state },
		beforeSend: function(xhr) {
		    xhr.setRequestHeader(header, token);
		}
	}).done(function(){
		getTodoList();
	}).fail(function(){
		alert("データの更新に失敗しました");
	});
}

function finishTodo(btn) {
    var row = table.row($(btn).closest('tr'));
    var rowData = row.data();
    var todoId = rowData.id;
    
    console.log(rowData.id);
    
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    
    console.log(token);
    console.log(header);
    
	
	$.ajax({
            url: "/todo/finishtoggle", 
            method: "PUT",
            data: { todoId: todoId},
//            headers: {
//            	"X-CSRF-TOKEN": token   // ← CSRF トークン付与
//        	},
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader(header, token);  // ← Spring の正しいヘッダー名で送信
	        },
            success: function (updatedTodo) {
                row.data(updatedTodo).invalidate();
                //console.log("更新後 finishedDate:", updatedTodo.finishedDate);
                console.log(updatedTodo);
                setIconsData()

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
