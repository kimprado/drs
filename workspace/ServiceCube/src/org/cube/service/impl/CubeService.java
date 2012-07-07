package org.cube.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import org.cube.service.impl.control.CubeServiceControl;
import org.cube.service.impl.dao.DAO;
import org.cube.service.impl.dao.PersisteCuboServiceDAO;
import org.cube.service.impl.infra.AbreConexao;
import org.cube.service.impl.infra.FechaConexao;
import org.cube.service.impl.modelo.Atributo;
import org.cube.service.impl.modelo.ChavePrimaria;
import org.cube.service.impl.modelo.Cubo;
import org.cube.service.impl.modelo.Dimensao;
import org.cube.service.impl.modelo.Fato;
import org.cube.service.impl.modelo.Ligacao;
import org.globus.cube.stubs.Cube.AddCube;
import org.globus.cube.stubs.Cube.CubeCollResponse;
import org.globus.cube.stubs.Cube.CubeMetaData;
import org.globus.cube.stubs.Cube.CubeMetadataResponse;
import org.globus.cube.stubs.Cube.DimensaoMetaData;
import org.globus.cube.stubs.Cube.ExecuteQuery;
import org.globus.cube.stubs.Cube.ExecuteQueryResponse;
import org.globus.cube.stubs.Cube.FatoMetaData;
import org.globus.cube.stubs.Cube.GetCubeColl;
import org.globus.cube.stubs.Cube.LigacaoMetaData;
import org.globus.cube.stubs.Cube.PrintCubeResponse;
import org.globus.cube.stubs.Cube.RemoveCubeResponse;
import org.globus.cube.stubs.Cube.SetChavePrimaria;
import org.globus.cube.stubs.Cube.SetLigacao;
import org.globus.wsrf.Resource;
import org.globus.wsrf.ResourceProperties;
import org.globus.wsrf.ResourceProperty;
import org.globus.wsrf.ResourcePropertySet;
import org.globus.wsrf.impl.ReflectionResourceProperty;
import org.globus.wsrf.impl.SimpleResourcePropertySet;

public class CubeService implements Resource, ResourceProperties{
	
	private HashMap< Integer, Cubo>  cubos = new HashMap< Integer, Cubo>();
	private int idCount;
	private String serviceIndexURI;
	private String serviceCubeURI;
	private String bancoMetadadosDriver;
	private String bancoMetadadosConexao; 
	
	/* Resource Property set */
	private ResourcePropertySet propSet;

	/* Resource properties */
	private String cube;
	
