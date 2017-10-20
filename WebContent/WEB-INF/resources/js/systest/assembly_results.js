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
    		this.chassisPartsArray = [];
    		this.chassisAttrArray = [];
    		this.miscPartsArray = [];
    		this.alertDialog = null;
    		this.searchBtn = null;
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
    		self.searchBtn = $('#searchBtn').button();
    		self.searchBtn.click(self.loadAssembly);
	    	$( '#chassis' ).accordion();
	    	$( '#chassis' ).accordion( "option", "collapsible", true );
	    	$( '#chassis' ).accordion('refresh');
	    	$( '#misc' ).accordion();
	    	$( '#misc' ).accordion( "option", "collapsible", true );
	    	$( '#misc' ).accordion('refresh');	
		};

		AstAssemblyClass.prototype.loadCallback = function(data) { 
			var self = astAssemblyObj;
			self.nodeCount = 0;
			self.chassisPartsArray = [];
			self.chassisAttrArray = [];
			self.miscPartsArray = [];
			self.chassisPartAsm = null;
			self.nodeAsmsArray = [];
			self.nodePartsArray = [];
			$('#chassis_data').html('<table id="chassis_grid"></table><br><table id="chassis_part_grid"></table>');
			$('#node_1').html('<h3>Node</h3><div id="node_data_1"><table id="node_grid_1"></table><br><table id="node_part_grid_1"></table></div>');
			$('#node_2').html('<h3>Node</h3><div id="node_data_2"><table id="node_grid_2"></table><br><table id="node_part_grid_2"></table></div>');
			$('#node_3').html('<h3>Node</h3><div id="node_data_3"><table id="node_grid_3"></table><br><table id="node_part_grid_3"></table></div>');
			$('#node_4').html('<h3>Node</h3><div id="node_data_4"><table id="node_grid_4"></table><br><table id="node_part_grid_4"></table></div>');
			$('#misc_data').html('<table id="misc_grid"></table>');
			$('#node_1').hide();
			$('#node_2').hide();
			$('#node_3').hide();
			$('#node_4').hide();
			$('#chassis').accordion('refresh');
			$('#misc').accordion('refresh');
			if ( data.status == 'FAIL' ) {
				self.displaySimpleDialog('ERROR', data.result.message);
			}
			else if ( $('#submitChassisSerialNum').val() == '' ) {
				$("#loading-div-background").hide();
				return false;
			}
			else {	
				///////////////////////////////////
		    	$('#chassis_grid').appendGrid({
		            columns: [
		                { name: 'compTypeAttrId', display: 'compTypeAttrId', type: 'hidden', ctrlAttr: { readonly: true } }, 
		                { name: 'partId', display: 'partId', type: 'hidden', ctrlAttr: { readonly: true } }, 
		                { name: 'attrNm', display: 'Name', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
		                { name: 'attrDs', display: 'Chassis Attribute', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '300px'} },
		                { name: 'dataSourceNm', display: 'Data Source', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
		                { name: 'dataTypeNm', display: 'Data Type', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
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
		    	$('#chassis_part_grid').appendGrid({
		            columns: [
		                { name: 'partId', display: 'partId', type: 'hidden', ctrlAttr: { readonly: true } },      
		                { name: 'compTypeNm', display: 'Chassis Parts', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '300px' } },
		                { name: 'partNum', display: 'Part Number', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
		                { name: 'serialNum', display: 'Serial Number', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} }
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
				                { name: 'attrDs', display: 'Attribute', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '300px'} },
				                { name: 'dataSourceNm', display: 'Data Source', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
				                { name: 'dataTypeNm', display: 'Data Type', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
				                { name: 'valueTx', display: 'Value', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '300px'} }
		                    ]
		                });
		            }
		        });
		    	$('#misc_grid').appendGrid({
		            columns: [
		                { name: 'partNum', display: 'Part Number', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '180px'} },
		                { name: 'comment', display: 'Comment', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '180px'} },
		                { name: 'partCount', display: 'Count', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '50px'} }
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
		    	$('#misc_grid').appendGrid('load', []);		    
		    	//////////////////////////////////////////////////////////
				
				self.chassisPartAsm = data.result.chassisPartAsm;
				
				
				if ( self.chassisPartAsm.excludedPartAsms != null ) {
					var excludedPartsArray = [];
					for (var epIdx=0; epIdx < self.chassisPartAsm.excludedPartAsms.length; epIdx++) {
						excludedPartsArray.push(
								new ExcludedPart(
										self.chassisPartAsm.excludedPartAsms[epIdx].partNumber, 
										'', 
										self.chassisPartAsm.excludedPartAsms[epIdx].comment, 
										self.chassisPartAsm.excludedPartAsms[epIdx].partCount
										)
							);
					}
					$('#misc_grid').appendGrid('load', excludedPartsArray);	
				}
				
				
				if ( self.chassisPartAsm.partAsmAttrs != null ) {

					for (var i=0; i < self.chassisPartAsm.partAsmAttrs.length; i++) {
							self.chassisAttrArray.push(
									new PartAttr(
											self.chassisPartAsm.partAsmAttrs[i].partAsmAttrId, 
											'', //partId
											self.chassisPartAsm.partAsmAttrs[i].attrNm, 
											self.chassisPartAsm.partAsmAttrs[i].attrDs,
											'', //dataSourceNm
											'', //dataTypeNm 
											self.chassisPartAsm.partAsmAttrs[i].valueTx
											)
								);
	
					}
				}
				for (var pIdx=0; pIdx < self.chassisPartAsm.partAsms.length; pIdx++) {
						var userPartAttrsArray = [];
						if ( self.chassisPartAsm.partAsms[pIdx].partAsmAttrs != null ) {
							for (var aIdx=0; aIdx < self.chassisPartAsm.partAsms[pIdx].partAsmAttrs.length; aIdx++) {
							
									userPartAttrsArray.push(
											new PartAttr(
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].partAsmAttrId, 
													self.chassisPartAsm.partAsms[pIdx].partAsmId, 
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].attrNm,
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].attrDs, 
													'', //dataSourceNm, 
													'', //dataTypeNm, 
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].valueTx
													)
										);
							
							}
						}
						var part = new Part(
								self.chassisPartAsm.partAsms[pIdx].partAsmId,
								'', //serialNum, 
								'', //partNum,
								'', //partDs,
								self.chassisPartAsm.partAsms[pIdx].typeDs, //compTypeNm,
								0,
								userPartAttrsArray,
								null //comment
								);
						
						if ( part.compTypeNm == 'Node' ) {
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
				$('#misc').accordion('refresh');
				self.addNodes();
			}
			$("#loading-div-background").hide();
		};

		AstAssemblyClass.prototype.addNodes = function() { 
			var self = astAssemblyObj;
			for (var nIdx = 0; nIdx < self.nodePartsArray.length; nIdx++ ) {
				self.nodeCount += 1;
				$( '#node_' + self.nodeCount.toString() ).accordion();
	    		$( '#node_' + self.nodeCount.toString() ).accordion( "option", "collapsible", true );
	    		$( '#node_' + self.nodeCount.toString() ).show();
	    		$('#node_grid_' + self.nodeCount.toString()).appendGrid({
	                columns: [
	                    { name: 'compTypeAttrId', display: 'compTypeAttrId', type: 'hidden', ctrlAttr: { readonly: true } },       
	                    { name: 'partId', display: 'partId', type: 'hidden', ctrlAttr: { readonly: true } }, 
	                    { name: 'attrNm', display: 'Name', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
	                    { name: 'attrDs', display: 'Node Attribute', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '300px'} },
	                    { name: 'dataSourceNm', display: 'Data Source', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
	                    { name: 'dataTypeNm', display: 'Data Type', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
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
	    		$('#node_grid_' + self.nodeCount.toString()).appendGrid('load', self.nodePartsArray[nIdx].userAttrArray);
	    		var nodeParts = [];
				for (var pIdx=0; pIdx < self.nodeAsmsArray[nIdx].length; pIdx++) {
						var userPartAttrsArray = [];
						if ( self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs != null ) {
							for (var aIdx=0; aIdx < self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs.length; aIdx++) {
									userPartAttrsArray.push(
											new PartAttr(
													self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].partAsmAttrId, 
													self.nodeAsmsArray[nIdx][pIdx].partAsmId, 
													self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].attrNm, 
													self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].attrDs, 
													'', //dataSourceNm, 
													'', //dataTypeNm, 
													self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].valueTx
													)
										);
							}
						}
						var part = new Part(
								self.nodeAsmsArray[nIdx][pIdx].partAsmId,
								'', //serialNum, 
								'', //partNum,
								'', //partDs,
								self.nodeAsmsArray[nIdx][pIdx].typeDs, //compTypeNm,
								0,
								userPartAttrsArray,
								null //comment
								);						
						nodeParts.push(part);
				}
	    		$('#node_part_grid_' + self.nodeCount.toString()).appendGrid({
	                columns: [
	                    { name: 'partId', display: 'partId', type: 'hidden', ctrlAttr: { readonly: true } }, 
	                    { name: 'compTypeNm', display: 'Node Part', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '300px'} },
		                { name: 'partNum', display: 'Part Number', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
		                { name: 'serialNum', display: 'Serial Number', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
		                { name: 'node', display: 'node', type: 'hidden', ctrlAttr: { readonly: true } }
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
	    	                        { name: 'partId', display: 'partId', type: 'hidden', ctrlAttr: { readonly: true } }, 
	    			                { name: 'attrNm', display: 'Name', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
	    			                { name: 'attrDs', display: 'Attribute', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '300px'} },
	    			                { name: 'dataSourceNm', display: 'Data Source', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
	    			                { name: 'dataTypeNm', display: 'Data Type', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
	    			                { name: 'valueTx', display: 'Value', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '300px'} }
	    	                    ]
	    	                });
		            }
	            });
	    		$('#node_part_grid_' + self.nodeCount.toString()).appendGrid('load', nodeParts);
	    		
	    		$( '#node_' + self.nodeCount.toString() ).accordion('refresh');
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
						null //comment
						);
    			var npArray = [];
    			npArray[0] = nodePart;
    			$('#node_part_grid_' + rowData.nodeOpt.toString()).appendGrid('appendRow', npArray);
    			$( '#node_' + rowData.nodeOpt.toString() ).accordion('refresh');
    		}
		};
			
		AstAssemblyClass.prototype.loadAssembly = function() {
			var self = astAssemblyObj;
    		self.nodeCount = 0;
    		$("#loading-div-background").show();
    		$.ajax({ 
    			method: 'POST',
				url: cp + '/systest/getAssemblyResultsData.json', 
				data: { 
    				submitChassisSerialNum: $('#submitChassisSerialNum').val(),
    				touchLevel: $('#touchLevel').val(),
    				returnMissingAttrs: 'N'
    				},
				contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
				dataType: 'json'
    		})
    		.done(self.loadCallback)
    		.fail(function(jqXHR, textStatus) {
    			$("#loading-div-background").hide();
    			self.displaySimpleDialog('ERROR', 'Unknown error fetching node assembly data. Please contact support. Status: ' + textStatus);
    		});
    		return true;
		};
		
		astAssemblyObj = new AstAssemblyClass();
		astAssemblyObj.initWidgets(); 
		var submitError = $('#submitError').html();
		if ( submitError != null && submitError != '' ) {
			$("#loading-div-background").hide();
			astAssemblyObj.displaySimpleDialog('ERROR', 'Assembly Error: ' + submitError);
    	}
		else if ( $('#submitChassisSerialNum').val() != '' ) {
			astAssemblyObj.loadAssembly(); 
		}
		
    }); //document ready
} ) ( jQuery );