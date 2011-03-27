package org.cube.service.impl.metadados.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Columns {
	
	public static String TABLE_CAT = "TABLE_CAT"; //String => table catalog (may be null)
	public static String TABLE_SCHEM = "TABLE_SCHEM"; //String => table schema (may be null)
	public static String TABLE_NAME = "TABLE_NAME"; //String => table name
	public static String COLUMN_NAME = "COLUMN_NAME"; //String => column name
	public static String DATA_TYPE = "DATA_TYPE"; //int => SQL type from java.sql.Types
	public static String TYPE_NAME = "TYPE_NAME"; //String => Data source dependent type name, for a UDT the type name is fully qualified
	public static String COLUMN_SIZE = "COLUMN_SIZE"; //int => column size.
	//public static String BUFFER_LENGTH = "BUFFER_LENGTH";// is not used.
	public static String DECIMAL_DIGITS = "DECIMAL_DIGITS"; //int => the number of fractional digits. Null is returned for data types where DECIMAL_DIGITS is not applicable.
	public static String NUM_PREC_RADIX = "NUM_PREC_RADIX"; //int => Radix (typically either 10 or 2)
	public static String NULLABLE = "NULLABLE"; //int => is NULL allowed.
	
	public static String REMARKS = "REMARKS"; //String => comment describing column (may be null)
	public static String COLUMN_DEF = "COLUMN_DEF"; //String => default value for the column, which should be interpreted as a string when the value is enclosed in single quotes (may be null)
	public static String SQL_DATA_TYPE = "SQL_DATA_TYPE"; //int => unused
	public static String SQL_DATETIME_SUB = "SQL_DATETIME_SUB"; //int => unused
	public static String CHAR_OCTET_LENGTH = "CHAR_OCTET_LENGTH"; //int => for char types the maximum number of bytes in the column
	public static String ORDINAL_POSITION = "ORDINAL_POSITION"; //int => index of column in table (starting at 1)
	public static String IS_NULLABLE = "IS_NULLABLE"; //String => ISO rules are used to determine the nullability for a column.
	
	public static String SCOPE_CATLOG = "SCOPE_CATLOG"; //String => catalog of table that is the scope of a reference attribute (null if DATA_TYPE isn't REF)
	public static String SCOPE_SCHEMA = "SCOPE_SCHEMA"; //String => schema of table that is the scope of a reference attribute (null if the DATA_TYPE isn't REF)
	public static String SCOPE_TABLE = "SCOPE_TABLE"; //String => table name that this the scope of a reference attribure (null if the DATA_TYPE isn't REF)
	public static String SOURCE_DATA_TYPE = "SOURCE_DATA_TYPE";// short => source type of a distinct type or user-generated Ref type, SQL type from java.sql.Types (null if DATA_TYPE isn't DISTINCT or user-generated REF)
	public static String IS_AUTOINCREMENT = "IS_AUTOINCREMENT"; //String => Indicates whether this column is auto incremented
	
	private ResultSet rsColumn;

	public Columns(ResultSet rsColumn) {
		this.rsColumn = rsColumn;
	}
	
	public String getColumnMetaDataString(String metaDataResultSetColumnInformation) throws SQLException{
		return rsColumn.getString(metaDataResultSetColumnInformation);
	}
	
	public int getColumnMetaDataInt(String metaDataResultSetColumnInformation) throws SQLException{
		return rsColumn.getInt(metaDataResultSetColumnInformation);
	}
	
	public short getColumnMetaDataShort(String metaDataResultSetColumnInformation) throws SQLException{
		return rsColumn.getShort(metaDataResultSetColumnInformation);
	}
	
	public boolean next() throws SQLException{
		return rsColumn.next();
	}
}
