
applySettings();
console.log(settingMap);
if($(".badge").text() > 0) {
	$(".badge").show();
}
if ($(".badge").text() >= 100) {
	$(".badge").text("99+");
}


function applySettings() {
	if ('headerBgColor' in settingMap) {
		$(".navbar").css('background-color', settingMap.headerBgColor);
	} else {
		$(".navbar").css('background-color', '#0d6efd');
	}
	
	if ('headerFontColor' in settingMap) {
		$(".navbar-brand").css({'cssText': 'color:' + settingMap.headerFontColor + '!important;'});
	} else {
		$(".navbar-brand").css({'cssText': 'color: #ffffff !important;'});
	}
	
	if ('backgroundImg' in settingMap && settingMap.backgroundImg != '') {
		$(".main").css('background-image', 'url("/img/' + settingMap.imgURL + '")');
	} else {
		$(".main").css('background-image', 'none');
	}
	
	if ('fontSize' in settingMap) {
		$("th, td, input, .modal-body p").css("font-size", settingMap.fontSize + "rem");
		$("a, button").not(".navbar-brand").css("font-size", settingMap.fontSize + "rem");
		$("#todo-table-all, #todo-table-unfinished").css("max-width", settingMap.fontSize * 1000 + "px");
		$(".msg-modal").css('width', settingMap.fontSize * 270 +'px');
		$(".msg-modal").css('height', settingMap.fontSize * 180 +'px');	
	} else {
		$("th, td, input").css("font-size", "1rem");
	}

}
