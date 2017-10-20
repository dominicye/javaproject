<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="jsdate" value="<%= com.avnet.alapps.common.AlAppsConstants.JS_TIMESTAMP %>" scope="request" />
<c:set var="cp" value="${pageContext.request.contextPath}" scope="request" />
<c:set var="pageTitle" value="Test Result XML" scope="request" />
<c:set var="enablePageTitle" value="true" scope="request" />
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../include/header.jsp" flush="true" />
		<link href="${cp}/resources/appendgrid/jquery.appendGrid-1.6.1.css" rel="stylesheet" type="text/css">
		<script src="${cp}/resources/appendgrid/jquery.appendGrid-1.6.1.js" type="text/javascript"></script>
		<script src="${cp}/resources/js/systest/submit_test_results.js?v=${jsdate}" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="../include/menubar.jsp" flush="true" />
		<br>
		<strong>Copy/Paste Test Result XML: </strong> <button id="sendBtn">Send</button>
		<br><br> 
		<textarea cols="100" rows="20" id="xmlData"></textarea>
		<br><br>
		<strong>XML Response: </strong>
		<br><br>
		<textarea cols="100" rows="5" id="xmlResponse"></textarea>
		<br><br>
		<div id="alertDialog" title=""><div id="alertDialogBody"></div></div>
		<jsp:include page="../include/footer.jsp" flush="true" />
	</body>
</html>