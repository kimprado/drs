/**
 * 
 */
package br.com.kasystemas.drs.indice.infra.servico.grid;

import static org.junit.Assert.fail;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import junit.framework.Assert;

import org.apache.axis.types.URI.MalformedURIException;
import org.globus.index.stubs.Cube.CubeEntry;
import org.globus.index.stubs.Cube.CubeIndexPortType;
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
	
	String serviceURI = null;
	String cubeserviceURI = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
		serviceURI = new ServicoUtil().getServiceURI(ServicoUtil.CUBE_INDEX_SERVICE);
		cubeserviceURI = new ServicoUtil().getServiceURI(ServicoUtil.CUBE_SERVICE);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.cubeindex.service.impl.CubeIndexService#getCubeList(org.globus.index.stubs.Cube.GetCubeList)}.
	 */
	//@Test
	public final void testGetCubeList() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.cubeindex.service.impl.CubeIndexService#getCubeEntry(int)}.
	 */
	//@Test
	public final void testGetCubeEntry() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.cubeindex.service.impl.CubeIndexService#addCubeEntry(org.globus.index.stubs.Cube.CubeEntry)}.
	 */
	@Test
	public final void testAddCubeEntry() {
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
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link org.cubeindex.service.impl.CubeIndexService#removeEntry(int)}.
	 */
	//@Test
	public final void testRemoveEntry() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.cubeindex.service.impl.CubeIndexService#refreshCube(int)}.
	 */
	@Test
	public final void testRefreshCubeInvalido() {
		try {
			CubeIndexPortType cubeIndex = new ServicoUtil().obterEndpointCubeIndexService();
			
			boolean refreshCube = cubeIndex.refreshCube(Integer.MIN_VALUE);
			Assert.assertFalse(refreshCube);
			
		} catch (MalformedURIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Test method for {@link org.cubeindex.service.impl.CubeIndexService#refreshCube(int)}.
	 */
	@Test
	public final void testRefreshCubeValido() {
		try {
			CubeIndexPortType cubeIndex = new ServicoUtil().obterEndpointCubeIndexService();
			
			boolean refreshCube = cubeIndex.refreshCube(1);
			Assert.assertTrue(refreshCube);
			
		} catch (MalformedURIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
