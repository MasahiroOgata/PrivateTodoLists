
applySettings();
console.log(settingMap);
if($(".badge").text() > 0) {
	$(".badge").show();
}
if ($(".badge").text() >= 100) {
	$(".badge").text("99+");
}


function applySettings() {
	console.log(imgList);
	
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
	
	if ('imgURL' in settingMap) {
		$(".main").css('background-image', 'url("/img/' + settingMap.imgURL + '")');
//	} else if (settingMap.backgroundImg == 'random'){
//		var randomImgNum = Math.floor( Math.random() * imgList.length );
//		$(".main").css('background-image', 'url("/img/' + imgList[randomImgNum] + '")');
//		var randomImgNum = Math.floor( Math.random() * 8 + 1 );
//		$(".main").css('background-image', 'url("/img/img0' + randomImgNum + '.jpg")');
//	} else if (settingMap.backgroundImg) {
//		$(".main").css('background-image', 'url("/img/' + settingMap.backgroundImg + '")');
	} else {
		$(".main").css('background-image', 'none');
	}
	
	if ('fontFamily' in settingMap) {
		$(".main").css("font-family", settingMap.fontFamily);
		$(".navbar-brand").css({'font-family': 'color:' + settingMap.fontFamily + '!important;'});
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
