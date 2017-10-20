/**
 * ZsqtcWblTrack_Helper.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf151104.08 v2211211011
 */

package com.avnet.alapps.webservice.evolvesalesorderdetail;

public class ZsqtcWblTrack_Helper {
    // Type metadata
    private static final com.ibm.ws.webservices.engine.description.TypeDesc typeDesc =
        new com.ibm.ws.webservices.engine.description.TypeDesc(ZsqtcWblTrack.class);

    static {
        typeDesc.setOption("buildNum","cf151104.08");
        com.ibm.ws.webservices.engine.description.FieldDesc field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("vbeln");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Vbeln"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("zseqno");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Zseqno"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "numeric3"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("zdocno");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Zdocno"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("zcarrname");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Zcarrname"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char40"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("zshpdate");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Zshpdate"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "date10"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("zwaybill");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Zwaybill"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char30"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("zweight");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Zweight"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char15"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("zfrieght");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Zfrieght"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "curr13.2"));
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
          new ZsqtcWblTrack_Ser(
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
          new ZsqtcWblTrack_Deser(
            javaType, xmlType, typeDesc);
    };

}
