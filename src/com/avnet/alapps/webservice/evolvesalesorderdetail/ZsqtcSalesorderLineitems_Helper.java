/**
 * ZsqtcSalesorderLineitems_Helper.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf151104.08 v2211211011
 */

package com.avnet.alapps.webservice.evolvesalesorderdetail;

public class ZsqtcSalesorderLineitems_Helper {
    // Type metadata
    private static final com.ibm.ws.webservices.engine.description.TypeDesc typeDesc =
        new com.ibm.ws.webservices.engine.description.TypeDesc(ZsqtcSalesorderLineitems.class);

    static {
        typeDesc.setOption("buildNum","cf151104.08");
        com.ibm.ws.webservices.engine.description.FieldDesc field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("rmaExptDt");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "RmaExptDt"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "date10"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("lineitemNo");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "LineitemNo"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "numeric6"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("materialNo");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "MaterialNo"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char18"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("custPartNo");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "CustPartNo"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char35"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("manfPartNo");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "ManfPartNo"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char40"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("manfNumber");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "ManfNumber"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("manfName");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "ManfName"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char30"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("bomPartNo");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "BomPartNo"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "numeric6"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("itemCategory");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "ItemCategory"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char4"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("qtyOrdered");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "QtyOrdered"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "quantum15.3"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("srvQtyOrdered");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "SrvQtyOrdered"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "quantum13.3"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("processingStatus");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "ProcessingStatus"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char1"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("rejectionStatus");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "RejectionStatus"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char1"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("rejectionReason");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "RejectionReason"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char2"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("resaleUnitPrice");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "ResaleUnitPrice"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "curr11.2"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("extUnitPrice");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "ExtUnitPrice"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "curr11.2"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("partDescription");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "PartDescription"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char40"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("profitCenter");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "ProfitCenter"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("storageLoc");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "StorageLoc"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char4"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("plant");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Plant"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char4"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("purchasingGrp");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "PurchasingGrp"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char3"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("vendSaleOrdr");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "VendSaleOrdr"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char15"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("customerPo");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "CustomerPo"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char35"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("poItmNo");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "PoItmNo"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char6"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("configId");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "ConfigId"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char20"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("wip");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Wip"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char5"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("dropshipPo");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "DropshipPo"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("agreementId");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "AgreementId"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char25"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("varId");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "VarId"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char15"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("estShipDate");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "EstShipDate"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "date10"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("hgLvItem");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "HgLvItem"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "numeric6"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("faSerialno");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "FaSerialno"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "char10"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("webOrdItemno");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "WebOrdItemno"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "char20"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("zzCustref");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "ZzCustref"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "char10"));
        typeDesc.addFieldDesc(field);
    };

    /**
     * Return type metadata object
     */
    public static com.ibm.ws.webservices.engine.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static com.ibm.ws.webservices.engine.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class javaType,  
           javax.xml.namespace.QName xmlType) {
        return 
          new ZsqtcSalesorderLineitems_Ser(
            javaType, xmlType, typeDesc);
    };

    /**
     * Get Custom Deserializer
     */
    public static com.ibm.ws.webservices.engine.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class javaType,  
           javax.xml.namespace.QName xmlType) {
        return 
          new ZsqtcSalesorderLineitems_Deser(
            javaType, xmlType, typeDesc);
    };

}
