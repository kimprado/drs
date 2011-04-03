package org.cube.service.impl.modelo;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Tabela {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "tabela_sequence")
	@SequenceGenerator(allocationSize=1, initialValue=1, name="tabela_sequence", sequenceName="tabela_sequence")
	private Integer id;
	
	private String nome;
	
	@OneToMany(mappedBy="tabela", fetch=FetchType.LAZY)
	private List<Atributo> atributos = new LinkedList<Atributo>();
	
	@Transient
	private int idCount;
	
	@OneToOne(fetch=FetchType.LAZY)
	private ChavePrimaria chavePrimaria;
	
	public Tabela(){
		this.nome = "";
	}
	
	public Tabela(String nome){
		this.nome = nome;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void addAtributo(Atributo atributo){
		idCount++;
		getAtributos().add(atributo);
	}
	
	public Atributo getAtributo(int i){
		for (Atributo atributo : getAtributos()) {
			if (atributo.getId() == i){
				return atributo;
			}
		}
		
		return null;
	}
	
	public Atributo getAtributo(String name){
		for (Atributo atributo : getAtributos()) {
			if (atributo.getName().equals(name)){
				return atributo;
			}
		}
		
		return null;
	}
	
	public int GetQuantidadeAtributo(){
		return getAtributos().size();
	}
	
	public int getIdMaxAtributo(){
		//TODO Esse método deve deixar de ser usado
		return 5000;//idCount;
	}
	
	public boolean contemAtributo(int idAtributo){
		return getAtributo(Integer.valueOf(idAtributo)) != null ;
	}
	
	public boolean naoPossuiAtributo(){
		return getAtributos().isEmpty();
	} 
	
	public ChavePrimaria getChavePrimaria(){
		if ( chavePrimaria == null){
			chavePrimaria = new ChavePrimaria();
			chavePrimaria.setTabela(this);
		}
		return chavePrimaria;
	}
	
	public void setChavePrimaria(ChavePrimaria ChavePrimaria){
		chavePrimaria = ChavePrimaria;
	}
	
	public void Setnome(String nome){
		
		this.nome = nome;	
	}
	
	public String getNome(){
		return this.nome;		
	}

	public String imprimir(PrintStream p, String print){
		print = print+this.getNome()+"\n";
		print = print+"  Atributos:\n";
		
		for (Atributo atributo : getAtributos()) {
			try{
				print = atributo.imprimir(p,print);
			} catch (NullPointerException e){
				
			}
		}
		
		print += "ChavePrimaria: ";
		
		print = getChavePrimaria().imprimir(p, print);
		
		return print;
	}

	public void setAtributos(List<Atributo> atributos) {
		this.atributos = atributos;
	}

	public List<Atributo> getAtributos() {
		return atributos;
	}
}
