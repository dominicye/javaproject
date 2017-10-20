/**
 * ZsqtcInvoices_Ser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf151104.08 v2211211011
 */

package com.avnet.alapps.webservice.evolvesalesorderdetail;

public class ZsqtcInvoices_Ser extends com.ibm.ws.webservices.engine.encoding.ser.BeanSerializer {
    /**
     * Constructor
     */
    public ZsqtcInvoices_Ser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    public void serialize(
        javax.xml.namespace.QName name,
        org.xml.sax.Attributes attributes,
        java.lang.Object value,
        com.ibm.ws.webservices.engine.encoding.SerializationContext context)
        throws java.io.IOException
    {
        context.startElement(name, addAttributes(attributes, value, context));
        addElements(value, context);
        context.endElement();
    }
    protected org.xml.sax.Attributes addAttributes(
        org.xml.sax.Attributes attributes,
        java.lang.Object value,
        com.ibm.ws.webservices.engine.encoding.SerializationContext context)
        throws java.io.IOException
    {
        return attributes;
    }
    protected void addElements(
        java.lang.Object value,
        com.ibm.ws.webservices.engine.encoding.SerializationContext context)
        throws java.io.IOException
    {
        ZsqtcInvoices bean = (ZsqtcInvoices) value;
        java.lang.Object propValue;
        javax.xml.namespace.QName propQName;
        {
          propQName = QName_0_15;
          propValue = bean.getSalesDoc();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_14,
              true,null,context);
          }
          propQName = QName_0_16;
          propValue = bean.getSalesDocItem();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_5,
              true,null,context);
          }
          propQName = QName_0_24;
          propValue = bean.getInvoiceNo();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_14,
              true,null,context);
          }
          propQName = QName_0_25;
          propValue = bean.getInvoiceItem();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_5,
              true,null,context);
          }
          propQName = QName_0_26;
          propValue = bean.getBillingType();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_30,
              true,null,context);
          }
          propQName = QName_0_27;
          propValue = bean.getInvoiceDate();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_6,
              true,null,context);
          }
          propQName = QName_0_28;
          propValue = bean.getInvoiceAmount();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_7,
              true,null,context);
          propQName = QName_0_3;
          propValue = bean.getCurrency();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_8,
              true,null,context);
          }
          propQName = QName_0_29;
          propValue = bean.getInvoiceQuantity();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_31,
              true,null,context);
        }
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
    private final static javax.xml.namespace.QName QName_1_6 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "date10");
    private final static javax.xml.namespace.QName QName_1_5 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "numeric6");
    private final static javax.xml.namespace.QName QName_1_30 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char4");
    private final static javax.xml.namespace.QName QName_1_8 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "cuky5");
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
    private final static javax.xml.namespace.QName QName_1_14 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char10");
    private final static javax.xml.namespace.QName QName_1_31 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "quantum13.3");
    private final static javax.xml.namespace.QName QName_0_15 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "SalesDoc");
    private final static javax.xml.namespace.QName QName_0_29 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "InvoiceQuantity");
    private final static javax.xml.namespace.QName QName_1_7 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "curr15.2");
    private final static javax.xml.namespace.QName QName_0_26 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "BillingType");
}
