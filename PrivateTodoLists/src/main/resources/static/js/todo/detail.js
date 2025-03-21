var finishedDateValue;

window.onload = function() {
	
	console.log(document.getElementById('finishedDate').value);
	if (document.getElementById('finishedDate').value) {
		finishedDateValue = document.getElementById('finishedDate').value;
		document.getElementById('finishedDate').disabled = false;
	} else {
		document.getElementById('finishedCheck').checked = false;
	}
}

function doCheck() {	
	if (document.getElementById('finishedCheck').checked) {
		document.getElementById('finishedDate').disabled = false;
		if (!finishedDateValue) {
			$('#finishedDate').val(new Date().toLocaleDateString('sv-SE'));
		} else {
			$('#finishedDate').val(finishedDateValue);
		}
	} else {
		finishedDateValue = $('#finishedDate').val();
		$('#finishedDate').val(null);
		document.getElementById('finishedDate').disabled = true;
	}
	enableEdit();
}

function enableEdit() {	
	const inputItemName = $('#itemName').val();
	const inputExpireDate = $('#expireDate').val();
	const inputFinishedDate = $('#finishedDate').val();
	
	if (inputItemName == previousTodo.itemName 
		&& inputExpireDate == previousTodo.expireDate
		&& (inputFinishedDate == previousTodo.finishedDate
			|| (!inputFinishedDate && !previousTodo.finishedDate))
			) {
		document.getElementById('editButton').disabled = true;
	} else {
		document.getElementById('editButton').disabled = false;
	}
}

$("#openModal").click(function(){
	$("#delete-confirm-modal").fadeIn(200);
	$(".modal").fadeIn(200);
});

$("#closeModal, .btn-close").click(function() {
	$("#delete-confirm-modal").hide();
});

$(function() {
	$(".modal-wrapper").click(function () {	    
	        $(".modal-wrapper").hide();	    
	});
	$(".modal").click(function(e){
		e.stopPropagation();
	});
});