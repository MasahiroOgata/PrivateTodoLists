window.onload = function() {
	if (document.getElementById('finishedCheck').checked) {
	document.getElementById('finishedDate').disabled = false;
	}
}
function doChange() {
	var finishedDateValue = document.getElementById('finishedDate').value;
	if (document.getElementById('finishedCheck').checked) {
		document.getElementById('finishedDate').disabled = false;
		if (!finishedDateValue) {
			$('#finishedDate').val(new Date().toLocaleDateString('sv-SE'));
		}
	} else {
		$('#finishedDate').val(null);
		document.getElementById('finishedDate').disabled = true;
	}
	console.log(document.getElementById('finishedDate').value);
}