window.onload = function() {
	
	var todoEvents = [];
		
	todoList.forEach((todo) => {			
		var event = {};
		event.id = todo.id;
		event.title = todo.itemName;
		event.start = todo.expireDate.substr(0, 10);
		event.startStr = todo.expireDate.substr(0, 10);
		if (todo.finishedDate) {
			event.color = "#0d95f0";
		} else if (!todo.finishedDate 
					&& (new Date(todo.expireDate).getTime() < new Date().getTime() - 86400000)) {
			event.color = "#dc3545";
		} else {
			event.color = "#fd7e14"
		}
		todoEvents.push(event);		
	});
	
	var firstDayNum = settingMap.firstDayOfWeek == "1" ? 1 : 0;
	
	var calendarEl = document.getElementById('calendar');
	
	var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        selectable: true,
        locale: "ja",
        firstDay: firstDayNum,
        dayMaxEvents: 2,
        buttonText: {today: '今日'},
        dayCellContent: function(day) {
		    	return day.date.getDate();
		},
	
        events: todoEvents,
        
        eventClick: function(e) {
			$("#event-modal").fadeIn(200);
            $(".modal").fadeIn(200);                        
            $("#event-content").html("");
            $("#no-todo-msg").text("");
            $(".modal").css("overflow-y", "");     
            
            $(".modal").css({'height': '250px','width': '50%', 'max-width': '750px', 'min-width': '400px'});
            
            var clickedEvent = todoList.find((todo) =>{
				return todo.id == e.event.id;
			});			
            var finishedDateTxt;
            if (clickedEvent.finishedDate != null) {
				finishedDateTxt = clickedEvent.finishedDate + "完了";
			} else {
				finishedDateTxt = "未完了"
			}
			
			$("#event-date").text(e.event.title); 
            $("#event-content").append('<tr><td>'
                         + e.event.startStr + "まで"
                         + '</td><td>'
                         + finishedDateTxt
                         + '</td></tr>'
                         ); 
            
            $("#todo-transition-btn").text("詳細").removeClass("btn-primary").addClass("btn-outline-success");
            $("#todo-transition-btn").attr("href","/todo/detail/" + e.event.id);
            
			e.jsEvent.stopPropagation(); //クリックイベントの伝播を止める。
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
			console.log(todaysEvents);
			if (todaysEvents.length == 0) {
				$(".modal").css({'height': '250px','width': '400px'});
				$("#no-todo-msg").text("期限が設定された作業はありません");
			} else {
				$(".modal").css({'height': '400px', 'width':'50%', 'max-width': '750px', 'min-width': '400px'});
			}

			todaysEvents.forEach((event) => {
				
				$("#event-content").append('<tr><td class="title">' 
             	         +'</td><td class="text-center" style="width: 25%">'
                         + '<a class="btn btn-outline-success btn-sm rounded-pill col-6" href="/todo/detail/'
                         + event.id
                         + '">詳細</a></td></tr>'
                         );
                         $("#event-content tr:last-child td:first-child").text(event.title);
//                         $(".title").text(event.title);
//                         $("td").removeClass("title"); 
 			});
 			$("#todo-transition-btn").text("作業登録").removeClass("btn-outline-success").addClass("btn-primary");
 			$("#todo-transition-btn").attr("href","/todo/create?expireDate=" + info.dateStr);		
		 }
     });
     
     
//     $("<span>●</span>").insertBefore(".fc-event-title fc-sticky");
     
     calendar.render();	
     
     $(".btn-close, #modal-close").click(function () {
		$("#event-modal").hide();
	 });
	 
     $(document).click(function (e) {
         if (!$(e.target).closest('.modal').length) {
            $(".modal-wrapper").hide();
         }
     });	 
	 
	 $(".fc-toolbar-chunk:nth-of-type(3)").prepend(
		 '<p><span>■</span>完了 <span>■</span>未完了 <span>■</span>期限切れ</p>');
		 

	
//	 $(".fc").css('background-color', 'rgba(155,155,155,0.95)');
	
}

//	 $(".fc-event").prepend(
//		"<span>●</span>");	
//	 	 
//	 $(".fc-event-main").prepend(
//		"<span>●</span>");	

    $("<span>●</span>").insertBefore(".fc-event-title-container");
    
     $("<span>〇</span>").insertBefore(".fc-sticky");
	 	 
	 $(".fc-event-main-frame").prepend("<span>●</span>");
	 
	 $(".fc-event").prepend("<span>●</span>");