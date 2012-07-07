/**
 * 
 */
package br.com.kasystemas.drs.test.cubo.infra.servico.grid;

import static org.junit.Assert.fail;

import java.math.BigInteger;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.types.URI.MalformedURIException;
import org.cube.service.impl.modelo.Cubo;
import org.globus.cube.stubs.Cube.AddCube;
import org.globus.cube.stubs.Cube.CubeMetaData;
import org.globus.cube.stubs.Cube.CubePortType;
import org.globus.cube.stubs.Cube.RemoveCubeResponse;
import org.junit.After;
import org.junit.AfterClass;
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
	private static final String NOME_CUBO_REMOCAO_MOCK = "Vendas_ii_Teste_Remocao_Mock";
	private static BigInteger idCuboInclusao;
	private static BigInteger idCuboRemocao;// = BigInteger.valueOf(3l);
	private static Cubo cuboInclusao = null;
	private static Cubo cuboRemocao = null;


	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cuboInclusao = new Cubo(NOME_CUBO_MOCK, "localhost", null, "jdbc:postgresql://localhost:5432/vendas", "kim",
				"kim", "org.postgresql.Driver", 30000l );
		cuboInclusao.setURIService("http://localhost:8443/wsrf/services/cube/Cube");
		
		cuboRemocao = new Cubo(NOME_CUBO_REMOCAO_MOCK, "localhost", null, "jdbc:postgresql://localhost:5432/vendas", "kim",
				"kim", "org.postgresql.Driver", 30000l );
		cuboRemocao.setURIService("http://localhost:8443/wsrf/services/cube/Cube");
		
		preparaCuboRemocaoMock();
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
	//@Test
	public final void testGetCubeColl() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.cube.service.impl.CubeService#printCube(int)}.
	 */
	//@Test
	public final void testPrintCube() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.cube.service.impl.CubeService#executeQuery(org.globus.cube.stubs.Cube.ExecuteQuery)}.
	 */
	//@Test
	public final void testExecuteQuery() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.cube.service.impl.CubeService#getCubeMetaData(int)}.
	 */
	//@Test
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
			cubeMetaData.setId( cuboInclusao.getId()== null ? 0 :cuboInclusao.getId() );
			cubeMetaData.setName( cuboInclusao.getNome() );
			cubeMetaData.setUri( cuboInclusao.getURIService() );
			cubeMetaData.setUser( cuboInclusao.getConnectionUser() );
			cubeMetaData.setPassword( cuboInclusao.getConnectionPassword() );
			cubeMetaData.setConnectionUrl( cuboInclusao.getConnectionUrl() );
			cubeMetaData.setDriver( cuboInclusao.getDriver() );
			cubeMetaData.setMillisecond( cuboInclusao.getRefresh() );
			
			addCube.setCube(cubeMetaData);
		
			CubePortType cube = new ServicoUtil().obterEndpointCubeService();
			
			idCuboInclusao = cube.addCube(addCube);
			if (idCuboInclusao != null &&  idCuboInclusao.longValue() < 1 ) {
				fail("Cubo não foi adicionado.");
			}
			
			
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
		try {
			CubePortType cube = new ServicoUtil().obterEndpointCubeService();
			RemoveCubeResponse removeCube = cube.removeCube(idCuboRemocao.intValue());
			
			if (!removeCube.isSuccess()) {
				fail("Cubo não pôde ser removido.");
			}
			
			System.out.println("Cubo removido: " + removeCube.getName());
		} catch (MalformedURIException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static void preparaCuboRemocaoMock() {
		try {
			AddCube addCube = new AddCube(null,"",1l,"","","","");
			
			CubeMetaData cubeMetaData = new CubeMetaData();
			cubeMetaData.setId( cuboRemocao.getId()== null ? 0 :cuboRemocao.getId() );
			cubeMetaData.setName( cuboRemocao.getNome() );
			cubeMetaData.setUri( cuboRemocao.getURIService() );
			cubeMetaData.setUser( cuboRemocao.getConnectionUser() );
			cubeMetaData.setPassword( cuboRemocao.getConnectionPassword() );
			cubeMetaData.setConnectionUrl( cuboRemocao.getConnectionUrl() );
			cubeMetaData.setDriver( cuboRemocao.getDriver() );
			cubeMetaData.setMillisecond( cuboRemocao.getRefresh() );
			
			addCube.setCube(cubeMetaData);
		
			CubePortType cube = new ServicoUtil().obterEndpointCubeService();
			
			idCuboRemocao = cube.addCube(addCube);
			
		} catch (MalformedURIException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
