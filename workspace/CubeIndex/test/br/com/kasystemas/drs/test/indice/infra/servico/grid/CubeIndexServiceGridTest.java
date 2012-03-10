/**
 * 
 */
package br.com.kasystemas.drs.test.indice.infra.servico.grid;

import static org.junit.Assert.fail;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import junit.framework.Assert;

import org.apache.axis.types.URI.MalformedURIException;
import org.globus.index.stubs.Cube.CubeEntry;
import org.globus.index.stubs.Cube.CubeIndexPortType;
import org.globus.index.stubs.Cube.CubeListResponse;
import org.globus.index.stubs.Cube.GetCubeList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.kasystemas.drs.indice.infra.servico.ServicoUtil;

/**
 * @author kim
 *
 */
public class CubeIndexServiceGridTest {
	
	private static final String NOME_ENTRADA_CUBO_MOCK = "Vendas_iii_Automatico_Mock";
	private static final String NOME_ENTRADA_CUBO_REMOCAO_MOCK = "Vendas_iii_Automatico_remocao_Mock";
	private static String serviceURI = null;
	private static String cubeserviceURI = null;
	private static int idEntradaCuboIndice = -1;
	private static int idEntradaCuboIndiceRemocao = -1;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		serviceURI = new ServicoUtil().getServiceURI(ServicoUtil.CUBE_INDEX_SERVICE);
		cubeserviceURI = new ServicoUtil().getServiceURI(ServicoUtil.CUBE_SERVICE);
		
		CubeIndexPortType cubeIndex = new ServicoUtil().obterEndpointCubeIndexService();
		CubeListResponse cubeList = cubeIndex.getCubeList(new GetCubeList());		
		int quantidadedeRegistros = cubeList.getCubeEntry().length;
		for (int i=0; i < quantidadedeRegistros; i++){
			CubeEntry cb = cubeList.getCubeEntry(i);
			if (NOME_ENTRADA_CUBO_MOCK.equals(cb.getName()) ) {
				idEntradaCuboIndice = cb.getEntry();
			}
			
			if (NOME_ENTRADA_CUBO_REMOCAO_MOCK.equals(cb.getName()) ) {
				idEntradaCuboIndiceRemocao = cb.getEntry();
			}
		}
		
		if (idEntradaCuboIndice < 1) {
			CubeEntry entry = new CubeEntry(0, Integer.MIN_VALUE, NOME_ENTRADA_CUBO_MOCK, null, cubeserviceURI);
			idEntradaCuboIndice = cubeIndex.addCubeEntry(entry);
		}
		
