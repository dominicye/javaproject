var astAssemblyObj = null;

( function($) {
    $(document).ready(function() {

  		function Part(partAsmId, compTypeNm, userAttrArray) {
  			this.partAsmId = partAsmId;
			this.compTypeNm = compTypeNm;
			this.userAttrArray = userAttrArray;
		}
		
		function PartAttr(compTypeAttrId, partAttrId, partAsmId, partAsmAttrId, attrNm, attrDs, dataSourceNm, dataTypeNm, valueTx, editableFl) {
			this.compTypeAttrId = compTypeAttrId;
			this.partAttrId = partAttrId;
			this.partAsmId = partAsmId;
			this.partAsmAttrId = partAsmAttrId;
			this.attrNm = attrNm;
			this.attrDs = attrDs;
			this.dataSourceNm = dataSourceNm;
			this.dataTypeNm = dataTypeNm;
			this.valueTx = valueTx;
			this.editableFl = editableFl;
		}
    	
    	
    	var AstAssemblyClass = function() {
    		this.nodeCount = 0;
    		this.chassisPartsArray = [];
    		this.chassisAttrArray = [];
    		this.alertDialog = null;
    		this.searchBtn = null;
    		this.submitBtn = null;
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
    
    		self.submitBtn = $('#submitBtn').button();
    		self.submitBtn.click(self.submitClick);
    		
	    	$( '#chassis' ).accordion();
	    	$( '#chassis' ).accordion( "option", "collapsible", true );
	    	$( '#chassis' ).accordion('refresh');

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
	    		var userAssemblyDataJSON = JSON.stringify(userAssemblyData);
	    		$('#testText1').html(userAssemblyDataJSON);
	    	});
	    	
		};

		AstAssemblyClass.prototype.loadCallback = function(data) { 
			var self = astAssemblyObj;
			self.nodeCount = 0;
			self.chassisPartsArray = [];
			self.chassisAttrArray = [];
			self.chassisPartAsm = null;
			self.nodeAsmsArray = [];
			self.nodePartsArray = [];
			$('#testText1').html('');
			$('#chassis_data').html('<table id="chassis_grid"></table><br><table id="chassis_part_grid"></table>');
			$('#node_1').html('<h3>Node</h3><div id="node_data_1"><table id="node_grid_1"></table><br><table id="node_part_grid_1"></table></div>');
			$('#node_2').html('<h3>Node</h3><div id="node_data_2"><table id="node_grid_2"></table><br><table id="node_part_grid_2"></table></div>');
			$('#node_3').html('<h3>Node</h3><div id="node_data_3"><table id="node_grid_3"></table><br><table id="node_part_grid_3"></table></div>');
			$('#node_4').html('<h3>Node</h3><div id="node_data_4"><table id="node_grid_4"></table><br><table id="node_part_grid_4"></table></div>');
			//$('#misc_data').html('<table id="misc_grid"></table>');
			$('#node_1').hide();
			$('#node_2').hide();
			$('#node_3').hide();
			$('#node_4').hide();
			$('#chassis').accordion('refresh');
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
		                { name: 'partAttrId', display: 'partAttrId', type: 'hidden', ctrlAttr: { readonly: true } },
		                { name: 'partAsmId', display: 'partAsmId', type: 'hidden', ctrlAttr: { readonly: true } }, 
		                { name: 'partAsmAttrId', display: 'partAsmAttrId', type: 'hidden', ctrlAttr: { readonly: true } }, 
		                { name: 'editableFl', display: 'editableFl', type: 'hidden', ctrlAttr: { readonly: true } }, 
		                { name: 'attrNm', display: 'Name', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
		                { name: 'attrDs', display: 'Chassis Attribute', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '300px'} },
		                { name: 'dataSourceNm', display: 'Source', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '80px'} },
		                { name: 'dataTypeNm', display: 'Data Type', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '40px'} },
		                { 
		                	name: 'valueTx', 
		                	display: 'Value', 
		                	type: 'custom', 
		                	ctrlAttr: { readonly: false }, 
		                	displayCss: { 'min-width': '300px'},
		                	customBuilder: function (parent, idPrefix, name, uniqueIndex) {
		                        var ctrlId = idPrefix + '_' + name + '_' + uniqueIndex;
		                        var ctrl = $('<input/>', { type: 'text', id: ctrlId }).appendTo(parent);
		                        return ctrl;
		                    },
		                    customGetter: function (idPrefix, name, uniqueIndex) {
		                        var ctrlId = idPrefix + '_' + name + '_' + uniqueIndex;
		                        return $('#' + ctrlId).val();
		                    },
		                	customSetter: function (idPrefix, name, uniqueIndex, value) {
		                        var ctrlId = idPrefix + '_' + name + '_' + uniqueIndex;
		                        var editableCtrlId = idPrefix + '_editableFl_' + uniqueIndex;
		                        $('#' + ctrlId).val(value);
		                        if ( $('#' + editableCtrlId).val() == 'N' ) {
		                        	$('#' + ctrlId).css("background-color", "white");
		                        	$('#' + ctrlId).prop('disabled', true);
		                        }
		                    }
		                }
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
		                { name: 'partAsmId', display: 'partAsmId', type: 'hidden', ctrlAttr: { readonly: true } },      
		                { name: 'compTypeNm', display: 'Chassis Parts', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '190px' } }
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
		                        { name: 'partAttrId', display: 'partAttrId', type: 'hidden', ctrlAttr: { readonly: true } },
		                        { name: 'partAsmId', display: 'partAsmId', type: 'hidden', ctrlAttr: { readonly: true } }, 
		                        { name: 'partAsmAttrId', display: 'partAsmAttrId', type: 'hidden', ctrlAttr: { readonly: true } }, 
		                        { name: 'editableFl', display: 'editableFl', type: 'hidden', ctrlAttr: { readonly: true } }, 
				                { name: 'attrNm', display: 'Name', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
				                { name: 'attrDs', display: 'Attribute', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '300px'} },
				                { name: 'dataSourceNm', display: 'Source', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '80px'} },
				                { name: 'dataTypeNm', display: 'Data Type', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '40px'} },
				                { 
				                	name: 'valueTx', 
				                	display: 'Value', 
				                	type: 'custom', 
				                	ctrlAttr: { readonly: false }, 
				                	displayCss: { 'min-width': '300px'},
				                	customBuilder: function (parent, idPrefix, name, uniqueIndex) {
				                        var ctrlId = idPrefix + '_' + name + '_' + uniqueIndex;
				                        var ctrl = $('<input/>', { type: 'text', id: ctrlId }).appendTo(parent);
				                        return ctrl;
				                    },
				                    customGetter: function (idPrefix, name, uniqueIndex) {
				                        var ctrlId = idPrefix + '_' + name + '_' + uniqueIndex;
				                        return $('#' + ctrlId).val();
				                    },
				                	customSetter: function (idPrefix, name, uniqueIndex, value) {
				                        var ctrlId = idPrefix + '_' + name + '_' + uniqueIndex;
				                        var editableCtrlId = idPrefix + '_editableFl_' + uniqueIndex;
				                        $('#' + ctrlId).val(value);
				                        if ( $('#' + editableCtrlId).val() == 'N' ) {
				                        	$('#' + ctrlId).css("background-color", "white");
				                        	$('#' + ctrlId).prop('disabled', true);
				                        }
				                    }
				                }
		                    ]
		                });
		            }
		        });
		        
		    	$('#chassis_grid').appendGrid('load', []);
		    	$('#chassis_part_grid').appendGrid('load', []);		    
		    	//////////////////////////////////////////////////////////
	
				self.chassisPartAsm = data.result.chassisPartAsm;

				if ( self.chassisPartAsm.partAsmAttrs != null ) {

					for (var i=0; i < self.chassisPartAsm.partAsmAttrs.length; i++) {
							self.chassisAttrArray.push(
									new PartAttr(
											self.chassisPartAsm.partAsmAttrs[i].compTypeAttrId,
											self.chassisPartAsm.partAsmAttrs[i].partAttrId,
											self.chassisPartAsm.partAsmAttrs[i].partAsmId,
											self.chassisPartAsm.partAsmAttrs[i].partAsmAttrId, 
											self.chassisPartAsm.partAsmAttrs[i].attrNm, 
											self.chassisPartAsm.partAsmAttrs[i].attrDs,
											self.chassisPartAsm.partAsmAttrs[i].dataSourceNm,
											self.chassisPartAsm.partAsmAttrs[i].dataTypeNm,
											self.chassisPartAsm.partAsmAttrs[i].valueTx,
											self.chassisPartAsm.partAsmAttrs[i].editableFl
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
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].compTypeAttrId,
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].partAttrId,
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].partAsmId,
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].partAsmAttrId, 
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].attrNm,
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].attrDs,
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].dataSourceNm,
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].dataTypeNm,
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].valueTx,
													self.chassisPartAsm.partAsms[pIdx].partAsmAttrs[aIdx].editableFl
													)
										);
							
							}
						}
						var part = new Part(
								self.chassisPartAsm.partAsms[pIdx].partAsmId,
								self.chassisPartAsm.partAsms[pIdx].typeDs, //compTypeNm,
								userPartAttrsArray
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
	                    { name: 'partAttrId', display: 'partAttrId', type: 'hidden', ctrlAttr: { readonly: true } },
	                    { name: 'partAsmId', display: 'partAsmId', type: 'hidden', ctrlAttr: { readonly: true } }, 
	                    { name: 'partAsmAttrId', display: 'partAsmAttrId', type: 'hidden', ctrlAttr: { readonly: true } }, 
	                    { name: 'editableFl', display: 'editableFl', type: 'hidden', ctrlAttr: { readonly: true } }, 
	                    { name: 'attrNm', display: 'Name', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '160px'} },
	                    { name: 'attrDs', display: 'Node Attribute', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '300px'} },
	                    { name: 'dataSourceNm', display: 'Source', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '80px'} },
		                { name: 'dataTypeNm', display: 'Data Type', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '40px'} },
		                { 
		                	name: 'valueTx', 
		                	display: 'Value', 
		                	type: 'custom', 
		                	ctrlAttr: { readonly: false }, 
		                	displayCss: { 'min-width': '300px'},
		                	customBuilder: function (parent, idPrefix, name, uniqueIndex) {
		                        var ctrlId = idPrefix + '_' + name + '_' + uniqueIndex;
		                        var ctrl = $('<input/>', { type: 'text', id: ctrlId }).appendTo(parent);
		                        return ctrl;
		                    },
		                    customGetter: function (idPrefix, name, uniqueIndex) {
		                        var ctrlId = idPrefix + '_' + name + '_' + uniqueIndex;
		                        return $('#' + ctrlId).val();
		                    },
		                	customSetter: function (idPrefix, name, uniqueIndex, value) {
		                        var ctrlId = idPrefix + '_' + name + '_' + uniqueIndex;
		                        var editableCtrlId = idPrefix + '_editableFl_' + uniqueIndex;
		                        $('#' + ctrlId).val(value);
		                        if ( $('#' + editableCtrlId).val() == 'N' ) {
		                        	$('#' + ctrlId).css("background-color", "white");
		                        	$('#' + ctrlId).prop('disabled', true);
		                        }
		                    }
		                }
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
													self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].compTypeAttrId,
													self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].partAttrId,
													self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].partAsmId,
													self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].partAsmAttrId, 
													self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].attrNm, 
													self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].attrDs, 
													self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].dataSourceNm,
													self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].dataTypeNm,
													self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].valueTx,
													self.nodeAsmsArray[nIdx][pIdx].partAsmAttrs[aIdx].editableFl
													)
										);
							}
						}
						var part = new Part(
								self.nodeAsmsArray[nIdx][pIdx].partAsmId,
								self.nodeAsmsArray[nIdx][pIdx].typeDs, //compTypeNm,
								userPartAttrsArray
								);						
						nodeParts.push(part);
				}
	    		$('#node_part_grid_' + self.nodeCount.toString()).appendGrid({
	                columns: [
	                    { name: 'partAsmId', display: 'partId', type: 'hidden', ctrlAttr: { readonly: true } }, 
	                    { name: 'compTypeNm', display: 'Node Part', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '190px'} }
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
	    	                        { name: 'editableFl', display: 'editableFl', type: 'hidden', ctrlAttr: { readonly: true } }, 
	    			                { name: 'attrNm', display: 'Name', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '190px'} },
	    			                { name: 'attrDs', display: 'Attribute', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '300px'} },
	    			                { name: 'dataSourceNm', display: 'Source', type: 'text', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '80px'} },
					                { name: 'dataTypeNm', display: 'Data Type', type: 'hidden', ctrlAttr: { readonly: true }, displayCss: { 'min-width': '40px'} },
					                { 
					                	name: 'valueTx', 
					                	display: 'Value', 
					                	type: 'custom', 
					                	ctrlAttr: { readonly: false }, 
					                	displayCss: { 'min-width': '300px'},
					                	customBuilder: function (parent, idPrefix, name, uniqueIndex) {
					                        var ctrlId = idPrefix + '_' + name + '_' + uniqueIndex;
					                        var ctrl = $('<input/>', { type: 'text', id: ctrlId }).appendTo(parent);
					                        return ctrl;
					                    },
					                    customGetter: function (idPrefix, name, uniqueIndex) {
					                        var ctrlId = idPrefix + '_' + name + '_' + uniqueIndex;
					                        return $('#' + ctrlId).val();
					                    },
					                	customSetter: function (idPrefix, name, uniqueIndex, value) {
					                        var ctrlId = idPrefix + '_' + name + '_' + uniqueIndex;
					                        var editableCtrlId = idPrefix + '_editableFl_' + uniqueIndex;
					                        $('#' + ctrlId).val(value);
					                        if ( $('#' + editableCtrlId).val() == 'N' ) {
					                        	$('#' + ctrlId).css('background-color', 'ghostwhite');
					                        	$('#' + ctrlId).prop('disabled', true);
					                        }
					                    }
					                }
	    	                    ]
	    	                });
		            }
	            });
	    		$('#node_part_grid_' + self.nodeCount.toString()).appendGrid('load', nodeParts);
	    		
	    		$( '#node_' + self.nodeCount.toString() ).accordion('refresh');
			}
			return true;
		};
		
			
		AstAssemblyClass.prototype.loadAssembly = function() {
			var self = astAssemblyObj;
    		self.nodeCount = 0;
    		$("#loading-div-background").show();
    		
    		/*
    		//Raw AJAX request test
    		var request = new XMLHttpRequest();
    		request.open('POST', cp + '/systest/getAssemblyResultsData.json', true);
    		request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
    		request.onload = function() {
			  if (request.status >= 200 && request.status < 400) {
			    // Success!
			    var resp = JSON.parse(request.responseText);
			    $("#loading-div-background").hide();
			    self.loadCallback(resp);
			  } 
			  else {
				  // We reached our target server, but it returned an error
				  $("#loading-div-background").hide();
      			  self.displaySimpleDialog('ERROR', 'Unknown error fetching node assembly data. Status = ' + request.status);
			  }
			};
			request.onerror = function() {
				$("#loading-div-background").hide();
    			self.displaySimpleDialog('ERROR', 'Unknown error fetching node assembly data. Please contact support.');
			};
    		request.send('submitChassisSerialNum=' + $('#submitChassisSerialNum').val() + '&returnMissingAttrs=Y');
    		*/

    		$.ajax({ 
    			method: 'POST',
				url: cp + '/systest/getAssemblyResultsData.json', 
				data: { 
    				submitChassisSerialNum: $('#submitChassisSerialNum').val(),
    				returnMissingAttrs: 'Y'
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
		
		AstAssemblyClass.prototype.submitClick = function(e) {
    		var self = astAssemblyObj;
    		$("#loading-div-background").show();
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
    		var userAssemblyDataJSON = JSON.stringify(userAssemblyData);
    		$('#submitData').val(userAssemblyDataJSON);
    		$('#editChassisSerialNum').val($('#submitChassisSerialNum').val());
    		$('#submitForm').submit();
		};
		
		astAssemblyObj = new AstAssemblyClass();
		astAssemblyObj.initWidgets(); 
		if ( $('#submitChassisSerialNum').val() != '' ) {
			astAssemblyObj.loadAssembly();
		}
		
    }); //document ready
} ) ( jQuery );