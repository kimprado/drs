package br.com.kasystemas.drsclienteweb.dao;

import java.rmi.RemoteException;

import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.cube.service.impl.modelo.Cubo;
import org.globus.cube.stubs.Cube.CubeMetaData;
import org.globus.cube.stubs.Cube.CubeMetadataResponse;
import org.globus.cube.stubs.Cube.CubePortType;
import org.globus.cube.stubs.Cube.service.CubeServiceAddressingLocator;

public class CubeServiceDAO {
	
	public CubePortType getPortType(/*s*/) {
		
		CubeServiceAddressingLocator locator = new CubeServiceAddressingLocator();
		
		try {
			// Create endpoint reference to service
			EndpointReferenceType endpoint = new EndpointReferenceType();
			endpoint.setAddress(new Address(WebServiceDAO.getServiceURI(WebServiceDAO.CUBESERVICEURI)));
			
			
			// Get PortType
			CubePortType cube = locator.getCubePortTypePort(endpoint);
			return cube;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Cubo consultarCubo( Cubo cuboParametro ) throws RemoteException {
		Cubo cubo = new Cubo();
		
		CubeMetadataResponse metaDados = getPortType().getCubeMetaData( cuboParametro.getId() );
		
		CubeMetaData cubeMetaData = metaDados.getCubeMetaData();
		
		cubo.setId( cubeMetaData.getId() );
		cubo.setNome( cubeMetaData.getName() );
		cubo.setConnectionUrl( cubeMetaData.getConnectionUrl() );
		cubo.setConnectionUser( cubeMetaData.getUser() );
		cubo.setConnectionPassword( cubeMetaData.getPassword() );
		cubo.setDriver( cubeMetaData.getDriver() );
		cubo.setURIService( cubeMetaData.getUri() );
		cubo.setRefresh( cubeMetaData.getMillisecond() );
		
		return cubo;
	}
	
}
