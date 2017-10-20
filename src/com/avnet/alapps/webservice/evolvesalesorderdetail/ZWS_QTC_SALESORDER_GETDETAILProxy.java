package com.avnet.alapps.webservice.evolvesalesorderdetail;

public class ZWS_QTC_SALESORDER_GETDETAILProxy implements com.avnet.alapps.webservice.evolvesalesorderdetail.ZWS_QTC_SALESORDER_GETDETAIL {
  private boolean _useJNDI = true;
  private boolean _useJNDIOnly = false;
  private String _endpoint = null;
  private com.avnet.alapps.webservice.evolvesalesorderdetail.ZWS_QTC_SALESORDER_GETDETAIL __zWS_QTC_SALESORDER_GETDETAIL = null;
  
  public ZWS_QTC_SALESORDER_GETDETAILProxy() {
    _initZWS_QTC_SALESORDER_GETDETAILProxy();
  }
  
  private void _initZWS_QTC_SALESORDER_GETDETAILProxy() {
  
    if (_useJNDI || _useJNDIOnly) {
      try {
        javax.naming.InitialContext ctx = new javax.naming.InitialContext();
        __zWS_QTC_SALESORDER_GETDETAIL = ((com.avnet.alapps.webservice.evolvesalesorderdetail.GetEvolveSalesOrderDetailVS)ctx.lookup("java:comp/env/service/getEvolveSalesOrderDetailVS")).getGetEvolveSalesOrderDetailVSsoaphttp();
      }
      catch (javax.naming.NamingException namingException) {
        if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
          System.out.println("JNDI lookup failure: javax.naming.NamingException: " + namingException.getMessage());
          namingException.printStackTrace(System.out);
        }
      }
      catch (javax.xml.rpc.ServiceException serviceException) {
        if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
          System.out.println("Unable to obtain port: javax.xml.rpc.ServiceException: " + serviceException.getMessage());
          serviceException.printStackTrace(System.out);
        }
      }
    }
    if (__zWS_QTC_SALESORDER_GETDETAIL == null && !_useJNDIOnly) {
      try {
        __zWS_QTC_SALESORDER_GETDETAIL = (new com.avnet.alapps.webservice.evolvesalesorderdetail.GetEvolveSalesOrderDetailVSLocator()).getGetEvolveSalesOrderDetailVSsoaphttp();
        
      }
      catch (javax.xml.rpc.ServiceException serviceException) {
        if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
          System.out.println("Unable to obtain port: javax.xml.rpc.ServiceException: " + serviceException.getMessage());
          serviceException.printStackTrace(System.out);
        }
      }
    }
    if (__zWS_QTC_SALESORDER_GETDETAIL != null) {
      if (_endpoint != null)
        ((javax.xml.rpc.Stub)__zWS_QTC_SALESORDER_GETDETAIL)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
      else
        _endpoint = (String)((javax.xml.rpc.Stub)__zWS_QTC_SALESORDER_GETDETAIL)._getProperty("javax.xml.rpc.service.endpoint.address");
    }
    
  }
  
  
  public void useJNDI(boolean useJNDI) {
    _useJNDI = useJNDI;
    __zWS_QTC_SALESORDER_GETDETAIL = null;
    
  }
  
  public void useJNDIOnly(boolean useJNDIOnly) {
    _useJNDIOnly = useJNDIOnly;
    __zWS_QTC_SALESORDER_GETDETAIL = null;
    
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (__zWS_QTC_SALESORDER_GETDETAIL == null)
      _initZWS_QTC_SALESORDER_GETDETAILProxy();
    if (__zWS_QTC_SALESORDER_GETDETAIL != null)
      ((javax.xml.rpc.Stub)__zWS_QTC_SALESORDER_GETDETAIL)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public void zqtcSalesorderGetdetail(java.lang.String salesOrderNo, java.lang.String searchType, java.lang.String webOrderNumber, javax.xml.rpc.holders.StringHolder acctchannel, javax.xml.rpc.holders.StringHolder billingPlan, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcBillingplanHolder billingPlandetails, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcContractHolder contractDetails, javax.xml.rpc.holders.StringHolder creditStatus, javax.xml.rpc.holders.StringHolder csrName, javax.xml.rpc.holders.StringHolder currency, javax.xml.rpc.holders.StringHolder customerPo, javax.xml.rpc.holders.StringHolder customerRefNo, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcDeliveriesHolder deliveryNos, javax.xml.rpc.holders.StringHolder endUserPo, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcFactorySoHolder factorySo, javax.xml.rpc.holders.StringHolder govtContractNo, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcInvoicesHolder invoiceNos, javax.xml.rpc.holders.BigDecimalHolder orderAmount, javax.xml.rpc.holders.StringHolder orderDate, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcSalesorderLineitemsHolder orderItemsOut, javax.xml.rpc.holders.StringHolder orderNo, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcScheduleDetailsHolder orderSchedule, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcSalesorderSerialNoHolder orderSerialnoOut, javax.xml.rpc.holders.StringHolder orderType, javax.xml.rpc.holders.StringHolder overallStatus, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcPartnerDetailsHolder partnerDetails, javax.xml.rpc.holders.StringHolder payTerm, javax.xml.rpc.holders.StringHolder r2ONumber, javax.xml.rpc.holders.StringHolder releaseNo, javax.xml.rpc.holders.StringHolder rollupFlag, javax.xml.rpc.holders.StringHolder shippingAttention, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcWblTrackHolder wayBill) throws java.rmi.RemoteException{
    if (__zWS_QTC_SALESORDER_GETDETAIL == null)
      _initZWS_QTC_SALESORDER_GETDETAILProxy();
    __zWS_QTC_SALESORDER_GETDETAIL.zqtcSalesorderGetdetail(salesOrderNo, searchType, webOrderNumber, acctchannel, billingPlan, billingPlandetails, contractDetails, creditStatus, csrName, currency, customerPo, customerRefNo, deliveryNos, endUserPo, factorySo, govtContractNo, invoiceNos, orderAmount, orderDate, orderItemsOut, orderNo, orderSchedule, orderSerialnoOut, orderType, overallStatus, partnerDetails, payTerm, r2ONumber, releaseNo, rollupFlag, shippingAttention, wayBill);
  }
  
  
  public com.avnet.alapps.webservice.evolvesalesorderdetail.ZWS_QTC_SALESORDER_GETDETAIL getZWS_QTC_SALESORDER_GETDETAIL() {
    if (__zWS_QTC_SALESORDER_GETDETAIL == null)
      _initZWS_QTC_SALESORDER_GETDETAILProxy();
    return __zWS_QTC_SALESORDER_GETDETAIL;
  }
  
}