
applySettings();
console.log(settingMap);
if($(".badge").text() > 0) {
	$(".badge").show();
}
if ($(".badge").text() >= 100) {
	$(".badge").text("99+");
}


function applySettings() {
	
	//var headerBgColor = 'headerBgColor' in settingMap ? settingMap.headerBgColor : '#0d6efd';
	var headerBgColor = settingMap.headerBgColor || '#0d6efd';
	$(".navbar").css('background-color', headerBgColor);
	
	//var headerFontColor = 'headerFontColor' in settingMap ? settingMap.headerFontColor : '#ffffff';
	var headerFontColor = settingMap.headerFontColor || '#ffffff';
	$(".navbar-brand").css({'cssText': 'color:' + headerFontColor + '!important;'});
	
	var imgURL = settingMap.imgURL == 'none' ? 'none' : 'url("/img/' + settingMap.imgURL + '")';
	$(".main").css('background-image', imgURL);
	
	//var fontFamily = 'fontFamily' in settingMap ? settingMap.fontFamily : 'system-ui';
	var fontFamily = settingMap.fontFamily || 'system-ui';
	var navbarStyle = $(".navbar-brand").attr('style');
	$(".main").css("font-family", fontFamily);
	$(".navbar-brand").css({'cssText': navbarStyle + 'font-family:' + fontFamily + '!important;'});
	
	//var fontSize = 'fontSize' in settingMap ? settingMap.fontSize : '1';
	var fontSize =  settingMap.fontSize || '1';
	$("th, td, input, select, .select2, .modal-body p").css("font-size", fontSize + "rem");
	$("a, button").not(".navbar-brand").css("font-size", fontSize + "rem");
	$(".select2-container, .select2-selection--single").css({'cssText': 'font-size:' + fontSize + 'rem !important;'});
	$(".select2-selection__rendered, .select2-results__options").css({'cssText': 'font-size:' + fontSize + 'rem !important;'});
	$("#todo-table-all, #todo-table-unfinished").css("max-width", fontSize * 1000 + "px");
	$(".form-setting").css("max-width", fontSize * 700 + "px");
	$(".msg-modal").css({'width': fontSize * 270 +'px', 'height':fontSize * 180 +'px'});
	
}
