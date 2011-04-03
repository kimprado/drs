package org.cube.service.impl.modelo;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class ChaveEstrangeira {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "chaveEstrangeira_sequence")
	@SequenceGenerator(allocationSize=1, initialValue=1, name="chaveEstrangeira_sequence", sequenceName="chaveEstrangeira_sequence")
	private Integer id;
	
	private String nome = "";
	
	@ManyToOne
	private Fato fato;
	
	@OneToMany(mappedBy="chaveEstrangeira")
	private List<Ligacao> ligacoes = new LinkedList<Ligacao>();
	
	@OneToOne
	private Dimensao dimensao;
	
	public ChaveEstrangeira(){
		
	}
	
	public ChaveEstrangeira(Dimensao dimensao){
		this.dimensao = dimensao;
		dimensao.setChaveEstrangeira(this);
	}

	public ChaveEstrangeira(Fato fato, Dimensao dimensao) {
		this.fato = fato;
		this.dimensao = dimensao;
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

	public void setFato(Fato fato) {
		this.fato = fato;
	}
	
	public Fato getFato() {
		return fato;
	}
	
	public void addLigacao(Atributo estrangeiro, Atributo primario){
		Ligacao ligacao = new Ligacao(estrangeiro, primario);
		estrangeiro.setLigacao(ligacao);
		primario.setLigacao(ligacao);
		this.getLigacoes().add(ligacao);
	}
	
	public void addLigacao(Ligacao ligacao){
		this.getLigacoes().add(ligacao);
	}
	
	public Dimensao getDimensao(){
		return this.dimensao;
	}

	public void setLigacoes(List<Ligacao> ligacoes) {
		this.ligacoes = ligacoes;
	}

	public List<Ligacao> getLigacoes() {
		return ligacoes;
	}
	
	public String imprimir(PrintStream p, String print){
		print = getNome() + "";
		
		for (Ligacao ligacao : getLigacoes()) {
			print += "\n\t" + ligacao.imprimir(p, print);
		}
		
		return print;
	}
	
}