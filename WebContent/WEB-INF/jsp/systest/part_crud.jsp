<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="jsdate" value="<%= com.avnet.alapps.common.AlAppsConstants.JS_TIMESTAMP %>" scope="request" />
<c:set var="cp" value="${pageContext.request.contextPath}" scope="request" />
<c:set var="pageTitle" value="Part CRUD" scope="request" />
<c:set var="enablePageTitle" value="true" scope="request" />
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../include/header.jsp" flush="true" />
		<link href="${cp}/resources/jtable/themes/jqueryui/jtable_jqueryui.css" rel="stylesheet" type="text/css" />
		 
		<script src="${cp}/resources/jtable/jquery.jtable.js" type="text/javascript"></script>
		<script type="text/javascript" src="${cp}/resources/js/systest/part_crud.js?v=${jsdate}"></script>	
		
		 
		<style>
			.ui-widget {
    			font-family: Segoe UI,Arial,sans-serif;
    			font-size: 1em;
			}
			
			div.jtable-main-container div.jtable-title div.jtable-title-text {			
				background: #c71114 url("${cp}/resources/jquery/images/ui-bg_inset-soft_30_c71114_1x100.png") 50% 50% repeat-x;
    			color: #ffffff;
			  	font-size: 1em;
			  	font-weight: bold;
			}
			
			div.ui-widget-header {
				background: #c71114 url("${cp}/resources/jquery/images/ui-bg_inset-soft_30_c71114_1x100.png") 50% 50% repeat-x;			
    			color: #ffffff;
			  	font-size: 1em;
			  	font-weight: bold;
			}
		</style>
		
	
	</head>
	<body>
		<jsp:include page="../include/menubar.jsp" flush="true" />
		<br>
		<table id="formInputTable" cellpadding="3" border="0" style="border-collapse:collapse;">
		<tr valign="middle" >
			<th align="left">Component Type:</th>
			<td>
				<select id="componentTypeSelect" name="componentTypeSelect" style="width: 225px">
					<option value="">Select a type...</option> 
					<c:forEach var="ct" items="${componentTypeList}">
						<option value="${ct.compTypeId}">${ct.typeDs}</option> 
					</c:forEach>
				</select>
			</td>
		</tr>
		</table>
		<br>
		<div id="crud_grid"></div>
		<div id="alertDialog" title=""><div id="alertDialogBody"></div></div>
		<jsp:include page="../include/footer.jsp" flush="true" />
	</body>
</html>