	
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
//	$(".navbar").css({'cssText': 'background-color:' + settingMap.headerBgColor + '!important;'});
//	$(".navbar-brand").css({'cssText': 'color:' + settingMap.headerFontColor + '!important;'});

