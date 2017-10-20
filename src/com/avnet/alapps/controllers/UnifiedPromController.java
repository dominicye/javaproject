package com.avnet.alapps.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.avnet.alapps.common.AjaxResponse;
import com.avnet.alapps.common.MailSender;
import com.avnet.alapps.model.gsfc.UpromHost;
import com.avnet.alapps.security.SecurityService;
import com.avnet.alapps.security.SecurityUser;
import com.avnet.alapps.services.UpromService;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

@Controller
public class UnifiedPromController {
	private static Logger log = Logger.getLogger(UnifiedPromController.class);
	private static final String GROUP_EMAIL = "UNIFIED-PROM@avnet.com"; 
	private static final String MASTER_USER = "uPromPub";
	//private static final String MASTER_HOST = "prommaster";
	private static final int MASTER_PORT = 22;
	private static final String MASTER_BASE_PATH = "/uprom/"; //For testing use "/uprom/ken/"
	private static final String UPNEWDIR_SCRIPT = "./upnewdir"; //For testing use "./upnewdir_ken"
	private static final String SYNC_SCRIPT_PREFIX = "sync."; //For testing use "sync.ken."
	private static final String SITE_BASE_PATH = MASTER_BASE_PATH + "site/";
	private static final String STANDARD_BASE_PATH = MASTER_BASE_PATH + "std/";
	private static final String ARCHIVE_BASE_PATH = MASTER_BASE_PATH + "archive/";
	private static final String SEQUENCE_BASE_PATH = MASTER_BASE_PATH + "seq/";
	
	private static final String SEQUENCE_WRITE_GROUP = "sequential";
	private static final String STANDARD_WRITE_GROUP = "w_uprom";
	
	private static final String PRIVATE_KEY_FILE_PATH = "WEB-INF/resources/uprom.pub";
	
	public static final String UPROM_TOOL_USER = "UPTLU";
	
	
	public enum UnzipStatus {
		SUCCESS ("File uzipped successfully."),
	    FAIL_NO_RESPONSE ("No response from the directory unzip operation. Please contact support."),
	    FAIL_UNKNOWN ("Unzip operation failed to unzip file. Please contact support."),
	    FAIL_EXISTS ("Directory already exists on server. You must toggle overwrites to upload this file."),
	    FAIL_MALFORMED_REQUEST ("Unzip request error. Please contact support."),
	    FAIL_MISSING_FILE ("Zip file does not exist on server. Please try again or contact support."),
	    FAIL_SN_MISMATCH ("Specified serial number does not match the serial number contained within the zip file."),
	    FAIL_ZIP_NOT_REMOVED ("Unzip operation failed to remove zip file. Please contact support."),
	    FAIL_LOG_ERROR ("Unzip operation failed to create or write to log file. Please contact support.")
	    ;
	    
		UnzipStatus(String errorMessage) { this.errorMessage = errorMessage; };
	    private String errorMessage;
		public String getErrorMessage() {
			return errorMessage;
		}
	    
	}

