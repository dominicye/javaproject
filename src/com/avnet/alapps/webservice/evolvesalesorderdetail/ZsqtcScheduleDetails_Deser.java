/**
 * ZsqtcScheduleDetails_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf151104.08 v2211211011
 */

package com.avnet.alapps.webservice.evolvesalesorderdetail;

public class ZsqtcScheduleDetails_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public ZsqtcScheduleDetails_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new ZsqtcScheduleDetails();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_0) {
          ((ZsqtcScheduleDetails)value).setLineitemNo(strValue);
          return true;}
        else if (qName==QName_0_78) {
          ((ZsqtcScheduleDetails)value).setReqDate(strValue);
          return true;}
        else if (qName==QName_0_79) {
          ((ZsqtcScheduleDetails)value).setReqQty(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseBigDecimal(strValue));
          return true;}
        else if (qName==QName_0_80) {
          ((ZsqtcScheduleDetails)value).setConfirQty(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseBigDecimal(strValue));
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
    private final static javax.xml.namespace.QName QName_0_0 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "LineitemNo");
    private final static javax.xml.namespace.QName QName_0_79 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ReqQty");
    private final static javax.xml.namespace.QName QName_0_78 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ReqDate");
    private final static javax.xml.namespace.QName QName_0_80 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ConfirQty");
}
