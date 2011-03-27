package org.cube.service.impl.metadados.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrimaryKeys {
	
	public static String TABLE_CAT = "TABLE_CAT";// String => table catalog (may be null)  
	public static String TABLE_SCHEM = "TABLE_SCHEM";// String => table schema (may be null)  
	public static String TABLE_NAME = "TABLE_NAME";// String => table name 
	public static String COLUMN_NAME = "COLUMN_NAME";// String => column name 
	/*public static String FKTABLE_CAT = "FKTABLE_CAT";//FKTABLE_CAT String => foreign key table catalog (may be null)  
	public static String FKTABLE_SCHEM = "FKTABLE_SCHEM";// String => foreign key table schema (may be null) 
	public static String FKTABLE_NAME = "FKTABLE_NAME";// String => foreign key table name
	public static String FKCOLUMN_NAME = "FKCOLUMN_NAME";// String => foreign key column name 
*/	public static String KEY_SEQ = "KEY_SEQ";// short => sequence number within a foreign key
	/*public static String UPDATE_RULE = "UPDATE_RULE";// short => What happens to a foreign key when the primary key is updated
	public static String DELETE_RULE = "DELETE_RULE";// short => What happens to the foreign key when primary is deleted. 
	public static String FK_NAME = "FK_NAME";// String => foreign key name (may be null)  
*/	public static String PK_NAME = "PK_NAME";// String => primary key name (may be null) 
	/*public static String DEFERRABILITY = "DEFERRABILITY";// short => can the evaluation of foreign key constraints be deferred until commit 
*/	
	private ResultSet rsPrimaryKey;

	public PrimaryKeys(ResultSet rsPrimaryKey) {
		this.rsPrimaryKey = rsPrimaryKey;
	}
	
	public String getrsPrimaryKeyMetaData(String metaDataResultSetColumn) throws SQLException{
		return rsPrimaryKey.getString(metaDataResultSetColumn);
	}
	
	public boolean next() throws SQLException{
		return rsPrimaryKey.next();
	}
}
