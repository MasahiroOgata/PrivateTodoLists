
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
	$(".main").css('background-image', 'url("/img/' + settingMap.backgroundImg + '")');
} else {
	$(".main").css('background-image', 'none');
}
	
//	$(".navbar").css({'cssText': 'background-color:' + settingMap.headerBgColor + '!important;'});
//	$(".navbar-brand").css({'cssText': 'color:' + settingMap.headerFontColor + '!important;'});

$("th, td, input").addClass("fs-6");
