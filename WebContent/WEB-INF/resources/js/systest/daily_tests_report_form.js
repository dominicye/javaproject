var astReportObj = null;

( function($) {
    $(document).ready(function() {

 
    	var AstReportClass = function() { }; 
    	
    	AstReportClass.prototype.displaySimpleDialog = function(title, msg) {
    		var self = astReportObj;
			$('#alertDialog').dialog('option', 'title', title);
			$('#alertDialogBody').html(msg);
			$('#alertDialog').dialog('open');
		};
    	
		AstReportClass.prototype.initWidgets = function() {
    		var self = astReportObj;
    		self.alertDialog = $('#alertDialog').dialog({
                autoOpen: false, 
                modal: true,
                buttons: {
                   OK: function() {$(this).dialog('close');}
                }
        	});
    		
    		$('#testDate').datepicker({dateFormat: 'dd-M-yy'});
    		self.submitBtn = $('#submitBtn').button();
    		self.submitBtn.click(self.submitClick);
    	
		};

		
		AstReportClass.prototype.submitClick = function(e) {
    		var self = astReportObj;
    		var testDate = $('#testDate').val();
    		if ( testDate == '' ) {
    			astReportObj.displaySimpleDialog('ERROR', 'Please select a date.');
    			return false;
    		}
    		//$("#loading-div-background").show();
			$('#submitForm').submit();
			//$("#loading-div-background").hide();
			return true;
		};
		
		astReportObj = new AstReportClass();
		astReportObj.initWidgets();
		if ( $('#errorMessage').val() != '' ) {
			astReportObj.displaySimpleDialog('ERROR', $('#errorMessage').val());
		}
		
		
    }); //document ready
} ) ( jQuery );