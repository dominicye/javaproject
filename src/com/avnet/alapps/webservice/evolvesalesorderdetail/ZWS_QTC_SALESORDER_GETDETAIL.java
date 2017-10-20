/**
 * ZWS_QTC_SALESORDER_GETDETAIL.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf151104.08 v2211211011
 */

package com.avnet.alapps.webservice.evolvesalesorderdetail;

public interface ZWS_QTC_SALESORDER_GETDETAIL extends java.rmi.Remote {
    public void zqtcSalesorderGetdetail(java.lang.String salesOrderNo, java.lang.String searchType, java.lang.String webOrderNumber, javax.xml.rpc.holders.StringHolder acctchannel, javax.xml.rpc.holders.StringHolder billingPlan, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcBillingplanHolder billingPlandetails, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcContractHolder contractDetails, javax.xml.rpc.holders.StringHolder creditStatus, javax.xml.rpc.holders.StringHolder csrName, javax.xml.rpc.holders.StringHolder currency, javax.xml.rpc.holders.StringHolder customerPo, javax.xml.rpc.holders.StringHolder customerRefNo, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcDeliveriesHolder deliveryNos, javax.xml.rpc.holders.StringHolder endUserPo, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcFactorySoHolder factorySo, javax.xml.rpc.holders.StringHolder govtContractNo, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcInvoicesHolder invoiceNos, javax.xml.rpc.holders.BigDecimalHolder orderAmount, javax.xml.rpc.holders.StringHolder orderDate, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcSalesorderLineitemsHolder orderItemsOut, javax.xml.rpc.holders.StringHolder orderNo, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcScheduleDetailsHolder orderSchedule, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcSalesorderSerialNoHolder orderSerialnoOut, javax.xml.rpc.holders.StringHolder orderType, javax.xml.rpc.holders.StringHolder overallStatus, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcPartnerDetailsHolder partnerDetails, javax.xml.rpc.holders.StringHolder payTerm, javax.xml.rpc.holders.StringHolder r2ONumber, javax.xml.rpc.holders.StringHolder releaseNo, javax.xml.rpc.holders.StringHolder rollupFlag, javax.xml.rpc.holders.StringHolder shippingAttention, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcWblTrackHolder wayBill) throws java.rmi.RemoteException;
}
