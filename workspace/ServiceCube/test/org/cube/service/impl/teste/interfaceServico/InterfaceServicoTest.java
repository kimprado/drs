package org.cube.service.impl.teste.interfaceServico;

import java.rmi.RemoteException;

import org.cube.service.impl.teste.dao.CubeServiceDAO;
import org.junit.Test;


public class InterfaceServicoTest {

	@Test
	public void imprimeMetaDadosCubo() throws RemoteException{
		
		String metadadosSolicitados = null;
		
		metadadosSolicitados = new CubeServiceDAO().getPotType().printCube(6).getCube();
		
		System.out.println(metadadosSolicitados);
	}
}
