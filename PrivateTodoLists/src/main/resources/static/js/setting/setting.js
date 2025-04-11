window.onload = function() {
	
	if (flashMsg) {
		  $("#msg-modal").fadeIn(200);
		  $(".modal").fadeIn(200);
	}
	
	applyFormFirstSettings()
}

function applyFormFirstSettings() {
	
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
	
	if (settingMap.fontSize) {
		$('#fontSize input[value="'+ settingMap.fontSize + '"]').prop('checked', true);
	} else {
		$('#fontSize input[value="1"]').prop('checked', true);
	}
	
	if (settingMap.firstDayOfWeek) {
		$('#firstDayOfWeek input[value="'+ settingMap.firstDayOfWeek + '"]').prop('checked', true);
	} else {
		$('#firstDayOfWeek input[value="0"]').prop('checked', true);
	}
	
	applySettings();
	//jsEvent.stopPropagation();
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
	}else if ($("#backgroundImg").val() == 'random') {
		var randomImgNum = Math.floor( Math.random() * imgList.length );
		$(".main").css('background-image', 'url("/img/' + imgList[randomImgNum] + '")');
	} else {
		$(".main").css('background-image', 'url("/img/' + $("#backgroundImg").val() + '")');	
		//$(".main").css({'cssText': 'background-image: url("/img/' + $("#backgroundImg").val() + '") !important;'});	
	}
}

function changeFontSize() {
	var fontSizeNum = $("input[name='fontSize']:checked").val();
	console.log(fontSizeNum);
	$("th, td, input").css("font-size" , fontSizeNum + "rem");
}
