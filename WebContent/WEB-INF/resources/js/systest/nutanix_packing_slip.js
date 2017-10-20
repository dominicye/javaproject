var astPackingSlipObj = null;

( function($) {
    $(document).ready(function() {

 
    	var AstPackingSlipClass = function() { }; 
    	
    	AstPackingSlipClass.prototype.displaySimpleDialog = function(title, msg) {
    		var self = astPackingSlipObj;
			$('#alertDialog').dialog('option', 'title', title);
			$('#alertDialogBody').html(msg);
			$('#alertDialog').dialog('open');
		};
    	
		AstPackingSlipClass.prototype.initWidgets = function() {
    		var self = astPackingSlipObj;
    		self.alertDialog = $('#alertDialog').dialog({
                autoOpen: false, 
                modal: true,
                buttons: {
                   OK: function() {$(this).dialog('close');}
                }
        	});

    		self.submitBtn = $('#submitBtn').button();
    		self.submitBtn.click(self.submitClick);
    	
		};

		
		AstPackingSlipClass.prototype.submitClick = function(e) {
    		var self = astPackingSlipObj;
    		if ( $('#scn').val() == null || $('#scn').val().trim() == '' ) {
    			self.displaySimpleDialog('ERROR', 'Please supply an SCN.');
    			return false;
    		}
    		//else if ( $('#shipToContactName').val() == null || $('#shipToContactName').val().trim() == '' ) {
    		//	self.displaySimpleDialog('ERROR', 'Please supply a Ship-to Contact Name.');
    		//	return false;
    		//}
    		else {
    			$('#submitForm').submit();
    			return true;
    		}
		};
		
		astPackingSlipObj = new AstPackingSlipClass();
		astPackingSlipObj.initWidgets();
		if ( $('#errorMessage').val() != '' ) {
			astPackingSlipObj.displaySimpleDialog('ERROR', $('#errorMessage').val());
		}
		
		
    }); //document ready
} ) ( jQuery );