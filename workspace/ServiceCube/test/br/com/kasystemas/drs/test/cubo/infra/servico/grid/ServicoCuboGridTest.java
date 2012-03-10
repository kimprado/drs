/**
 * 
 */
package br.com.kasystemas.drs.test.cubo.infra.servico.grid;

import static org.junit.Assert.fail;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.types.URI.MalformedURIException;
import org.cube.service.impl.modelo.Cubo;
import org.globus.cube.stubs.Cube.AddCube;
import org.globus.cube.stubs.Cube.CubeMetaData;
import org.globus.cube.stubs.Cube.CubePortType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.kasystemas.drs.cubo.infra.servico.ServicoUtil;

/**
 * @author kim
 *
 */
public class ServicoCuboGridTest {
	
	private static final String NOME_CUBO_MOCK = "Vendas_ii_Teste_Mock";
	
	private static Cubo cb = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cb = new Cubo(NOME_CUBO_MOCK, "localhost", null, "jdbc:postgresql://localhost:5432/vendas", "kim",
				"kim", "org.postgresql.Driver", 30000l );
		
		cb.setURIService("http://localhost:8443/wsrf/services/cube/Cube");
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
	 * Test method for {@link org.cube.service.impl.CubeService#getCubeColl(org.globus.cube.stubs.Cube.GetCubeColl)}.
	 */
	@Test
	public final void testGetCubeColl() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.cube.service.impl.CubeService#printCube(int)}.
	 */
	@Test
	public final void testPrintCube() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.cube.service.impl.CubeService#executeQuery(org.globus.cube.stubs.Cube.ExecuteQuery)}.
	 */
	@Test
	public final void testExecuteQuery() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.cube.service.impl.CubeService#getCubeMetaData(int)}.
	 */
	@Test
	public final void testGetCubeMetaData() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.cube.service.impl.CubeService#addCube(org.globus.cube.stubs.Cube.AddCube)}.
	 */
	@Test
	public final void testAddCube() {
		try {
			AddCube addCube = new AddCube(null,"",1l,"","","","");
			
			CubeMetaData cubeMetaData = new CubeMetaData();
			cubeMetaData.setId( cb.getId()== null ? 0 :cb.getId() );
			cubeMetaData.setName( cb.getNome() );
			cubeMetaData.setUri( cb.getURIService() );
			cubeMetaData.setUser( cb.getConnectionUser() );
			cubeMetaData.setPassword( cb.getConnectionPassword() );
			cubeMetaData.setConnectionUrl( cb.getConnectionUrl() );
			cubeMetaData.setDriver( cb.getDriver() );
			cubeMetaData.setMillisecond( cb.getRefresh() );
			
			addCube.setCube(cubeMetaData);
		
			CubePortType cube = new ServicoUtil().obterEndpointCubeService();
			boolean inclusaoRealizada = cube.addCube(addCube);
			
			Assert.assertTrue(inclusaoRealizada);
			
		} catch (MalformedURIException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link org.cube.service.impl.CubeService#removeCube(int)}.
	 */
	@Test
	public final void testRemoveCube() {
		fail("Not yet implemented"); // TODO
	}

}
