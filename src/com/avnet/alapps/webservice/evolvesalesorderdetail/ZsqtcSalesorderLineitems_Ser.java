/**
 * ZsqtcSalesorderLineitems_Ser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf151104.08 v2211211011
 */

package com.avnet.alapps.webservice.evolvesalesorderdetail;

public class ZsqtcSalesorderLineitems_Ser extends com.ibm.ws.webservices.engine.encoding.ser.BeanSerializer {
    /**
     * Constructor
     */
    public ZsqtcSalesorderLineitems_Ser(
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
        ZsqtcSalesorderLineitems bean = (ZsqtcSalesorderLineitems) value;
        java.lang.Object propValue;
        javax.xml.namespace.QName propQName;
        {
          propQName = QName_0_32;
          propValue = bean.getRmaExptDt();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_6,
              true,null,context);
          }
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
          propQName = QName_0_33;
          propValue = bean.getMaterialNo();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_65,
              true,null,context);
          }
          propQName = QName_0_34;
          propValue = bean.getCustPartNo();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_66,
              true,null,context);
          }
          propQName = QName_0_35;
          propValue = bean.getManfPartNo();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_67,
              true,null,context);
          }
          propQName = QName_0_36;
          propValue = bean.getManfNumber();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_14,
              true,null,context);
          }
          propQName = QName_0_37;
          propValue = bean.getManfName();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_68,
              true,null,context);
          }
          propQName = QName_0_38;
          propValue = bean.getBomPartNo();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_5,
              true,null,context);
          }
          propQName = QName_0_39;
          propValue = bean.getItemCategory();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_30,
              true,null,context);
          }
          propQName = QName_0_40;
          propValue = bean.getQtyOrdered();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_69,
              true,null,context);
          propQName = QName_0_41;
          propValue = bean.getSrvQtyOrdered();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_31,
              true,null,context);
          propQName = QName_0_42;
          propValue = bean.getProcessingStatus();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_9,
              true,null,context);
          }
          propQName = QName_0_43;
          propValue = bean.getRejectionStatus();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_9,
              true,null,context);
          }
          propQName = QName_0_44;
          propValue = bean.getRejectionReason();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_70,
              true,null,context);
          }
          propQName = QName_0_45;
          propValue = bean.getResaleUnitPrice();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_71,
              true,null,context);
          propQName = QName_0_46;
          propValue = bean.getExtUnitPrice();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_71,
              true,null,context);
          propQName = QName_0_47;
          propValue = bean.getPartDescription();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_67,
              true,null,context);
          }
          propQName = QName_0_48;
          propValue = bean.getProfitCenter();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_14,
              true,null,context);
          }
          propQName = QName_0_49;
          propValue = bean.getStorageLoc();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_30,
              true,null,context);
          }
          propQName = QName_0_50;
          propValue = bean.getPlant();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_30,
              true,null,context);
          }
          propQName = QName_0_51;
          propValue = bean.getPurchasingGrp();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_72,
              true,null,context);
          }
          propQName = QName_0_52;
          propValue = bean.getVendSaleOrdr();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_73,
              true,null,context);
          }
          propQName = QName_0_53;
          propValue = bean.getCustomerPo();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_66,
              true,null,context);
          }
          propQName = QName_0_54;
          propValue = bean.getPoItmNo();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_74,
              true,null,context);
          }
          propQName = QName_0_55;
          propValue = bean.getConfigId();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_75,
              true,null,context);
          }
          propQName = QName_0_56;
          propValue = bean.getWip();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_76,
              true,null,context);
          }
          propQName = QName_0_57;
          propValue = bean.getDropshipPo();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_14,
              true,null,context);
          }
          propQName = QName_0_58;
          propValue = bean.getAgreementId();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_77,
              true,null,context);
          }
          propQName = QName_0_59;
          propValue = bean.getVarId();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_73,
              true,null,context);
          }
          propQName = QName_0_60;
          propValue = bean.getEstShipDate();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_6,
              true,null,context);
          }
          propQName = QName_0_61;
          propValue = bean.getHgLvItem();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_5,
              true,null,context);
          }
          propQName = QName_0_62;
          propValue = bean.getFaSerialno();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_14,
              true,null,context);
          }
          propQName = QName_0_63;
          propValue = bean.getWebOrdItemno();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_75,
              true,null,context);
          }
          propQName = QName_0_64;
          propValue = bean.getZzCustref();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_14,
              true,null,context);
          }
        }
    }
    private final static javax.xml.namespace.QName QName_2_75 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:soap:functions:mc-style",
                  "char20");
    private final static javax.xml.namespace.QName QName_0_0 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "LineitemNo");
    private final static javax.xml.namespace.QName QName_0_39 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ItemCategory");
    private final static javax.xml.namespace.QName QName_0_50 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Plant");
    private final static javax.xml.namespace.QName QName_1_66 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char35");
    private final static javax.xml.namespace.QName QName_0_52 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "VendSaleOrdr");
    private final static javax.xml.namespace.QName QName_0_45 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ResaleUnitPrice");
    private final static javax.xml.namespace.QName QName_1_69 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "quantum15.3");
    private final static javax.xml.namespace.QName QName_0_42 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ProcessingStatus");
    private final static javax.xml.namespace.QName QName_1_6 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "date10");
    private final static javax.xml.namespace.QName QName_1_68 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char30");
    private final static javax.xml.namespace.QName QName_0_59 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "VarId");
    private final static javax.xml.namespace.QName QName_2_14 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:soap:functions:mc-style",
                  "char10");
    private final static javax.xml.namespace.QName QName_0_61 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "HgLvItem");
    private final static javax.xml.namespace.QName QName_0_37 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ManfName");
    private final static javax.xml.namespace.QName QName_0_64 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ZzCustref");
    private final static javax.xml.namespace.QName QName_0_44 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "RejectionReason");
    private final static javax.xml.namespace.QName QName_1_5 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "numeric6");
    private final static javax.xml.namespace.QName QName_0_48 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ProfitCenter");
    private final static javax.xml.namespace.QName QName_0_51 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "PurchasingGrp");
    private final static javax.xml.namespace.QName QName_0_47 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "PartDescription");
    private final static javax.xml.namespace.QName QName_0_57 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "DropshipPo");
    private final static javax.xml.namespace.QName QName_1_74 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char6");
    private final static javax.xml.namespace.QName QName_1_71 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "curr11.2");
    private final static javax.xml.namespace.QName QName_0_33 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "MaterialNo");
    private final static javax.xml.namespace.QName QName_1_73 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char15");
    private final static javax.xml.namespace.QName QName_1_65 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char18");
    private final static javax.xml.namespace.QName QName_0_56 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "Wip");
    private final static javax.xml.namespace.QName QName_0_60 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "EstShipDate");
    private final static javax.xml.namespace.QName QName_0_53 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "CustomerPo");
    private final static javax.xml.namespace.QName QName_0_34 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "CustPartNo");
    private final static javax.xml.namespace.QName QName_1_14 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char10");
    private final static javax.xml.namespace.QName QName_1_67 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char40");
    private final static javax.xml.namespace.QName QName_0_38 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "BomPartNo");
    private final static javax.xml.namespace.QName QName_0_32 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "RmaExptDt");
    private final static javax.xml.namespace.QName QName_0_40 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "QtyOrdered");
    private final static javax.xml.namespace.QName QName_0_36 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ManfNumber");
    private final static javax.xml.namespace.QName QName_0_35 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ManfPartNo");
    private final static javax.xml.namespace.QName QName_0_43 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "RejectionStatus");
    private final static javax.xml.namespace.QName QName_0_55 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ConfigId");
    private final static javax.xml.namespace.QName QName_1_31 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "quantum13.3");
    private final static javax.xml.namespace.QName QName_1_77 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char25");
    private final static javax.xml.namespace.QName QName_0_41 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "SrvQtyOrdered");
    private final static javax.xml.namespace.QName QName_1_9 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char1");
    private final static javax.xml.namespace.QName QName_1_72 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char3");
    private final static javax.xml.namespace.QName QName_1_70 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char2");
    private final static javax.xml.namespace.QName QName_1_76 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char5");
    private final static javax.xml.namespace.QName QName_0_63 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "WebOrdItemno");
    private final static javax.xml.namespace.QName QName_1_30 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char4");
    private final static javax.xml.namespace.QName QName_0_54 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "PoItmNo");
    private final static javax.xml.namespace.QName QName_1_75 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "urn:sap-com:document:sap:rfc:functions",
                  "char20");
    private final static javax.xml.namespace.QName QName_0_49 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "StorageLoc");
    private final static javax.xml.namespace.QName QName_0_62 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "FaSerialno");
    private final static javax.xml.namespace.QName QName_0_58 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "AgreementId");
    private final static javax.xml.namespace.QName QName_0_46 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ExtUnitPrice");
}
