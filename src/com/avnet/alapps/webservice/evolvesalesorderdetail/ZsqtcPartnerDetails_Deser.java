/**
 * ZsqtcPartnerDetails_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf151104.08 v2211211011
 */

package com.avnet.alapps.webservice.evolvesalesorderdetail;

public class ZsqtcPartnerDetails_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public ZsqtcPartnerDetails_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new ZsqtcPartnerDetails();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_87) {
          ((ZsqtcPartnerDetails)value).setPartnerNo(strValue);
          return true;}
        else if (qName==QName_0_88) {
          ((ZsqtcPartnerDetails)value).setPartnerType(strValue);
          return true;}
        else if (qName==QName_0_89) {
          ((ZsqtcPartnerDetails)value).setName(strValue);
          return true;}
        else if (qName==QName_0_90) {
          ((ZsqtcPartnerDetails)value).setName2(strValue);
          return true;}
        else if (qName==QName_0_91) {
          ((ZsqtcPartnerDetails)value).setAddress1(strValue);
          return true;}
        else if (qName==QName_0_92) {
          ((ZsqtcPartnerDetails)value).setAddress2(strValue);
          return true;}
        else if (qName==QName_0_93) {
          ((ZsqtcPartnerDetails)value).setAddress3(strValue);
          return true;}
        else if (qName==QName_0_94) {
          ((ZsqtcPartnerDetails)value).setCity(strValue);
          return true;}
        else if (qName==QName_0_95) {
          ((ZsqtcPartnerDetails)value).setState(strValue);
          return true;}
        else if (qName==QName_0_96) {
          ((ZsqtcPartnerDetails)value).setCountry(strValue);
          return true;}
        else if (qName==QName_0_97) {
          ((ZsqtcPartnerDetails)value).setPostalCode(strValue);
          return true;}
        else if (qName==QName_0_98) {
          ((ZsqtcPartnerDetails)value).setTelNumber(strValue);
          return true;}
        else if (qName==QName_0_99) {
          ((ZsqtcPartnerDetails)value).setFaxNumber(strValue);
          return true;}
        else if (qName==QName_0_100) {
          ((ZsqtcPartnerDetails)value).setEmailId(strValue);
          return true;}
        return false;
    }
    protected boolean tryAttributeSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        return false;
    }
    protected boolean tryElementSetFromObject(javax.xml.namespace.QName qName, java.lang.Object objValue) {
        return false;
    }
    protected boolean tryElementSetFromList(javax.xml.namespace.QName qName, java.util.List listValue) {
        return false;
    }
    private final static javax.xml.namespace.QName QName_0_98 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "TelNumber");
    private final static javax.xml.namespace.QName QName_0_88 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "PartnerType");
    private final static javax.xml.namespace.QName QName_0_90 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Name2");
    private final static javax.xml.namespace.QName QName_0_89 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Name");
    private final static javax.xml.namespace.QName QName_0_99 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "FaxNumber");
    private final static javax.xml.namespace.QName QName_0_87 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "PartnerNo");
    private final static javax.xml.namespace.QName QName_0_94 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "City");
    private final static javax.xml.namespace.QName QName_0_93 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Address3");
    private final static javax.xml.namespace.QName QName_0_92 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Address2");
    private final static javax.xml.namespace.QName QName_0_95 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "State");
    private final static javax.xml.namespace.QName QName_0_91 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Address1");
    private final static javax.xml.namespace.QName QName_0_100 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "EmailId");
    private final static javax.xml.namespace.QName QName_0_97 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "PostalCode");
    private final static javax.xml.namespace.QName QName_0_96 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Country");
}
