package org.cube.service.impl.control.metadados;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.cube.service.impl.metadados.jdbc.Columns;
import org.cube.service.impl.metadados.jdbc.ForeignKeys;
import org.cube.service.impl.metadados.jdbc.PrimaryKeys;
import org.cube.service.impl.metadados.jdbc.Tables;
import org.cube.service.impl.modelo.Atributo;
import org.cube.service.impl.modelo.ChaveEstrangeira;
import org.cube.service.impl.modelo.Dimensao;
import org.cube.service.impl.modelo.Fato;
import org.cube.service.impl.modelo.Ligacao;
import org.cube.service.impl.modelo.Tabela;

public class ObtemMetaDadosControl {
	
	/**
	 * Assume que a primeira tabela que encontra que possui chave estrangeira, ou seja, campo referenciando a chave de outra
	 * tabela, é o fato do modelo dimensional estrela.
	 * @throws SQLException 
	 */
	
	public Fato obterFatoMetaData(Connection conn, String catalogo) throws SQLException{
		
		DatabaseMetaData dbmd = conn.getMetaData();
		
		String nomeTabelaFato = detectaFatoPelasForengnKeys(dbmd, catalogo);
		Fato fato = criaTabelaFato(dbmd, catalogo, nomeTabelaFato) ;
		criaTabelasDimensao(dbmd, catalogo, fato);
		criaLigacoesDeChavesEstrangeirasParaFato(fato, dbmd);
		criaChavePrimariaParaFato(fato, dbmd);
		return fato;
	}
	
	public String detectaFatoPelasForengnKeys(DatabaseMetaData dbmd, String catalago) throws SQLException{
		
		String nomeTabelaFato = null;
		ResultSet rsTabela = dbmd.getTables(catalago, null, null, Tables.TABLETYPE);
		
		while (rsTabela.next()) {
			
			String nomeTabelaAtual = rsTabela.getString( Tables.TABLE_NAME );
			ResultSet rs = dbmd.getImportedKeys(null, null, nomeTabelaAtual);
			
			while (rs.next()) {
				nomeTabelaFato = nomeTabelaAtual;
				break;
			}
		}
		
		return nomeTabelaFato;
	}
	
	public Fato criaTabelaFato(DatabaseMetaData dbmd, String catalogo, String nomeTabelaFato) throws SQLException{
		Fato fato = null;
		Tables tables = new Tables(dbmd, catalogo, nomeTabelaFato);
		if (tables.next()) {
			fato = new Fato(nomeTabelaFato);
			criaAtributosParaTabela(fato, dbmd, tables);
		}
		return fato;
	}
	
	public void criaTabelasDimensao(DatabaseMetaData dbmd, String catalogo, Fato fato) throws SQLException{
		Tables tables = new Tables(dbmd, catalogo);
		while (tables.next()) {
			String nomeTabela = tables.getTableMetaDataString(Tables.TABLE_NAME);
			if ( !fato.getNome().equals(nomeTabela) ) {
				Dimensao dimensao = new Dimensao(nomeTabela);
				criaAtributosParaTabela(dimensao, dbmd, tables);
				fato.addDimensao(dimensao);
			}
			
		}
	}
	
	public void criaAtributosParaTabela(Tabela tabela, DatabaseMetaData dbmd, Tables tables) throws SQLException{
		Columns columns = tables.getColumns(dbmd);
		while( columns.next() ){
			tabela.addAtributo( criaAtributo(tabela, columns) );
		}
	}
	
	public Atributo criaAtributo(Tabela tabela, Columns columns) throws SQLException{
		Atributo atributo = new Atributo();
		atributo.setTabela(tabela);
		atributo.setNome(columns.getColumnMetaDataString(Columns.COLUMN_NAME));
		atributo.setTipo(columns.getColumnMetaDataString(Columns.TYPE_NAME));
		atributo.setTamanho(columns.getColumnMetaDataString(Columns.COLUMN_SIZE));
		try{
			atributo.setDecimal(Integer.valueOf(columns.getColumnMetaDataString(Columns.DECIMAL_DIGITS)).toString());
		} catch (NullPointerException e){ }
		
		return atributo;
	}
	
