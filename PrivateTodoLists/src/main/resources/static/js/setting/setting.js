window.onload = function() {
	
	if (flashMsg) {
		  $("#msg-modal").fadeIn(200);
		  $(".modal").fadeIn(200);
	}
	
	applyFormPresentSettings()
	console.log($(".navbar-brand").css('font-family'));
}

function applyFormPresentSettings() {
	
	var headerBgColor = settingMap.headerBgColor || '#0d6efd';
	$("#headerBgColor").val(headerBgColor);
	
	var headerFontColor = settingMap.headerFontColor || '#ffffff';
	$("#headerFontColor").val(headerFontColor);

	var backgroundImg = settingMap.backgroundImg || 'none';
	$("#backgroundImg").val(backgroundImg);
	
	var fontFamily = settingMap.fontFamily || 'system-ui';
	$('#fontFamily input[value="'+ fontFamily + '"]').prop('checked', true);
	
	var fontSize = settingMap.fontSize || '1';
	$('#fontSize input[value="'+ fontSize + '"]').prop('checked', true);
	
	var firstDayOfWeek = settingMap.firstDayOfWeek || '0';
	$('#firstDayOfWeek input[value="'+ firstDayOfWeek + '"]').prop('checked', true);
	
	var isHidingFinishedTodo = settingMap.isHidingFinishedTodo || '0';
	$('#isHidingFinishedTodo input[value="' + isHidingFinishedTodo + '"]').prop('checked', true);
	
	applySettings();
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

function changeFontFamily() {	
	var navbarStyle = $(".navbar-brand").attr('style');
	var fontFamily = $("input[name='fontFamily']:checked").val();
	$(".main").not('#fontFamily').css("font-family", fontFamily);
	$(".navbar-brand").css({'cssText': navbarStyle + 'font-family:' + fontFamily + '!important;'});
	
}

function changeFontSize() {
	var fontSizeNum = $("input[name='fontSize']:checked").val();
	$("th, td, input").css("font-size" , fontSizeNum + "rem");
	$(".form-setting").css("max-width", fontSizeNum * 700 + "px");
}
