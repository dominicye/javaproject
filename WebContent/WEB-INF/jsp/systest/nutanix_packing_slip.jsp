<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="jsdate" value="<%= com.avnet.alapps.common.AlAppsConstants.JS_TIMESTAMP %>" scope="request" />
<c:set var="cp" value="${pageContext.request.contextPath}" scope="request" />
<c:set var="pageTitle" value="Nutanix Packing Slip" scope="request" />
<c:set var="enablePageTitle" value="true" scope="request" />

<c:if test="${ type == null }">
	<c:set var="type" scope="page" value="PACK"/>
</c:if>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../include/header.jsp" flush="true" />
		<script src="${cp}/resources/js/systest/nutanix_packing_slip.js?v=${jsdate}" type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="../include/menubar.jsp" flush="true" />
		
		<form:form method="POST" action="${cp}/report/nutanixPackingSlipResults.htm" commandName="reportForm">
		<table cellpadding="3" border="0" style="border-collapse: collapse;">
			<tr>
				<td align="right">
					<strong>Sales Order: </strong>	
				</td>
				<td>
					<input type="text" id="scn" name="scn" value="${scn}">
				</td>
			</tr>
			<tr>
				<td align="right">
					<strong>Type: </strong>	
				</td>
				<td>
					<c:choose>
						<c:when test="${type == 'PACK'}">
							<input type="radio"  name="type" value="PACK" checked>Nutanix
							<input type="radio"  name="type" value="CUST">Nutanix Customer
							<input type="radio"  name="type" value="PPS">Pick/Pack/Ship
						</c:when>
						<c:when test="${type == 'PPS'}">
							<input type="radio"  name="type" value="PACK">Nutanix
							<input type="radio"  name="type" value="CUST">Nutanix Customer
							<input type="radio"  name="type" value="PPS" checked>Pick/Pack/Ship
						</c:when>
						<c:otherwise>
							<input type="radio"  name="type" value="PACK">Nutanix
							<input type="radio"  name="type" value="CUST" checked>Nutanix Customer
							<input type="radio"  name="type" value="PPS">Pick/Pack/Ship
						</c:otherwise>
					</c:choose>
					<input type="hidden" id="ship99Flag" name="ship99Flag" value="off">
				</td>
			</tr>
			<%-- 
			<tr>
				<td align="right">
					<strong>Ship-to Contact Name: </strong>	
				</td>
				<td>
					<input type="text" id="shipToContactName" name="shipToContactName">
				</td>
			</tr>
			--%>
			<tr>
				<td></td>
				<td><button id="submitBtn">Download</button></td>
			</tr>
		</table>	
		<input type="hidden" id="errorMessage" name="errorMessage" value="${errorMessage}">	
		</form:form>
		<div id="alertDialog" title=""><div id="alertDialogBody"></div></div>			
		<jsp:include page="../include/footer.jsp" flush="true" />
	</body>
</html>