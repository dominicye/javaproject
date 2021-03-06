
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="cp" value="${pageContext.request.contextPath}" scope="request" />
<c:set var="pageTitle" value="Welcome to the new home for miscellaneous tools of Avnet Logistics!" scope="request" />
<c:set var="enablePageTitle" value="true" scope="request" />
<!DOCTYPE html>
<html>
	<head>
	<jsp:include page="include/header.jsp" flush="true" />
	</head>
	<body>
		<jsp:include page="include/menubar.jsp" flush="true" />
		<br>
		<div class="ui-widget">
			<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">
				<p>
				<span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
				You are logged in as <strong><c:out value="${securityUser.fullName}" /></strong> (<c:out value="${securityUser.avnetGlobalUserId}" />) on <c:out value="${hostname}" />
				</p>
			</div>
		</div>
		<jsp:include page="include/footer.jsp" flush="true" />
	</body>
</html>
