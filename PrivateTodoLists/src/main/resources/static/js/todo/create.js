$(document).ready(function() {
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

      const $option = $(`
        <span><i class="${iconClass}" style="margin-right: 1rem; color: ${iconColor};"></i>${option.text}</span>
      `);
      return $option;
    }
  });
  
 