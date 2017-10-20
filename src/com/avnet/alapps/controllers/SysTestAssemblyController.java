package com.avnet.alapps.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.avnet.alapps.common.AjaxResponse;
import com.avnet.alapps.common.AlAppsConstants;
import com.avnet.alapps.common.MailSender;
import com.avnet.alapps.common.Util;
import com.avnet.alapps.controllers.UnifiedPromController.UnzipStatus;
import com.avnet.alapps.model.alapps.AstCompType;
import com.avnet.alapps.model.alapps.AstCompTypeAttr;
import com.avnet.alapps.model.alapps.AstPart;
import com.avnet.alapps.model.alapps.AstPartAsm;
import com.avnet.alapps.model.alapps.AstPartAsmAttr;
import com.avnet.alapps.model.alapps.AstPartAttr;
import com.avnet.alapps.model.alapps.AstTempBom;
import com.avnet.alapps.model.alapps.AstTestResult;
import com.avnet.alapps.model.alapps.MinAstPartAsm;
import com.avnet.alapps.model.gsfc.UpromHost;
import com.avnet.alapps.security.SecurityService;
import com.avnet.alapps.security.SecurityUser;
import com.avnet.alapps.services.EvolveSalesOrderDetailService;
import com.avnet.alapps.services.SysTestResultService;
import com.avnet.alapps.services.SysTestService;
import com.avnet.alapps.services.UpromService;
import com.avnet.alapps.systest.model.DisplayBom;
import com.avnet.alapps.systest.model.DisplayBomPart;
import com.avnet.alapps.systest.model.DisplayPartAsm;
import com.avnet.alapps.systest.model.DisplayPartAsmAttr;
import com.avnet.alapps.systest.model.DisplayTestResult;
import com.avnet.alapps.systest.model.DisplayTestResultItem;
import com.avnet.alapps.systest.model.JtableCompTypeAttr;
import com.avnet.alapps.systest.model.JtablePart;
import com.avnet.alapps.systest.model.TestSystemClientEnum;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;


