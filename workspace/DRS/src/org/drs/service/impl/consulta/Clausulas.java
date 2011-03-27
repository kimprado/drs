package org.drs.service.impl.consulta;

public class Clausulas {
	
	private String a_Select;
	
	private String a_from;
	 
	private String a_where;
	 
	private String a_groupBy;
	 
	private String a_having;
	 
	private String a_orderBy;
	 
	private String a_distinct;
	
	
	
	public Clausulas(){
		a_Select = null;
		a_from = null;
		a_where = null;
		a_groupBy = null;
		a_having = null;
		a_orderBy = null;
		a_distinct = null;
	}
	
	
	public String getSelect(){
		return a_Select;
	}
	
	public void setSelect(String select){
		a_Select = select;
	}
	
	
	public String getFrom(){
		return a_from;
	}
	
	public void setFrom(String from){
		a_from = from;
	}
	
	public String getWhere(){
		return a_where;
	}
	
	public void setWhere(String where){
		a_where = where;
	}
	
	public String getOrderBy(){
		return a_orderBy;
	}
	
	public void setOrderBy(String orderBy){
		a_orderBy = orderBy;
	}
	
	public String getGroupBy(){
		return a_groupBy;
	}
	
	public void setGroupBy(String groupBy){
		a_groupBy = groupBy;
	}
	
	public String getHaving(){
		return a_having;
	}
	
	public void setHaving(String having){
		a_having = having;
	}
	
	public String getDistinct(){
		return a_distinct;
	}
	
	public void setDistinct(String distinct){
		a_distinct = distinct;
	}
	
	public String getSQL(){
		String sql = null;
		
		if (a_Select != null && a_from != null ){
			sql = "SELECT " + a_Select + " FROM "+a_from;
			
			if (a_where != null){
				sql = sql + " WHERE "+a_where;
			}
			
			if (a_groupBy != null && !("".equals(a_groupBy)) ){
				sql = sql + " GROUP BY " + a_groupBy;
				if (a_having != null && !("".equals(a_having)) ){
					sql = sql + " HAVING " + a_having;
				}
			}
			
			if (a_orderBy != null && !("".equals(a_orderBy)) ){
				sql = sql + " ORDER BY " + a_orderBy;
			}
			
			return sql;
		
		}
		
		return sql;	
	}
}
 
