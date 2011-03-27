package org.cube.service.impl.modelo;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ChavePrimaria {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String nome = "";
	
	@OneToOne
	private Tabela tabela;
	
	int idAtributo;
	
	@OneToMany(mappedBy="chavePrimaria", fetch=FetchType.LAZY)
	//@Transient
	private List<Atributo> atributos = new LinkedList<Atributo>();
	
	public ChavePrimaria(){
		//atributos = new HashMap<Integer, Atributo>();
		//idAtributo = 0;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void addAtributo(Atributo atributo){
		idAtributo++;
		atributo.setChavePrimaria(this);
		getAtributos().add(atributo);
	}
	
	/*public Atributo[] getAtributos(){
		Atributo[] atributos = new Atributo[a_chaveP.size()];
		int count = 0;
		for (int i=1; i < idAtributo + 1; i++){
			if (a_chaveP.containsKey(new Integer(i))){
				atributos[count] = a_chaveP.get(new Integer(i));
				count++;
			}
		}
		
		return atributos;
	}*/

	public void setTabela(Tabela tabela) {
		this.tabela = tabela;
	}

	public Tabela getTabela() {
		return tabela;
	}
	
	public void setAtributos(List<Atributo> atributos) {
		this.atributos = atributos;
	}

	public List<Atributo> getAtributos() {
		return atributos;
	}

	public String imprimir(PrintStream p, String print){
		
		print += getNome() + "\n\t";
		
		for (Atributo atributo : getAtributos()) {
			print += "" + atributo.getName() + "; ";
		}
		
		
		return print;
	}
	

}
