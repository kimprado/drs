package org.cube.service.impl.modelo;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class ChavePrimaria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "chavePrimaria_sequence")
	@SequenceGenerator(allocationSize=1, initialValue=1, name="chavePrimaria_sequence", sequenceName="chavePrimaria_sequence")
	private Integer id;
	
	private String nome = "";
	
	@OneToOne
	private Tabela tabela;
	
	//@Transient
	//int idAtributo;
	
	@OneToMany(mappedBy="chavePrimaria", fetch=FetchType.LAZY)
	//@Transient
	private List<Atributo> atributos = new LinkedList<Atributo>();
	
	public ChavePrimaria(){
		
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
		//idAtributo++;
		atributo.setChavePrimaria(this);
		getAtributos().add(atributo);
	}
	
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
