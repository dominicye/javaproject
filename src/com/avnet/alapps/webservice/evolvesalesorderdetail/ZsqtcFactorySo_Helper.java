/**
 * ZsqtcFactorySo_Helper.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf151104.08 v2211211011
 */

package com.avnet.alapps.webservice.evolvesalesorderdetail;

public class ZsqtcFactorySo_Helper {
    // Type metadata
    private static final com.ibm.ws.webservices.engine.description.TypeDesc typeDesc =
        new com.ibm.ws.webservices.engine.description.TypeDesc(ZsqtcFactorySo.class);

    static {
        typeDesc.setOption("buildNum","cf151104.08");
        com.ibm.ws.webservices.engine.description.FieldDesc field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("salesDoc");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "SalesDoc"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("lineitemNo");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "LineitemNo"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "numeric6"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("factorySo");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "FactorySo"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10"));
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
          new ZsqtcFactorySo_Ser(
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
          new ZsqtcFactorySo_Deser(
            javaType, xmlType, typeDesc);
    };

}