		if (idEntradaCuboIndiceRemocao < 1) {
			CubeEntry entry = new CubeEntry(0, Integer.MIN_VALUE, NOME_ENTRADA_CUBO_REMOCAO_MOCK, null, cubeserviceURI);
			idEntradaCuboIndiceRemocao = cubeIndex.addCubeEntry(entry);
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.cubeindex.service.impl.CubeIndexService#getCubeList(org.globus.index.stubs.Cube.GetCubeList)}.
	 * @throws MalformedURIException 
	 * @throws ServiceException 
	 * @throws RemoteException 
	 */
	@Test
	public final void testGetCubeList() throws MalformedURIException, ServiceException, RemoteException {
		try {
			CubeIndexPortType cubeIndex = new ServicoUtil().obterEndpointCubeIndexService();
			CubeListResponse cubeList = cubeIndex.getCubeList(new GetCubeList());		
			int quantidadedeRegistros = cubeList.getCubeEntry().length;
			System.out.println("Qtd de cubos registrados: " + quantidadedeRegistros);
			for (int i=0; i < quantidadedeRegistros; i++){
				CubeEntry cb = cubeList.getCubeEntry(i);
				System.out.println("Entrada ("+ cb.getEntry()+")  "+cb.getIndex()+" - "+cb.getName()+" ("+cb.getUri()+") validade: "+cb.getTime().getTime());
			}
			
			if (quantidadedeRegistros < 1) {
				fail("Não foi possível recuperar lista de cubos");
			}
		} catch (MalformedURIException e) {
			e.printStackTrace();
			throw e;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw e;
		} catch (RemoteException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Test method for {@link org.cubeindex.service.impl.CubeIndexService#getCubeEntry(int)}.
	 * @throws MalformedURIException 
	 * @throws ServiceException 
	 * @throws RemoteException 
	 */
	@Test
	public final void testGetCubeEntry() throws MalformedURIException, ServiceException, RemoteException {
		try {
			CubeIndexPortType cubeIndex = new ServicoUtil().obterEndpointCubeIndexService();
			
			CubeEntry cubeEntry = cubeIndex.getCubeEntry(idEntradaCuboIndice).getCubeEntry();
			System.out.println("Entrada: " + idEntradaCuboIndice + " - " + cubeEntry.getName()+" ("+cubeEntry.getUri()+") cubo: "+cubeEntry.getIndex() +"  time: "+cubeEntry.getTime().getTime());
			
		} catch (MalformedURIException e) {
			e.printStackTrace();
			throw e;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw e;
		} catch (RemoteException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Test method for {@link org.cubeindex.service.impl.CubeIndexService#addCubeEntry(org.globus.index.stubs.Cube.CubeEntry)}.
	 */
	@Test
	public final void testAddCubeEntry() throws MalformedURIException, ServiceException, RemoteException {
		try {
			CubeIndexPortType cubeIndex = new ServicoUtil().obterEndpointCubeIndexService();
			
			CubeEntry entry = new CubeEntry(0, Integer.MIN_VALUE, "Vendas_iii_Automatico", null, cubeserviceURI);
			int keyIndex = cubeIndex.addCubeEntry(entry);
			
			if (keyIndex < 1 ) {
				fail("Entrada não pode ser inferior a 1.");
			}
			
			System.out.println("O novo cubo foi adicionado ao 'CubeIndex' com o \"KEY\": "+keyIndex);
			
		} catch (MalformedURIException e) {
			e.printStackTrace();
			throw e;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw e;
		} catch (RemoteException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Test method for {@link org.cubeindex.service.impl.CubeIndexService#removeEntry(int)}.
	 * @throws ServiceException 
	 * @throws MalformedURIException 
	 * @throws RemoteException 
	 */
	@Test
	public final void testRemoveEntry() throws MalformedURIException, ServiceException, RemoteException {
		try {
			CubeIndexPortType cubeIndex = new ServicoUtil().obterEndpointCubeIndexService();
			boolean entradaCuboRemovida = cubeIndex.removeEntry(idEntradaCuboIndiceRemocao);
			Assert.assertTrue("Entrada do cubo precisa ser removida", entradaCuboRemovida);
			
		} catch (MalformedURIException e) {
			e.printStackTrace();
			throw e;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw e;
		} catch (RemoteException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Test method for {@link org.cubeindex.service.impl.CubeIndexService#refreshCube(int)}.
	 */
	@Test
	public final void testRefreshCubeInvalido() throws MalformedURIException, ServiceException, RemoteException {
		try {
			CubeIndexPortType cubeIndex = new ServicoUtil().obterEndpointCubeIndexService();
			
			boolean refreshCube = cubeIndex.refreshCube(Integer.MIN_VALUE);
			Assert.assertFalse(refreshCube);
			
		} catch (MalformedURIException e) {
			e.printStackTrace();
			throw e;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw e;
		} catch (RemoteException e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	/**
	 * Test method for {@link org.cubeindex.service.impl.CubeIndexService#refreshCube(int)}.
	 */
	@Test
	public final void testRefreshCubeValido() throws MalformedURIException, ServiceException, RemoteException {
		try {
			CubeIndexPortType cubeIndex = new ServicoUtil().obterEndpointCubeIndexService();
			
			boolean refreshCube = cubeIndex.refreshCube(idEntradaCuboIndice);
			Assert.assertTrue(refreshCube);
			
		} catch (MalformedURIException e) {
			e.printStackTrace();
			throw e;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw e;
		} catch (RemoteException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	
}
