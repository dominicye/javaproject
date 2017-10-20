/**
 * GetEvolveSalesOrderDetailVSInformation.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf151104.08 v2211211011
 */

package com.avnet.alapps.webservice.evolvesalesorderdetail;

public class GetEvolveSalesOrderDetailVSInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

    private static java.util.Map operationDescriptions;
    private static java.util.Map typeMappings;

    static {
         initOperationDescriptions();
         initTypeMappings();
    }

    private static void initOperationDescriptions() { 
        operationDescriptions = new java.util.HashMap();

        java.util.Map inner0 = new java.util.HashMap();

        java.util.List list0 = new java.util.ArrayList();
        inner0.put("zqtcSalesorderGetdetail", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc zqtcSalesorderGetdetail0Op = _zqtcSalesorderGetdetail0Op();
        list0.add(zqtcSalesorderGetdetail0Op);

        operationDescriptions.put("getEvolveSalesOrderDetailVSsoaphttp",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _zqtcSalesorderGetdetail0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc zqtcSalesorderGetdetail0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "SalesOrderNo"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "SearchType"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char2"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "WebOrderNumber"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "char20"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Acctchannel"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "char3"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "BillingPlan"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "BillingPlandetails"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcBillingplan"), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcBillingplan[].class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "ContractDetails"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcContract"), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcContract[].class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "CreditStatus"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char1"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "CsrName"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char40"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Currency"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "cuky5"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "CustomerPo"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char35"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "CustomerRefNo"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char12"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "DeliveryNos"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcDeliveries"), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcDeliveries[].class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "EndUserPo"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char35"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "FactorySo"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcFactorySo"), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcFactorySo[].class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "GovtContractNo"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char22"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "InvoiceNos"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcInvoices"), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcInvoices[].class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderAmount"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "curr15.2"), java.math.BigDecimal.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderDate"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "date10"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderItemsOut"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcSalesorderLineitems"), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcSalesorderLineitems[].class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderNo"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderSchedule"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcScheduleDetails"), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcScheduleDetails[].class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderSerialnoOut"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcSalesorderSerialNo"), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcSalesorderSerialNo[].class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderType"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char4"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OverallStatus"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char1"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "PartnerDetails"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcPartnerDetails"), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcPartnerDetails[].class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "PayTerm"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "char4"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "R2oNumber"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "ReleaseNo"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char12"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "RollupFlag"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "char3"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "ShippingAttention"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char40"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "WayBill"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcWblTrack"), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcWblTrack[].class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("inputPosition","0");
        _params0[0].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char10");
        _params0[0].setOption("partName","char10");
        _params0[1].setOption("inputPosition","1");
        _params0[1].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char2");
        _params0[1].setOption("partName","char2");
        _params0[2].setOption("inputPosition","2");
        _params0[2].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}char20");
        _params0[2].setOption("partName","char20");
        _params0[3].setOption("outputPosition","0");
        _params0[3].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}char3");
        _params0[3].setOption("partName","char3");
        _params0[4].setOption("outputPosition","1");
        _params0[4].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char10");
        _params0[4].setOption("partName","char10");
        _params0[5].setOption("outputPosition","2");
        _params0[5].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}ZttqtcBillingplan");
        _params0[5].setOption("partName","ZttqtcBillingplan");
        _params0[6].setOption("outputPosition","3");
        _params0[6].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}ZttqtcContract");
        _params0[6].setOption("partName","ZttqtcContract");
        _params0[7].setOption("outputPosition","4");
        _params0[7].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char1");
        _params0[7].setOption("partName","char1");
        _params0[8].setOption("outputPosition","5");
        _params0[8].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char40");
        _params0[8].setOption("partName","char40");
        _params0[9].setOption("outputPosition","6");
        _params0[9].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}cuky5");
        _params0[9].setOption("partName","cuky5");
        _params0[10].setOption("outputPosition","7");
        _params0[10].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char35");
        _params0[10].setOption("partName","char35");
        _params0[11].setOption("outputPosition","8");
        _params0[11].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char12");
        _params0[11].setOption("partName","char12");
        _params0[12].setOption("outputPosition","9");
        _params0[12].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}ZttqtcDeliveries");
        _params0[12].setOption("partName","ZttqtcDeliveries");
        _params0[13].setOption("outputPosition","10");
        _params0[13].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char35");
        _params0[13].setOption("partName","char35");
        _params0[14].setOption("outputPosition","11");
        _params0[14].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}ZttqtcFactorySo");
        _params0[14].setOption("partName","ZttqtcFactorySo");
        _params0[15].setOption("outputPosition","12");
        _params0[15].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char22");
        _params0[15].setOption("partName","char22");
        _params0[16].setOption("outputPosition","13");
        _params0[16].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}ZttqtcInvoices");
        _params0[16].setOption("partName","ZttqtcInvoices");
        _params0[17].setOption("outputPosition","14");
        _params0[17].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}curr15.2");
        _params0[17].setOption("partName","curr15.2");
        _params0[18].setOption("outputPosition","15");
        _params0[18].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}date10");
        _params0[18].setOption("partName","date10");
        _params0[19].setOption("outputPosition","16");
        _params0[19].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}ZttqtcSalesorderLineitems");
        _params0[19].setOption("partName","ZttqtcSalesorderLineitems");
        _params0[20].setOption("outputPosition","17");
        _params0[20].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char10");
        _params0[20].setOption("partName","char10");
        _params0[21].setOption("outputPosition","18");
        _params0[21].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}ZttqtcScheduleDetails");
        _params0[21].setOption("partName","ZttqtcScheduleDetails");
        _params0[22].setOption("outputPosition","19");
        _params0[22].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}ZttqtcSalesorderSerialNo");
        _params0[22].setOption("partName","ZttqtcSalesorderSerialNo");
        _params0[23].setOption("outputPosition","20");
        _params0[23].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char4");
        _params0[23].setOption("partName","char4");
        _params0[24].setOption("outputPosition","21");
        _params0[24].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char1");
        _params0[24].setOption("partName","char1");
        _params0[25].setOption("outputPosition","22");
        _params0[25].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}ZttqtcPartnerDetails");
        _params0[25].setOption("partName","ZttqtcPartnerDetails");
        _params0[26].setOption("outputPosition","23");
        _params0[26].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}char4");
        _params0[26].setOption("partName","char4");
        _params0[27].setOption("outputPosition","24");
        _params0[27].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char10");
        _params0[27].setOption("partName","char10");
        _params0[28].setOption("outputPosition","25");
        _params0[28].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char12");
        _params0[28].setOption("partName","char12");
        _params0[29].setOption("outputPosition","26");
        _params0[29].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}char3");
        _params0[29].setOption("partName","char3");
        _params0[30].setOption("outputPosition","27");
        _params0[30].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char40");
        _params0[30].setOption("partName","char40");
        _params0[31].setOption("outputPosition","28");
        _params0[31].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}ZttqtcWblTrack");
        _params0[31].setOption("partName","ZttqtcWblTrack");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(null, com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://websphere.ibm.com/webservices/", "Void"), void.class, true, false, false, false, true, true); 
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        zqtcSalesorderGetdetail0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("zqtcSalesorderGetdetail", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZqtcSalesorderGetdetail"), _params0, _returnDesc0, _faults0, null);
        zqtcSalesorderGetdetail0Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZWS_QTC_SALESORDER_GETDETAIL"));
        zqtcSalesorderGetdetail0Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZqtcSalesorderGetdetailResponse"));
        zqtcSalesorderGetdetail0Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "getEvolveSalesOrderDetailVS"));
        zqtcSalesorderGetdetail0Op.setOption("buildNum","cf151104.08");
        zqtcSalesorderGetdetail0Op.setOption("ResponseNamespace","urn:sap-com:document:sap:soap:functions:mc-style");
        zqtcSalesorderGetdetail0Op.setOption("targetNamespace","urn:sap-com:document:sap:soap:functions:mc-style");
        zqtcSalesorderGetdetail0Op.setOption("ResponseLocalPart","ZqtcSalesorderGetdetailResponse");
        zqtcSalesorderGetdetail0Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZqtcSalesorderGetdetail"));
        zqtcSalesorderGetdetail0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return zqtcSalesorderGetdetail0Op;

    }


    private static void initTypeMappings() {
        typeMappings = new java.util.HashMap();
        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char1"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char12"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char15"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char18"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char2"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char20"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char22"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char241"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char25"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char3"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char30"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char35"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char4"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char40"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char5"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char6"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "cuky5"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "curr11.2"),
                         java.math.BigDecimal.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "curr13.2"),
                         java.math.BigDecimal.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "curr15.2"),
                         java.math.BigDecimal.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "date10"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "numeric2"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "numeric3"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "numeric6"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "quantum13.3"),
                         java.math.BigDecimal.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "quantum15.3"),
                         java.math.BigDecimal.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "char10"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "char20"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "char3"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "char4"),
                         java.lang.String.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcBillingplan"),
                         com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcBillingplan.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcContract"),
                         com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcContract.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcDeliveries"),
                         com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcDeliveries.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcFactorySo"),
                         com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcFactorySo.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcInvoices"),
                         com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcInvoices.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcSalesorderLineitems"),
                         com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcSalesorderLineitems.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcScheduleDetails"),
                         com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcScheduleDetails.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcSalesorderSerialNo"),
                         com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcSalesorderSerialNo.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcPartnerDetails"),
                         com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcPartnerDetails.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcWblTrack"),
                         com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcWblTrack.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcBillingplan"),
                         com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcBillingplan[].class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcContract"),
                         com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcContract[].class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcDeliveries"),
                         com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcDeliveries[].class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcFactorySo"),
                         com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcFactorySo[].class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcInvoices"),
                         com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcInvoices[].class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcSalesorderLineitems"),
                         com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcSalesorderLineitems[].class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcScheduleDetails"),
                         com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcScheduleDetails[].class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcSalesorderSerialNo"),
                         com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcSalesorderSerialNo[].class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcPartnerDetails"),
                         com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcPartnerDetails[].class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcWblTrack"),
                         com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcWblTrack[].class);

        typeMappings = java.util.Collections.unmodifiableMap(typeMappings);
    }

    public java.util.Map getTypeMappings() {
        return typeMappings;
    }

    public Class getJavaType(javax.xml.namespace.QName xmlName) {
        return (Class) typeMappings.get(xmlName);
    }

    public java.util.Map getOperationDescriptions(String portName) {
        return (java.util.Map) operationDescriptions.get(portName);
    }

    public java.util.List getOperationDescriptions(String portName, String operationName) {
        java.util.Map map = (java.util.Map) operationDescriptions.get(portName);
        if (map != null) {
            return (java.util.List) map.get(operationName);
        }
        return null;
    }

}
