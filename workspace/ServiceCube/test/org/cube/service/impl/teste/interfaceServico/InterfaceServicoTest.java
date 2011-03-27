package org.cube.service.impl.teste.interfaceServico;

import java.rmi.RemoteException;

import org.cube.service.impl.teste.dao.CubeServiceDAO;
import org.junit.Test;


public class InterfaceServicoTest {

	@Test
	public void imprimeMetaDadosCubo(){
		
		String metadadosSolicitados = null;
		try {
			metadadosSolicitados = new CubeServiceDAO().getPotType().printCube(6).getCube();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new Exception();
		}
		System.out.println(metadadosSolicitados);
		
	}
}
