/**
 * ZsqtcPartnerDetails_Helper.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf151104.08 v2211211011
 */

package com.avnet.alapps.webservice.evolvesalesorderdetail;

public class ZsqtcPartnerDetails_Helper {
    // Type metadata
    private static final com.ibm.ws.webservices.engine.description.TypeDesc typeDesc =
        new com.ibm.ws.webservices.engine.description.TypeDesc(ZsqtcPartnerDetails.class);

    static {
        typeDesc.setOption("buildNum","cf151104.08");
        com.ibm.ws.webservices.engine.description.FieldDesc field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("partnerNo");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "PartnerNo"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("partnerType");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "PartnerType"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char2"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("name");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Name"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char30"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("name2");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Name2"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char30"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("address1");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Address1"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char30"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("address2");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Address2"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char40"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("address3");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Address3"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char40"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("city");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "City"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char25"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("state");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "State"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char3"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("country");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Country"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char3"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("postalCode");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "PostalCode"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("telNumber");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "TelNumber"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char30"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("faxNumber");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "FaxNumber"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char30"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("emailId");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "EmailId"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char241"));
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
          new ZsqtcPartnerDetails_Ser(
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
          new ZsqtcPartnerDetails_Deser(
            javaType, xmlType, typeDesc);
    };

}
