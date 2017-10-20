/**
 * ZsqtcDeliveries_Helper.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf151104.08 v2211211011
 */

package com.avnet.alapps.webservice.evolvesalesorderdetail;

public class ZsqtcDeliveries_Helper {
    // Type metadata
    private static final com.ibm.ws.webservices.engine.description.TypeDesc typeDesc =
        new com.ibm.ws.webservices.engine.description.TypeDesc(ZsqtcDeliveries.class);

    static {
        typeDesc.setOption("buildNum","cf151104.08");
        com.ibm.ws.webservices.engine.description.FieldDesc field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("salesDoc");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "SalesDoc"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("salesDocItem");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "SalesDocItem"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "numeric6"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("delivery");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Delivery"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("deliveryItem");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "DeliveryItem"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "numeric6"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("deliveryStatus");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "DeliveryStatus"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char1"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("postGoodsIssueDate");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "PostGoodsIssueDate"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "date10"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("deliveryHighItem");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "DeliveryHighItem"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "numeric6"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("shipDate");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "ShipDate"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "date10"));
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
          new ZsqtcDeliveries_Ser(
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
          new ZsqtcDeliveries_Deser(
            javaType, xmlType, typeDesc);
    };

}