	@Autowired private SecurityService securityService;
	@Autowired private MailSender mailSender;
	@Autowired private String tier;
	
	
	private String getHostLongDescription(UpromHost h) {
		return h.getHostDs() + " (" + h.getHostNm() + ")";
	}
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value = "/uprom/linkSheetUpload.json", method = RequestMethod.POST) 
	public @ResponseBody AjaxResponse linkSheetUpload(MultipartHttpServletRequest request) throws Exception {
		  AjaxResponse ajaxResponse = new AjaxResponse();
		  UpromService upromService = new UpromService();
		  String userId = request.getParameter("userId");

		  try {
	
			UpromHost masterHost = upromService.getMasterSite();
		    if ( masterHost == null ) {
		    	throw new RuntimeException("No master host found. Please contact support.");
		    }
		    
		    Map<String, UpromHost> siteNameMap = 
		    	upromService.getUpromHostsMapByName(true, false, false, true);  
		    if ( siteNameMap == null || siteNameMap.size() == 0 ) {
		    	throw new RuntimeException("No hosts found. Please contact support.");
		    }
	
		    Map<String, UpromHost> siteHostMap = 
		    	upromService.getUpromHostsMapByHost(true, false, false);  
		    if ( siteHostMap == null || siteHostMap.size() == 0 ) {
		    	throw new RuntimeException("No hosts found. Please contact support.");
		    }
		    
		    Map<String, List<String>> toggleMap = new LinkedHashMap<String, List<String>>();
		    
		    
    		Map<String, MultipartFile> fileMap = request.getFileMap();
    		if ( fileMap != null && fileMap.size() > 0 ) {		
    			for ( String fileKey: fileMap.keySet() ) {
    				InputStream fis = null;
    				OPCPackage opcPackage = null;
    				NPOIFSFileSystem npoFileSystem = null;
    				Workbook wb = null;
    				//Process all Excel files uploaded to extract all serial numbers and uprom sites pairs
    				try {
						MultipartFile file = fileMap.get(fileKey);
					  	String originalFileName = file.getOriginalFilename(); 
					  	fis = file.getInputStream();
					  	if ( originalFileName.toUpperCase().endsWith(".XLSX") ) { //Excel xlsx files
					  		opcPackage = OPCPackage.open(fis);
					  		wb = new XSSFWorkbook(opcPackage);
					  	}
					  	else if ( originalFileName.toUpperCase().endsWith(".XLS") ) { //Excel xls files
					  		npoFileSystem = new NPOIFSFileSystem(fis);
						  	wb = new HSSFWorkbook(npoFileSystem.getRoot(), true);
					  	}
					  	else {
					  		throw new RuntimeException(
									  "Uploaded file is not of type .xlsx or .xls. Please correct and try upload again.");
					  	}
		
					  	Sheet sheet1 = wb.getSheetAt(0);
					    for (Row row : sheet1) {					    	
					    	String serialNumber = null;
					    	String upromSiteName = null;
					        for (Cell cell : row) {
					        	String val = null;
					        	//System.out.println("cidx=" + cell.getColumnIndex());
					            switch (cell.getCellType()) {
					                case Cell.CELL_TYPE_STRING:
					                    val = cell.getRichStringCellValue().getString().trim();
					                    break;
					                case Cell.CELL_TYPE_NUMERIC:
					                	val = String.valueOf((int)cell.getNumericCellValue());
					                    break;
					            }
					            if ( cell.getColumnIndex() == 0 ) serialNumber = val;
					            else if ( cell.getColumnIndex() == 1 ) upromSiteName = val;
					        }
					        
					        if ( serialNumber != null && serialNumber.length() > 0 
					        		&& upromSiteName != null && upromSiteName.length() > 0 ) {
					        	if ( !siteNameMap.containsKey(upromSiteName.toUpperCase()) ) {
					        		throw new RuntimeException(
											  "Excel sheet contains invalid uProm site name " + upromSiteName + ". Please correct and try upload again.");
					        	}
					        	
					        	UpromHost host = siteNameMap.get(upromSiteName.toUpperCase());
					        	if ( masterHost.getHostNm().equalsIgnoreCase(host.getHostNm()) ) {
						    		throw new Exception(masterHost.getHostDs() + " is the referent server and cannot be linked or unlinked. Please correct and try upload again.");
						    	}
					        	
					        	if ( !toggleMap.containsKey(host.getHostNm()) ) {
					        		toggleMap.put(host.getHostNm(), new ArrayList<String>());
					        	}
					        	toggleMap.get(host.getHostNm()).add(serialNumber);
					        	
					        }
					        else {
					        	throw new RuntimeException(
										  "Excel sheet contains a blank serial number and/or uProm site name. Please correct and try upload again.");
					        }
					    } //end processing excel sheet
    				}
				    catch ( Exception e ) {
				    	throw e;
				    }
				    finally {
				    	if ( npoFileSystem != null ) { npoFileSystem.close(); }
				    	if ( opcPackage != null ) { opcPackage.close(); }
				    	if ( fis != null ) { fis.close(); }
				    } 
				} //end loop of all files uploaded
    			
    			//Now link toggle serials with hosts from file(s) 
    			//Linking toggle only happens on the master, but the master itself cannot be linked to a serial
    			//Currently not allowing multiple file uploads right now on front end, but it is supported here
    			Session session = null;
    			
			    try {
			    	StringBuilder message = new StringBuilder();
				    JSch jsch = this.setupJSch();
				    session = getConnectedJSchSession(jsch, MASTER_USER, masterHost.getHostNm(), MASTER_PORT);		    
				    for ( String hn : toggleMap.keySet() ) {
				    	List<String> serialList = toggleMap.get(hn);
				    	UpromHost host = siteHostMap.get(hn);
				    	for ( String sn : serialList ) {
				    		String serialNumberPath = this.createSerialPath(sn);
							String[] dirParts = serialNumberPath.split("\\\\"); 	    
						    boolean isLinked = linkCheck(session, SITE_BASE_PATH, host.getHostCd(), dirParts[1], dirParts[2], sn);
				    		if ( isLinked ) {
				    			boolean unlinked 
				    				= unlink(session, SITE_BASE_PATH, host.getHostCd(), dirParts[1], dirParts[2], sn);
				    			if ( !unlinked ) {
				    				message.append("<br>").append("Failed to delete symbolic link for ").append(getHostLongDescription(host))
				    					.append(" in ").append(SITE_BASE_PATH).append(host.getHostCd())
				    					.append("/std/").append(dirParts[1]).append("/").append(dirParts[2]).append("/")
				    					.append(" on ").append(masterHost.getHostDs());
				    				throw new Exception(message.toString());
				    			}
				    			message.append("<br>").append(sn).append(" is now unlinked from ").append(getHostLongDescription(host));
				    		}
				    		else {
				    			isLinked = link(session, SITE_BASE_PATH, host.getHostCd(), dirParts[1], dirParts[2], sn);
				    			if ( !isLinked ) {
				    				message.append("<br>").append("Failed to create symbolic link for ").append(getHostLongDescription(host))
				    					.append(" in ").append(SITE_BASE_PATH).append(host.getHostCd())
				    					.append("/std/").append(dirParts[1]).append("/").append(dirParts[2]).append("/")
				    					.append(" on ").append(masterHost.getHostDs());
				    				throw new Exception(message.toString());
				    			}
				    			message.append("<br>").append(sn).append(" is now linked to ").append(getHostLongDescription(host));
				    		}
				    	} //end loop over host serial number list
				    } //end loop over host map
				    
				    ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_SUCCESS);
				    ajaxResponse.setResult(message.toString());
			
					String subject = "Unified Prom Batch Link Modification Report";
					String emailMessage = 
						userId + " has batch linked serial numbers with the following results: <br>" + message.toString();
					mailSender.sendMessage(GROUP_EMAIL, subject, emailMessage, true, 5);
			    }
				catch ( Exception e ) {
					throw e;
				}
				finally {
					if ( session != null ) { session.disconnect(); }
				}
    	
    		} //end check if files where uploaded
		
		} 
		catch (Exception ex) {
			log.error("Could not execute uprom link toggle via Excel file.", ex);
			ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_FAIL);
			ajaxResponse.setResult(ex);
		}
		return ajaxResponse;
	}
	
	@Transactional(readOnly = true, timeout = 720)
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/uprom/appletUpload.json", method = RequestMethod.POST) //, headers="Accept=application/json"
	public @ResponseBody AjaxResponse appletUpload(MultipartHttpServletRequest request) throws Exception {
		  AjaxResponse ajaxResponse = new AjaxResponse();
		  boolean hasChangeError = false;
		  //boolean isRecursive = false;
		  boolean isOverwrite = false;
		  boolean isSequential = false;
		  String originalFileName = "";
		  String serialNumber = "";
		  //String relativePath = ""; //No longer supporting nested directories / whole directory upload selection
		  String[] siteNameArray = null;
		  //response.setContentType("text/plain");
		  UpromService upromService = new UpromService();
		  String entireDirectoryFlag = ""; //N = single files, Y = multiple files, Z = zipped directory
		  boolean isZipFileUpload = false;
		  try{
	
			  
		    Enumeration<String> paraNames = (Enumeration<String>)request.getParameterNames();
		    String pname;
		    String pvalue;
		    while ( paraNames.hasMoreElements() ) {
			      pname = (String)paraNames.nextElement();
			      pvalue = request.getParameter(pname);
			      //if ( pname.contains("relpathinfo0") ) {
			      //	  relativePath = pvalue.trim().replaceAll("\\\\", "/");
			      //}
			      if ( pname.contains("entireDirectoryFlag") ) {
			    	  entireDirectoryFlag = pvalue;
			    	  isZipFileUpload = "Z".equalsIgnoreCase(entireDirectoryFlag);
			      }
			      else if ( pname.contains("siteParam") ) { //ignore siteSelectParm. Does not have multiselect
			    	  String siteNameParam = pvalue.trim();
			    	  if ( siteNameParam != null && siteNameParam.length() > 0 ) {
			    		  siteNameArray = siteNameParam.split("[,]");
			    	  }
			      }
			      else if ( pname.contains("overwriteFlag") ) {
			    	  isOverwrite = ( "Y".equalsIgnoreCase(pvalue) ) ? true : false;
			      }
			      else if ( pname.contains("sequentialFlag") ) {
			    	  isSequential = ( "Y".equalsIgnoreCase(pvalue) ) ? true : false;
			      }
			      else if ( pname.contains("serialNumber") ) {
			    	  serialNumber = pvalue.trim();
			      }
			      //System.out.println("*** uprom param: " + pname + " = " + pvalue);   
		    }
		    
		    if ( serialNumber == null || serialNumber.length() == 0 ) {
		    	throw new RuntimeException("You must supply a serial number.");
		    }

		    if ( siteNameArray == null || siteNameArray.length == 0 ) {
		    	throw new RuntimeException("You must select at least one host from the Sites list.");
		    }
		    
		    if ( isZipFileUpload && isSequential ) {
		    	throw new RuntimeException("Zip file upload in Sequention mode is not permitted.");
		    }
		    
		    String serialNumberPath = request.getParameter("translatedPath").trim().replaceAll("\\\\", "/");;
			String[] dirParts = serialNumberPath.split("/"); 
		
			final String writeGroup = ( isSequential ) ? SEQUENCE_WRITE_GROUP : STANDARD_WRITE_GROUP;
			final String basePath = ( isSequential ) ? SEQUENCE_BASE_PATH : STANDARD_BASE_PATH;
			String fullDirectoryPath = basePath + serialNumberPath + "/"; // + relativePath;
			
			
			UpromHost masterHost = upromService.getMasterSite();
		    if ( masterHost == null ) {
		    	throw new RuntimeException("No master host found. Please contact support.");
		    }
		    
		    Map<String, UpromHost> hostNameMap = 
		    	upromService.getUpromHostsMapByHost(true, false, false);  
		    if ( hostNameMap == null || hostNameMap.size() == 0 ) {
		    	throw new RuntimeException("No hosts found. Please contact support.");
		    }
			
    		Map<String, MultipartFile> fileMap = request.getFileMap();
    		if ( fileMap != null && fileMap.size() > 0 ) {	
    			JSch jsch = this.setupJSch();	
    			for ( String fileKey: fileMap.keySet() ) {

    				File tempFile = null;
    				InputStream fis = null;
    				FileOutputStream tmpFos = null;
    				try {
    				  MultipartFile file = fileMap.get(fileKey);
    				  originalFileName = file.getOriginalFilename();
  
					  tempFile = File.createTempFile("uprom_", ".tmp");
					  System.out.println("Temp file : " + tempFile.getAbsolutePath());
					  tmpFos = new FileOutputStream(tempFile);
					  fis = file.getInputStream();
					  IOUtils.copy(fis, tmpFos);
					  int siteCounter = 1;
					  for ( String siteName : siteNameArray ) {
						  final UpromHost host = hostNameMap.get(siteName);
						  final boolean isMasterHost = masterHost.getHostNm().equals(host.getHostNm());
						  
						  if (  isMasterHost && isSequential ) {
							  throw new RuntimeException(
									  "You cannot select " + masterHost.getHostDs() + " when publishing a sequential!  Sequentials must be published to individual site servers.  Aborting..."
									  );
						  }
						  else if ( !isMasterHost && siteCounter == 1 && !isSequential ) {							  
							  throw new RuntimeException(
									  masterHost.getHostDs() + " must be selected when publishing a non-sequential Serial Number!  Aborting..."
									  );
						  }
						  else if ( isMasterHost && siteNameArray.length == 1 && !isSequential && !isZipFileUpload ) {
							  throw new RuntimeException(
									  "You must select at least one local site when publishing a non-sequential Serial Number!  Aborting..."
									  );
						  }

						  Session session = null;
						  InputStream tmpFis = null;
						  try {
						      
							  //session.setPortForwardingL(request.getLocalPort(), MASTER_HOST, MASTER_PORT); //Not working for single file uploads
							 
						      if ( isSequential || isMasterHost ) {
						    	  session = getConnectedJSchSession(jsch, MASTER_USER, host.getHostNm(), MASTER_PORT);
						    	  
						    	  
						    	  if ( isZipFileUpload ) { //zipped file xfer
						    		  fullDirectoryPath = "";
									  tmpFis = new FileInputStream(tempFile);
									  this.copyFile(session, tmpFis, "", originalFileName);
									  
									  UnzipStatus unzipStatus =
										  this.remoteUnzip(session, originalFileName, dirParts[1], dirParts[2], serialNumber, isOverwrite);
									  if ( unzipStatus != UnzipStatus.SUCCESS ) {
						    				throw new RuntimeException(unzipStatus.getErrorMessage() +
						    						" host=" + getHostLongDescription(host) +
						    						", file=" + originalFileName + ".");
									  }
								  }
						    	  else {
							    	  //Master handles all links, directories, and physical files unless its sequential mode. Evan says dont worry about checking servers other than master
								      boolean fileExists = checkIfFileExistsCaseInsensitive(session, fullDirectoryPath, originalFileName);						  
									  if ( !isOverwrite && fileExists ) {
										  String errMsg = 
											  fullDirectoryPath + originalFileName + " already exists on " + host.getHostNm() +
											  ".  You must delete the existing file or toggle overwrites to continue.";
										  throw new RuntimeException(errMsg);
									  }
									  if ( ! this.checkIfDirectoryExists(session, fullDirectoryPath) ) {
										  if ( !this.createDirectory(session, fullDirectoryPath) ) {
											  throw new RuntimeException(
													  "Could not create directory " + fullDirectoryPath + " on host " + host.getHostNm());
										  }
									  }
									  tmpFis = new FileInputStream(tempFile);
									  this.copyFile(session, tmpFis, fullDirectoryPath, originalFileName);
						    	  }
							  }
					
							  if ( !isSequential && isMasterHost ) {
								  
								  if ( session == null || !session.isConnected() ) {
									  session = getConnectedJSchSession(jsch, MASTER_USER, host.getHostNm(), MASTER_PORT);
								  }
								  
								  boolean changeError = false;
								  StringBuilder changeMessage = new StringBuilder();
								  boolean success = changeOwnership(session, writeGroup, basePath + serialNumberPath);
								  if ( !success ) {
									  changeError = true;
									  changeMessage.append("Failed to modify permissions on ").append(basePath + serialNumberPath)
									  	.append(" from ").append(masterHost.getHostDs()).append(".");
								  }
								  success = changeMode(session, basePath + serialNumberPath);
								  if ( !success ) {
									  changeError = true;
									  changeMessage.append("Failed to chown ").append(basePath + serialNumberPath)
									  	.append(" from ").append(masterHost.getHostDs()).append(".");
								  } 

								  if ( changeError ) {
									  //response.getOutputStream().write(("WARNING: " + changeMessage.toString()).getBytes()); 
									  //TODO: need to start handling errors vs. warning vs. success
									  ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_SUCCESS);
									  ajaxResponse.setResult("WARNING: " + changeMessage.toString());
									  hasChangeError = true;
								  }
							  }
							  
							  if ( !isSequential && !isMasterHost ) {
								  	Session msess = null;
				    				try {
					    				msess = getConnectedJSchSession(jsch, MASTER_USER, masterHost.getHostNm(), MASTER_PORT);
					    				final String linkPath = SITE_BASE_PATH + "/" + host.getHostCd() + "/std/" + 
					    					dirParts[1] + "/" + dirParts[2] + "/";
					    				boolean success = checkIfLinkExists(msess, linkPath, serialNumber);
					    				if ( !success ) {
					    					//This suffers from race condition when uploading multiple single files for same SN
					    					success = link(msess, SITE_BASE_PATH, host.getHostCd(), dirParts[1], dirParts[2], serialNumber);
							    			//if ( !success ) {
							    			//	throw new RuntimeException(
							    			//			"Failed to create symbolic link for " + getHostLongDescription(host) +
							    			//			" in " + linkPath + serialNumber + " on " + masterHost.getHostDs());
							    			//}
					    				}
				    				}
				    				finally {
				    					if ( msess != null ) msess.disconnect();
				    				}
							  }
						  }
						  catch ( Exception e ) {
							  throw new RuntimeException(
									  "Failed to transfer " + fullDirectoryPath + originalFileName + " to " + host.getHostNm() + ": " + e.getMessage());
						  }
					      finally {
					    	  if ( tmpFis != null ) tmpFis.close();
					    	  if ( session != null ) session.disconnect();
					      }
					      siteCounter++;
					  }
    				}
    				finally {
    					if ( tempFile != null ) {
    						if ( fis != null ) fis.close();
    						if ( tmpFos != null ) tmpFos.close();
    						tempFile.delete();
    						tempFile = null;
    					}
    				}	
    				
    				
				} //End loop over all files uploaded
    		} //End check if any files where uploaded
	      
			if ( !hasChangeError ) {
				ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_SUCCESS);
				ajaxResponse.setResult("uProm file upload successful.");
			}
		  }
		  catch(Exception e){
			  log.error("Could not upload uprom file.", e);
			  //response.getOutputStream().write(("ERROR: " + e.getMessage()).getBytes()); 
			  ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_FAIL);
			  ajaxResponse.setResult(e);
		  }

		  return ajaxResponse;
	  }
	  
	  @Transactional(readOnly = true)
	  @RequestMapping(value="/uprom/emailAlertCommand.json", method=RequestMethod.GET)
	  public @ResponseBody AjaxResponse emailAlertCommand(@RequestParam String message, @RequestParam String subject) throws Exception {
			AjaxResponse ajaxResponse = new AjaxResponse();
			//Map<String, Object> resultMap = new HashMap<String, Object>();
			Session session = null;
			try{	

				mailSender.sendMessage(GROUP_EMAIL, subject, message, false, 5);
				
				ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_SUCCESS);
				ajaxResponse.setResult("Email alert set to " + GROUP_EMAIL);
			} 
			catch (Exception ex) {
				ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_FAIL);
				ajaxResponse.setResult(ex);
			}
			finally {
				if ( session != null ) session.disconnect();
			}
			return ajaxResponse;
	}
	  
	  @Transactional(readOnly = true, timeout = 600)
	  @RequestMapping(value="/uprom/newDirectoryCommand.json", method=RequestMethod.GET)
	  public @ResponseBody AjaxResponse newDirectoryCommand(@RequestParam String translatedPath, @RequestParam String sequentialFlag) throws Exception {
		    AjaxResponse ajaxResponse = new AjaxResponse();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Session session = null;
			UpromService upromService = new UpromService();
			try{		
				UpromHost masterHost = upromService.getMasterSite();
			    if ( masterHost == null ) {
			    	throw new RuntimeException("No master host found. Please contact support.");
			    }
			    
				String serialNumberPath = translatedPath.trim();
				String[] dirParts = serialNumberPath.split("\\\\"); 
			    boolean isSequential = ( "Y".equalsIgnoreCase(sequentialFlag.trim()) ) ? true : false;		    
			    JSch jsch = this.setupJSch();
			    session = getConnectedJSchSession(jsch, MASTER_USER, masterHost.getHostNm(), MASTER_PORT);
			    upnewdirExecute(session, isSequential, dirParts[1], dirParts[2]);
			    resultMap.put("message", "Creation complete.");
			    ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_SUCCESS);
				ajaxResponse.setResult(resultMap);
			} 
			catch (Exception ex) {
				ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_FAIL);
				ajaxResponse.setResult(ex);
			}
			finally {
				if ( session != null ) session.disconnect();
			}
			return ajaxResponse;
	}	
	 
	  
	  @Transactional(readOnly = true, timeout = 600)
	  @RequestMapping(value="/uprom/showCommand.json", method=RequestMethod.GET)
	  public @ResponseBody AjaxResponse showCommand(
			  @RequestParam String serialNumber, 
			  @RequestParam String translatedPath, 
			  @RequestParam String sequentialFlag) throws Exception {

		  	StringBuilder message = new StringBuilder();
		  	AjaxResponse ajaxResponse = new AjaxResponse();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Session session = null;
			UpromService upromService = new UpromService();
			try{	
				
				UpromHost masterHost = upromService.getMasterSite();
			    if ( masterHost == null ) {
			    	throw new RuntimeException("No master host found. Please contact support.");
			    }
				
		    	if ( serialNumber == null || serialNumber.trim().length() == 0 ) {
		    		throw new Exception("You must supply a serial number!  Aborting...");
		    	}
		    	serialNumber = serialNumber.trim();
				
				String serialNumberPath = translatedPath;
				String[] dirParts = serialNumberPath.split("\\\\"); 
			    
				boolean isSequential = ( "Y".equalsIgnoreCase(sequentialFlag.trim()) ) ? true : false;	
				final String basePath = ( isSequential ) ? SEQUENCE_BASE_PATH : STANDARD_BASE_PATH;
				final String targetDir = basePath + "/" + dirParts[1] + "/" + dirParts[2] + "/" + serialNumber;
			    
			    JSch jsch = this.setupJSch();
			    session = getConnectedJSchSession(jsch, MASTER_USER, masterHost.getHostNm(), MASTER_PORT);
				String resp = rlsExecute(session, targetDir);
				message.append(resp);
			    
			    resultMap.put("message", message.toString());
			    ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_SUCCESS);
				ajaxResponse.setResult(resultMap);
			} 
			catch (Exception ex) {
				log.error("Could not execute uprom show.", ex);
				ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_FAIL);
				ajaxResponse.setResult(ex);
			}
			finally {
				if ( session != null ) session.disconnect();
			}
			return ajaxResponse;	
	}	
	  
	  @Transactional(readOnly = true, timeout = 600)
	  @RequestMapping(value="/uprom/toggleSiteLinkCommand.json", method=RequestMethod.GET)
	  public @ResponseBody AjaxResponse toggleSiteLinkCommand(
			  @RequestParam String siteDescParam, 
			  @RequestParam String siteParam,
			  @RequestParam String sequentialFlag,
			  @RequestParam String serialNumber,
			  @RequestParam String translatedPath,
			  @RequestParam String userId) throws Exception {
			AjaxResponse ajaxResponse = new AjaxResponse();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Session session = null;
			StringBuilder message = new StringBuilder();
			UpromService upromService = new UpromService();
			try{	
				UpromHost masterHost = upromService.getMasterSite();
			    if ( masterHost == null ) {
			    	throw new RuntimeException("No master host found. Please contact support.");
			    }
			    
			    Map<String, UpromHost> hostNameMap = 
			    	upromService.getUpromHostsMapByHost(true, false, false);  
			    if ( hostNameMap == null || hostNameMap.size() == 0 ) {
			    	throw new RuntimeException("No hosts found. Please contact support.");
			    }
				
				String[] siteArray = null;
				siteDescParam = siteDescParam.trim();
				siteParam = siteParam.trim();
		    	if ( siteParam != null && siteParam.length() > 0 ) {
		    		  siteArray = siteParam.split("[,]");
		    	}
		    	if ( siteArray == null || siteArray.length == 0 ) {
		    		throw new Exception("You must select at least one site to synchronize!  Aborting...");
		    	}
		    	
		    	if ( masterHost.getHostNm().equalsIgnoreCase(hostNameMap.get(siteArray[0]).getHostNm()) ) {
		    		throw new Exception(masterHost.getHostDs() + " is the referent server and cannot be linked or unlinked.");
		    	}
				
		    	if ( "Y".equalsIgnoreCase(sequentialFlag) ) {
		    		throw new Exception("Sequentials cannot be linked or unlinked!  Aborting...");
		    	}
		    	
		    	serialNumber = serialNumber.trim();
		    	if ( serialNumber == null || serialNumber.length() == 0 ) {
		    		throw new Exception("You must supply a serial number!  Aborting...");
		    	}
	
		    	String serialNumberPath = translatedPath.trim();
				String[] dirParts = serialNumberPath.split("\\\\"); 	    
			    JSch jsch = this.setupJSch();
			    session = getConnectedJSchSession(jsch, MASTER_USER, masterHost.getHostNm(), MASTER_PORT);
			    boolean links = false;
			    boolean unlinks = false;
		    	for ( String siteName : siteArray ) {
		    		
		    		//final SiteEnum host = SiteEnum.valueOf(siteName);
		    		final UpromHost host = hostNameMap.get(siteName);
		    		
		    		boolean isLinked = linkCheck(session, SITE_BASE_PATH, host.getHostCd(), dirParts[1], dirParts[2], serialNumber);
		    		if ( isLinked ) {
		    			boolean unlinked 
		    				= unlink(session, SITE_BASE_PATH, host.getHostCd(), dirParts[1], dirParts[2], serialNumber);
		    			if ( !unlinked ) {
		    				message.append("<br>").append("Failed to delete symbolic link for ").append(getHostLongDescription(host))
	    					.append(" in ").append(SITE_BASE_PATH).append(host.getHostCd())
	    					.append("/std/").append(dirParts[1]).append("/").append(dirParts[2]).append("/")
	    					.append(" on ").append(masterHost.getHostDs());
		    				throw new Exception(message.toString());
		    			}
		    			message.append("<br>").append(serialNumber).append(" is now unlinked from ").append(getHostLongDescription(host));
		    			unlinks = true;
		    		}
		    		else {
		    			isLinked = link(session, SITE_BASE_PATH, host.getHostCd(), dirParts[1], dirParts[2], serialNumber);
		    			if ( !isLinked ) {
		    				message.append("<br>").append("Failed to create symbolic link for ").append(getHostLongDescription(host))
		    					.append(" in ").append(SITE_BASE_PATH).append(host.getHostCd())
		    					.append("/std/").append(dirParts[1]).append("/").append(dirParts[2]).append("/")
		    					.append(" on ").append(masterHost.getHostDs());
		    				throw new Exception(message.toString());
		    			}
		    			message.append("<br>").append(serialNumber).append(" is now linked to ").append(getHostLongDescription(host));
		    			links = true;
		    		}
		    	}
				resultMap.put("message", message.toString());
				ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_SUCCESS);
				ajaxResponse.setResult(resultMap);
		
				if ( links && !unlinks ) {				
					String subject = "Unified Prom Link Modification Report";
					String emailMessage = userId + " has linked Serial Number " + serialNumber + " on " + 
					masterHost.getHostDs() + "  to the following sites (servers): " + siteDescParam;
					mailSender.sendMessage(GROUP_EMAIL, subject, emailMessage, false, 5);
				}
				else if ( !links && unlinks ) {			
					String subject = "Unified Prom Link Modification Report";
					String emailMessage = userId + " has unlinked Serial Number " + serialNumber + " on " + 
					masterHost.getHostDs() + "  to the following sites (servers): " + siteDescParam;
					mailSender.sendMessage(GROUP_EMAIL, subject, emailMessage, false, 5);
				}
				else if ( links && unlinks ) {			
					String subject = "Unified Prom Link Modification Report";
					String emailMessage = userId + " has modified links for Serial Number " + serialNumber + " on " + 
					masterHost.getHostDs() + "  to the following sites (servers): " + siteDescParam;
					mailSender.sendMessage(GROUP_EMAIL, subject, emailMessage, false, 5);
				}
			} 
			catch (Exception ex) {
				log.error("Could not execute uprom link toggle.", ex);
				ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_FAIL);
				ajaxResponse.setResult(ex);
			}
			finally {
				if ( session != null ) session.disconnect();
			}
			return ajaxResponse;
	}	
	  
	  @Transactional(readOnly = true, timeout = 720)
	  @RequestMapping(value="/uprom/syncCommand.json", method=RequestMethod.GET)
	  public @ResponseBody AjaxResponse syncCommand(
			  @RequestParam String siteParam,
			  @RequestParam String sequentialFlag
			  ) throws Exception {
			AjaxResponse ajaxResponse = new AjaxResponse();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Session session = null;
			StringBuilder message = new StringBuilder();
			UpromService upromService = new UpromService();
			try{	
				
				UpromHost masterHost = upromService.getMasterSite();
			    if ( masterHost == null ) {
			    	throw new RuntimeException("No master host found. Please contact support.");
			    }
			    
			    Map<String, UpromHost> hostNameMap = 
			    	upromService.getUpromHostsMapByHost(true, false, false);  
			    if ( hostNameMap == null || hostNameMap.size() == 0 ) {
			    	throw new RuntimeException("No hosts found. Please contact support.");
			    }
				
				String[] siteArray = null;
				siteParam = siteParam.trim();
		    	if ( siteParam != null && siteParam.length() > 0 ) {
		    		  siteArray = siteParam.split("[,]");
		    	}
		    	if ( siteArray == null || siteArray.length == 0 ) {
		    		throw new Exception("You must select at least one site to synchronize!  Aborting...");
		    	}
		    	
		    	if ( masterHost.getHostNm().equalsIgnoreCase(hostNameMap.get(siteArray[0]).getHostNm()) ) {
		    		throw new Exception(masterHost.getHostDs() + " is the referent server and cannot be synchronized.");
		    	}
		    
		    	if ( "Y".equalsIgnoreCase(sequentialFlag) ) {
		    		throw new Exception("Sequentials cannot be synchronized!");
		    	}
	    
			    JSch jsch = this.setupJSch();
			    session = getConnectedJSchSession(jsch, MASTER_USER, masterHost.getHostNm(), MASTER_PORT);
			    
			    String activeSyncs = syncsInProgress(session, hostNameMap, siteArray);
	    		if ( activeSyncs != null ) {
	    			throw new Exception("Sync aborted. Site(s) in progress: " + activeSyncs);
	    		}
		    	
		    	for ( String siteName : siteArray ) {
		    		UpromHost host = hostNameMap.get(siteName);
		    		boolean success = sync(session, host.getHostCd());
		    		if ( !success ) {
		    			throw new Exception("Failed to launch sync." + host.getHostCd() + " on " + masterHost.getHostDs());
		    		}
		    	}
		    	message.append("Manual sync complete.");
				resultMap.put("message", message.toString());
				ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_SUCCESS);
				ajaxResponse.setResult(resultMap);
			} 
			catch (Exception ex) {
				log.error("Could not execute uprom sync.", ex);
				ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_FAIL);
				ajaxResponse.setResult(ex);
			}
			finally {
				if ( session != null ) session.disconnect();
			}
			return ajaxResponse;
	}	
	  
	  @Transactional(readOnly = true, timeout = 600)
	  @RequestMapping(value="/uprom/deleteCommand.json", method=RequestMethod.GET)
	  public @ResponseBody AjaxResponse deleteCommand(
			  @RequestParam String  serialNumber,
			  @RequestParam String  siteDescParam,
			  @RequestParam String  siteParam,
			  @RequestParam String  sequentialFlag,
			  @RequestParam String  entireDirectoryFlag,
			  @RequestParam String  progFile,
			  @RequestParam String  taskFile,
			  @RequestParam String  labelFile,
			  @RequestParam String  translatedPath,
			  @RequestParam String  userId
			  ) throws Exception {
			AjaxResponse ajaxResponse = new AjaxResponse();
			Map<String, Object> resultMap = new HashMap<String, Object>();		
			StringBuilder message = new StringBuilder();
			UpromService upromService = new UpromService();
			try{	
			
				if ( serialNumber == null || serialNumber.trim().length() == 0 ) {
					throw new Exception("You must enter a serial number!");
				}
				
				String[] siteNameArray = null;
				siteDescParam = siteDescParam.trim();
				siteParam = siteParam.trim();
		    	if ( siteParam != null && siteParam.length() > 0 ) {
		    		  siteNameArray = siteParam.split("[,]");
		    	}
		    	if ( siteNameArray == null || siteNameArray.length == 0 ) {
		    		throw new Exception("At least one site must be selected!");
		    	}
		    	boolean isSequential = "Y".equalsIgnoreCase(sequentialFlag);	
		    	boolean isRecursive = 
		    		("Y".equalsIgnoreCase(entireDirectoryFlag) || "Z".equalsIgnoreCase(entireDirectoryFlag) ) ? true : false;
		    	
		    	//Alvin asked to have ability to delete task and label files on 1/14/2014
		    	progFile = progFile.trim();
    			taskFile = taskFile.trim();
    			labelFile = labelFile.trim();
		    	
    			if ( !isRecursive && progFile.length() == 0 && taskFile.length() == 0 && labelFile.length() == 0 ) {
    				throw new Exception("You must enter at least one filename or click Entire Directory!");
    			}
  
    			String serialNumberPath = translatedPath.trim();
				String[] dirParts = serialNumberPath.split("\\\\"); 
    			
    			final String basePath = ( isSequential ) ? SEQUENCE_BASE_PATH : STANDARD_BASE_PATH;
    			@SuppressWarnings("unused")
				final String writeGroup = ( isSequential ) ? SEQUENCE_WRITE_GROUP : STANDARD_WRITE_GROUP;
    			
    			UpromHost masterHost = upromService.getMasterSite();
			    if ( masterHost == null ) {
			    	throw new RuntimeException("No master host found. Please contact support.");
			    }
			    
			    Map<String, UpromHost> hostNameMap = 
			    	upromService.getUpromHostsMapByHost(true, false, false);  
			    if ( hostNameMap == null || hostNameMap.size() == 0 ) {
			    	throw new RuntimeException("No hosts found. Please contact support.");
			    }
    		    
			    JSch jsch = this.setupJSch();
			    int siteCounter = siteNameArray.length;
		    	for ( String siteName : siteNameArray ) {
		    		//siteCounter++;
		    		
		    		//final SiteEnum host = SiteEnum.valueOf(siteName);	
		    		UpromHost host = hostNameMap.get(siteName);
		    		
		    		Session session = null;
		    		try {
			    		session = getConnectedJSchSession(jsch, MASTER_USER, host.getHostNm(), MASTER_PORT);
			    		if ( masterHost.getHostNm().equals(host.getHostNm()) && isSequential && isRecursive   ) {
			    			throw new Exception("Entire sequential directories cannot be removed from " + masterHost.getHostDs() + "!  Aborting...");
			    		}
			    		else if ( masterHost.getHostNm().equals(host.getHostNm()) && isSequential && !isRecursive   ) {
			    			throw new Exception("You cannot select " + masterHost.getHostDs() + " when deleting a sequential!  Sequential files must be deleted from individual site servers.  Aborting...");
			    		}
			    		else if ( !masterHost.getHostNm().equals(host.getHostNm()) && siteCounter == 1 && !isSequential   ) {
			    			throw new Exception(masterHost.getHostDs() + " must be selected when deleting a non-sequential file or directory!  Aborting...");
			    		}
			    		else if ( masterHost.getHostNm().equals(host.getHostNm()) && siteNameArray.length < hostNameMap.values().size() && isRecursive  ) {
			    			throw new Exception("When deleting directories from " + masterHost.getHostDs() + ", you must also delete all symbolic links in the mirror area!  Aborting...");
			    		}
			    		
			    		final String targetDir = basePath + "/" + dirParts[1] + "/" + dirParts[2] + "/" + serialNumber + "/";
			    		
			    		if ( !isRecursive ) {
			    			
			    			//Alvin asked to have ability to delete task and label files on 1/14/2014
			    			if ( progFile.length() > 0  ) {
				    			boolean exists = checkIfFileExistsCaseInsensitive(session, targetDir, progFile);
				    			if ( !exists ) {
				    				message.append("<br>File ").append(targetDir).append("/").append(progFile)
			    						.append(" does not exist on ").append(host.getHostNm());
				    			}
				    			else {
				    				boolean deleted = deleteFile(session, targetDir, progFile);
				    				if ( !deleted ) {
				    					message.append("<br>Failed to delete ").append(targetDir).append("/").append(progFile)
			    							.append(" from ").append(host.getHostNm());
				    				}
				    			}
			    			}
			    			if ( taskFile.length() > 0  ) {
				    			boolean exists = checkIfFileExistsCaseInsensitive(session, targetDir, taskFile);
				    			if ( !exists ) {
				    				message.append("<br>File ").append(targetDir).append("/").append(taskFile)
			    						.append(" does not exist on ").append(host.getHostNm());
				    			}
				    			else {
				    				boolean deleted = deleteFile(session, targetDir, taskFile);
				    				if ( !deleted ) {
				    					message.append("<br>Failed to delete ").append(targetDir).append("/").append(taskFile)
			    							.append(" from ").append(host.getHostNm());
				    				}
				    			}
			    			}
			    			if ( labelFile.length() > 0  ) {
				    			boolean exists = checkIfFileExistsCaseInsensitive(session, targetDir, labelFile);
				    			if ( !exists ) {
				    				message.append("<br>File ").append(targetDir).append("/").append(labelFile)
			    						.append(" does not exist on ").append(host.getHostNm());
				    			}
				    			else {
				    				boolean deleted = deleteFile(session, targetDir, labelFile);
				    				if ( !deleted ) {
				    					message.append("<br>Failed to delete ").append(targetDir).append("/").append(labelFile)
			    							.append(" from ").append(host.getHostNm());
				    				}
				    			}
			    			}
			    		}
			    		else {
			    			if ( masterHost.getHostNm().equals(host.getHostNm()) ) {
			    				final String archiveDir = ARCHIVE_BASE_PATH + "/" + dirParts[1] + "/" + dirParts[2] + "/";
			    				boolean success = checkIfDirectoryExists(session, targetDir);
			    				if ( !success ) {
			    					message.append("<br>Directory ").append(targetDir).append("/")
		    							.append(" does not exist on ").append(host.getHostNm());
			    				}
			    				else {
			    					success = createDirectory(session, archiveDir);
			    					if ( !success ) {
			    						message.append("<br>Failed to create ").append(archiveDir)
			    							.append(" on ").append(host.getHostNm());
			    					}
			    				}
			    				success = moveDirectory(session, targetDir, archiveDir); //Archive it
			    				if ( !success ) {
			    					message.append("<br>Failed to archive ").append(targetDir)
			    						.append(" on ").append(host.getHostNm());
			    				}
			    			}
			    			else if ( !isSequential ) {
			    				Session msess = null;
			    				try {
				    				msess = getConnectedJSchSession(jsch, MASTER_USER, masterHost.getHostNm(), MASTER_PORT);
				    				final String linkPath = 
				    					SITE_BASE_PATH + "/" + host.getHostCd() + "/std/" + dirParts[1] + "/" + dirParts[2] + "/";
				    				boolean success = checkIfLinkExists(msess, linkPath, serialNumber);
				    				if ( !success ) {
				    					message.append("<br>").append(linkPath).append(serialNumber).append(" does not exist on ")
				    						.append(masterHost.getHostDs()).append(" or is not a symbolic link.");
				    				}
				    				else {
				    					success = deleteFile(msess, linkPath, serialNumber);
				    					if ( !success ) {
				    						message.append("<br>Failed to delete ").append(linkPath).append(serialNumber)
				    							.append(" on ").append(masterHost.getHostDs());
				    					}
				    					else {
				    						message.append("<br>Successfully removed link ").append(linkPath).append(serialNumber)
			    							.append(" on ").append(masterHost.getHostDs());
				    					}
				    				}
			    				}
			    				finally {
			    					if ( msess != null ) msess.disconnect();
			    				}
			    			}
			    		}
		    		}	
		    		catch( Exception e ) {
		    			log.error("Could not execute uprom delete on host " + host.getHostDs(), e);
		    			throw e;
		    		}
			    	finally {
			    		if ( session != null ) session.disconnect();
			    	}
		    	}
				resultMap.put("message", message.toString());
				ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_SUCCESS);
				ajaxResponse.setResult(resultMap);
				
				
				String subject = "Unified Prom Delete Report for SN: " + serialNumber + " generated by " + userId;
				String emailMessage = userId + " has deleted files from Serial Number " + serialNumber + " on " + 
					masterHost.getHostDs() + " and unlinked it from the following sites (servers): " + siteDescParam;
		
				mailSender.sendMessage(GROUP_EMAIL, subject, emailMessage, false, 5);
			} 
			catch (Exception ex) {
				log.error("Could not execute uprom delete.", ex);
				ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_FAIL);
				ajaxResponse.setResult(ex);
			}
			return ajaxResponse;
	}	  

	  
	@RequestMapping(value="/uprom/upromForm.htm", method=RequestMethod.GET)
	@Transactional(readOnly = true, timeout = 600)
	public String upromForm(ModelMap map) throws Exception {
		SecurityUser user = this.securityService.getAuthenticatedUser();
		if (!this.securityService.isAuthorized(user.getAvnetGlobalUserId(), UPROM_TOOL_USER)) {
			return "auth_error";
		}
	
		UpromService upromService = new UpromService();
		List<UpromHost> list = upromService.getUpromHosts(true, false, false);
		
		if ( list == null || list.size() == 0 ) {
			String msg = "No uPROM hosts found. Please contact support.";
			log.error(msg);
			map.put("message", msg);
			map.put("upromHostList", list.toArray(new UpromHost[list.size()]));
		}
		else {
			map.put("message", null);
			map.put("upromHostList", list.toArray(new UpromHost[list.size()]));
		}
		map.put("userId", user.getAvnetGlobalUserId()); 

		return "uprom/uprom_form";
	}
	
	
	private JSch setupJSch() throws Exception {
		JSch jsch = new JSch();	    				      
		URL keyFileURL = 
			  this.getClass().getClassLoader().getResource(PRIVATE_KEY_FILE_PATH);
		if (keyFileURL == null)  {
			  throw new RuntimeException("Private key file " + PRIVATE_KEY_FILE_PATH + " not found in classpath for uprom.");
		}
		URI keyFileURI = keyFileURL.toURI();		    				       
		jsch.addIdentity(new File(keyFileURI).getAbsolutePath());	
		return jsch;
	}
	
	private Session getConnectedJSchSession(JSch jsch, String user, String host, int port) throws Exception {
		Session session = null;
		session = jsch.getSession(user, host, port);
		//UserInfo ui = new HostUserInfo(password);
		//session.setUserInfo(ui);	    				      
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		return session;
	}
	
	
	
	private boolean linkCheck(Session session, String baseDirectory, String siteCode, String firstDir, String secondDir, String serialNumber) throws Exception {
		String cmd = " test -L " + baseDirectory + "/" + siteCode + "/std/" + firstDir + "/" + secondDir + "/" + serialNumber
			+ " ; echo $?";
		String[] execResp = executeRemoteCommand(session, cmd, true) ;
		if ( execResp != null && execResp.length > 0 &&  "0".equalsIgnoreCase(execResp[0]) ) {
			return true;
		}
		return false;
	}	
	
	private boolean link(Session session, String baseDirectory, String siteCode, String firstDir, String secondDir, String serialNumber) throws Exception {
		
		final String sourceDir = MASTER_BASE_PATH + "std/" + firstDir + "/" + secondDir + "/" + serialNumber;
		if ( !this.checkIfDirectoryExists(session, sourceDir) ) {
				throw new Exception("Cannot create link. Source directory does not exist: " + sourceDir);
		}
		
		final String targetDir = baseDirectory + "/" + siteCode + "/std/" + firstDir + "/" + secondDir;
		if ( !this.checkIfDirectoryExists(session, targetDir) ) {
			if ( !this.createDirectory(session, targetDir) ) {
				throw new Exception("Failed to create directory " + targetDir);
			}
		}
		
		String cmd = "ln -s " + sourceDir + " " + targetDir +  "/" + " ;  echo $?";
		String[] execResp = executeRemoteCommand(session, cmd, true) ;
		/*
		StringBuilder sb = new StringBuilder().append("\n\n ****** cmd=[")
		.append(cmd).append("], execResp=[" );
		for ( String resp : execResp ) {
			sb.append("\n").append(resp);
		}
		sb.append("\n]\n\n");
		System.out.println(sb.toString());
		*/
		if ( execResp != null && execResp.length > 0 &&  "0".equalsIgnoreCase(execResp[0]) ) {
			return true;
		}
		return false;
	}	
	
	private boolean unlink(Session session, String baseDirectory, String siteCode, String firstDir, String secondDir, String serialNumber) throws Exception {
		final String targetPath = baseDirectory + "/" + siteCode + "/std/" + firstDir + "/" + secondDir + "/" + serialNumber;
		String cmd = "rm " + targetPath + " ; echo $?";
		String[] execResp = executeRemoteCommand(session, cmd, true) ;
		if ( execResp != null && execResp.length > 0 &&  "0".equalsIgnoreCase(execResp[0]) ) {
			return true;
		}
		return false;
	}	
	
	private boolean checkIfLinkExists(Session session, String targetDir, String targetName) throws Exception {
		String cmd = "test -L " + targetDir + targetName + " ; echo $?";
		String[] execResp = this.executeRemoteCommand(session, cmd, true);
		if ( execResp != null && execResp.length > 0 &&  "0".equalsIgnoreCase(execResp[0]) ) {
			return true;
		}
		return false;
	}
	
	private boolean sync(Session session, String siteCode) throws Exception {
		if ( !checkIfFileExists(session, "", SYNC_SCRIPT_PREFIX + siteCode) ) {
			UpromService upromService = new UpromService();
			UpromHost masterHost = upromService.getMasterSite();
		    if ( masterHost == null ) {
		    	throw new RuntimeException("No master host found. Please contact support.");
		    }
		    
			//Check to make sure site specific sync script exists
			throw new Exception(SYNC_SCRIPT_PREFIX + siteCode + " does not exist on " + masterHost.getHostDs());
		}
		String cmd = "./" + SYNC_SCRIPT_PREFIX + siteCode + " -dv start ; echo $?";
		//System.out.println("********* sync cmd = " + cmd);
		String[] execResp = executeRemoteCommand(session, cmd, true) ;
		for ( String resp : execResp ) {
			//System.out.println("********* sync resp = " + resp);
			if ( "0".equalsIgnoreCase(resp) ) {
				return true;
			}
		}
		return false;
	}
	
	private String syncsInProgress(Session session, Map<String, UpromHost> hostNameMap, String[] siteArray) throws Exception {
		String activeSyncSites = null;
		if ( siteArray != null && siteArray.length > 0 ) {
			for ( String siteName : siteArray ) {
	    		UpromHost host = hostNameMap.get(siteName);
				String cmd = "ps -e | grep -v grep | grep sync." + host.getHostCd() + "; echo $?";
				String[] execResp = executeRemoteCommand(session, cmd, true) ;
				for ( String resp : execResp ) {
					if ( "0".equalsIgnoreCase(resp) ) {
						if ( activeSyncSites == null ) {
							activeSyncSites = "";
						}
						activeSyncSites += " * " + host.getHostDs();
					}
				}
			}
		}
		return activeSyncSites;
	}
	
	private boolean checkIfFileExistsCaseInsensitive(Session session, String targetDir, String targetName) throws Exception {
		String cmd = "find '" + targetDir + "' -type f | grep -i '" + targetDir + targetName + "' ";
		String[] execResp = this.executeRemoteCommand(session, cmd, true);
		
		if ( execResp != null && execResp.length > 0 ) {
			for ( String resp : execResp ) {
				if ( (targetDir + targetName).equalsIgnoreCase(resp) ) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkIfFileExists(Session session, String targetDir, String targetName) throws Exception {
		String cmd = "test -e " + targetDir + targetName + " ; echo $?";
		String[] execResp = this.executeRemoteCommand(session, cmd, true);
		
		if ( execResp != null && execResp.length > 0 ) {
			for ( String resp : execResp ) {
				if ( "0".equalsIgnoreCase(resp) ) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean deleteFile(Session session, String targetDir, String targetName) throws Exception {
		String cmd = "rm -f '" + targetDir + targetName + "' ; echo $?";
		String[] execResp = this.executeRemoteCommand(session, cmd, true);
		if ( execResp != null && execResp.length > 0 ) {
			for ( String resp : execResp ) {
				if ( "0".equalsIgnoreCase(resp) ) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkIfDirectoryExists(Session session, String targetDir) throws Exception {
		String cmd = "test -e " + targetDir + " ; echo $?";
		String[] execResp = this.executeRemoteCommand(session, cmd, true);
		if ( execResp != null && execResp.length > 0 ) {
			for ( String resp : execResp ) {
				if ( "0".equalsIgnoreCase(resp) ) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean createDirectory(Session session, String targetDir) throws Exception {
		String cmd = "mkdir -p " + targetDir + " ; echo $?";
		String[] execResp = executeRemoteCommand(session, cmd, true) ;
		if ( execResp != null && execResp.length > 0 ) {
			for ( String resp : execResp ) {
				if ( "0".equalsIgnoreCase(resp) ) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean moveDirectory(Session session, String sourceDir, String targetDir) throws Exception {
		String cmd = "/bin/mv -f  " + sourceDir + " " + targetDir + " ; echo $?";
		String[] execResp = executeRemoteCommand(session, cmd, true) ;
		if ( execResp != null && execResp.length > 0 ) {
			for ( String resp : execResp ) {
				if ( "0".equalsIgnoreCase(resp) ) {
					return true;
				}
			}
		}
		return false;
	}
	

	private boolean changeOwnership(Session session, String writeGroup, String targetDir) throws Exception {
		String cmd = "chown -R  " + MASTER_USER + ":" + writeGroup + " " + targetDir + " ; echo $?";
		String[] execResp = executeRemoteCommand(session, cmd, true) ;
		if ( execResp != null && execResp.length > 0 ) {
			for ( String resp : execResp ) {
				if ( "0".equalsIgnoreCase(resp) ) {
					return true;
				}
			}
		}
		return false;
	}
	

	private boolean changeMode(Session session, String targetDir) throws Exception {
		String cmd = "chmod 775  " + targetDir + " ; echo $?";
		String[] execResp = executeRemoteCommand(session, cmd, true) ;
		if ( execResp != null && execResp.length > 0 ) {
			for ( String resp : execResp ) {
				if ( "0".equalsIgnoreCase(resp) ) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	private boolean upnewdirExecute(Session session, boolean isSequential, String firstDir, String secondDir) throws Exception {
		String arg = "";
		if ( isSequential ) {
			arg = "sequential";
		}
		else  {
			UpromService upromService = new UpromService();
			UpromHost masterHost = upromService.getMasterSite();
		    if ( masterHost == null ) {
		    	throw new RuntimeException("No master host found. Please contact support.");
		    }
			arg = masterHost.getHostNm();
		}
		String cmd = UPNEWDIR_SCRIPT + " " + arg + " " + firstDir + " " + secondDir;
		String[] execResp = executeRemoteCommand(session, cmd, true) ;
		if ( execResp != null && execResp.length > 0 ) {
			for ( String resp : execResp ) {
				if ( "1".equalsIgnoreCase(resp) ) {
					return true;
				}
			}
		}
		return false;
	}	
	
	private String rlsExecute(Session session, String targetPath) throws Exception {
		StringBuilder returnMsg = new StringBuilder();
		String cmd = "./rls " + targetPath;
		String[] execResp = executeRemoteCommand(session, cmd, true) ;
		for ( String resp : execResp ) {
			returnMsg.append("<br>").append(resp);
		}
		return returnMsg.toString();
	}

	private String[] executeRemoteCommand(Session session, String command, boolean getResponse) throws Exception {
		StringBuilder output = new StringBuilder();
		Channel channel = null;
		try {
			channel = session.openChannel("exec");
			((ChannelExec)channel).setCommand(command);
			((ChannelExec)channel).setErrStream(System.err);
			InputStream in=channel.getInputStream();
			channel.connect();
			if ( getResponse ) {
				byte[] tmp=new byte[1024];
				while(true){
					while(in.available()>0) {
						int i = in.read(tmp, 0, 1024);
						if( i < 0 )break;
						output.append(new String(tmp, 0, i));
						//log.debug("uprom SSH = " + new String(tmp, 0, i));
					}
					if(channel.isClosed()){
						//log.warn("uprom SSH exit-status: " + channel.getExitStatus());
						break;
					}
					try{ Thread.sleep(1000); } catch(Exception ee) { }
				}
			}
		}
		catch (Exception e) {
			log.error("Could not execute remote command for uprom: command=" + command, e);
			throw e;
		}
		finally {
			if ( channel != null ) {
				channel.disconnect();
			}
		}
		return output.toString().split("[\n]");
	}
	
	private UnzipStatus remoteUnzip(Session session, String targetName, String firstDir, String secondDir, String serialNumber, boolean isOverwrite) throws Exception {
		
		String overwriteArg = ( isOverwrite ) ? "1" : "0";
		
		String cmd = "./upunzip " + serialNumber + " " + firstDir + " " + secondDir + " " + targetName + " " + overwriteArg + " ; echo $?"; 
		String[] execResp = executeRemoteCommand(session, cmd, true) ;
		if ( execResp != null && execResp.length > 0 ) {
			final int returnCode = Integer.valueOf(execResp[execResp.length - 1]).intValue(); //Look at last status element
			switch ( returnCode ) {
				case 0: return UnzipStatus.SUCCESS;
				case 1: return UnzipStatus.FAIL_UNKNOWN;
				case 2: return UnzipStatus.FAIL_UNKNOWN;
				case 3: return UnzipStatus.FAIL_EXISTS;
				case 4: return UnzipStatus.FAIL_MALFORMED_REQUEST;
				case 5: return UnzipStatus.FAIL_MISSING_FILE;
				case 6: return UnzipStatus.FAIL_SN_MISMATCH;
			}
		}
		return UnzipStatus.FAIL_NO_RESPONSE;
	}	
	
	private boolean copyFile(Session session, InputStream is, String targetDir, String targetName) throws Exception {
		//System.out.println("targetDir = " + targetDir);
		boolean returnStat = true;
		Channel channel = null;
		try {
			channel = session.openChannel("sftp");
			channel.connect();
			//log.info("JSCH TIMEOUT = " + String.valueOf(session.getTimeout()));
			//((ChannelSftp)channel).cd(MASTER_BASE_PATH);
			((ChannelSftp)channel).put(is, targetDir + targetName);			
		}
		catch ( Exception e ) {
			returnStat = false;
			String errMsg = "Could not copy file to host " + session.getHost() + ":" + targetDir + targetName + ": " + e.getMessage();
			throw new RuntimeException(errMsg);
		}
		finally {
			if ( channel != null ) {
				channel.disconnect();
			}
		}
		return returnStat;
	}
	
	private String createSerialPath(String serialNumber) {
			StringBuilder snb = new StringBuilder();
			try { 
		        Integer.parseInt(serialNumber); 
		    } 
			catch(NumberFormatException e) { 
		        return null; 
		    } 

    		int newLength = serialNumber.length();
        	if ( newLength == 9 ) {
        		snb.append('\\') 
        			.append(serialNumber.charAt(0))
        			.append(serialNumber.charAt(1))
        			.append(serialNumber.charAt(2))
        			.append('\\')
        			.append(serialNumber.charAt(3))
					.append(serialNumber.charAt(4))
					.append(serialNumber.charAt(5))
					.append('\\')
					.append(serialNumber);
        	}
        	else if ( newLength == 8 ) {
        		snb.append('\\') 
        		.append('0') 
    			.append(serialNumber.charAt(0))
    			.append(serialNumber.charAt(1))
    			.append('\\')
    			.append(serialNumber.charAt(2))
				.append(serialNumber.charAt(3))
				.append(serialNumber.charAt(4))
				.append('\\')
				.append(serialNumber);
        	}
        	else if ( newLength == 7 ) {
        		snb.append('\\') 
        		.append('0') 
        		.append('0') 
    			.append(serialNumber.charAt(0))
    			.append('\\')
    			.append(serialNumber.charAt(1))
				.append(serialNumber.charAt(2))
				.append(serialNumber.charAt(3))
				.append('\\')
				.append(serialNumber);
        	}
        	else if ( newLength == 6 ) {
        		snb.append('\\') 
        		.append('0') 
        		.append('0') 
        		.append('0') 
    			.append('\\')
    			.append(serialNumber.charAt(0))
				.append(serialNumber.charAt(1))
				.append(serialNumber.charAt(2))
				.append('\\')
				.append(serialNumber);
        	}
    		else if ( newLength == 5 ) {			
    			snb.append('\\') 
        		.append('0') 
        		.append('0') 
        		.append('0') 
    			.append('\\')
    			.append('0') 
    			.append(serialNumber.charAt(0))
				.append(serialNumber.charAt(1))
				.append('\\')
				.append(serialNumber);	
    		}
    		else if ( newLength == 4 ) {
    			snb.append('\\') 
        		.append('0') 
        		.append('0') 
        		.append('0') 
    			.append('\\')
    			.append('0') 
    			.append('0') 
    			.append(serialNumber.charAt(0))
				.append('\\')
				.append(serialNumber);	
    		}
    		else  {
    			snb.append('\\') 
        		.append('0') 
        		.append('0') 
        		.append('0') 
    			.append('\\')
    			.append('0') 
    			.append('0') 
    			.append('0') 
				.append('\\')
				.append(serialNumber);	
    		}
        	return snb.toString();
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	@Autowired
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public String getTier() {
		return tier;
	}

	@Autowired
	public void setTier(String tier) {
		this.tier = tier;
	}

	@Autowired
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	
}
