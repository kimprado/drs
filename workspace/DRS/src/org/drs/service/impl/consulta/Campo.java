package org.drs.service.impl.consulta;

import java.util.HashMap;


public class Campo {
 
	private boolean a_agrupar;
	 
	private boolean a_mostrar;
	
	private int mostrarPosicao;
	
	private boolean ordenar;
	
	private int ordenarPosicao;
	
	private int ordenarDirecao;
	
	private String a_nome;
	
	private String a_tipo;
	
	private String a_idObjeto;
	
	private Tabela tabela;
	
	 
	private int idCondicao = 0;
	
	private HashMap< Integer, Condicao> a_condicao;
	
	private int idAgregacaoSelect = 0;
	private HashMap< Integer, AgregacaoSelect> a_agregacaoSelect;
	
	private int idAgregacaoHaving = 0;
	private HashMap< Integer, AgregacaoHaving> a_agregacaoHaving;
	
	public Campo(Tabela tabela){
		this.tabela = tabela;
		
		idCondicao = 0;
		a_condicao = new HashMap< Integer, Condicao>();
		
		idAgregacaoSelect = 0;
		a_agregacaoSelect = new HashMap< Integer, AgregacaoSelect>() ;
		
		idAgregacaoHaving = 0;
		a_agregacaoHaving = new HashMap< Integer, AgregacaoHaving>() ;
	}
	
	public Campo(String nome, Tabela tabela){
		this.tabela = tabela;
		a_nome = nome;
		idCondicao = 0;
		a_condicao = new HashMap< Integer, Condicao>();
		
		idAgregacaoSelect = 0;
		a_agregacaoSelect = new HashMap< Integer, AgregacaoSelect>() ;

		idAgregacaoHaving = 0;
		a_agregacaoHaving = new HashMap< Integer, AgregacaoHaving>() ;
	}
	
	
	public boolean getAgrupar(){
		return a_agrupar;
	}
	
	public void setAgrupar(boolean agrupar){
		a_agrupar = agrupar;
	}
	
	
	public boolean getMostrar(){		
		return a_mostrar;
	}

	public void setMostrar(boolean mostrar){
		a_mostrar = mostrar;
	}
	
	public int getMostrarPosicao() {
		return mostrarPosicao;
	}

	public void setMostrarPosicao(int mostrarPosicao) {
		this.mostrarPosicao = mostrarPosicao;
	}

	public boolean getOrdenar() {
		return ordenar;
	}

	public void setOrdenar(boolean ordenar) {
		this.ordenar = ordenar;
	}

	public int getOrdenarPosicao() {
		return ordenarPosicao;
	}

	public void setOrdenarPosicao(int ordenarPosicao) {
		this.ordenarPosicao = ordenarPosicao;
	}

	public int getOrdenarDirecao() {
		return ordenarDirecao;
	}
	
	public String getOrdenarDirecaoFormatado(){
		switch (ordenarDirecao) {
			case 1:	return new String("ASC");
			case 2:	return new String("DESC");
		}
		return "";
	}

	public void setOrdenarDirecao(int ordenarDirecao) {
		this.ordenarDirecao = ordenarDirecao;
	}

	public String getNome(){
		return a_nome;
	}
	
	public void setNome(String nome){
		a_nome = nome;
	}
	
	public String getTipo(){
		return a_tipo;
	}
	
	public void setTipo(String tipo){
		a_tipo = tipo;
	}

	public String getIdObjeto() {
		return a_idObjeto;
	}

	public void setIdObjeto(String objeto) {
		a_idObjeto = objeto;
	}
	
	public Tabela getTabela() {
		return tabela;
	}

	public void setTabela(Tabela tabela) {
		this.tabela = tabela;
	}

	public AgregacaoSelect getAgregacaoSelect(int i){ 
		return a_agregacaoSelect.get( new Integer(i) );
	}
	
	public Object[] getAgregacaoSelect(){ 
		return a_agregacaoSelect.values().toArray();
	}
	
