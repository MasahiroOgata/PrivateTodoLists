window.onload = function() {}
	
	var todoEvents = [];

	const TODO_FINISHED_COLOR = "#0d95f0";
	const TODO_EXPIRED_COLOR = "#dc3545";
	const TODO_UNFINISHED_COLOR = "#fd7e14"
	
	console.log(todoList);
		
	todoList.forEach((todo) => {			
		var event = {};
		event.id = todo.id;
		event.title = todo.itemName;
		event.start = todo.expireDate.substr(0, 10);
		event.startStr = todo.expireDate.substr(0, 10);
		if (todo.finishedDate) {
			event.color = TODO_FINISHED_COLOR;			
		} else if (!todo.finishedDate 
					&& (new Date(todo.expireDate).getTime() < new Date().getTime() - 86400000)) {
			event.color = TODO_EXPIRED_COLOR;
		} else {
			event.color = TODO_UNFINISHED_COLOR;
		}
//		event.custom_param = '○'
		event.finishedDate = todo.finishedDate;
		event.expireDate = todo.expireDate;
		todoEvents.push(event);	
			
	});
//	todoEvents.sort((a, b) => {
//		if (a.finishedDate === null && b.finishedDate === null) return 0;
//		if (a.finishedDate === null) return -1; // aを先頭にする
//		if (b.finishedDate === null) return 1; 
//		a.finishedDate > b.finishedDate ? 1 : -1;
//	});
	console.log(todoEvents);
	
	var firstDayNum = settingMap.firstDayOfWeek == "1" ? 1 : 0;
	
	var calendarEl = document.getElementById('calendar');
	
