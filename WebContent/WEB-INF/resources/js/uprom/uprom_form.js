var upromAppObj;

( function($) {
    $(document).ready(function() {
    	
    	var UpromAppClass = function() {
    		this.linkSheetUploadObj = null;
    		this.zippedUploadObj = null;
    		this.advancedUploadObj = null;
    		this.progFileUploadObj = null;
    		this.taskFileUploadObj = null;
    		this.labelFileUploadObj = null;
    	};

    	UpromAppClass.prototype.initializeUploaderWidgets = function() {	
    		var self = this;
    		
    		self.linkSheetUploadObj = $("#linkSheetUpload").uploadFile({
	    		method: "POST",
	    		url: cp + "/uprom/linkSheetUpload.json",
	    		acceptFiles: "*",
	    		allowedTypes: "xls,xlsx",
	    		returnType: "json",
	    		multiple:false,
	    		autoSubmit:false,
	    		fileName:"excelfile",
	    		dynamicFormData: function()
	    		{
	    			var data = { 
	    	    			userId: $('#userId').val()
	    	    		};
	    			return data;
	    		},
	    		maxFileSize:1000000000 * 3, //3GB max
	    		maxFileCount:1,
	    		showStatusAfterSuccess:true,
	    		showProgress:true,
	    		showCancel:true,
	    		showDelete:false,
	    		showAbort:false,
	    		showDone:false,
	    		dragDropStr: "<span><b>You may drag & drop an Excel file here.</b></span>",
	    		abortStr:"Abort",
	    		cancelStr:"Remove",
	    		doneStr:"Done",
	    		multiDragErrorStr: "Drag and drop error.",
	    		extErrorStr:" - You must supply an Excel file: *.",
	    		sizeErrorStr:"File size is too large.",
	    		uploadErrorStr:"Upload error.",
	    		onSubmit:function(files)
	    		{
	    			self.appendMessages('INFO', "Starting upload: " + JSON.stringify(files));
	    		},
	    		onSuccess:function(files,data,xhr,arguments)
	    		{
	    			var pb = arguments.progressbar[0];
	    			if ( data.status != 'SUCCESS' ) {
	    				$(pb).css("background-color", "#c71114");
	    				self.appendMessages('ERROR', data.result);
	            	}
	            	else {
	            		$(pb).css("background-color", "#339900");
	            		self.appendMessages('SUCCESS', data.result);
	            	}
	    			
	    		},
	    		onError: function(files,status,errMsg)
	    		{
	    			self.appendMessages('ERROR', errMsg + ": " + JSON.stringify(files));
	    		}

	    	});
    		
    		self.zippedUploadObj = $("#zippedUpload").uploadFile({
	    		method: "POST",
	    		url: cp + "/uprom/appletUpload.json",
	    		acceptFiles: "*",
	    		allowedTypes: "zip",
	    		returnType: "json",
	    		multiple:false,
	    		autoSubmit:false,
	    		fileName:"zipfile",
	    		dynamicFormData: function()
	    		{
	    			var data = { 
	    	    			userId: $('#userId').val(),
	    					entireDirectoryFlag: $('#entireDirectoryFlag').val(),
	    					overwriteFlag: $('#overwriteFlag').val(),
	    					sequentialFlag: $('#sequentialFlag').val(),
	    					serialNumber: $('#serialNumber').val(), 
	    					translatedPath: $('#translatedPath').val(),
	    					siteParam: $('#siteParam').val(),
	    					siteDescParam: $('#siteDescParam').val()
	    	    		};
	    			return data;
	    		},
	    		maxFileSize:1000000000 * 3, //3GB max
	    		maxFileCount:1,
	    		showStatusAfterSuccess:true,
	    		showProgress:true,
	    		showCancel:true,
	    		showDelete:false,
	    		showAbort:false,
	    		showDone:false,
	    		dragDropStr: "<span><b>You may drag & drop the zipped directory file here.</b></span>",
	    		abortStr:"Abort",
	    		cancelStr:"Remove",
	    		doneStr:"Done",
	    		multiDragErrorStr: "Drag and drop error.",
	    		extErrorStr:" - You must supply a zipped directory file: *.",
	    		sizeErrorStr:"File size is too large.",
	    		uploadErrorStr:"Upload error.",
	    		onSubmit:function(files)
	    		{
	    			self.appendMessages('INFO', "Starting upload: " + JSON.stringify(files));
	    		},
	    		onSuccess:function(files,data,xhr,arguments)
	    		{
	    			var pb = arguments.progressbar[0];
	    			if ( data.status != 'SUCCESS' ) {
	    				$(pb).css("background-color", "#c71114");
	    				self.appendMessages('ERROR', data.result.message + ": " + JSON.stringify(files));
	            	}
	            	else {
	            		$(pb).css("background-color", "#339900");
	            		self.appendMessages('SUCCESS', data.result + ": " + JSON.stringify(files));
	            		self.sendUploadCompleteEmail(JSON.stringify(files));
	            	}
	    			
	    		},
	    		onError: function(files,status,errMsg)
	    		{
	    			self.appendMessages('ERROR', errMsg + ": " + JSON.stringify(files));
	    		}

	    	});
	    	
    		self.advancedUploadObj = $("#advancedUpload").uploadFile({
	    		method: "POST",
	    		url: cp + "/uprom/appletUpload.json",
	    		acceptFiles: "*",
	    		returnType: "json",
	    		multiple:true,
	    		autoSubmit:false,
	    		fileName:"upromfiles",
	    		dynamicFormData: function()
	    		{
	    			var data = { 
	    	    			userId: $('#userId').val(),
	    					entireDirectoryFlag: $('#entireDirectoryFlag').val(),
	    					overwriteFlag: $('#overwriteFlag').val(),
	    					sequentialFlag: $('#sequentialFlag').val(),
	    					serialNumber: $('#serialNumber').val(), 
	    					translatedPath: $('#translatedPath').val(),
	    					siteParam: $('#siteParam').val(),
	    					siteDescParam: $('#siteDescParam').val()
	    	    		};
	    			return data;
	    		},
	    		maxFileSize:1000000000 * 3, //3GB max
	    		maxFileCount:-1,
	    		showStatusAfterSuccess:true,
	    		showProgress:true,
	    		showCancel:true,
	    		showDelete:false,
	    		showAbort:false,
	    		showDone:false,
	    		dragDropStr: "<span><b>You may also drag & drop multiple files here.</b></span>",
	    		abortStr:"Abort",
	    		cancelStr:"Remove",
	    		doneStr:"Done",
	    		multiDragErrorStr: "Drag and drop error.",
	    		extErrorStr:"File type extension is prohibited.",
	    		sizeErrorStr:"File size is too large.",
	    		uploadErrorStr:"Upload error.",
	    		//uploadButtonClass: "",
	    		onSubmit:function(files)
	    		{
	    			self.appendMessages('INFO', "Starting upload: " + JSON.stringify(files));
	    		},
	    		onSuccess:function(files,data,xhr,arguments)
	    		{
	    			var pb = arguments.progressbar[0];
	    			if ( data.status != 'SUCCESS' ) {
	    				$(pb).css("background-color", "#c71114");
	    				self.appendMessages('ERROR', data.result.message + ": " + JSON.stringify(files));
	            	}
	            	else {
	            		$(pb).css("background-color", "#339900");
	            		self.appendMessages('SUCCESS', data.result + ": " + JSON.stringify(files));
	            		self.sendUploadCompleteEmail(JSON.stringify(files));
	            	}
	    			
	    		},
	    		//afterUploadAll:function()
	    		//{
	    			//$("#uploadEventsMessage").html($("#uploadEventsMessage").html()+"<br/> Upload operation completed.");
	    		//	self.appendMessages('INFO', "Upload operation completed.");
	    		//},
	    		onError: function(files,status,errMsg)
	    		{
	    			//$("#uploadEventsMessage").html($("#uploadEventsMessage").html()+"<br/>Error for: "+JSON.stringify(files));
	    			self.appendMessages('ERROR', errMsg + ": " + JSON.stringify(files));
	    		}

	    	});
    		
    		self.progFileUploadObj = $("#progFileUpload").uploadFile({
	    		method: "POST",
	    		url: cp + "/uprom/appletUpload.json",
	    		acceptFiles: "*",
	    		returnType: "json",
	    		multiple:false,
	    		autoSubmit:false,
	    		fileName:"progFile",
	    		dynamicFormData: function()
	    		{
	    			var data = { 
	    	    			userId: $('#userId').val(),
	    					entireDirectoryFlag: $('#entireDirectoryFlag').val(),
	    					overwriteFlag: $('#overwriteFlag').val(),
	    					sequentialFlag: $('#sequentialFlag').val(),
	    					serialNumber: $('#serialNumber').val(), 
	    					translatedPath: $('#translatedPath').val(),
	    					siteParam: $('#siteParam').val(),
	    					siteDescParam: $('#siteDescParam').val()
	    	    		};
	    			return data;
	    		},
	    		maxFileSize:1000000000 * 3, //3GB max
	    		maxFileCount:1,
	    		showStatusAfterSuccess:true,
	    		showProgress:true,
	    		showCancel:true,
	    		showDelete:false,
	    		showAbort:false,
	    		showDone:false,
	    		dragDropStr: "<span><b>You may also drag & drop file here.</b></span>",
	    		abortStr:"Abort",
	    		cancelStr:"Remove",
	    		doneStr:"Done",
	    		multiDragErrorStr: "Drag and drop error.",
	    		extErrorStr:"File type extension is prohibited.",
	    		sizeErrorStr:"File size is too large.",
	    		uploadErrorStr:"Upload error.",
	    		onSubmit:function(files)
	    		{
	    			self.appendMessages('INFO', "Starting upload: " + JSON.stringify(files));
	    		},
	    		onSuccess:function(files,data,xhr,arguments)
	    		{
	    			var pb = arguments.progressbar[0];
	    			if ( data.status != 'SUCCESS' ) {
	    				$(pb).css("background-color", "#c71114");
	    				self.appendMessages('ERROR', data.result.message + ": " + JSON.stringify(files));
	            	}
	            	else {
	            		$(pb).css("background-color", "#339900");
	            		self.appendMessages('SUCCESS', data.result + ": " + JSON.stringify(files));
	            		self.sendUploadCompleteEmail(JSON.stringify(files));
	            	}
	    			
	    		},
	    		onError: function(files,status,errMsg)
	    		{
	    			self.appendMessages('ERROR', errMsg + ": " + JSON.stringify(files));
	    		}

	    	});
    		
    		self.taskFileUploadObj = $("#taskFileUpload").uploadFile({
	    		method: "POST",
	    		url: cp + "/uprom/appletUpload.json",
	    		acceptFiles: "*",
	    		returnType: "json",
	    		multiple:false,
	    		autoSubmit:false,
	    		fileName:"taskFile",
	    		dynamicFormData: function()
	    		{
	    			var data = { 
	    	    			userId: $('#userId').val(),
	    					entireDirectoryFlag: $('#entireDirectoryFlag').val(),
	    					overwriteFlag: $('#overwriteFlag').val(),
	    					sequentialFlag: $('#sequentialFlag').val(),
	    					serialNumber: $('#serialNumber').val(), 
	    					translatedPath: $('#translatedPath').val(),
	    					siteParam: $('#siteParam').val(),
	    					siteDescParam: $('#siteDescParam').val()
	    	    		};
	    			return data;
	    		},
	    		maxFileSize:1000000000 * 3, //3GB max
	    		maxFileCount:1,
	    		showStatusAfterSuccess:true,
	    		showProgress:true,
	    		showCancel:true,
	    		showDelete:false,
	    		showAbort:false,
	    		showDone:false,
	    		dragDropStr: "<span><b>You may also drag & drop file here.</b></span>",
	    		abortStr:"Abort",
	    		cancelStr:"Remove",
	    		doneStr:"Done",
	    		multiDragErrorStr: "Drag and drop error.",
	    		extErrorStr:"File type extension is prohibited.",
	    		sizeErrorStr:"File size is too large.",
	    		uploadErrorStr:"Upload error.",
	    		onSubmit:function(files)
	    		{
	    			self.appendMessages('INFO', "Starting upload: " + JSON.stringify(files));
	    		},
	    		onSuccess:function(files,data,xhr,arguments)
	    		{
	    			var pb = arguments.progressbar[0];
	    			if ( data.status != 'SUCCESS' ) {
	    				$(pb).css("background-color", "#c71114");
	    				self.appendMessages('ERROR', data.result.message + ": " + JSON.stringify(files));
	            	}
	            	else {
	            		$(pb).css("background-color", "#339900");
	            		self.appendMessages('SUCCESS', data.result + ": " + JSON.stringify(files));
	            		self.sendUploadCompleteEmail(JSON.stringify(files));
	            	}
	    			
	    		},
	    		onError: function(files,status,errMsg)
	    		{
	    			self.appendMessages('ERROR', errMsg + ": " + JSON.stringify(files));
	    		}

	    	});
    		
    		self.labelFileUploadObj = $("#labelFileUpload").uploadFile({
	    		method: "POST",
	    		url: cp + "/uprom/appletUpload.json",
	    		acceptFiles: "*",
	    		returnType: "json",
	    		multiple:false,
	    		autoSubmit:false,
	    		fileName:"labelFile",
	    		dynamicFormData: function()
	    		{
	    			var data = { 
	    	    			userId: $('#userId').val(),
	    					entireDirectoryFlag: $('#entireDirectoryFlag').val(),
	    					overwriteFlag: $('#overwriteFlag').val(),
	    					sequentialFlag: $('#sequentialFlag').val(),
	    					serialNumber: $('#serialNumber').val(), 
	    					translatedPath: $('#translatedPath').val(),
	    					siteParam: $('#siteParam').val(),
	    					siteDescParam: $('#siteDescParam').val()
	    	    		};
	    			return data;
	    		},
	    		maxFileSize:1000000000 * 3, //3GB max
	    		maxFileCount:1,
	    		showStatusAfterSuccess:true,
	    		showProgress:true,
	    		showCancel:true,
	    		showDelete:false,
	    		showAbort:false,
	    		showDone:false,
	    		dragDropStr: "<span><b>You may also drag & drop file here.</b></span>",
	    		abortStr:"Abort",
	    		cancelStr:"Remove",
	    		doneStr:"Done",
	    		multiDragErrorStr: "Drag and drop error.",
	    		extErrorStr:"File type extension is prohibited.",
	    		sizeErrorStr:"File size is too large.",
	    		uploadErrorStr:"Upload error.",
	    		onSubmit:function(files)
	    		{
	    			self.appendMessages('INFO', "Starting upload: " + JSON.stringify(files));
	    		},
	    		onSuccess:function(files,data,xhr,arguments)
	    		{
	    			var pb = arguments.progressbar[0];
	    			if ( data.status != 'SUCCESS' ) {
	    				$(pb).css("background-color", "#c71114");
	    				self.appendMessages('ERROR', data.result.message + ": " + JSON.stringify(files));
	            	}
	            	else {
	            		$(pb).css("background-color", "#339900");
	            		self.appendMessages('SUCCESS', data.result + ": " + JSON.stringify(files));
	            		self.sendUploadCompleteEmail(JSON.stringify(files));
	            	}
	    		},
	    		onError: function(files,status,errMsg)
	    		{
	    			self.appendMessages('ERROR', errMsg + ": " + JSON.stringify(files));
	    		}

	    		});
    	};
    	
    	UpromAppClass.prototype.startSingleUploads = function() {
    		var self = this;
    		self.progFileUploadObj.startUpload(); 
    		self.taskFileUploadObj.startUpload(); 
    		self.labelFileUploadObj.startUpload(); 
    	};
    	
    	UpromAppClass.prototype.initializeButtons = function() {
    		var self = this;
    		$("#linkSheetUploadButton").button();
	    	$("#linkSheetUploadCancel").button();
    		$("#zippedUploadButton").button();
	    	$("#zippedUploadCancel").button();
			$("#advancedUploadButton").button();
	    	$("#advancedUploadCancel").button();
	    	$("#singlesUploadButton").button();
	    	$("#singlesUploadCancel").button();
	    	$("#entireDirectoryBtn").button();
	    	$("#showBtn").button();
	    	$("#syncBtn").button();
	    	$("#deleteBtn").button();
	    	$("#newDirBtn").button();
	    	$("#linkUnlinkSiteBtn").button();
	    	$("#clearBtn").button();
	    	$("#overwriteBtn").button();
	    	$("#allSitesBtn").button();
	    	$("#sequentialBtn").button();

	    	//
	    	
	    	$("#linkSheetUploadButton").click(function() { self.linkSheetUploadObj.startUpload(); });
	    	$("#linkSheetUploadCancel").click(function() { self.linkSheetUploadObj.cancelAll(); });
	    	$("#zippedUploadButton").click(function() { self.zippedUploadObj.startUpload(); });
	    	$("#zippedUploadCancel").click(function() { self.zippedUploadObj.cancelAll(); });
	    	$("#advancedUploadButton").click(function() { self.advancedUploadObj.startUpload(); });
	    	$("#advancedUploadCancel").click(function() { self.advancedUploadObj.cancelAll(); });
	    	$("#singlesUploadButton").click(function() { self.startSingleUploads();});
	    	$("#entireDirectoryBtn").click(function() { self.entireDirectoryChange(); });
	    	$("#showBtn").click(function() { self.showBtnClick(); });
	    	$("#syncBtn").click(function() { self.syncBtnClick(); });
	    	$("#deleteBtn").click(function() { self.deleteBtnClick(); });
	    	$("#newDirBtn").click(function() { self.newDirBtnClick(); });
	    	$("#linkUnlinkSiteBtn").click(function() { self.linkUnlinkSiteBtnClick(); });
	    	$("#clearBtn").click(function() { self.clearBtnClick(); });
	    	$("#overwriteBtn").click(function() { self.overwriteChange(); });
	    	$("#allSitesBtn").click(function() { self.allSitesChange(); });
	    	$("#sequentialBtn").click(function() { self.sequentialChange(); });
	    	
	    	$("#singlesUploadCancel").click(function() { 
	    		self.progFileUploadObj.cancelAll(); 
	    		self.taskFileUploadObj.cancelAll(); 
	    		self.labelFileUploadObj.cancelAll(); 
	    	});
	    	
	    	$("#deleteBtn").prop("disabled", true);
	    	$("#showBtn").prop("disabled", false);
	    	$("#syncBtn").prop("disabled", true);
	    	$("#newDirBtn").prop("disabled", false);
	    	$("#linkUnlinkSiteBtn").prop("disabled", false);
	    	$("#clearBtn").prop("disabled", false);
	    	$('entireDirectoryFlag').val('N');
	    	$('sequentialFlag').val('N');
	    	$('overwriteFlag').val('N');
	    	
	    	$("#messageDialog").dialog({
	    		title: "Messages:",
	        	autoOpen: true,
	        	resizable:true,
	        	width: 400,
	        	height: 200,
	        	closeOnEscape: false,
	        	position: { my: "left top", at: "left+400 top+185" , collision: "none"}, 
	        	open: function(event, ui) {      	    
	        	    $(this).parent().children().children('.ui-dialog-titlebar-close').hide(); //hide close button.
	        	}
	        });
	    	
	    	$("#alertDialog").dialog({
	               autoOpen: false, 
	               modal: true,
	               buttons: {
	                  OK: function() {$(this).dialog("close");}
	               }
	        });
	    	
	    	$("#serialNumber").on("focusout", 
	    			function() {
	    				//var element = this;
	    				//var text = $(element).val();
    				    //self.validateMouseSerialNumber(text);
	    				self.updateSerialPath();
	    			}
	    	);
	    	
	    	self.selectOnlyFirstSite();
		};
		
		UpromAppClass.prototype.displaySimpleDialog = function(title, msg) {
			$("#alertDialog").dialog("option", "title", title);
			$("#alertDialogBody").html(msg);
			$("#alertDialog").dialog("open");
		};
		
		UpromAppClass.prototype.appendMessages = function(pre, msg) {
			var self = this;
    		var now = new Date();
    		
    		if ( pre === 'ERROR' || pre === 'FAIL' ) {
    			$("#messageDialogBody").html(
    					$("#messageDialogBody").html() + '<br><font style="color:red">' + now.toString()  + ' ' + pre + ': ' + msg + "</font>"
    					);
    		}
    		else if ( pre === 'SUCCESS' ) {
    			$("#messageDialogBody").html(
    					$("#messageDialogBody").html() + '<br><font style="color:lightgreen">' + now.toString()  + ' ' + pre + ': ' + msg + "</font>"
    					);
    		}
    		else {
    			$("#messageDialogBody").html( 
    					$("#messageDialogBody").html() + '<br>' + now.toString()  + ' ' + pre + ': ' + msg
    					);
    		}
    		$("#messageDialog").animate({
                scrollTop: $("#messageDialog").scrollTop() + $("#messageDialog").height()
            });
    	};	
    	
    	UpromAppClass.prototype.clearMessages = function() {
    		$("#messageDialogBody").html('');
    	};
    	
    	UpromAppClass.prototype.entireDirectoryChange = function() {
    		var self = this;
    		//$("#entireDirectoryBtn").prop('checked')
        	if ( $('#entireDirectoryFlag').val() == 'N' ) { 
        		$('label[for=entireDirectoryBtn]').html("<span class='ui-button-text'>Multiple Files</span>");
        		$('#entireDirectoryFlag').val('Y');
        		$('#uploadPreventDiv').hide();
        		$('#uploadZippedDiv').hide();
        		$('#uploadLinkSheetDiv').hide();
        		$('#uploadDirectoryDiv').show();
        		$('#uploadFilesDiv').hide();
        		self.clearMessages();      		
        	}
        	else if ( $('#entireDirectoryFlag').val() == 'Y' ) { 
        		$('label[for=entireDirectoryBtn]').html("<span class='ui-button-text'>Zipped Directory</span>");
        		$('#entireDirectoryFlag').val('Z');
        		
        		//Check if sequential and zip file upload here
        		if ( $("#sequentialBtn").prop('checked')  ) {
        			self.sequentialAndZipCondition();
        		}
        		else {
        			self.nonsequentialAndZipCondition();
        		}
        		
        		$('#uploadLinkSheetDiv').hide();
        		$('#uploadDirectoryDiv').hide();
        		$('#uploadFilesDiv').hide();
        		$("#entireDirectoryBtn").prop("checked", true);
        		self.clearMessages();      		
        	}
        	else if ( $('#entireDirectoryFlag').val() == 'Z' ) { 
        		$('label[for=entireDirectoryBtn]').html("<span class='ui-button-text'>Link Toggle Sheet</span>");
        		$('#entireDirectoryFlag').val('X');
        		$('#uploadPreventDiv').hide();
        		$('#uploadZippedDiv').hide();
        		$('#uploadLinkSheetDiv').show();
        		$('#uploadDirectoryDiv').hide();
        		$('#uploadFilesDiv').hide();
        		$("#entireDirectoryBtn").prop("checked", true);
        		self.clearMessages();      		
        	}
        	else {
        		$('label[for=entireDirectoryBtn]').html("<span class='ui-button-text'>Files Only</span>");
        		$('#entireDirectoryFlag').val('N');
        		$('#uploadPreventDiv').hide();
        		$('#uploadZippedDiv').hide();
        		$('#uploadLinkSheetDiv').hide();
        		$('#uploadDirectoryDiv').hide();
        		$('#uploadFilesDiv').show();
        	}
        	$("#linkSheetUploadCancel").click();
        	$("#zippedUploadCancel").click();
        	$("#advancedUploadCancel").click();
    		$("#singlesUploadCancel").click();
        };
        
        UpromAppClass.prototype.sendUploadCompleteEmail = function(fileList) {
        	var self = this;
    		var subject = '';
        	var message = '';
        	if ( $('#sequentialFlag').val() == 'N' ) {
        		subject = 'Unified Prom Upload Report for SN: ' + $('#serialNumber').val() + 
        			' generated by ' + $('#userId').val();
        		message = $('#userId').val() + ' has uploaded files to Serial Number ' + $('#serialNumber').val() + 
        			' to prommaster and linked it to the following sites (servers): ' + $('#siteDescParam').val() +
        			', File(s): ' + fileList;
        	}
        	else {
        		subject = 'Unified Prom Sequential Upload Report for SN: ' + $('#serialNumber').val() + 
    				' generated by ' + $('#userId').val();
        		message = $('#userId').val() + ' has uploaded files to Serial Number ' + $('#serialNumber').val() + 
    				' has uploaded files to sequential Serial Number: ' + $('#siteDescParam').val()+
        			', File(s): ' + fileList;
        	}
        	self.emailAlert(subject, message);
    	};
    	
    	UpromAppClass.prototype.emailAlert = function(subject, message) {
    		var self = this;
    		var object = {
    				userId: $('#userId').val(),
    				subject: subject,
    				message: message
    		};
    		var callbackFunction = function(response) {
            	if ( response.status != 'SUCCESS' ) {
            		self.displaySimpleDialog('Error', response.result.message);
            	}
            	else {
            		self.appendMessages('SUCCESS', response.result);
            	}
    		};
			$("#loading-div-background").show();
			$.getJSON( cp + "/uprom/emailAlertCommand.json", object )
			  .done(function( json ) {
					$("#loading-div-background").hide();  
					callbackFunction(json);
			  })
			  .fail(function( jqxhr, textStatus, error ) {
					$("#loading-div-background").hide();
				    var err = textStatus + ", " + error;
				    self.appendMessages('FAIL', err);
			});
            return true;
        };
        
        UpromAppClass.prototype.showBtnClick = function() {
        	var self = this;
    		var object = {
    				userId: $('#userId').val(),
    				siteParam: $('#siteParam').val(),
    				siteDescParam: $('#siteDescParam').val(),
    				serialNumber: $('#serialNumber').val(),
    				translatedPath: $('#translatedPath').val(),
    				sequentialFlag: $('#sequentialFlag').val() 
    		};
            var callbackFunction = function(response) {
            	if ( response.status != 'SUCCESS' ) {
            		self.appendMessages('FAIL', response.result.message);
            	}
            	else {
        			self.appendMessages('SUCCESS', response.result.message);
            	}
    		};
			$("#loading-div-background").show();
			$.getJSON( cp + "/uprom/showCommand.json", object )
			  .done(function( json ) {
					$("#loading-div-background").hide();  
					callbackFunction(json);
			  })
			  .fail(function( jqxhr, textStatus, error ) {
					$("#loading-div-background").hide();
				    var err = textStatus + ", " + error;
				    self.appendMessages('FAIL', err);
			});

            return true;
        };
        
        UpromAppClass.prototype.syncBtnClick = function() {
        	var self = this;
    		var object = {
    				userId: $('#userId').val(),
    				siteParam: $('#siteParam').val(),
    				siteDescParam: $('#siteDescParam').val(),
    				serialNumber: $('#serialNumber').val(),
    				translatedPath: $('#translatedPath').val(),
    				sequentialFlag: $('#sequentialFlag').val() 
    		};
    		var callbackFunction = function(response) {
            	if ( response.status != 'SUCCESS' ) {
            		self.appendMessages('ERROR', response.result.message);
            	}
            	else {
        			self.appendMessages('SUCCESS', response.result.message);
            	}
    		};
    	
			$("#loading-div-background").show();
			$.getJSON( cp + "/uprom/syncCommand.json", object )
			  .done(function( json ) {
					$("#loading-div-background").hide();  
					callbackFunction(json);
			  })
			  .fail(function( jqxhr, textStatus, error ) {
					$("#loading-div-background").hide();
				    var err = textStatus + ", " + error;
				    self.appendMessages('FAIL', err);
			});
            return true;
        };
        
        UpromAppClass.prototype.deleteBtnClick = function() {
        	var self = this;
        	
        	var progFile = '';
        	self.progFileUploadObj.existingFileNames.forEach(function(fname) {
        			progFile = fname;
        	});
        	var taskFile = '';
        	self.taskFileUploadObj.existingFileNames.forEach(function(fname) {
    			taskFile = fname;
        	});
        	var labelFile = '';
        	self.labelFileUploadObj.existingFileNames.forEach(function(fname) {
    			labelFile = fname;
        	});
  
        	var object = {
        			userId: $('#userId').val(),
        			progFile: progFile,
        			taskFile: taskFile,
        			labelFile: labelFile,
    				siteParam: $('#siteParam').val(),
    				siteDescParam: $('#siteDescParam').val(),
    				serialNumber: $('#serialNumber').val(),
    				translatedPath: $('#translatedPath').val(),
    				sequentialFlag: $('#sequentialFlag').val(),
    				entireDirectoryFlag: $('#entireDirectoryFlag').val()
    		};
    		var callbackFunction = function(response) {
            	if ( response.status != 'SUCCESS' ) {
            		self.appendMessages('FAIL', response.result.message);
            	}
            	else {
        			self.appendMessages('SUCCESS', 'Deletion complete.<br>' + response.result.message);
            	}
    		};
			$("#loading-div-background").show();
			$.getJSON( cp + "/uprom/deleteCommand.json", object )
			  .done(function( json ) {
					$("#loading-div-background").hide();  
					callbackFunction(json);
			  })
			  .fail(function( jqxhr, textStatus, error ) {
					$("#loading-div-background").hide();
				    var err = textStatus + ", " + error;
				    self.appendMessages('FAIL', err);
			});
            return true;
        };
        
        UpromAppClass.prototype.newDirBtnClick = function() {
        	var self = this;
    		var object = {
    				userId: $('#userId').val(),
    				translatedPath: $('#translatedPath').val(),
    				sequentialFlag: $('#sequentialFlag').val() 
    		};
    		var callbackFunction = function(response) {
            	if ( response.status != 'SUCCESS' ) {
            		self.displaySimpleDialog('Error', response.result.message);
            	}
            	else {
        			self.appendMessages('SUCCESS', response.result.message);
            	}
    		};
			$("#loading-div-background").show();
			$.getJSON( cp + "/uprom/newDirectoryCommand.json", object )
			  .done(function( json ) {
					$("#loading-div-background").hide();  
					callbackFunction(json);
			  })
			  .fail(function( jqxhr, textStatus, error ) {
					$("#loading-div-background").hide();
				    var err = textStatus + ", " + error;
				    self.appendMessages('FAIL', err);
			});
            return true;
        };
        
        UpromAppClass.prototype.linkUnlinkSiteBtnClick = function() {
        	var self = this;
    		var object = {
    				userId: $('#userId').val(),
    				siteParam: $('#siteParam').val(),
    				siteDescParam: $('#siteDescParam').val(),
    				serialNumber: $('#serialNumber').val(),
    				translatedPath: $('#translatedPath').val(),
    				sequentialFlag: $('#sequentialFlag').val() 
    		};
            
    		var callbackFunction = function(response) {
            	if ( response.status != 'SUCCESS' ) {
            		self.appendMessages('ERROR', response.result.message);
            	}
            	else {
        			self.appendMessages('SUCCESS', response.result.message);
            	}
    		};
    		
    		$("#loading-div-background").show();
			$.getJSON( cp + "/uprom/toggleSiteLinkCommand.json", object )
			  .done(function( json ) {
					$("#loading-div-background").hide();  
					callbackFunction(json);
			  })
			  .fail(function( jqxhr, textStatus, error ) {
					$("#loading-div-background").hide();
				    var err = textStatus + ", " + error;
				    self.appendMessages('FAIL', err);
			});
            return true;
        };
 
        UpromAppClass.prototype.selectAllSites = function() {
        	var self = this;
        	$('#siteSelect option').each(function(){
    			$(this).prop("selected", true);
      		});
        	self.updateSiteParam();
      		//$('#siteSelect').selectmenu('refresh');
        };
        
        UpromAppClass.prototype.deselectAllSites = function() {
        	var self = this;
        	$('#siteSelect option').each(function(){
    			$(this).prop("selected", false);
      		});
        	self.updateSiteParam();
      		//$('#siteSelect').selectmenu('refresh');
        };
        
        UpromAppClass.prototype.selectOnlyFirstSite = function() {
        	var self = this;
        	var sIdx = 0;
    		$('#siteSelect option').each(function(){
    			if ( sIdx == 0 ) {
    				$(this).prop("selected", true);
    			}
    			else {
    				$(this).prop("selected", false);
    			}
    			sIdx++;
      		});
    		self.updateSiteParam();
      		//$('#siteSelect').selectmenu('refresh');
        };
        
        UpromAppClass.prototype.resetFilesButton = function() {
        	var self = this;
        	$("#entireDirectoryBtn").prop("checked", false);
    		$('label[for=entireDirectoryBtn]').html("<span class='ui-button-text'>Files Only</span>");
    		$('#entireDirectoryFlag').val('N');
    		$('#uploadZippedDiv').hide();
    		$('#uploadLinkSheetDiv').hide();
    		$('#uploadDirectoryDiv').hide();
    		$('#uploadFilesDiv').show();
    		$('#entireDirectoryBtn').button("refresh");
        };
        
        UpromAppClass.prototype.resetSitesButton = function() {
        	var self = this;
        	if ( $("#allSitesBtn").prop('checked') ) {
	        	$("#allSitesBtn").prop("checked", false);
	    		$('label[for=allSitesBtn]').html("<span class='ui-button-text'>Selected Sites</span>");
	    		$('#allSitesBtn').button("refresh");
        	}
        };
        
        UpromAppClass.prototype.resetSequentialButton = function() {
        	var self = this;
        	$("#sequentialBtn").prop("checked", false);
    		$('label[for=sequentialBtn]').html("<span class='ui-button-text'>Non-sequential</span>");
    		$('#sequentialFlag').val('N');
    		$('#sequentialBtn').button("refresh");
        };
        
        UpromAppClass.prototype.resetOverwriteButton = function() {
        	var self = this;
        	$("#overwriteBtn").prop("checked", false); 
    		$('label[for=overwriteBtn]').html("<span class='ui-button-text'>No Overwrite</span>");
    		$('#overwriteFlag').val('N');
    		$('#overwriteBtn').button("refresh");
        };
        
        UpromAppClass.prototype.clearBtnClick = function() {
        	var self = this;
    		$('#serialNumber').val('');
    		$('#translatedPath').val('');
    		self.resetFilesButton();
    		self.selectOnlyFirstSite();
    		self.resetSitesButton();
    		self.resetSequentialButton();
    		self.resetOverwriteButton();
    		self.clearMessages();
    		$("#syncBtn").prop("disabled", true);
    		$("#deleteBtn").prop("disabled", true);
    		
    		//self.resetAllUploadStatus();
        };
    	
        UpromAppClass.prototype.overwriteChange = function() {
        	var self = this;
        	if ( $("#overwriteBtn").prop('checked') ) {
        		$('label[for=overwriteBtn]').html("<span class='ui-button-text'>Overwrite</span>");
        		$('#overwriteFlag').val('Y');
        	}
        	else {
        		$('label[for=overwriteBtn]').html("<span class='ui-button-text'>No Overwrite</span>");
        		$('#overwriteFlag').val('N');
        	}
        };
        
        UpromAppClass.prototype.allSitesChange = function() {
        	var self = this;
        	if ( $("#allSitesBtn").prop('checked') ) {
        		$('label[for=allSitesBtn]').html("<span class='ui-button-text'>All Sites</span>");
        		self.selectAllSites();
        	}
        	else {
        		$('label[for=allSitesBtn]').html("<span class='ui-button-text'>Selected Sites</span>");
        		self.selectOnlyFirstSite();
        	}
        	$("#syncBtn").prop("disabled", false);
    		$("#deleteBtn").prop("disabled", false);   
        };

        UpromAppClass.prototype.sequentialChange = function() {
        	var self = this;
  
        	if ( $("#sequentialBtn").prop('checked')  ) {
        		$('label[for=sequentialBtn]').html("<span class='ui-button-text'>Sequential</span>");
        		$('#sequentialFlag').val('Y');
        		self.deselectAllSites();
        		
        		//Check if sequential and zip file upload here
            	if ( $('#entireDirectoryFlag').val() == 'Z' ) {
            		self.sequentialAndZipCondition();
            	}     
        	}
        	else {
        		$('label[for=sequentialBtn]').html("<span class='ui-button-text'>Non-sequential</span>");
        		$('#sequentialFlag').val('N');
        		self.selectOnlyFirstSite();
        		
        		if ( $('#entireDirectoryFlag').val() == 'Z' ) {
            		self.nonsequentialAndZipCondition();
            	}   
        	}
        };
        
        UpromAppClass.prototype.sequentialAndZipCondition = function() {
        	var self = this;
        	$('#uploadZippedDiv').hide();
			$('#uploadPreventMsgDiv').html('<p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>Sequential ZIP file upload not permitted.</p>');
			$('#uploadPreventDiv').show();
        };
        
        UpromAppClass.prototype.nonsequentialAndZipCondition = function() {
        	var self = this;
        	$('#uploadZippedDiv').show();
			$('#uploadPreventDiv').hide();
        };
        
        UpromAppClass.prototype.updateSiteParam = function() {
        	var self = this;
    		var newSiteParamVal = '';
    		var newSiteDescParamVal = '';
    		var selectedCount = 0;
    		$('#siteSelect option').each(function(){
    			if ( $(this).prop("selected") ) {
    				if ( selectedCount > 0 ) {
				    	newSiteParamVal = newSiteParamVal + ',';
				    	newSiteDescParamVal = newSiteDescParamVal + ', ';
				    }
				    newSiteParamVal = newSiteParamVal + $(this).val();
				    newSiteDescParamVal = newSiteDescParamVal + $(this).text();
				    selectedCount++;
    			}
      		});
    		if ( selectedCount > 0 ) {
    			$("#syncBtn").prop("disabled", false);
        		$("#deleteBtn").prop("disabled", false);
    		}
    		else {
    			$("#syncBtn").prop("disabled", true);
        		$("#deleteBtn").prop("disabled", true);
    		}
    		$('#siteParam').val(newSiteParamVal);
    		$('#siteDescParam').val(newSiteDescParamVal);
    	};
    	
    	UpromAppClass.prototype.updateSerialPath = function() {
    		var newSerialNumber = $('#serialNumber').val();
    		
    		var isnum = /^\d+$/.test(newSerialNumber);
	    	if( isnum == false ) {
    			$('#serialNumber').val('');
    			$('#translatedPath').val('');
    			return false;
    		}
    		
    		var newLength = newSerialNumber.length;
        	var newDirectory = '';
        	if ( newLength == 9 ) {
        		newDirectory = '\\' 
        			+ newSerialNumber.charAt(0)
        			+ newSerialNumber.charAt(1)
        			+ newSerialNumber.charAt(2)
        			+ '\\'
        			+ newSerialNumber.charAt(3)
        			+ newSerialNumber.charAt(4)
        			+ newSerialNumber.charAt(5)
        			+ '\\'
        			+ newSerialNumber;
        	}
        	else if ( newLength == 8 ) {
        		newDirectory = '\\0' 
        			+ newSerialNumber.charAt(0)
        			+ newSerialNumber.charAt(1)
        			+ '\\'
        			+ newSerialNumber.charAt(2)
        			+ newSerialNumber.charAt(3)
        			+ newSerialNumber.charAt(4)
        			+ '\\'
        			+ newSerialNumber;
        	}
        	else if ( newLength == 7 ) {
        		newDirectory = '\\00' 
        			+ newSerialNumber.charAt(0)
        			+ '\\'
        			+ newSerialNumber.charAt(1)
        			+ newSerialNumber.charAt(2)
        			+ newSerialNumber.charAt(3)
        			+ '\\'
        			+ newSerialNumber;
        	}
        	else if ( newLength == 6 ) {
        		newDirectory = '\\000\\' 
        			+ newSerialNumber.charAt(0)
        			+ newSerialNumber.charAt(1)
        			+ newSerialNumber.charAt(2)
        			+ '\\'
        			+ newSerialNumber;
        	}
    		else if ( newLength == 5 ) {
    			newDirectory = '\\000\\0' 
        			+ newSerialNumber.charAt(0)
        			+ newSerialNumber.charAt(1)
        			+ '\\'
        			+ newSerialNumber;		
    		}
    		else if ( newLength == 4 ) {
    			newDirectory = '\\000\\00' 
        			+ newSerialNumber.charAt(0)
        			+ '\\'
        			+ newSerialNumber;	
    		}
    		else if ( newLength == 3 ) {
    			newDirectory = '\\000\\000\\' 
        			+ newSerialNumber;	
    		}
    		else if ( newLength == 2 ) {
    			newDirectory = '\\000\\000\\' 
        			+ newSerialNumber;	
    		}
    		else if ( newLength == 1 ) {
    			newDirectory = '\\000\\000\\' 
        			+ newSerialNumber;	
    		}
        	$('#translatedPath').val(newDirectory);
    	};
    	
    	UpromAppClass.prototype.validateSerialNumber = function(e) {
    		var self = this;
        	//alert('got here');
        	var theEvent = e || window.event;
        	var key = theEvent.keyCode || theEvent.which;
        	//Only allow 9 chars max. 
        	if ( $('#serialNumber').val().length >= 10 ) {
        		$('#serialNumber').val($('#serialNumber').val().substring(0, 9));
        		theEvent.returnValue = false;
        		if( theEvent.preventDefault ) {
        			theEvent.preventDefault();
        			return;
        		}
        	}
        	//alert('key1=' + key);
        	//Allow backspace, delete, and arrows, but not delete (46) because its same as "."?
        	if ( key == 37 || key == 38 || key == 39 || key == 40 ) {
        		return;
        	}
        	var isBackspaceOrDelete = false;
        	if ( key == 8 || key == 46 ) {
        		isBackspaceOrDelete = true;
        		key = '';
        	}
        	else {
        		key = String.fromCharCode(key);
        	}
        	
        	//alert('key2=' + key);
        	if ( isBackspaceOrDelete == false ) {
    	    	//Allow only numeric
    	    	var regex = /[0-9]/;
    	    	if( !regex.test(key) ) {
    	    		theEvent.returnValue = false;
    	    		if( theEvent.preventDefault ) {
    	    			theEvent.preventDefault();
    	    			return false;
    	    		}
    	    	}
        	}
        	self.updateSerialPath();
        };
        
    	upromAppObj = new UpromAppClass();
    	upromAppObj.initializeButtons();
    	upromAppObj.initializeUploaderWidgets();
    });
    } ) ( jQuery );