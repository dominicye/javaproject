/**
 * GetEvolveSalesOrderDetailVSLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf151104.08 v2211211011
 */

package com.avnet.alapps.webservice.evolvesalesorderdetail;

public class GetEvolveSalesOrderDetailVSLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, com.avnet.alapps.webservice.evolvesalesorderdetail.GetEvolveSalesOrderDetailVS {

    public GetEvolveSalesOrderDetailVSLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "urn:sap-com:document:sap:soap:functions:mc-style",
           "getEvolveSalesOrderDetailVS"));

        context.setLocatorName("com.avnet.alapps.webservice.evolvesalesorderdetail.GetEvolveSalesOrderDetailVSLocator");
    }

    public GetEvolveSalesOrderDetailVSLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("com.avnet.alapps.webservice.evolvesalesorderdetail.GetEvolveSalesOrderDetailVSLocator");
    }

    // Use to get a proxy class for getEvolveSalesOrderDetailVSsoaphttp
    private final java.lang.String getEvolveSalesOrderDetailVSsoaphttp_address = "http://wmx-svs-prod.avnet.com:5555/ws/getEvolveSalesOrderDetailVS";

    public java.lang.String getGetEvolveSalesOrderDetailVSsoaphttpAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return getEvolveSalesOrderDetailVSsoaphttp_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("getEvolveSalesOrderDetailVSsoaphttp");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return getEvolveSalesOrderDetailVSsoaphttp_address;
        }
    }

    private java.lang.String getEvolveSalesOrderDetailVSsoaphttpPortName = "getEvolveSalesOrderDetailVSsoaphttp";

    // The WSDD port name defaults to the port name.
    private java.lang.String getEvolveSalesOrderDetailVSsoaphttpWSDDPortName = "getEvolveSalesOrderDetailVSsoaphttp";

    public java.lang.String getGetEvolveSalesOrderDetailVSsoaphttpWSDDPortName() {
        return getEvolveSalesOrderDetailVSsoaphttpWSDDPortName;
    }

    public void setGetEvolveSalesOrderDetailVSsoaphttpWSDDPortName(java.lang.String name) {
        getEvolveSalesOrderDetailVSsoaphttpWSDDPortName = name;
    }

    public com.avnet.alapps.webservice.evolvesalesorderdetail.ZWS_QTC_SALESORDER_GETDETAIL getGetEvolveSalesOrderDetailVSsoaphttp() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getGetEvolveSalesOrderDetailVSsoaphttpAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // unlikely as URL was validated in WSDL2Java
        }
        return getGetEvolveSalesOrderDetailVSsoaphttp(endpoint);
    }

    public com.avnet.alapps.webservice.evolvesalesorderdetail.ZWS_QTC_SALESORDER_GETDETAIL getGetEvolveSalesOrderDetailVSsoaphttp(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        com.avnet.alapps.webservice.evolvesalesorderdetail.ZWS_QTC_SALESORDER_GETDETAIL _stub =
            (com.avnet.alapps.webservice.evolvesalesorderdetail.ZWS_QTC_SALESORDER_GETDETAIL) getStub(
                getEvolveSalesOrderDetailVSsoaphttpPortName,
                (String) getPort2NamespaceMap().get(getEvolveSalesOrderDetailVSsoaphttpPortName),
                com.avnet.alapps.webservice.evolvesalesorderdetail.ZWS_QTC_SALESORDER_GETDETAIL.class,
                "com.avnet.alapps.webservice.evolvesalesorderdetail.Binding_T_HTTP_A_HTTP_ZWS_QTC_SALESORDER_GETDETAIL_PROFILE_LStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(getEvolveSalesOrderDetailVSsoaphttpWSDDPortName);
        }
        return _stub;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.avnet.alapps.webservice.evolvesalesorderdetail.ZWS_QTC_SALESORDER_GETDETAIL.class.isAssignableFrom(serviceEndpointInterface)) {
                return getGetEvolveSalesOrderDetailVSsoaphttp();
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("WSWS3273E: Error: There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        String inputPortName = portName.getLocalPart();
        if ("getEvolveSalesOrderDetailVSsoaphttp".equals(inputPortName)) {
            return getGetEvolveSalesOrderDetailVSsoaphttp();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        getEvolveSalesOrderDetailVSsoaphttpWSDDPortName = prefix + "/" + getEvolveSalesOrderDetailVSsoaphttpPortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("urn:sap-com:document:sap:soap:functions:mc-style", "getEvolveSalesOrderDetailVS");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "getEvolveSalesOrderDetailVSsoaphttp",
               "http://schemas.xmlsoap.org/wsdl/soap/");
        }
        return port2NamespaceMap;
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            String serviceNamespace = getServiceName().getNamespaceURI();
            for (java.util.Iterator i = getPort2NamespaceMap().keySet().iterator(); i.hasNext(); ) {
                ports.add(
                    com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                        serviceNamespace,
                        (String) i.next()));
            }
        }
        return ports.iterator();
    }

    public javax.xml.rpc.Call[] getCalls(javax.xml.namespace.QName portName) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName should not be null.");
        }
        if  (portName.getLocalPart().equals("getEvolveSalesOrderDetailVSsoaphttp")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "ZqtcSalesorderGetdetail", "null"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName should not be null.");
        }
    }
}
