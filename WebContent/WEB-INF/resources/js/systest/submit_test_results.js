var astAssemblyObj = null;

( function($) {
    $(document).ready(function() {

    	var AstAssemblyClass = function() {
    		this.alertDialog = null;
    		this.sendBtn = null;
    	};
    	
    	AstAssemblyClass.prototype.displaySimpleDialog = function(title, msg) {
    		var self = astAssemblyObj;
			$('#alertDialog').dialog('option', 'title', title);
			$('#alertDialogBody').html(msg);
			$('#alertDialog').dialog('open');
		};
    	
		AstAssemblyClass.prototype.initWidgets = function() {
    		var self = astAssemblyObj;
    		
    		self.alertDialog = $('#alertDialog').dialog({
                autoOpen: false, 
                modal: true,
                buttons: {
                   OK: function() {$(this).dialog('close');}
                }
        	});
    		
    		self.sendBtn = $('#sendBtn').button();
    		self.sendBtn.click(self.loadXML);
    		
	
		};
	
		AstAssemblyClass.prototype.loadCallback = function(data) { 
			var self = astAssemblyObj;
			self.displaySimpleDialog('INFO', ' RESPONSE: ' + data);
			$('#xmlResponse').val(data);
			/*
			if ( data.status == 'FAIL' ) {
				self.displaySimpleDialog('ERROR', data.result.message);
			}
			else {
				self.displaySimpleDialog('INFO', 'XML submit successful');
			}
			*/
		};

		AstAssemblyClass.prototype.loadXML = function() {
			var self = astAssemblyObj;
    
    		self.nodeCount = 0;
    		
    		$.ajax({ 
    			method: 'POST',
				url: cp + '/systest/services/submitTestResultsData.xml', 
				data: { xmlData: $('#xmlData').val() },
				dataType: 'text',
				success: self.loadCallback,
				fail: self.loadCallback
    		});
    		/*
    		.done(self.loadCallback)
    		.fail(function() {
    			self.displaySimpleDialog('ERROR', 'Unknown error sending test result XML.');
    		});
    		*/
    		return true;
		};

		astAssemblyObj = new AstAssemblyClass();
		astAssemblyObj.initWidgets();

    	

        
    }); //document ready
} ) ( jQuery );