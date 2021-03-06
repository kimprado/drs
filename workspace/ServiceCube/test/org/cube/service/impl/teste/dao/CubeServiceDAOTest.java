package org.cube.service.impl.teste.dao;

import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.globus.cube.stubs.Cube.CubePortType;
import org.globus.cube.stubs.Cube.service.CubeServiceAddressingLocator;

public class CubeServiceDAOTest {
	
	public CubePortType getPotType(/*s*/) {
		
		CubeServiceAddressingLocator locator = new CubeServiceAddressingLocator();
		
		try {
			
			// Create endpoint reference to service
			EndpointReferenceType endpoint = new EndpointReferenceType();
			endpoint.setAddress(new Address(WebServiceDAOTest.getServiceURI(WebServiceDAOTest.CUBESERVICEURI)));
			
			// Get PortType
			CubePortType cube = locator.getCubePortTypePort(endpoint);
			return cube;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