	/* Constructor. Initializes RPs */
	public CubeService() throws RemoteException {
		serviceIndexURI = getServiceURI("cubeindexservice"); //cubeIndex
		System.out.println("\n\nIndex: "+getServiceURI("cubeindexservice"));
		serviceCubeURI = getServiceURI("cubeservice");
		System.out.println("Cube: "+getServiceURI("cubeservice"));
		bancoMetadadosDriver = getServiceURI("bancometadadosdriver");
		System.out.println("bancoMetadadosDriver: "+getServiceURI("bancometadadosdriver"));
		bancoMetadadosConexao = getServiceURI("bancometadadosconexao");
		System.out.println("bancometadadosconexao: "+getServiceURI("bancometadadosconexao"));
		
		/* Create RP set */
		try {
			this.propSet = new SimpleResourcePropertySet(CubeQNames.RESOURCE_PROPERTIES);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		
		/* Initialize the RP's */
		try {
			ResourceProperty cubeRP = new ReflectionResourceProperty(
					CubeQNames.RP_Cube, "Cube", this);
			this.propSet.add(cubeRP);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		//Obter Cubos
		try {
			configurarCubos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Get/Setters for the RPs */
	
	public String getCube() {
		return cube;
	}

	public void setCube(String cube) {
		this.cube = cube;
	}
	
	/* Remotely-accessible operations */
		
	public CubeCollResponse getCubeColl(GetCubeColl cubeColl) throws RemoteException {
		if ( getCubos().size() >= (1) ){
			String[] cubeName = new String[getCubos().size()];
			String[] cubeIndex = new String[getCubos().size()];
			String[] cubeServer = new String[getCubos().size()];
		
			int entry = 0;
		
			for (int i=1; i < idCount + 1; i++){
			
				if (getCubos().containsKey(new Integer(i))){
					cubeName[entry] = (getCube(i)).getNome();
					cubeIndex[entry] = Integer.toString(i);
					cubeServer[entry] = (getCube(i)).getServer();
					entry++;
				}
			}
			return new CubeCollResponse(cubeIndex, cubeName, cubeServer);
		}
		return new CubeCollResponse(null,null,null);
	}
	
	public PrintCubeResponse printCube(int index) throws RemoteException {
		PrintCubeResponse printCubeResponse = null;
		if (index==(-1)){
			String print = "\nTodos os Cubos:";
			for(int i = 0; i < getCubos().size(); i++){
				print = print + ("\n"+(getCube(i)).imprimir(System.out)+"\n");
			}
			printCubeResponse = new PrintCubeResponse(print);
		} else if (getCubos().containsKey(new Integer(index))){
			printCubeResponse = new PrintCubeResponse((getCube(index)).imprimir(System.out));//(fato.Getnome());
		}
		return printCubeResponse;
	}
	
	public ExecuteQueryResponse executeQuery(ExecuteQuery exq){

		return CubeServiceControl.executeQuery((getCube(new Integer( exq.getSelectCube() ))).getConnection(), exq.getSql());

	}
	
	public CubeMetadataResponse getCubeMetaData(int index){
		CubeMetadataResponse cubeMetadataResponse = null;
		try {
			EntityManager em = AbreConexao.abreConexao();
			cubeMetadataResponse = CubeServiceControl.getCubeMetadata(em.merge(getCube(index)));
			FechaConexao.fechaConexao(em);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cubeMetadataResponse;
	}
	
	public Integer addCube(AddCube addcube1) {
		CubeMetaData CubeMetaData = addcube1.getCube();
		
		Cubo cubo = new Cubo();
		cubo.setConnection(CubeMetaData.getConnectionUrl(), CubeMetaData.getUser(), CubeMetaData.getPassword());
		
		try {
			CubeServiceControl.setCubeMetaData(cubo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cubo.setNome(CubeMetaData.getName());
		
		idCount++;
		getCubos().put(new Integer(idCount), cubo);
		
		cubo.setURIService(serviceCubeURI); //CubeService
		
		cubo.setTimer(serviceIndexURI, CubeMetaData.getMillisecond(), idCount);
		
		cubo.setDriver(CubeMetaData.getDriver());
		cubo.setRefresh(CubeMetaData.getMillisecond());
		cubo.setServer(serviceIndexURI);
		try {
			EntityManager em = AbreConexao.abreConexao();
			
			//Persiste cubo e toda árvore de metadados
			PersisteCuboServiceDAO persisteCuboServiceDAO = new PersisteCuboServiceDAO(em);
			persisteCuboServiceDAO.persisteCubo(cubo);
			
			FechaConexao.fechaConexao(em);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return idCount;
	}
	
	public Cubo cuboFromMetadados(CubeMetadataResponse metaDados) {
		Cubo cubo = new Cubo();
		CubeMetaData cubeMetaData = metaDados.getCubeMetaData();
		cubo.setId( cubeMetaData.getId() );
		cubo.setNome( cubeMetaData.getName() );
		cubo.setConnectionUrl( cubeMetaData.getConnectionUrl() );
		cubo.setConnectionUser( cubeMetaData.getUser() );
		cubo.setConnectionPassword( cubeMetaData.getPassword() );
		cubo.setDriver( cubeMetaData.getDriver() );
		cubo.setURIService( cubeMetaData.getUri() );
		cubo.setRefresh( cubeMetaData.getMillisecond() );
		return cubo;
	}
	
	public boolean addCubeMetadata(AddCube addcube){
		Cubo cubo = new Cubo();
		cubo.setConnection(addcube.getUri(), addcube.getUser(), addcube.getPassword());
		
		try {
			CubeServiceControl.setCubeMetaData(cubo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cubo.setNome(addcube.getName());
		
		//idCount+=10;
		idCount++;
		getCubos().put(new Integer(idCount), cubo);
		
		cubo.setURIService(serviceCubeURI); //CubeService
		
		cubo.setTimer(serviceIndexURI, addcube.getMillisecond(), idCount);
		
		return true;
	}
	
	
	public RemoveCubeResponse removeCube(int cube){
		System.out.println("\n\nRemovendo Cubo: " + cube);
		RemoveCubeResponse removeCubeResponse = null;
		if (getCubos().containsKey(new Integer(cube))){	
			
			Cubo cb = getCubos().get(Integer.valueOf(cube));
			try {
				EntityManager em = AbreConexao.abreConexao();
				DAO<Cubo> dao = new DAO<Cubo>(em, Cubo.class);
				Cubo cubo = dao.busca(cb.getId());
				dao.remove(cubo);
				FechaConexao.fechaConexao(em);
				
				getCubos().remove(Integer.valueOf(cube));
				CubeServiceControl.removeCubeIndexService(cb,getServiceURI(serviceIndexURI) );
				
				removeCubeResponse = new RemoveCubeResponse(cb.getNome(),true);
			} catch (Exception e) {
				e.printStackTrace();
				removeCubeResponse = new RemoveCubeResponse(null, false);
			}
		} else {
			removeCubeResponse = new RemoveCubeResponse("não Removido",false);
		}
		System.out.println("Cubo (" + cube + ") removido: " + removeCubeResponse.isSuccess() );
		return removeCubeResponse;
	}
	
	@Deprecated
	public boolean setChavePrimaria(SetChavePrimaria setchavePrimaria){
		Fato fato = getCube(new Integer(setchavePrimaria.getCube())).getFato();
		FatoMetaData ftMD = setchavePrimaria.getFatoMetaData();
		ChavePrimaria chaveFato = new ChavePrimaria();
		for(int i=0; i < ftMD.getFieldMetaData().length; i++){
			chaveFato.addAtributo(fato.getAtributo(ftMD.getFieldMetaData(i).getKey())); // Recuperar para incluir na cahve o atributo que possui a key igual ao solicitado pelo Obj MetaDados
		}
		fato.setChavePrimaria(chaveFato);
		
		for(int i=0; i < ftMD.getDimensaoMetaData().length; i++){
			DimensaoMetaData dmMD = ftMD.getDimensaoMetaData(i);
			Dimensao dimensao = fato.getdimensao(dmMD.getKey());
			ChavePrimaria chaveDimensao = new ChavePrimaria();
			
			for(int j=0; j < dmMD.getFieldMetaData().length; j++){
				chaveDimensao.addAtributo(dimensao.getAtributo(dmMD.getFieldMetaData(j).getKey())); // Recuperar para incluir na cahve o atributo que possui a key igual ao solicitado pelo Obj MetaDados
			}
			dimensao.setChavePrimaria(chaveDimensao);
		}
		
		return true;
	}
	
	@Deprecated
	public boolean setLigacao(SetLigacao setLigacao){
		Fato fato = getCube(new Integer(setLigacao.getCube())).getFato();
		FatoMetaData ftMD = setLigacao.getFatoMetaData();
		
		System.out.println("\nDimens�es: "+ftMD.getDimensaoMetaData().length);
		for(int i=0; i < ftMD.getDimensaoMetaData().length; i++){
			
			DimensaoMetaData dmMD = ftMD.getDimensaoMetaData(i);
			System.out.println("\n Dimens�o: "+dmMD.getKey() );
			Dimensao dimensao = fato.getdimensao(dmMD.getKey());
			LigacaoMetaData[] ligacaoMD = dmMD.getLigacaoMetaData();
			System.out.println("Liaga��es : "+ligacaoMD.length +" da dimensa�o: "+dimensao);
			for(int j=0; j < ligacaoMD.length; j++){
				Atributo atEstrangeiro = fato.getAtributo(ligacaoMD[j].getEstrangeiro().getKey()); // Recuperar para incluir na liga��o o atributo que possui a key igual ao solicitado pelo Obj MetaDados
				System.out.println("Atributo "+ ligacaoMD[j].getPrimario().getKey() +" prim�rio da Dimens�o: "+ dimensao.getAtributo(ligacaoMD[j].getPrimario().getKey()));
				Atributo atPrimario = dimensao.getAtributo(ligacaoMD[j].getPrimario().getKey()); // Recuperar para incluir na liga��o o atributo que possui a key igual ao solicitado pelo Obj MetaDados
				Ligacao ligacao = new Ligacao(atEstrangeiro,atPrimario);
				fato.addLigacao(dmMD.getKey(), ligacao);
			}
		}
		
		return true;
	}
	
	/* Required by interface ResourceProperties */
	public ResourcePropertySet getResourcePropertySet() {
		return this.propSet;
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
	
	public void setCubos(HashMap< Integer, Cubo> cubos) {
		this.cubos = cubos;
	}

	public HashMap< Integer, Cubo> getCubos() {
		return cubos;
	}
	
	private Cubo getCube(int i) {
		EntityManager em = AbreConexao.abreConexao();
		Cubo cubo = null;
		try {
			cubo = em.merge(getCubos().get(new Integer( i )));
			FechaConexao.fechaConexao(em);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cubo;
	}
	
	private void configurarCubos() throws Exception {
		EntityManager em = AbreConexao.abreConexao();
	
		DAO<Cubo> cuboDAO = new DAO<Cubo>(em, Cubo.class);
		List<Cubo> lista = cuboDAO.lista();
		for (Cubo cubo : lista) {
			idCount++;
			getCubos().put(Integer.valueOf(idCount), cubo);
			
			cubo.setURIService(serviceCubeURI);
			System.out.println(serviceIndexURI + " - " + cubo.getRefresh() + " - " + idCount);
			cubo.setTimer(serviceIndexURI, cubo.getRefresh(), idCount);
		}
		
		FechaConexao.fechaConexao(em);
	}
}