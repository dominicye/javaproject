package com.avnet.alapps.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.holders.BigDecimalHolder;
import javax.xml.rpc.holders.StringHolder;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import com.avnet.alapps.spring.WebConfig;
import com.avnet.alapps.systest.model.NutanixPackingSlip;
import com.avnet.alapps.systest.model.NutanixPackingSlipWaybill;
import com.avnet.alapps.webservice.evolvesalesorderdetail.ZWS_QTC_SALESORDER_GETDETAILProxy;
import com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcBillingplanHolder;
import com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcContractHolder;
import com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcDeliveriesHolder;
import com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcFactorySoHolder;
import com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcInvoicesHolder;
import com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcPartnerDetailsHolder;
import com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcSalesorderLineitemsHolder;
import com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcSalesorderSerialNoHolder;
import com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcScheduleDetailsHolder;
import com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcWblTrackHolder;


public class EvolveSalesOrderDetailService {
	
	private static Logger log = Logger.getLogger(EvolveSalesOrderDetailService.class);
	
	@Value("${alapps.webservice.url.getevolvesalesorderdetailvs}") private String serviceUrl; 
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties(){
		return WebConfig.propertySourcesPlaceholderConfigurer();
	}
	
	public void getSalesOrderForNutanixPackingSlip(NutanixPackingSlip slip) throws Exception {

		
		ZWS_QTC_SALESORDER_GETDETAILProxy proxy = new ZWS_QTC_SALESORDER_GETDETAILProxy();
		proxy.setEndpoint(serviceUrl);
		proxy.useJNDI(false);
		
		String salesOrderNo = slip.getSalesOrderNumber(); 
		String searchType = "ON"; 
		String webOrderNumber = "";
		StringHolder billingPlan = new StringHolder();
		ZttqtcBillingplanHolder billingPlandetails = new ZttqtcBillingplanHolder();
		ZttqtcContractHolder contractDetails = new ZttqtcContractHolder();
		StringHolder creditStatus = new StringHolder();
		StringHolder csrName = new StringHolder();
		StringHolder currency = new StringHolder();
		StringHolder customerPo = new StringHolder();
		StringHolder customerRefNo = new StringHolder();
		ZttqtcDeliveriesHolder deliveryNos = new ZttqtcDeliveriesHolder();
		StringHolder endUserPo = new StringHolder();
		ZttqtcFactorySoHolder factorySo = new ZttqtcFactorySoHolder();
		StringHolder govtContractNo = new StringHolder();
		ZttqtcInvoicesHolder invoiceNos = new ZttqtcInvoicesHolder();
		BigDecimalHolder orderAmount = new BigDecimalHolder();
		StringHolder orderDate = new StringHolder();
		ZttqtcSalesorderLineitemsHolder orderItemsOut = new ZttqtcSalesorderLineitemsHolder();
		StringHolder orderNo = new StringHolder();
		ZttqtcScheduleDetailsHolder orderSchedule = new ZttqtcScheduleDetailsHolder();
		ZttqtcSalesorderSerialNoHolder orderSerialnoOut = new ZttqtcSalesorderSerialNoHolder();
		StringHolder orderType = new StringHolder();
		StringHolder overallStatus = new StringHolder();
		ZttqtcPartnerDetailsHolder partnerDetails = new ZttqtcPartnerDetailsHolder();
		StringHolder payTerm = new StringHolder();
		StringHolder r2ONumber = new StringHolder();
		StringHolder releaseNo = new StringHolder();
		StringHolder rollupFlag = new StringHolder();
		StringHolder shippingAttention = new StringHolder();
		//ZttqtcWblTrackHolder wayBills = new ZttqtcWblTrackHolder();
		StringHolder acctchannel = new StringHolder();
		ZttqtcWblTrackHolder wayBill = new ZttqtcWblTrackHolder();
		
		proxy.zqtcSalesorderGetdetail(
				salesOrderNo, 
				searchType, 
				webOrderNumber, 
				acctchannel,
				billingPlan, 
				billingPlandetails, 
				contractDetails, 
				creditStatus, 
				csrName, 
				currency, 
				customerPo, 
				customerRefNo, 
				deliveryNos, 
				endUserPo, 
				factorySo, 
				govtContractNo, 
				invoiceNos, 
				orderAmount, 
				orderDate, 
				orderItemsOut, 
				orderNo, 
				orderSchedule, 
				orderSerialnoOut, 
				orderType, 
				overallStatus, 
				partnerDetails, 
				payTerm, 
				r2ONumber, 
				releaseNo, 
				rollupFlag, 
				shippingAttention, 
				wayBill);

		//if ( deliveryNos != null && deliveryNos.value != null && deliveryNos.value.length > 0  ) {
			//slip.setDeliveryNote(deliveryNos.value[0].getDelivery());
		//}
		
		if ( endUserPo != null && endUserPo.value != null ) {
			slip.setCustomerOrderNumber(endUserPo.value);
		}
		
		if ( partnerDetails != null && partnerDetails.value != null && partnerDetails.value.length > 0  ) {
			
			boolean foundShipto = false;
			boolean foundContact = false;
			boolean foundShipvia = false;
			for ( int p = 0; p < partnerDetails.value.length; p++ ) {
				
				if ( "WE".equalsIgnoreCase(partnerDetails.value[p].getPartnerType()) ) {
					slip.setShipToName(partnerDetails.value[p].getName());
					slip.setShipToAttention(partnerDetails.value[p].getName2());
					slip.setShipToAddress1(partnerDetails.value[p].getAddress1());
					slip.setShipToAddress2(partnerDetails.value[p].getAddress2());
					slip.setShipToAddress3(partnerDetails.value[p].getAddress3());
					slip.setShipToCity(partnerDetails.value[p].getCity());
					slip.setShipToState(partnerDetails.value[p].getState());
					slip.setShipToZip(partnerDetails.value[p].getPostalCode());
					slip.setShipToCountry(partnerDetails.value[p].getCountry());
					foundShipto = true;
				}
				else if ( "AP".equalsIgnoreCase(partnerDetails.value[p].getPartnerType()) ) {
					slip.setShipToContactName(partnerDetails.value[p].getName());
					slip.setShipToContactPhone(partnerDetails.value[p].getTelNumber());
					foundContact = true;
				}
				else if ( "SP".equalsIgnoreCase(partnerDetails.value[p].getPartnerType()) ) {
					slip.setShipViaName(partnerDetails.value[p].getName());
					foundShipvia = true;
				}
				
				if ( foundShipto && foundContact && foundShipvia ) break;
				
			}
		}
		
		Map<String, NutanixPackingSlipWaybill> waybillMap = new HashMap<String, NutanixPackingSlipWaybill>();
		if ( wayBill != null && wayBill.value != null && wayBill.value.length > 0  ) {
			log.info("*************  wayBill count =" + wayBill.value.length);
			for ( int w = 0; w < wayBill.value.length; w++ ) {
				String deliveryNote = wayBill.value[w].getZdocno();
				//Get only one record per delivery note
				if ( !waybillMap.containsKey(deliveryNote) ) {
					log.info("*************  deliveryNote=" + deliveryNote);
					String carrierName = wayBill.value[w].getZcarrname();
					String waybill = wayBill.value[w].getZwaybill();
					String shipDateString = wayBill.value[w].getZshpdate();
					
					Date shipDate = null;
					if ( StringUtils.trimToNull(shipDateString) != null ) {
						
						
						
						DateTimeFormatter dateFormat = DateTimeFormat.forPattern("YYYY-MM-dd");
						try {
							shipDate = dateFormat.parseDateTime(shipDateString).toDate();
						}
						catch ( Exception e ) {
							log.warn(
									"getSalesOrderForNutanixPackingSlip could not parse Zshpdate, SCN=" + slip.getSalesOrderNumber() + ": "
									, e);
						}
					}
					
					NutanixPackingSlipWaybill wb = 
						new NutanixPackingSlipWaybill(deliveryNote, carrierName, waybill, shipDate);
					
					waybillMap.put(deliveryNote, wb);
				}
			}
			List<NutanixPackingSlipWaybill> waybills = new ArrayList<NutanixPackingSlipWaybill>(waybillMap.values());
			slip.setNutanixPackingSlipWaybills(waybills); 
		}
	}
	
	
public String getZzCustRefBySalesOrderAndLineNumber(String salesOrderNumber, String materialNumber) throws Exception {
		String zzCustRef = null;
		
		ZWS_QTC_SALESORDER_GETDETAILProxy proxy = new ZWS_QTC_SALESORDER_GETDETAILProxy();
		proxy.setEndpoint(serviceUrl);
		proxy.useJNDI(false);
		
		String salesOrderNo = salesOrderNumber; 
		String searchType = "ON"; 
		String webOrderNumber = "";
		StringHolder billingPlan = new StringHolder();
		ZttqtcBillingplanHolder billingPlandetails = new ZttqtcBillingplanHolder();
		ZttqtcContractHolder contractDetails = new ZttqtcContractHolder();
		StringHolder creditStatus = new StringHolder();
		StringHolder csrName = new StringHolder();
		StringHolder currency = new StringHolder();
		StringHolder customerPo = new StringHolder();
		StringHolder customerRefNo = new StringHolder();
		ZttqtcDeliveriesHolder deliveryNos = new ZttqtcDeliveriesHolder();
		StringHolder endUserPo = new StringHolder();
		ZttqtcFactorySoHolder factorySo = new ZttqtcFactorySoHolder();
		StringHolder govtContractNo = new StringHolder();
		ZttqtcInvoicesHolder invoiceNos = new ZttqtcInvoicesHolder();
		BigDecimalHolder orderAmount = new BigDecimalHolder();
		StringHolder orderDate = new StringHolder();
		ZttqtcSalesorderLineitemsHolder orderItemsOut = new ZttqtcSalesorderLineitemsHolder();
		StringHolder orderNo = new StringHolder();
		ZttqtcScheduleDetailsHolder orderSchedule = new ZttqtcScheduleDetailsHolder();
		ZttqtcSalesorderSerialNoHolder orderSerialnoOut = new ZttqtcSalesorderSerialNoHolder();
		StringHolder orderType = new StringHolder();
		StringHolder overallStatus = new StringHolder();
		ZttqtcPartnerDetailsHolder partnerDetails = new ZttqtcPartnerDetailsHolder();
		StringHolder payTerm = new StringHolder();
		StringHolder r2ONumber = new StringHolder();
		StringHolder releaseNo = new StringHolder();
		StringHolder rollupFlag = new StringHolder();
		StringHolder shippingAttention = new StringHolder();
		//ZttqtcWblTrackHolder wayBills = new ZttqtcWblTrackHolder();
		StringHolder acctchannel = new StringHolder();
		ZttqtcWblTrackHolder wayBill = new ZttqtcWblTrackHolder();
		
		proxy.zqtcSalesorderGetdetail(
				salesOrderNo, 
				searchType, 
				webOrderNumber, 
				acctchannel,
				billingPlan, 
				billingPlandetails, 
				contractDetails, 
				creditStatus, 
				csrName, 
				currency, 
				customerPo, 
				customerRefNo, 
				deliveryNos, 
				endUserPo, 
				factorySo, 
				govtContractNo, 
				invoiceNos, 
				orderAmount, 
				orderDate, 
				orderItemsOut, 
				orderNo, 
				orderSchedule, 
				orderSerialnoOut, 
				orderType, 
				overallStatus, 
				partnerDetails, 
				payTerm, 
				r2ONumber, 
				releaseNo, 
				rollupFlag, 
				shippingAttention, 
				wayBill);
		
		if ( orderItemsOut != null && orderItemsOut.value != null && orderItemsOut.value.length > 0  ) {
			materialNumber = StringUtils.leftPad(materialNumber, 18, "0");
			for ( int i = 0; i < orderItemsOut.value.length; i++ ) {
				//String sapLineItemNumber = orderItemsOut.value[i].getLineitemNo();
				String sapLineItemNumber = orderItemsOut.value[i].getMaterialNo();
				//log.info("******** materialNumber=" + materialNumber + ", sapLineItemNumber=" + sapLineItemNumber);
				if ( materialNumber.equals(sapLineItemNumber) ) {
					zzCustRef = orderItemsOut.value[i].getZzCustref();
					//log.info("******** zzCustRef=" + zzCustRef);
					break;
				}
			}
		}
		return zzCustRef;
	}

}
