package org.cube.service.impl.control.tarefas;

import java.util.TimerTask;
import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.cube.service.impl.control.CubeServiceControl;
import org.cube.service.impl.modelo.Cubo;
import org.globus.index.stubs.Cube.CubeIndexPortType;
import org.globus.index.stubs.Cube.service.CubeIndexServiceAddressingLocator;
 
public class TaskRefresh extends TimerTask {
	String a_IndexServiceURI = new String();
	int a_cubeEntry;
	Cubo a_cube;
	
	public TaskRefresh(String serviceURI, int cubeEntry, Cubo cube){
		a_IndexServiceURI = serviceURI; //IndexService
		a_cubeEntry = cubeEntry; //id local no Cube
		a_cube = cube;
	}
	
	public void run(){
		if (true) {
			CubeIndexServiceAddressingLocator locator = new CubeIndexServiceAddressingLocator();

			try {
				
				// Create endpoint reference to service
				EndpointReferenceType endpoint = new EndpointReferenceType();
				endpoint.setAddress(new Address(a_IndexServiceURI));
				
				// Get PortType
				CubeIndexPortType cubeIndex = locator.getCubeIndexPortTypePort(endpoint);
				
				if (cubeIndex.refreshCube(a_cube.getKeyindex()) == false){  // faz um refresh no tempo de vida
					
					//cadastrar e registrar o Cubo no CubeIndex
					CubeServiceControl.registerCubeIndexService(a_IndexServiceURI, a_cube, a_cubeEntry);
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
        } else {
            System.exit(0);   
        }
	}

}
