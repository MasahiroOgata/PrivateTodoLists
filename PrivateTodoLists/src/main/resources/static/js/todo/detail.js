var finishedDateValue;

window.onload = function() {
	
	console.log(previousTodo);
	
	console.log(document.getElementById('finishedDate').value);
	if (document.getElementById('finishedDate').value) {
		finishedDateValue = document.getElementById('finishedDate').value;
		document.getElementById('finishedDate').disabled = false;
	} else {
		document.getElementById('finishedCheck').checked = false;
	}
}

$(document).ready(function() {
    $('#tag-select').select2({
		width: '100%',
    	templateResult: formatOption,
    	templateSelection: formatOption
    });
    
    function formatOption(option) {
		
		//console.log(option);
      if (!option.id) {
        return option.text;
      }

      const iconClass = $(option.element).data('icon');
      if (!iconClass) {
        return option.text;
      }
      const iconColor = $(option.element).data('color');

      const $option = $(`
        <span><i class="${iconClass}" style="margin-right: 1rem; color: ${iconColor};"></i>${option.text}</span>
      `);
      return $option;
    }
  });
  

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
	const inputTagId = $('#tag-select').val();
	
	if (inputItemName == previousTodo.itemName 
		&& inputExpireDate == previousTodo.expireDate
		&& (inputFinishedDate == previousTodo.finishedDate
			|| (!inputFinishedDate && !previousTodo.finishedDate))
		&& (inputTagId == previousTodo.tagId
			|| (!inputTagId && !previousTodo.tagId))
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