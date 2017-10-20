<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="jsdate" value="<%= com.avnet.alapps.common.AlAppsConstants.JS_TIMESTAMP %>" scope="request" />
<c:set var="cp" value="${pageContext.request.contextPath}" scope="request" />
<c:set var="pageTitle" value="Imaging/FST Test Results Report" scope="request" />
<c:set var="enablePageTitle" value="true" scope="request" />

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../include/header.jsp" flush="true" />
		<script src="${cp}/resources/js/systest/daily_tests_report_form.js?v=${jsdate}" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="../include/menubar.jsp" flush="true" />
		
		<form:form method="GET" action="${cp}/report/astDailyImagingFSTTestReportResults.htm" commandName="reportForm">
		<table cellpadding="3" border="0" style="border-collapse: collapse;">
			<tr>
				<td align="right">
					<strong>Date: </strong>	
				</td>
				<td>
					<input type="text" name="testDate" id="testDate"/>
					<%-- <div id="datepicker"></div> --%>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><button id="submitBtn">Excel</button></td>
			</tr>
		</table>
		<%-- <input type="hidden" id="testDate" name="testDate" value=""> --%>	
		<input type="hidden" id="errorMessage" name="errorMessage" value="${errorMessage}">	
		</form:form>
		<div id="alertDialog" title=""><div id="alertDialogBody"></div></div>			
		<jsp:include page="../include/footer.jsp" flush="true" />
	</body>
</html>