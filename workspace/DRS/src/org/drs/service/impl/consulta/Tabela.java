package org.drs.service.impl.consulta;

import java.util.HashMap;

public class Tabela {
	
	private int idObjeto;
	
	private Consulta consulta;
	
	private String a_nome;
	 
	private int idCampoMax;
	
	private HashMap< Integer, Campo > a_campo;
	 
	private int idLigacao;
	
	private HashMap< Integer, Ligacao > a_ligacao;
	 
	public Tabela(){
		a_campo = new HashMap<Integer, Campo>();
		a_ligacao = new HashMap<Integer, Ligacao>();
	}
	
	public Tabela(String nome,Ligacao[] ligacao,Consulta consulta){
		idCampoMax = 0;
		idLigacao = 0;
		a_nome = nome;
		a_campo = new HashMap<Integer, Campo>();
		a_ligacao = new HashMap<Integer, Ligacao>();
		
		for(int i=0; i < ligacao.length; i++){
			this.addLigacao(ligacao[i]);
		}
		
	}
	
	public String getNome(){
		return a_nome;
	}
	
	public void setNome(String nome){
		a_nome = nome;
	}
	
	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public void addLigacao(Ligacao ligacao){
		idLigacao++;
		a_ligacao.put( new Integer(idLigacao), ligacao);
	}
	
	public void addCampo(Campo campo, int id){
		if (id > idCampoMax){
			idCampoMax = id;
		}
		
		campo.setIdObjeto(getNomeQualificado() + id);
		a_campo.put( new Integer(id), campo);
		
		if(campo.getMostrar()){
			System.out.println("\nAdicionando campo em ordem campo\n");
			getConsulta().getOrdemCampos().add( campo , campo.getIdObjeto() );
		}
		
		//campo.setOrdenar(true);
		if(campo.getOrdenar()){
			System.out.println("\nAdicionando campo em Ordem Dados\n");
			getConsulta().getOrdemDados().add( campo , campo.getIdObjeto() );
		}
		
		
		
	}
	
	public void removeCampo(int idCampo){
		if (a_campo.containsKey(new Integer(idCampo))){
			getConsulta().getOrdemCampos().remove( a_campo.get(new Integer(idCampo)).getIdObjeto() );
			getConsulta().getOrdemDados().remove( a_campo.get(new Integer(idCampo)).getIdObjeto() );
			a_campo.remove(new Integer(idCampo));
		}
	}
	
	public Campo getCampo(int idCampo){
		return a_campo.get(new Integer(idCampo));
	}
	
	public int getIdCampoMax(){
		return idCampoMax;
	}
	
	public int getCampoSize(){
		return a_campo.size();
	}
	public boolean contemCampo(int idCampo){
		if (a_campo.containsKey(new Integer(idCampo)) ){
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isEmptyCampo(){
		return a_campo.isEmpty();
	}
	
	public void limparCampo(){
		idCampoMax = 0;
		a_campo.clear();
	}
	
	public String getNomeQualificado(){
		return  getConsulta().getNomeQualificado() + getIdObjeto();
	}
	
	public Clausulas getClausulas(Clausulas cls, String fato){
		
		for (int i = 0; i < idCampoMax + 1; i++){
			if (a_campo.containsKey(new Integer(i))){
				a_campo.get(new Integer(i)).getClausulas(cls, a_nome);
				
			}
		}
		
		return cls;
	}
	
	public String getLigacoesStr(String fato){
		
		if (idLigacao > 0){
			String Ligacoes = new String(" INNER JOIN "+ a_nome +" ON ");
			if ( a_ligacao.containsKey(new Integer(1)) ){
				Ligacoes = Ligacoes + a_ligacao.get(new Integer(1)).getClausulas(fato, a_nome);
			}
			for (int i=2; i < idLigacao; i++){
				if ( a_ligacao.containsKey(new Integer(i)) ){
					Ligacoes = Ligacoes + " and " + a_ligacao.get(new Integer(i)).getClausulas(fato, a_nome);
				}
			}
			return Ligacoes;
		}
		
		return null;
	}

	public int getIdObjeto() {
		return idObjeto;
	}

	public void setIdObjeto(int idObjeto) {
		this.idObjeto = idObjeto;
	}
}
 
