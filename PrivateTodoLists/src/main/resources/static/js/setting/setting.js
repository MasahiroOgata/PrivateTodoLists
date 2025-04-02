window.onload = function() {
	
	if(settingMap.headerBgColor) {
		$("#headerBgColor").val(settingMap.headerBgColor);
	} else {
		$("#headerBgColor").val('#0d6efd')
	}
	
	if(settingMap.headerFontColor) {
		$("#headerFontColor").val(settingMap.headerFontColor);
	} else {
		$("#headerFontColor").val('#ffffff')
	}


	

	
}

function changeHeaderBgColor() {
	
	$(".navbar").css({'cssText': 'background-color:' + $("#headerBgColor").val() + '!important;'});
}

function changeHeaderTextColor() {
	
	$(".navbar-brand").css({'cssText': 'color:' + $("#headerFontColor").val() + '!important;'});
}

function changeBackgroundImg() {
	
	if($("#backgroundImg").val()) {	
		$(".main").css({'cssText': 'background-image: url("/img/' + $("#backgroundImg").val() + '") !important;'});
	} else {
		$(".main").css('background-image', 'none');
	}
}