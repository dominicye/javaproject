/**
 * ZsqtcScheduleDetails_Ser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf151104.08 v2211211011
 */

package com.avnet.alapps.webservice.evolvesalesorderdetail;

public class ZsqtcScheduleDetails_Ser extends com.ibm.ws.webservices.engine.encoding.ser.BeanSerializer {
    /**
     * Constructor
     */
    public ZsqtcScheduleDetails_Ser(
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
        ZsqtcScheduleDetails bean = (ZsqtcScheduleDetails) value;
        java.lang.Object propValue;
        javax.xml.namespace.QName propQName;
        {
          propQName = QName_0_0;
          propValue = bean.getLineitemNo();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_5,
              true,null,context);
          }
          propQName = QName_0_78;
          propValue = bean.getReqDate();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_6,
              true,null,context);
          }
          propQName = QName_0_79;
          propValue = bean.getReqQty();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_69,
              true,null,context);
          propQName = QName_0_80;
          propValue = bean.getConfirQty();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_31,
              true,null,context);
        }
    }
    private final static javax.xml.namespace.QName QName_1_69 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "quantum15.3");
    private final static javax.xml.namespace.QName QName_0_0 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "LineitemNo");
    private final static javax.xml.namespace.QName QName_1_5 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "numeric6");
    private final static javax.xml.namespace.QName QName_1_31 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "quantum13.3");
    private final static javax.xml.namespace.QName QName_0_79 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ReqQty");
    private final static javax.xml.namespace.QName QName_1_6 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "date10");
    private final static javax.xml.namespace.QName QName_0_78 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ReqDate");
    private final static javax.xml.namespace.QName QName_0_80 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ConfirQty");
}
