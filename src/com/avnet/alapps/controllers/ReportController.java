package com.avnet.alapps.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.avnet.alapps.common.MailSender;
import com.avnet.alapps.common.Util;
import com.avnet.alapps.forms.GSFCFinancialReportForm;
import com.avnet.alapps.model.alapps.AstCompType;
import com.avnet.alapps.model.alapps.AstPart;
import com.avnet.alapps.model.alapps.AstPartAsm;
import com.avnet.alapps.model.alapps.AstPartAsmAttr;
import com.avnet.alapps.model.dbconnect.CarrierCode;
import com.avnet.alapps.model.dbconnect.Integration;
import com.avnet.alapps.model.dbconnect.ItsPart;
import com.avnet.alapps.model.dbconnect.Orderaddress;
import com.avnet.alapps.model.dbconnect.Orderheader;
import com.avnet.alapps.report.ReportExportTypeEnum;
import com.avnet.alapps.report.ReportService;
import com.avnet.alapps.report.model.FiscalDateRange;
import com.avnet.alapps.report.model.ProgramRevenueVolumeLocation;
import com.avnet.alapps.report.model.RevenueVolumeLocation;
import com.avnet.alapps.report.model.RevenueVolumeLocationCustomerDivision;
import com.avnet.alapps.security.SecurityService;
import com.avnet.alapps.security.SecurityUser;
import com.avnet.alapps.services.EvolveSalesOrderDetailService;
import com.avnet.alapps.services.GSFCFinancialReportService;
import com.avnet.alapps.services.RedPrairieService;
import com.avnet.alapps.services.SysTestService;
import com.avnet.alapps.systest.model.NutanixCustomerPackingSlipLine;
import com.avnet.alapps.systest.model.NutanixPackingSlip;
import com.avnet.alapps.systest.model.NutanixPackingSlipLine;
import com.avnet.alapps.systest.model.TestResultReportItem;

@Controller
public class ReportController {
	private static Logger log = Logger.getLogger(ReportController.class);
	@Autowired private SecurityService securityService;
	@Autowired private MailSender mailSender;
	@Autowired private String tier;
	@Autowired private RedPrairieService redprairieService;
	@Autowired private EvolveSalesOrderDetailService evolveSalesOrderDetailService;
	
	
	private static Map<String, FiscalDateRange> fiscalMonthMap = null;
	public static final String ACCOUNTING_REPORT_USER = "ACCRP";
	
	static {
		fiscalMonthMap = new LinkedHashMap<String, FiscalDateRange>();
		/*
		fiscalMonthMap.put("FY16-July", new FiscalDateRange("28-JUN-15", "28-JUN-15", "01-AUG-15"));
		fiscalMonthMap.put("FY16-August", new FiscalDateRange("28-JUN-15", "02-AUG-15", "29-AUG-15"));
		fiscalMonthMap.put("FY16-September", new FiscalDateRange("28-JUN-15", "30-AUG-15", "03-OCT-15"));
		fiscalMonthMap.put("FY16-October", new FiscalDateRange("28-JUN-15", "04-OCT-15", "31-OCT-15"));
		fiscalMonthMap.put("FY16-November", new FiscalDateRange("28-JUN-15", "01-NOV-15", "28-NOV-15"));
		fiscalMonthMap.put("FY16-December", new FiscalDateRange("28-JUN-15", "29-NOV-15", "02-JAN-16"));
		fiscalMonthMap.put("FY16-January", new FiscalDateRange("28-JUN-15", "03-JAN-16", "30-JAN-16"));
		fiscalMonthMap.put("FY16-February", new FiscalDateRange("28-JUN-15", "31-JAN-16", "27-FEB-16"));
		fiscalMonthMap.put("FY16-March", new FiscalDateRange("28-JUN-15", "28-FEB-16", "02-APR-16"));
		fiscalMonthMap.put("FY16-April", new FiscalDateRange("28-JUN-15", "03-APR-16", "30-APR-16"));
		fiscalMonthMap.put("FY16-May", new FiscalDateRange("28-JUN-15", "01-MAY-16", "28-MAY-16"));
		*/
		fiscalMonthMap.put("FY16-June", new FiscalDateRange("28-JUN-15", "29-MAY-16", "02-JUL-16"));
		fiscalMonthMap.put("FY17-July", new FiscalDateRange("03-JUL-16", "03-JUL-16", "30-JUL-16"));
		fiscalMonthMap.put("FY17-August", new FiscalDateRange("03-JUL-16", "31-JUL-16", "27-AUG-16"));
		fiscalMonthMap.put("FY17-September", new FiscalDateRange("03-JUL-16", "28-AUG-16", "01-OCT-16"));
		fiscalMonthMap.put("FY17-October", new FiscalDateRange("03-JUL-16", "02-OCT-16", "29-OCT-16"));
		fiscalMonthMap.put("FY17-November", new FiscalDateRange("03-JUL-16", "30-OCT-16", "26-NOV-16"));
		fiscalMonthMap.put("FY17-December", new FiscalDateRange("03-JUL-16", "27-NOV-16", "31-DEC-16"));
		fiscalMonthMap.put("FY17-January", new FiscalDateRange("03-JUL-16", "01-JAN-17", "28-JAN-17"));
		fiscalMonthMap.put("FY17-February", new FiscalDateRange("03-JUL-16", "29-JAN-17", "25-FEB-17"));
		fiscalMonthMap.put("FY17-March", new FiscalDateRange("03-JUL-16", "26-FEB-17", "01-APR-17"));
		fiscalMonthMap.put("FY17-April", new FiscalDateRange("03-JUL-16", "02-APR-17", "29-APR-17"));
		fiscalMonthMap.put("FY17-May", new FiscalDateRange("03-JUL-16", "30-APR-17", "27-MAY-17"));
		fiscalMonthMap.put("FY17-June", new FiscalDateRange("03-JUL-16", "28-MAY-17", "01-JUL-17"));
		
	}
	
