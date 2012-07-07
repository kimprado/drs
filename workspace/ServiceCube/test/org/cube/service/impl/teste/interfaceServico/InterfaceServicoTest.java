package org.cube.service.impl.teste.interfaceServico;

import java.rmi.RemoteException;

import org.cube.service.impl.teste.dao.CubeServiceDAOTest;
import org.junit.Test;


public class InterfaceServicoTest {

	//TODO - Habilitar teste depois de resolver problema de LazyInitializationException em  CubeService.printCube()
	//@Test
	public void imprimeMetaDadosCubo() throws RemoteException{
		
		String metadadosSolicitados = null;
		
		metadadosSolicitados = new CubeServiceDAOTest().getPotType().printCube(1).getCube();
		
		System.out.println(metadadosSolicitados);
	}
}
