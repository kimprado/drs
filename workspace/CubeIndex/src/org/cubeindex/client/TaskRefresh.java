package org.cubeindex.client;

import java.util.TimerTask;
import java.util.Date;
import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.globus.index.stubs.Cube.CubeIndexPortType;
import org.globus.index.stubs.Cube.service.CubeIndexServiceAddressingLocator;

import org.globus.index.stubs.Cube.CubeEntry;

public class TaskRefresh extends TimerTask {
	String a_serviceURI = new String();
	int a_cube;
	
	public TaskRefresh(String serviceURI, int cube){
		a_serviceURI = serviceURI;
		a_cube = cube;
	}
	
	public void run(){
		if (true) {
			CubeIndexServiceAddressingLocator locator = new CubeIndexServiceAddressingLocator();

			try {
				
				// Create endpoint reference to service
				EndpointReferenceType endpoint = new EndpointReferenceType();
				endpoint.setAddress(new Address(a_serviceURI));
				
				// Get PortType
				CubeIndexPortType cubeIndex = locator.getCubeIndexPortTypePort(endpoint);
				
				
				System.out.println("\n Estou na Task " + new Date());
				//cubeIndex.refreshCube(a_cube);
				CubeEntry cubeEntry = cubeIndex.getCubeEntry(a_cube).getCubeEntry();
				if (cubeEntry != null){
					System.out.println(cubeEntry.getName()+" ("+cubeEntry.getUri()+") - "+cubeEntry.getIndex() +"  time: "+cubeEntry.getTime().getTime());
				}
				else{
					System.out.println("não pude recuperar a entrada");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
        } else {
            
            System.out.println("Time's up!");
            //timer.cancel(); // Not necessary because
                              // we call System.exit
            System.exit(0);   // Stops the AWT thread 
                              // (and everything else)
        }
	}

}
