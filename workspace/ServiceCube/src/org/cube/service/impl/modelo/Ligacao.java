package org.cube.service.impl.modelo;

import java.io.PrintStream;

import javax.persistence.CascadeType;
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
public class Ligacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "ligacao_sequence")
	@SequenceGenerator(allocationSize=1, initialValue=1, name="ligacao_sequence", sequenceName="ligacao_sequence")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private ChaveEstrangeira chaveEstrangeira;
	
	@OneToOne(fetch=FetchType.LAZY, orphanRemoval=true, cascade={CascadeType.REMOVE})
	private Atributo atributoFato;// Atributo no fato. O comum � que o fato tenha uma chave composta por chaves estrangeiras referenciadas a dimens�es.
	
	@OneToOne(fetch=FetchType.LAZY, orphanRemoval=true, cascade={CascadeType.REMOVE})
	private Atributo atributoDimensao;// Atributo na Dimens�o. O comum � que a dimens�o tenha sua chave prim�ria referencia por uma chave estrangeira localizada na tabela de fatos.
	
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
		
		return printObjetoLocal ;
	}
}