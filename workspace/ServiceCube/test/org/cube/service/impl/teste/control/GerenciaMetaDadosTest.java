package org.cube.service.impl.teste.control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.HashMap;

import org.cube.service.impl.control.CubeServiceControl;
import org.cube.service.impl.modelo.Cubo;
import org.junit.Test;


public class GerenciaMetaDadosTest {
	
	private HashMap< Integer, Cubo>  a_cubeColl = new HashMap< Integer, Cubo>();
	private int idCount = 0;
	private String serviceIndexURI;
	private String serviceCubeURI;
	private String bancoMetadadosDriver;
	private String bancoMetadadosConexao; 
	
	private void configuraAtributos(){
		
		serviceIndexURI = getServiceURI("cubeindexservice"); //cubeIndex
		System.out.println("Index: "+getServiceURI("cubeindexservice"));
		serviceCubeURI = getServiceURI("cubeservice");
		System.out.println("Cube: "+getServiceURI("cubeservice"));
		bancoMetadadosDriver = getServiceURI("bancometadadosdriver");
		System.out.println("bancoMetadadosDriver: "+getServiceURI("bancometadadosdriver"));
		bancoMetadadosConexao = getServiceURI("bancometadadosconexao");
		System.out.println("bancometadadosconexao: "+getServiceURI("bancometadadosconexao"));
		
	}
	
	public String getServiceURI(String procurar){
		try{
		FileReader reader = new FileReader(System.getenv("CUBO_CONF"));
		BufferedReader leitor = new BufferedReader(reader,1*1024*1024);
		String linha = null;
		while( leitor.ready()  ) {
			linha = leitor.readLine();
			if (linha.toCharArray().length > 0){
				if(procurar.equalsIgnoreCase( String.copyValueOf(linha.toCharArray(),0,procurar.length()))){
					return String.copyValueOf(linha.toCharArray(),procurar.length()+1,linha.length() - procurar.length()-1) ;
				}
			}
		}
		leitor.close();
		reader.close();
		}catch (Exception e){ e.printStackTrace();}
		return null;
	}
	
	@Test
	public void imprimeMetaDadosDeEsquema() throws SQLException{
		this.configuraAtributos();
		
		idCount = CubeServiceControl.setCubeCollectionMetaData( a_cubeColl, serviceIndexURI,serviceCubeURI, bancoMetadadosDriver, bancoMetadadosConexao);
		CubeServiceControl.printMetaData(a_cubeColl.get(idCount).getConnection());
	}
	
	@Test
	public void imprimeCubo() throws SQLException{
		this.configuraAtributos();
		
		idCount = CubeServiceControl.setCubeCollectionMetaData( a_cubeColl, serviceIndexURI,serviceCubeURI, bancoMetadadosDriver, bancoMetadadosConexao);
		Cubo cubo = a_cubeColl.get(idCount);
		System.out.println(cubo.imprimir(System.out));
		//cubo;
	}
	
	public Cubo carregaMetaDadosDeCuboDinamicamente() throws SQLException{
		/*Cubo cube = new Cubo("Vendas_ii_Automatico", "eingrid005.unigranrio.br", null, "jdbc:postgresql://eingrid005.unigranrio.br:5432/vendas", "kim", 
				"kim", "org.postgresql.Driver", new Long(30000).longValue());*/
		
		Cubo cube = new Cubo("Vendas_ii_Automatico", "localhost", null, "jdbc:postgresql://localhost:5432/vendas", "kim", 
				"kim", "org.postgresql.Driver", new Long(30000).longValue());
		
		cube.setURIService("http://localhost:8443/wsrf/services/cube/Cube");
		
		CubeServiceControl.setCubeMetaData(cube);
		
		System.out.println("Cubo Criado dinamicamente:\n" + cube.imprimir(System.out));
		
		return cube;
	}
	
	/*public Cubo carregaMetaDadosDeCuboDinamicamente() throws SQLException{
		Cubo cube = new Cubo("Exactum BI", "eingrid005.unigranrio.br", null, "jdbc:postgresql://eingrid005.unigranrio.br:5432/exactumbi", "kim", 
				"kim", "org.postgresql.Driver", new Long(30000).longValue());
		
		cube.setURIService("http://localhost:8443/wsrf/services/cube/Cube");
		
		CubeServiceControl.setCubeMetaData(cube);
		
		System.out.println("Cubo Criado dinamicamente:\n" + cube.imprimir(System.out));
		
		return cube;
	}*/

	@Test
	public void carregaMetaDadosDeCuboDinamicamenteTest() throws SQLException{
		carregaMetaDadosDeCuboDinamicamente();
	}
	
	
}