function setCalendar() { 
	
	var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        selectable: true,
        locale: "ja",
        firstDay: firstDayNum,
        dayMaxEvents: 2,
        contentHeight: 'auto',
        headerToolbar: {
	        left: 'title',
	        center: '',
	        right: 'myTodayButton myPrevButton,myNextButton'
        },
        customButtons: {
			myTodayButton: {
		        text: '今日',
		        click: function () {
		            calendar.today(); // 今日に戻す
		            showEventIcon(); // カスタム処理
		            showHolidays();
		        }
	        },
	        myPrevButton: {
				icon: 'chevron-left',
	         	click: function () {
		            calendar.prev(); // 通常の前の月・週・日に移動
		            showEventIcon(); // カスタム処理
		            showHolidays();
	         	}
	        },
	        myNextButton: {
				icon: 'chevron-right',
	         	click: function () {
		            calendar.next(); // 通常の次の月・週・日に移動
		            showEventIcon(); // カスタム処理
		            showHolidays();
	        	}
	        }
        },        
        dayCellContent: function(day) {
		    	return day.date.getDate();
		},
	
        events: todoEvents,
        eventOrder: '-finishedDate',
        
        eventDidMount: function(info) {
		    // イベント要素にIDをdata属性として追加
		    $(info.el).attr('data-event-id', info.event.id);
		},
        
        eventClick: function(e) {
			$(".fc .fc-more-popover").css({'cssText': 'display: none; !important;'});
			$("#event-modal").fadeIn(200);
            $(".modal").fadeIn(200);                        
            $("#event-content").html("");
            $("#no-todo-msg").text("");
            $(".modal").css("overflow-y", "");     
            
            $(".modal").css({'height': '250px','width': '50%', 'max-width': '750px', 'min-width': '400px'});
            
            var clickedEvent = todoEvents.find((todo) =>{
				return todo.id == e.event.id;
			});			
            var finishedDateTxt;
            var finishToggleBtn;
            if (clickedEvent.finishedDate != null) {
				finishedDateTxt = clickedEvent.finishedDate.substr(0, 10) + "完了";
				finishToggleBtn = '<button class="btn btn-outline-danger btn-sm rounded-pill col mx-1" '
				+ 'id="finish-toggle-btn-' + clickedEvent.id + '" '
				+ 'onclick="finishTodo(' + clickedEvent.id + ')">未完了にする</button>';
			} else {
				finishedDateTxt = "未完了";
				finishToggleBtn = '<button class="btn btn-outline-primary btn-sm rounded-pill col mx-1" '
				+ 'id="finish-toggle-btn-' + clickedEvent.id + '" '
				+ 'onclick="finishTodo(' + clickedEvent.id + ')">完了する</button>';
			}
			
			$("#event-date").text(e.event.title);
			if (clickedEvent.tag) {
				$("#event-date").append('<span><i class="mx-2 fa-lg '
				         + clickedEvent.tag.tagIcon
				         + '"></i></span><span class="h4 fw-normal"></span>');
				$("#event-date span i").css("color", clickedEvent.tag.tagColor);
				$("#event-date span:last-child").text(clickedEvent.tag.tagName);
			}
			
			$("#event-content").css("font-size", settingMap.fontSize + "rem"); 
			$("#event-content").append('<tr><td>'
                         + e.event.startStr + "まで"
                         + '</td><td id="finished-date-cell-' + clickedEvent.id + '">'
                         + finishedDateTxt
                         + '</td><td class="text-end">'
                         + finishToggleBtn
                         + '</td></tr>'
                         ); 
            
            $("#todo-transition-btn").text("詳細").removeClass("btn-outline-primary").addClass("btn-outline-success");
            $("#todo-transition-btn").attr("href","/todo/detail/" + e.event.id);
            
			e.jsEvent.stopPropagation(); //クリックイベントの伝播を止めdateClickが発火しないようにする
		},
        
        dateClick: function (info) {
			console.log(info);			
            $("#event-modal").fadeIn(200);
            $(".modal").fadeIn(200);
            
            var clickedDate = info.dateStr.replaceAll("-0", "- ");
            $("#event-date").text(clickedDate.substr(0, 4) + "年"
            					+ clickedDate.substr(5, 2) + "月"
            					+ clickedDate.substr(8, 2) + "日");
            
            $("#event-content").html("");
            $("#no-todo-msg").text("");
            $(".modal").css("overflow-y", "");
            
            var todaysEvents = todoEvents.filter((event) =>{
				return event.start == info.dateStr
			});
			
			todaysEvents.sort((a, b) => {
				if (a.finishedDate === null && b.finishedDate === null) return 0;
				if (a.finishedDate === null) return -1; // aを先頭にする
				if (b.finishedDate === null) return 1; 
				a.finishedDate > b.finishedDate ? 1 : -1;
			});
			console.log(todaysEvents);
			var modalHeight = (todaysEvents.length * 50 + 200) + "px";
			if (todaysEvents.length == 0) {
				$(".modal").css({'height': '250px','width': '400px'});
				$("#no-todo-msg").text("期限が設定された作業はありません");
			} else {
				$(".modal").css({'height': modalHeight , 'width':'50%', 'max-width': '750px', 'min-width': '400px'});
			}
			
			todaysEvents.forEach((event) => {
				
	            var finishedDateTxt;
	            var finishToggleBtn;
	            if (event.finishedDate != null) {
					finishedDateTxt = event.finishedDate.substr(0, 10) + "完了";
					finishToggleBtn = '<button class="btn btn-outline-danger btn-sm rounded-pill col mx-1" '
					+ 'id="finish-toggle-btn-' + event.id + '" '
					+ 'onclick="finishTodo(' + event.id + ')">未完了にする</button>';
				} else {
					finishedDateTxt = "未完了";
					finishToggleBtn = '<button class="btn btn-outline-primary btn-sm rounded-pill col mx-1" '
					+ 'id="finish-toggle-btn-' + event.id + '" '
					+ 'onclick="finishTodo(' + event.id + ')">完了する</button>';
				}
				
				
				$("#event-content").css("font-size", settingMap.fontSize + "rem");
				$("#event-content").append('<tr><td class="title">' 
             	         + '</td><td class="text-end">' + finishToggleBtn //+ '</td>'
             	        // + '<td class="text-center" style="width: 25%">'
             	         + '<a class="btn btn-outline-success btn-sm rounded-pill col mx-1">詳細</a></td></tr>'
                         );
				$("#event-content tr:last-child td:first-child").text(event.title);
				$("#event-content tr:last-child td:last-child a").attr('href', '/todo/detail/' + event.id)
						//.css("font-size", settingMap.fontSize + "rem");
			});
			$("#todo-transition-btn").text("作業登録").removeClass("btn-outline-success").addClass("btn-outline-primary");
			$("#todo-transition-btn").attr("href","/todo/create?expireDate=" + info.dateStr);		
		 }
     });
     
     return calendar;
}

