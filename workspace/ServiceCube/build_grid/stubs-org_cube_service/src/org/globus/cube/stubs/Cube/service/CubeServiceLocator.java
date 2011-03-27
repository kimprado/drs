/**
 * CubeServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.cube.stubs.Cube.service;

public class CubeServiceLocator extends org.apache.axis.client.Service implements org.globus.cube.stubs.Cube.service.CubeService {

    public CubeServiceLocator() {
    }


    public CubeServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CubeServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CubePortTypePort
    private java.lang.String CubePortTypePort_address = "http://localhost:8080/wsrf/services/";

    public java.lang.String getCubePortTypePortAddress() {
        return CubePortTypePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CubePortTypePortWSDDServiceName = "CubePortTypePort";

    public java.lang.String getCubePortTypePortWSDDServiceName() {
        return CubePortTypePortWSDDServiceName;
    }

    public void setCubePortTypePortWSDDServiceName(java.lang.String name) {
        CubePortTypePortWSDDServiceName = name;
    }

    public org.globus.cube.stubs.Cube.CubePortType getCubePortTypePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CubePortTypePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCubePortTypePort(endpoint);
    }

    public org.globus.cube.stubs.Cube.CubePortType getCubePortTypePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.globus.cube.stubs.Cube.bindings.CubePortTypeSOAPBindingStub _stub = new org.globus.cube.stubs.Cube.bindings.CubePortTypeSOAPBindingStub(portAddress, this);
            _stub.setPortName(getCubePortTypePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCubePortTypePortEndpointAddress(java.lang.String address) {
        CubePortTypePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.globus.cube.stubs.Cube.CubePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                org.globus.cube.stubs.Cube.bindings.CubePortTypeSOAPBindingStub _stub = new org.globus.cube.stubs.Cube.bindings.CubePortTypeSOAPBindingStub(new java.net.URL(CubePortTypePort_address), this);
                _stub.setPortName(getCubePortTypePortWSDDServiceName());
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
        if ("CubePortTypePort".equals(inputPortName)) {
            return getCubePortTypePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube/service", "CubeService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/Cube/service", "CubePortTypePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        if ("CubePortTypePort".equals(portName)) {
            setCubePortTypePortEndpointAddress(address);
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