	public void AddAgregacaoSelect(AgregacaoSelect agregacaoSelect){ 
		idAgregacaoSelect++;
		a_agregacaoSelect.put(new Integer(idAgregacaoSelect), agregacaoSelect);
		
	}
	
	public void limparAgregacaoSelect(){
		idAgregacaoSelect = 0;
		a_agregacaoSelect.clear();
	}
	
	public int sizeAgregacaoSelect(){
		return a_agregacaoSelect.size();
	}
	
	public boolean contemAgregacaoSelect(int idAgSelect){
		return a_agregacaoSelect.containsKey(new Integer(idAgSelect));
	}
		
	public AgregacaoHaving getAgregacaoHaving(int i){ 
		return a_agregacaoHaving.get( new Integer(i) );
	}
	
	public Object[] getAgregacaoHaving(){ 
		return a_agregacaoHaving.values().toArray();
	}
	
	public void AddAgregacaoHaving(AgregacaoHaving agregacaoHaving){ 
		idAgregacaoHaving++;
		a_agregacaoHaving.put(new Integer(idAgregacaoHaving), agregacaoHaving);
		
	}
	
	public void limparAgregacaoHaving(){
		idAgregacaoHaving = 0;
		a_agregacaoHaving.clear();
	}
	
	public int sizeAgregacaoHaving(){
		return a_agregacaoHaving.size();
	}
	
	public boolean contemAgregacaoHaving(int idAgHaving){
		return a_agregacaoHaving.containsKey(new Integer(idAgHaving));
	}	
	
	public Condicao getCondicao(int i){ 
		return a_condicao.get( new Integer(i) );
	}
	
	public Object[] getCondicao(){ 
		return a_condicao.values().toArray();
	}
	
	public void AddCondicao(Condicao condicao){ 
		idCondicao++;
		a_condicao.put(new Integer(idCondicao), condicao);
	}
	
	public void limparCondicao(){
		idCondicao = 0;
		a_condicao.clear();
	}
	
	public int sizeCondicao(){
		return a_condicao.size();
	}
	
	public boolean contemCondicao(int idcondicao){
		return a_condicao.containsKey(new Integer(idcondicao));
	}
	
	public Clausulas getClausulas(Clausulas cls, String tbNome){
		
		if (cls.getSelect() == null){
			if (getMostrar() == true){
				cls.setSelect(tbNome + "." + getNome());
			}
		}else{
			if (getMostrar() == true){
				cls.setSelect(cls.getSelect() + ", " + tbNome + "." + getNome());
			}
		}
		
		if (cls.getGroupBy() == null){
			if (getAgrupar() == true){
				cls.setGroupBy(tbNome + "." + getNome());
			}
		}else{
			if (getAgrupar() == true){
				cls.setGroupBy(cls.getGroupBy() + ", " + tbNome + "." + getNome());
			}
		}

		
		for (int i = 0; i < idAgregacaoSelect + 1; i++){
			if (a_agregacaoSelect.containsKey(new Integer(i))){
				a_agregacaoSelect.get(new Integer(i)).getClausulas(cls,tbNome , getNome(), getTipo());
			}
		}
		

		for (int i = 0; i < idCondicao + 1; i++){
			if (a_condicao.containsKey(new Integer(i))){
				a_condicao.get(new Integer(i)).getClausulas(cls, new String(tbNome + "." + getNome()), getTipo());
			}
		}

		for (int i = 0; i < idAgregacaoHaving + 1; i++){
			if (a_agregacaoHaving.containsKey(new Integer(i))){
				a_agregacaoHaving.get(new Integer(i)).getClausulas(cls,tbNome , getNome(), getTipo());
			}
		}
		
		return cls;
	}
	
	public Clausulas getClausulaAgregacaoSelect(Clausulas cls){
		for (int i = 0; i < idAgregacaoSelect + 1; i++){
			if (a_agregacaoSelect.containsKey(new Integer(i))){
				if(i > 1){
					cls.setSelect( cls.getSelect() + ", " );
				}
				a_agregacaoSelect.get(new Integer(i)).getClausulas(cls,tabela.getNome() , getNome(), getTipo());
			}
		}
		return cls;
	}

}
 