var myCalendar = setCalendar()
myCalendar.render();
showEventIcon();
showHolidays();
	 
	 const observer = new MutationObserver(function(mutationsList) {
		mutationsList.forEach(function(mutation) {
			mutation.addedNodes.forEach(function(node) {
				if (node.nodeType === 1 && node.classList.contains('fc-more-popover')) {
					// ポップオーバーが追加されたとき(表示月の変更時)に、アイコン表示処理を実行
					showEventIcon();
				}
			});
		});
	});

	// body以下を監視対象とする
	observer.observe(document.body, { childList: true, subtree: true });
	 
     $(".btn-close, #modal-close").click(function () {
		$("#event-modal").hide();
	 });
	 
     $(document).click(function (e) {
         if (!$(e.target).closest('.modal').length) {
            $(".modal-wrapper").hide();
         }
     });	 
	 
	 $(".fc-toolbar-chunk:nth-of-type(3)").prepend(
		 '<p>' +
		 '<span><i class="fa-regular fa-face-smile"></i></span>完了 ' +
		 '<span><i class="fa-regular fa-face-meh"></i></span>未完了 ' +
		 '<span><i class="fa-regular fa-face-frown"></i></span>期限切れ</p>');
	
	function showEventIcon() {
		 $(".fc-daygrid-event-harness span").remove()
		 $(".fc-daygrid-event-harness").each(function() {

			  var eventId = $(this).find(".fc-event").data("event-id");
			  var thisEvent = todoList.find(todo => todo.id == eventId);
			  
			 if (thisEvent.tag) {
				 $(this).find(".fc-event").css({'display': 'inline-block', 'width': '80%'});    
				 var tagIcon = '<i class="' + thisEvent.tag.tagIcon + '"></i>';
				 var tagColor = thisEvent.tag.tagColor;
				 
				 $(this).prepend(
				    '<span data-bs-toggle="tooltip" data-bs-placement="left"' +
				    ' style="color: ' +
				    tagColor +
				    '; background-color: rgba(0,0,0,0); display: inline-block; width: 15%;"' 
				    + ' class="text-center">' +
				    tagIcon +
				    '</span>'
			    	);
			    	
			      $(this).find('span').attr('data-bs-title', thisEvent.tag.tagName);

			    }
			});	
			
		const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
		const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))
	}	
	
	async function showHolidays() {
		$('.fc-daygrid-day-top span').remove();
		
		const res = await fetch("https://holidays-jp.github.io/api/v1/date.json");
		const holidays = await res.json();
		
		$('.fc-daygrid-day-top').each(function(){
			var dateStr = $(this).closest('.fc-day').data('date');
			if (dateStr in holidays) {
				$(this).closest('.fc-day').addClass('fc-day-holiday');
				$(this).append('<span class="mt-1 ms-0 text-danger"></span>');
				var holidayName = holidays[dateStr].includes('振替休日')? '振替休日' : holidays[dateStr];
				$(this).find('span').text(holidayName);
			};
		});
		
	}
//}

function finishTodo(todoId) {
	var clickedvent = todoEvents.find((todo) =>{
		return todo.id == todoId;
	});
	console.log(clickedvent);
		
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    
	$.ajax({
            url: "/todo/finishtoggle", 
            method: "PUT",
            data: { todoId: todoId},
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader(header, token);
	        },
            success: function (updatedTodo) {
                console.log(updatedTodo.finishedDate);
                
 				var clickedEvent = todoEvents.find((todo) =>{
					return todo.id == updatedTodo.id;
				});
				console.log(clickedEvent.color, clickedEvent.finishedDate);
				//clickedEvent.color = "black";
				clickedEvent.finishedDate = updatedTodo.finishedDate;
				
				if (clickedEvent.finishedDate) {
					clickedEvent.color = TODO_FINISHED_COLOR;			
				} else if (!clickedEvent.finishedDate 
							&& (new Date(clickedEvent.expireDate).getTime() < new Date().getTime() - 86400000)) {
					clickedEvent.color = TODO_EXPIRED_COLOR;
				} else {
					clickedEvent.color = TODO_UNFINISHED_COLOR;
				}				
						
				
				console.log(clickedEvent.color, clickedEvent.finishedDate);
				
				if(updatedTodo.finishedDate) {
					$('#finished-date-cell-' +  updatedTodo.id).text(updatedTodo.finishedDate.substr(0, 10) + "完了")
					$('#finish-toggle-btn-' + updatedTodo.id)
					.removeClass("btn-outline-primary").addClass("btn-outline-danger").text("未完了にする");
				} else {
					$('#finished-date-cell-' +  updatedTodo.id).text("未完了");
					$('#finish-toggle-btn-' + updatedTodo.id)
					.removeClass("btn-outline-danger").addClass("btn-outline-primary").text("完了する");				
				}
				
//				$(".fc-event").each(function() {
//				   if($(this).data("event-id") == updatedTodo.id) {
//					   $(this).css({"border-color": "black", "background-color": "black"});
//					}	
//				});	

			   
			 	const currentSavedDate = 	new Date(myCalendar.getDate());			
				
				myCalendar = setCalendar();
				myCalendar.render();			    
				myCalendar.gotoDate(currentSavedDate);
				showEventIcon();
				showHolidays();				
				
				
		               
                
            },
            error: function () {
                alert("データの更新に失敗しました");
            }
        });
		
		
}





