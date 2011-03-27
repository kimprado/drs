package org.cubeindex.client;

//import java.util.Date;
import java.util.Timer;

import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.globus.index.stubs.Cube.CubeIndexPortType;
import org.globus.index.stubs.Cube.GetCubeList;
import org.globus.index.stubs.Cube.CubeEntry;
import org.globus.index.stubs.Cube.CubeListResponse;
import org.globus.index.stubs.Cube.service.CubeIndexServiceAddressingLocator;


public class ClientIndex {

	/**
	 * @param args
	 */
	
	public static void run(String serviceURI){
		CubeIndexServiceAddressingLocator locator = new CubeIndexServiceAddressingLocator();

		try {
			
			// Create endpoint reference to service
			EndpointReferenceType endpoint = new EndpointReferenceType();
			endpoint.setAddress(new Address(serviceURI));
			
			// Get PortType
			CubeIndexPortType cubeIndex = locator.getCubeIndexPortTypePort(endpoint);

			//System.out.println("refresh is: " + cubeIndex.refreshCube(1));

			/*
			CubeEntry cubeEntry = cubeIndex.getCubeEntry(1).getCubeEntry();
			System.out.println(cubeEntry.getName()+" ("+cubeEntry.getUri()+") - "+cubeEntry.getIndex() +"  time: "+cubeEntry.getTime().getTime());
			//*/
			
			
			//*
			CubeListResponse cubeList = cubeIndex.getCubeList(new GetCubeList());		
			if (cubeList.getCubeEntry() != null ) {
				System.out.println("Qtd de cubos registrados: " + cubeList.getCubeEntry().length);
				for (int i=0; i < cubeList.getCubeEntry().length; i++){
					CubeEntry cb = cubeList.getCubeEntry(i);
					System.out.println("Entrada ("+ cb.getEntry()+")  "+cb.getIndex()+" - "+cb.getName()+" ("+cb.getUri()+") validade: "+cb.getTime().getTime());
				}
			} 
			else {
				System.out.println("Não possui algum cubo válido");
			}//*/
			
			/*
			System.out.println("\n Agora: " + new Date()+ "\n");
			for (int i=1; i<3; i++){
				CubeEntry cubeEntry = cubeIndex.getCubeEntry(i).getCubeEntry();
				if ( cubeEntry != null){
					System.out.println(cubeEntry.getName()+" ("+cubeEntry.getUri()+") - "+cubeEntry.getIndex() +"  time: "+cubeEntry.getTime().getTime());
				}
				else {
					System.out.println("Não pode recuperar  a entrada");
				}
			}//*/
			
			
			/*
			CubeEntry entry = new CubeEntry(1,1,"Vendas",null,"eingrid001.u.b");	
			int keyIndex = cubeIndex.addCubeEntry(entry);
			System.out.println("O novo cubo foi adicionado ao 'CubeIndex' com o \"KEY\": "+keyIndex);
			//*/

			
			//if (cubeIndex.removeEntry(1))
			//System.out.println("A entrada foi removida");
			
			
			
			/*if (cubeIndex.addCubeEntry(new AddCubeEntry(4,"Estoque","http://10.0.2.182:8443/wsrf/services/cube/Cube"))){
				CubeListResponse cubeList = cubeIndex.getCubeList(new GetCubeList());	
				CubeEntry cbEntry = cubeList.getCubeEntry(cubeList.getCubeEntry().length - 1);
				System.out.println("O cubo \""+cbEntry.getName()+"\" foi Registrado");
			}else System.out.println("Não foi possível registrar o cubo");
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void tempo(String serviceURI){
		Timer timer = new Timer();
		timer.schedule(new org.cubeindex.client.TaskRefresh(serviceURI,2), 0, 5000);
		
	}
	
	public static void main(String[] args) { 
		
		String serviceURI = "http://eingrid005.unigranrio.br:8443/wsrf/services/cubeIndex"; //new String(args[0]);
		run(serviceURI);
		//tempo( serviceURI );
		
	}

}
