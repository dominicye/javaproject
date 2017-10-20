/**
 * ZsqtcDeliveries_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf151104.08 v2211211011
 */

package com.avnet.alapps.webservice.evolvesalesorderdetail;

public class ZsqtcDeliveries_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public ZsqtcDeliveries_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new ZsqtcDeliveries();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_15) {
          ((ZsqtcDeliveries)value).setSalesDoc(strValue);
          return true;}
        else if (qName==QName_0_16) {
          ((ZsqtcDeliveries)value).setSalesDocItem(strValue);
          return true;}
        else if (qName==QName_0_17) {
          ((ZsqtcDeliveries)value).setDelivery(strValue);
          return true;}
        else if (qName==QName_0_18) {
          ((ZsqtcDeliveries)value).setDeliveryItem(strValue);
          return true;}
        else if (qName==QName_0_19) {
          ((ZsqtcDeliveries)value).setDeliveryStatus(strValue);
          return true;}
        else if (qName==QName_0_20) {
          ((ZsqtcDeliveries)value).setPostGoodsIssueDate(strValue);
          return true;}
        else if (qName==QName_0_21) {
          ((ZsqtcDeliveries)value).setDeliveryHighItem(strValue);
          return true;}
        else if (qName==QName_0_22) {
          ((ZsqtcDeliveries)value).setShipDate(strValue);
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
    private final static javax.xml.namespace.QName QName_0_17 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Delivery");
    private final static javax.xml.namespace.QName QName_0_16 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "SalesDocItem");
    private final static javax.xml.namespace.QName QName_0_21 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "DeliveryHighItem");
    private final static javax.xml.namespace.QName QName_0_15 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "SalesDoc");
    private final static javax.xml.namespace.QName QName_0_22 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ShipDate");
    private final static javax.xml.namespace.QName QName_0_18 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "DeliveryItem");
    private final static javax.xml.namespace.QName QName_0_19 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "DeliveryStatus");
    private final static javax.xml.namespace.QName QName_0_20 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "PostGoodsIssueDate");
}
