/**
 * DRSPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Nov 14, 2006 (10:23:53 EST) WSDL2Java emitter.
 */

package org.globus.drs.stubs.Cube;

public interface DRSPortType extends java.rmi.Remote {
    public boolean addCampo(org.globus.drs.stubs.Cube.AddCampo parameters) throws java.rmi.RemoteException;
    public int criarConsulta(org.globus.drs.stubs.Cube.CriarConsulta parameters) throws java.rmi.RemoteException;
    public int verificarConsulta(org.globus.drs.stubs.Cube.VerificarConsulta parameters) throws java.rmi.RemoteException;
    public java.lang.String getSQL(int parameters) throws java.rmi.RemoteException;
    public boolean removeCampo(org.globus.drs.stubs.Cube.RemoveCampo parameters) throws java.rmi.RemoteException;
    public org.globus.drs.stubs.Cube.GetCampoResponse getCampo(org.globus.drs.stubs.Cube.GetCampo parameters) throws java.rmi.RemoteException;
    public org.globus.drs.stubs.Cube.ResumoResponse getResumo(org.globus.drs.stubs.Cube.GetResumo parameters) throws java.rmi.RemoteException;
    public boolean salvarOrdemCampos(org.globus.drs.stubs.Cube.SalvarOrdemCampos parameters) throws java.rmi.RemoteException;
    public boolean salvarOrdemDados(org.globus.drs.stubs.Cube.SalvarOrdemDados parameters) throws java.rmi.RemoteException;
    public org.oasis.wsrf.properties.SetResourcePropertiesResponse setResourceProperties(org.oasis.wsrf.properties.SetResourceProperties_Element setResourcePropertiesRequest) throws java.rmi.RemoteException, org.oasis.wsrf.properties.InvalidSetResourcePropertiesRequestContentFaultType, org.oasis.wsrf.properties.InvalidResourcePropertyQNameFaultType, org.oasis.wsrf.properties.ResourceUnknownFaultType, org.oasis.wsrf.properties.SetResourcePropertyRequestFailedFaultType, org.oasis.wsrf.properties.UnableToModifyResourcePropertyFaultType;
    public org.oasis.wsrf.properties.QueryResourcePropertiesResponse queryResourceProperties(org.oasis.wsrf.properties.QueryResourceProperties_Element queryResourcePropertiesRequest) throws java.rmi.RemoteException, org.oasis.wsrf.properties.UnknownQueryExpressionDialectFaultType, org.oasis.wsrf.properties.InvalidResourcePropertyQNameFaultType, org.oasis.wsrf.properties.InvalidQueryExpressionFaultType, org.oasis.wsrf.properties.QueryEvaluationErrorFaultType, org.oasis.wsrf.properties.ResourceUnknownFaultType;
    public org.oasis.wsrf.properties.GetResourcePropertyResponse getResourceProperty(javax.xml.namespace.QName getResourcePropertyRequest) throws java.rmi.RemoteException, org.oasis.wsrf.properties.InvalidResourcePropertyQNameFaultType, org.oasis.wsrf.properties.ResourceUnknownFaultType;
    public org.oasis.wsrf.properties.GetMultipleResourcePropertiesResponse getMultipleResourceProperties(org.oasis.wsrf.properties.GetMultipleResourceProperties_Element getMultipleResourcePropertiesRequest) throws java.rmi.RemoteException, org.oasis.wsrf.properties.InvalidResourcePropertyQNameFaultType, org.oasis.wsrf.properties.ResourceUnknownFaultType;
}
