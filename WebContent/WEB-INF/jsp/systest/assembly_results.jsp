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
		<link href="${cp}/resources/appendgrid/jquery.appendGrid-1.6.1.css" rel="stylesheet" type="text/css">
		<script src="${cp}/resources/appendgrid/jquery.appendGrid-1.6.1.js" type="text/javascript"></script>
		<script src="${cp}/resources/js/systest/assembly_results.js?v=${jsdate}" type="text/javascript"></script>
		
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
						
		</style>
		
	</head>
	<body>
		<jsp:include page="../include/menubar.jsp" flush="true" />
		<br>
		<strong>Chassis S/N: </strong> <input type="text" id="submitChassisSerialNum" name="submitChassisSerialNum" class="searchBox" value="${submitChassisSerialNum}"> <button id="searchBtn">Search</button>
		<br><br>
		<div id="chassis">
			<h3>Chassis</h3>
			<div id="chassis_data"><table id="chassis_grid"></table><br><table id="chassis_part_grid"></table></div>
		</div>
		<div id="node_1_cont">
			<div id="node_1" style="display: none;"><h3>Node</h3><div id="node_data_1"><table id="node_grid_1"></table><br><table id="node_part_grid_1"></table></div></div>
		</div>
		<div id="node_2_cont">
			<div id="node_2" style="display: none;"><h3>Node</h3><div id="node_data_2"><table id="node_grid_2"></table><br><table id="node_part_grid_2"></table></div></div>
		</div>
		<div id="node_3_cont">
			<div id="node_3" style="display: none;"><h3>Node</h3><div id="node_data_3"><table id="node_grid_3"></table><br><table id="node_part_grid_2"></table></div></div>
		</div>
		<div id="node_4_cont">
			<div id="node_4" style="display: none;"><h3>Node</h3><div id="node_data_4"><table id="node_grid_4"></table><br><table id="node_part_grid_2"></table></div></div>
		</div>
		<div id="misc">
			<h3>Excluded Parts</h3>
			<div id="misc_data"><table id="misc_grid"></table></div>
		</div>
		<br><br>
		<input type="hidden" id="touchLevel" name="touchLevel"  value="${touchLevel}">
		<div id="submitError" style="display: none;">${submitError}</div>
		<div id="alertDialog" title=""><div id="alertDialogBody"></div></div>
		<jsp:include page="../include/footer.jsp" flush="true" />
	</body>
</html>