@Controller
public class SysTestAssemblyController {
	private static Logger log = Logger.getLogger(SysTestAssemblyController.class);
	@Autowired private SecurityService securityService;
	@Autowired private MailSender mailSender;
	@Autowired private String tier;
	@Autowired private EvolveSalesOrderDetailService evolveSalesOrderDetailService;
	
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/assemblyForm.htm", method=RequestMethod.GET)
	public ModelAndView showForm() throws Exception {
		ModelAndView view = new ModelAndView("systest/assembly_form");
		return view;
	}
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/assembly2ndTouchForm.htm", method=RequestMethod.GET)
	public ModelAndView show2ndTouchForm() throws Exception {
		ModelAndView view = new ModelAndView("systest/assembly_2nd_touch_form");
		return view;
	}
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/assemblyAllTouchForm.htm", method=RequestMethod.GET)
	public ModelAndView showAllTouchForm() throws Exception {
		ModelAndView view = new ModelAndView("systest/assembly_all_touch_form");
		return view;
	}

	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/bomPartList.json", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse getBomPartList(
			@RequestParam String chassisSerial,
			@RequestParam String icn
			) throws Exception {
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{	
			
			SysTestService serv = this.getSysTestService();
	
			//Switch to get BOM data from ITS instead of artificial 
			//DisplayBom bom = serv.getTempBom(chassisSerial, icn);
			DisplayBom bom = serv.getBom(chassisSerial, icn, new BigDecimal(1), null);
			
			
			if ( bom == null ) {
				resultMap.put("message", "S/N not found: " + chassisSerial);
				ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_FAIL);
				ajaxResponse.setResult(resultMap);
				return ajaxResponse;
			}
	
		    resultMap.put("bom", bom);
		    ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_SUCCESS);
			ajaxResponse.setResult(resultMap);
		} 
		catch (Exception ex) {
			log.error("Could not get BOM", ex);
			ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_FAIL);
			ajaxResponse.setResult(ex);
		}
		finally {
		}
		return ajaxResponse;
	}
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/bomPartList2ndTouch.json", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse getBomPartList2ndTouch(
			@RequestParam String chassisSerial,
			@RequestParam String icn
			) throws Exception {
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{	
			SysTestService serv = this.getSysTestService();
			DisplayPartAsm dispPartAsm = null;
			AstPartAsm firstTouchChassisAsm = null;
			BigDecimal firstTouchAssemblyId = null;
			
			/*
			 * Get latest passing 1st touch assembly for same top level chassis serial, that is not same ICN
			 * Throw error if none found
			 */
			Map<BigDecimal, Date> idMap = serv.getChassisPartAsmIdsWithCreateDateBySerialNumber(chassisSerial);
			if ( idMap != null && !idMap.isEmpty() ) {
				BigDecimal[] partAsmIdArray = (idMap.keySet().toArray(new BigDecimal[idMap.size()]));
				boolean firstTouchFound = false;
				boolean testResultsFound = false;
				boolean lastTestResultPassed = false;
				for ( BigDecimal id : partAsmIdArray ) {
					AstPartAsm chassisAsm = serv.getAstPartAsmById(id, false, null);
					if ( chassisAsm.getTouchLevel().intValue() == 1 ) {
						firstTouchFound = true;
						
						List<BigDecimal> filterTestResultIds = new ArrayList<BigDecimal>();
						for ( Object o : chassisAsm.getAstTestResults() ) {
							/*
							 * TODO: May need to check for PASS from specific test system here
							 * tr.getTestSystemNm()
							 */
							AstTestResult tr = (AstTestResult)o;
							filterTestResultIds.add(tr.getTestResultId());
						}
						Collections.sort(filterTestResultIds, new Util.BigDecimalDescendingComparator());
						
						dispPartAsm = new DisplayPartAsm(chassisAsm, false, filterTestResultIds);
						if ( dispPartAsm.getTestResults() != null && dispPartAsm.getTestResults().size() > 0 ) {
							testResultsFound = true;
							DisplayTestResultItem tr = dispPartAsm.getTestResults().get(0);
							if ( "PASS".equalsIgnoreCase(tr.getResultCode()) ) {
								lastTestResultPassed = true;
							}
						}
						firstTouchChassisAsm = chassisAsm;
						firstTouchAssemblyId = firstTouchChassisAsm.getPartAsmId();
						break;
					}
				}
				
				if ( !firstTouchFound ) {
					throw new Exception("1st-touch assembly not found for chassis serial number: " + chassisSerial);
				}
				else if ( !testResultsFound ) {
					throw new Exception("Previously built 1st-touch assembly was not tested for chassis serial number: " + chassisSerial);
				}
				else if ( !lastTestResultPassed ) {
					throw new Exception("Previously built 1st-touch assembly did not PASS for chassis serial number: " + chassisSerial);
				}
			}
			else {
				throw new Exception("No 1st-touch assemblies found for chassis serial number: " + chassisSerial);
			}
			
			/*
			 * TODO: Remove identified component types from BOM list. Currently doing this
			 * in javascript
			 */
			
			resultMap.put("chassisPartAsm", dispPartAsm);

			/*
			 * Get new bom parts from ITS
			 */
			DisplayBom bom = serv.getBom(chassisSerial, icn,  new BigDecimal(2), firstTouchChassisAsm);
			if ( bom == null ) {
				resultMap.put("message", "S/N not found: " + chassisSerial);
				ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_FAIL);
				ajaxResponse.setResult(resultMap);
				return ajaxResponse;
			}
	
		    resultMap.put("bom", bom);
		    resultMap.put("firstTouchAssemblyId", firstTouchAssemblyId.toPlainString());
		    ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_SUCCESS);
			ajaxResponse.setResult(resultMap);
		} 
		catch (Exception ex) {
			log.error("Could not get BOM", ex);
			ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_FAIL);
			ajaxResponse.setResult(ex);
		}
		finally {
		}
		return ajaxResponse;
	}
	
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/bomPartListAllTouch.json", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse getBomPartListAllTouch(
			@RequestParam String chassisSerial,
			@RequestParam String icn
			) throws Exception {
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		DisplayBom bom = null;
		try{	
			boolean isSecondTouch = icn.startsWith("NXCFG") == false;
			SysTestService serv = this.getSysTestService();
			
			
			if ( isSecondTouch ) {
				
				DisplayPartAsm dispPartAsm = null;
				AstPartAsm firstTouchChassisAsm = null;
				BigDecimal firstTouchAssemblyId = null;
				
				/*
				 * Get latest passing 1st touch assembly for same top level chassis serial, that is not same ICN
				 * Throw error if none found
				 */
				Map<BigDecimal, Date> idMap = serv.getChassisPartAsmIdsWithCreateDateBySerialNumber(chassisSerial);
				if ( idMap != null && !idMap.isEmpty() ) {
					BigDecimal[] partAsmIdArray = (idMap.keySet().toArray(new BigDecimal[idMap.size()]));
					boolean firstTouchFound = false;
					boolean testResultsFound = false;
					boolean lastTestResultPassed = false;
					for ( BigDecimal id : partAsmIdArray ) {
						AstPartAsm chassisAsm = serv.getAstPartAsmById(id, false, null);
						if ( chassisAsm.getTouchLevel().intValue() == 1 ) {
							firstTouchFound = true;
							
							List<BigDecimal> filterTestResultIds = new ArrayList<BigDecimal>();
							for ( Object o : chassisAsm.getAstTestResults() ) {
								/*
								 * TODO: May need to check for PASS from specific test system here
								 * tr.getTestSystemNm()
								 */
								AstTestResult tr = (AstTestResult)o;
								filterTestResultIds.add(tr.getTestResultId());
							}
							Collections.sort(filterTestResultIds, new Util.BigDecimalDescendingComparator());
							
							dispPartAsm = new DisplayPartAsm(chassisAsm, false, filterTestResultIds);
							if ( dispPartAsm.getTestResults() != null && dispPartAsm.getTestResults().size() > 0 ) {
								testResultsFound = true;
								DisplayTestResultItem tr = dispPartAsm.getTestResults().get(0);
								if ( "PASS".equalsIgnoreCase(tr.getResultCode()) ) {
									lastTestResultPassed = true;
								}
							}
							firstTouchChassisAsm = chassisAsm;
							firstTouchAssemblyId = firstTouchChassisAsm.getPartAsmId();
							break;
						}
					}
					
					if ( !firstTouchFound ) {
						throw new Exception("1st-touch assembly not found for chassis serial number: " + chassisSerial);
					}
					else if ( !testResultsFound ) {
						throw new Exception("Previously built 1st-touch assembly was not tested for chassis serial number: " + chassisSerial);
					}
					else if ( !lastTestResultPassed ) {
						throw new Exception("Previously built 1st-touch assembly did not PASS for chassis serial number: " + chassisSerial);
					}
				}
				else {
					throw new Exception("No 1st-touch assemblies found for chassis serial number: " + chassisSerial);
				}
				
				/*
				 * TODO: Remove identified component types from BOM list. Currently doing this
				 * in javascript
				 */
				
				resultMap.put("chassisPartAsm", dispPartAsm);

				/*
				 * Get new bom parts from ITS
				 */
				bom = serv.getBom(chassisSerial, icn,  new BigDecimal(2), firstTouchChassisAsm);
	
			    resultMap.put("firstTouchAssemblyId", firstTouchAssemblyId.toPlainString());

				
			}
			else {
				bom = serv.getBom(chassisSerial, icn, new BigDecimal(1), null);
			}
			
	
			if ( bom == null ) {
				resultMap.put("message", "S/N not found: " + chassisSerial);
				ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_FAIL);
				ajaxResponse.setResult(resultMap);
				return ajaxResponse;
			}
	
		    resultMap.put("bom", bom);
		    ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_SUCCESS);
			ajaxResponse.setResult(resultMap);
		} 
		catch (Exception ex) {
			log.error("Could not get BOM", ex);
			ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_FAIL);
			ajaxResponse.setResult(ex);
		}
		finally {
		}
		return ajaxResponse;
	}
	
	@Transactional(readOnly = true, timeout = 720)
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/systest/nodeUpload.json", method = RequestMethod.POST) //, headers="Accept=application/json"
	public @ResponseBody AjaxResponse nodeUpload(
			MultipartHttpServletRequest request,
			@RequestParam String chassisSerial,
			@RequestParam String icn
			
	) throws Exception {
		

	
		//Setup container for processing json
		ContainerFactory containerFactory = new ContainerFactory(){
				public List creatArrayContainer() {
				  return new LinkedList();
				}
				public Map createObjectContainer() {
				  return new LinkedHashMap();
				}                       
			};

		  AjaxResponse ajaxResponse = new AjaxResponse();
		  InputStream fis = null;
		  try {
	    		Map<String, MultipartFile> fileMap = request.getFileMap();
	    		
	    		if ( fileMap != null && fileMap.size() > 0 ) {	
	    			//for ( String fileKey: fileMap.keySet() ) {
					String fileKey = fileMap.keySet().iterator().next();
					MultipartFile file = fileMap.get(fileKey);
					fis = file.getInputStream();
					
					//System.out.println("***  MultipartFile = " + file.getOriginalFilename());
					String jsonString = IOUtils.toString(fis);
				  
					//TODO: May need this to do any pre validation of json file
					JSONParser jsonParser = new JSONParser();
					
					try {
						//Map jsonMap = (Map)
						jsonParser.parse(jsonString, containerFactory);
						ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_SUCCESS);
				  		ajaxResponse.setResult(jsonString);
		    		} 
					catch ( ParseException e ) {
						//log.error("Could not parse .json file: ", e);
						ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_FAIL);
						ajaxResponse.setResult(new Exception("Could not parse node .json file: " + e.toString()));
		    		}

					//} //End loop over all files uploaded
	    		} //End check if any files where uploaded
		  }
		  catch( Exception e ){
			  log.error("Could not process node file.", e);
			  ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_FAIL);
			  ajaxResponse.setResult(e);
		  }
		  finally {
			  if ( fis != null ) fis.close();
		  }
		  return ajaxResponse;
	  }

	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/getComponentTypeAttributesByTypeName.json", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse getComponentTypeAttributesByTypeName(@RequestParam String compTypeNm) throws Exception {
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{	
			
			SysTestService serv = this.getSysTestService();
		    resultMap.put("compTypeAttrs", serv.getComponentTypeAttributesByTypeName(compTypeNm, true));
		    ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_SUCCESS);
			ajaxResponse.setResult(resultMap);
		} 
		catch (Exception ex) {
			log.error("Could not get comp type attrs by type name", ex);
			ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_FAIL);
			ajaxResponse.setResult(ex);
		}
		finally {
		}
		return ajaxResponse;
	}
	

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, timeout = 600)
	@RequestMapping(value="/systest/submitAssembly.htm", method=RequestMethod.POST)
	public ModelAndView submitAssembly(
			@RequestParam String submitData,
			@RequestParam String submitChassisSerialNum,
			@RequestParam String submitICN,
			@RequestParam String submitChassisPartNum,
			@RequestParam String submitChassisPartId,
			@RequestParam String submitTouchLevel,
			@RequestParam(value = "submitFirstTouchAssemblyId", defaultValue = "") final String submitFirstTouchAssemblyId
			) throws Exception {
		
		BigDecimal touchLevel = null;
		submitICN = submitICN.trim();
		try{
			touchLevel = new BigDecimal(submitTouchLevel);
		}
		catch ( Exception e ) {
			throw new Exception("Touch level for assembly is invalid: submitTouchLevel = " + submitTouchLevel);
		}
		
		SecurityUser user = securityService.getAuthenticatedUser();
		SysTestService serv = this.getSysTestService();
	
		ModelAndView view = new ModelAndView("systest/assembly_results");
		view.getModel().put("submitData", submitData);	
		view.getModel().put("submitChassisSerialNum", submitChassisSerialNum);
		view.getModel().put("submitError", null);
		try {
			
			boolean persist = true; //To make development in PROD easier without risk of submitting
			
			
			serv.saveAssemblyFromJsonString(
					submitData, 
					submitChassisSerialNum, 
					submitICN,
					submitChassisPartNum, 
					submitChassisPartId,
					user,
					touchLevel,
					submitFirstTouchAssemblyId,
					persist,
					evolveSalesOrderDetailService
					);
			
			try {
				if ( persist ) serv.updateAstOrderInfoForMiddleware(this.getEvolveSalesOrderDetailService(), submitICN);
			}
			catch (Exception e) {
				log.warn("Could not update AST Order Info for Middleware", e);
			}
		}
		catch ( Exception e ) {
			log.error(
					"Could not submit assembly for serialnumber=" + submitChassisSerialNum + ", icn=" + submitICN +" : ", 
					e);
			view.getModel().put(
					"submitError", 
					"Could not submit assembly for serialnumber=" + submitChassisSerialNum + ", icn=" + submitICN +": " + e.getMessage()
					);
		}
		return view;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, timeout = 600)
	@RequestMapping(value="/systest/submitAssemblyEdit.htm", method=RequestMethod.POST)
	public ModelAndView submitAssemblyEdit(
			@RequestParam String submitData,
			@RequestParam String editChassisSerialNum
			) throws Exception {
		
		SecurityUser user = securityService.getAuthenticatedUser();
		SysTestService serv = this.getSysTestService();
		
		AstPartAsm chassisAsm = null;
		Map<BigDecimal, Date> idMap = serv.getChassisPartAsmIdsWithCreateDateBySerialNumber(editChassisSerialNum);
		if ( idMap != null && !idMap.isEmpty() ) {
			BigDecimal id = (idMap.keySet().toArray(new BigDecimal[idMap.size()]))[0];
			chassisAsm = serv.getAstPartAsmById(id, false, null);
			if ( chassisAsm == null ) {
				throw new Exception("Assembly not found for chassis serial number " + editChassisSerialNum);
			}
		}
		

		serv.saveAssemblyEditFromJsonString(
				submitData, 
				editChassisSerialNum, 
				chassisAsm,
				user
				);
			
		ModelAndView view = new ModelAndView("systest/assembly_edit");
		view.getModel().put("submitData", submitData);	
		view.getModel().put("submitChassisSerialNum", editChassisSerialNum);
		return view;
	}
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/getAssemblyResultsData.json", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody AjaxResponse getAssemblyResultsData(
			@RequestParam(value = "returnMissingAttrs", required = false, defaultValue = "N") final String returnMissingAttrs,
			@RequestParam(value = "touchLevel", required = false, defaultValue = "") final String touchLevel,
			@RequestParam(value = "submitChassisSerialNum", required = true, defaultValue = "") final String submitChassisSerialNum,
			HttpServletResponse response
			) throws Exception {
		
		
		//response.setHeader("Keep-Alive", "timeout=70, max=20");
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean snNotFound = false;
		try{
			SecurityUser user = securityService.getAuthenticatedUser();
			boolean showMissingAttrs = ( "Y".equalsIgnoreCase(returnMissingAttrs) ) ? true : false;
			SysTestService serv = this.getSysTestService();
			DisplayPartAsm dispPartAsm = null;
			Map<BigDecimal, BigDecimal> idMap = serv.getChassisPartAsmIdsWithTouchLevelBySerialNumber(submitChassisSerialNum);
			if ( idMap != null && !idMap.isEmpty() ) {
				
				if ( StringUtils.trimToNull(touchLevel) == null ) {
					BigDecimal id = (idMap.keySet().toArray(new BigDecimal[idMap.size()]))[0];
					AstPartAsm chassisAsm = serv.getAstPartAsmById(id, showMissingAttrs, user);
					if ( chassisAsm != null ) {
						dispPartAsm = new DisplayPartAsm(chassisAsm, false, new ArrayList<BigDecimal>());
					}
					else {
						snNotFound = true;
						throw new Exception("Assembly not found for chassis serial number " + submitChassisSerialNum);
					}
				}
				else if ( StringUtils.isNumeric(touchLevel) ) {
					boolean found = false;
					for (BigDecimal id : idMap.keySet() ) {
						BigDecimal level = idMap.get(id);
						if ( level.equals(new BigDecimal(touchLevel)) ) {
							AstPartAsm chassisAsm = serv.getAstPartAsmById(id, showMissingAttrs, user);
							if ( chassisAsm != null ) {
								dispPartAsm = new DisplayPartAsm(chassisAsm, false, new ArrayList<BigDecimal>());
								found = true;
								break;
							}
						}
					}
					
					if ( !found ) {
						throw new Exception("Assembly not found for chassis serial number " + submitChassisSerialNum + ", touch level " + touchLevel);
					}
				}
				
			}
			
			if ( dispPartAsm == null ) {
				snNotFound = true;
				throw new Exception("Assembly not found for chassis serial number " + submitChassisSerialNum);
			}
			
			resultMap.put("chassisPartAsm", dispPartAsm);
		    
		    
		    ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_SUCCESS);
			ajaxResponse.setResult(resultMap);
		} 
		catch (Exception ex) {
			ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_FAIL);
			if ( snNotFound == true ) {
				log.error(ex.getMessage());
				Map<String, String> msg = new HashMap<String, String>();
				msg.put("message", ex.getMessage());
				ajaxResponse.setResult(msg);
			}
			else {
				log.error("Could not get get chassis assembly. ", ex);
				ajaxResponse.setResult(ex);
			}
		}
		return ajaxResponse;
	}
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/services/getAssemblyResultsData.xml", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String getAssemblyResultsDataXML(
			@RequestParam String chassisSerialNum, 
			@RequestParam String client,
			@RequestParam(value = "returnAllTestResults", defaultValue = "N") final String returnAllTestResults
			) throws Exception {
		
		boolean allTestResults = ( "Y".equalsIgnoreCase(returnAllTestResults) ) ? true : false;
		String returnXml = null;
		try {
			if ( client != null && client.trim().length() > 0 ) {
				client = client.trim();
				
				if ( !TestSystemClientEnum.getNameList().contains(client) ) {
					throw new Exception("Invalid parameter value '" + client + "': Valid clients = " + TestSystemClientEnum.getNameListString());
				}
				log.info("getAssemblyResultsDataXML Calling client: " + client);
			}
			else {
				throw new Exception("Missing parameter: Valid clients = " + TestSystemClientEnum.getNameListString());
			}
			
			boolean getTestResultsAndExtractedValuesList = true;
			TestSystemClientEnum testSystemClient = TestSystemClientEnum.getTestSystemClientByName(client);
			if ( testSystemClient == TestSystemClientEnum.IST ||
					testSystemClient == TestSystemClientEnum.BURNIN ||
					testSystemClient == TestSystemClientEnum.CONFIG ) {
				getTestResultsAndExtractedValuesList = false;
			}
			
			
			SysTestService serv = this.getSysTestService();
			DisplayPartAsm dispPartAsm = null;
			Map<BigDecimal, Date> idMap = serv.getChassisPartAsmIdsWithCreateDateBySerialNumber(chassisSerialNum);
			if ( idMap != null && !idMap.isEmpty() ) {
				BigDecimal id = (idMap.keySet().toArray(new BigDecimal[idMap.size()]))[0];
				AstPartAsm chassisAsm = serv.getAstPartAsmById(id, false, null);
				if ( chassisAsm != null ) {
					if ( chassisAsm.getTouchLevel() != null && chassisAsm.getTouchLevel().intValue() == 1 ) { //1st touch	
						boolean isGoldOrTestUnit = ( chassisSerialNum.toUpperCase().contains("GOLD") || chassisSerialNum.toUpperCase().contains("TEST") );
						if (  isGoldOrTestUnit ) {
							if ( testSystemClient == TestSystemClientEnum.FST  || testSystemClient == TestSystemClientEnum.IMAGING ) {
								throw new Exception("FST and IMAGING are not valid for a 1st-touch assembly GOLD and TEST units.");
							}
						}
						else if (  testSystemClient == TestSystemClientEnum.FST  || testSystemClient == TestSystemClientEnum.IMAGING || testSystemClient == TestSystemClientEnum.CONFIG ) {
							throw new Exception("CONFIG, FST, and IMAGING are not valid for a 1st-touch assembly.");
						}
					}
					else { //2nd touch
						if(  testSystemClient == TestSystemClientEnum.IST  || testSystemClient == TestSystemClientEnum.BURNIN ) {
							throw new Exception("IST and BURNIN tests are not valid for a 2nd-touch assembly.");
						}
					}
					
					
					List<BigDecimal> filterDisplayTestResultIds = new ArrayList<BigDecimal>();
					
					if ( getTestResultsAndExtractedValuesList ) {
						List<BigDecimal> allTestResultIds = new ArrayList<BigDecimal>();
						Map<BigDecimal, String> testResultSystemNamesMap = new HashMap<BigDecimal, String>();
						for ( Object o : chassisAsm.getAstTestResults() ) {
							AstTestResult tr = (AstTestResult)o;
							allTestResultIds.add(tr.getTestResultId());
							testResultSystemNamesMap.put(tr.getTestResultId(), tr.getTestSystemNm());
						}
						Collections.sort(allTestResultIds, new Util.BigDecimalDescendingComparator());
						if ( allTestResults ) {
							//Add all test results from all systems for this chassis
							filterDisplayTestResultIds = allTestResultIds;
						}
						else {
							//Only test the latest test results from each system for this chassis
							List<String> sysList = new ArrayList<String>();
							for ( BigDecimal trId : allTestResultIds) {
								String sys = testResultSystemNamesMap.get(trId);
								if ( !sysList.contains(sys) ) {
									filterDisplayTestResultIds.add(trId);
									sysList.add(sys);
								}
							}
						}
					}
					
					if ( getTestResultsAndExtractedValuesList ) {
						MinAstPartAsm minChassisAsm = serv.getPartAsmByTestResultIdList(chassisAsm.getPartAsmId(), filterDisplayTestResultIds);
						dispPartAsm = new DisplayPartAsm(minChassisAsm, false); //Takes less time, but still too long
						//chassisAsm = serv.getSlimPartAsmByTestResultList(chassisAsm, filterDisplayTestResultIds); //This takes too long.
						//dispPartAsm = new DisplayPartAsm(chassisAsm, getTestResultsAndExtractedValuesList, filterDisplayTestResultIds);
					}
					else {
						dispPartAsm = new DisplayPartAsm(chassisAsm, getTestResultsAndExtractedValuesList, filterDisplayTestResultIds);
					}					
				}
				else {
					throw new Exception("Assembly not found for chassis serial number " + chassisSerialNum);
				}
			}
			else {
				throw new Exception("Chassis serial number not found: " + chassisSerialNum);
			}
			returnXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><system_info>"+ dispPartAsm.toXML() + "</system_info>";
			
			/*
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?><tns:system_info xmlns:tns=\"http://alapps.avnet.com/ALAPPSResponseSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://alapps.avnet.com/ALAPPSResponseSchema ALAPPSResponseSchema.xsd \">"
			 */
		}
		catch ( Exception ex ) {
			log.error("Could not generate xml. ", ex);
			returnXml = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><ast_response>" +
					"<status>" + AjaxResponse.RESPONSE_TYPE_FAIL + "</status>" +
					"<message> " + ex.getMessage()+ "</message>" +
				"</ast_response>";
		}
		return returnXml;
	}
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/assemblyResults.htm", method=RequestMethod.GET)
	public ModelAndView assemblyResults(
			@RequestParam String submitChassisSerialNum,
			@RequestParam(value = "returnMissingAttrs", required = false, defaultValue = "N") final String returnMissingAttrs,
			@RequestParam(value = "touchLevel", required = false, defaultValue = "") final String touchLevel
			) throws Exception {
		ModelAndView view = new ModelAndView("systest/assembly_results");
		view.getModel().put("submitData", "");	
		view.getModel().put("submitChassisSerialNum", submitChassisSerialNum);
		view.getModel().put("returnMissingAttrs", returnMissingAttrs);
		view.getModel().put("touchLevel", touchLevel);
		return view;
	}
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/assemblyResultsEdit.htm", method=RequestMethod.GET)
	public ModelAndView assemblyResultsEdit(
			@RequestParam String submitChassisSerialNum
			) throws Exception {
		/*
		SecurityUser user = securityService.getAuthenticatedUser();
		if (!this.securityService.isAuthorized(user.getAvnetGlobalUserId(), AlAppsConstants.AST_ADMIN_CODE)) {
			return new ModelAndView("auth_error");
		}
		*/
		ModelAndView view = new ModelAndView("systest/assembly_edit");
		view.getModel().put("submitData", "");	
		view.getModel().put("submitChassisSerialNum", submitChassisSerialNum);
		return view;
	}
	
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/services/getLastTestResults.xml", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String getLastTestResults(
			@RequestParam String chassisSerialNum,
			@RequestParam(value = "touchLevel", defaultValue = "") final String touchLevel
			) throws Exception {
		
	
		String returnXml = null;
		try {
			List<DisplayTestResult> resultList = new ArrayList<DisplayTestResult>();
			SysTestService serv = this.getSysTestService();
			SysTestResultService trServ = this.getSysTestResultService();
			Map<BigDecimal, BigDecimal> idMap = serv.getChassisPartAsmIdsWithTouchLevelBySerialNumber(chassisSerialNum);
			if ( idMap != null && !idMap.isEmpty() ) {
	
				List<BigDecimal> allTestResultIds = new ArrayList<BigDecimal>();
				Map<BigDecimal, String> testResultSystemNamesMap = new HashMap<BigDecimal, String>();
					
				if  ( StringUtils.trimToNull(touchLevel) == null ) {
					BigDecimal id = (idMap.keySet().toArray(new BigDecimal[idMap.size()]))[0];
					this.getTestResultIds(serv, id, allTestResultIds, testResultSystemNamesMap);
				}
				else if ( "ALL".equalsIgnoreCase(touchLevel) ) {
					BigDecimal firstTouchId = null;
					BigDecimal secondTouchId = null;
					
					for ( BigDecimal idKey : idMap.keySet() ) {
						if ( idMap.get(idKey).equals(new BigDecimal(1)) ) {
							firstTouchId = idKey;
							break;
						}
					}
					
					for ( BigDecimal idKey : idMap.keySet() ) {
						if ( idMap.get(idKey).equals(new BigDecimal(2)) ) {
							secondTouchId = idKey;
							break;
						}
					}
					
					if ( firstTouchId == null && secondTouchId == null ) {
						throw new Exception("Assembly not found for chassis serial number " + chassisSerialNum + ", with touch level " + touchLevel);
					}
					else {
						if ( firstTouchId != null ) {
							this.getTestResultIds(serv, firstTouchId, allTestResultIds, testResultSystemNamesMap);
						}
						
						if ( secondTouchId != null ) {
							this.getTestResultIds(serv, secondTouchId, allTestResultIds, testResultSystemNamesMap);
						}
					}
					
				}
				else if ( StringUtils.isNumeric(touchLevel) ) {
					BigDecimal id = null;
					for ( BigDecimal idKey : idMap.keySet() ) {
						if ( idMap.get(idKey).equals(new BigDecimal(touchLevel)) ) {
							id = idKey;
							break;
						}
					}
					
					if ( id == null ) {
						throw new Exception("Assembly not found for chassis serial number " + chassisSerialNum + ", with touch level " + touchLevel);
					}
					
					this.getTestResultIds(serv, id, allTestResultIds, testResultSystemNamesMap);
				}
				
	
				Collections.sort(allTestResultIds, new Util.BigDecimalDescendingComparator());
			
				//Only get the latest test results from each system for this chassis
				List<String> sysList = new ArrayList<String>();
				List<BigDecimal> filterDisplayTestResultIds = new ArrayList<BigDecimal>();
				for ( BigDecimal trId : allTestResultIds) {
					String sys = testResultSystemNamesMap.get(trId);
					if ( !sysList.contains(sys) ) {
						filterDisplayTestResultIds.add(trId);
						sysList.add(sys);
					}
				}
				for ( BigDecimal testResultId : filterDisplayTestResultIds ) {
					AstTestResult tr = trServ.getTestResultByTestResultId(testResultId);
					resultList.add(new DisplayTestResult(tr));
				}
				
			}
			else {
				throw new Exception("Chassis serial number not found: " + chassisSerialNum);
			}
			StringBuilder s = new StringBuilder();
			s.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><test_results_info>");
			for ( DisplayTestResult tr : resultList ) {
				s.append(tr.toTestXML());
			}
			s.append("</test_results_info>");
			returnXml = s.toString();
		}
		catch ( Exception ex ) {
			log.error("Could not generate xml. ", ex);
			returnXml = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><ast_response>" +
					"<status>" + AjaxResponse.RESPONSE_TYPE_FAIL + "</status>" +
					"<message> " + ex.getMessage()+ "</message>" +
				"</ast_response>";
		}
		return returnXml;
	}
	
	private void getTestResultIds(SysTestService serv, BigDecimal partAsmId, List<BigDecimal> testResultIdsList, Map<BigDecimal, String> testResultSystemNamesMap) throws Exception {
		AstPartAsm chassisAsm = serv.getAstPartAsmById(partAsmId, false, null);
		if ( chassisAsm != null ) {
			for ( Object o : chassisAsm.getAstTestResults() ) {
				AstTestResult tr = (AstTestResult)o;
				testResultIdsList.add(tr.getTestResultId());
				testResultSystemNamesMap.put(tr.getTestResultId(), tr.getTestSystemNm());
			}
		}
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

	//TODO: Need to set this up for autowire
	public SysTestService getSysTestService() {
		return new SysTestService();
	}
	
	//TODO: Need to set this up for autowire
	public SysTestResultService getSysTestResultService() {
		return new SysTestResultService();
	}
	
	public EvolveSalesOrderDetailService getEvolveSalesOrderDetailService() {
		return evolveSalesOrderDetailService;
	}

	@Autowired
	public void setEvolveSalesOrderDetailService(
			EvolveSalesOrderDetailService evolveSalesOrderDetailService) {
		this.evolveSalesOrderDetailService = evolveSalesOrderDetailService;
	}
	
}
