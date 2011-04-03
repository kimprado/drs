package org.cube.service.impl.modelo;

import java.io.PrintStream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;


@Entity
public class Ligacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "ligacao_sequence")
	@SequenceGenerator(allocationSize=1, initialValue=1, name="ligacao_sequence", sequenceName="ligacao_sequence")
	private Integer id;
	
	@ManyToOne
	private ChaveEstrangeira chaveEstrangeira;
	
	@OneToOne
	private Atributo atributoFato;// Atributo no fato. O comum é que o fato tenha uma chave composta por chaves estrangeiras referenciadas a dimensões.
	
	@OneToOne
	private Atributo atributoDimensao;// Atributo na Dimensão. O comum é que a dimensão tenha sua chave primária referencia por uma chave estrangeira localizada na tabela de fatos.
	
	public Ligacao(){
		
	}
			
	public Ligacao(Atributo atributoFato, Atributo atributoDimensao){
		this.atributoFato = atributoFato;
		this.atributoDimensao = atributoDimensao;
		atributoFato.setLigacao(this);
		atributoDimensao.setLigacao(this);
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setChaveEstrangeira(ChaveEstrangeira chaveEstrangeira) {
		this.chaveEstrangeira = chaveEstrangeira;
	}
	
	public ChaveEstrangeira getChaveEstrangeira() {
		return chaveEstrangeira;
	}
	
	public Atributo getAtributoFato(){
		return atributoFato;
	}
	
	public Atributo getAtributoDimensao(){
		return atributoDimensao;
	}
	
	public String imprimir(PrintStream p, String print){
		
		String printObjetoLocal = "";
		
		try{
			Atributo AtributoEstrangeiro = getAtributoFato();
			Atributo Atributoprimario = getAtributoDimensao();
			
			printObjetoLocal +=  AtributoEstrangeiro.getTabela().getNome() + "(" + AtributoEstrangeiro.getName() + ")";
			printObjetoLocal +=  " references ";
			printObjetoLocal +=  Atributoprimario.getTabela().getNome() + "(" + Atributoprimario.getName() + ")";
		} catch (Exception e){
			printObjetoLocal = "";
			e.printStackTrace();
		}
		
		//print += printObjetoLocal;
		
		return printObjetoLocal ;//+ printObjetoLocal;
	}
}