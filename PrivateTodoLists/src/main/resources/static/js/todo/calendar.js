window.onload = function() {
	
//	var holidayEvents = [];
	
	var holidays = {};
	
	var todoEvents = [];

//    $.ajax({
//          url: 'https://holidays-jp.github.io/api/v1/date.json',
//          type: 'GET',
//          dataType: 'json',
//          timeout: 5000
//    }).done(function(data){
//		 holidays = JSON.parse(JSON.stringify(data));
//		 // Object.keys(data).forEach((key) => {
//		//	  holidays[key] = data[key];
////			  var event = {};
////			  event.start = key;
////			  event.startStr = key;
////			  event.title = data[key];
////			  event.color = TODO_FINISHED_COLOR;
////			  holidayEvents.push(event);	
//		//  });
//		  console.log(holidays);
//		 // console.log(holidayEvents);
//	}).fail(function(data){
//	}).always(function(data){  
//	});
	
	async function loadHolidays() {
	  const res = await fetch("https://holidays-jp.github.io/api/v1/date.json");
	  const data = await res.json();
	
	  return data;
	}
	
	//holidays = loadHolidays();
	
	//console.log(holidays);
	//console.log(holidayEvents);
	
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
		event.custom_param = '○'
//		event.extraParams = {
//	        custom_param1: '○',
//	        custom_param2: 'somethingelse'
//        };
		todoEvents.push(event);	
			
	});
	console.log(todoEvents);
	
	var firstDayNum = settingMap.firstDayOfWeek == "1" ? 1 : 0;
	
	var calendarEl = document.getElementById('calendar');
	
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
	          }
	        },
	        myPrevButton: {
				icon: 'chevron-left',
	         	click: function () {
		            calendar.prev(); // 通常の前の月・週・日に移動
		            showEventIcon(); // カスタム処理
	         	}
	        },
	        myNextButton: {
				icon: 'chevron-right',
	         	click: function () {
		            calendar.next(); // 通常の次の月・週・日に移動 fc-icon fc-icon-chevron-right
		            showEventIcon(); // カスタム処理
	        	}
	        }
        },
        
        dayCellContent: function(day) {
		    	return day.date.getDate();
		},
	
        events: todoEvents,
		
//		events : holidayEvents,
        
        
        eventDidMount: function(info) {
		    // イベント要素にIDをdata属性として追加
		    $(info.el).attr('data-event-id', info.event.id);
//		    console.log(info.event);
//		    console.log(info.event.backgroundColor);
		//    $(info.el).attr('data-event-id', info.event.extraParams);
		//	$(info.el).attr('data-event-param', info.event.extendedProps.custom_param);
			$(info.el).attr('data-event-color', info.event.backgroundColor);			
		},
        
//        eventDisplay: 'list-item',
//        eventTextColor: 'red',
//        eventBackgroundColor: 'green',
//        eventBorderColor: 'white',
        
        eventClick: function(e) {
			$(".fc .fc-more-popover").css({'cssText': 'display: none; !important;'});
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
				finishedDateTxt = clickedEvent.finishedDate.substr(0, 10) + "完了";
			} else {
				finishedDateTxt = "未完了"
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
                         + '</td><td>'
                         + finishedDateTxt
                         + '</td></tr>'
                         ); 
            
            $("#todo-transition-btn").text("詳細").removeClass("btn-outline-primary").addClass("btn-outline-success");
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
				$("#event-content").css("font-size", settingMap.fontSize + "rem");
				$("#event-content").append('<tr><td class="title">' 
             	         + '</td><td class="text-center" style="width: 25%">'
             	         + '<a class="btn btn-outline-success btn-sm rounded-pill col-6">詳細</a></td></tr>'
                         );
                         $("#event-content tr:last-child td:first-child").text(event.title);
                         $("#event-content tr:last-child td:last-child a").attr('href', '/todo/detail/' + event.id)
                         .css("font-size", settingMap.fontSize + "rem");
 			});
 			$("#todo-transition-btn").text("作業登録").removeClass("btn-outline-success").addClass("btn-outline-primary");
 			$("#todo-transition-btn").attr("href","/todo/create?expireDate=" + info.dateStr);		
		 }
     });
     
     calendar.render();
     showEventIcon();
     
     $(".fc-daygrid-more-link").click(function() {
		 console.log('clicked!');
		 showEventIcon();
	 });	
	 
	 $(".fc-popover").mouseover(function() {
		console.log('hover!'); 
	 });
     
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
	
	async function showEventIcon() {
		 $(".fc-daygrid-event-harness span").remove()
		 //$(".fc-popover-body .fc-daygrid-event-harness .fc-event").remove()
		 $(".fc-daygrid-event-harness").each(function() {
			  var eventColor = $(this).find(".fc-event").data("event-color");
			  var eventId = $(this).find(".fc-event").data("event-id");
			  console.log(eventId);
			  var thisEvent = todoList.find(todo => todo.id == eventId);

//			  if (thisEvent.finishedDate) {
//				  var eventIcon = '<i class="fa-regular fa-face-smile"></i>';
//			  } else if (new Date(thisEvent.expireDate).getTime() < new Date().getTime() - 86400000){
//				  var eventIcon = '<i class="fa-regular fa-face-frown"></i>';
//			  } else {
//				  var eventIcon = '<i class="fa-regular fa-face-meh"></i>';
//			  }
			  if (thisEvent.tag) {
				  $(this).find(".fc-event").css({'display': 'inline-block', 'width': '80%'});    
			     var eventIcon = '<i class="' + thisEvent.tag.tagIcon + '"></i>';
			      eventColor = thisEvent.tag.tagColor;
			      
			      //var tagName = text(thisEvent.tag.tagName);
			      //console.log(tagName);
			      
			      //$span = 
			  
				  $(this).prepend(
				    '<span data-bs-toggle="tooltip" data-bs-placement="left" data-bs-title="' +
				    thisEvent.tag.tagName +
				    '" style="color: ' +
				    eventColor +
				    '; background-color: rgba(0,0,0,0); display: inline-block; width: 15%;"' 
				    + ' class="text-center">' +
				    eventIcon +
				    '</span>'
			    	);
			    	
			      $(this).find('span').data('bs-title', thisEvent.tag.tagName);
			     // console.log($(this).find('span').data('bs-title'));
			      $(this).find('span').addClass("sample-class");
			    }
			});	
			
		const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
		const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))

		$('.fc-daygrid-day-top span').remove();
//		$('.fc-daygrid-day-top').append('<span class="mt-1 ms-0 text-danger">holiday title</span>');
		
		holidays = await loadHolidays()
		
//		console.log(typeof holidays);
//		console.log(holidays);
//		console.log(Object.keys(holidays));
//		console.log(("2025-05-05") in holidays);
		
		$('.fc-daygrid-day-top').each(function(){
			var date_str = $(this).closest('.fc-day').data('date');
			if (date_str in holidays) {
				$(this).append('<span class="mt-1 ms-0 text-danger"></span>');
				var holiday_name = holidays[date_str].includes('振替休日')? '振替休日' : holidays[date_str];
				$(this).find('span').text(holiday_name);
			};
		});
		
		// $(".fc-event").css({'display': 'inline-block', 'width': '80%'});
	}	
//	console.log(calendar.currentData.eventSources);
//	 $(".fc").css('background-color', 'rgba(155,155,155,0.95)');
	
}
