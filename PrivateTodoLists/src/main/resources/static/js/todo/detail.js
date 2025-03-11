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
		finishedDateValue = document.getElementById('finishedDate').value;
		$('#finishedDate').val(null);
		document.getElementById('finishedDate').disabled = true;
	}
	enableEdit();
}

function enableEdit() {	
	const inputItemName = document.getElementById('itemName').value;
	const inputExpireDate = document.getElementById('expireDate').value;
	const inputFinishedDate = document.getElementById('finishedDate').value;
	
	if (inputItemName == todoForm.itemName 
		&& inputExpireDate == todoForm.expireDate
		&& (inputFinishedDate == todoForm.finishedDate
			|| (!inputFinishedDate && !todoForm.finishedDate))
		) {
		document.getElementById('editButton').disabled = true;
	} else {
		document.getElementById('editButton').disabled = false;
	}
}