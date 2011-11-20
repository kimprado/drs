package org.cube.service.impl.control;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.cube.service.impl.control.metadados.ObtemMetaDadosControl;
import org.cube.service.impl.metadados.jdbc.ForeignKeys;
import org.cube.service.impl.modelo.Atributo;
import org.cube.service.impl.modelo.ChavePrimaria;
import org.cube.service.impl.modelo.Cubo;
import org.cube.service.impl.modelo.Dimensao;
import org.cube.service.impl.modelo.Fato;
import org.cube.service.impl.modelo.Ligacao;
import org.globus.cube.stubs.Cube.ColumnResponse;
import org.globus.cube.stubs.Cube.CubeMetaData;
import org.globus.cube.stubs.Cube.CubeMetadataResponse;
import org.globus.cube.stubs.Cube.DimensaoMetaData;
import org.globus.cube.stubs.Cube.ExecuteQueryResponse;
import org.globus.cube.stubs.Cube.FatoMetaData;
import org.globus.cube.stubs.Cube.FieldMetaData;
import org.globus.cube.stubs.Cube.LigacaoMetaData;
import org.globus.index.stubs.Cube.CubeEntry;
import org.globus.index.stubs.Cube.CubeIndexPortType;
import org.globus.index.stubs.Cube.service.CubeIndexServiceAddressingLocator;

public class CubeServiceControl {

	public static int setCubeCollectionMetaData(
			HashMap<Integer, Cubo> a_cubeColl, String serviceIndexURI,
			String serviceCubeURI, String bancoMetadadosDriver,
			String bancoMetadadosConexao) throws SQLException {
		
		int idCount = 0;
		Connection conn = null;
		try {
			Class.forName(bancoMetadadosDriver); // Inicialização do driver
			// jdbc
			
			conn = DriverManager.getConnection(bancoMetadadosConexao, "globus",
					"globus");

			Statement stCubo = conn.createStatement(3, 1);

			ResultSet rsCubo = stCubo
					.executeQuery("SELECT * FROM cubo ORDER BY nome ASC"); // Recuperar
			// Atributos
			// dos
			// fatos;

			while (rsCubo.next()) {
				int cuboAtual = rsCubo.getInt("idcubo");
				if (idCount < cuboAtual) {
					idCount = cuboAtual;
				}
				int cuboAdicionarIncdice = rsCubo.getInt("idcubo");
				Cubo cube = new Cubo(rsCubo.getString(2), rsCubo
						.getString("servidor"), null, rsCubo
						.getString("conexao_url"), rsCubo
						.getString("conexao_usuario"), rsCubo
						.getString("conexao_senha"), rsCubo
						.getString("conexao_driver"), new Long(rsCubo
						.getObject("tempo_refresh").toString()).longValue());

				Statement stFato = conn.createStatement(3, 1);
				ResultSet rsFato = stFato
						.executeQuery("SELECT * FROM ((cubo As c INNER JOIN fato AS f ON c.idcubo = f.idcubo) INNER JOIN tabela t ON f.idfato = t.idtabela) INNER JOIN  atributo AS a ON t.idtabela = a.idtabela WHERE c.idcubo= "
								+ cuboAtual + " ORDER BY idatributo"); // Recuperar
				// Atributos
				// dos
				// fatos;
				while (rsFato.next()) { // Adiciona Atributos do Fato;
					Fato fato = new Fato(rsFato.getString(12));
					cube.setFato(fato);
					ChavePrimaria chp = new ChavePrimaria();
					fato.setChavePrimaria(chp);
					while (cuboAtual == rsFato.getInt("idcubo")) {
						Atributo atributo = new Atributo(rsFato.getString(14),
								rsFato.getString("tipo"), rsFato.getObject(
										"tamanho").toString(), rsFato
										.getObject("precisao").toString());
						if (rsFato.getBoolean("chaveprimaria")) {
							chp.addAtributo(atributo);
						}
						atributo.setId(rsFato.getInt("idatributo"));
						fato.addAtributo(atributo/*, rsFato.getInt("idatributo")*/);
						if (!(rsFato.next())) {
							break;
						}
					}
					if (!(rsFato.next())) {
						break;
					}
				}

				Statement stDimensao = conn.createStatement(3, 1);
				ResultSet rsDimensao = stDimensao
						.executeQuery("SELECT * FROM (((fato AS f INNER JOIN chaveestrangeira AS ch ON f.idfato = ch.idfato) INNER JOIN tabela AS t ON ch.iddimensao = t.idtabela) INNER JOIN atributo AS a ON t.idtabela = a.idtabela) LEFT OUTER JOIN ligacao AS l ON a.idatributo = l.idatributodimensao WHERE f.idcubo= "
								+ cuboAdicionarIncdice + " ORDER BY idatributo"); // Recuperar Atributos dos fatos;
				
				while (rsDimensao.next()) {
					Dimensao dimensao = new Dimensao(rsDimensao.getString(7));
					int dimensaoAtual = rsDimensao.getInt("iddimensao");
					dimensao.setId(dimensaoAtual);
					cube.getFato().addDimensao(dimensao/*, dimensaoAtual*/);
					

					while (dimensaoAtual == rsDimensao.getInt("iddimensao")) {
						Atributo atributo = new Atributo(rsDimensao
								.getString(9), rsDimensao.getString("tipo"),
								rsDimensao.getObject("tamanho").toString(),
								rsDimensao.getObject("precisao").toString());
						ChavePrimaria chp = new ChavePrimaria();
						dimensao.setChavePrimaria(chp);
						atributo.setId(rsDimensao.getInt("idatributo"));
						dimensao.addAtributo(atributo/*, rsDimensao
								.getInt("idatributo")*/);
						if (rsDimensao.getBoolean("chaveprimaria")) {
							chp.addAtributo(atributo);
						}

						if (rsDimensao.getObject("idatributofato") != null && rsDimensao.getObject("idatributodimensao") != null) {
							Ligacao ligacao = new Ligacao( cube.getFato().getAtributo(rsDimensao.getInt("idatributofato")),
									dimensao.getAtributo(rsDimensao.getInt("idatributodimensao")));
							cube.getFato().addLigacao(dimensaoAtual, ligacao);
						}

						if (!(rsDimensao.next())) {
							break;
						} else {
							if (dimensaoAtual != rsDimensao
									.getInt("iddimensao")) {
								dimensao = new Dimensao(rsDimensao.getString(7));
								dimensaoAtual = rsDimensao.getInt("iddimensao");
								dimensao.setId(dimensaoAtual);
								cube.getFato().addDimensao(dimensao/*, dimensaoAtual*/);
							}
						}
					}

				}

				cube.setURIService(serviceCubeURI); // CubeService
				a_cubeColl.put(new Integer(cuboAtual), cube);
				cube.setTimer(serviceIndexURI, cube.getRefresh(), cuboAtual);  //TAREFA

			}
			return idCount;

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(conn != null){
				conn.close();
			}
		}
		
		return idCount;
	}

