/**
 * DRSService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.drs.stubs.Cube.service;

public interface DRSService extends javax.xml.rpc.Service {
    public java.lang.String getDRSPortTypePortAddress();

    public org.globus.drs.stubs.Cube.DRSPortType getDRSPortTypePort() throws javax.xml.rpc.ServiceException;

    public org.globus.drs.stubs.Cube.DRSPortType getDRSPortTypePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
