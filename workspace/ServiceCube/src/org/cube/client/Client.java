package org.cube.client;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import javax.xml.namespace.QName;

import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.cube.service.impl.CubeQNames;
import org.cube.service.impl.modelo.Atributo;
import org.cube.service.impl.modelo.ChavePrimaria;
import org.cube.service.impl.modelo.Cubo;
import org.cube.service.impl.modelo.Dimensao;
import org.cube.service.impl.modelo.Fato;
import org.cube.service.impl.modelo.Ligacao;
import org.globus.cube.stubs.Cube.AddCube;
import org.globus.cube.stubs.Cube.CubeCollResponse;
import org.globus.cube.stubs.Cube.CubeMetadataResponse;
import org.globus.cube.stubs.Cube.CubePortType;
import org.globus.cube.stubs.Cube.DimensaoMetaData;
import org.globus.cube.stubs.Cube.ExecuteQuery;
import org.globus.cube.stubs.Cube.ExecuteQueryResponse;
import org.globus.cube.stubs.Cube.FatoMetaData;
import org.globus.cube.stubs.Cube.FieldMetaData;
import org.globus.cube.stubs.Cube.GetCubeColl;
import org.globus.cube.stubs.Cube.LigacaoMetaData;
import org.globus.cube.stubs.Cube.SetChavePrimaria;
import org.globus.cube.stubs.Cube.SetLigacao;
import org.globus.cube.stubs.Cube.service.CubeServiceAddressingLocator;
import org.oasis.wsrf.properties.GetMultipleResourcePropertiesResponse;
import org.oasis.wsrf.properties.GetMultipleResourceProperties_Element;
import org.oasis.wsrf.properties.GetResourcePropertyResponse;

public class Client {

	public static void consultaMysql(){
		try {
			System.out.println(new Date());
			Class.forName("com.mysql.jdbc.Driver");   // Inicializa��o do driver jdbc
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://casa:3306/territorio", "post","post");
			Statement stCube = conn.createStatement(3,1);
			//ResultSet rsCubo = stCube.executeQuery("SELECT * FROM ((cubo As c INNER JOIN fato AS f ON c.idcubo = f.idcubo) INNER JOIN tabela t ON f.idfato = t.idtabela) INNER JOIN  atributo AS a ON t.idtabela = a.idtabela"); // Recuperar Atributos dos fatos;
			ResultSet rsCubo = stCube.executeQuery("SELECT * FROM surdo"); // Recuperar Atributos dos fatos;
			
			
			while (rsCubo.next()){
				System.out.println("um linha");
				System.out.println(rsCubo.getObject(1).toString() + rsCubo.getObject(2).toString());
			}
			conn.close();
			rsCubo.close();
			stCube.close();
			System.out.println(new Date());
			
		}catch (Exception e){ e.printStackTrace();}
	}
	
