$(document).ready(function() {
	
//	$(".select2-selection__rendered, .select2-results__options").css({'cssText': 'font-size:' + settingMap.fontSize + 'rem !important;'});
//	$(".select2-results__options").css({'cssText': 'font-size:' + settingMap.fontSize + 'rem !important;'});	
	
    $('#tag-select').select2({
		width: '100%',
    	templateResult: formatOption,
    	templateSelection: formatOption
    });
    
    function formatOption(option) {
		
		console.log(option);
      if (!option.id) {
        return option.text;
      }

      const iconClass = $(option.element).data('icon');
      if (!iconClass) {
        return option.text;
      }
      const iconColor = $(option.element).data('color');

      const $option = $('<span></span>');
      $option.text(option.text);
      $option.prepend(`<i class="${iconClass}" style="margin-right: 1rem; color: ${iconColor};"></i>`)
      return $option;
    }
    
});
  
 