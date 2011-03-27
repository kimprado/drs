package org.cube.service.impl.modelo;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ChaveEstrangeira {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String nome = "";
	
	@ManyToOne
	private Fato fato;
	
	/*@Transient
	int idLigacao;*/
	
	@OneToMany(mappedBy="chaveEstrangeira")
	private List<Ligacao> ligacoes = new LinkedList<Ligacao>();
	
	@OneToOne
	private Dimensao dimensao;
	
	public ChaveEstrangeira(Dimensao dimensao){
		//idLigacao = 0;
		//setLigacoes(new HashMap<Integer, Ligacao>());
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
		//idLigacao++;
		this.getLigacoes().add(ligacao);
	}
	
	public void addLigacao(Ligacao ligacao){
		//idLigacao++;
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