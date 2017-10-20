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
    		//if ( $('#componentTypeSelect').val() == '' ) {
    		//	displaySimpleDialog("ERROR", "Please select a component type.");
    		//}
    		if ( $('#componentTypeSelect').val() != '' ) {
                e.preventDefault();
                $('#crud_grid').jtable('load', {
                	compTypeId: $('#componentTypeSelect').val()
                });
    		}
    	});
    	
    	/*
    	$("#loadBtn").button();
    	$('#loadBtn').click(function (e) {
    		if ( $('#componentTypeSelect').val() == '' ) {
    			displaySimpleDialog("ERROR", "Please select a component type.");
    		}
    		else {
                e.preventDefault();
                $('#crud_grid').jtable('load', {
                	compTypeId: $('#componentTypeSelect').val()
                });
    		}
    	});   
    	*/

    	$('#crud_grid').jtable({
            title: 'Component Type Attributes',
            jqueryuiTheme: true,
            selecting: true, //Enable selecting 
            paging: true, //Enable paging
            sorting: true, //Enable sorting
            defaultSorting: 'compTypeAttrId ASC',
            actions: {
    			listAction: cp + '/systest/componentTypeAttrList.json',
                createAction: cp + '/systest/componentTypeAttrCreate.json',
                updateAction: cp + '/systest/componentTypeAttrUpdate.json',
                deleteAction: cp + '/systest/componentTypeAttrDelete.json'
            },
            fields: {
            	compTypeAttrId: {
            		title: 'ID',
	            	key: true,
	                create: false,
	                edit: false,
	                list: false
                },
                compTypeId: {
                    title: 'Type',
                    width: '10%',
                    edit: false,
                    options: function(data) {
                		return cp + '/systest/componentTypeOptions.json';
                    }
                },
                attrNm: {
                    title: 'Name',
                    width: '10%',
                    edit: false
                },
                attrDs: {
                    title: 'Description',
                    width: '10%'
                },
                dataTypeId: {
                    title: 'Data Type',
                    width: '10%',
                    options: function(data) {
                        return cp + '/systest/dataTypeOptions.json';
                    }
                },
                dataSourceId: {
                    title: 'Source',
                    width: '10%',
                    options: function(data) {
                        return cp + '/systest/dataSourceOptions.json';
                    }
                },
                xmlTag: {
                    title: 'XML Tag',
                    width: '10%'
                },
                keyFl: {
                    title: 'Part Key',
                    width: '8%',
                    type: 'checkbox',
                    values: { 'N': 'No', 'Y': 'Yes' },
                    defaultValue: 'N'
                },
                editableFl: {
                    title: 'Editable',
                    width: '7%',
                    type: 'checkbox',
                    values: { 'N': 'No', 'Y': 'Yes' },
                    defaultValue: 'N'
                },
                activeFl: {
                    title: 'Status',
                    width: '7%',
                    type: 'checkbox',
                    values: { 'N': 'Inactive', 'Y': 'Active' },
                    defaultValue: 'N'
                }
            },
            //Register to selectionChanged event to hanlde events                                     
            recordAdded: function(event, data){
                //after record insertion, reload the records
            	$('#crud_grid').jtable('load', {
                	compTypeId: $('#componentTypeSelect').val()
                });
            },
            recordUpdated: function(event, data){
                //after record updation, reload the records
            	$('#crud_grid').jtable('load', {
                	compTypeId: $('#componentTypeSelect').val()
                });
            }
        });

        
    }); //document ready
} ) ( jQuery );