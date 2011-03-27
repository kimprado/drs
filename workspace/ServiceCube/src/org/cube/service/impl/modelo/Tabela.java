package org.cube.service.impl.modelo;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
//@Table(name="Tabela")
@Inheritance(strategy=InheritanceType.JOINED)
//@DiscriminatorColumn(name="TYPE", discriminatorType=DiscriminatorType.INTEGER)
public class Tabela {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String nome;
	
	//@OneToMany(mappedBy="tabela", fetch=FetchType.LAZY)
	//@Transient
	//private List<Atributo> atributosLista;
	
	@OneToMany(mappedBy="tabela", fetch=FetchType.LAZY)
	//@Transient
	private List<Atributo> atributos = new LinkedList<Atributo>();
	
	@Transient
	private int idCount;// = 0;
	
	@OneToOne(mappedBy="tabela", fetch=FetchType.LAZY)
	//@Transient
	private ChavePrimaria chavePrimaria;
	
	public Tabela(){
		this.nome = "";
	}
	
	public Tabela(String nome){
		//chavePrimaria = new ChavePrimaria();
		this.nome = nome;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void addAtributo(Atributo atributo){
		try{
			int i = atributo.getId() + 1;
		} catch (NullPointerException e){
			atributo.setId(idCount + 1);
		}
		
		if (atributo.getId() > idCount){
			idCount = atributo.getId();
		}
		else{
			idCount+=1;
		}
		
		getAtributos().add(/*new Integer( idCount),*/ atributo);
		//System.out.println("Adicionei atributo "+idCount+" na tabela "+  this.nome);
	}
	
	/*public void Addatributo(Atributo atributo, int idAtributo){
		if(idAtributo > idCount){
			idCount = idAtributo;
		}
		atributos.add(new Integer( idAtributo), atributo);
		//System.out.println("Adicionei atributo "+idAtributo+" na tabela "+  this.nome);
	}*/
	
	public Atributo getAtributo(int i){
		for (Atributo atributo : getAtributos()) {
			if (atributo.getId() == i){
				return atributo;
			}
		}
		
		return null;//getAtributos().get( new Integer(i) );
	}
	
	public Atributo getAtributo(String name){
		for (Atributo atributo : getAtributos()) {
			if (atributo.getName().equals(name)){
				return atributo;
			}
		}
		
		return null;//getAtributos().get( new Integer(i) );
	}
	
	public int GetQuantidadeAtributo(){
		return getAtributos().size();
	}
	
	public int getIdMaxAtributo(){
		return idCount;
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
		//p.println(this.Getnome());
		print = print+this.getNome()+"\n";
		//p.println("  Atributos:");
		print = print+"  Atributos:\n";
		//for (int i=0;i < this.GetQuantidadeAtributo();i++){
		for (int i=0;i < 5000;i++){
			Atributo at = this.getAtributo(i);
			try{
				print = at.imprimir(p,print);
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

	/*public void setAtributosLista(List<Atributo> atributosLista) {
		this.atributosLista = atributosLista;
	}

	public List<Atributo> getAtributosLista() {
		return atributosLista;
	}*/
}
