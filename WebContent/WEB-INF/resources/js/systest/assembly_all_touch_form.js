var astAssemblyObj = null;

( function($) {
    $(document).ready(function() {

  		function Part(partId, serialNum, partNum, partBomDs, compTypeNm, compTypeDs, node, userAttrArray, comment) {
			this.partId = partId;
			this.serialNum = serialNum;
			this.partNum = partNum;
			this.partBomDs = partBomDs;
			this.compTypeNm = compTypeNm;
			this.compTypeDs = compTypeDs;
			this.node = node;
			this.userAttrArray = userAttrArray;
			this.comment = comment;
		}
  		
  		function PartAttr(compTypeAttrId, partId, attrNm, attrDs, dataSourceNm, dataTypeNm, valueTx) {
			this.compTypeAttrId = compTypeAttrId;
			this.partId = partId;
			this.attrNm = attrNm;
			this.attrDs = attrDs;
			this.dataSourceNm = dataSourceNm;
			this.dataTypeNm = dataTypeNm;
			this.valueTx = valueTx;
		}
  		
  		function PartAssembly(partId, partAsmId, partNum, serialNum, compTypeNm, compTypeDs, userAttrArray) {
    		this.partId = partId;
  			this.partAsmId = partAsmId;
  			this.partNum = partNum;
  			this.serialNum = serialNum;
			this.compTypeNm = compTypeNm;
			this.compTypeDs = compTypeDs;
			this.userAttrArray = userAttrArray;
		}
  		
  		function PartAssemblyAttr(compTypeAttrId, partAttrId, partAsmId, partAsmAttrId, attrNm, attrDs, dataSourceNm, dataTypeNm, valueTx) {
			this.compTypeAttrId = compTypeAttrId;
			this.partAttrId = partAttrId;
			this.partAsmId = partAsmId;
			this.partAsmAttrId = partAsmAttrId;
			this.attrNm = attrNm;
			this.attrDs = attrDs;
			this.dataSourceNm = dataSourceNm;
			this.dataTypeNm = dataTypeNm;
			this.valueTx = valueTx;
		}

  		function ExcludedPart(partNum, partBomDs, comment, partCount) {
			this.partNum = partNum;
			this.partBomDs = partBomDs;
			this.comment = comment;
			this.partCount = partCount;
		}
  		
    	var AstAssemblyClass = function() {
    		this.addedNodeLocations = [];
    		this.loadedNodeLocations = [];
    		this.partsArray = [];
    		this.chassisPartsArray = [];
    		this.miscArray = [];
    		this.chassisAttrArray = [];
    		this.alertDialog = null;
    		this.loadBtn = null;
    		this.addNodeBtn = null;
    		this.deleteNodeBtn = null;
    		this.submitBtn = null;
    		this.bomData = null;
    		this.partDataArray = [];
    		this.chassisSerialNum = null;
    		this.icn = null;
    		this.chassisPartNum = null;
    		this.chassisPartId = null;
    		this.Obj = null;
    		
    		//2nd-touch
    		this.firstTouchAssemblyId = null;
    		this.chassisPartAsm = null;
    		this.nodeAsmsArray = [];
    		this.nodePartsArray = [];
    		
    	};

    	var UserAssemblyNodeData = function() {
    		this.nodeAttrs = null;
    		this.nodeParts = null;
    	};
    	
    	var UserAssemblyData = function() {
    		this.chassisAttrs = null;
    		this.chassisParts = null;
    		this.nodeData = [];
    		this.miscParts = null;
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
    		
    		self.loadBtn = $('#loadBtn').button();
    		self.loadBtn.click(self.loadClick);
    		
    		self.addNodeBtn = $('#addNodeBtn').button();
    		self.addNodeBtn.click(self.uploadNodeClick);
    		
    		self.deleteNodeBtn = $('#deleteNodeBtn').button();
    		self.deleteNodeBtn.click(self.deleteNodeClick);
    		self.deleteNodeBtn.prop('disabled', true);
    		self.deleteNodeBtn.hide();
    		
    		self.submitBtn = $('#submitBtn').button();
    		self.submitBtn.click(self.submitClick);
    		self.submitBtn.prop('disabled', true);
    		
    		self.nodeFileUploadObj = $('#nodeFileUpload').uploadFile({
	    		method: 'POST',
	    		url: cp + '/systest/nodeUpload.json',
	    		acceptFiles: '*.json',
	    		returnType: 'json',
	    		multiple:false,
	    		autoSubmit:false,
	    		fileName: 'nodeFile',
	    		dynamicFormData: function()
	    		{
	    			var data = { 
	    					chassisSerial: self.chassisSerialNum,
	    					icn: self.icn
	    	    		};
	    			return data;
	    		},
	    		maxFileSize:1024000, //1MB max
	    		maxFileCount:1,
	    		showStatusAfterSuccess:true,
	    		showProgress:true,
	    		showCancel:true,
	    		showDelete:true,
	    		showAbort:false,
	    		showDone:false,
	    		dragDropStr: '<span><b>You may also drag & drop file here.</b></span>',
	    		abortStr:'Abort',
	    		cancelStr:'Remove',
	    		doneStr:'Done',
	    		multiDragErrorStr: 'Drag and drop error.',
	    		extErrorStr:'File type extension is prohibited.',
	    		sizeErrorStr:'File size is too large.',
	    		uploadErrorStr:'Upload error.',
	    		onSuccess:function(files,data,xhr,arguments)
	    		{
	    			var pb = arguments.progressbar[0];
	    			if ( data.status != 'SUCCESS' ) {
	    				$(pb).css('background-color', '#c71114');
	    				self.displaySimpleDialog('ERROR', data.result.message);
	            	}
	            	else {
	            		$(pb).css('background-color', '#339900');
	            		var nodeResp = JSON.parse(data.result);
	            		var nodeData = nodeResp.nodeData[0];
            			var nodeLocation = nodeData.nodeAttrs[0].valueTx;
            			var nodeNumber = self.getNodeNumber(nodeLocation);
	            		
	            		if ( self.chassisSerialNum != nodeResp.chassisAttrs[0].nutanixSerialNum ) {
	            			self.displaySimpleDialog('ERROR', 'Node chassis serial does not match: ' + nodeResp.chassisAttrs[0].nutanixSerialNum);
	            			return false;
	            		}
	            		
	            		var lnIdx = self.loadedNodeLocations.indexOf(nodeLocation);
	        			if ( lnIdx > -1 ) {
	        				self.displaySimpleDialog('ERROR', 'Node .json file was previously submitted for location ' + nodeLocation);
	        				return false;
	        			}

	            		var touchLevel = $('#submitTouchLevel').val();
	            		if ( touchLevel == '1' ) {
		            		if ( self.addedNodeLocations.length == 4 ) {
	                			self.displaySimpleDialog('ERROR', 'Maximum number of NODES exceeded');
	                			return false;
	                		}
		            		var cpgCount =  $('#chassis_part_grid').appendGrid('getRowCount');
	            			for ( var cpgIdx = 0; cpgIdx < cpgCount; cpgIdx++ ) { //Loop over chassis parts grid
	            				var cpgPartSerialNum = $('#chassis_part_grid').appendGrid('getCtrlValue', 'serialNum', cpgIdx);
	            				for ( var cpIdx = 0; cpIdx < nodeResp.chassisParts.length; cpIdx++ ) { //Loop over node file chassis parts
	    	            			var chassisPart = nodeResp.chassisParts[cpIdx];
	    	            			if ( chassisPart.serialNum == cpgPartSerialNum ) {
	    	            				var sgIdx = cpgIdx + 1;
	    	            				var sgElem = '#chassis_part_grid_' + sgIdx.toString();
	    	            				var cpagCount =  $(sgElem).appendGrid('getRowCount');
	    	            				for ( var cpagIdx = 0; cpagIdx < cpagCount; cpagIdx++ ) { //Loop over chassis part subgrid attributes
	    	            					var cpagAttrNm = $(sgElem).appendGrid('getCtrlValue', 'attrNm', cpagIdx); 	            					
	    	            					if ( cpagAttrNm == chassisPart[0].attrNm ) {
		    	            					var cpagPartElem = $(sgElem).appendGrid('getCellCtrl', 'valueTx', cpagIdx);
		        	            				$(cpagPartElem).val(chassisPart[0].valueTx);
	    	            					}
	    	            				}
	                				}
	    	            		}
	            			}
	
	            			//Add node widget to UI
	                		$.ajax({ 
	                			method: 'POST',
	            				url: cp + '/systest/getComponentTypeAttributesByTypeName.json', 
	            				data: { compTypeNm: 'node' },
	            				dataType: 'json'
	                		})
	                		.done(
	                				function(nodeAttrData) {
	                					self.addFirstTouchNodeCallback(nodeNumber, nodeLocation, nodeAttrData);
	                					self.addUploadedNodeData(nodeNumber, nodeLocation, nodeData);	
		            				}
	                		)
	                		.fail(function() {
	                			self.displaySimpleDialog('ERROR', 'Unknown error fetching node attr data. Please contact support.');
	                		});
	            		}
	            		else { //2nd Touch
	            			
	            			var nIdx = self.addedNodeLocations.indexOf(nodeLocation);
	            			if ( nIdx == -1 ) { 
	            				self.displaySimpleDialog('ERROR', 'Node ' + nodeLocation + ' does not exist.');
		            			self.nodeFileUploadObj.cancelAll();
		            			return false;
	            			}
	            			else {
	            				self.addUploadedNodeData(nodeNumber, nodeLocation, nodeData);
	            			}

	            		}
	            		self.loadedNodeLocations.push(nodeLocation);
	            	}
	    		},
	    		onError: function(files,status,errMsg) 
	    		{
	    			self.displaySimpleDialog('ERROR', errMsg);
	    		}
	    	});
 
    		///////////////////////////////////
	    	$('#chassis_grid').appendGrid({
	            columns: [
	                { name: 'compTypeAttrId', display: 'compTypeAttrId', type: 'hidden', ctrlAttr: { readonly: true } }, 
	                { name: 'partId', display: 'partId', type: 'hidden', ctrlAttr: { readonly: true } }, 
	                { name: 'attrNm', display: 'Name', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
	                { name: 'attrDs', display: 'Chassis Attribute', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
	                { name: 'dataSourceNm', display: 'Data Source', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
	                { name: 'dataTypeNm', display: 'Data Type', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
	                { name: 'valueTx', display: 'Value', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} }
	            ],
	            hideRowNumColumn: true,
	            hideButtons: {
	    			append: true,
	    			removeLast: true,
	    			insert: true,
	                remove: true,
	                moveUp: true,
	                moveDown: true
	            }
	        });
	    	
	    	$('#chassis_part_grid').appendGrid({
	            columns: [
	                { name: 'partId', display: 'partId', type: 'hidden', ctrlAttr: { readonly: true } }, 
	                { name: 'compTypeNm', display: 'compTypeNm', type: 'hidden', ctrlAttr: { readonly: true } },
	                { name: 'compTypeDs', display: 'Part Type', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
	                { name: 'partNum', display: 'Part Number', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
	                { name: 'serialNum', display: 'Serial Number', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} }
	            ],
	            initRows: 1,
	            rowDragging: false,
	            hideRowNumColumn: false,
	            hideButtons: {
	    			append: true,
	    			removeLast: true,
	    			insert: true,
	                remove: true,
	                moveUp: true,
	                moveDown: true
	            },
	            rowDataLoaded: function (caller, record, rowIndex, uniqueIndex) {
	            	if ( record.userAttrArray != null ) {
                        $('#chassis_part_grid_' + uniqueIndex, caller).appendGrid('load', record.userAttrArray);
                    }
                },
                subPanelGetter: function (uniqueIndex) {
                	var spVal = $('#chassis_part_grid_' + uniqueIndex).appendGrid('getAllValue');
                    return spVal;
                },
	            useSubPanel: true,
	            subPanelBuilder: function (cell, uniqueIndex) {
	                var subgrid = $('<table></table>').attr('id', 'chassis_part_grid_' + uniqueIndex).appendTo(cell);
	                subgrid.appendGrid({
	                    initRows: 0,
	                    rowDragging: false,
	                    hideRowNumColumn: false,
	                    hideButtons: {
	    	    			append: true,
	    	    			removeLast: true,
	    	    			insert: true,
	    	                remove: true,
	    	                moveUp: true,
	    	                moveDown: true
	    	            },
	                    columns: [
	                        { name: 'compTypeAttrId', display: 'compTypeAttrId', type: 'hidden', ctrlAttr: { readonly: true } },       
	                        { name: 'partId', display: 'partId', type: 'hidden', ctrlAttr: { readonly: true } }, 
			                { name: 'attrNm', display: 'Name', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
			                { name: 'attrDs', display: 'Attribute', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
			                { name: 'dataSourceNm', display: 'Data Source', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
			                { name: 'dataTypeNm', display: 'Data Type', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
			                { name: 'valueTx', display: 'Value', type: 'text', ctrlAttr: { readonly: false }, displayCss: { 'min-width': '160px'} }
	                    ]
	                });
	            }
	        });
	    	
	    	
	    	$('#parts_grid').appendGrid({
	            columns: [
	                { name: 'partId', display: 'partId', type: 'hidden', ctrlAttr: { readonly: true } }, 
	                { name: 'compTypeNm', display: 'compTypeNm', type: 'hidden', ctrlAttr: { readonly: true } },
	                { name: 'compTypeDs', display: 'Part Type', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
	                { name: 'partNum', display: 'Part Number', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '195px'} },
	                { name: 'serialNum', display: 'Serial Number', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '195px'} },
	                { name: 'nodeOpt', display: 'Node', type: 'select', ctrlOptions: { 0: '', 1: 'Node A', 2: 'Node B', 3: 'Node C', 4: 'Node D'}, displayCss: { 'min-width': '70px'} }
	            ],
	            customRowButtons: [
	                   { uiButton: { icons: { primary: 'ui-icon-circle-plus' }, label: 'Add to Node' }, click: self.addPartToNodeCallback }
	            ],
	            hideRowNumColumn: true,
	            hideButtons: {
	    			append: true,
	    			removeLast: true,
	    			insert: true,
	                remove: true,
	                moveUp: true,
	                moveDown: true
	            }
	        });
	    	
	    	
	    	$('#misc_grid').appendGrid({
	            columns: [
	                { name: 'partNum', display: 'Part Number', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
	                { name: 'partBomDs', display: 'BOM Desc', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '300px'} },
	                { name: 'comment', display: 'Comment', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '180px'} },
	                { name: 'partCount', display: 'Count', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '20px'} },
	                { name: 'confirm', display: 'Confirm (parts should not be tested)', type: 'checkbox', ctrlProp: { checked: true } }
	            ],
	            hideRowNumColumn: true,
	            hideButtons: {
	    			append: true,
	    			removeLast: true,
	    			insert: true,
	                remove: true,
	                moveUp: true,
	                moveDown: true
	            }
	        });
	    	
	    	$('#chassis_grid').appendGrid('load', []);
	    	$('#chassis_part_grid').appendGrid('load', []);
	    	$('#parts_grid').appendGrid('load', []);
	    	$('#misc_grid').appendGrid('load', []);
	    	
	    	$( '#chassis' ).accordion();
	    	$( '#chassis' ).accordion( "option", "collapsible", true );
	    	$( '#parts' ).accordion();
	    	$( '#parts' ).accordion( "option", "collapsible", true );
	    	$( '#misc' ).accordion();
	    	$( '#misc' ).accordion( "option", "collapsible", true );
	    	
	   
	    	$('#testBtn1').button();
	    	$('#testBtn1').click(function (e) {
	    		
	    		var userAssemblyData = new UserAssemblyData();
	    		userAssemblyData.chassisAttrs = $('#chassis_grid').appendGrid('getAllValue');
	    		userAssemblyData.chassisParts =  $('#chassis_part_grid').appendGrid('getAllValue');
	    		
	    		self.addedNodeLocations.sort();
	    		for ( var nlIdx = 0; nlIdx < self.addedNodeLocations.length; nlIdx++ ) {
	    			var nodeLoc = self.addedNodeLocations[nlIdx];
	    			var nodeNum = self.getNodeNumber(nodeLoc);
	    			
	    			var userAssemblyNodeData = new UserAssemblyNodeData();
	    			userAssemblyNodeData.nodeAttrs = $('#node_grid_' + nodeNum).appendGrid('getAllValue');
	    			userAssemblyNodeData.nodeParts = $('#node_part_grid_' + nodeNum).appendGrid('getAllValue');
	    			userAssemblyData.nodeData.push(userAssemblyNodeData);
	    		}
	    		userAssemblyData.miscParts =  $('#misc_grid').appendGrid('getAllValue');
	    		var userAssemblyDataJSON = JSON.stringify(userAssemblyData);
	    		
	    		$('#testText1').html(userAssemblyDataJSON);
	    	});
	    	$( '#chassis' ).accordion('refresh');
	        $( '#parts' ).accordion('refresh');
	        $( '#misc' ).accordion('refresh');
		};
		
		AstAssemblyClass.prototype.addUploadedNodeData = function(nodeNumber, nodeLocation, nodeData) {
    		var self = astAssemblyObj;
    		
    		var ngElem = '#node_grid_' + nodeNumber;
			var ngCount =  $(ngElem).appendGrid('getRowCount');
			for ( var ngIdx = 0; ngIdx < ngCount; ngIdx++ ) { //Loop over node attributes
				var ngAttrNm = $(ngElem).appendGrid('getCtrlValue', 'attrNm', ngIdx); 	            					
				if ( ngAttrNm == 'location' ) {
					var ngAttrElem = $(ngElem).appendGrid('getCellCtrl', 'valueTx', ngIdx);
    				$(ngAttrElem).val(nodeLocation);
				}
			}
			
			var addedSerials = new Array();
			var addedPartCount = 0;
			for ( var npIdx = 0; npIdx < nodeData.nodeParts.length; npIdx++ ) { //Loop over node file parts
    			var nodePart = nodeData.nodeParts[npIdx];
				var partsCount =  $('#parts_grid').appendGrid('getRowCount');
				for ( var pIdx = 0; pIdx < partsCount; pIdx++ ) { //Loop over unassigned parts
					var pRowData = $('#parts_grid').appendGrid('getRowValue', pIdx); 
					if ( (pRowData.compTypeNm == 'dimm' && pRowData.serialNum.endsWith(nodePart.serialNum)) ||
							pRowData.serialNum == nodePart.serialNum ) {
						//Node upload file only sends last 8 chars of serial number
						addedSerials[addedPartCount] = pRowData.serialNum;
						addedPartCount++;                 							
						var nPart = self.partDataArray['part_' + pRowData.serialNum.toString()];
		    			var nodePart = new Part(
		    					pRowData.partId,
		    					pRowData.serialNum, 
		    					pRowData.partNum,
								'',
								pRowData.compTypeNm,
								pRowData.compTypeDs,
								nodeNumber,
								nPart.userAttrArray,
								null
								);
		    			var npArray = [];
		    			npArray[0] = nodePart;
		    			$('#node_part_grid_' + nodeNumber).appendGrid('appendRow', npArray);               						                  		
					}
				}
    		}
			
			//Loop over assigned parts to remove assigned ones
			if ( addedPartCount != 0 ) {
				for ( var apIdx = 0; apIdx < addedPartCount; apIdx++ ) { //Loop over node file parts
					var partsCount =  $('#parts_grid').appendGrid('getRowCount');
					for ( var pIdx = 0; pIdx < partsCount; pIdx++ ) { //Loop over unassigned parts
						var rowSerialNum = $('#parts_grid').appendGrid('getCtrlValue', 'serialNum', pIdx);
						if ( rowSerialNum == addedSerials[apIdx] ) {
							//Remove part from part unassigned list
    		    			$('#parts_grid').appendGrid('removeRow', pIdx);
						}
					}
				}
				$( '#node_' + nodeNumber ).accordion('refresh');
    			$( '#parts' ).accordion('refresh');
			}
			
			//Add attribute values to assigned parts
			var npgElem = '#node_part_grid_' + nodeNumber;
			var npgCount =  $(npgElem).appendGrid('getRowCount');
			for ( var npgIdx = 0; npgIdx < npgCount; npgIdx++ ) { //Loop over node parts grid
				var npgPartSerialNum = $(npgElem).appendGrid('getCtrlValue', 'serialNum', npgIdx);
				for ( var npIdx = 0; npIdx < nodeData.nodeParts.length; npIdx++ ) { //Loop over node file parts
        			var nodePart = nodeData.nodeParts[npIdx];
        			if ( npgPartSerialNum.endsWith(nodePart.serialNum) ) {
        				var npRowIdx = npgIdx + 1;
        				var npagElem = npgElem + '_' + npRowIdx.toString();
        				var npagCount =  $(npagElem).appendGrid('getRowCount');
        				for ( var npagIdx = 0; npagIdx < npagCount; npagIdx++ ) { //Loop over node part subgrid attributes
        					var npagAttrNm = $(npagElem).appendGrid('getCtrlValue', 'attrNm', npagIdx); 
        					if ( npagAttrNm == nodePart[0].attrNm ) {
            					var npagPartElem = $(npagElem).appendGrid('getCellCtrl', 'valueTx', npagIdx);
	            				$(npagPartElem).val(nodePart[0].valueTx);
        					}
        				}
    				}
				}
			}
			
			self.displaySimpleDialog('SUCCESS', 'Node ' + nodeLocation + ' added to chassis');
    		self.nodeFileUploadObj.cancelAll();
		};
		
		AstAssemblyClass.prototype.submitClick = function(e) {
    		var self = astAssemblyObj;
    		var data = $('#misc_grid').appendGrid('getAllValue');
    		for ( var i = 0; i < data.length; i++ ) {
    			if ( data[i].confirm == 0 ) {
    				self.displaySimpleDialog('ERROR', 'Please review and confirm undefined parts.');
    				return false;
    			}
    		}
    		
    		data = $('#parts_grid').appendGrid('getRowCount');
    		if ( data > 0 ) {
    			self.displaySimpleDialog('ERROR', 'All parts must be assigned to a node.');
    			return false;
    		}
    		
    		
    		//TODO: Validate entry and datatype of USER fields here
    		
    		var userAssemblyData = new UserAssemblyData();
    		userAssemblyData.chassisAttrs = $('#chassis_grid').appendGrid('getAllValue');
    		userAssemblyData.chassisParts =  $('#chassis_part_grid').appendGrid('getAllValue');
    		self.addedNodeLocations.sort();
    		for ( var nlIdx = 0; nlIdx < self.addedNodeLocations.length; nlIdx++ ) {
    			var nodeLoc = self.addedNodeLocations[nlIdx];
    			var nodeNum = self.getNodeNumber(nodeLoc);
    			var userAssemblyNodeData = new UserAssemblyNodeData();
    			userAssemblyNodeData.nodeAttrs = $('#node_grid_' + nodeNum).appendGrid('getAllValue');
    			userAssemblyNodeData.nodeParts = $('#node_part_grid_' + nodeNum).appendGrid('getAllValue');
    			userAssemblyData.nodeData.push(userAssemblyNodeData);
    		}
    		userAssemblyData.miscParts =  $('#misc_grid').appendGrid('getAllValue');
    		var userAssemblyDataJSON = JSON.stringify(userAssemblyData);
    		$('#submitData').val(userAssemblyDataJSON);
    		$('#submitChassisSerialNum').val(self.chassisSerialNum);
    		$('#submitICN').val(self.icn);
    		$('#submitChassisPartNum').val(self.chassisPartNum);
    		$('#submitChassisPartId').val(self.chassisPartId);
    		$('#submitFirstTouchAssemblyId').val(self.firstTouchAssemblyId);
    		$("#loading-div-background").show();
    		$('#submitForm').submit();
		};
		
		AstAssemblyClass.prototype.loadClick = function(e) {
    		var self = astAssemblyObj;
    		
    		if ( $('#chassisSerial').val() == '' || $('#icn').val() == '' ) {
    			self.displaySimpleDialog('ERROR', 'You must supply a Chassis S/N and Production Order.');
    			return false;
    		}
 
    		self.nodeFileUploadObj.cancelAll();
    		//TODO: fix this
    		for ( var x = 1; x < 5; x++ ) {
    			var nodeNum = x.toString();
    			var nodeLoc = self.getNodeLocation(nodeNum);
	    		$( '#node_' + x.toString() + '_cont' ).html(
	    				'<div id="node_' + x.toString() + '" style="display: none;"><h3>Node ' + nodeLoc + '</h3><div id="node_data_' + nodeNum + '"><table id="node_grid_' + nodeNum + '"></table><br><table id="node_part_grid_' + nodeNum + '"></table></div></div>'
	    				);
    		}
    		
    		
    		if ( $('#icn').val().startsWith('NXCFG') ) {
    			$('#submitTouchLevel').val('1');
    			self.deleteNodeBtn.prop('disabled', true);
    			self.deleteNodeBtn.hide();
    		}
    		else {
    			$('#submitTouchLevel').val('2');
    			self.deleteNodeBtn.prop('disabled', false);
    			self.deleteNodeBtn.show();
    		}
    		
    		self.chassisSerialNum = null;
    		self.icn = null;
    		self.chassisPartNum = null;
    		self.chassisPartId = null;
    		self.firstTouchAssemblyId = null;
    		self.chassisPartAsm = null;
    		self.nodeAsmsArray = [];
    		self.nodePartsArray = [];
    		self.addedNodeLocations = [];
    		self.loadedNodeLocations = [];
    		
    		self.chassisAttrArray = [];
    		$('#chassis_grid').appendGrid('load', self.chassisAttrArray);
    		
    		self.chassisPartsArray = [];
    		$('#chassis_part_grid').appendGrid('load', self.chassisPartsArray);
  
    		self.partsArray = [];
    		$('#parts_grid').appendGrid('load', self.partsArray);
    		
    		self.miscArray = [];
    		$('#misc_grid').appendGrid('load', self.miscArray);
    		
    		$( '#chassis' ).accordion('refresh');
    		$( '#parts' ).accordion('refresh');
	        $( '#misc' ).accordion('refresh');
	        $("#loading-div-background").show();
    		$.ajax({ 
    			method: 'POST',
				url: cp + '/systest/bomPartListAllTouch.json', 
				data: { 
    				chassisSerial: $('#chassisSerial').val(),
    				icn: $('#icn').val()
    			},
				dataType: 'json'
    		})
    		.done(
    			function(data) {
    				var touchLevel = $('#submitTouchLevel').val();
    				if ( touchLevel == '2' ) {
    					self.secondTouchLoadCallback(data);
    				}
    				else {
    					self.firstTouchLoadCallback(data);
    				}
    				self.submitBtn.prop('disabled', false);
    			}	
    		)
    		.fail(function() {
    			$('#loading-div-background').hide();
    			self.displaySimpleDialog('ERROR', 'Unknown error fetching BOM data. Please contact support.');
    		});
		};
		
		AstAssemblyClass.prototype.firstTouchLoadCallback = function(data) { 
			var self = astAssemblyObj;
			$('#loading-div-background').hide();
			if ( data.status == 'FAIL' ) {
				self.displaySimpleDialog('ERROR', data.result.message);
			}
			else {
				self.bomData = data.result.bom;
				self.partDataArray = [];
	
				if ( data.result.bom.part != null ) {
					
					self.chassisPartId = data.result.bom.part.partId;
					self.chassisSerialNum = data.result.bom.serialNum;
					self.icn = data.result.bom.icn;
		    		self.chassisPartNum = data.result.bom.partNum;
					
					for (var i=0; i < data.result.bom.part.partAttrList.length; i++) {
						if ( data.result.bom.part.partAttrList[i].compTypeAttr.dataSourceNm == 'USER' ) {
							self.chassisAttrArray.push(
									new PartAttr(
											data.result.bom.part.partAttrList[i].compTypeAttrId, 
											data.result.bom.part.partId, 
											data.result.bom.part.partAttrList[i].compTypeAttr.attrNm, 
											data.result.bom.part.partAttrList[i].compTypeAttr.attrDs,
											data.result.bom.part.partAttrList[i].compTypeAttr.dataSourceNm, 
											data.result.bom.part.partAttrList[i].compTypeAttr.dataTypeNm, 
											data.result.bom.part.partAttrList[i].valueTx
											)
								);
						}
					}
				}
	
				for (var pIdx=0; pIdx < data.result.bom.bomPartList.length; pIdx++) {
					if ( data.result.bom.bomPartList[pIdx].serialNum == null || data.result.bom.bomPartList[pIdx].serialNum == '') {
						self.miscArray.push(
								new ExcludedPart(
										data.result.bom.bomPartList[pIdx].partNum, 
										data.result.bom.bomPartList[pIdx].partDs, 
										'Configured. Missing S/N.', 
										1)
    							);
					}
					else {
						var userPartAttrsArray = [];
						if ( data.result.bom.bomPartList[pIdx].part.partAttrList != null ) {
							for (var aIdx=0; aIdx < data.result.bom.bomPartList[pIdx].part.partAttrList.length; aIdx++) {
								
								if ( data.result.bom.bomPartList[pIdx].part.partAttrList[aIdx].compTypeAttr.dataSourceNm == 'USER' ) {
									userPartAttrsArray.push(
											new PartAttr(
													data.result.bom.bomPartList[pIdx].part.partAttrList[aIdx].compTypeAttrId, 
													data.result.bom.bomPartList[pIdx].part.partId, 
													data.result.bom.bomPartList[pIdx].part.partAttrList[aIdx].compTypeAttr.attrNm, 
													data.result.bom.bomPartList[pIdx].part.partAttrList[aIdx].compTypeAttr.attrDs,
													data.result.bom.bomPartList[pIdx].part.partAttrList[aIdx].compTypeAttr.dataSourceNm, 
													data.result.bom.bomPartList[pIdx].part.partAttrList[aIdx].compTypeAttr.dataTypeNm, 
													data.result.bom.bomPartList[pIdx].part.partAttrList[aIdx].valueTx
													)
										);
								}
							}
						}
						
						//TODO: DEBUGING - REMOVE THIS - LIMITS RETURNED PARTS TO SMALL SUBSET
						//if ( data.result.bom.bomPartList[pIdx].part.compType.typeNm == 'nic' ||
						//		data.result.bom.bomPartList[pIdx].part.compType.typeNm == 'mb' ) {
						
						var part = new Part(
								data.result.bom.bomPartList[pIdx].part.partId,
								data.result.bom.bomPartList[pIdx].serialNum, 
								data.result.bom.bomPartList[pIdx].partNum,
								data.result.bom.bomPartList[pIdx].partDs,
								data.result.bom.bomPartList[pIdx].part.compType.typeNm,
								data.result.bom.bomPartList[pIdx].part.compType.typeDs,
								0,
								userPartAttrsArray,
								null
								);
						
						self.partDataArray['part_' + part.serialNum] = part;
						
						//Check if parent type is chassis, then add to chassis part list or general parts list
						if ( data.result.bom.bomPartList[pIdx].part.compType.parentCompType != null && 
								data.result.bom.bomPartList[pIdx].part.compType.parentCompType.typeNm == 'chassis' ) {
							self.chassisPartsArray.push(part);
							
						}
						else {
							self.partsArray.push(part);
						}
						
						//}
						
						
					}
				}
				
				for (var epIdx=0; epIdx < data.result.bom.excludedPartList.length; epIdx++) {
					var ep = data.result.bom.excludedPartList[epIdx];
					var excl = new ExcludedPart(
							ep.partNumber, 
							ep.description, 
							ep.comment, 
							ep.partCount);
					self.miscArray.push(excl);
				}
		
				$('#chassis_grid').appendGrid('load', self.chassisAttrArray);
				$('#chassis_part_grid').appendGrid('load', self.chassisPartsArray);				

				$('#parts_grid').appendGrid('load', self.partsArray);
				$('#misc_grid').appendGrid('load', self.miscArray);
				
				$( '#chassis' ).accordion('refresh');
		        $( '#parts' ).accordion('refresh');
		        $( '#misc' ).accordion('refresh');

			}
		};
		
		AstAssemblyClass.prototype.secondTouchLoadCallback = function(data) { 
			var self = astAssemblyObj;
			self.chassisPartsArray = [];
			self.chassisAttrArray = [];
			self.chassisPartAsm = null;
			self.firstTouchAssemblyId = null;
			self.nodeAsmsArray = [];
			self.nodePartsArray = [];
			$('#testText1').html('');

			$('#chassis_grid').appendGrid('load', self.chassisAttrArray);
			$('#chassis_part_grid').appendGrid('load', self.chassisPartsArray);	
			
			$('#node_1').html('<h3>Node A</h3><div id="node_data_1"><table id="node_grid_1"></table><br><table id="node_part_grid_1"></table></div>');
			$('#node_2').html('<h3>Node B</h3><div id="node_data_2"><table id="node_grid_2"></table><br><table id="node_part_grid_2"></table></div>');
			$('#node_3').html('<h3>Node C</h3><div id="node_data_3"><table id="node_grid_3"></table><br><table id="node_part_grid_3"></table></div>');
			$('#node_4').html('<h3>Node D</h3><div id="node_data_4"><table id="node_grid_4"></table><br><table id="node_part_grid_4"></table></div>');

			$('#node_1').hide();
			$('#node_2').hide();
			$('#node_3').hide();
			$('#node_4').hide();
			$('#chassis').accordion('refresh');
			if ( data.status == 'FAIL' ) {
				$("#loading-div-background").hide();
				self.displaySimpleDialog('ERROR', data.result.message);
				return false;
			}
			else if ( $('#chassisSerial').val() == '' ) {
				$('#loading-div-background').hide();
				return false;
			}
			else {	
				self.chassisPartAsm = data.result.chassisPartAsm;
				if ( self.chassisPartAsm.partAsmAttrs != null ) {
					for (var i=0; i < self.chassisPartAsm.partAsmAttrs.length; i++) {
						if ( 'USER' == self.chassisPartAsm.partAsmAttrs[i].dataSourceNm ) {
							self.chassisAttrArray.push(
									new PartAssemblyAttr(
											self.chassisPartAsm.partAsmAttrs[i].compTypeAttrId,
											self.chassisPartAsm.partAsmAttrs[i].partAttrId,
											self.chassisPartAsm.partAsmAttrs[i].partAsmId,
											self.chassisPartAsm.partAsmAttrs[i].partAsmAttrId, 
											self.chassisPartAsm.partAsmAttrs[i].attrNm, 
											self.chassisPartAsm.partAsmAttrs[i].attrDs,
											self.chassisPartAsm.partAsmAttrs[i].dataSourceNm,
											self.chassisPartAsm.partAsmAttrs[i].dataTypeNm,
											self.chassisPartAsm.partAsmAttrs[i].valueTx
											)
								);
						}	
					}
				}
				for (var pIdx=0; pIdx < self.chassisPartAsm.partAsms.length; pIdx++) {
						var userPartAttrsArray = [];
						if ( self.chassisPartAsm.partAsms[pIdx].partAsmAttrs != null ) {
							for (var aIdx=0; aIdx < self.chassisPartAsm.partAsms[pIdx].partAsmAttrs.length; aIdx++) {
								if ( 'USER' == self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].dataSourceNm ) {
									userPartAttrsArray.push(
											new PartAssemblyAttr(
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].compTypeAttrId,
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].partAttrId,
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].partAsmId,
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].partAsmAttrId, 
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].attrNm,
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].attrDs,
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].dataSourceNm,
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].dataTypeNm,
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].valueTx
													)
										);
								}
							}
						}
						var part = new PartAssembly(
								self.chassisPartAsm.partAsms[pIdx].partId,
								self.chassisPartAsm.partAsms[pIdx].partAsmId,
								self.chassisPartAsm.partAsms[pIdx].partNum,
								self.chassisPartAsm.partAsms[pIdx].serialNum,
								self.chassisPartAsm.partAsms[pIdx].typeNm, 
								self.chassisPartAsm.partAsms[pIdx].typeDs, 
								userPartAttrsArray
								);
						
						if ( part.compTypeNm == 'node' ) {
							self.nodeAsmsArray.push(self.chassisPartAsm.partAsms[pIdx].partAsms);
				    		self.nodePartsArray.push(part);
						}
						else {
							self.chassisPartsArray.push(part);
						}
				}
				$('#chassis_grid').appendGrid('load', self.chassisAttrArray);
				$('#chassis_part_grid').appendGrid('load', self.chassisPartsArray);				
				$('#chassis').accordion('refresh');
				self.addSecondTouchNodes();
			}
			
			
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			self.firstTouchAssemblyId = data.result.firstTouchAssemblyId;
			self.bomData = data.result.bom;
			self.partDataArray = [];

			if ( data.result.bom.part != null ) {
				self.chassisPartId = data.result.bom.part.partId;
				self.chassisSerialNum = data.result.bom.serialNum;
				self.icn = data.result.bom.icn;
	    		self.chassisPartNum = data.result.bom.partNum;
			}

			for (var pIdx=0; pIdx < data.result.bom.bomPartList.length; pIdx++) {
				if ( data.result.bom.bomPartList[pIdx].serialNum == null || data.result.bom.bomPartList[pIdx].serialNum == '') {
					self.miscArray.push(
							new ExcludedPart(
									data.result.bom.bomPartList[pIdx].partNum, 
									data.result.bom.bomPartList[pIdx].partDs, 
									'Configured. Missing S/N.', 
									1)
							);
				}
				else {
					var userPartAttrsArray = [];
					if ( data.result.bom.bomPartList[pIdx].part.partAttrList != null ) {
						for (var aIdx=0; aIdx < data.result.bom.bomPartList[pIdx].part.partAttrList.length; aIdx++) {
							
							if ( data.result.bom.bomPartList[pIdx].part.partAttrList[aIdx].compTypeAttr.dataSourceNm == 'USER' ) {
								userPartAttrsArray.push(
										new PartAttr(
												data.result.bom.bomPartList[pIdx].part.partAttrList[aIdx].compTypeAttrId, 
												data.result.bom.bomPartList[pIdx].part.partId, 
												data.result.bom.bomPartList[pIdx].part.partAttrList[aIdx].compTypeAttr.attrNm, 
												data.result.bom.bomPartList[pIdx].part.partAttrList[aIdx].compTypeAttr.attrDs,
												data.result.bom.bomPartList[pIdx].part.partAttrList[aIdx].compTypeAttr.dataSourceNm, 
												data.result.bom.bomPartList[pIdx].part.partAttrList[aIdx].compTypeAttr.dataTypeNm, 
												data.result.bom.bomPartList[pIdx].part.partAttrList[aIdx].valueTx
												)
									);
							}
						}
					}
					
					//For 2nd touch, include only certain part types in assignable parts list
					var typeNm = data.result.bom.bomPartList[pIdx].part.compType.typeNm;
					if ( typeNm == 'ssd' || typeNm == 'hdd' || typeNm == 'nic' || typeNm == 'gpu'  || typeNm == 'dimm' ) {	
						var part = new Part(
								data.result.bom.bomPartList[pIdx].part.partId,
								data.result.bom.bomPartList[pIdx].serialNum, 
								data.result.bom.bomPartList[pIdx].partNum,
								data.result.bom.bomPartList[pIdx].partDs,
								data.result.bom.bomPartList[pIdx].part.compType.typeNm,
								data.result.bom.bomPartList[pIdx].part.compType.typeDs,
								0,
								userPartAttrsArray,
								null
								);
						
						self.partDataArray['part_' + part.serialNum] = part;
						
						//Check if parent type is chassis, then add to chassis part list or general parts list
						//if ( data.result.bom.bomPartList[pIdx].part.compType.parentCompType != null && 
						//		data.result.bom.bomPartList[pIdx].part.compType.parentCompType.typeNm == 'chassis' ) {
						//	self.chassisPartsArray.push(part);	
						//}
						//else {
						self.partsArray.push(part);
						//}
					}
				}
			}
			
			for (var epIdx=0; epIdx < data.result.bom.excludedPartList.length; epIdx++) {
				var ep = data.result.bom.excludedPartList[epIdx];
				var excl = new ExcludedPart(
						ep.partNumber, 
						ep.description, 
						ep.comment, 
						ep.partCount);
				self.miscArray.push(excl);
			}			

			$('#parts_grid').appendGrid('load', self.partsArray);
			$('#misc_grid').appendGrid('load', self.miscArray);
			
			//$( '#chassis' ).accordion('refresh');
	        $( '#parts' ).accordion('refresh');
	        $( '#misc' ).accordion('refresh');
			
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			$("#loading-div-background").hide();
		};
		
		
		AstAssemblyClass.prototype.uploadNodeClick = function(e) {
    		var self = astAssemblyObj;
    		self.nodeFileUploadObj.startUpload(); 
    		return true;
		};
		
		
		AstAssemblyClass.prototype.addFirstTouchNodeCallback = function(nodeNumber, nodeLocation, data) { 
			var self = astAssemblyObj;
			var attrArray = [];
			if ( data.status == 'FAIL' ) {
				self.displaySimpleDialog('ERROR', data.result.message);
				return false;
			}
			else {
				self.addedNodeLocations.push(nodeLocation);
				if ( data.result.compTypeAttrs != null ) {
					for (var i=0; i < data.result.compTypeAttrs.length; i++) {
						if ( data.result.compTypeAttrs[i].dataSourceNm == 'USER' ) {
							attrArray.push(									
									new PartAttr(
											data.result.compTypeAttrs[i].compTypeAttrId, 
											'', //node does not have a part id
											data.result.compTypeAttrs[i].attrNm, 
											data.result.compTypeAttrs[i].attrDs, 
											data.result.compTypeAttrs[i].dataSourceNm, 
											data.result.compTypeAttrs[i].dataTypeNm, 
											nodeNumber
											)
								);
						}
					}
				}
        		$( '#node_' + nodeNumber ).accordion();
        		$( '#node_' + nodeNumber ).accordion( "option", "collapsible", true );
        		$( '#node_' + nodeNumber ).show();

        		$('#node_grid_' + nodeNumber).appendGrid({
                    columns: [
                        { name: 'compTypeAttrId', display: 'compTypeAttrId', type: 'hidden', ctrlAttr: { readonly: true } },       
                        { name: 'partId', display: 'partId', type: 'hidden', ctrlAttr: { readonly: true } }, 
                        { name: 'attrNm', display: 'Name', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
                        { name: 'attrDs', display: 'Node Attribute', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
                        { name: 'dataSourceNm', display: 'Data Source', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
                        { name: 'dataTypeNm', display: 'Data Type', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
                        { name: 'valueTx', display: 'Value', type: 'text', ctrlAttr: { readonly: false }, displayCss: { 'min-width': '160px'} }
                    ],
                    hideRowNumColumn: false,
                    hideButtons: {
            			append: true,
            			removeLast: true,
            			insert: true,
                        remove: true,
                        moveUp: true,
                        moveDown: true
                    }
                });
        		$('#node_grid_' + nodeNumber).appendGrid('load', attrArray);
        		
        		$('#node_part_grid_' + nodeNumber).appendGrid({
                    columns: [
                        { name: 'partId', display: 'partId', type: 'hidden', ctrlAttr: { readonly: true } }, 
                        { name: 'compTypeNm', display: 'compTypeNm', type: 'hidden', ctrlAttr: { readonly: true } },
                        { name: 'compTypeDs', display: 'Part Type', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
    	                { name: 'partNum', display: 'Part Number', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '195px'} },
    	                { name: 'serialNum', display: 'Serial Number', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '195px'} },
    	                { name: 'node', display: 'node', type: 'hidden', ctrlAttr: { readonly: true } }
                    ],
                    customRowButtons: [
      	        	    { uiButton: { icons: { primary: 'ui-icon-circle-minus' }, label: 'Remove' }, click: self.removePartFromNodeCallback }
      	        	],
      	        	initRows: 0,
    	            rowDragging: false,
    	            hideRowNumColumn: false,
    	            hideButtons: {
    	    			append: true,
    	    			removeLast: true,
    	    			insert: true,
    	                remove: true,
    	                moveUp: true,
    	                moveDown: true
    	            },
    	            dataLoaded: function(caller, records) {
    	            	alert('dataLoaded');
    	            },
    	            rowDataLoaded: function(caller, record, rowIndex, uniqueIndex) {
    	            	var thisGrid = this;
                        if ( record.userAttrArray != null ) {
                            $( '#' + thisGrid.idPrefix + '_' + uniqueIndex ).appendGrid('load', record.userAttrArray);
                        }
                    },
 
                    subPanelGetter: function(uniqueIndex) {
                    	var thisGrid = this;
                        return $( '#' + thisGrid.idPrefix + '_' + uniqueIndex ).appendGrid('getAllValue');
                    },
    	            useSubPanel: true,
    	            subPanelBuilder: function (cell, uniqueIndex) {
                    		var thisGrid = this;
                    		var subgrid = $('<table></table>').attr('id', thisGrid.idPrefix + '_' + uniqueIndex).appendTo(cell);
        	                subgrid.appendGrid({
        	                    initRows: 0,
        	                    rowDragging: false,
        	                    hideRowNumColumn: false,
        	                    hideButtons: {
        	    	    			append: true,
        	    	    			removeLast: true,
        	    	    			insert: true,
        	    	                remove: true,
        	    	                moveUp: true,
        	    	                moveDown: true
        	    	            },
        	                    columns: [
        	                        { name: 'compTypeAttrId', display: 'compTypeAttrId', type: 'hidden', ctrlAttr: { readonly: true } },       
        	                        { name: 'partId', display: 'partId', type: 'hidden', ctrlAttr: { readonly: true } }, 
        			                { name: 'attrNm', display: 'Name', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
        			                { name: 'attrDs', display: 'Attribute', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
        			                { name: 'dataSourceNm', display: 'Data Source', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
        			                { name: 'dataTypeNm', display: 'Data Type', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
        			                { name: 'valueTx', display: 'Value', type: 'text', ctrlAttr: { readonly: false }, displayCss: { 'min-width': '160px'} }
        	                    ]
        	                });
    	            }
                });    
        		
        		$( '#node_' + nodeNumber ).accordion('refresh');
        		$( '#chassis' ).accordion('refresh');
			}
			return true;
		};
		
		AstAssemblyClass.prototype.addSecondTouchNodes = function() { 
			var self = astAssemblyObj;
			var nodeNumber = '';
			var nodeLocation = '';
			for (var nIdx = 0; nIdx < self.nodePartsArray.length; nIdx++ ) {
				nodeNumber = (nIdx + 1).toString();
				nodeLocation = self.getNodeLocation(nodeNumber);
				self.addedNodeLocations.push(nodeLocation);
				$( '#node_' + nodeNumber ).accordion();
	    		$( '#node_' + nodeNumber ).accordion( "option", "collapsible", true );
	    		$( '#node_' + nodeNumber ).show();
	    		$('#node_grid_' + nodeNumber).appendGrid({
	                columns: [     
	                    { name: 'compTypeAttrId', display: 'compTypeAttrId', type: 'hidden', ctrlAttr: { readonly: true } },
	                    { name: 'partAttrId', display: 'partAttrId', type: 'hidden', ctrlAttr: { readonly: true } },
	                    { name: 'partAsmId', display: 'partAsmId', type: 'hidden', ctrlAttr: { readonly: true } }, 
	                    { name: 'partAsmAttrId', display: 'partAsmAttrId', type: 'hidden', ctrlAttr: { readonly: true } }, 
	                    { name: 'attrNm', display: 'Name', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
	                    { name: 'attrDs', display: 'Node Attribute', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '300px'} },
	                    { name: 'dataSourceNm', display: 'Source', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '80px'} },
		                { name: 'dataTypeNm', display: 'Data Type', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '40px'} },
	                    { name: 'valueTx', display: 'Value', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '300px'} }
	                ],
	                hideRowNumColumn: false,
	                hideButtons: {
	        			append: true,
	        			removeLast: true,
	        			insert: true,
	                    remove: true,
	                    moveUp: true,
	                    moveDown: true
	                }
	            });
	    		$('#node_grid_' + nodeNumber).appendGrid('load', self.nodePartsArray[nIdx].userAttrArray);
	    		var nodeParts = [];
				for (var pIdx=0; pIdx < self.nodeAsmsArray[nIdx].length; pIdx++) {
						var userPartAttrsArray = [];
						if ( self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs != null ) {
							for (var aIdx=0; aIdx < self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs.length; aIdx++) {
									if ( 'USER' == self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].dataSourceNm ) {
										userPartAttrsArray.push(
												new PartAssemblyAttr(
														self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].compTypeAttrId,
														self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].partAttrId,
														self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].partAsmId,
														self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].partAsmAttrId, 
														self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].attrNm, 
														self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].attrDs, 
														self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].dataSourceNm,
														self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].dataTypeNm,
														self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].valueTx
														)
											);
									}
							}
						}
						
						//For 2nd touch, exclude certain part types from load
						var typeNm = self.nodeAsmsArray[nIdx][pIdx].typeNm;
						if ( typeNm != 'ssd' && typeNm != 'hdd' && typeNm != 'nic' && typeNm != 'gpu'  && typeNm != 'dimm' ) {
							var part = new PartAssembly(
									self.nodeAsmsArray[nIdx][pIdx].partId,
									self.nodeAsmsArray[nIdx][pIdx].partAsmId,
									self.nodeAsmsArray[nIdx][pIdx].partNum,
									self.nodeAsmsArray[nIdx][pIdx].serialNum,
									self.nodeAsmsArray[nIdx][pIdx].typeNm,
									self.nodeAsmsArray[nIdx][pIdx].typeDs,
									userPartAttrsArray
									);	
							nodeParts.push(part);
						}
				}
	    		$('#node_part_grid_' + nodeNumber).appendGrid({
	                columns: [
						{ name: 'partId', display: 'partId', type: 'hidden', ctrlAttr: { readonly: true } },       
						{ name: 'partAsmId', display: 'partAsmId', type: 'hidden', ctrlAttr: { readonly: true } },  
						{ name: 'compTypeNm', display: 'compTypeNm', type: 'hidden', ctrlAttr: { readonly: true } },
						{ name: 'compTypeDs', display: 'Part Type', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '190px' } },
						{ name: 'partNum', display: 'Part Type', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
						{ name: 'serialNum', display: 'Serial Number', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} }      
	                ],
	  	        	initRows: 0,
		            rowDragging: false,
		            hideRowNumColumn: false,
		            hideButtons: {
		    			append: true,
		    			removeLast: true,
		    			insert: true,
		                remove: true,
		                moveUp: true,
		                moveDown: true
		            },
		            dataLoaded: function(caller, records) {
		            	//alert('dataLoaded');
		            },
		            rowDataLoaded: function(caller, record, rowIndex, uniqueIndex) {
		            	var thisGrid = this;
	                    if ( record.userAttrArray != null ) {
	                        $( '#' + thisGrid.idPrefix + '_' + uniqueIndex ).appendGrid('load', record.userAttrArray);
	                        }
	                    },
	 
	                    subPanelGetter: function(uniqueIndex) {
	                    	var thisGrid = this;
	                        return $( '#' + thisGrid.idPrefix + '_' + uniqueIndex ).appendGrid('getAllValue');
	                },
		            useSubPanel: true,
		            subPanelBuilder: function (cell, uniqueIndex) {
	                		var thisGrid = this;
	                		var subgrid = $('<table></table>').attr('id', thisGrid.idPrefix + '_' + uniqueIndex).appendTo(cell);
	    	                subgrid.appendGrid({
	    	                    initRows: 0,
	    	                    rowDragging: false,
	    	                    hideRowNumColumn: false,
	    	                    hideButtons: {
	    	    	    			append: true,
	    	    	    			removeLast: true,
	    	    	    			insert: true,
	    	    	                remove: true,
	    	    	                moveUp: true,
	    	    	                moveDown: true
	    	    	            },
	    	                    columns: [     
	    	                        { name: 'compTypeAttrId', display: 'compTypeAttrId', type: 'hidden', ctrlAttr: { readonly: true } },
	    	                        { name: 'partAttrId', display: 'partAttrId', type: 'hidden', ctrlAttr: { readonly: true } },
	    	                        { name: 'partAsmId', display: 'partAsmId', type: 'hidden', ctrlAttr: { readonly: true } }, 
	    	                        { name: 'partAsmAttrId', display: 'partAsmAttrId', type: 'hidden', ctrlAttr: { readonly: true } }, 
	    			                { name: 'attrNm', display: 'Name', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '190px'} },
	    			                { name: 'attrDs', display: 'Attribute', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '300px'} },
	    			                { name: 'dataSourceNm', display: 'Source', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '80px'} },
					                { name: 'dataTypeNm', display: 'Data Type', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '40px'} },
	    			                { name: 'valueTx', display: 'Value', type: 'text', ctrlAttr: { readonly: false }, displayCss: { 'min-width': '300px'} }
	    	                    ]
	    	                });
		            }
	            });
	    		$('#node_part_grid_' + nodeNumber).appendGrid('load', nodeParts);
	    		
	    		$( '#node_' + nodeNumber ).accordion('refresh');
	    		$( '#node_' + nodeNumber ).accordion( "option", "active", false );
			}
			
			self.deleteNodeBtn.html('<span class="ui-button-text">Remove Node ' + nodeLocation + '</span>');
			
			return true;
		};
		
		AstAssemblyClass.prototype.addPartToNodeCallback = function(evtObj, uniqueIndex, rowData) {
    		var self = astAssemblyObj;
    		if ( rowData.nodeOpt == 0 ) {
    			self.displaySimpleDialog('ERROR', 'Node not selected');
    		}
    		else {
    			var nodeNumber = rowData.nodeOpt.toString();
    			var nodeLocation = self.getNodeLocation(nodeNumber);
    			var nIdx = self.addedNodeLocations.indexOf(nodeLocation);
    			if ( nIdx == -1 ) {
    				self.displaySimpleDialog('ERROR', 'Selected node has not been added: ' + nodeLocation);
    				return false;
    			}
    			
    			
    			var nPart = self.partDataArray['part_' + rowData.serialNum.toString()];
    			var nodePart = new Part(
    					rowData.partId,
    					rowData.serialNum, 
    					rowData.partNum,
						'',
						rowData.compTypeNm,
						rowData.compTypeDs,
						nodeNumber,
						nPart.userAttrArray,
						null
						);
    			var npArray = [];
    			npArray[0] = nodePart;
    			
    			if ( npArray[0].compTypeNm == 'nic' ) {
	    			for( var i = 0; i < npArray[0].userAttrArray.length ; i++ ) {
	    				if( npArray[0].userAttrArray[i].attrNm == 'location' ) {
	        				npArray[0].userAttrArray[i].valueTx = 'SLOT1';
	        			}
	    			}
    			}
    			
    			$('#node_part_grid_' + nodeNumber).appendGrid('appendRow', npArray);
  
				//Remove part from part assign list
    			$('#parts_grid').appendGrid('removeRow', $('#parts_grid').appendGrid('getRowIndex', uniqueIndex));
    			$( '#node_' + nodeNumber ).accordion('refresh');
    			$( '#parts' ).accordion('refresh');
    	
    		}
		};
		
		AstAssemblyClass.prototype.removePartFromNodeCallback = function(evtObj, uniqueIndex, rowData) {
    		var self = astAssemblyObj;

			var nodePart = new Part(
					rowData.partId,
					rowData.serialNum, 
					rowData.partNum,
					rowData.partDs,
					rowData.compTypeNm,
					rowData.compTypeDs,
					0,
					null,
					null
					);
			$('#parts_grid').appendGrid('appendRow', [nodePart]);
			$('#node_part_grid_' + rowData.node.toString()).appendGrid('removeRow', $('#node_part_grid_' + rowData.node.toString()).appendGrid('getRowIndex', uniqueIndex));
			$( '#node_' + rowData.node.toString() ).accordion('refresh');
			$( '#parts' ).accordion('refresh');
		};
		
		AstAssemblyClass.prototype.deleteNodeClick = function(e) {
			var self = astAssemblyObj;
    		if ( self.addedNodeLocations.length == 0 ) {
    			self.displaySimpleDialog('ERROR', 'No NODES to delete');
    			return false;
    		}
    		
    		self.addedNodeLocations.sort();
    		var nodeLocation = self.addedNodeLocations[(self.addedNodeLocations.length - 1)];
    		var nodeNumber = self.getNodeNumber(nodeLocation);

			$( '#deleteNodeDialog' ).dialog({
				resizable: false,
				height: 'auto',
				width: 400,
				modal: true,
				buttons: {
					'Remove': function() {					
						self.confirmedDeleteNode(nodeLocation, nodeNumber);
						$( this ).dialog( 'close' );
					},
					Cancel: function() {
						$( this ).dialog( 'close' );
					}
				}
			});
			
			$('#deleteNodeDialog').dialog('option', 'title', 'Remove Node ' + nodeLocation + ' ?');
    		
		};

		AstAssemblyClass.prototype.confirmedDeleteNode = function(nodeLocation, nodeNumber) {
    		var self = astAssemblyObj;

    		/*
    		//No longer adding parts back into parts list. New support for node devan
    		var rowCount = $('#node_part_grid_' + self.nodeCount.toString()).appendGrid('getRowCount');
    		for ( var currRow = (rowCount - 1); currRow >= 0; currRow-- ) {
    			var rowData = $('#node_part_grid_' + self.nodeCount.toString()).appendGrid('getRowValue', currRow);
    			var nodePart = new Part(
    					rowData.partId,
    					rowData.serialNum, 
    					rowData.partNum,
    					rowData.partDs,
    					rowData.compTypeNm,
    					rowData.compTypeDs,
    					0,
    					null,
    					null
    					);
    			$('#parts_grid').appendGrid('appendRow', [nodePart]);
    			$('#node_part_grid_' + rowData.node.toString()).appendGrid('removeRow', currRow);
    			
    		}
			$( '#parts' ).accordion('refresh');
			*/
    		
			
    		$( '#node_' + nodeNumber + '_cont' ).html(
    				'<div id="node_' + nodeNumber + '" style="display: none;"><h3>Node ' + nodeLocation + '</h3><div id="node_data_' + nodeNumber + '"><table id="node_grid_' + nodeNumber + '"></table><br><table id="node_part_grid_' + nodeNumber + '"></table></div></div>'
    				);
    		
    		self.addedNodeLocations.splice((self.addedNodeLocations.length - 1), 1);
    		if ( self.addedNodeLocations.length == 0 ) {
    			self.deleteNodeBtn.html('<span class="ui-button-text">Remove Node</span>');
    			self.deleteNodeBtn.prop('disabled', true);
    		}
    		else {
    			self.addedNodeLocations.sort();
        		nodeLocation = self.addedNodeLocations[(self.addedNodeLocations.length - 1)];
    			self.deleteNodeBtn.html('<span class="ui-button-text">Remove Node ' + nodeLocation + '</span>');
    			self.deleteNodeBtn.prop('disabled', false);
    		}
		};
		
		AstAssemblyClass.prototype.getNodeLocation = function(num) {
			if ( num == '1' ) {
				return 'A';
			}
			else if ( num == '2' ) {
				return 'B';
			}
			else if ( num == '3' ) {
				return 'C';
			}
			else if ( num == '4' ) {
				return 'D';
			}
			return '';
		};
		
		AstAssemblyClass.prototype.getNodeNumber = function(location) {
			if ( location == 'A' ) {
				return '1';
			}
			else if ( location == 'B' ) {
				return '2';
			}
			else if ( location == 'C' ) {
				return '3';
			}
			else if ( location == 'D' ) {
				return '4';
			}
			return '';
		};

		astAssemblyObj = new AstAssemblyClass();
		astAssemblyObj.initWidgets();

    }); //document ready
} ) ( jQuery );