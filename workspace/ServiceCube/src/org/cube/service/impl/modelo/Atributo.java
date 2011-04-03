package org.cube.service.impl.modelo;

import java.io.PrintStream;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
public class Atributo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "atributo_sequence")
	@SequenceGenerator(allocationSize=1, initialValue=1, name="atributo_sequence", sequenceName="atributo_sequence")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Tabela tabela;

	private String nome;
	private String tipo;
	private String tamanho;
	private String decimal;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Ligacao ligacao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private ChavePrimaria chavePrimaria; 
	
	public Atributo(){
		
	}
	
	public Atributo(String nome, String tipo, String tamanho, String decimal){
		this.nome = nome;
		this.tipo = tipo;
		this.tamanho = tamanho;
		this.decimal = decimal;
		this.ligacao = null;
		this.chavePrimaria = null;
		
	}
	
	public Atributo(String nome, String tipo, String tamanho, String decimal, Tabela tabela){
		this.nome = nome;
		this.tipo = tipo;
		this.tamanho = tamanho;
		this.decimal = decimal;
		this.ligacao = null;
		this.chavePrimaria = null;
		this.tabela = tabela;
		
	}
	
	public Atributo(String nome, String tipo){
		this.nome = nome;
		this.tipo = tipo;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getName(){
		return this.nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	

	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
	
	
	public String getTamanho(){
		return this.tamanho;
	}
	
	public void setTamanho(String tamanho){
		this.tamanho = tamanho;
	}
	
	
	public String getDecimal(){
		return this.decimal;
	}
	
	public void setDecimal(String decimal){
		this.decimal = decimal;
	}
	
	public Ligacao getLigacao(){
		return this.ligacao;
	}
	
	public void setLigacao(Ligacao ligado){
		this.ligacao = ligado;
	}
	
	public boolean isChaveEstrangeira(){
		if (getLigacao() != null){
			return true;
		}
		return false;
	}
	
	public ChavePrimaria getChavePrimaria(){
		return this.chavePrimaria;
	}
	
	public void setChavePrimaria(ChavePrimaria chavePrimaria){
		this.chavePrimaria = chavePrimaria;
	}
	
	public String imprimir(PrintStream p, String print){
		print = print+(getName() + " (" + getTipo() + " - " + getTamanho() + ")\n");
		return print;
	}

	public void setTabela(Tabela tabela) {
		this.tabela = tabela;
	}

	public Tabela getTabela() {
		return tabela;
	}
}
