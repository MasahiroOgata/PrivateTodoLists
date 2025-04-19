window.onload = () => {
	changeTagColor()
	$("#icon-display span").text("");
	$("#icon-display i").removeClass();
	$("#icon-display i").addClass($("#icon-select input:checked").val()).addClass("fa-2xl")
	console.log(previousTag);
}

function changeTagColor() {
	$("#icon-display i").css('color', $("input[type=color]").val());
	$("i").css('color', $("input[type=color]").val());
}

$("#icon-select input").change(function() {

	console.log($(this).val());
	$("#icon-display span").text("");
	$("#icon-display i").removeClass();
	$("#icon-display i").addClass($(this).val()).addClass("fa-2xl")
});



function enableEdit() {	
	const inputTagName = $('#tagName').val();
	const inputTagColor = $('#tagColor').val();
	const inputTagIcon = $("#icon-select input:checked").val()
	
	if (inputTagName == previousTag.tagName
		&& inputTagColor == previousTag.tagColor
		&& inputTagIcon == previousTag.tagIcon) {
			
		$("input[type=submit]").prop( 'disabled', true);
	} else {
		$("input[type=submit]").prop( 'disabled', false);
	}
}