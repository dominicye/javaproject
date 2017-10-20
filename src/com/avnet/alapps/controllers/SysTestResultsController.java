package com.avnet.alapps.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.avnet.alapps.common.AjaxResponse;
import com.avnet.alapps.common.MailSender;
import com.avnet.alapps.model.alapps.AstPartAsm;
import com.avnet.alapps.model.alapps.AstPartAsmAttr;
import com.avnet.alapps.model.alapps.AstTestResult;
import com.avnet.alapps.model.alapps.AstTestResultCode;
import com.avnet.alapps.model.alapps.AstTestResultItem;
import com.avnet.alapps.security.SecurityService;
import com.avnet.alapps.security.SecurityUser;
import com.avnet.alapps.services.SysTestResultService;
import com.avnet.alapps.services.SysTestService;
import com.avnet.alapps.systest.model.TestSystemClientEnum;

import javax.servlet.http.HttpServletRequest;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URL;


@Controller
public class SysTestResultsController {
	private static Logger log = Logger.getLogger(SysTestResultsController.class);
	@Autowired private SecurityService securityService;
	@Autowired private MailSender mailSender;
	@Autowired private String tier;
	
	
	@RequestMapping(value="/systest/submitTestResultsForm.htm", method=RequestMethod.GET)
	public ModelAndView showForm() throws Exception {
		ModelAndView view = new ModelAndView("systest/submit_test_results");
		return view;
	}
	
