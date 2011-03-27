package org.drs.service.impl.consulta;

import java.util.HashMap;
import java.util.TreeSet;

public class Consulta {
	
	private int idObjeto;
	
	private int a_cubeIndexEntry;
	
	private String a_uriCubo;
	
	private int a_idCubo;
	
	private String a_fatoNome;
	
	private String a_cubeNome;
	
	private HashMap<Integer, Tabela> a_tabela;
	
	private int idTabelaMax;
	
	private OrdemCampos<String, Object> ordemCampos;// = new OrdemCampos<String, Campo>(this);
	
	private OrdemDados<String, Campo> ordemDados;// = new OrdemCampos<String, Campo>(this);
	
	public Consulta(){
		idTabelaMax = -2;
		a_tabela = new HashMap<Integer, Tabela>();
		ordemCampos = new OrdemCampos<String, Object>(this);
		ordemDados = new OrdemDados<String, Campo>(this);
	}
	
	public Consulta(int cubeEntry,int idCubo, String cubeNome, String fatoNome){
		this();
		a_cubeIndexEntry = cubeEntry;
		a_idCubo = idCubo;
		a_cubeNome = cubeNome;
		a_fatoNome = fatoNome;
		//idTabelaMax = -2;
		//a_tabela = new HashMap<Integer, Tabela>();
		
	}
	
	public void addTabela(Tabela tabela , int Idtabela){
		if (idTabelaMax < Idtabela){
			idTabelaMax = Idtabela;
		}
		a_tabela.put(new Integer(Idtabela), tabela);
		//System.out.println("Em addTabela QTD de Tabelas: "+a_tabela.size());
	}
	
	public void removeTabela(int Idtabela){
		if ( a_tabela.containsKey(new Integer(Idtabela)) ){
			a_tabela.remove(new Integer(Idtabela));
		}
	}
	
	public int getCubeIndexEntry(){
		return a_cubeIndexEntry;
	}
	
	public String getUri_cubo(){
		return a_uriCubo;
	}
	public int getIdCubo(){
		return a_idCubo;
	}
	
	public String getCubeNome(){
		return a_cubeNome;
	}
	
	public String getFatoNome(){
		return a_fatoNome;
	}
	
	public int getIdObjeto() {
		return idObjeto;
	}

	public void setIdObjeto(int idObjeto) {
		this.idObjeto = idObjeto;
	}
	
	public Tabela getTabela(int idTabela){
		return a_tabela.get(new Integer(idTabela));		
	}
	
	public OrdemCampos<String, Object> getOrdemCampos() {
		return ordemCampos;
	}

	public void setOrdemCampos(OrdemCampos<String, Object> ordemCampos) {
		this.ordemCampos = ordemCampos;
	}

	public OrdemDados<String, Campo> getOrdemDados() {
		return ordemDados;
	}

	public void setOrdemDados(OrdemDados<String, Campo> ordemDados) {
		this.ordemDados = ordemDados;
	}

	public int getTabelaSize(){
		return a_tabela.size();
	}
	
	public boolean isEmptyTabela(){
		return a_tabela.isEmpty();
	}
	

	public int getIdTabelaMax(){
		return idTabelaMax;
	}
	
	public boolean contemTabela(int idTabela){
		return a_tabela.containsKey(new Integer(idTabela));
	}
	
	public String getNomeQualificado(){
		return a_cubeIndexEntry + a_idCubo + idObjeto + "";
	}
	
	public String getSQL(Clausulas cls){
		//System.out.println("\nESTOU EM getClausulas de Consulta\n");
		if (isEmptyTabela() == false){
			//System.out.println("QTD de TABELAS: "+ a_tabela.size());
			
			if (a_tabela.size() == (1) ){ //&& a_tabela.containsKey(new Integer(-1)) == true){
				a_tabela.get(new Integer(idTabelaMax)).getClausulas(cls,getFatoNome());
				cls.setFrom(a_tabela.get(new Integer(idTabelaMax)).getNome());
			
			} else if (a_tabela.size() > (1) ){
				boolean primeiraTabela = false; // indica Se a primeira tabela ja foi processada
				for (int i = 1; i < idTabelaMax + 1; i++){
					if (contemTabela(i) == true){
						if (primeiraTabela == false){
							if (a_tabela.get(new Integer(i)).isEmptyCampo() == false){
								a_tabela.get(new Integer(i)).getClausulas(cls,getFatoNome());
								cls.setFrom(getFatoNome() + " " + a_tabela.get(new Integer(i)).getLigacoesStr(getFatoNome()));
								primeiraTabela = true;
							}
						}else {
							if (a_tabela.get(new Integer(i)).isEmptyCampo() == false){
								a_tabela.get(new Integer(i)).getClausulas(cls,getFatoNome());
								cls.setFrom( cls.getFrom() + a_tabela.get(new Integer(i)).getLigacoesStr(getFatoNome()));
							}
						}
					}
				}
				
			if ( (contemTabela(-1) == true) && (a_tabela.get(new Integer(-1)).isEmptyCampo() == false) ){ // se houver a tabela "-1" e ela possuir algum atributo de parâmetro
					a_tabela.get(new Integer(-1)).getClausulas(cls,getFatoNome());
					if (cls.getFrom() == null){
						cls.setFrom(a_tabela.get(new Integer(-1)).getNome());
					}
				}
			}
			
		}
		
		
		//cls.setSelect(select);
		
		//String select = "";
		
		cls.setSelect("");
		
		//*
		Object[] camposOrdem = getOrdemCampos().getCamposOrdenadosToArray();
		for(int i = 0; i < camposOrdem.length ; i++ ){
			Campo campo = (Campo)camposOrdem[i];
			
			if(campo.getMostrar()){
				if ( !("").equals(cls.getSelect() ) ){
					cls.setSelect( cls.getSelect() + ", ");
				}
				cls.setSelect( cls.getSelect() + campo.getTabela().getNome() + "." + campo.getNome() );
			}
			if(campo.sizeAgregacaoSelect() > 0){
				if ( !("").equals(cls.getSelect() ) ){
					cls.setSelect( cls.getSelect() + ", ");
				}
				//System.out.println("\n\nAgregação Select foi detectada\n");
				campo.getClausulaAgregacaoSelect(cls);
			}
			
			System.out.println("\n\nselect: " + cls.getSelect());
			
		}//*/
		
		
		cls.setOrderBy("");
		//*
		Object[] camposOrdemDados = getOrdemDados().getCamposOrdenadosToArray();
		for(int i = 0; i < camposOrdemDados.length ; i++ ){
			Campo campo = (Campo)camposOrdemDados[i];
			
			if(campo.getOrdenar()){
				if ( !("").equals(cls.getOrderBy() ) ){
					cls.setOrderBy( cls.getOrderBy() + ", ");
				}
				cls.setOrderBy( cls.getOrderBy() + campo.getTabela().getNome() + "." + campo.getNome() + " " + campo.getOrdenarDirecaoFormatado() );
			}
			System.out.println("\n\nOrdenação: " + cls.getOrderBy());
			
		}//*/
		
		
		/*
		TreeSet<Campo> camposOrdem = getOrdemCampos().getCamposOrdenados();
		for(int i = 0; i < camposOrdem.size() ; i++ ){
			if ( !("").equals(select) ){
				select += ", ";
			}
			//select += ((Campo)camposOrdem.[i]).getTabela().getNome() + "." + ((Campo)camposOrdem[i]).getNome() ;
			camposOrdem.
		}//*/
		
		//cls.setSelect(select);
		return cls.getSQL();
	}
}
 
