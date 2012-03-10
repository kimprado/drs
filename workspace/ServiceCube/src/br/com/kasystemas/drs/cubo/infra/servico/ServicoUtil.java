package br.com.kasystemas.drs.cubo.infra.servico;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.xml.rpc.ServiceException;

import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.axis.types.URI.MalformedURIException;
import org.globus.cube.stubs.Cube.CubePortType;
import org.globus.cube.stubs.Cube.service.CubeServiceAddressingLocator;
import org.globus.drs.stubs.Cube.DRSPortType;
import org.globus.drs.stubs.Cube.service.DRSServiceAddressingLocator;
import org.globus.index.stubs.Cube.CubeIndexPortType;
import org.globus.index.stubs.Cube.service.CubeIndexServiceAddressingLocator;

public class ServicoUtil {
	
	public static final String CUBE_INDEX_SERVICE = "cubeindexservice";
	public static final String CUBE_SERVICE = "cubeservice";
	public static final String DRS_SERVICE = "drsservice";

	public String getServiceURI(String procurar) {
		try {
			FileReader reader = new FileReader(System.getenv("CUBO_CONF"));
			BufferedReader leitor = new BufferedReader(reader,1*1024*1024);
			String linha = null;
			while(leitor.ready()) {
				linha = leitor.readLine();
				if (linha.toCharArray().length > 0) {
					if(procurar.equalsIgnoreCase( String.copyValueOf(linha.toCharArray(),0,procurar.length()))) {
						return String.copyValueOf(linha.toCharArray(),procurar.length()+1,linha.length() - procurar.length()-1) ;
					}
				}
			}
			leitor.close();
			reader.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public CubeIndexPortType obterEndpointCubeIndexService() throws MalformedURIException, ServiceException {
		CubeIndexServiceAddressingLocator locator = new CubeIndexServiceAddressingLocator();

		// Create endpoint reference to service
		EndpointReferenceType endpoint = new EndpointReferenceType();
		endpoint.setAddress(new Address(getServiceURI(CUBE_INDEX_SERVICE)));
		
		// Get PortType
		CubeIndexPortType cubeIndex = locator.getCubeIndexPortTypePort(endpoint);
		return cubeIndex;
	}
	
	public CubePortType obterEndpointCubeService() throws MalformedURIException, ServiceException {
		CubeServiceAddressingLocator locator = new CubeServiceAddressingLocator();
		
		// Create endpoint reference to service
		EndpointReferenceType endpoint = new EndpointReferenceType();
		endpoint.setAddress(new Address(getServiceURI(CUBE_SERVICE)));
		
		// Get PortType
		CubePortType cube = locator.getCubePortTypePort(endpoint);
		return cube;
	}
	
	public DRSPortType obterEndpointDRSService() throws MalformedURIException, ServiceException {
		DRSServiceAddressingLocator locator = new DRSServiceAddressingLocator();
		
		// Create endpoint reference to service
		EndpointReferenceType endpoint = new EndpointReferenceType();
		endpoint.setAddress(new Address(getServiceURI(DRS_SERVICE)));
		
		// Get PortType
		DRSPortType drs = locator.getDRSPortTypePort(endpoint);
		return drs;
	}

}
