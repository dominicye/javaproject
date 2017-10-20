<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="jsdate" value="<%= com.avnet.alapps.common.AlAppsConstants.JS_TIMESTAMP %>" scope="request" />
<c:set var="cp" value="${pageContext.request.contextPath}" scope="request" />
<c:set var="pageTitle" value="Component Type CRUD" scope="request" />
<c:set var="enablePageTitle" value="true" scope="request" />
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../include/header.jsp" flush="true" />
		<link href="${cp}/resources/jtable/themes/jqueryui/jtable_jqueryui.css" rel="stylesheet" type="text/css" />
		<script src="${cp}/resources/jtable/jquery.jtable.js" type="text/javascript"></script>
		<script type="text/javascript" src="${cp}/resources/js/systest/component_type_crud.js?v=${jsdate}"></script>
		
		<style>
			.ui-widget {
    			font-family: Segoe UI,Arial,sans-serif;
    			font-size: 1em;
			}
			
			div.jtable-main-container div.jtable-title div.jtable-title-text {			
				background: #c71114 url("${cp}/resources/jquery/images/ui-bg_inset-soft_30_c71114_1x100.png") 50% 50% repeat-x;
    			color: #ffffff;
			  	font-size: 16px;
			  	font-weight: bold;
			}
			
			div.ui-widget-header {
				background: #c71114 url("${cp}/resources/jquery/images/ui-bg_inset-soft_30_c71114_1x100.png") 50% 50% repeat-x;			
    			color: #ffffff;
			  	font-size: 16px;
			  	font-weight: bold;
			}
		</style>
	</head>
	<body>
		<jsp:include page="../include/menubar.jsp" flush="true" />
		<div id="crud_grid"></div>
		<jsp:include page="../include/footer.jsp" flush="true" />
	</body>
</html>