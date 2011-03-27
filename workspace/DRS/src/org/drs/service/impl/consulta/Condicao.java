package org.drs.service.impl.consulta;

public class Condicao {
 
	private int a_logico;
	 
	private int a_comparacao;
	 
	private String a_valor;

	
	public Condicao(){
		
	}
	
	public Condicao(int logico, int comparacao, String valor){
		a_logico = logico;
		a_comparacao = comparacao;
		a_valor = valor;
	}
	
	public int getLogico(){
		return a_logico;
	}
	
	public void setLogico(int logico){
		a_logico = logico;
	}
	
	public int getComparacao(){
		return a_comparacao;
	}
	
	public void setComparacao(int comparacao){
		a_comparacao = comparacao;
	}
	
	public String getValor(){
		return a_valor;
	}
	
	public void setValor(String valor){
		a_valor = valor;
	}
	
	
	public Clausulas getClausulas(Clausulas cls, String campo, String tipo){

		if (cls.getWhere() == null){
			if ( a_logico == 3 ){
				cls.setWhere(" " + toStrLogico() + " " + campo + " " + toStrComparacao() + " " + toStrValor(tipo) );
			} else{
				cls.setWhere(" " + campo + " " + toStrComparacao() + " " + toStrValor(tipo) );
			}
		}else{
			if ( a_logico == 3 ){
				cls.setWhere(cls.getWhere() + " and " + toStrLogico() + " " + campo + " " + toStrComparacao() + " " + toStrValor(tipo) );
			}else{
				cls.setWhere(cls.getWhere() + " " + toStrLogico() + " " + campo + " " + toStrComparacao() + " " + toStrValor(tipo) );
			}
		}
		
		
		return cls;
	}
	
	
	private String toStrLogico(){
		switch (a_logico) {
		case 1:	return new String("and");
		case 2:	return new String("or");
		case 3:	return new String("not");
		}
		return null;
	}
	
	private String toStrComparacao(){
		switch (a_comparacao) {
		case 1:	return new String("=");
		case 2:	return new String("<");
		case 3:	return new String("<=");
		case 4:	return new String(">");
		case 5:	return new String(">=");
		case 6:	return new String("<>");
		}
		return null;
	}
	
	private String toStrValor(String tipo){
		if (tipo.toUpperCase().contains( new String("VARCHAR").subSequence(0, 6) ) == true ){
			return "'"+a_valor+"'";
		}else
			if (tipo.toUpperCase().contains( new String("INT").subSequence(0, 2) ) == true ){ 
				return a_valor;
		}else
			if (tipo.toUpperCase().contains( new String("DATE").subSequence(0, 3) ) == true ){
				return "'"+a_valor+"'";
			
		}else{
			return a_valor;
		}
	}
	
}
 
