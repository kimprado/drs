package org.cube.service.impl.metadados.jdbc;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tables {
	public static String[] TABLETYPE = {"TABLE"};
	
	public static String TABLE_NAME = "TABLE_NAME"; //String => table name
	
	ResultSet rsTable;
	
	/**
	 * Obtém metadados da tabela. TableName NULL para obter todas as Tabelas
	 * 
	 * @param dbmd
	 * @param catalog
	 * @param tableName
	 * @throws SQLException
	 */
	
	public Tables(DatabaseMetaData dbmd, String catalog, String tableName) throws SQLException {
		this.rsTable = dbmd.getTables(catalog, null, tableName, TABLETYPE);
	}
	
	/**
	 * 
	 * @param dbmd
	 * @param catalog
	 * @throws SQLException
	 */
	public Tables(DatabaseMetaData dbmd, String catalog) throws SQLException {
		this(dbmd, catalog, null);
	}
	
	public String getTableMetaDataString(String metaDataResultSetColumn) throws SQLException{
		return rsTable.getString(metaDataResultSetColumn);
	}
	
	/**
	 * 
	 * @return true or false to indicate if resultSet has more elements
	 * @throws SQLException
	 */
	public boolean next() throws SQLException{
		return rsTable.next();
	}
	
	public Columns getColumns(DatabaseMetaData dbmd) throws SQLException{
		ResultSet rsFild = dbmd.getColumns(null, null,
				rsTable.getString(TABLE_NAME), null);
		Columns columns = new Columns(rsFild);
		return columns;
	}
	
}
