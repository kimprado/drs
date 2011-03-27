package org.drs.service.impl.consulta;

public class Ligacao {
 
	private String a_campoFato;
	 
	private String a_campoDimensao;
	
	
	public Ligacao(String campoFato, String campoDimensao ){
		a_campoFato = campoFato;
		a_campoDimensao = campoDimensao;
	}
	
	public String getCampoFato(){
		return a_campoFato;
	}
	public String getCampoDimensao(){
		return a_campoDimensao;
	}
	
	public String getClausulas(String fato, String dimensao){
		return new String(" " + fato + "." + a_campoFato + " = " + dimensao + "." + a_campoDimensao);
	}
}
 
