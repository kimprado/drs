package br.com.kasystemas.drsclienteweb.dao;

import java.rmi.RemoteException;

import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.cube.service.impl.modelo.Cubo;
import org.globus.cube.stubs.Cube.AddCube;
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
	
	/**
	 * Consultar
	 * @param cuboParametro
	 * @return
	 * @throws RemoteException
	 */
	public Cubo consultarCubo( Cubo cuboParametro ) throws RemoteException {
		CubeMetadataResponse metaDados = getPortType().getCubeMetaData( cuboParametro.getId() );
		Cubo cubo = cuboFromMetadados(metaDados);
		return cubo;
	}
	
	/**
	 * Incluir
	 * @param cuboParametro
	 * @return
	 * @throws RemoteException
	 */
	public boolean incluirCubo( Cubo cb ) throws RemoteException {
		AddCube addCube = new AddCube(null,"",1l,"","","","");
		
		CubeMetaData cubeMetaData = new CubeMetaData();
		cubeMetaData.setId( cb.getId()== null ? 0 :cb.getId() );
		cubeMetaData.setName( cb.getNome() );
		cubeMetaData.setUri( cb.getURIService() );
		cubeMetaData.setUser( cb.getConnectionUser() );
		cubeMetaData.setPassword( cb.getConnectionPassword() );
		cubeMetaData.setConnectionUrl( cb.getConnectionUrl() );
		cubeMetaData.setDriver( cb.getDriver() );
		cubeMetaData.setMillisecond( cb.getRefresh() );
		
		addCube.setCube(cubeMetaData);
		
		boolean inclusaoRealizada = getPortType().addCube( addCube );
		
		return inclusaoRealizada;
	}

	/**
	 * @param metaDados
	 * @return
	 */
	public Cubo cuboFromMetadados(CubeMetadataResponse metaDados) {
		Cubo cubo = new Cubo();
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
