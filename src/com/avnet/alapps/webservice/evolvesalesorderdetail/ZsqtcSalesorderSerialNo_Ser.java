/**
 * ZsqtcSalesorderSerialNo_Ser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf151104.08 v2211211011
 */

package com.avnet.alapps.webservice.evolvesalesorderdetail;

public class ZsqtcSalesorderSerialNo_Ser extends com.ibm.ws.webservices.engine.encoding.ser.BeanSerializer {
    /**
     * Constructor
     */
    public ZsqtcSalesorderSerialNo_Ser(
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
        ZsqtcSalesorderSerialNo bean = (ZsqtcSalesorderSerialNo) value;
        java.lang.Object propValue;
        javax.xml.namespace.QName propQName;
        {
          propQName = QName_0_81;
          propValue = bean.getSalesOrderNo();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_14,
              true,null,context);
          }
          propQName = QName_0_82;
          propValue = bean.getSaleOrderLine();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_5,
              true,null,context);
          }
          propQName = QName_0_83;
          propValue = bean.getCounter();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_86,
              true,null,context);
          }
          propQName = QName_0_84;
          propValue = bean.getHardwarePn();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_65,
              true,null,context);
          }
          propQName = QName_0_85;
          propValue = bean.getSerialNo();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_67,
              true,null,context);
          }
        }
    }
    private final static javax.xml.namespace.QName QName_0_84 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "HardwarePn");
    private final static javax.xml.namespace.QName QName_0_81 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "SalesOrderNo");
    private final static javax.xml.namespace.QName QName_0_82 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "SaleOrderLine");
    private final static javax.xml.namespace.QName QName_1_5 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "numeric6");
    private final static javax.xml.namespace.QName QName_1_65 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char18");
    private final static javax.xml.namespace.QName QName_1_14 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char10");
    private final static javax.xml.namespace.QName QName_0_83 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Counter");
    private final static javax.xml.namespace.QName QName_1_86 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "numeric2");
    private final static javax.xml.namespace.QName QName_1_67 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char40");
    private final static javax.xml.namespace.QName QName_0_85 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "SerialNo");
}
