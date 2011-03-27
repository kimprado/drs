/**
 * CubeIndexPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.index.stubs.Cube;

public interface CubeIndexPortType extends java.rmi.Remote {
    public org.globus.index.stubs.Cube.CubeListResponse getCubeList(org.globus.index.stubs.Cube.GetCubeList parameters) throws java.rmi.RemoteException;
    public org.globus.index.stubs.Cube.CubeEntryResponse getCubeEntry(int parameters) throws java.rmi.RemoteException;
    public int addCubeEntry(org.globus.index.stubs.Cube.CubeEntry parameters) throws java.rmi.RemoteException;
    public boolean removeEntry(int parameters) throws java.rmi.RemoteException;
    public boolean refreshCube(int parameters) throws java.rmi.RemoteException;
    public org.oasis.wsrf.properties.SetResourcePropertiesResponse setResourceProperties(org.oasis.wsrf.properties.SetResourceProperties_Element setResourcePropertiesRequest) throws java.rmi.RemoteException, org.oasis.wsrf.properties.InvalidSetResourcePropertiesRequestContentFaultType, org.oasis.wsrf.properties.InvalidResourcePropertyQNameFaultType, org.oasis.wsrf.properties.ResourceUnknownFaultType, org.oasis.wsrf.properties.SetResourcePropertyRequestFailedFaultType, org.oasis.wsrf.properties.UnableToModifyResourcePropertyFaultType;
    public org.oasis.wsrf.properties.QueryResourcePropertiesResponse queryResourceProperties(org.oasis.wsrf.properties.QueryResourceProperties_Element queryResourcePropertiesRequest) throws java.rmi.RemoteException, org.oasis.wsrf.properties.UnknownQueryExpressionDialectFaultType, org.oasis.wsrf.properties.InvalidResourcePropertyQNameFaultType, org.oasis.wsrf.properties.InvalidQueryExpressionFaultType, org.oasis.wsrf.properties.QueryEvaluationErrorFaultType, org.oasis.wsrf.properties.ResourceUnknownFaultType;
    public org.oasis.wsrf.properties.GetResourcePropertyResponse getResourceProperty(javax.xml.namespace.QName getResourcePropertyRequest) throws java.rmi.RemoteException, org.oasis.wsrf.properties.InvalidResourcePropertyQNameFaultType, org.oasis.wsrf.properties.ResourceUnknownFaultType;
    public org.oasis.wsrf.properties.GetMultipleResourcePropertiesResponse getMultipleResourceProperties(org.oasis.wsrf.properties.GetMultipleResourceProperties_Element getMultipleResourcePropertiesRequest) throws java.rmi.RemoteException, org.oasis.wsrf.properties.InvalidResourcePropertyQNameFaultType, org.oasis.wsrf.properties.ResourceUnknownFaultType;
}
