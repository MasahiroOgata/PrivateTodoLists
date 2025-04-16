function changeTagColor() {
	$("#icon-display i").css('color', $("input[type=color]").val());
	$("i").css('color', $("input[type=color]").val());
}

$("#icon-select input").change(function() {
//	console.log($(this).next().attr("class"));
	console.log($(this).val());
//	console.log($("label[for=" +$(this).attr('id') + "] i").attr("class"));
	$("#icon-display span").text("");
	$("#icon-display i").removeClass();
	$("#icon-display i").addClass($(this).val()).addClass("fa-2xl")
//	$("#icon-display i").addClass($(this).next().attr("class"))
//	.removeClass("fa-xl").addClass("fa-2xl");
});