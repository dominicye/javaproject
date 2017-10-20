<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="cp" value="${pageContext.request.contextPath}" scope="request" />
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
       <meta http-equiv="X-UA-Compatible" content="IE=edge" />
       <title><spring:message code="default.page.title" /></title>
       <script type="text/javascript">var cp = "${cp}";</script>
       <link rel="stylesheet" type="text/css" href="${cp}/resources/css/site.css" />
       <link rel="stylesheet" type="text/css" href="${cp}/resources/menubar/css/menu.css" />
       <%-- 
       <link rel="stylesheet" type="text/css" href="${cp}/resources/css/menubar.css" /> 
       <link rel="stylesheet" type="text/css" href="${cp}/resources/jmenu/css/jmenu.css" />
       --%>
       <link rel="stylesheet" type="text/css" href="${cp}/resources/jquery/jquery-ui.css" >
       <script type="text/javascript" src="${cp}/resources/js/js.js"></script>
       <script type="text/javascript" src="${cp}/resources/jquery/external/jquery/jquery.js"></script>
       <script type="text/javascript" src="${cp}/resources/jquery/jquery-ui.js"></script>
       <%-- 
       <script type="text/javascript" src="${cp}/resources/jquery/external/touch-punch/jquery.ui.touch-punch.min.js"></script>
       <script type="text/javascript" src="${cp}/resources/js/menubar.js"></script> 
       <script type="text/javascript" src="${cp}/resources/jmenu/js/jMenu.jquery.js"></script>
       --%>
       	<script type="text/javascript">
			( function($) {
			    $(document).ready(function() {
			    	$.ajaxSetup({
					  timeout: 0,
					  cache: false,
					  crossDomain: false
					});
			    });
			  } ) ( jQuery );
		</script>
       
       <style>
		body{
			font: 78% "Trebuchet MS", sans-serif;
			margin: 20px;
		}
		.demoHeaders {
			margin-top: 2em;
		}
		#dialog-link {
			padding: .4em 1em .4em 20px;
			text-decoration: none;
			position: relative;
		}
		#dialog-link span.ui-icon {
			margin: 0 5px 0 0;
			position: absolute;
			left: .2em;
			top: 50%;
			margin-top: -8px;
		}
		#icons {
			margin: 0;
			padding: 0;
		}
		#icons li {
			margin: 2px;
			position: relative;
			padding: 4px 0;
			cursor: pointer;
			float: left;
			list-style: none;
		}
		#icons span.ui-icon {
			float: left;
			margin: 0 4px;
		}
		.fakewindowcontain .ui-widget-overlay {
			position: absolute;
		}
		<%--
		select {
			width: 200px;
		}
		--%>
		#loading-div-background 
	    {
	        display:none;
	        position:fixed;
	        top:0;
	        left:0;
	        background:black;
	        width:100%;
	        height:100%;
	        z-index:200;
	     }
	     #loading-div
	    {
	         width: 100px;
	         height: 100px;
	         background-color: #0c0b0b;
	         text-align:center;
	         position:absolute;
	         left: 50%;
	         top: 50%;
	         margin-left:-150px;
	         margin-top: -100px;
	         z-index:300;
	     }
	</style>