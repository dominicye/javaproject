<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="jsdate" value="<%= com.avnet.alapps.common.AlAppsConstants.JS_TIMESTAMP %>" scope="request" />
<c:set var="cp" value="${pageContext.request.contextPath}" scope="request" />
<c:set var="pageTitle" value="GSFC Financial Reports" scope="request" />
<c:set var="enablePageTitle" value="true" scope="request" />


<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../include/header.jsp" flush="true" />
	</head>
	<body>
		<jsp:include page="../include/menubar.jsp" flush="true" />
		
		
		<table cellpadding="3" border="0" style="border-collapse: collapse;">
			<tr>
				<td>
					<form:form method="POST" action="gsfcFinancialReportResults.htm" commandName="reportForm">
						<form:select path="fiscalMonth" items="${fiscalMonthList}" />
						<input type="submit"" value="submit">
					</form:form>
				</td>
			</tr>
		</table>			
					
		<jsp:include page="../include/footer.jsp" flush="true" />
	</body>
</html>