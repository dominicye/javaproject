<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="cp" value="${pageContext.request.contextPath}" scope="request" />
	<div id="loading-div-background">
	    <div id="loading-div" class="ui-corner-all" >
	      <img style="height:30px;margin:15px;" src="${cp}/resources/images/spin.gif" alt="Loading.."/>
	      <h4 style="color:gray;font-weight:normal;">Please wait....</h4>
	     </div>
	</div>
    <div>
	<table width="793px" border=0 >
	<tr valign="top">
	<td>
	 <img src="${cp}/resources/images/avnet_logistics_logo.gif" >
	</td>
	<td align="right">	
    	<h2><spring:message code="default.page.title" /></h2>
    </td>
    <tr valign="top" style="background-color: #333333; ">
    <td colspan=2 >	
    
    <!-- start header  -->
	<header>	
		<div class="container">
				<div class="navbar navbar-static-top">
					<div class="navigation">
						<nav>
							<ul class="nav topnav bold">
								<li>
									<a href="${cp}">HOME</a>
								</li>
								<sec:authorize access="hasRole('UPTLU')">
								<li class="dropdown">
									<a href="${cp}/uprom/upromForm.htm">UPROM TOOL</a>
								</li>
								</sec:authorize>
								<sec:authorize access="hasRole('ACCRP')">
								<li class="dropdown">
									<a href="#">REPORTS <i class="icon-angle-down"></i></a>
									<ul class="dropdown-menu bold">
										<sec:authorize access="hasRole('ACCRP')">
					  					<li><a href="${cp}/report/gsfcFinancialReportForm.htm">GSFC Financial</a></li>
					  					</sec:authorize>
									</ul>
								</li>
								</sec:authorize>
								<sec:authorize access="hasRole('ASTUS') or hasRole('ASTAD')">
								<li class="dropdown">
									<a href="#">AVNET SYSTEM TEST <i class="icon-angle-down"></i></a>
									<ul style="display: none;" class="dropdown-menu bold">
										<li>
					  						<a href="${cp}/systest/assemblyAllTouchForm.htm">Assembly</a>
					  					</li> 
					  					<%-- <li class="dropdown">
											<a href="#">Assembly <i class="icon-angle-right"></i></a>
					  						<ul style="display: none;" class="dropdown-menu sub-menu-level1 bold">
					  							<li><a href="${cp}/systest/assemblyForm.htm">1st Touch</a></li>
							  					<li><a href="${cp}/systest/assembly2ndTouchForm.htm">2nd Touch</a></li>
							  					<li><a href="${cp}/systest/assemblyAllTouchForm.htm">All Touch</a></li>
					  						</ul>
					  					</li> --%>
					  					<li>
					  						<a href="${cp}/systest/assemblyResults.htm?submitChassisSerialNum=">Assembly Search</a>
					  					</li>
					  					<li>
					  						<a href="${cp}/systest/assemblyResultsEdit.htm?submitChassisSerialNum=">Assembly Edit</a>
					  					</li>					  					
					  					<li>
					  						<a href="${cp}/report/nutanixPackingSlipForm.htm">Nutanix Packing Slip</a>
					  					</li>
					  					<li>
					  						<a href="${cp}/systest/services/getAssemblyResultsData.xml?client=IST&chassisSerialNum=">Scan-in XML View</a>
					  					</li>
					  					<li>
					  						<a href="${cp}/systest/services/getLastTestResults.xml?chassisSerialNum=">Latest Test Results XML View</a>
					  					</li>
					  					<sec:authorize access="hasRole('ASTAD')">
					  					<li>
					  						<a href="${cp}/systest/submitTestResultsForm.htm">Test Results XML Submit</a>
					  					</li>					  					
					  					<li class="dropdown">
											<a href="#">CRUD <i class="icon-angle-right"></i></a>
					  						<ul style="display: none;" class="dropdown-menu sub-menu-level1 bold">
					  							<li><a href="${cp}/systest/componentTypeCRUD.htm">Component Types </a></li>
							  					<li><a href="${cp}/systest/componentTypeAttrCRUD.htm">Component Attributes </a></li>
							  					<li><a href="${cp}/systest/partCRUD.htm">Parts </a></li>
					  						</ul>
					  					</li>					  									  					
					  					<li>
					  						<a href="${cp}/report/astDailyTestImagingFSTResultsReportForm.htm">Imaging/FST Report</a>
					  					</li>
					  					</sec:authorize>
									</ul>
								</li>
								</sec:authorize>
							</ul>
						</nav>
					</div>
					<!-- end navigation -->
				</div>
			</div>
	</header>	
	<!-- end header -->
	
    <%-- 
    		//<div class="navigation">
	  		//<ul class="nav">  
	  		<ul id="jMenu">
	  			<li>
	  				<a href="${cp}">HOME</a>
	  			</li>
	  			<li>
	  				<a href="${cp}/uprom/upromForm.htm">UPROM TOOL</a>
	  			</li>
	  			<li>
	  				<a href="#">REPORTS</a>
	  				<ul>
	  					<sec:authorize access="hasRole('ACCRP')">
	  					<li><a href="${cp}/report/gsfcFinancialReportForm.htm">GSFC Financial</a></li>
	  					</sec:authorize>
	  				</ul>
	  			</li>
	  			<li>
	  				<a href="#">AVNET SYSTEM TEST</a>
	  				<ul>
	  					<li>
	  						<a href="#">Components</a>
	  						<ul>
	  							<li><a href="${cp}/systest/componentTypeCRUD.htm">Component Types</a></li>
			  					<li><a href="${cp}/systest/componentTypeAttrCRUD.htm">Component Attributes</a></li>
	  						</ul>
	  					</li>
	  					<li>
	  						<a href="#">Parts</a>
	  					</li>
	  					<li>
	  						<a href="#">Assembly</a>
	  					</li>
	  				</ul>
	  			</li>
	  		</ul>
  		//</div> 
  		--%>
      </td>
      </tr>
      <c:if test="${enablePageTitle == 'true'}">
      <tr><td colspan=2>
      	<table cellpadding="3" border="0" width="99%" align="center" style="border-collapse:collapse;background-color: whitesmoke;" >
		<tr><td><h3>${pageTitle}</h3></td></tr>
	</table>
      </td></tr>
      </c:if>
      <tr valign="top">
  	<td colspan=2>
        