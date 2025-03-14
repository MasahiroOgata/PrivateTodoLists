window.onload = function() {
	if (flashMsg) {
		  $("#msgModal").fadeIn(200);
		  $(".modal").fadeIn(200);
	}
}
            
$("#closeModal, .btn-close").click(function () {
	$("#msgModal").hide();
});