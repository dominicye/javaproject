/**
 * Binding_T_HTTP_A_HTTP_ZWS_QTC_SALESORDER_GETDETAIL_PROFILE_LStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf151104.08 v2211211011
 */

package com.avnet.alapps.webservice.evolvesalesorderdetail;

public class Binding_T_HTTP_A_HTTP_ZWS_QTC_SALESORDER_GETDETAIL_PROFILE_LStub extends com.ibm.ws.webservices.engine.client.Stub implements com.avnet.alapps.webservice.evolvesalesorderdetail.ZWS_QTC_SALESORDER_GETDETAIL {
    public Binding_T_HTTP_A_HTTP_ZWS_QTC_SALESORDER_GETDETAIL_PROFILE_LStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        }
        else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[1];
        java.lang.String theOption = (java.lang.String)super._getProperty("lastStubMapping");
        if (theOption == null || !theOption.equals("com.avnet.alapps.webservice.evolvesalesorderdetail.Binding_T_HTTP_A_HTTP_ZWS_QTC_SALESORDER_GETDETAIL_PROFILE_L")) {
                initTypeMapping();
                super._setProperty("lastStubMapping","com.avnet.alapps.webservice.evolvesalesorderdetail.Binding_T_HTTP_A_HTTP_ZWS_QTC_SALESORDER_GETDETAIL_PROFILE_L");
        }
        super.cachedEndpoint = endpointURL;
        super.connection = ((com.ibm.ws.webservices.engine.client.Service) super.service).getConnection(endpointURL);
    }

    private void initTypeMapping() {
        javax.xml.rpc.encoding.TypeMapping tm = super.getTypeMapping(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
        java.lang.Class javaType = null;
        javax.xml.namespace.QName xmlType = null;
        javax.xml.namespace.QName compQName = null;
        javax.xml.namespace.QName compTypeQName = null;
        com.ibm.ws.webservices.engine.encoding.SerializerFactory sf = null;
        com.ibm.ws.webservices.engine.encoding.DeserializerFactory df = null;
        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char1");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char12");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char15");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char18");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char2");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char20");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char22");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char241");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char25");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char3");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char30");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char35");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char4");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char40");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char5");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char6");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "cuky5");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.math.BigDecimal.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "curr11.2");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "decimal");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.math.BigDecimal.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "curr13.2");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "decimal");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.math.BigDecimal.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "curr15.2");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "decimal");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "date10");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "numeric2");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "numeric3");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "numeric6");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.math.BigDecimal.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "quantum13.3");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "decimal");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.math.BigDecimal.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "quantum15.3");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "decimal");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "char10");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "char20");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "char3");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = java.lang.String.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "char4");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleSerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcBillingplan.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcBillingplan");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcContract.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcContract");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcDeliveries.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcDeliveries");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcFactorySo.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcFactorySo");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcInvoices.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcInvoices");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcSalesorderLineitems.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcSalesorderLineitems");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcScheduleDetails.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcScheduleDetails");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcSalesorderSerialNo.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcSalesorderSerialNo");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcPartnerDetails.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcPartnerDetails");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcWblTrack.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcWblTrack");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcBillingplan[].class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcBillingplan");
        compQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "item");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcBillingplan");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArraySerializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArrayDeserializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcContract[].class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcContract");
        compQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "item");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcContract");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArraySerializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArrayDeserializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcDeliveries[].class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcDeliveries");
        compQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "item");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcDeliveries");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArraySerializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArrayDeserializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcFactorySo[].class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcFactorySo");
        compQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "item");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcFactorySo");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArraySerializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArrayDeserializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcInvoices[].class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcInvoices");
        compQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "item");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcInvoices");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArraySerializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArrayDeserializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcSalesorderLineitems[].class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcSalesorderLineitems");
        compQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "item");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcSalesorderLineitems");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArraySerializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArrayDeserializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcScheduleDetails[].class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcScheduleDetails");
        compQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "item");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcScheduleDetails");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArraySerializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArrayDeserializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcSalesorderSerialNo[].class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcSalesorderSerialNo");
        compQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "item");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcSalesorderSerialNo");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArraySerializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArrayDeserializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcPartnerDetails[].class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcPartnerDetails");
        compQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "item");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcPartnerDetails");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArraySerializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArrayDeserializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcWblTrack[].class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcWblTrack");
        compQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "item");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZsqtcWblTrack");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArraySerializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArrayDeserializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _zqtcSalesorderGetdetailOperation0 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getzqtcSalesorderGetdetailOperation0() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "SalesOrderNo"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "SearchType"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char2"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "WebOrderNumber"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "char20"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Acctchannel"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "char3"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "BillingPlan"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "BillingPlandetails"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcBillingplan"), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcBillingplan[].class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "ContractDetails"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcContract"), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcContract[].class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "CreditStatus"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char1"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "CsrName"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char40"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Currency"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "cuky5"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "CustomerPo"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char35"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "CustomerRefNo"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char12"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "DeliveryNos"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcDeliveries"), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcDeliveries[].class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "EndUserPo"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char35"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "FactorySo"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcFactorySo"), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcFactorySo[].class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "GovtContractNo"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char22"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "InvoiceNos"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcInvoices"), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcInvoices[].class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderAmount"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "curr15.2"), java.math.BigDecimal.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderDate"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "date10"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderItemsOut"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcSalesorderLineitems"), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcSalesorderLineitems[].class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderNo"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderSchedule"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcScheduleDetails"), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcScheduleDetails[].class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderSerialnoOut"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcSalesorderSerialNo"), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcSalesorderSerialNo[].class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderType"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char4"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OverallStatus"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char1"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "PartnerDetails"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcPartnerDetails"), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcPartnerDetails[].class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "PayTerm"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "char4"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "R2oNumber"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char10"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "ReleaseNo"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char12"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "RollupFlag"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "char3"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "ShippingAttention"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:rfc:functions", "char40"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "WayBill"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZttqtcWblTrack"), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcWblTrack[].class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("inputPosition","0");
        _params0[0].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char10");
        _params0[0].setOption("partName","char10");
        _params0[1].setOption("inputPosition","1");
        _params0[1].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char2");
        _params0[1].setOption("partName","char2");
        _params0[2].setOption("inputPosition","2");
        _params0[2].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}char20");
        _params0[2].setOption("partName","char20");
        _params0[3].setOption("outputPosition","0");
        _params0[3].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}char3");
        _params0[3].setOption("partName","char3");
        _params0[4].setOption("outputPosition","1");
        _params0[4].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char10");
        _params0[4].setOption("partName","char10");
        _params0[5].setOption("outputPosition","2");
        _params0[5].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}ZttqtcBillingplan");
        _params0[5].setOption("partName","ZttqtcBillingplan");
        _params0[6].setOption("outputPosition","3");
        _params0[6].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}ZttqtcContract");
        _params0[6].setOption("partName","ZttqtcContract");
        _params0[7].setOption("outputPosition","4");
        _params0[7].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char1");
        _params0[7].setOption("partName","char1");
        _params0[8].setOption("outputPosition","5");
        _params0[8].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char40");
        _params0[8].setOption("partName","char40");
        _params0[9].setOption("outputPosition","6");
        _params0[9].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}cuky5");
        _params0[9].setOption("partName","cuky5");
        _params0[10].setOption("outputPosition","7");
        _params0[10].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char35");
        _params0[10].setOption("partName","char35");
        _params0[11].setOption("outputPosition","8");
        _params0[11].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char12");
        _params0[11].setOption("partName","char12");
        _params0[12].setOption("outputPosition","9");
        _params0[12].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}ZttqtcDeliveries");
        _params0[12].setOption("partName","ZttqtcDeliveries");
        _params0[13].setOption("outputPosition","10");
        _params0[13].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char35");
        _params0[13].setOption("partName","char35");
        _params0[14].setOption("outputPosition","11");
        _params0[14].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}ZttqtcFactorySo");
        _params0[14].setOption("partName","ZttqtcFactorySo");
        _params0[15].setOption("outputPosition","12");
        _params0[15].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char22");
        _params0[15].setOption("partName","char22");
        _params0[16].setOption("outputPosition","13");
        _params0[16].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}ZttqtcInvoices");
        _params0[16].setOption("partName","ZttqtcInvoices");
        _params0[17].setOption("outputPosition","14");
        _params0[17].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}curr15.2");
        _params0[17].setOption("partName","curr15.2");
        _params0[18].setOption("outputPosition","15");
        _params0[18].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}date10");
        _params0[18].setOption("partName","date10");
        _params0[19].setOption("outputPosition","16");
        _params0[19].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}ZttqtcSalesorderLineitems");
        _params0[19].setOption("partName","ZttqtcSalesorderLineitems");
        _params0[20].setOption("outputPosition","17");
        _params0[20].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char10");
        _params0[20].setOption("partName","char10");
        _params0[21].setOption("outputPosition","18");
        _params0[21].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}ZttqtcScheduleDetails");
        _params0[21].setOption("partName","ZttqtcScheduleDetails");
        _params0[22].setOption("outputPosition","19");
        _params0[22].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}ZttqtcSalesorderSerialNo");
        _params0[22].setOption("partName","ZttqtcSalesorderSerialNo");
        _params0[23].setOption("outputPosition","20");
        _params0[23].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char4");
        _params0[23].setOption("partName","char4");
        _params0[24].setOption("outputPosition","21");
        _params0[24].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char1");
        _params0[24].setOption("partName","char1");
        _params0[25].setOption("outputPosition","22");
        _params0[25].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}ZttqtcPartnerDetails");
        _params0[25].setOption("partName","ZttqtcPartnerDetails");
        _params0[26].setOption("outputPosition","23");
        _params0[26].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}char4");
        _params0[26].setOption("partName","char4");
        _params0[27].setOption("outputPosition","24");
        _params0[27].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char10");
        _params0[27].setOption("partName","char10");
        _params0[28].setOption("outputPosition","25");
        _params0[28].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char12");
        _params0[28].setOption("partName","char12");
        _params0[29].setOption("outputPosition","26");
        _params0[29].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}char3");
        _params0[29].setOption("partName","char3");
        _params0[30].setOption("outputPosition","27");
        _params0[30].setOption("partQNameString","{urn:sap-com:document:sap:rfc:functions}char40");
        _params0[30].setOption("partName","char40");
        _params0[31].setOption("outputPosition","28");
        _params0[31].setOption("partQNameString","{urn:sap-com:document:sap:soap:functions:mc-style}ZttqtcWblTrack");
        _params0[31].setOption("partName","ZttqtcWblTrack");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(null, com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://websphere.ibm.com/webservices/", "Void"), void.class, true, false, false, false, true, true); 
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _zqtcSalesorderGetdetailOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("zqtcSalesorderGetdetail", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZqtcSalesorderGetdetail"), _params0, _returnDesc0, _faults0, "urn:sap-com:document:sap:soap:functions:mc-style:ZWS_QTC_SALESORDER_GETDETAIL:ZqtcSalesorderGetdetailRequest");
        _zqtcSalesorderGetdetailOperation0.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZWS_QTC_SALESORDER_GETDETAIL"));
        _zqtcSalesorderGetdetailOperation0.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZqtcSalesorderGetdetailResponse"));
        _zqtcSalesorderGetdetailOperation0.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "getEvolveSalesOrderDetailVS"));
        _zqtcSalesorderGetdetailOperation0.setOption("buildNum","cf151104.08");
        _zqtcSalesorderGetdetailOperation0.setOption("ResponseNamespace","urn:sap-com:document:sap:soap:functions:mc-style");
        _zqtcSalesorderGetdetailOperation0.setOption("targetNamespace","urn:sap-com:document:sap:soap:functions:mc-style");
        _zqtcSalesorderGetdetailOperation0.setOption("ResponseLocalPart","ZqtcSalesorderGetdetailResponse");
        _zqtcSalesorderGetdetailOperation0.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "ZqtcSalesorderGetdetail"));
        _zqtcSalesorderGetdetailOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _zqtcSalesorderGetdetailOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _zqtcSalesorderGetdetailOperation0;

    }

    private int _zqtcSalesorderGetdetailIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getzqtcSalesorderGetdetailInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_zqtcSalesorderGetdetailIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(Binding_T_HTTP_A_HTTP_ZWS_QTC_SALESORDER_GETDETAIL_PROFILE_LStub._zqtcSalesorderGetdetailOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("urn:sap-com:document:sap:soap:functions:mc-style:ZWS_QTC_SALESORDER_GETDETAIL:ZqtcSalesorderGetdetailRequest");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_zqtcSalesorderGetdetailIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public void zqtcSalesorderGetdetail(java.lang.String salesOrderNo, java.lang.String searchType, java.lang.String webOrderNumber, javax.xml.rpc.holders.StringHolder acctchannel, javax.xml.rpc.holders.StringHolder billingPlan, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcBillingplanHolder billingPlandetails, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcContractHolder contractDetails, javax.xml.rpc.holders.StringHolder creditStatus, javax.xml.rpc.holders.StringHolder csrName, javax.xml.rpc.holders.StringHolder currency, javax.xml.rpc.holders.StringHolder customerPo, javax.xml.rpc.holders.StringHolder customerRefNo, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcDeliveriesHolder deliveryNos, javax.xml.rpc.holders.StringHolder endUserPo, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcFactorySoHolder factorySo, javax.xml.rpc.holders.StringHolder govtContractNo, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcInvoicesHolder invoiceNos, javax.xml.rpc.holders.BigDecimalHolder orderAmount, javax.xml.rpc.holders.StringHolder orderDate, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcSalesorderLineitemsHolder orderItemsOut, javax.xml.rpc.holders.StringHolder orderNo, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcScheduleDetailsHolder orderSchedule, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcSalesorderSerialNoHolder orderSerialnoOut, javax.xml.rpc.holders.StringHolder orderType, javax.xml.rpc.holders.StringHolder overallStatus, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcPartnerDetailsHolder partnerDetails, javax.xml.rpc.holders.StringHolder payTerm, javax.xml.rpc.holders.StringHolder r2ONumber, javax.xml.rpc.holders.StringHolder releaseNo, javax.xml.rpc.holders.StringHolder rollupFlag, javax.xml.rpc.holders.StringHolder shippingAttention, com.avnet.alapps.webservice.evolvesalesorderdetail.holders.ZttqtcWblTrackHolder wayBill) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getzqtcSalesorderGetdetailInvoke0(new java.lang.Object[] {salesOrderNo, searchType, webOrderNumber}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        for (int _i = 0; _i < _resp.size(); ++_i) {
            com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue _param = (com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(_i);
            if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Acctchannel").equals(_param.getQName())) {
                try {
                    acctchannel.value = (java.lang.String) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    acctchannel.value = (java.lang.String) super.convert(_param.getValue(), java.lang.String.class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "BillingPlan").equals(_param.getQName())) {
                try {
                    billingPlan.value = (java.lang.String) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    billingPlan.value = (java.lang.String) super.convert(_param.getValue(), java.lang.String.class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "BillingPlandetails").equals(_param.getQName())) {
                try {
                    billingPlandetails.value = (com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcBillingplan[]) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    billingPlandetails.value = (com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcBillingplan[]) super.convert(_param.getValue(), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcBillingplan[].class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "ContractDetails").equals(_param.getQName())) {
                try {
                    contractDetails.value = (com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcContract[]) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    contractDetails.value = (com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcContract[]) super.convert(_param.getValue(), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcContract[].class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "CreditStatus").equals(_param.getQName())) {
                try {
                    creditStatus.value = (java.lang.String) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    creditStatus.value = (java.lang.String) super.convert(_param.getValue(), java.lang.String.class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "CsrName").equals(_param.getQName())) {
                try {
                    csrName.value = (java.lang.String) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    csrName.value = (java.lang.String) super.convert(_param.getValue(), java.lang.String.class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Currency").equals(_param.getQName())) {
                try {
                    currency.value = (java.lang.String) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    currency.value = (java.lang.String) super.convert(_param.getValue(), java.lang.String.class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "CustomerPo").equals(_param.getQName())) {
                try {
                    customerPo.value = (java.lang.String) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    customerPo.value = (java.lang.String) super.convert(_param.getValue(), java.lang.String.class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "CustomerRefNo").equals(_param.getQName())) {
                try {
                    customerRefNo.value = (java.lang.String) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    customerRefNo.value = (java.lang.String) super.convert(_param.getValue(), java.lang.String.class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "DeliveryNos").equals(_param.getQName())) {
                try {
                    deliveryNos.value = (com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcDeliveries[]) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    deliveryNos.value = (com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcDeliveries[]) super.convert(_param.getValue(), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcDeliveries[].class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "EndUserPo").equals(_param.getQName())) {
                try {
                    endUserPo.value = (java.lang.String) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    endUserPo.value = (java.lang.String) super.convert(_param.getValue(), java.lang.String.class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "FactorySo").equals(_param.getQName())) {
                try {
                    factorySo.value = (com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcFactorySo[]) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    factorySo.value = (com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcFactorySo[]) super.convert(_param.getValue(), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcFactorySo[].class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "GovtContractNo").equals(_param.getQName())) {
                try {
                    govtContractNo.value = (java.lang.String) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    govtContractNo.value = (java.lang.String) super.convert(_param.getValue(), java.lang.String.class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "InvoiceNos").equals(_param.getQName())) {
                try {
                    invoiceNos.value = (com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcInvoices[]) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    invoiceNos.value = (com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcInvoices[]) super.convert(_param.getValue(), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcInvoices[].class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderAmount").equals(_param.getQName())) {
                try {
                    orderAmount.value = (java.math.BigDecimal) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    orderAmount.value = (java.math.BigDecimal) super.convert(_param.getValue(), java.math.BigDecimal.class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderDate").equals(_param.getQName())) {
                try {
                    orderDate.value = (java.lang.String) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    orderDate.value = (java.lang.String) super.convert(_param.getValue(), java.lang.String.class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderItemsOut").equals(_param.getQName())) {
                try {
                    orderItemsOut.value = (com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcSalesorderLineitems[]) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    orderItemsOut.value = (com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcSalesorderLineitems[]) super.convert(_param.getValue(), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcSalesorderLineitems[].class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderNo").equals(_param.getQName())) {
                try {
                    orderNo.value = (java.lang.String) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    orderNo.value = (java.lang.String) super.convert(_param.getValue(), java.lang.String.class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderSchedule").equals(_param.getQName())) {
                try {
                    orderSchedule.value = (com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcScheduleDetails[]) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    orderSchedule.value = (com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcScheduleDetails[]) super.convert(_param.getValue(), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcScheduleDetails[].class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderSerialnoOut").equals(_param.getQName())) {
                try {
                    orderSerialnoOut.value = (com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcSalesorderSerialNo[]) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    orderSerialnoOut.value = (com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcSalesorderSerialNo[]) super.convert(_param.getValue(), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcSalesorderSerialNo[].class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OrderType").equals(_param.getQName())) {
                try {
                    orderType.value = (java.lang.String) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    orderType.value = (java.lang.String) super.convert(_param.getValue(), java.lang.String.class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "OverallStatus").equals(_param.getQName())) {
                try {
                    overallStatus.value = (java.lang.String) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    overallStatus.value = (java.lang.String) super.convert(_param.getValue(), java.lang.String.class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "PartnerDetails").equals(_param.getQName())) {
                try {
                    partnerDetails.value = (com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcPartnerDetails[]) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    partnerDetails.value = (com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcPartnerDetails[]) super.convert(_param.getValue(), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcPartnerDetails[].class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "PayTerm").equals(_param.getQName())) {
                try {
                    payTerm.value = (java.lang.String) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    payTerm.value = (java.lang.String) super.convert(_param.getValue(), java.lang.String.class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "R2oNumber").equals(_param.getQName())) {
                try {
                    r2ONumber.value = (java.lang.String) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    r2ONumber.value = (java.lang.String) super.convert(_param.getValue(), java.lang.String.class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "ReleaseNo").equals(_param.getQName())) {
                try {
                    releaseNo.value = (java.lang.String) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    releaseNo.value = (java.lang.String) super.convert(_param.getValue(), java.lang.String.class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "RollupFlag").equals(_param.getQName())) {
                try {
                    rollupFlag.value = (java.lang.String) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    rollupFlag.value = (java.lang.String) super.convert(_param.getValue(), java.lang.String.class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "ShippingAttention").equals(_param.getQName())) {
                try {
                    shippingAttention.value = (java.lang.String) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    shippingAttention.value = (java.lang.String) super.convert(_param.getValue(), java.lang.String.class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "WayBill").equals(_param.getQName())) {
                try {
                    wayBill.value = (com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcWblTrack[]) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    wayBill.value = (com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcWblTrack[]) super.convert(_param.getValue(), com.avnet.alapps.webservice.evolvesalesorderdetail.ZsqtcWblTrack[].class);
                }
            }
        }
    }

    private static void _staticInit() {
        _zqtcSalesorderGetdetailOperation0 = _getzqtcSalesorderGetdetailOperation0();
    }

    static {
       _staticInit();
    }
}