	@Transactional(readOnly = false, timeout = 600)
	@RequestMapping(value="/systest/services/submitTestResultsData.xml", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String  submitResults(HttpServletRequest request, @RequestParam String xmlData) throws Exception {
		String xmlResponse = "";
	
		try{
			
			log.info("*** xmlData = [" + xmlData + "]");
			
			
			//Validate the input XML against the XSD
			
			/*
			String xsdUrl = null;
			if ( "local".equalsIgnoreCase(this.tier) ) {
				xsdUrl = "http://localhost:9084";
			}
			else if ( "dev".equalsIgnoreCase(this.tier) ) {
				xsdUrl = "http://alapps-dev.avnet.com";
			}
			else if ( "test".equalsIgnoreCase(this.tier) ) {
				xsdUrl = "http://alapps-test.avnet.com";
			}
			else  {
				xsdUrl = "http://alapps.avnet.com";
			}
			xsdUrl = xsdUrl + request.getContextPath() + "/resources/xml/ISTResponseSchema.xsd";
			//System.out.println("**** xsdUrl :" + xsdUrl);
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new URL(xsdUrl));
			*/
			
			InputStream xsdStream = SysTestResultsController.class.getClassLoader().getResourceAsStream("com/avnet/resources/xml/ISTResponseSchema.xsd");
			StreamSource xsdSource = new StreamSource(xsdStream);
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(xsdSource);
			
			try {
				schema.newValidator().validate(new StreamSource(new StringReader(xmlData)));
			}
			catch (SAXException e) {
				//throw new Exception("XML did not validate for XSD: " + xsdUrl);
				throw new Exception("Input XML did not validate for XSD: ISTResponseSchema.xsd");
			}
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    InputSource is = new InputSource(new StringReader(xmlData));
		    Document document = dBuilder.parse(is);
			document.getDocumentElement().normalize();
			//System.out.println("Root element: " + document.getDocumentElement().getNodeName());
			//String chassis_partnumber = document.getElementsByTagName("chassis_partnumber").item(0).getTextContent();
			String chassis_serialnumber = document.getElementsByTagName("chassis_serialnumber").item(0).getTextContent();
			String test_station = document.getElementsByTagName("test_station").item(0).getTextContent();
			String tester_name = document.getElementsByTagName("tester_name").item(0).getTextContent();
			String operator_id = document.getElementsByTagName("operator_id").item(0).getTextContent();
			String result_code = document.getElementsByTagName("result_code").item(0).getTextContent();
			String client = document.getElementsByTagName("client").item(0).getTextContent();
			
			
			
			//////////////////////////////////////////
			//TODO: Set this in database once you find out the agreed upon date-time string format from test clients
			// 21 Jun 2016 09:26:10 PM 
			// dd MMM YYYY KK:mm:ss a
			DateTimeFormatter dateFormat = DateTimeFormat.forPattern("dd MMM YYYY KK:mm:ss a"); 
			Date resultStart = null;
			NodeList startElement = document.getElementsByTagName("result_start");
			if ( startElement != null && startElement.getLength() > 0 ) {
				try {
					String dateString = startElement.item(0).getTextContent();
					resultStart = dateFormat.parseDateTime(dateString).toDate();
				}
				catch ( Exception e ) { } 
			}
			
			Date resultStop = null;
			NodeList stopElement = document.getElementsByTagName("result_stop");
			if ( stopElement != null && stopElement.getLength() > 0 ) {
				try {
					String dateString = stopElement.item(0).getTextContent();
					resultStop = dateFormat.parseDateTime(dateString).toDate();
				}
				catch ( Exception e ) { } 
			}
			//////////////////////////////////////////
			
			
			
			if ( client != null && client.trim().length() > 0 ) {
				client = client.trim();
				if ( !TestSystemClientEnum.getNameList().contains(client) ) {
					throw new Exception("Invalid parameter value '" + client + "': Valid clients = " + TestSystemClientEnum.getNameListString());
				}
			}
			else {
				throw new Exception("Invalid parameter value '" + client + "': Valid clients = " + TestSystemClientEnum.getNameListString());
			}
			TestSystemClientEnum testSystemClient = TestSystemClientEnum.getTestSystemClientByName(client);
			
			SysTestService serv = this.getSysTestService();
			AstPartAsm chassisAsm = null;
			Map<BigDecimal, Date> idMap = serv.getChassisPartAsmIdsWithCreateDateBySerialNumber(chassis_serialnumber);
			if ( idMap != null && !idMap.isEmpty() ) {
				BigDecimal id = (idMap.keySet().toArray(new BigDecimal[idMap.size()]))[0];
				chassisAsm = serv.getAstPartAsmById(id, false, null);
				if ( chassisAsm == null ) {
					throw new Exception("Assembly not found for chassis serial number " + chassis_serialnumber);
				}
			}
			else {
				throw new Exception("Assembly not found for chassis serial number " + chassis_serialnumber);
			}
			Date now = new Date();
	
			SysTestResultService rserv = this.getSysTestResultService();
			AstTestResultCode passCode = rserv.getTestResultCodeByName("PASS");
			AstTestResultCode failCode = rserv.getTestResultCodeByName("FAIL");
			AstTestResultCode abortCode = rserv.getTestResultCodeByName("ABORT");
			AstTestResultCode testHeaderCode = rserv.getTestResultCodeByName(result_code);

			AstTestResult testResult = new AstTestResult();
			testResult.setCreateDt(now);
			testResult.setOperatorId(operator_id);
			testResult.setTesterName(tester_name);
			testResult.setTestStation(test_station);
			testResult.setResultStart(resultStart);
			testResult.setResultStop(resultStop);
			testResult.setAstTestResultCode(testHeaderCode);
			testResult.setTestSystemNm(client);
			testResult.setAstPartAsm(chassisAsm);
			
			
			//Get chassis manufacturer serial number (non-Nutanix)
			String chassisMfrSerialNumber = null;
			for ( Object o : chassisAsm.getAstPartAsmAttrs() ) {
				AstPartAsmAttr cAttr = (AstPartAsmAttr)o;
				if ( "serialnumber".equalsIgnoreCase(cAttr.getAstPartAttr().getAstCompTypeAttr().getAttrNm()) ) {
					chassisMfrSerialNumber = cAttr.getValueTx();
					break;
				}
			}
	
			Map<String, List<AstTestResultItem>> componentResultItemsBySerialNumber 
				= new HashMap<String, List<AstTestResultItem>>();

	
			NodeList passResultNodes = document.getElementsByTagName("pass_result");
			for ( int i = 0; i < passResultNodes.getLength(); i++ ) {
				if ( passResultNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element)passResultNodes.item(i);
					String test_name = elem.getElementsByTagName("test_name").item(0).getTextContent();
					String test_description = elem.getElementsByTagName("test_description").item(0).getTextContent();
					String component_type = elem.getElementsByTagName("component_type").item(0).getTextContent();
					String component_location = elem.getElementsByTagName("component_location").item(0).getTextContent();
					//String component_partnumber = elem.getElementsByTagName("component_partnumber").item(0).getTextContent();
					String component_serialnumber = elem.getElementsByTagName("component_serialnumber").item(0).getTextContent();
					String component_extracted_value = elem.getElementsByTagName("component_extracted_value").item(0).getTextContent();
					
					this.validateTestResultRecord(component_type, component_location, component_serialnumber);
					
					//System.out.println("pass_result->test_name="+test_name);
					AstTestResultItem testResultItem = new AstTestResultItem();
					//testResultItem.setTestResultItemId(id); //Needs to be set by sequence service
					testResultItem.setCreateDt(now);
					testResultItem.setAstTestResult(testResult);
					testResultItem.setAstTestResultCode(passCode);
					testResultItem.setResultCodeNm(passCode.getCodeNm());
					testResultItem.setResultCodeDs(passCode.getCodeDs());
					testResultItem.setTestDs(test_description);
					testResultItem.setTestNm(test_name);
					testResultItem.setExtractedVal(component_extracted_value);
					//testResultItem.setAstPartAsm(astPartAsm); //We loop through asms and set this below
					

					if ( "NODE".equalsIgnoreCase(component_type) ) {

						if ( componentResultItemsBySerialNumber.containsKey(component_location) ) {
							componentResultItemsBySerialNumber.get(component_location).add(testResultItem);
						}
						else  {
							List<AstTestResultItem> triList = new LinkedList<AstTestResultItem>();
							triList.add(testResultItem);
							componentResultItemsBySerialNumber.put(component_location, triList);
						}
					}
					else {

						if ( componentResultItemsBySerialNumber.containsKey(component_serialnumber) ) {
							componentResultItemsBySerialNumber.get(component_serialnumber).add(testResultItem);
						}
						else  {
							List<AstTestResultItem> triList = new LinkedList<AstTestResultItem>();
							triList.add(testResultItem);
							componentResultItemsBySerialNumber.put(component_serialnumber, triList);
						}
					}
	
					
				}
			}
			
			NodeList failResultNodes = document.getElementsByTagName("fail_result");
			for ( int i = 0; i < failResultNodes.getLength(); i++ ) {
				if ( failResultNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element)failResultNodes.item(i);
					String test_name = elem.getElementsByTagName("test_name").item(0).getTextContent();
					String test_description = elem.getElementsByTagName("test_description").item(0).getTextContent();
					String failure_code = elem.getElementsByTagName("fail_code").item(0).getTextContent();
					String failure_description = elem.getElementsByTagName("fail_description").item(0).getTextContent();
					String component_type = elem.getElementsByTagName("component_type").item(0).getTextContent();
					String component_location = elem.getElementsByTagName("component_location").item(0).getTextContent();
					//String component_partnumber = elem.getElementsByTagName("component_partnumber").item(0).getTextContent();
					String component_serialnumber = elem.getElementsByTagName("component_serialnumber").item(0).getTextContent();
					String component_extracted_value = elem.getElementsByTagName("component_extracted_value").item(0).getTextContent();
					
					this.validateTestResultRecord(component_type, component_location, component_serialnumber);
					
					//System.out.println("fail_result->test_name="+test_name);
					AstTestResultItem testResultItem = new AstTestResultItem();
					//testResultItem.setTestResultItemId(id); //Needs to be set by sequence service
					testResultItem.setCreateDt(now);
					testResultItem.setAstTestResult(testResult);
					
					if ( failure_code.toUpperCase().contains("ABORT") ) {
						testResultItem.setAstTestResultCode(abortCode);
					}
					else {
						testResultItem.setAstTestResultCode(failCode);
					}
					
					testResultItem.setResultCodeNm(failure_code);
					testResultItem.setResultCodeDs(failure_description);
					testResultItem.setTestDs(test_description);
					testResultItem.setTestNm(test_name);
					testResultItem.setExtractedVal(component_extracted_value);
					//testResultItem.setAstPartAsm(astPartAsm); //We loop through asms and set this below
					
					
					if ( "NODE".equalsIgnoreCase(component_type) ) {
						if ( componentResultItemsBySerialNumber.containsKey(component_location) ) {
							componentResultItemsBySerialNumber.get(component_location).add(testResultItem);
						}
						else  {
							List<AstTestResultItem> triList = new LinkedList<AstTestResultItem>();
							triList.add(testResultItem);
							componentResultItemsBySerialNumber.put(component_location, triList);
						}
					}
					else {
						if ( componentResultItemsBySerialNumber.containsKey(component_serialnumber) ) {
							componentResultItemsBySerialNumber.get(component_serialnumber).add(testResultItem);
						}
						else  {
							List<AstTestResultItem> triList = new LinkedList<AstTestResultItem>();
							triList.add(testResultItem);
							componentResultItemsBySerialNumber.put(component_serialnumber, triList);
						}
					}

				}
			}
			

			//Also check chassis, or does it have test results other than header?
			if ( componentResultItemsBySerialNumber.containsKey(chassisMfrSerialNumber) ) {
				for ( AstTestResultItem item : componentResultItemsBySerialNumber.get(chassisMfrSerialNumber) ) {
					item.setAstPartAsm(chassisAsm);
				}
			}
			
			//Check chassis parts in assembly. Loop though to link part info to serials/locations and asms and set missing serials to abort test result item
			for ( Object p1 : chassisAsm.getAstPartAsms() ) { //Chassis parts
				AstPartAsm chassisPartAsm = (AstPartAsm)p1;
				
				if ( "NODE".equalsIgnoreCase(chassisPartAsm.getAstPart().getAstCompType().getTypeNm()) ) {
					
					//Do nodes have test results? If so, add those results to a chassis node map, since they have no serial numbers
					//looks like actual nodes will not be tested, so no abort should be logged if missing
					/*
					for ( Object a1 : chassisPartAsm.getAstPartAsmAttrs() ) { //Chassis nodes
						AstPartAsmAttr nodeAsmAttr =  (AstPartAsmAttr)a1;
						String attrNm = nodeAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAttrNm();
						if ( "location".equalsIgnoreCase(attrNm) ) {
							String location = nodeAsmAttr.getValueTx();
							if ( componentResultItemsBySerialNumber.containsKey(location) ) {
								for ( AstTestResultItem item : componentResultItemsBySerialNumber.get(location) ) {
									item.setAstPartAsm(chassisPartAsm);
								}
							}
							// Never enter an abort if any test system has a missing node PASS/FAIL/ABORT record
							//else {
							//	AstTestResultItem testResultItem = new AstTestResultItem();
							//	testResultItem.setTestResultItemId(id); //Needs to be set by sequence service
							//	testResultItem.setCreateDt(now);
							//	testResultItem.setAstTestResultCode(abortCode);
							//	testResultItem.setResultCodeNm(abortCode.getCodeNm());
							//	testResultItem.setResultCodeDs(abortCode.getCodeDs());
							//	testResultItem.setTestDs(null);
							//	testResultItem.setTestNm(null);
							//	testResultItem.setAstPartAsm(chassisPartAsm); 
							//	List<AstTestResultItem> triList = new LinkedList<AstTestResultItem>();
							//	triList.add(testResultItem);
							//	componentResultItemsBySerialNumber.put(location, triList);
							//}
							//
							break;
						}
					}
					*/
	
					for ( Object p2 : chassisPartAsm.getAstPartAsms() ) { //Chassis node parts
						AstPartAsm chassisNodePartAsm = (AstPartAsm)p2;
						
						for ( Object a2 : chassisNodePartAsm.getAstPartAsmAttrs() ) {
							AstPartAsmAttr chassisNodePartAsmAttr =  (AstPartAsmAttr)a2;
							String attrNm = chassisNodePartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAttrNm();
							if ( "serialnumber".equalsIgnoreCase(attrNm) ) {
								String serialnumber = chassisNodePartAsmAttr.getValueTx();
								if ( componentResultItemsBySerialNumber.containsKey(serialnumber) ) {
									for ( AstTestResultItem item : componentResultItemsBySerialNumber.get(serialnumber) ) {
										item.setAstPartAsm(chassisNodePartAsm);
									}
								}
								else if ( testSystemClient.isUseAbortLogic() ) { //Do this for IST only
									AstTestResultItem testResultItem = new AstTestResultItem();
									//testResultItem.setTestResultItemId(id); //Needs to be set by sequence service
									testResultItem.setCreateDt(now);
									testResultItem.setAstTestResult(testResult);
									testResultItem.setAstTestResultCode(abortCode);
									testResultItem.setResultCodeNm(abortCode.getCodeNm());
									testResultItem.setResultCodeDs(abortCode.getCodeDs());
									testResultItem.setTestDs(null);
									testResultItem.setTestNm(null);
									testResultItem.setAstPartAsm(chassisNodePartAsm); 
									List<AstTestResultItem> triList = new LinkedList<AstTestResultItem>();
									triList.add(testResultItem);
									componentResultItemsBySerialNumber.put(serialnumber, triList);
								}
								break;
							}
						}
					}
				}
				else {
					for ( Object a1 : chassisPartAsm.getAstPartAsmAttrs() ) { //Chassis parts
						AstPartAsmAttr chassisPartAsmAttr =  (AstPartAsmAttr)a1;

						String attrNm = chassisPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAttrNm();
						if ( "serialnumber".equalsIgnoreCase(attrNm) ) {
							String serialnumber = chassisPartAsmAttr.getValueTx();
							if ( componentResultItemsBySerialNumber.containsKey(serialnumber) ) {
								for ( AstTestResultItem item : componentResultItemsBySerialNumber.get(serialnumber) ) {
									item.setAstPartAsm(chassisPartAsm);
								}
							}
							else if ( testSystemClient.isUseAbortLogic() ) { //Do this for IST only
								AstTestResultItem testResultItem = new AstTestResultItem();
								//testResultItem.setTestResultItemId(id); //Needs to be set by sequence service
								testResultItem.setCreateDt(now);
								testResultItem.setAstTestResult(testResult);
								testResultItem.setAstTestResultCode(abortCode);
								testResultItem.setResultCodeNm(abortCode.getCodeNm());
								testResultItem.setResultCodeDs(abortCode.getCodeDs());
								testResultItem.setTestDs(null);
								testResultItem.setTestNm(null);
								testResultItem.setAstPartAsm(chassisPartAsm); 
								List<AstTestResultItem> triList = new LinkedList<AstTestResultItem>();
								triList.add(testResultItem);
								componentResultItemsBySerialNumber.put(serialnumber, triList);
			
							}
							break;
						}
					}
				}
			}
			//Send test results to service for storage in db and get their sequences
			rserv.saveTestResults(testResult, componentResultItemsBySerialNumber); 

			xmlResponse = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><ast_response>" +
					"<status>" + AjaxResponse.RESPONSE_TYPE_SUCCESS + "</status>" +
					"<message></message>" +
				"</ast_response>";
		} 
		catch (Exception ex) {
			log.error("Could not submit test results xml. ", ex);
			xmlResponse = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><ast_response>" +
					"<status>" + AjaxResponse.RESPONSE_TYPE_FAIL + "</status>" +
					"<message> Could not submit test results xml: " + ex.getMessage()+ "</message>" +
				"</ast_response>";
		}
		
		return xmlResponse;
	}
	
	private void validateTestResultRecord(String component_type, String component_location, String component_serialnumber) throws Exception {
		
		if ( component_type == null || component_type.trim().length() == 0 ) {
			throw new Exception("At least one submitted test result does not provide a component type. All test results rejected.");
		}
		else if ( "NODE".equalsIgnoreCase(component_type) && (component_location == null || component_location.trim().length() == 0) ) {
			throw new Exception("At least one submitted test result for component type '" + component_type + "' does not provide a component location. All test results rejected.");
		}
		else if ( component_serialnumber == null || component_serialnumber.trim().length() == 0 ) {
			throw new Exception("At least one submitted test result for component type '" + component_type + "' does not provide a component serialnumber. All test results rejected.");
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
	
	
}