	public static boolean registerCubeIndexService(String serviceIndexURI,
			Cubo cb, int index) {

		CubeIndexServiceAddressingLocator locator = new CubeIndexServiceAddressingLocator();
		try {

			// Create endpoint reference to service
			EndpointReferenceType endpoint = new EndpointReferenceType();
			endpoint.setAddress(new Address(serviceIndexURI));
			CubeIndexPortType cubeIndex = locator
					.getCubeIndexPortTypePort(endpoint);

			CubeEntry entry = new CubeEntry(0, index, cb.getNome(), null, cb
					.getURIService());

			int keyIndex = cubeIndex.addCubeEntry(entry);

			cb.setKeyindex(keyIndex);

			// System.out.println("O novo cubo "+cb.getNome()+" foi adicionado
			// ao 'CubeIndex' com o \"KEY\": "+cb.getKeyindex());

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean removeCubeIndexService(Cubo cb, String serviceIndexURI) {

		CubeIndexServiceAddressingLocator locator = new CubeIndexServiceAddressingLocator();
		try {

			// Create endpoint reference to service
			EndpointReferenceType endpoint = new EndpointReferenceType();
			endpoint.setAddress(new Address(serviceIndexURI));
			CubeIndexPortType cubeIndex = locator
					.getCubeIndexPortTypePort(endpoint);

			cb.cancelTimer();
			cubeIndex.removeEntry(cb.getKeyindex());

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static CubeMetadataResponse getCubeMetadata(Cubo cb) {
		
		//Dados do Cubo
		CubeMetaData cubeMetaData = new CubeMetaData();
		cubeMetaData.setId( cb.getId() );
		cubeMetaData.setName( cb.getNome() );
		cubeMetaData.setUri( cb.getURIService() );
		cubeMetaData.setUser( cb.getConnectionUser() );
		cubeMetaData.setPassword( cb.getConnectionPassword() );
		cubeMetaData.setConnectionUrl( cb.getConnectionUrl() );
		cubeMetaData.setDriver( cb.getDriver() );
		cubeMetaData.setMillisecond( cb.getRefresh() );
		
		
		// System.out.println("\nEstou em getCubeMetadata");
		Fato fato = cb.getFato();
		// System.out.println("FATO: "+fato);
		FatoMetaData ftmd = new FatoMetaData(); // a terminação 'md' se refere a
		// meta data

		ftmd.setName(fato.getNome());

		FieldMetaData[] fields = new FieldMetaData[fato.GetQuantidadeAtributo()];
		int posicao = 0;
		for (int i = 0; i < fato.getIdMaxAtributo() + 1; i++) {

			if (fato.contemAtributo(i)) {

				FieldMetaData fdmd = new FieldMetaData();
				fdmd.setName(fato.getAtributo(i).getName());
				fdmd.setKey(i);
				fdmd.setType(fato.getAtributo(i).getTipo());
				fdmd.setSize(fato.getAtributo(i).getTamanho());
				fdmd.setDecimal(fato.getAtributo(i).getDecimal());
				if (fato.getAtributo(i).getLigacao() != null) {
					fdmd.setForeignKey(true);
				} else {
					fdmd.setForeignKey(false);
				}

				if (fato.getAtributo(i).getChavePrimaria() != null) {
					fdmd.setPrimaryKey(true);
				} else {
					fdmd.setPrimaryKey(false);
				}

				fields[posicao] = fdmd;
				posicao++;
			}

		}
		ftmd.setFieldMetaData(fields);

		DimensaoMetaData[] dims = new DimensaoMetaData[fato
				.getQuantidadeDimensao()];
		int posicaoDimensao = 0;
		for (int i = 0; i < fato.getIdMaxDimensao() + 1; i++) {

			if (fato.contemDimensao(i)) {

				DimensaoMetaData dimensaomd = new DimensaoMetaData();
				Dimensao dimensao = fato.getdimensao(i);

				dimensaomd.setName(dimensao.getNome());
				dimensaomd.setKey(i);

				int idLigacao = 0; // obter qtd de ligações

				LigacaoMetaData ligAUX[] = new LigacaoMetaData[dimensao
						.GetQuantidadeAtributo()];

				FieldMetaData[] fieldsDimen = new FieldMetaData[dimensao
						.GetQuantidadeAtributo()];
				int posicaoCampo = 0;
				for (int j = 0; j < dimensao.getIdMaxAtributo() + 1; j++) {

					if (dimensao.contemAtributo(j)) {

						FieldMetaData fdmd = new FieldMetaData();
						fdmd.setName(dimensao.getAtributo(j).getName());
						fdmd.setKey(j);
						fdmd.setType(dimensao.getAtributo(j).getTipo());
						fdmd.setSize(dimensao.getAtributo(j).getTamanho());
						fdmd.setDecimal(dimensao.getAtributo(j).getDecimal());
						if (dimensao.getAtributo(j).getLigacao() != null) {
							// fdmd.setForeignKey(true);
							Ligacao ligacao = dimensao.getAtributo(j)
									.getLigacao();

							FieldMetaData estrangeiro = new FieldMetaData();
							estrangeiro.setName(ligacao.getAtributoFato()
									.getName());

							FieldMetaData primario = new FieldMetaData();
							primario.setName(fdmd.getName());
							primario.setKey(fdmd.getKey());

							ligAUX[idLigacao] = new LigacaoMetaData(
									estrangeiro, primario);

							idLigacao++;

						}
						
						// Definir Chave Primária
						if (dimensao.getAtributo(j).getChavePrimaria() != null) {
							fdmd.setPrimaryKey(true);
						} else {
							fdmd.setPrimaryKey(false);
						}
						
						fdmd.setForeignKey(dimensao.getAtributo(j).isChaveEstrangeira()); // Definir Chave Estrangeira

						fieldsDimen[posicaoCampo] = fdmd;
						posicaoCampo++;
					}// fim testar existe atributo

				}// for
				dimensaomd.setFieldMetaData(fieldsDimen);

				LigacaoMetaData ligacoes[] = new LigacaoMetaData[idLigacao];
				for (int l = 0; l < idLigacao; l++) {
					ligacoes[l] = ligAUX[l];
				}
				
				dimensaomd.setLigacaoMetaData(ligacoes);

				dims[posicaoDimensao] = dimensaomd;
				posicaoDimensao++;
			}// fim if testar existe dimensao

		}

		ftmd.setDimensaoMetaData(dims);

		CubeMetadataResponse cubeMetaDataResponse = new CubeMetadataResponse();
		cubeMetaDataResponse.setName(cb.getNome());
		cubeMetaDataResponse.setFatoMetaData(ftmd);
		
		cubeMetaData.setFato( ftmd );
		cubeMetaDataResponse.setCubeMetaData(cubeMetaData);
		
		return cubeMetaDataResponse;

	}

	public static ExecuteQueryResponse executeQuery(Connection conn, String sql) {


		try {
			// Statement st = conn.createStatement(3,1);
			
			//System.out.println("\n\n\nCriar Conexão\n\n");
			//Statement st = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			Statement st = conn.createStatement();
			
			
			
			ResultSet rs = st.executeQuery(sql);

			int colSel = rs.getMetaData().getColumnCount(); // Qtd de Colunas Selecionadas
			int linSel; // Qtd de Linhas Selecionadas
			
			
			// armazena o nome dos campos selecionados Pela consulta
			String[] columnName = new String[rs.getMetaData().getColumnCount()];
			for (int c = 1; c < rs.getMetaData().getColumnCount() + 1; c++) {
				columnName[c - 1] = rs.getMetaData().getColumnName(c);
			}
			
			
			// listasResultados Irá armazenar as coleções que representam colunas do resultado
			Object[] listasResultados = new Object[colSel];
			for (int i = 0; i < colSel; i++) {
				listasResultados[i] = new LinkedList<String>(); // Inicia os Objetos
			}

			while (rs.next()) { // Varre o resultado da consulta recuperando os campos
				for (int i = 0; i < colSel; i++) {
					if (rs.getObject(i+1) != null){ // Testa se um Campo não é Nullo
						((LinkedList<String>) listasResultados[i]).add(rs.getObject(i+1).toString());
					}else{
						((LinkedList<String>) listasResultados[i]).add(null);
					}	
				}
			}
			
			
		    
			
			// A QTD de Objetos de uma Coleção é a mesma de linhas recuperadas na Consulta
			linSel = ((LinkedList<String>)listasResultados[0]).size();
			
			 // Recupera os campos das coleções e monta o resultado
			ColumnResponse[] colresColl = null;
			if (linSel > 0){
				 //Cada posição de 'colresColl' aponta para um Objeto que possui um Array que representa todos os valores de determinada coluna
				colresColl = new ColumnResponse[colSel];
				for(int i = 0; i < colSel; i++){
					// Com ToArray do LinkedList são recuperados os objetos que representam uma coluna 
					colresColl[i] = new ColumnResponse(((LinkedList<String>)listasResultados[i]).toArray(new String[linSel]) , columnName[i]);
				}
				rs.close();
				st.close();
				conn.close(); //Fecha a conexão
				return new ExecuteQueryResponse(colresColl, columnName); //Retorna o resultado
			}
			
			rs.close();
			st.close();
			conn.close(); //Fecha a conexão

			return new ExecuteQueryResponse(colresColl, columnName);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void printMetaData(Connection conn) {

		try {

			String[] typeTable = new String[1];
			typeTable[0] = "TABLE";

			DatabaseMetaData dbmd = conn.getMetaData();
			ResultSet rsTabela = dbmd.getTables(conn.getCatalog(), null, null,
					typeTable);

			while (rsTabela.next()) {

				 System.out.println("Tabela: " +
				 rsTabela.getString("table_name"));

				ResultSet rsFild = dbmd.getColumns(null, null, rsTabela
						.getString("table_name"), null);
				 System.out.println(" Atributos: ");
				while (rsFild.next()) {
					System.out.println(rsFild.getString("COLUMN_NAME")+"" +
					"("+rsFild.getString("TYPE_NAME") + " - " +
					rsFild.getString("COLUMN_SIZE") + ")");
					for (int z = 1; z < 19; z++) {
						/*System.out.println(rsFild.getMetaData().getColumnName(z)
						+ ": " + rsFild.getString(z));*/
					}
				}

				// System.out.println("\n \n");
			}
			
			System.out.println("\nForeign Keys loja");
			//chaves-estrangeiras
			ResultSet rs = dbmd.getImportedKeys(null, null, "loja");
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			
			while (rs.next()) {
				//System.out.println("\t PKTABLE_NAME"+/*rsmd.getColumnName(i)+*/"=\t"+rs.getString("PKTABLE_NAME") +"("+rs.getString("PKCOLUMN_NAME")+") - " +rs.getString("FKCOLUMN_NAME"));
				System.out.println("\t" + rs.getString(ForeignKeys.FK_NAME) + ":\t"+rs.getString(ForeignKeys.PKTABLE_NAME) +"("+rs.getString(ForeignKeys.PKCOLUMN_NAME)+") - "   + "\t"+rs.getString(ForeignKeys.FKTABLE_NAME) +"("+rs.getString(ForeignKeys.FKCOLUMN_NAME)+")  ");
				/*for (int i=1;i<=cols;i++) {
					System.out.println("\t"+rsmd.getColumnName(i)+"=\t"+rs.getString(i));
				}
				System.out.println("");*/
			}
			
			System.out.println("\nForeign Keys vendas");
			
			rs = dbmd.getImportedKeys(null, null, "vendas");
			rsmd = rs.getMetaData();
			cols = rsmd.getColumnCount();
			
			while (rs.next()) {
				//System.out.println("\t PKTABLE_NAME"+/*rsmd.getColumnName(i)+*/"=\t"+rs.getString("PKTABLE_NAME") +"("+rs.getString("PKCOLUMN_NAME")+") - " +rs.getString("FKCOLUMN_NAME"));
				System.out.println("\t" + rs.getString(ForeignKeys.FK_NAME) + ":\t"+rs.getString(ForeignKeys.PKTABLE_NAME) +"("+rs.getString(ForeignKeys.PKCOLUMN_NAME)+") - "   + "\t"+rs.getString(ForeignKeys.FKTABLE_NAME) +"("+rs.getString(ForeignKeys.FKCOLUMN_NAME)+")  ");
				/*for (int i=1;i<=cols;i++) {
					System.out.println("\t"+rsmd.getColumnName(i)+"=\t"+rs.getString(i));
				}
				System.out.println("");*/
			}
			
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Deprecated
	public static void setCubeMetaDataDesativado(Cubo cube, String fato) {
		Connection conn = cube.getConnection();

		try {

			String[] typeTable = new String[1];
			typeTable[0] = "TABLE";

			DatabaseMetaData dbmd = conn.getMetaData();
			cube.setNome(conn.getCatalog());
			ResultSet rsTabela = dbmd.getTables(conn.getCatalog(), null, fato,
					typeTable);

			// System.out.println(conn.getCatalog());

			// System.out.println("criar Fato venda");
			if (rsTabela.next()) {
				cube.setFato(new Fato(rsTabela.getString("table_name")));
				ResultSet rsFild = dbmd.getColumns(null, null, rsTabela
						.getString("table_name"), null);
				while (rsFild.next()) {
					// System.out.println(rsFild.getString("COLUMN_SIZE"));
					cube.getFato().addAtributo(
							new Atributo(rsFild.getString("COLUMN_NAME"),
									rsFild.getString("TYPE_NAME"), rsFild
											.getString("COLUMN_SIZE"), rsFild
											.getString("DECIMAL_DIGITS")));
				}
			}
			// System.out.println("Fato venda Criado");

			ResultSet rsDim = dbmd.getTables(conn.getCatalog(), null, null,
					typeTable);

			while (rsDim.next()) {
				Dimensao dm = new Dimensao(rsDim.getString("table_name"));
				// System.out.println("Nova dimensão: "+ dm.Getnome());
				if (cube.getFato().getNome().equals(dm.getNome()) == false) {
					cube.getFato().addDimensao(dm);
					ResultSet rsFild = dbmd.getColumns(null, null, rsDim
							.getString("table_name"), null);
					while (rsFild.next()) {
						// System.out.println(rsFild.getString("COLUMN_SIZE"));
						dm.addAtributo(new Atributo(rsFild
								.getString("COLUMN_NAME"), rsFild
								.getString("TYPE_NAME"), rsFild
								.getString("COLUMN_SIZE"), rsFild
								.getString("DECIMAL_DIGITS")));
					}
				}
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void setCubeMetaData(Cubo cube) throws SQLException {
		Connection conn = cube.getConnection();
		try {
			String catalogo = conn.getCatalog();
			cube.setFato( new ObtemMetaDadosControl().obterFatoMetaData(conn, catalogo));
			cube.getFato().setCubo(cube);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}
}
