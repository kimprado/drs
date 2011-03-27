/**
 * DRSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.drs.stubs.Cube.service;

public class DRSServiceLocator extends org.apache.axis.client.Service implements org.globus.drs.stubs.Cube.service.DRSService {

    public DRSServiceLocator() {
    }


    public DRSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DRSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for DRSPortTypePort
    private java.lang.String DRSPortTypePort_address = "http://localhost:8080/wsrf/services/";

    public java.lang.String getDRSPortTypePortAddress() {
        return DRSPortTypePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DRSPortTypePortWSDDServiceName = "DRSPortTypePort";

    public java.lang.String getDRSPortTypePortWSDDServiceName() {
        return DRSPortTypePortWSDDServiceName;
    }

    public void setDRSPortTypePortWSDDServiceName(java.lang.String name) {
        DRSPortTypePortWSDDServiceName = name;
    }

    public org.globus.drs.stubs.Cube.DRSPortType getDRSPortTypePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(DRSPortTypePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDRSPortTypePort(endpoint);
    }

    public org.globus.drs.stubs.Cube.DRSPortType getDRSPortTypePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.globus.drs.stubs.Cube.bindings.DRSPortTypeSOAPBindingStub _stub = new org.globus.drs.stubs.Cube.bindings.DRSPortTypeSOAPBindingStub(portAddress, this);
            _stub.setPortName(getDRSPortTypePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDRSPortTypePortEndpointAddress(java.lang.String address) {
        DRSPortTypePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.globus.drs.stubs.Cube.DRSPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                org.globus.drs.stubs.Cube.bindings.DRSPortTypeSOAPBindingStub _stub = new org.globus.drs.stubs.Cube.bindings.DRSPortTypeSOAPBindingStub(new java.net.URL(DRSPortTypePort_address), this);
                _stub.setPortName(getDRSPortTypePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("DRSPortTypePort".equals(inputPortName)) {
            return getDRSPortTypePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube/service", "DRSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube/service", "DRSPortTypePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        if ("DRSPortTypePort".equals(portName)) {
            setDRSPortTypePortEndpointAddress(address);
        }
        else { // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
