<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="jsdate" value="<%= com.avnet.alapps.common.AlAppsConstants.JS_TIMESTAMP %>" scope="request" />
<c:set var="cp" value="${pageContext.request.contextPath}" scope="request" />
<%-- @ taglib prefix="security" uri="http://www.springframework.org/security/tags" --%>
<c:set var="pageTitle" value="Unified Prom File Publishing Tool" scope="request" />
<c:set var="enablePageTitle" value="true" scope="request" />


<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../include/header.jsp" flush="true" />
		<link href="${cp}/resources/uploadfile/uploadfile.min.css" rel="stylesheet">
		<script src="${cp}/resources/uploadfile/jquery.form.js"></script>
		<script src="${cp}/resources/uploadfile/jquery.uploadfile.js"></script>
		<script type="text/javascript" src="${cp}/resources/js/uprom/uprom_form.js?v=${jsdate}"></script>
	</head>
	<body>
		<jsp:include page="../include/menubar.jsp" flush="true" />
	
					
		<input type="hidden" value="N" id="entireDirectoryFlag" name="entireDirectoryFlag" />
		<input type="hidden" value="N" id="sequentialFlag" name="sequentialFlag" />
		<input type="hidden" value="N" id="overwriteFlag" name="overwriteFlag" />
		<input type="hidden" value="" id="siteParam" name="siteParam" />
		<input type="hidden" value="" id="siteDescParam" name="siteDescParam" />
		<input type="hidden" value="${userId}" id="userId" name="userId" />
						
		<table cellpadding="3" border="0" style="border-collapse: collapse;">
			<tr>
				<td>
						<table id="formInputTable" cellpadding="3" border="0" style="border-collapse:collapse;">
			
							<tr >
								<th align="left">Serial Number:</th>
								<td >
									<input type="text" id="serialNumber" name="serialNumber" onkeyup="upromAppObj.validateSerialNumber(event)" />
								</td>
							</tr>
							<tr>
								<th align="left">Translated Path:</th>
								<td>
									<input type="text" id="translatedPath" name="translatedPath" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<th align="left">Sites:</th>
								<td>
									<select id="siteSelect" name="siteSelect"  multiple size="10" onclick="upromAppObj.resetSitesButton();" onchange="upromAppObj.updateSiteParam();">
										<c:forEach var="hostItem" items="${upromHostList}">
											<option value="${hostItem.hostNm}">${hostItem.hostDs} (${hostItem.hostNm})</option> 
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<th align="left">Options:</th>
								<td >
									<table cellpadding="0" border="0" style="border-collapse: collapse;">
							    		<tr>
							    			<td>
							    				<label for="entireDirectoryBtn">Files Only</label><input type="checkbox" id="entireDirectoryBtn" />
							    			</td>
							    			<td>
							    				<label for="allSitesBtn">Selected Sites</label><input type="checkbox" id="allSitesBtn" >
							    			</td>
							    			<td>
							    				<label for="sequentialBtn">Non-sequential</label><input type="checkbox" id="sequentialBtn" />
							    			</td>
							    			<td>
							    				<label for="overwriteBtn">No Overwrite</label><input type="checkbox" id="overwriteBtn" />
							    			</td>									    			
							    		</tr>							    						    	
								    </table>
								</td>
							</tr>
							<tr>
								<th align="left"><div id="uploadLabelDiv">Files:</div></th>
								<td>
									<div id="uploadPreventDiv" style="display: none;">
										<div class="ui-state-error ui-corner-all" style="margin-top: 5px; padding: 0 .7em;width: 500px;">
											<div id="uploadPreventMsgDiv"></div>
										</div>
										<br>
								    </div>
									<div id="uploadDirectoryDiv" style="display: none;">
										<div id="advancedUpload">Select Multiple Files</div><br>
										<button id="advancedUploadButton">Upload</button><button id="advancedUploadCancel">Clear Files</button>
									    <%-- <div id="uploadEventsMessage"></div> --%>
								    </div>
								    <div id="uploadZippedDiv" style="display: none;">
								    	<div class="ui-state-highlight ui-corner-all" style="margin-top: 5px; padding: 0 .7em;width: 500px;">
											<p>
											<span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
											Please zip the entire directory for the serial number containing multiple files and sub-directories.
											</p>
										</div>
										<br>
										<div id="zippedUpload">Select Zip File</div><br>
										<button id="zippedUploadButton">Upload</button><button id="zippedUploadCancel">Clear Files</button>
								    </div>
								    <div id="uploadLinkSheetDiv" style="display: none;">
								    	<div class="ui-state-highlight ui-corner-all" style="margin-top: 5px; padding: 0 .7em;width: 500px;">
											<p>
											<span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
											Upload an Excel file with serial-number/site-name pairs. This will toggle their linking/unlink. No column names in file. Col A: serial-numbers, Col B: site-names.
											</p>
										</div>
										<br>
										<div id="linkSheetUpload">Select Excel File</div><br>
										<button id="linkSheetUploadButton">Upload</button><button id="linkSheetUploadCancel">Clear Files</button>
								    </div>
								    <div id="uploadFilesDiv">
								    
								    	<table cellpadding="0" border="0" style="border-collapse: collapse;height: 200px; width: 450px;" >
								    		<tr>
								    			<td><div id="progFileUpload">Select Programming File</div><br></td>
								    		</tr>
								    		<tr>
								    			<td><div id="taskFileUpload">Select JobMaster/Task File</div><br></td>
								    		</tr>
								    		<tr>
								    			<td><div id="labelFileUpload">Select Label File</div><br></td>
								    		</tr>
								    		<tr>
								    			<td>
								    				<button id="singlesUploadButton">Upload</button><button id="singlesUploadCancel">Clear Files</button>
								    				<br>
								    			</td>
								    		</tr>
								    	</table>
								    </div>
								</td>
							</tr>
							<tr>
								<th align="left">Other Actions:</th>
								<td >
									<table cellpadding="0" border="0" style="border-collapse: collapse;">
							    		<tr>
							    			<td><button id="deleteBtn">Delete</button></td>
							    			<td><button id="showBtn">Show</button></td>
							    			<td><button id="syncBtn">Sync</button></td>
							    			<td><button id="newDirBtn">New Directory</button></td>
							    			<td><button id="linkUnlinkSiteBtn">Link/Unlink Site</button></td>
							    			<td><button id="clearBtn">Clear</button></td>						    			
							    		</tr>							    						    	
								    </table>
								</td>
							</tr>
						</table>	
						
				</td>
				<td valign="top">
					<div id="messageDialog"><div id="messageDialogBody"></div></div>
					<div id="alertDialog" title=""><div id="alertDialogBody"></div></div>
				</td>
			</tr>
		</table>
		<jsp:include page="../include/footer.jsp" flush="true" />
	</body>
</html>