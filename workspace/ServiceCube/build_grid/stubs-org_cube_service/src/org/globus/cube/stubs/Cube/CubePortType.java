/**
 * CubePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.cube.stubs.Cube;

public interface CubePortType extends java.rmi.Remote {
    public org.globus.cube.stubs.Cube.CubeCollResponse getCubeColl(org.globus.cube.stubs.Cube.GetCubeColl parameters) throws java.rmi.RemoteException;
    public org.globus.cube.stubs.Cube.PrintCubeResponse printCube(int parameters) throws java.rmi.RemoteException;
    public org.globus.cube.stubs.Cube.ExecuteQueryResponse executeQuery(org.globus.cube.stubs.Cube.ExecuteQuery parameters) throws java.rmi.RemoteException;
    public org.globus.cube.stubs.Cube.CubeMetadataResponse getCubeMetaData(int parameters) throws java.rmi.RemoteException;
    public boolean addCube(org.globus.cube.stubs.Cube.AddCube parameters) throws java.rmi.RemoteException;
    public org.globus.cube.stubs.Cube.RemoveCubeResponse removeCube(int parameters) throws java.rmi.RemoteException;
    public boolean setChavePrimaria(org.globus.cube.stubs.Cube.SetChavePrimaria parameters) throws java.rmi.RemoteException;
    public boolean setLigacao(org.globus.cube.stubs.Cube.SetLigacao parameters) throws java.rmi.RemoteException;
    public org.oasis.wsrf.properties.SetResourcePropertiesResponse setResourceProperties(org.oasis.wsrf.properties.SetResourceProperties_Element setResourcePropertiesRequest) throws java.rmi.RemoteException, org.oasis.wsrf.properties.InvalidSetResourcePropertiesRequestContentFaultType, org.oasis.wsrf.properties.InvalidResourcePropertyQNameFaultType, org.oasis.wsrf.properties.ResourceUnknownFaultType, org.oasis.wsrf.properties.SetResourcePropertyRequestFailedFaultType, org.oasis.wsrf.properties.UnableToModifyResourcePropertyFaultType;
    public org.oasis.wsrf.properties.QueryResourcePropertiesResponse queryResourceProperties(org.oasis.wsrf.properties.QueryResourceProperties_Element queryResourcePropertiesRequest) throws java.rmi.RemoteException, org.oasis.wsrf.properties.UnknownQueryExpressionDialectFaultType, org.oasis.wsrf.properties.InvalidResourcePropertyQNameFaultType, org.oasis.wsrf.properties.InvalidQueryExpressionFaultType, org.oasis.wsrf.properties.QueryEvaluationErrorFaultType, org.oasis.wsrf.properties.ResourceUnknownFaultType;
    public org.oasis.wsrf.properties.GetResourcePropertyResponse getResourceProperty(javax.xml.namespace.QName getResourcePropertyRequest) throws java.rmi.RemoteException, org.oasis.wsrf.properties.InvalidResourcePropertyQNameFaultType, org.oasis.wsrf.properties.ResourceUnknownFaultType;
    public org.oasis.wsrf.properties.GetMultipleResourcePropertiesResponse getMultipleResourceProperties(org.oasis.wsrf.properties.GetMultipleResourceProperties_Element getMultipleResourcePropertiesRequest) throws java.rmi.RemoteException, org.oasis.wsrf.properties.InvalidResourcePropertyQNameFaultType, org.oasis.wsrf.properties.ResourceUnknownFaultType;
}
