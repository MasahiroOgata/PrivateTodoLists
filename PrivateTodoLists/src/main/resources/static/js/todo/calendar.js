window.onload = function() {
	
	var todoEvents = [];
		
	todoList.forEach((todo) => {
		var event = {};
		event.id = todo.id;
		event.title = todo.itemName;
		event.start = todo.expireDate.substr(0, 10);
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
	
	var calendarEl = document.getElementById('calendar');
	
	var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        selectable: true,
        locale: "ja",
        dayMaxEvents: 2,
        buttonText: {today: '今日'},
        dayCellContent: function(day) {
		    	return day.date.getDate();
		},
	
        events: todoEvents,
        
        dateClick: function (info) {
			console.log(info);
			var clickedDate = info.dateStr;
            $("#event-modal").fadeIn(200);
            $(".modal").fadeIn(200);
            
            $("#event-date").text(clickedDate.substr(0, 4) + "年");
            if (Number(clickedDate.substr(5, 2)) > 9) {
				$("#event-date").append(clickedDate.substr(5, 2) + "月");
			} else {
				$("#event-date").append(" " + clickedDate.substr(6, 1) + "月");
			}
            if(clickedDate.substr(8, 2) > 9) {  
            	$("#event-date").append(clickedDate.substr(8, 2) + "日");
            } else {
				$("#event-date").append(" " + clickedDate.substr(9, 1) + "日");
			}
            
            $("#event-content").html("");
            $("#NoTodoMsg").text('');
            $(".modal").css("overflow-y", "");
            var todaysEvents = todoEvents.filter((event) =>{
				return event.start == clickedDate
			});
			console.log(todaysEvents);
			if (todaysEvents.length == 0) {
				$(".modal").css({'height': '250px','width': '400px'});
				$("#no-todo-msg").text('期限が設定された作業はありません');
			} else {
				$(".modal").css({'height': '50%','width': '50%'});
			}
			if (todaysEvents.length > 7) {
				$(".modal").css("overflow-y", "scroll");
			}
			todaysEvents.forEach((event) => {
				$("#event-content").append('<tr><td>'
                         + event.title
                         + '</td><td class="text-center">'
                         + '<a class="btn btn-outline-success btn-sm rounded-pill col-6" href="/todo/detail/'
                         + event.id
                         + '">詳細</a></td></tr>'
                         ); 
 			});
 			
 			$("#todo-create-btn").attr("href","/todo/create?expireDate=" + clickedDate);		
		 }
		
		
        
     });
     
     calendar.render();	
     
     $(".btn-close").click(function () {
		$("#event-modal").hide();
	 });
	 
	 $(".fc-toolbar-chunk:nth-of-type(3)").prepend(
		 '<p><span>■</span>完了 <span>■</span>未完了 <span>■</span>期限切れ</p>');
	
	
	
}