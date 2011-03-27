package org.cube.service.impl.modelo;

import java.io.PrintStream;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;


@Entity
public class Dimensao extends Tabela {
	
	@OneToOne(mappedBy="dimensao", fetch=FetchType.LAZY)
	private ChaveEstrangeira chaveEstrangeira;
	
	public Dimensao(String nome){
		
		super(nome);	
	}
	
	public ChaveEstrangeira getChaveEstrangeira(){
		return this.chaveEstrangeira;
	}
	
	public void setChaveEstrangeira(ChaveEstrangeira chaveEstrangeira ){
		this.chaveEstrangeira = chaveEstrangeira;
	}
	
	public String imprimir(PrintStream p, String print){
		
		print = super.imprimir(p,print);
		
		print += "\nChaveEstrangeira: ";
		
		print += getChaveEstrangeira().imprimir(p, print);
		
		print += "\n";
		
		return print;
	}

}
