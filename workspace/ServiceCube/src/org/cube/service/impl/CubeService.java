package org.cube.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.util.HashMap;

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

	

	private HashMap< Integer, Cubo>  a_cubeColl = new HashMap< Integer, Cubo>();
	private int idCount = 0;
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
		
		/*try {
			System.out.println("Acessar o entity Manager JPA \n\nThe sky is the limit\n\n");
			System.out.println("O bagulho ta doIdu.");
			//buscaAtributoTest();
		} catch (Exception e1) {
			e1.printStackTrace();
		}*/
		
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
			
			idCount = CubeServiceControl.setCubeCollectionMetaData( a_cubeColl, serviceIndexURI,serviceCubeURI, bancoMetadadosDriver, bancoMetadadosConexao);
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		// Inicializa��o manual de cubo.
		try {
			Cubo cube = new Cubo("Vendas_ii_Automatico", "eingrid005.unigranrio.br", null, "jdbc:postgresql://eingrid005.unigranrio.br:5432/vendas", "kim", 
					"kim", "org.postgresql.Driver", new Long(30000).longValue());
			
			CubeServiceControl.setCubeMetaData(cube);
			System.out.println("Cubo Criado dinamicamente:\n" + cube.imprimir(System.out));
			
			idCount++;
			a_cubeColl.put(Integer.valueOf(idCount), cube);
			
			cube.setUri_service(serviceCubeURI); // CubeService
			/*a_cubeColl.put(new Integer(cuboAtual), cube);*/
			cube.setTimer(serviceIndexURI, cube.getRefresh(), idCount);
			
		} catch (Exception e) {
			
		}
	}

	/* Get/Setters for the RPs */
	
	public String getCube() {
		return cube;
	}

	public void setCube(String cube) {
		this.cube = cube;
	}

	public Cubo getCube(int index){
		return a_cubeColl.get(new Integer( index ));
	}
	
	public void setIdCount(int idCount){
		this.idCount = idCount;
	}
	
	
	/* Remotely-accessible operations */
		
	public CubeCollResponse getCubeColl(GetCubeColl cubeColl) throws RemoteException {
		
		//idCount = Controller.setCubeCollectionMetaData( a_cubeColl, serviceIndexURI,serviceCubeURI, bancoMetadadosDriver, bancoMetadadosConexao);
		
		
		if ( a_cubeColl.size() >= (1) ){
			String[] cubeName = new String[a_cubeColl.size()];
			String[] cubeIndex = new String[a_cubeColl.size()];
			String[] cubeServer = new String[a_cubeColl.size()];
		
			int entry = 0;
		
			for (int i=1; i < idCount + 1; i++){
			
				if (a_cubeColl.containsKey(new Integer(i))){
					cubeName[entry] = (a_cubeColl.get(new Integer( i ))).getNome();
					cubeIndex[entry] = Integer.toString(i);
					cubeServer[entry] = (a_cubeColl.get(new Integer( i ))).getServer();
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
			for(int i = 0; i < a_cubeColl.size(); i++){
				print = print + ("\n"+(a_cubeColl.get(new Integer( i ))).imprimir(System.out)+"\n");
			}
			return new PrintCubeResponse(print);
		}
		else
		return new PrintCubeResponse((a_cubeColl.get(new Integer( index ))).imprimir(System.out));//(fato.Getnome());
	}
	
	
	
	public ExecuteQueryResponse executeQuery(ExecuteQuery exq){

		return CubeServiceControl.executeQuery((a_cubeColl.get(new Integer( exq.getSelectCube() ))).getConnection(), exq.getSql());

	}
	
	
	public CubeMetadataResponse getCubeMetaData(int index){
		//System.out.println("\n\nRecuperar metadata do cubo: "+index);
		return CubeServiceControl.getCubeMetadata(a_cubeColl.get(new Integer( index )));
	}
	
	public boolean addCube(AddCube addcube){
		Cubo cb = new Cubo();
		cb.setConnection(addcube.getUri(), addcube.getUser(), addcube.getPassword());
		//CubeServiceControl.setCubeMetaDataDesativado(cb, addcube.getFato());
		CubeServiceControl.setCubeMetaData(cb);
		cb.setNome(addcube.getName());
		
		//idCount+=10;
		idCount++;
		a_cubeColl.put(new Integer(idCount), cb);
		
		cb.setUri_service(serviceCubeURI); //CubeService
		
		//if (Controller.registerCubeIndexService(serviceIndexURI, a_cubeColl.get(new Integer(idCount)),idCount)){
		//	System.out.println("O novo cubo foi cadastrado e \n registrado no Index Cube");
		//}
		
		
		cb.setTimer(serviceIndexURI, addcube.getMillisecond(), idCount);
		
		return true;
	}
	
	
	public RemoveCubeResponse removeCube(int cube){
		
		if (a_cubeColl.containsKey(new Integer(cube))){	
			Cubo cb = a_cubeColl.remove(new Integer(cube));
			CubeServiceControl.removeCubeIndexService(cb,getServiceURI(serviceIndexURI) );
			return new RemoveCubeResponse(cb.getNome(),true);
		}
		
		else {
			return new RemoveCubeResponse(null,false);
		}
	}
	
	//N�O � MAIS USADO
	public boolean setChavePrimaria(SetChavePrimaria setchavePrimaria){
		Fato fato = a_cubeColl.get(new Integer(setchavePrimaria.getCube())).getFato();
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
	
	//N�O � MAIS USADO
	public boolean setLigacao(SetLigacao setLigacao){
		Fato fato = a_cubeColl.get(new Integer(setLigacao.getCube())).getFato();
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
	
	public void buscaAtributoTest() throws Exception {
		EntityManager abreConexao = AbreConexao.abreConexao();
		DAO<Atributo> dao = new DAO<Atributo>(abreConexao, Atributo.class);
		
		Atributo atributo = dao.busca(56);
		
		System.out.println("atributo localizado: " + atributo.getId() + " " + atributo.getName() + " " + atributo.getDecimal() + " " + atributo.getTamanho() + " " +  atributo.getTipo());
		
		FechaConexao.fechaConexao(abreConexao);
	}
	
}