	public void setCubeCollectionMetaData(){
		try {
			Class.forName("org.postgresql.Driver");   // Inicializa��o do driver jdbc
			Connection conn;
			conn = DriverManager.getConnection("jdbc:postgresql://eingrid005.unigranrio.br:5432/cubo", "globus","globus");
			Statement stCube = conn.createStatement(3,1);
			//ResultSet rsCubo = stCube.executeQuery("SELECT * FROM ((cubo As c INNER JOIN fato AS f ON c.idcubo = f.idcubo) INNER JOIN tabela t ON f.idfato = t.idtabela) INNER JOIN  atributo AS a ON t.idtabela = a.idtabela"); // Recuperar Atributos dos fatos;
			ResultSet rsCubo = stCube.executeQuery("SELECT * FROM cubo order by idcubo DESC"); // Recuperar Atributos dos fatos;
			
			
			while (rsCubo.next()){
				int cuboAtual = rsCubo.getInt("idcubo");
				int cuboAdicionarIncdice = rsCubo.getInt("idcubo");
				Cubo cube = new Cubo(rsCubo.getString(2),rsCubo.getString("servidor"),null,rsCubo.getString("conexao_url"),rsCubo.getString("conexao_usuario"),rsCubo.getString("conexao_senha"),rsCubo.getString("conexao_driver"),new Long( rsCubo.getObject("tempo_refresh").toString()).longValue());
				
				Statement stFato = conn.createStatement(3,1);
				ResultSet rsFato = stFato.executeQuery("SELECT * FROM ((cubo As c INNER JOIN fato AS f ON c.idcubo = f.idcubo) INNER JOIN tabela t ON f.idfato = t.idtabela) INNER JOIN  atributo AS a ON t.idtabela = a.idtabela WHERE c.idcubo= " + cuboAtual + " ORDER BY idatributo"); // Recuperar Atributos dos fatos; 
				while (rsFato.next()){ // Adiciona Atributos do Fato;
					Fato fato = new Fato(rsFato.getString(12));
					cube.setFato(fato);
					ChavePrimaria chp = new ChavePrimaria();
					fato.setChavePrimaria(chp);
					while (cuboAtual == rsFato.getInt("idcubo")){
						Atributo atributo = new Atributo(rsFato.getString(14),rsFato.getString("tipo"),rsFato.getObject("tamanho").toString(),rsFato.getObject("precisao").toString());
						if (rsFato.getBoolean("chaveprimaria")){
							chp.addAtributo(atributo);
						}
						atributo.setId(rsFato.getInt("idatributo"));
						fato.addAtributo(atributo/*, rsFato.getInt("idatributo")*/);
						if ( !(rsFato.next()) ){
							break;
						}else{
							//cuboAtual = rsFato.getInt("idcubo");
						}
					}
					if ( !(rsFato.next()) ){
						break;
					}
				}
				
				Statement stDimensao = conn.createStatement(3,1);
				ResultSet rsDimensao = stDimensao.executeQuery("SELECT * FROM (((fato AS f INNER JOIN chaveestrangeira AS ch ON f.idfato = ch.idfato) INNER JOIN tabela AS t ON ch.iddimensao = t.idtabela) INNER JOIN atributo AS a ON t.idtabela = a.idtabela) LEFT OUTER JOIN ligacao AS l ON a.idatributo = l.idatributoprimario WHERE f.idcubo= " + cuboAdicionarIncdice + " ORDER BY idatributo"); // Recuperar Atributos dos fatos;
				while (rsDimensao.next()){
					Dimensao dimensao = new Dimensao(rsDimensao.getString(7));
					int dimensaoAtual =  rsDimensao.getInt("iddimensao");
					dimensao.setId(dimensaoAtual);
					cube.getFato().addDimensao(dimensao/*, dimensaoAtual*/);
					System.out.println("\n\nNOVA DIMENS�O "+rsDimensao.getInt("iddimensao"));
					System.out.println("Atributo "+rsDimensao.getInt("idatributo"));
					
					while (dimensaoAtual == rsDimensao.getInt("iddimensao") ){
						Atributo atributo = new Atributo(rsDimensao.getString(9),rsDimensao.getString("tipo"),rsDimensao.getObject("tamanho").toString(),rsDimensao.getObject("precisao").toString());
						ChavePrimaria chp = new ChavePrimaria();
						dimensao.setChavePrimaria(chp);
						atributo.setId(rsFato.getInt("idatributo"));
						dimensao.addAtributo(atributo/*, rsDimensao.getInt("idatributo")*/);
						if (rsDimensao.getBoolean("chaveprimaria")){
							chp.addAtributo(atributo);
						}
						
						if ( rsDimensao.getObject("idatributoprimario") != null &&  rsDimensao.getObject("idatributoestrangeiro") != null){
							Ligacao ligacao = new Ligacao(cube.getFato().getAtributo(rsDimensao.getInt("idatributoestrangeiro")), dimensao.getAtributo(rsDimensao.getInt("idatributoprimario")) );
							cube.getFato().addLigacao(dimensaoAtual, ligacao);
						}
						
						if ( !(rsDimensao.next()) ){
							break;
						}else{
							if( dimensaoAtual != rsDimensao.getInt("iddimensao") ){
								dimensao = new Dimensao(rsDimensao.getString(7));
								dimensaoAtual = rsDimensao.getInt("iddimensao");
								dimensao.setId(dimensaoAtual);
								cube.getFato().addDimensao(dimensao/*, dimensaoAtual*/);
								System.out.println("\n\nNOVA DIMENS�O "+rsDimensao.getInt("iddimensao"));
								System.out.println("Atributo "+rsDimensao.getInt("idatributo"));
								//break;
							}
						}
					}
					
					
					System.out.println("QTD de Dimes�es "+cube.getFato().getQuantidadeDimensao());
				}
				
				
				
			}
			conn.close();
			
		}catch (Exception e){ e.printStackTrace();}
	}
	
	
	public void run(String serviceURI) {
		CubeServiceAddressingLocator locator = new CubeServiceAddressingLocator();
		
		try {
			
			
			// Create endpoint reference to service
			EndpointReferenceType endpoint = new EndpointReferenceType();
			endpoint.setAddress(new Address(serviceURI));
			
			
			// Get PortType
			CubePortType cube = locator.getCubePortTypePort(endpoint);
			
			
			//System.getenv();
			//File fileCubo = new File("/tmp/cubo.conf");
			/*String procurar = "cubeservice";
			String procurar = "drsservice";
			FileReader reader = new FileReader(System.getenv("CUBO_CONF"));
			BufferedReader leitor = new BufferedReader(reader,1*1024*1024);
			String linha = null;
			while( leitor.ready()  ) {
				linha = leitor.readLine();
				if (linha.toCharArray().length > 0){
					if(procurar.equalsIgnoreCase( String.copyValueOf(linha.toCharArray(),0,procurar.length()))){
						System.out.println( String.copyValueOf(linha.toCharArray(),procurar.length()+1,linha.length() - procurar.length()-1) );
					}
				}
			}
			leitor.close();
			reader.close();//*/
			
			
			/*
			RemoveCubeResponse rmCube = cube.removeCube(10);
			System.out.println("sucesso is: "+ rmCube.isSuccess());
			if (rmCube.isSuccess()){
			System.out.println("O cubo  \""+rmCube.getName().toUpperCase()+"\"  foi removido");}
			else { System.out.println("O cubo n�o pode ser removido");}
			//*/
			
			
			// Perform some operations and print out the resource properties
			// faz uma chamada ao m�todo 'fato'. recebe um objeto FatoResponse
			// e recupera o conte�do do atributo 'fatonome' que est� definido
			// no wsdl.
			//System.out.println("cubo 1: "+cube.fato(0).getFatonome());
			//System.out.println("cubo 2: "+cube.fato(1).getFatonome());
			//System.out.println(cube.printCubeCollection(0).getCubeColl());
			//System.out.println(cube.printCube(1).getCube());
			
			
			/*
			System.out.println("\nVou executar \"cubeMetaData\"");
			CubeMetadataResponse cubeMD = cube.getCubeMetaData(10);
			
			System.out.println("o nome deste cubo espec�fico �: "+cubeMD.getName());
			FatoMetaData fatoMD = cubeMD.getFatoMetaData();
			System.out.println("\nFATO: " + fatoMD.getName());
			System.out.println("  Atributos: ");
			for(int j=0; j < fatoMD.getFieldMetaData().length ; j++){
				//System.out.println("Campo: "+fatoMD.getFieldMetaData(j));
				System.out.println(fatoMD.getFieldMetaData(j).getName() + " - " +fatoMD.getFieldMetaData(j).isForeignKey());
			}
			System.out.println();
			
			
			DimensaoMetaData dimMD;
			
			for(int j=0; j < fatoMD.getDimensaoMetaData().length; j++){
				dimMD = fatoMD.getDimensaoMetaData(j);
				System.out.println("\n Dimens�o: "+dimMD.getName());
				System.out.println("  Atributos: ");
				for(int i=0; i <dimMD.getFieldMetaData().length; i++){
					System.out.println(dimMD.getFieldMetaData(i).getName() +" - "+dimMD.getFieldMetaData(i).isPrimaryKey());
				}
			}//*/
			
			
			/* ADICIONAR
			if ( cube.addCube(new AddCube("venda",59000,"Vendas","globus","jdbc:postgresql://eingrid002.unigranrio.br:5432/venda","globus")) ){
				System.out.println("O cubo foi adicionado");
			}else System.out.println("N�o foi cadastrado");//*/
			
	
			//setCubeCollectionMetaData();
			
			
			//adicionarVendasT(cube); //ADICIONAR CUBO VENDAS COM FATO T
			
			
						
			/*
			System.out.println("vou a adicionar");
			if ( cube.addCube(new AddCube("littleblackbook",59000,"Estoque","ogsadai","jdbc:postgresql://eingrid001.unigranrio.br:5432/ogsadai","ogsadai")) ){
				System.out.println("O cubo foi adicionado");
			}else System.out.println("N�o foi adicionado");
			//*/
			
			
			//query(cube);
			queryLittle(cube);
			
			
			//printResourceProperties(cube);
			//printMultipleResourceProperties(cube);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void printResourceProperties(CubePortType cube) throws Exception {
		GetResourcePropertyResponse cubeRP, cubeObjRP;
		String prop;

		
		cubeRP = cube.getResourceProperty(CubeQNames.RP_Cube);
		prop = cubeRP.get_any()[0].getValue();

		
		System.out.println(prop);
	}


	/*
	 * This method prints out MathService's resource properties by using the
	 * GetMultipleResourceProperties operation.
	 */
	private void printMultipleResourceProperties(CubePortType cube)
			throws Exception {
		GetMultipleResourceProperties_Element request;
		GetMultipleResourcePropertiesResponse response;

		/* Create request object */
		QName[] resourceProperties = new QName[] { CubeQNames.RP_Cube };
		request = new GetMultipleResourceProperties_Element(resourceProperties);

		/* Invoke GetMultipleResourceProperties */
		response = cube.getMultipleResourceProperties(request);

		/* Print all the resource properties returned */
		for(int i=0; i<response.get_any().length;i++)
		{
			String name = response.get_any()[i].getLocalName();
			String value = response.get_any()[i].getValue();
			System.out.println(name +": " + value);
		}
	}
	
	private static void query(CubePortType cube){
		
		try {
			/*
			System.out.println("\nPesquisando cubos:");
			CubeCollResponse collresp = cube.getCubeColl(new GetCubeColl());
			
			if ( collresp.getCubeName() == null ){
				System.out.println("O service cube n�o possui algum Cubo");}
			else {
			
				
			System.out.println("\n Cubos dispon�veis:");
			for (int i=0; i < collresp.getCubeIndex().length; i++){
				System.out.println(collresp.getCubeName(i)+" ("+collresp.getCubeServer(i)+") - "+collresp.getCubeIndex(i));
			}*/
		
		System.out.print("Escolha o cubo que deseja acessar:");
		int selectCube = Keyboard.readInt();
		System.out.println();

		//System.out.println(cube.printCube(selectCube).getCube());
		
		System.out.println("Escreva uma consulta ou \n digite 1 para executar: 'SELECT * FROM T ORDER BY id' \n digite 2 para executar: 'SELECT * FROM Mytable ORDER BY id'");
		String sql = new String(Keyboard.readString());
		if (sql.equals(new String("1")))
			sql = "SELECT * FROM T ORDER BY id";
		else
			if (sql.equals(new String("2")))
				sql = "SELECT * FROM Mytable ORDER BY id";
		
		System.out.println();


		ExecuteQueryResponse exquery = cube.executeQuery(new ExecuteQuery(selectCube, "SELECT * FROM T ORDER BY id"));
		//ExecuteQueryResponse exquery = cube.executeQuery("SELECT * FROM mytable where id < 350 and id > 150");
		
		
		
		String columns = new String(exquery.getResultColumnName(0));
		for (int k=1; k < exquery.getResultColumnName().length; k++){
			columns = columns+"    "+exquery.getResultColumnName(k);
		}
		System.out.println(columns);
		System.out.println("------------------------------------");
		
		
		if (exquery.getColumnResponse() == null){
			System.out.println("\n A CONSULTA N�O OBTEVE RESULTADOS");
		}
		else {
			
			for(int v=0;v < exquery.getColumnResponse(0).getColumn().length; v++){
		
			
			if (! (exquery.getColumnResponse(0).getColumn(v) == null ))
				System.out.print(exquery.getColumnResponse(0).getColumn(v));
			else
				System.out.print("      ");
			
			for(int u = 1; u < exquery.getResultColumnName().length; u++){
				
				if (! (exquery.getColumnResponse(u).getColumn(v) == null ))
					System.out.print("  |  " + exquery.getColumnResponse(u).getColumn(v));
				else
					System.out.print("  |        ");
			}
			
			System.out.println();
		}
			
		
		}

		
		//}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
private void queryLittle(CubePortType cube){
		
		try {
			
			//*System.out.println("\nPesquisando cubos:");
			CubeCollResponse collresp = cube.getCubeColl(new GetCubeColl());
			
			if ( collresp.getCubeName() == null ){
				System.out.println("O service cube n�o possui algum Cubo");}
			else {
			
				
			System.out.println("\n Cubos dispon�veis:");
			for (int i=0; i < collresp.getCubeIndex().length; i++){
				System.out.println(collresp.getCubeName(i)+" ("+collresp.getCubeServer(i)+") - "+collresp.getCubeIndex(i));
			}//*/
		
			/*
		int selectCube = 1;
		String sql = "SELECT * FROM littleblackbook";
		System.out.println("Consulta: "+sql);

		for(int i=0; i< 20; i++){
			ExecuteQueryResponse exquery = cube.executeQuery(new ExecuteQuery(selectCube, sql));
			System.out.println("CONSULTA "+i+1);
		}*/
		
		
		
		/*
		String columns = new String(exquery.getResultColumnName(0));
		for (int k=1; k < exquery.getResultColumnName().length; k++){
			columns = columns+"    "+exquery.getResultColumnName(k);
		}
		System.out.println(columns);
		System.out.println("------------------------------------");
		
		
		if (exquery.getColumnResponse() == null){
			System.out.println("\n A CONSULTA N�O OBTEVE RESULTADOS");
		}
		else {
			
			for(int v=0;v < exquery.getColumnResponse(0).getColumn().length; v++){
		
			
			if (! (exquery.getColumnResponse(0).getColumn(v) == null ))
				System.out.print(exquery.getColumnResponse(0).getColumn(v));
			else
				System.out.print("      ");
			
			for(int u = 1; u < exquery.getResultColumnName().length; u++){
				
				if (! (exquery.getColumnResponse(u).getColumn(v) == null ))
					System.out.print("  |  " + exquery.getColumnResponse(u).getColumn(v));
				else
					System.out.print("  |        ");
			}
			
			System.out.println();
		}
			
		
		}*/

		
		//}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

	
	
	
	public static void tratarData(){
		
		Calendar calend = Calendar.getInstance(); 
		
		calend.set(2008, 2, 18,15,40,50);  // Criar uma data
		//Date depois = calend.getTime();    // atribuir uma data a um tipo Date
		Date agora = new Date();
		//System.out.println(agora.compareTo(depois)); // Faz compara��o tamb�m com segundos e prova velmente com milisegundos
		
		calend.setTime(agora);  // Setar uma data completa a um calendar
		
		for (int i=12; i < 15; i++)
		System.out.println(calend.get(i)); //recupera todas as informa��es como ano,dia e milis
		
		System.out.println(calend.getTime());
		calend.add(Calendar.MILLISECOND, 3600000); // adiciona um valor a uma parte da data. Neste caso (1 hora = 3600000 ms) 
		System.out.println(calend.getTime());
	}
	
	public static CubePortType getPotType(String serviceURI) {
		CubeServiceAddressingLocator locator = new CubeServiceAddressingLocator();
		
		try {
			
			
			// Create endpoint reference to service
			EndpointReferenceType endpoint = new EndpointReferenceType();
			endpoint.setAddress(new Address(serviceURI));
			
			
			// Get PortType
			CubePortType cube = locator.getCubePortTypePort(endpoint);
			return cube;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {

		//tratarData();
		
		/*
		DimensaoMetaData dimensaomd = new DimensaoMetaData();
		LigacaoMetaData lig[] = new LigacaoMetaData[1];
		lig[0]= new LigacaoMetaData(new FieldMetaData(null,false,0,"id",false,null,null),new FieldMetaData(null,false,0,"id",false,null,null));
		dimensaomd.setLigacaoMetaData(lig);
		System.out.println("Liga��o"+dimensaomd.getLigacaoMetaData());
		*/
		
		//*
		//System.out.println("OI");
		String uri = "http://localhost:8443/wsrf/services/cube/Cube";//"http://eingrid005.unigranrio.br:8443/wsrf/services/cube/Cube"; //new String(args[0]); 
		Client client = new Client();
		//client.run(uri);
		//*/
		
		//query(getPotType(uri));
		
		try {
			System.out.println(getPotType(uri).printCube(6).getCube());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//consultaMysql();
	}
	
	private void adicionarVendasT(CubePortType cube){
		try {
		//* ADICIONAR
		BigInteger idCubo = cube.addCube(new AddCube(null, "t",5000,"Vendas","globus","jdbc:postgresql://eingrid002.unigranrio.br:5432/ogsadb","globus"));
		if ( idCubo != null && idCubo.intValue() > 0  ){
			System.out.println("O cubo foi adicionado");
		}else System.out.println("N�o foi cadastrado");//*/
		
		//* METADADOS
		DimensaoMetaData[] dimensaoMDa = new DimensaoMetaData[2];
		
		FieldMetaData[] fieldMDa = new FieldMetaData[1]; // Array
		FieldMetaData fielda = new FieldMetaData();
		fielda.setKey(10);
		fieldMDa[0] = fielda;
		DimensaoMetaData dimensaoa = new DimensaoMetaData(fieldMDa,50,null,null);
		LigacaoMetaData[] ligacaoMDa = new LigacaoMetaData[1];
		ligacaoMDa[0] = new LigacaoMetaData(new FieldMetaData(null,true,10,null,false,null,null), new FieldMetaData(null,false,10,null,true,null,null) );
		dimensaoa.setLigacaoMetaData(ligacaoMDa);
		dimensaoMDa[0] = dimensaoa;
		
		
		//*
		FieldMetaData[] fieldMDb = new FieldMetaData[1]; // Array
		FieldMetaData fieldb = new FieldMetaData();
		fieldb.setKey(10);
		fieldMDb[0] = fieldb;
		DimensaoMetaData dimensaob = new DimensaoMetaData(fieldMDb,100,null,null);
		LigacaoMetaData[] ligacaoMDb = new LigacaoMetaData[1];
		ligacaoMDb[0] = new LigacaoMetaData(new FieldMetaData(null,true,10,null,false,null,null), new FieldMetaData(null,false,10,null,true,null,null) );
		dimensaob.setLigacaoMetaData(ligacaoMDb);
		dimensaoMDa[1] = dimensaob;
		//*/
		
		
		//*
		FieldMetaData[] fieldMDFato = new FieldMetaData[1]; // Array
		FieldMetaData fieldFato = new FieldMetaData();
		fieldFato.setKey(10);
		fieldMDFato[0] = fieldFato;
		FatoMetaData fatoMD =  new FatoMetaData(dimensaoMDa,fieldMDFato,null);
		
		int cubeID = 10; // Mudar De acordo Com o CUBO inserido
		///
		
		// CHAVE PRIM�RIA 
		if (cube.setChavePrimaria(new SetChavePrimaria(cubeID,fatoMD))){
			CubeMetadataResponse cubeMD = cube.getCubeMetaData(cubeID);
			//System.out.println("OK");
			//System.out.println("Definida a chave prim�riado do fato");
			System.out.println("Definida a chave prim�riado do fato '"+cubeMD.getFatoMetaData().getName()+"' do cubo '"+cubeMD.getName()+"'");
		}///
		
		// RELACIONAMENTOS
		if (cube.setLigacao(new SetLigacao(cubeID,fatoMD))){
			//System.out.println("setei os metadados");
			CubeMetadataResponse cubeMD = cube.getCubeMetaData(cubeID);
			//System.out.println("OK");
			//System.out.println("Definida as liga��es das dimens�es");
			System.out.println("Definida as liga��es das dimens�es'"+cubeMD.getFatoMetaData().getName()+"' do cubo '"+cubeMD.getName()+"'");
		}//*/
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	}


}