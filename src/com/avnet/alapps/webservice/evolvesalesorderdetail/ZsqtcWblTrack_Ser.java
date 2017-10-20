/**
 * ZsqtcWblTrack_Ser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf151104.08 v2211211011
 */

package com.avnet.alapps.webservice.evolvesalesorderdetail;

public class ZsqtcWblTrack_Ser extends com.ibm.ws.webservices.engine.encoding.ser.BeanSerializer {
    /**
     * Constructor
     */
    public ZsqtcWblTrack_Ser(
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
        ZsqtcWblTrack bean = (ZsqtcWblTrack) value;
        java.lang.Object propValue;
        javax.xml.namespace.QName propQName;
        {
          propQName = QName_0_102;
          propValue = bean.getVbeln();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_14,
              true,null,context);
          }
          propQName = QName_0_103;
          propValue = bean.getZseqno();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_110,
              true,null,context);
          }
          propQName = QName_0_104;
          propValue = bean.getZdocno();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_14,
              true,null,context);
          }
          propQName = QName_0_105;
          propValue = bean.getZcarrname();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_67,
              true,null,context);
          }
          propQName = QName_0_106;
          propValue = bean.getZshpdate();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_6,
              true,null,context);
          }
          propQName = QName_0_107;
          propValue = bean.getZwaybill();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_68,
              true,null,context);
          }
          propQName = QName_0_108;
          propValue = bean.getZweight();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_73,
              true,null,context);
          }
          propQName = QName_0_109;
          propValue = bean.getZfrieght();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_111,
              true,null,context);
        }
    }
    private final static javax.xml.namespace.QName QName_1_6 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "date10");
    private final static javax.xml.namespace.QName QName_1_73 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char15");
    private final static javax.xml.namespace.QName QName_0_108 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Zweight");
    private final static javax.xml.namespace.QName QName_1_68 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char30");
    private final static javax.xml.namespace.QName QName_0_104 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Zdocno");
    private final static javax.xml.namespace.QName QName_1_111 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "curr13.2");
    private final static javax.xml.namespace.QName QName_0_102 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Vbeln");
    private final static javax.xml.namespace.QName QName_0_107 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Zwaybill");
    private final static javax.xml.namespace.QName QName_1_14 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char10");
    private final static javax.xml.namespace.QName QName_1_67 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char40");
    private final static javax.xml.namespace.QName QName_0_103 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Zseqno");
    private final static javax.xml.namespace.QName QName_0_106 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Zshpdate");
    private final static javax.xml.namespace.QName QName_0_109 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Zfrieght");
    private final static javax.xml.namespace.QName QName_1_110 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "numeric3");
    private final static javax.xml.namespace.QName QName_0_105 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Zcarrname");
}
