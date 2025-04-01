window.onload = function() {
	
	if(settingMap.headerBgColor) {
		$("#headerBgColor").val(settingMap.headerBgColor);
	} else {
		$("#headerBgColor").val('#0d6efd')
	}
	
	if(settingMap.headerFontColor) {
		$("#headerFontColor").val(settingMap.headerFontColor);
	} else {
		$("#headerBgColor").val('#000000')
	}


	

	
}

function changeHeaderBgColor() {
	
	$(".navbar").css({'cssText': 'background-color:' + $("#headerBgColor").val() + '!important;'});
}

function changeHeaderTextColor() {
	
	//$(".navbar").css({'cssText': 'background-color:' + $("#header-bg-color").val() + '!important;'});
	$(".navbar-brand").css({'cssText': 'color:' + $("#headerFontColor").val() + '!important;'});
}