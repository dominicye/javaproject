/**
 * ZsqtcInvoices_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf151104.08 v2211211011
 */

package com.avnet.alapps.webservice.evolvesalesorderdetail;

public class ZsqtcInvoices_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public ZsqtcInvoices_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new ZsqtcInvoices();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_15) {
          ((ZsqtcInvoices)value).setSalesDoc(strValue);
          return true;}
        else if (qName==QName_0_16) {
          ((ZsqtcInvoices)value).setSalesDocItem(strValue);
          return true;}
        else if (qName==QName_0_24) {
          ((ZsqtcInvoices)value).setInvoiceNo(strValue);
          return true;}
        else if (qName==QName_0_25) {
          ((ZsqtcInvoices)value).setInvoiceItem(strValue);
          return true;}
        else if (qName==QName_0_26) {
          ((ZsqtcInvoices)value).setBillingType(strValue);
          return true;}
        else if (qName==QName_0_27) {
          ((ZsqtcInvoices)value).setInvoiceDate(strValue);
          return true;}
        else if (qName==QName_0_28) {
          ((ZsqtcInvoices)value).setInvoiceAmount(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseBigDecimal(strValue));
          return true;}
        else if (qName==QName_0_3) {
          ((ZsqtcInvoices)value).setCurrency(strValue);
          return true;}
        else if (qName==QName_0_29) {
          ((ZsqtcInvoices)value).setInvoiceQuantity(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseBigDecimal(strValue));
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
    private final static javax.xml.namespace.QName QName_0_24 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "InvoiceNo");
    private final static javax.xml.namespace.QName QName_0_16 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "SalesDocItem");
    private final static javax.xml.namespace.QName QName_0_25 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "InvoiceItem");
    private final static javax.xml.namespace.QName QName_0_27 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "InvoiceDate");
    private final static javax.xml.namespace.QName QName_0_3 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Currency");
    private final static javax.xml.namespace.QName QName_0_28 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "InvoiceAmount");
    private final static javax.xml.namespace.QName QName_0_15 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "SalesDoc");
    private final static javax.xml.namespace.QName QName_0_29 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "InvoiceQuantity");
    private final static javax.xml.namespace.QName QName_0_26 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "BillingType");
}