	public class NutanixChassisLine {
		public String nutanixPartNumber = null;
		public String nutanixSerialNumber = null;
		public String description = null;
		public BigDecimal unitDetailId = null;
		Map<String, NutanixPartLine> partMap = new LinkedHashMap<String, NutanixPartLine>();
	}
	
	public static class NutanixChassisLineComparator implements Comparator<NutanixChassisLine> {
	    @Override
	    public int compare(NutanixChassisLine o1, NutanixChassisLine o2) {
	    	return o1.nutanixPartNumber.compareTo(o2.nutanixPartNumber);
	    }
	}
	
	public class NutanixPartLine {
		public String partNumber = null;
		//public String serialNumbers = null;
		public List<String> serialNumberList = new ArrayList<String>();
		public String description = null;
		public long quantity = 0;
	}
	
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value = "/report/gsfcFinancialReportResults.htm", method = RequestMethod.POST) 
	public String gsfcFinancialReportResults(@ModelAttribute("reportForm") GSFCFinancialReportForm reportForm,
												Map<String, Object> model,
												HttpServletRequest request, 
												HttpServletResponse response
												) throws Exception {

		try {
			ReportService rs = new ReportService();
			GSFCFinancialReportService reportService = new GSFCFinancialReportService(); 
			
			FiscalDateRange range = fiscalMonthMap.get(reportForm.getFiscalMonth());
			
			log.info("ProgramRevenueVolumeLocation " + reportForm.getFiscalMonth());
			List<ProgramRevenueVolumeLocation> beanList1 = reportService.getProgramRevenueVolumeLocationReport(range.getMonthStart(), range.getMonthEnd());
			
			log.info("RevenueVolumeLocation " + reportForm.getFiscalMonth());
			List<RevenueVolumeLocation> beanList2 = reportService.getRevenueVolumeLocationReport(range.getMonthStart(), range.getMonthEnd());
			
			log.info("RevenueVolumeLocationCustomerDivision " + reportForm.getFiscalMonth());
			List<RevenueVolumeLocationCustomerDivision> beanList3 = reportService.getRevenueVolumeLocationCustomerDivisionReport(range.getMonthStart(), range.getMonthEnd());
			
			log.info("ProgramRevenueVolumeLocation YTD " + reportForm.getFiscalMonth());
			List<ProgramRevenueVolumeLocation> beanList4 = reportService.getProgramRevenueVolumeLocationReport(range.getYearToDateStart(), range.getMonthEnd());
			
			log.info("RevenueVolumeLocation YTD " + reportForm.getFiscalMonth());
			List<RevenueVolumeLocation> beanList5 = reportService.getRevenueVolumeLocationReport(range.getYearToDateStart(), range.getMonthEnd());
			
			log.info("RevenueVolumeLocationCustomerDivision YTD " + reportForm.getFiscalMonth());
			List<RevenueVolumeLocationCustomerDivision> beanList6 = reportService.getRevenueVolumeLocationCustomerDivisionReport(range.getYearToDateStart(), range.getMonthEnd());
			
			byte[] outputArray = rs.generateAllFinancialReports(beanList1, beanList2, beanList3, beanList4, beanList5, beanList6, ReportExportTypeEnum.XLS, request);
			
			//byte[] outputArray = rs.generateProgramRevenueVolumeLocationReport(beanList1, ReportExportTypeEnum.XLS, request);
			//byte[] outputArray = rs.generateRevenueVolumeLocationReport(beanList2, ReportExportTypeEnum.XLS, request);
			//byte[] outputArray = rs.generateRevenueVolumeLocationCustomerDivisionReport(beanList3, ReportExportTypeEnum.XLS, request);

			rs.prepareResponseHeaderForJasperReport(response, "GSFC-" + reportForm.getFiscalMonth(), ReportExportTypeEnum.XLS);
			response.getOutputStream().write(outputArray);
			
		} 
		catch (Exception ex) {
			log.error("Could not execute ProgramRevenueVolumeLocationReport.", ex);
			throw ex;
		}
		return null;
	}

	
	@Transactional(readOnly = false, timeout = 3600)
	@RequestMapping(value = "/util/updateAstCustomerOrderLine.htm", method = RequestMethod.GET) 
	public String updateAstCustomerOrderLine(
			HttpServletRequest request, 
			HttpServletResponse response
				) throws Exception {
		
		response.getOutputStream().print("<html><head></head><body>START!<br>");
		SysTestService serv = new SysTestService();
		List<BigDecimal> idList = serv.getAllSAPAssemblyIds(null);
		if ( idList == null || idList.size() == 0 ) {
			response.getOutputStream().print("<br><font color=\"red\">ERROR</font>|No records returned");
			response.getOutputStream().print("<br><br>DONE!</body></html>");
			response.getOutputStream().flush();
			return null;
		}
		
		int totalRecords = 0;
		int totalUpdates = 0;
		int totalMissingAttr = 0;
		int totalMissingItsPart = 0;
		int totalMissingItsIntegration = 0;
		int totalMissingSapZzCustRef = 0;
		
		for ( BigDecimal id : idList ) { 			
			AstPartAsm asm = serv.getAstPartAsmById(id, false, null);
			if ( asm.getAstPartAsmAttrs() != null && asm.getTouchLevel().intValue() == 2 ) {
				totalRecords++;
				String productionOrder = null;
				String topLevelSerialNumber = null;
				AstPartAsmAttr customerorderlinenumberAttr = null; 
				for ( Object attrObj : asm.getAstPartAsmAttrs().toArray() ) {
					AstPartAsmAttr attr = (AstPartAsmAttr)attrObj;
					String attrName = attr.getAstPartAttr().getAstCompTypeAttr().getAttrNm();
					if ( "nutanixserialnumber".equalsIgnoreCase(attrName) ) {
						topLevelSerialNumber = attr.getValueTx();
					}
					else if ( "ICN".equalsIgnoreCase(attrName)  ) {
						productionOrder = attr.getValueTx();
					}
					else if ( "customerorderlinenumber".equalsIgnoreCase(attrName) ) {
						customerorderlinenumberAttr = attr;
					}
					if ( topLevelSerialNumber != null && productionOrder != null && customerorderlinenumberAttr != null ) { break; }
				}
				
				if ( customerorderlinenumberAttr == null ) {
					totalMissingAttr++;
					response.getOutputStream().print("<br><font color=\"red\">ERROR</font>|Missing attribute for CUSTOMER ORDER LINE|topLevelSerialNumber=" + topLevelSerialNumber + "|productionOrder=" + productionOrder);
					response.getOutputStream().flush();	
					continue;
				}
				
				String salesOrder = null;
				Integration integration = serv.getIntegrationByICN(productionOrder);
				if ( integration != null ) {
					salesOrder = integration.getId().getItsScn();							
					if ( !salesOrder.startsWith("2") ) { //These are incorrect and should not be processed	
						ItsPart topLevelChassisPart = serv.getItsTopLevelPart(topLevelSerialNumber, productionOrder);
						if ( topLevelChassisPart == null ) {
							totalMissingItsPart++;
							response.getOutputStream().print("<br><font color=\"red\">ERROR</font>|TOP-LEVEL PART record missing in ITS|topLevelSerialNumber=" + topLevelSerialNumber + "|productionOrder=" + productionOrder);
							response.getOutputStream().flush();
							continue;
						}
						else {
							String sapMaterialNo = topLevelChassisPart.getSapMaterialNo();
							String customerOrderLineNumber = 
								evolveSalesOrderDetailService.getZzCustRefBySalesOrderAndLineNumber(salesOrder, sapMaterialNo);
							if ( StringUtils.trimToNull(customerOrderLineNumber) == null ) {
								totalMissingSapZzCustRef++;
								response.getOutputStream().print(
										"<br><font color=\"red\">ERROR</font>|CUSTOMER ORDER LINE missing in SAP|sapMaterialNo=" + sapMaterialNo + "|topLevelSerialNumber=" + topLevelSerialNumber + 
										"|productionOrder=" + productionOrder + "|salesOrder=" + salesOrder
										);
								response.getOutputStream().flush();
								continue;
							}
							else {
								customerorderlinenumberAttr.setValueTx(customerOrderLineNumber);
								totalUpdates++;
								response.getOutputStream().print(
										"<br><font color=\"green\">SUCCESS</font>|Updated|sapMaterialNo=" + sapMaterialNo + "|customerOrderLineNumber=" + customerOrderLineNumber +
										"|topLevelSerialNumber=" + topLevelSerialNumber + "|productionOrder=" + productionOrder + "|salesOrder=" + salesOrder
										);
								response.getOutputStream().flush();
								continue;
							}
						}
					}
				}
				else {
					totalMissingItsIntegration++;
					response.getOutputStream().print("<br><font color=\"red\">ERROR</font>|INTEGRATION record missing in ITS|topLevelSerialNumber=" + topLevelSerialNumber + "|productionOrder=" + productionOrder);
					response.getOutputStream().flush();
					continue;
				}
			}
		}	
		response.getOutputStream().print("<br><br>-----------------------------------------------------------");
		response.getOutputStream().print("<br>totalRecords = " + totalRecords);
		response.getOutputStream().print("<br>totalUpdates = " + totalUpdates);
		response.getOutputStream().print("<br>totalMissingAttr = " + totalMissingAttr);
		response.getOutputStream().print("<br>totalMissingItsPart = " + totalMissingItsPart);
		response.getOutputStream().print("<br>totalMissingItsIntegration = " + totalMissingItsIntegration);
		response.getOutputStream().print("<br>totalMissingSapZzCustRef = " + totalMissingSapZzCustRef);
		response.getOutputStream().print("<br><br>DONE!</body></html>");
		response.getOutputStream().flush();
		return null;
	}
	
	
	
	@Transactional(readOnly = false, timeout = 3600)
	@RequestMapping(value = "/util/massUpdateSecondaryNicMacAddresses.htm", method = RequestMethod.GET) 
	public String updateSecodaryMacAddresses(
			HttpServletRequest request, 
			HttpServletResponse response
				) throws Exception {
		
		long errorCount = 0;
		long updateCount = 0;
		long skipCount = 0;
		
		response.getOutputStream().print("<html><head></head><body><pre><br>");
		
		SysTestService serv = new SysTestService();
		AstCompType nicComponentType = serv.getComponentTypeByName("nic") ;
		List<AstPart> partList = serv.getParts(nicComponentType.getCompTypeId(), false, "keyValue", "ASC");
		for (AstPart part : partList ) {
			response.getOutputStream().print("<br><br>PART: " + part.getKeyValue());
			response.getOutputStream().flush();
			for ( Object pasm : part.getAstPartAsms() ) {
				int portCount = 0;
				AstPartAsm partAsm = (AstPartAsm)pasm;
				Map<String, AstPartAsmAttr> macAttrUpdateMap = new HashMap<String, AstPartAsmAttr>();
				for ( Object pasmattr : partAsm.getAstPartAsmAttrs() ) {
					AstPartAsmAttr partAsmAttr = (AstPartAsmAttr)pasmattr;
					String attrName = partAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAttrNm();
					if ( "portcount".equals(attrName) ) {
						portCount = Integer.parseInt(partAsmAttr.getValueTx());
					}
					else if ( "mac1".equals(attrName) || "mac2".equals(attrName) || "mac3".equals(attrName) || "mac4".equals(attrName) ) {
						macAttrUpdateMap.put(attrName, partAsmAttr);
					}
				}
				
				AstPartAsmAttr mac1Attr = macAttrUpdateMap.get("mac1");
				if ( portCount > 1 && mac1Attr.getValueTx() != null && mac1Attr.getValueTx().trim().length() > 0 ) {
					
					try {
						List<String> macList = Util.generateMacs(mac1Attr.getValueTx(), portCount);
						int portIdx = 0;
						for ( String macString : macList ) {
							portIdx++;
							AstPartAsmAttr updateMacAttr = macAttrUpdateMap.get("mac" + String.valueOf(portIdx));
	
							if ( updateMacAttr.getValueTx() == null || updateMacAttr.getValueTx().length() == 0 ) { 
								updateMacAttr.setValueTx(macString); //Hibernate automatically updates the DB on bean set!
								//serv.updateAstPartAsmAttr(updateMacAttr);
								response.getOutputStream().print("<br>UPDATED: " + "mac" + String.valueOf(portIdx) + " = " + macString);
								response.getOutputStream().flush();
								updateCount++;
							}
							else {
								response.getOutputStream().flush();
							}
						}
					}
					catch ( Exception e ) {
						errorCount++;
						response.getOutputStream().print("<br>UPDATE ERROR: " + e.getMessage());
						response.getOutputStream().flush();
						e.printStackTrace();
					}
					
				}
				else {
					skipCount++;
				}
			}
		}
		response.getOutputStream().print("<br><br>UPDATE COUNT: " + String.valueOf(updateCount));
		response.getOutputStream().print("<br>ERROR COUNT: " + String.valueOf(errorCount));
		response.getOutputStream().print("<br>SKIPPED COUNT: " + String.valueOf(skipCount));
		response.getOutputStream().print("<br><br>DONE!</pre></body></html>");
		response.getOutputStream().flush();
		return null;
	}
	
	
	@Transactional(readOnly = false, timeout = 3600)
	@RequestMapping(value = "/util/updateAstOrderInfoTableForMiddleware.htm", method = RequestMethod.GET) 
	public String updateAstOrderInfoTableForMiddleware(
			HttpServletRequest request, 
			HttpServletResponse response
				) throws Exception {
		
		response.getOutputStream().print("<html><head></head><body><br>");

	
		SysTestService serv = new SysTestService();
		
		
		List<NutanixPackingSlip> slipList = serv.getSlipsWithMissingWaybill(null);
		if ( slipList == null || slipList.size() == 0 ) {
			response.getOutputStream().print("*********** updateAstOrderInfoTableForMiddleware: No slips returned with missing waybill");
			response.getOutputStream().print("<br><br>DONE!</body></html>");
			response.getOutputStream().flush();
			return null;
		}
		response.getOutputStream().print("<br><br>Number of records with missing waybill: " + slipList.size());
		//List<String> icnList = serv.getDistinctICNList();
		for ( NutanixPackingSlip slip : slipList ) { 	
		//for ( String icn : icnList ) {
			response.getOutputStream().print("<br><br>ICN: " + slip.getProductionOrderNumber());
			log.info("*********** updateAstOrderInfoTableForMiddleware ICN=" + slip.getProductionOrderNumber());
			response.getOutputStream().flush();
			//NutanixPackingSlip slip = new NutanixPackingSlip();
			//slip.setProductionOrderNumber(icn);
			
			Integration integration = serv.getIntegrationByICN(slip.getProductionOrderNumber());
			if ( integration != null ) {
				
				//if ( integration.getId().getItsScn().startsWith("2") ) {
				//	log.info("*********** updateAstOrderInfoTableForMiddleware SKIPPING SCN=" + integration.getId().getItsScn());
				//	continue;
				//}
				
				slip.setNutanixOrderNumber(integration.getItsCustpono());
				slip.setSalesOrderNumber(integration.getId().getItsScn());
				response.getOutputStream().print("<br>SCN: " + integration.getId().getItsScn());
				log.info("*********** updateAstOrderInfoTableForMiddleware SCN=" + integration.getId().getItsScn());
				response.getOutputStream().flush();
				
				if ( slip.getProductionOrderNumber().startsWith("2") ) { //Evolve ICN				
					this.getEvolveSalesOrderDetailService().getSalesOrderForNutanixPackingSlip(slip);
					if ( slip.getNutanixPackingSlipWaybills() != null ) {
						response.getOutputStream().print("<br>Waybill record count: " + slip.getNutanixPackingSlipWaybills().size());
					}
					serv.updateAstOrderInfoFromSlip(slip);
				}
				/*
				else if ( icn.startsWith("P") ) { //Genesis ICN
				
					Orderaddress oa = serv.getGenesisOrderaddressBySCN(integration.getScn());
					if ( oa != null ) {
						response.getOutputStream().print("<br>Used SCN to get ADDRESS data from GENESIS_OWN: scn= " + integration.getScn());
						slip.setShipToName(oa.getShiptoname());
						slip.setShipToAddress1(oa.getShiptoaddress1());
						slip.setShipToAddress2(oa.getShiptoaddress2());
						slip.setShipToCity(oa.getShiptocity());
						slip.setShipToState(oa.getShiptostateprovince());
						slip.setShipToZip(oa.getShiptopostalcode());
						slip.setShipToCountry(oa.getShiptocountry());
					}
					else {
						response.getOutputStream().print("<br>Used INTEGRATION table from ITS");
						slip.setShipToName(integration.getItsShiptoName());
						slip.setShipToAddress1(integration.getItsShiptoAddr1());
						slip.setShipToAddress2(integration.getItsShiptoAddr2());
						slip.setShipToCity(integration.getItsShiptoCity());
						slip.setShipToState(integration.getItsShiptoState());
						slip.setShipToZip(integration.getItsShiptoZip());
						slip.setShipToCountry(integration.getItsShiptoCountry());
					}
			
					Orderheader oh = serv.getGenesisOrderheaderBySCN(integration.getScn());
					if ( oh != null ) {
						response.getOutputStream().print("<br>Used SCN to get CONTACT data from GENESIS_OWN: scn= " + integration.getScn());
						slip.setCustomerOrderNumber(oh.getCustspecdesc());
						slip.setShipToContactPhone(oh.getBuyerphoneno());
						slip.setShipToContactName(oh.getBuyerName()); 
					}
	
					serv.updateAstOrderInfoFromSlip(slip);
				}
				*/
				
			}
			else {
				response.getOutputStream().print("<br>INTEGRATION record missing in ITS: icn= " + slip.getProductionOrderNumber());
				response.getOutputStream().flush();
			}
		}	
	
		response.getOutputStream().print("<br><br>DONE!</body></html>");
		response.getOutputStream().flush();
		return null;
	}

	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value = "/report/nutanixPackingSlipResults.htm", method = RequestMethod.POST) 
	public String nutanixPackingSlipResults(
			@RequestParam String scn, 
			@RequestParam String type, 
			@RequestParam(value = "shipToContactName", defaultValue = "") final String shipToContactName,
			@RequestParam(value = "ship99Flag", defaultValue = "off") final String ship99Flag,
			HttpServletRequest request, 
			HttpServletResponse response
				) throws Exception {
		try {
			//icn = icn.trim();
			scn = scn.trim();
			
			//boolean isShip99 = ( "off".equalsIgnoreCase(ship99Flag) ) ? false : true;
			boolean isPickPackShip = "PPS".equalsIgnoreCase(type) ;
			ReportService rs = new ReportService();
			SysTestService serv = new SysTestService();
			NutanixPackingSlip slip = null;
			
			if  ( isPickPackShip ) {
				slip = this.redprairieService.getNutanixPackingSlipLines(scn); 
				if ( slip != null ) {
					this.getEvolveSalesOrderDetailService().getSalesOrderForNutanixPackingSlip(slip);
					
					if ( StringUtils.trimToNull(slip.getShipToCountry()) != null ) {
						String countryName = serv.getCountryNameByCountryCode(slip.getShipToCountry());
						if ( countryName != null ) {
							slip.setShipToCountry(countryName);
						}
					}
					
					/*
					Orderheader oh = serv.getGenesisOrderheaderBySCN(slip.getSalesOrderNumber());
					if ( oh != null ) {
						slip.setCustomerOrderNumber(oh.getCustspecdesc());
						slip.setShipToContactPhone(oh.getBuyerphoneno());
						slip.setShipToContactName(oh.getBuyerName()); 
					}
					*/
				}
				byte[] outputArray = rs.generateNutanixPackingSlip(slip, ReportExportTypeEnum.PDF, request);
				rs.prepareResponseHeaderForJasperReport(response, "NutanixPickPackShipSlip_" + scn, ReportExportTypeEnum.PDF);
				response.getOutputStream().write(outputArray);
			}
			else {
				boolean isCollectiveItsOrder = true;
				boolean isNonCustomerPackingSlip = "PACK".equalsIgnoreCase(type) ;
				//Get slip header data
				slip = new NutanixPackingSlip();
				
				List<Integration> integrationList = serv.getCollectiveIntegrationBySCN(scn);
				if ( integrationList == null || integrationList.size() == 0 ) {
					integrationList = serv.getIntegrationBySCN(scn);
					isCollectiveItsOrder = false;
				}
				
				if ( integrationList != null && integrationList.size() > 0 ) {
				//Integration integration = serv.getIntegrationByICN(icn);
				//if ( integration != null ) {
					Integration integration = integrationList.get(0);
					slip.setNutanixOrderNumber(integration.getItsCustpono());
					slip.setSalesOrderNumber(integration.getId().getItsScn());
					this.getEvolveSalesOrderDetailService().getSalesOrderForNutanixPackingSlip(slip);
					
					if ( StringUtils.trimToNull(slip.getShipToCountry()) != null ) {
						String countryName = serv.getCountryNameByCountryCode(slip.getShipToCountry());
						if ( countryName != null ) {
							slip.setShipToCountry(countryName);
						}
					}
					
					/*
					if ( isShip99 ) {
						Orderaddress oa = serv.getGenesisOrderaddressBySCN(integration.getScn());
						if ( oa != null ) {
							slip.setShipToName(oa.getShiptoname());
							slip.setShipToAddress1(oa.getShiptoaddress1());
							slip.setShipToAddress2(oa.getShiptoaddress2());
							slip.setShipToCity(oa.getShiptocity());
							slip.setShipToState(oa.getShiptostateprovince());
							slip.setShipToZip(oa.getShiptopostalcode());
							slip.setShipToCountry(oa.getShiptocountry());
						}
					}
					else {
						slip.setShipToName(integration.getItsShiptoName());
						slip.setShipToAddress1(integration.getItsShiptoAddr1());
						slip.setShipToAddress2(integration.getItsShiptoAddr2());
						slip.setShipToCity(integration.getItsShiptoCity());
						slip.setShipToState(integration.getItsShiptoState());
						slip.setShipToZip(integration.getItsShiptoZip());
						slip.setShipToCountry(integration.getItsShiptoCountry());
					}
					
					Orderheader oh = serv.getGenesisOrderheaderBySCN(integration.getScn());
					if ( oh != null ) {
						
						//slip.setCustomerOrderNumber(oh.getCustdrawdescBom());
						slip.setCustomerOrderNumber(oh.getCustspecdesc());
						
						slip.setShipToContactPhone(oh.getBuyerphoneno());
						slip.setShipToContactName(oh.getBuyerName()); 
					}
					
					CarrierCode carrier = serv.getCarrierByCode(integration.getItsCustShipviaCode());
					if ( carrier != null ) {
						slip.setShippingCarrier(carrier.getCarrierName());
					}	
					*/	
				}
				
				Map<BigDecimal, Date> idMap = serv.getChassisPartAsmIdsByICNList(integrationList);
				//Map<BigDecimal, Date> idMap = serv.getChassisPartAsmIdsByICN(icn);
				
				if ( idMap == null || idMap.isEmpty() ) {
					//throw new Exception("No assemblies found for ICN: " + icn);
					throw new Exception("No assemblies found for SCN: " + scn);
				}
				else {
					
					//Get only the latest assembly from each nutanix chassis serial/ICN combo for the given ICN
					
					Map<String, AstPartAsm> assemblyByNunanixSerialNumberMap = new HashMap<String, AstPartAsm>();
					for ( BigDecimal id : idMap.keySet() ) {
						AstPartAsm chassisAsm = serv.getAstPartAsmById(id, false, null);
						//String nutanixtoplevelpartnumber = null;
						String nutanixserialnumber = null;
						for ( Object ca : chassisAsm.getAstPartAsmAttrs() ) {
							AstPartAsmAttr chassisAttr = (AstPartAsmAttr)ca;
							if ( "nutanixserialnumber".equalsIgnoreCase(chassisAttr.getAstPartAttr().getAstCompTypeAttr().getAttrNm()) ) {
								nutanixserialnumber = chassisAttr.getValueTx();
								break;
							}
							//else if ( "nutanixtoplevelpartnumber".equalsIgnoreCase(chassisAttr.getAstPartAttr().getAstCompTypeAttr().getAttrNm()) ) {
							//	nutanixtoplevelpartnumber = chassisAttr.getValueTx();
							//}
						}
						if ( !assemblyByNunanixSerialNumberMap.containsKey(nutanixserialnumber) ) { //get only latest per chassis serial
							assemblyByNunanixSerialNumberMap.put(nutanixserialnumber, chassisAsm);
						}
					}
					List<NutanixPackingSlipLine> nutanixPackingSlipLines = new ArrayList<NutanixPackingSlipLine>();
					List<NutanixCustomerPackingSlipLine> nutanixCustomerPackingSlipLines = new ArrayList<NutanixCustomerPackingSlipLine>();
					
					
					
					
					//List<ItsPart> itsPartList = serv.get2ndTouchItsPartsByICN(icn);
					List<ItsPart> itsPartList = null;
					if ( isCollectiveItsOrder ) {
						List<String> icnList = new ArrayList<String>();
						for ( Integration integrationRec : integrationList ) {
							icnList.add(integrationRec.getId().getItsInvoiceNo());
						}
						itsPartList = serv.get2ndTouchItsPartsByICNList(icnList);
					}
					else {
						itsPartList = serv.get2ndTouchItsPartsBySCN(scn);
					}
					
					
					
	
					if ( isNonCustomerPackingSlip ) {
						Map<BigDecimal, NutanixChassisLine> chassisMapByUnitDetailId = new LinkedHashMap<BigDecimal, NutanixChassisLine>();
						//Get multiple chassis first
						for ( ItsPart itsPart : itsPartList ) {
							String itsPartPartNumber = itsPart.getItsPartMfgpartno();
							String itsPartSerial = itsPart.getItsPartSn();
							String itsPartDescription = itsPart.getItsPartDescription();
							String itsSapMaterialNumber = itsPart.getSapMaterialNo();
							BigDecimal itsUnitDetailId = itsPart.getItsUnitDetailId();
							if ( assemblyByNunanixSerialNumberMap.containsKey(itsPartSerial) ) { //only way to tell if this is a chassis. ITS chassis flag not reliable
								NutanixChassisLine chassisLine = null;
								if ( chassisMapByUnitDetailId.containsKey(itsPart.getItsUnitDetailId()) ) {
									chassisLine = chassisMapByUnitDetailId.get(itsPart.getItsUnitDetailId());
								}
								else {
									chassisLine = new NutanixChassisLine();
									chassisLine.nutanixPartNumber = itsPartPartNumber;
									chassisLine.nutanixSerialNumber = itsPartSerial;
									chassisLine.description = itsPartDescription;
									if ( chassisLine.description == null ) {
										chassisLine.description = 
											serv.getItsFullProductPartDescription(itsSapMaterialNumber);
									}
									else {
										chassisLine.description = chassisLine.description.toUpperCase();
									}
									chassisLine.unitDetailId = itsUnitDetailId;
								}
								chassisMapByUnitDetailId.put(itsPart.getItsUnitDetailId(), chassisLine);
							}
						}
						
						//Then get chassis parts
						for ( ItsPart itsPart : itsPartList ) {
							String itsSapMaterialNumber = itsPart.getSapMaterialNo();
							String itsPartPartNumber = itsPart.getItsPartMfgpartno();
							String itsPartSerial = itsPart.getItsPartSn();
							String itsPartDescription = itsPart.getItsPartDescription();
							if ( itsPartDescription == null ) {
								itsPartDescription = serv.getItsFullProductPartDescription(itsSapMaterialNumber);
							}
							else {
								itsPartDescription = itsPartDescription.toUpperCase();
							}
							BigDecimal itsUnitDetailId = itsPart.getItsUnitDetailId();						
							//log.error("******* itsUnitDetailId: " + itsUnitDetailId);
							
							if ( !assemblyByNunanixSerialNumberMap.containsKey(itsPartSerial) ) { //only way to tell if this is a chassis. ITS chassis flag not reliable. exclude
								
								NutanixChassisLine chassisLine = chassisMapByUnitDetailId.get(itsUnitDetailId);
								
								if ( chassisLine == null ) {
									log.error("Chassis part map not found for unit detail id: " + itsUnitDetailId);
									throw new Exception("ITS missing chassis part record for unit detail id = " + itsUnitDetailId);
								}
								else {
									Map<String, NutanixPartLine> partMap = chassisLine.partMap;
									if ( partMap.containsKey(itsPartPartNumber) ) {
										NutanixPartLine partLine = partMap.get(itsPartPartNumber);
										if ( itsPartSerial != null && itsPartSerial.length() > 0 ) {
											partLine.serialNumberList.add(itsPartSerial);
										}
										
										if ( ! "N".equalsIgnoreCase(itsPart.getConsolidatedPartFl()) ) {
											partLine.quantity += itsPart.getConsolidatedPartQt().longValue();
										}
										else {
											partLine.quantity += 1;
										}
										partMap.put(itsPartPartNumber, partLine);
									}
									else {
										NutanixPartLine partLine = new NutanixPartLine();
										partLine.partNumber = itsPartPartNumber;
										partLine.description = itsPartDescription;
										if ( itsPartSerial != null && itsPartSerial.length() > 0 ) {
											partLine.serialNumberList.add(itsPartSerial);
										}
	
										if ( ! "N".equalsIgnoreCase(itsPart.getConsolidatedPartFl()) ) {
											partLine.quantity += itsPart.getConsolidatedPartQt().longValue();
										}
										else {
											partLine.quantity = 1;
										}
										partMap.put(itsPartPartNumber, partLine);
									}
									chassisMapByUnitDetailId.get(itsUnitDetailId).partMap = partMap;
								}
							}			
						}
						
						List<NutanixChassisLine> chassisLineList = new ArrayList<NutanixChassisLine>();
						chassisLineList.addAll(chassisMapByUnitDetailId.values());
						Collections.sort(chassisLineList, new NutanixChassisLineComparator());
						for ( NutanixChassisLine chassisLine : chassisLineList ) {
							
							nutanixPackingSlipLines.add(
									new NutanixPackingSlipLine(
											chassisLine.nutanixPartNumber, 
											chassisLine.description,
											chassisLine.nutanixSerialNumber, 
											1L)
									);
							
							Map<String, NutanixPartLine> partMap = chassisLine.partMap;
							List<NutanixPackingSlipLine> partsNutanixPackingSlipLines = new ArrayList<NutanixPackingSlipLine>(); //Used for sorting parts with chassis on top
							for ( NutanixPartLine partLine : partMap.values() ) {
								StringBuilder sNums = new StringBuilder();
								
								/*
								//TODO: Keep this code! They will probably ask for serial numbers for sub parts to be sorted and displayed again
								Collections.sort(partLine.serialNumberList);
								boolean firstSn = true;
								for (String sNum : partLine.serialNumberList ) {
									if ( firstSn ) {
										firstSn = false;
										sNums.append(sNum);
									}
									else {
										sNums.append(", ").append(sNum);
									}
									
								}
								*/
								
								partsNutanixPackingSlipLines.add(
										new NutanixPackingSlipLine(
												partLine.partNumber, 
												partLine.description,
												sNums.toString(), 
												partLine.quantity)
										);
							}
							Collections.sort(partsNutanixPackingSlipLines, new Util.NutanixPackingSlipLineComparator());
							nutanixPackingSlipLines.addAll(partsNutanixPackingSlipLines);
							//for ( NutanixPackingSlipLine npsl : partsNutanixPackingSlipLines) {
							//	nutanixPackingSlipLines.add(npsl);
							//}
						}
						slip.setNutanixPackingSlipLines(nutanixPackingSlipLines);
						byte[] outputArray = rs.generateNutanixPackingSlip(slip, ReportExportTypeEnum.PDF, request);
						rs.prepareResponseHeaderForJasperReport(response, "NutanixPackingSlip_" + scn, ReportExportTypeEnum.PDF);
						response.getOutputStream().write(outputArray);
					}
					else { // CustomerPackingSlip
						//Must get info from AST since motherboards do not get returned in 2nd touch from ITS
						for ( AstPartAsm chassisAsm : assemblyByNunanixSerialNumberMap.values() ) {
							Map<String, String> chassisAttrMap = this.getPartAsmAttrMap(chassisAsm);
							String nutanixserialnumber = chassisAttrMap.get("nutanixserialnumber");
							String nutanixtoplevelpartnumber = chassisAttrMap.get("nutanixtoplevelpartnumber");
							List<AstPartAsm> motherboardList = new ArrayList<AstPartAsm>();
							this.getPartAsmList(chassisAsm, motherboardList, true); //Get only motherboards to display their serials
							
							List<String> mbSerialNumberList = new ArrayList<String>();
							for ( AstPartAsm mb : motherboardList ) {
								Map<String, String> mbAttrMap = this.getPartAsmAttrMap(mb);
								//String partNumber = attrMap.get("partnumber");
								String serialNumber = mbAttrMap.get("serialnumber");
								if ( !mbSerialNumberList.contains(serialNumber) ) {
									mbSerialNumberList.add(serialNumber);
								}
							}
							Collections.sort(mbSerialNumberList);
							
							for ( String snum : mbSerialNumberList )	{
								nutanixCustomerPackingSlipLines.add(
										new NutanixCustomerPackingSlipLine(
												nutanixtoplevelpartnumber,
												nutanixserialnumber, 
												snum)
										);
							}
							
						}
						Collections.sort(nutanixCustomerPackingSlipLines, new Util.NutanixCustomerPackingSlipLineComparator());
						slip.setNutanixCustomerPackingSlipLines(nutanixCustomerPackingSlipLines);
						byte[] outputArray = rs.generateNutanixCustomerPackingSlip(slip, ReportExportTypeEnum.PDF, request);
						rs.prepareResponseHeaderForJasperReport(response, "NutanixCustomerPackingSlip_" + scn, ReportExportTypeEnum.PDF);
						response.getOutputStream().write(outputArray);
					}
				}
			}	
		} 
		catch (Exception ex) {
			log.error("Could not execute nutanixPackingSlipResults.", ex);
			request.setAttribute("scn", scn);
			request.setAttribute("type", type);
			request.setAttribute("errorMessage", ex.getMessage());
			return "systest/nutanix_packing_slip";
		}
		return null;
	}
	
	
	private Map<String, String> getPartAsmAttrMap(AstPartAsm partAsm) {
		Map<String, String> returnMap = new HashMap<String, String>();
		for ( Object o : partAsm.getAstPartAsmAttrs() ) {
			AstPartAsmAttr attr = (AstPartAsmAttr)o;
			returnMap.put(attr.getAstPartAttr().getAstCompTypeAttr().getAttrNm(), attr.getValueTx());
		}
		return returnMap;
	}
	
	private void getPartAsmList(AstPartAsm parent, List<AstPartAsm> list, boolean motherboardsOnly) {
		//List<AstPartAsm> returnList = new ArrayList<AstPartAsm>();
		if ( !"node".equalsIgnoreCase(parent.getAstPart().getAstCompType().getTypeNm()) &&
				!"chassis".equalsIgnoreCase(parent.getAstPart().getAstCompType().getTypeNm())	) {
			list.add(parent);
		}
		for ( Object o : parent.getAstPartAsms() ) {
			AstPartAsm child = (AstPartAsm)o;
			if ( "node".equalsIgnoreCase(child.getAstPart().getAstCompType().getTypeNm()) ) {
				this.getPartAsmList(child, list, motherboardsOnly);
			}
			else {
				if ( motherboardsOnly  ) {
					if ( "mb".equalsIgnoreCase(child.getAstPart().getAstCompType().getTypeNm()) ) {
						list.add(child);
					}
				}
				else  {
					list.add(child);
				}
			}	
		}
	}
	  
	@RequestMapping(value="/report/gsfcFinancialReportForm.htm", method=RequestMethod.GET)
	public ModelAndView gsfcFinancialReportForm() throws Exception {
		ModelAndView mv = null;
		SecurityUser user = this.securityService.getAuthenticatedUser();
		if ( securityService.isAuthorized(user.getAvnetGlobalUserId(), ACCOUNTING_REPORT_USER) ) {
			mv = new ModelAndView("report/gsfc_financial_report_form");
			mv.addObject("reportForm", new GSFCFinancialReportForm());
	        mv.addObject("fiscalMonthList", fiscalMonthMap.keySet());
		}
		else {
			mv = new ModelAndView("auth_error");
		}
        return mv;
	}
	
	@RequestMapping(value="/report/nutanixPackingSlipForm.htm", method=RequestMethod.GET)
	public ModelAndView nutanixPackingSlipForm() throws Exception {
		return new ModelAndView("systest/nutanix_packing_slip");
	}
	
	@RequestMapping(value="/report/astDailyTestImagingFSTResultsReportForm.htm", method=RequestMethod.GET)
	public ModelAndView astDailyTestResultsReportForm() throws Exception {
		return new ModelAndView("systest/daily_tests_report_form");
	}
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value = "/report/astDailyImagingFSTTestReportResults.htm", method = { RequestMethod.GET, RequestMethod.POST }) 
	public String astDailyTestReportResults(
			@RequestParam String testDate,
			HttpServletRequest request, 
			HttpServletResponse response
				) throws Exception {
		if ( testDate == null || testDate.trim().length() == 0 ) {
			return "systest/daily_tests_report_form";
		}
		SecurityUser user = this.securityService.getAuthenticatedUser();
		testDate = testDate.toUpperCase();
		log.info("User " + user.getFullName() + " (" + user.getAvnetGlobalUserId() + ") executed astDailyImagingFSTTestReportResults for date " + testDate);

		ReportService rs = new ReportService();
		SysTestService serv = new SysTestService();
		List<TestResultReportItem> list = serv.getDailyImagingFSTTestResults(testDate);
		byte[] outputArray = rs.generateAstDailyTestResults(list, ReportExportTypeEnum.XLS, request);
		rs.prepareResponseHeaderForJasperReport(response, "DailyImagingFSTTestResults_" + testDate, ReportExportTypeEnum.XLS);
		response.getOutputStream().write(outputArray);
		//return "systest/daily_tests_report_form";
		return null;
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


	public RedPrairieService getRedprairieService() {
		return redprairieService;
	}

	@Autowired
	public void setRedprairieService(RedPrairieService redprairieService) {
		this.redprairieService = redprairieService;
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
