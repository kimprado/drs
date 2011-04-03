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
		this.propSet = new SimpleResourcePropertySet(CubeQNames.RESOURCE_PROPERTIES);
		
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
		if (index==(-1)){
			String print = "\nTodos os Cubos:";
			for(int i = 0; i < getCubos().size(); i++){
				print = print + ("\n"+(getCube(i)).imprimir(System.out)+"\n");
			}
			return new PrintCubeResponse(print);
		}
		else
		return new PrintCubeResponse((getCube(index)).imprimir(System.out));//(fato.Getnome());
	}
	
	public ExecuteQueryResponse executeQuery(ExecuteQuery exq){

		return CubeServiceControl.executeQuery((getCube(new Integer( exq.getSelectCube() ))).getConnection(), exq.getSql());

	}
	
	public CubeMetadataResponse getCubeMetaData(int index){
		return CubeServiceControl.getCubeMetadata(getCube(index));
	}
	
	public boolean addCube(AddCube addcube){
		Cubo cb = new Cubo();
		cb.setConnection(addcube.getUri(), addcube.getUser(), addcube.getPassword());
		
		try {
			CubeServiceControl.setCubeMetaData(cb);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cb.setNome(addcube.getName());
		
		//idCount+=10;
		idCount++;
		getCubos().put(new Integer(idCount), cb);
		
		cb.setURIService(serviceCubeURI); //CubeService
		
		cb.setTimer(serviceIndexURI, addcube.getMillisecond(), idCount);
		
		return true;
	}
	
	
	public RemoveCubeResponse removeCube(int cube){
		if (getCubos().containsKey(new Integer(cube))){	
			Cubo cb = getCubos().remove(new Integer(cube));
			CubeServiceControl.removeCubeIndexService(cb,getServiceURI(serviceIndexURI) );
			return new RemoveCubeResponse(cb.getNome(),true);
		} else {
			return new RemoveCubeResponse(null,false);
		}
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
		
		System.out.println("\nDimensões: "+ftMD.getDimensaoMetaData().length);
		for(int i=0; i < ftMD.getDimensaoMetaData().length; i++){
			
			DimensaoMetaData dmMD = ftMD.getDimensaoMetaData(i);
			System.out.println("\n Dimensão: "+dmMD.getKey() );
			Dimensao dimensao = fato.getdimensao(dmMD.getKey());
			LigacaoMetaData[] ligacaoMD = dmMD.getLigacaoMetaData();
			System.out.println("Liagações : "+ligacaoMD.length +" da dimensaão: "+dimensao);
			for(int j=0; j < ligacaoMD.length; j++){
				Atributo atEstrangeiro = fato.getAtributo(ligacaoMD[j].getEstrangeiro().getKey()); // Recuperar para incluir na ligação o atributo que possui a key igual ao solicitado pelo Obj MetaDados
				System.out.println("Atributo "+ ligacaoMD[j].getPrimario().getKey() +" primário da Dimensão: "+ dimensao.getAtributo(ligacaoMD[j].getPrimario().getKey()));
				Atributo atPrimario = dimensao.getAtributo(ligacaoMD[j].getPrimario().getKey()); // Recuperar para incluir na ligação o atributo que possui a key igual ao solicitado pelo Obj MetaDados
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
			// TODO Auto-generated catch block
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
			cubo.setTimer(serviceIndexURI, cubo.getRefresh(), idCount);
		}
		
		FechaConexao.fechaConexao(em);
	}
}