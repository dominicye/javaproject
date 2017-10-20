( function($) {
    $(document).ready(function() {
    	$('#crud_grid').jtable({
            title: 'Component Types',
            jqueryuiTheme: true,
            selecting: true, //Enable selecting 
            paging: true, //Enable paging
            sorting: true, //Enable sorting
            defaultSorting: 'compTypeId ASC',
            actions: {
    			listAction: cp + '/systest/componentTypeList.json',
                createAction: cp + '/systest/componentTypeCreate.json',
                updateAction: cp + '/systest/componentTypeUpdate.json',
                deleteAction: cp + '/systest/componentTypeDelete.json'
            },
            fields: {
            	compTypeId: {
            		title: 'ID',
	            	key: true,
	                create: false,
	                edit: false,
	                list: false
                },
                typeNm: {
                    title: 'Name',
                    width: '10%',
                    edit: false
                },
                typeDs: {
                    title: 'Description',
                    width: '10%'
                },
                parentCompTypeId: {
                    title: 'Parent Type',
                    width: '10%',
                    options: function(data) {
                		data.clearCache(); //cause reload of dropdown option lists
                        return cp + '/systest/componentTypeOptions.json';
                    }
                },
                xmlTag: {
                    title: 'XML Tag',
                    width: '10%'
                },
                activeFl: {
                    title: 'Status',
                    width: '10%',
                    type: 'checkbox',
                    values: { 'N': 'Inactive', 'Y': 'Active' },
                    defaultValue: 'N'
                }
            },
            //Register to selectionChanged event to hanlde events                                     
            recordAdded: function(event, data){
                //after record insertion
            },
            recordUpdated: function(event, data){
                //after record update
            }
        });
    	//Load student list from server
        $('#crud_grid').jtable('load');
    }); //document ready
} ) ( jQuery );