	public Ligacao criaLigacao(Tabela table1, Tabela table2, ForeignKeys foreignKey) throws SQLException{
		
		Atributo atributo1 = null;
		Atributo atributo2 = null;
		
		Ligacao ligacao = null;
		
		String tabelaComChaveEstrangeira = foreignKey.getForeignKeyMetaDataString(ForeignKeys.FKTABLE_NAME);
		String atributoChaveEstrangeira = foreignKey.getForeignKeyMetaDataString(ForeignKeys.FKCOLUMN_NAME);
		
		String tabelaComChavePrimaria = foreignKey.getForeignKeyMetaDataString(ForeignKeys.PKTABLE_NAME);
		String atributoChavePrimaria = foreignKey.getForeignKeyMetaDataString(ForeignKeys.PKCOLUMN_NAME);
		
		if ( tabelaComChaveEstrangeira.equals(table1.getNome()) & tabelaComChavePrimaria.equals(table2.getNome()) ) {
			atributo1 = table1.getAtributo(atributoChaveEstrangeira);
			atributo2 = table2.getAtributo(atributoChavePrimaria);
			ligacao = new Ligacao(atributo1, atributo2);
		}
		else if ( tabelaComChaveEstrangeira.equals(table2.getNome()) & tabelaComChavePrimaria.equals(table1.getNome()) ) {
			atributo1 = table1.getAtributo(atributoChavePrimaria);
			atributo2 = table2.getAtributo(atributoChaveEstrangeira);
			ligacao = new Ligacao(atributo2, atributo1);
		}
		
		try {
			atributo1.setLigacao(ligacao);
			atributo2.setLigacao(ligacao);
		} catch (NullPointerException e) {
			
		}
		
		return ligacao;
	}
	
	public ChaveEstrangeira criaChaveEstrangeiraParaFato(Fato fato, Dimensao dimensao, ForeignKeys foreignKey) throws SQLException{
		
		ChaveEstrangeira chaveEstrangeira =  new ChaveEstrangeira(fato, dimensao);
		Ligacao ligacao = criaLigacao(fato, dimensao, foreignKey);
		if ( ligacao != null){
			chaveEstrangeira.addLigacao(ligacao);
		}
		
		return chaveEstrangeira;
		
	}
	
	public void criaLigacaoDeChaveEstrangeiraParaFato( Fato fato, ForeignKeys foreignKey ) throws SQLException{
		for (ChaveEstrangeira chaveEstrangeira : fato.getChaveEstrangeira()) {
			Dimensao dimensao = chaveEstrangeira.getDimensao();
			if ( dimensao.getNome().equals(foreignKey.getForeignKeyMetaDataString(ForeignKeys.PKTABLE_NAME)) ){
				Ligacao ligacao = criaLigacao(fato, dimensao, foreignKey);
				if ( ligacao != null){
					chaveEstrangeira.addLigacao(ligacao);
					chaveEstrangeira.setNome(foreignKey.getForeignKeyMetaDataString(ForeignKeys.FK_NAME));
				}
			}
			
		}
	}
	
	public void criaLigacoesDeChavesEstrangeirasParaFato( Fato fato, DatabaseMetaData dbmd ) throws SQLException {
		
		ResultSet rsForeignKey = dbmd.getImportedKeys(null, null, fato.getNome());
			
		while (rsForeignKey.next()){
			ForeignKeys foreignKey = new ForeignKeys(rsForeignKey);
			//Cria uma ligação para a chave estrangeira correspondente
			criaLigacaoDeChaveEstrangeiraParaFato(fato, foreignKey);
		}
	}
	
	public void adicionaAtributoNaChavePrimaria(Tabela tabela, PrimaryKeys primaryKey) throws SQLException{
		
		String atributoChavePrimaria = primaryKey.getrsPrimaryKeyMetaData(PrimaryKeys.COLUMN_NAME);
		
		Atributo atributo = tabela.getAtributo(atributoChavePrimaria);
		if ( atributo != null){
			tabela.getChavePrimaria().addAtributo(atributo);
			atributo.setChavePrimaria(tabela.getChavePrimaria());
			tabela.getChavePrimaria().setNome(primaryKey.getrsPrimaryKeyMetaData(PrimaryKeys.PK_NAME));
		}
		
		
	}
	
	public void criaChavePrimariaParaTabela( Tabela tabela, DatabaseMetaData dbmd ) throws SQLException {
		ResultSet rsPrimaryKey = dbmd.getPrimaryKeys(null, null, tabela.getNome());
		
		while (rsPrimaryKey.next()){
			PrimaryKeys primaryKey = new PrimaryKeys(rsPrimaryKey);
			//Cria uma ligação para a chave estrangeira correspondente
			adicionaAtributoNaChavePrimaria(tabela, primaryKey);
		}
	}
	
	public void criaChavePrimariaParaFato( Fato fato, DatabaseMetaData dbmd  ) throws SQLException{
		
		criaChavePrimariaParaTabela(fato, dbmd);
		
		for (ChaveEstrangeira chaveEstrangeira : fato.getChaveEstrangeira()) {
			Dimensao dimensao = chaveEstrangeira.getDimensao();
			criaChavePrimariaParaTabela(dimensao, dbmd);
			
		}
	}
}
