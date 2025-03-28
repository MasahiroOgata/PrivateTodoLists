function changeHeaderBgColor() {
	console.log($("#header-bg-color").val());
	$(".navbar").css({'cssText': 'background-color:' + $("#header-bg-color").val() + '!important;'});
}

function changeHeaderTextColor() {
	console.log($("#header-text-color").val());
	//$(".navbar").css({'cssText': 'background-color:' + $("#header-bg-color").val() + '!important;'});
	$(".navbar-brand").css({'cssText': 'color:' + $("#header-text-color").val() + '!important;'});
}