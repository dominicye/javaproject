var astAssemblyObj = null;

( function($) {
    $(document).ready(function() {

  		function Part(partId, serialNum, partNum, partBomDs, compTypeNm, node, userAttrArray, comment) {
			this.partId = partId;
			this.serialNum = serialNum;
			this.partNum = partNum;
			this.partBomDs = partBomDs;
			this.compTypeNm = compTypeNm;
			this.node = node;
			this.userAttrArray = userAttrArray;
			this.comment = comment;
		}
  		
  		function ExcludedPart(partNum, partBomDs, comment, partCount) {
			this.partNum = partNum;
			this.partBomDs = partBomDs;
			this.comment = comment;
			this.partCount = partCount;
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
    	
    	
    	var AstAssemblyClass = function() {
    		this.nodeCount = 0;
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
    		self.addNodeBtn.click(self.addNodeClick);
    		
    		self.deleteNodeBtn = $('#deleteNodeBtn').button();
    		self.deleteNodeBtn.click(self.deleteNodeClick);
    		
    		self.submitBtn = $('#submitBtn').button();
    		self.submitBtn.click(self.submitClick);
    		
    		
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
	                { name: 'compTypeNm', display: 'Part Type', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
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
	                { name: 'compTypeNm', display: 'Part Type', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
	                { name: 'partNum', display: 'Part Number', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '195px'} },
	                { name: 'serialNum', display: 'Serial Number', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '195px'} },
	                { name: 'nodeOpt', display: 'Node', type: 'select', ctrlOptions: { 0: '', 1: 'Node 1', 2: 'Node 2', 3: 'Node 3', 4: 'Node 4'}, displayCss: { 'min-width': '70px'} }
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
	    		for ( var i = 1; i <= self.nodeCount; i++ ) {
	    			var userAssemblyNodeData = new UserAssemblyNodeData();
	    			userAssemblyNodeData.nodeAttrs = $('#node_grid_' + i.toString()).appendGrid('getAllValue');
	    			userAssemblyNodeData.nodeParts = $('#node_part_grid_' + i.toString()).appendGrid('getAllValue');
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
    		for ( var i = 1; i <= self.nodeCount; i++ ) {
    			var userAssemblyNodeData = new UserAssemblyNodeData();
    			userAssemblyNodeData.nodeAttrs = $('#node_grid_' + i.toString()).appendGrid('getAllValue');
    			userAssemblyNodeData.nodeParts = $('#node_part_grid_' + i.toString()).appendGrid('getAllValue');
    			userAssemblyData.nodeData.push(userAssemblyNodeData);
    		}
    		userAssemblyData.miscParts =  $('#misc_grid').appendGrid('getAllValue');
    		var userAssemblyDataJSON = JSON.stringify(userAssemblyData);
    		$('#submitData').val(userAssemblyDataJSON);
    		$('#submitChassisSerialNum').val(self.chassisSerialNum);
    		$('#submitICN').val(self.icn);
    		$('#submitChassisPartNum').val(self.chassisPartNum);
    		$('#submitChassisPartId').val(self.chassisPartId);
    		$("#loading-div-background").show();
    		$('#submitForm').submit();
    		
		};
		
		AstAssemblyClass.prototype.loadClick = function(e) {
    		var self = astAssemblyObj;
    		
    		if ( $('#chassisSerial').val() == '' || $('#icn').val() == '' ) {
    			self.displaySimpleDialog('ERROR', 'You must supply a Chassis S/N and ICN.');
    			return false;
    		}
    		
    		self.nodeCount = 0;
    		//TODO: fix this
    		for ( var x = 1; x < 5; x++ ) {
	    		$( '#node_' + x.toString() + '_cont' ).html(
	    				'<div id="node_' + x.toString() + '" style="display: none;"><h3>Node ' + x.toString() + '</h3><div id="node_data_' + x.toString() + '"><table id="node_grid_' + x.toString() + '"></table><br><table id="node_part_grid_' + x.toString() + '"></table></div></div>'
	    				);
    		}
    		
    		self.chassisSerialNum = null;
    		self.icn = null;
    		self.chassisPartNum = null;
    		self.chassisPartId = null;
    		
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
				url: cp + '/systest/bomPartList.json', 
				data: { 
    				chassisSerial: $('#chassisSerial').val(),
    				icn: $('#icn').val()
    			},
				dataType: 'json'
    		})
    		.done(self.loadCallback)
    		.fail(function() {
    			$("#loading-div-background").hide();
    			self.displaySimpleDialog('ERROR', 'Unknown error fetching BOM data. Please contact support.');
    		});
		};
		
		AstAssemblyClass.prototype.loadCallback = function(data) { 
			var self = astAssemblyObj;
			$("#loading-div-background").hide();
			if ( data.status == 'FAIL' ) {
				self.displaySimpleDialog('ERROR', data.result.message);
			}
			else {
				//displaySimpleDialog('INFO', JSON.stringify(data.result));
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
								data.result.bom.bomPartList[pIdx].part.compTypeNm,
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
		
		
		AstAssemblyClass.prototype.addNodeClick = function(e) {
    		var self = astAssemblyObj;
    		
    		if ( self.nodeCount == 4 ) {
    			self.displaySimpleDialog('ERROR', 'Maximum number of NODES exceeded');
    			return false;
    		}
    		self.nodeCount += 1;
    		
    		$.ajax({ 
    			method: 'POST',
				url: cp + '/systest/getComponentTypeAttributesByTypeName.json', 
				data: { compTypeNm: 'node' },
				dataType: 'json'
    		})
    		.done(self.addNodeCallback)
    		.fail(function() {
    			self.displaySimpleDialog('ERROR', 'Unknown error fetching node attr data. Please contact support.');
    		});
    		return true;
		};
		
		
		AstAssemblyClass.prototype.addNodeCallback = function(data) { 
			var self = astAssemblyObj;
			var attrArray = [];
			if ( data.status == 'FAIL' ) {
				self.displaySimpleDialog('ERROR', data.result.message);
				return false;
			}
			else {
				//self.displaySimpleDialog('INFO', JSON.stringify(data.result));
				if ( data.result.compTypeAttrs != null ) {
					for (var i=0; i < data.result.compTypeAttrs.length; i++) {
						if ( data.result.compTypeAttrs[i].dataSourceNm == 'USER' ) {
							var v = '';
							if ( data.result.compTypeAttrs[i].attrNm == 'location' ) {
								v = self.nodeCount;
							}
							attrArray.push(
									
									new PartAttr(
											data.result.compTypeAttrs[i].compTypeAttrId, 
											'', //node does not have a part id
											data.result.compTypeAttrs[i].attrNm, 
											data.result.compTypeAttrs[i].attrDs, 
											data.result.compTypeAttrs[i].dataSourceNm, 
											data.result.compTypeAttrs[i].dataTypeNm, 
											v
											)
								);
						}
					}
				}

	
        		$( '#node_' + self.nodeCount.toString() ).accordion();
        		$( '#node_' + self.nodeCount.toString() ).accordion( "option", "collapsible", true );
        		$( '#node_' + self.nodeCount.toString() ).show();

        		$('#node_grid_' + self.nodeCount.toString()).appendGrid({
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
        		$('#node_grid_' + self.nodeCount.toString()).appendGrid('load', attrArray);
        		
        		$('#node_part_grid_' + self.nodeCount.toString()).appendGrid({
                    columns: [
                        { name: 'partId', display: 'partId', type: 'hidden', ctrlAttr: { readonly: true } }, 
                        { name: 'compTypeNm', display: 'Part Type', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
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
        		///$('#node_part_grid_' + self.nodeCount.toString()).appendGrid('load', []);
        		
        		$( '#node_' + self.nodeCount.toString() ).accordion('refresh');
        		$( '#chassis' ).accordion('refresh');
				
			}
			return true;
		};
		
		AstAssemblyClass.prototype.addPartToNodeCallback = function(evtObj, uniqueIndex, rowData) {
    		var self = astAssemblyObj;
    		if ( rowData.nodeOpt == 0 ) {
    			self.displaySimpleDialog('ERROR', 'Node not selected');
    		}
    		else {
    			//alert('Clicked add row ' + uniqueIndex + ', ' + rowData.partNum + ' to node # ' + rowData.nodeOpt);
    			if ( rowData.nodeOpt > self.nodeCount ) {
    				self.displaySimpleDialog('ERROR', 'Selected node has not been added: ' + rowData.nodeOpt.toString());
    				return false;
    			}
    			
    			
    			var nPart = self.partDataArray['part_' + rowData.serialNum.toString()];
    			var nodePart = new Part(
    					rowData.partId,
    					rowData.serialNum, 
    					rowData.partNum,
						'',
						rowData.compTypeNm,
						rowData.nodeOpt.toString(),
						nPart.userAttrArray,
						null
						);
    			var npArray = [];
    			npArray[0] = nodePart;
    			$('#node_part_grid_' + rowData.nodeOpt.toString()).appendGrid('appendRow', npArray);
  
				//Remove part from part assign list
    			$('#parts_grid').appendGrid('removeRow', $('#parts_grid').appendGrid('getRowIndex', uniqueIndex));
    			$( '#node_' + rowData.nodeOpt.toString() ).accordion('refresh');
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
    		if ( self.nodeCount == 0 ) {
    			self.displaySimpleDialog('ERROR', 'No NODES to delete');
    			return false;
    		}
    		
    		
    		var rowCount = $('#node_part_grid_' + self.nodeCount.toString()).appendGrid('getRowCount');
    		for ( var currRow = (rowCount - 1); currRow >= 0; currRow-- ) {
    			var rowData = $('#node_part_grid_' + self.nodeCount.toString()).appendGrid('getRowValue', currRow);
    			var nodePart = new Part(
    					rowData.partId,
    					rowData.serialNum, 
    					rowData.partNum,
    					rowData.partDs,
    					rowData.compTypeNm,
    					0,
    					null,
    					null
    					);
    			$('#parts_grid').appendGrid('appendRow', [nodePart]);
    			$('#node_part_grid_' + rowData.node.toString()).appendGrid('removeRow', currRow);
    			
    		}
			$( '#parts' ).accordion('refresh');
    		$( '#node_' + self.nodeCount.toString() + '_cont' ).html(
    				'<div id="node_' + self.nodeCount.toString() + '" style="display: none;"><h3>Node ' + self.nodeCount.toString() + '</h3><div id="node_data_' + self.nodeCount.toString() + '"><table id="node_grid_' + self.nodeCount.toString() + '"></table><br><table id="node_part_grid_' + self.nodeCount.toString() + '"></table></div></div>'
    				);
    
    		self.nodeCount -= 1;
		};


		astAssemblyObj = new AstAssemblyClass();
		astAssemblyObj.initWidgets();
    	
    	

        
    }); //document ready
} ) ( jQuery );