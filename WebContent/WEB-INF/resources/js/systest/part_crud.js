( function($) {
    $(document).ready(function() {
    	
    	$("#alertDialog").dialog({
            autoOpen: false, 
            modal: true,
            buttons: {
               OK: function() {$(this).dialog("close");}
            }
    	});
    	
    	var displaySimpleDialog = function(title, msg) {
			$("#alertDialog").dialog("option", "title", title);
			$("#alertDialogBody").html(msg);
			$("#alertDialog").dialog("open");
		};
    	
    	$("#componentTypeSelect").selectmenu();
    	$("#componentTypeSelect").on( "selectmenuselect", function( e, ui ) {
    		if ( $('#componentTypeSelect').val() != '' ) {
                e.preventDefault();
                $('#crud_grid').jtable('load', {
                	compTypeId: $('#componentTypeSelect').val()
                });
    		}
    	});

    	$('#crud_grid').jtable({
            title: 'Parts',
            jqueryuiTheme: true,
            selecting: true, //Enable selecting 
            selectOnRowClick: false,
            selectingCheckboxes: true,
            multiselect: true,
            paging: true, //Enable paging
            sorting: true, //Enable sorting
            defaultSorting: 'partId ASC',
            actions: {
    			listAction: cp + '/systest/partList.json',
                createAction: cp + '/systest/partCreate.json',
                updateAction: cp + '/systest/partUpdate.json',
                deleteAction: cp + '/systest/partDelete.json'
            },
            fields: {
            	PartAttrs: {
	                title: '',
	                jqueryuiTheme: false,
	                width: '1%',
	                sorting: false,
	                edit: false,
	                create: false,
	                display: function (partData) {
	                    //Create an image that will be used to open child table
	            		//var $img = $('<div class="ui-state-default ui-corner-all" title=".ui-icon-note" style="width: 18px;" ><span class="ui-icon ui-icon-note" title="Edit part attributes"></span></div>');
            			var $img = $('<img src="' + cp + '/resources/jtable/themes/jqueryui/list2.png" title="Edit part attributes" />');
            			//Open child table when user clicks the image
	                    $img.click(function () {
	                        $('#crud_grid').jtable('openChildTable',
	                                $img.closest('tr'), //Parent row
	                                {
	                                title: partData.record.keyValue + ' Part Attributes',
	                                actions: {
	                                    listAction: cp + '/systest/partAttrBySourceList.json?dataSourceName=CONFIG&partId=' + partData.record.partId,
	                                    updateAction: cp + '/systest/partAttrUpdate.json'
	                                },
	                                fields: {
	                                	partId: {
	                                    	create: false,
	                                        edit: false,
	                                        type: 'hidden',
	                                        defaultValue: partData.record.partId
	                                    },
	                                    partAttrId: {
	                                        key: true,
	                                        create: false,
	                                        edit: false,
	                                        list: false
	                                    },
	                                    compTypeAttrId: {
	                                        title: 'Attribute',
	                                        width: '40%',
	                                        create: false,
	                                        edit: false,
	                                        options: function(data) {
	                                    		data.clearCache(); //cause reload of dropdown option lists
	                                    		return cp + '/systest/componentTypeAttrOptionsBySource.json?dataSourceName=CONFIG&compTypeId=' + $('#componentTypeSelect').val();
	                                        }	                      
	                                    },
	                                    valueTx: {
	                                        title: 'Value',
	                                        width: '30%'
	                                    },
	                                    keyFl: {
	                                        title: 'Part Key',
	                                        width: '10%',
	                                        edit: false,
	                                        type: 'checkbox',
	                                        values: { 'N': 'No', 'Y': 'Yes' },
	                                        defaultValue: 'N'
	                                    },
	                                    activeFl: {
	                                        title: 'Status',
	                                        width: '10%',
	                                        edit: false,
	                                        type: 'checkbox',
	                                        values: { 'N': 'Inactive', 'Y': 'Active' },
	                                        defaultValue: 'N'
	                                    }  	
	                                },
	                                recordUpdated: function(event, data){
	                                	if ( data.record.keyFl == 'Y' ) {
		                                    //after record updation, reload the records
		                                	$('#crud_grid').jtable('load', {
		                                    	compTypeId: $('#componentTypeSelect').val()
		                                    });
	                                	}
	                                }
	                            }, function (data) { //opened handler
	                                data.childTable.jtable('load');
	                            });
	                    });
	                    //Return image to show on the person row
	                    return $img;
	                }
	            },
            	partId: {
            		title: 'ID',
	            	key: true,
	                create: false,
	                edit: false,
	                list: false
                },
                compTypeId: {
                    title: 'Component Type',
                    width: '10%',
                    edit: false,
                    options: function(data) {
                        return cp + '/systest/componentTypeOptions.json';
                    }
                },
                keyValue: {
                    title: 'Part Key',
                    width: '10%',
                    edit: false
                },
                activeFl: {
                    title: 'Status',
                    width: '10%',
                    type: 'checkbox',
                    edit: true,
                    values: { 'N': 'Inactive', 'Y': 'Active' },
                    defaultValue: 'N'
                }
                /*
                ,addMissingAttributes: {
                    title: '',
                    create: false,
                    edit: false,
                    sorting: false,
                    width: '0.5%',
                    display: function (data) {
                		alert(data);
                        return '<button class="jtable-command-button jtable-add-command-button" title="Add Missing Attributes"><span>Add Missing Attributes</span></button>';
                    }
                }
                */
            },
            //Register to selectionChanged event to hanlde events                                     
            recordAdded: function(event, data){
                //after record insertion, reload the records
            	$('#crud_grid').jtable('load', {
                	compTypeId: $('#componentTypeSelect').val()
                });
            },
            recordUpdated: function(event, data){
                //after record update, reload the records
            	$('#crud_grid').jtable('load', {
                	compTypeId: $('#componentTypeSelect').val()
                });
            },
            toolbar: {
                items: [{
                    icon: cp + '/resources/jtable/themes/jqueryui/add.png',
                    text: 'Add Missing Attributes To Selected Parts',
                    click: function () {
                		//alert('Add Missing Attributes To All');
                		var selectedPartsArray = new Array();
                		var $selectedRows = $('#crud_grid').jtable('selectedRows');
                		$selectedRows.each(
                			function () {
	                		    var record = $(this).data('record');
	                		    selectedPartsArray.push(record.partId);
                			}
                		);
                		if ( selectedPartsArray.length == 0 ) {
                			displaySimpleDialog('WARNING', 'No parts selected.');
                		}
                		else  {
                			//alert(selectedPartsArray);
                			$.ajax({ 
                    			method: 'POST',
                    			traditional: true,
                				url: cp + '/systest/addMissingPartAttrs.json', 
                				data: { 
                    				partIds: selectedPartsArray
                    			},
                				dataType: 'json'
                    		})
                    		.done(addMissingAttrCallback)
                    		.fail(function() {
                    			displaySimpleDialog('ERROR', 'Unknown error adding missing part attributes. Please contact support.');
                    		});
                		}
                    }
                }]
            }
        });
    	
    	var addMissingAttrCallback = function(data) { 
			if ( data.status == 'FAIL' ) {
				displaySimpleDialog('ERROR', data.result.message);
				return false;
			}
			else {
				displaySimpleDialog('INFO', data.result);
				$('#crud_grid').jtable('load', {
                	compTypeId: $('#componentTypeSelect').val()
                });
			}
    	};

        
    }); //document ready
} ) ( jQuery );