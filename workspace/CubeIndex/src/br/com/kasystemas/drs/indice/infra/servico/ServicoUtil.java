package br.com.kasystemas.drs.indice.infra.servico;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.xml.rpc.ServiceException;

import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.axis.types.URI.MalformedURIException;
import org.globus.index.stubs.Cube.CubeIndexPortType;
import org.globus.index.stubs.Cube.service.CubeIndexServiceAddressingLocator;

public class ServicoUtil {
	
	public static final String CUBE_INDEX_SERVICE = "cubeindexservice";
	public static final String CUBE_SERVICE = "cubeservice";

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
	
	public CubeIndexPortType obterEndpointCubeIndexService()
			throws MalformedURIException, ServiceException {
		CubeIndexServiceAddressingLocator locator = new CubeIndexServiceAddressingLocator();

		// Create endpoint reference to service
		EndpointReferenceType endpoint = new EndpointReferenceType();
		endpoint.setAddress(new Address(getServiceURI(CUBE_INDEX_SERVICE)));
		
		// Get PortType
		CubeIndexPortType cubeIndex = locator.getCubeIndexPortTypePort(endpoint);
		return cubeIndex;
	}

}
