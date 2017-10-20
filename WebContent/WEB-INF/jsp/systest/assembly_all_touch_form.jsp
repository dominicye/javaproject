<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="jsdate" value="<%= com.avnet.alapps.common.AlAppsConstants.JS_TIMESTAMP %>" scope="request" />
<c:set var="cp" value="${pageContext.request.contextPath}" scope="request" />
<c:set var="pageTitle" value="Assembly" scope="request" />
<c:set var="enablePageTitle" value="true" scope="request" />
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../include/header.jsp" flush="true" />
		<link href="${cp}/resources/uploadfile/uploadfile.min.css" rel="stylesheet">
		<script src="${cp}/resources/uploadfile/jquery.form.js"></script>
		<script src="${cp}/resources/uploadfile/jquery.uploadfile.js"></script>
		<link href="${cp}/resources/appendgrid/jquery.appendGrid-1.6.1.css" rel="stylesheet" type="text/css">
		<script src="${cp}/resources/appendgrid/jquery.appendGrid-1.6.1.js" type="text/javascript"></script>
		<script src="${cp}/resources/js/systest/assembly_all_touch_form.js?v=${jsdate}" type="text/javascript"></script>
		
		<style>
	
			.searchBox {
			    width: 200px;
			    border: 1px;
			    padding: 2px;
			    border-style: solid;
			    background-color: white; 
			    border-color: gray;
			}
			input {
				border: 0px;
			    padding: 2px;
			    border-style: solid;
				background-color: powderblue;
				border-color: powderblue;
				color: black;
			}
			input[readonly] {
				border: 0px;
			    padding: 2px;
			    border-style: solid;
				background-color: ghostwhite;
				border-color: ghostwhite;
				color: black;
			}
			.ajax-file-upload {
				font-family: Segoe UI, Arial, sans-serif;
				font-size: 1.1em;
				font-weight: bold;
				padding: 0px 0px;
				cursor: pointer;
				line-height: 20px;
				height: 25px;
				margin: 0 10px 10px 0;
				display: inline-block;
				background: #fff;
				border: 1px solid #e8e8e8;
				color: #888;
				text-decoration: none;
				border-radius: 6px;
				-webkit-border-radius: 6px;
				-moz-border-radius: 6px;
				padding: 3px 10px 2px 10px;
				color: #eeeeee;
				background: #555555 url("${cp}/resources/jquery/images/ui-bg_glass_20_555555_1x400.png") 50% 50% repeat-x;
				border: 0;
				vertical-align: middle
			}
			.ajax-upload-dragdrop {
				border: 2px dotted #a5a5c7;
				color: #dadce3;
				text-align: left;
				vertical-align: middle;
				padding: 10px 2px 0 5px
			}
						
		</style>	
	</head>
	<body>
		<jsp:include page="../include/menubar.jsp" flush="true" />
		<br>
		<strong>Chassis S/N: </strong> <input type="text" id="chassisSerial" name="chassisSerial" class="searchBox" value="" >&nbsp;&nbsp; 
		<strong>Production Order: </strong> <input type="text" id="icn" name="icn" class="searchBox" value="" > 
		<button id="loadBtn">Load</button><button id="submitBtn">Submit Assembly</button>
		<br><br>
		<table><tr><td><div id="nodeFileUpload">Select Node .json File</div></td><td><button id="addNodeBtn">Submit Node</button></td></tr></table>
		<br><br>
		<div id="chassis">
			<h3>Chassis</h3>
			<%-- <div id="chassis_data"><div id="chassis_nodes"></div></div> --%>
			<div id="chassis_data"><table id="chassis_grid"></table><br><table id="chassis_part_grid"></table></div>
		</div>
		<div id="node_1_cont">
			<div id="node_1" style="display: none;"><h3>Node A</h3><div id="node_data_1"><table id="node_grid_1"></table><br><table id="node_part_grid_1"></table></div></div>
		</div>
		<div id="node_2_cont">
			<div id="node_2" style="display: none;"><h3>Node B</h3><div id="node_data_2"><table id="node_grid_2"></table><br><table id="node_part_grid_2"></table></div></div>
		</div>
		<div id="node_3_cont">
			<div id="node_3" style="display: none;"><h3>Node C</h3><div id="node_data_3"><table id="node_grid_3"></table><br><table id="node_part_grid_2"></table></div></div>
		</div>
		<div id="node_4_cont">
			<div id="node_4" style="display: none;"><h3>Node D</h3><div id="node_data_4"><table id="node_grid_4"></table><br><table id="node_part_grid_2"></table></div></div>
		</div>
		<br>
		<button id="deleteNodeBtn">Remove Node</button>
		<div id="deleteNodeDialog" title="Remove Node?" style="display: none;">
  			<p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>Removing a node cannot be undone. Are you sure?</p>
		</div>
		<br><br>
		<div id="parts">
			<h3>Unassigned Parts</h3>
			<div id="parts_data">
				Parts are assigned by uploading node files.
				<br><br>
				<table id="parts_grid"></table>
			</div>
		</div>
		<br><br>
		<div id="misc">
			<h3>Undefined Parts</h3>
			<div id="misc_data">
				If a part on this list should be tested, DO NOT confirm the part or submit this form. Please contact your Test System Administrator.
				<br><br>
				<table id="misc_grid"></table>
			</div>
		</div>
		<br><br>
		
		
		<form id="submitForm" name="submitForm" action="${cp}/systest/submitAssembly.htm" method="post">
			<input type="hidden" id="submitChassisSerialNum" name="submitChassisSerialNum" value="">
			<input type="hidden" id="submitICN" name="submitICN" value="">
			<input type="hidden" id="submitChassisPartNum" name="submitChassisPartNum" value="">
			<input type="hidden" id="submitChassisPartId" name="submitChassisPartId" value="">
			<input type="hidden" id="submitTouchLevel" name="submitTouchLevel" value="">
			<input type="hidden" id="submitFirstTouchAssemblyId" name="submitFirstTouchAssemblyId" value="">
			<input type="hidden" name="submitData" id="submitData" value="">
		</form>
		<button id="testBtn1">GET RAW DATA</button>
		<div id="testText1"></div>
		
		<div id="alertDialog" title=""><div id="alertDialogBody"></div></div>
		<jsp:include page="../include/footer.jsp" flush="true" />
	</body>
</html>