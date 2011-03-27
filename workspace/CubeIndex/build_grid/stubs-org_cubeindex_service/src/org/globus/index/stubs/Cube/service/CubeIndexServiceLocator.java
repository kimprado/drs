/**
 * CubeIndexServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.index.stubs.Cube.service;

public class CubeIndexServiceLocator extends org.apache.axis.client.Service implements org.globus.index.stubs.Cube.service.CubeIndexService {

    public CubeIndexServiceLocator() {
    }


    public CubeIndexServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CubeIndexServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CubeIndexPortTypePort
    private java.lang.String CubeIndexPortTypePort_address = "http://localhost:8080/wsrf/services/";

    public java.lang.String getCubeIndexPortTypePortAddress() {
        return CubeIndexPortTypePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CubeIndexPortTypePortWSDDServiceName = "CubeIndexPortTypePort";

    public java.lang.String getCubeIndexPortTypePortWSDDServiceName() {
        return CubeIndexPortTypePortWSDDServiceName;
    }

    public void setCubeIndexPortTypePortWSDDServiceName(java.lang.String name) {
        CubeIndexPortTypePortWSDDServiceName = name;
    }

    public org.globus.index.stubs.Cube.CubeIndexPortType getCubeIndexPortTypePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CubeIndexPortTypePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCubeIndexPortTypePort(endpoint);
    }

    public org.globus.index.stubs.Cube.CubeIndexPortType getCubeIndexPortTypePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.globus.index.stubs.Cube.bindings.CubeIndexPortTypeSOAPBindingStub _stub = new org.globus.index.stubs.Cube.bindings.CubeIndexPortTypeSOAPBindingStub(portAddress, this);
            _stub.setPortName(getCubeIndexPortTypePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCubeIndexPortTypePortEndpointAddress(java.lang.String address) {
        CubeIndexPortTypePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.globus.index.stubs.Cube.CubeIndexPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                org.globus.index.stubs.Cube.bindings.CubeIndexPortTypeSOAPBindingStub _stub = new org.globus.index.stubs.Cube.bindings.CubeIndexPortTypeSOAPBindingStub(new java.net.URL(CubeIndexPortTypePort_address), this);
                _stub.setPortName(getCubeIndexPortTypePortWSDDServiceName());
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
        if ("CubeIndexPortTypePort".equals(inputPortName)) {
            return getCubeIndexPortTypePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube/service", "CubeIndexService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube/service", "CubeIndexPortTypePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        if ("CubeIndexPortTypePort".equals(portName)) {
            setCubeIndexPortTypePortEndpointAddress(address);
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
