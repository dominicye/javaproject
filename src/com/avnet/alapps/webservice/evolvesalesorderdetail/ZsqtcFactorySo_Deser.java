/**
 * ZsqtcFactorySo_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf151104.08 v2211211011
 */

package com.avnet.alapps.webservice.evolvesalesorderdetail;

public class ZsqtcFactorySo_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public ZsqtcFactorySo_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new ZsqtcFactorySo();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_15) {
          ((ZsqtcFactorySo)value).setSalesDoc(strValue);
          return true;}
        else if (qName==QName_0_0) {
          ((ZsqtcFactorySo)value).setLineitemNo(strValue);
          return true;}
        else if (qName==QName_0_23) {
          ((ZsqtcFactorySo)value).setFactorySo(strValue);
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
    private final static javax.xml.namespace.QName QName_0_15 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "SalesDoc");
    private final static javax.xml.namespace.QName QName_0_23 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "FactorySo");
}
