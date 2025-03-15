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

        events: todoEvents,
        
        dateClick: function (info) {
		
		}
        
     });
     
     calendar.render();	
     
     $(".btn-close").click(function () {
		$("#event-modal").hide();
	 });
	
}