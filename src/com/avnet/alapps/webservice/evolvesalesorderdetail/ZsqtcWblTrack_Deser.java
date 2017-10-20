/**
 * ZsqtcWblTrack_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf151104.08 v2211211011
 */

package com.avnet.alapps.webservice.evolvesalesorderdetail;

public class ZsqtcWblTrack_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public ZsqtcWblTrack_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new ZsqtcWblTrack();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_102) {
          ((ZsqtcWblTrack)value).setVbeln(strValue);
          return true;}
        else if (qName==QName_0_103) {
          ((ZsqtcWblTrack)value).setZseqno(strValue);
          return true;}
        else if (qName==QName_0_104) {
          ((ZsqtcWblTrack)value).setZdocno(strValue);
          return true;}
        else if (qName==QName_0_105) {
          ((ZsqtcWblTrack)value).setZcarrname(strValue);
          return true;}
        else if (qName==QName_0_106) {
          ((ZsqtcWblTrack)value).setZshpdate(strValue);
          return true;}
        else if (qName==QName_0_107) {
          ((ZsqtcWblTrack)value).setZwaybill(strValue);
          return true;}
        else if (qName==QName_0_108) {
          ((ZsqtcWblTrack)value).setZweight(strValue);
          return true;}
        else if (qName==QName_0_109) {
          ((ZsqtcWblTrack)value).setZfrieght(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseBigDecimal(strValue));
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
    private final static javax.xml.namespace.QName QName_0_102 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Vbeln");
    private final static javax.xml.namespace.QName QName_0_104 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Zdocno");
    private final static javax.xml.namespace.QName QName_0_105 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Zcarrname");
    private final static javax.xml.namespace.QName QName_0_109 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Zfrieght");
    private final static javax.xml.namespace.QName QName_0_107 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Zwaybill");
    private final static javax.xml.namespace.QName QName_0_106 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Zshpdate");
    private final static javax.xml.namespace.QName QName_0_108 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Zweight");
    private final static javax.xml.namespace.QName QName_0_103 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Zseqno");
}
