package org.cube.service.impl.metadados.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ForeignKeys {
	
	public static String PKTABLE_CAT = "PKTABLE_CAT";// String => primary key table catalog being imported (may be null) 
	public static String PKTABLE_SCHEM = "PKTABLE_SCHEM";// String => primary key table schema being imported (may be null) 
	public static String PKTABLE_NAME = "PKTABLE_NAME";// String => primary key table name being imported 
	public static String PKCOLUMN_NAME = "PKCOLUMN_NAME";// String => primary key table name being imported
	public static String FKTABLE_CAT = "FKTABLE_CAT";//FKTABLE_CAT String => foreign key table catalog (may be null)  
	public static String FKTABLE_SCHEM = "FKTABLE_SCHEM";// String => foreign key table schema (may be null) 
	public static String FKTABLE_NAME = "FKTABLE_NAME";// String => foreign key table name
	public static String FKCOLUMN_NAME = "FKCOLUMN_NAME";// String => foreign key column name 
	public static String KEY_SEQ = "KEY_SEQ";// short => sequence number within a foreign key
	public static String UPDATE_RULE = "UPDATE_RULE";// short => What happens to a foreign key when the primary key is updated
	public static String DELETE_RULE = "DELETE_RULE";// short => What happens to the foreign key when primary is deleted. 
	public static String FK_NAME = "FK_NAME";// String => foreign key name (may be null)  
	public static String PK_NAME = "PK_NAME";// String => primary key name (may be null) 
	public static String DEFERRABILITY = "DEFERRABILITY";// short => can the evaluation of foreign key constraints be deferred until commit 
	
	private ResultSet rsForeignKey;

	public ForeignKeys(ResultSet rsForeignKey) {
		this.rsForeignKey = rsForeignKey;
	}
	
	public String getForeignKeyMetaDataString(String metaDataResultSetColumn) throws SQLException{
		return rsForeignKey.getString(metaDataResultSetColumn);
	}
	
	public short getForeignKeyMetaDataShort(String metaDataResultSetColumn) throws SQLException{
		return rsForeignKey.getShort(metaDataResultSetColumn);
	}
	
	public boolean next() throws SQLException{
		return rsForeignKey.next();
	}
}
