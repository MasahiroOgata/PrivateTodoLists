window.onload = function() {
	
	if (flashMsg) {
		  $("#msg-modal").fadeIn(200);
		  $(".modal").fadeIn(200);
	}
	
	if(settingMap.headerBgColor) {
		$("#headerBgColor").val(settingMap.headerBgColor);
	} else {
		$("#headerBgColor").val('#0d6efd');
	}
	
	if(settingMap.headerFontColor) {
		$("#headerFontColor").val(settingMap.headerFontColor);
	} else {
		$("#headerFontColor").val('#ffffff');
	}

	if (settingMap.backgroundImg) {
		$("#backgroundImg").val(settingMap.backgroundImg);
	} else {
		$("#backgroundImg").val("");
	}
	


}

$("#close-modal, .btn-close").click(function () {
	$("#msg-modal").hide();
});

function changeHeaderBgColor() {
	
	$(".navbar").css({'cssText': 'background-color:' + $("#headerBgColor").val() + '!important;'});
}

function changeHeaderTextColor() {
	
	$(".navbar-brand").css({'cssText': 'color:' + $("#headerFontColor").val() + '!important;'});
}

function changeBackgroundImg() {
	if (!$("#backgroundImg").val()) {
		$(".main").css('background-image', 'none');
	} else {
		$(".main").css('background-image', 'url("/img/' + $("#backgroundImg").val() + '")');	
		//$(".main").css({'cssText': 'background-image: url("/img/' + $("#backgroundImg").val() + '") !important;'});	
	}
}