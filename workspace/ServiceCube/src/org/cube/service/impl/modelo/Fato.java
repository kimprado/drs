package org.cube.service.impl.modelo;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
//@Table(name="Fato")
//@PrimaryKeyJoinColumn(name="id")
//@DiscriminatorValue("1")
public class Fato extends Tabela {
	
	@OneToMany(mappedBy="fato")
	private List<ChaveEstrangeira> chaveEstrangeira = new LinkedList<ChaveEstrangeira>();
	
	@OneToOne(fetch=FetchType.LAZY)
	private Cubo cubo;
	
	public Fato(){
		super();
	}
	
	public Fato(String nome){
		super(nome);
	}
	
	public void setCubo(Cubo cubo) {
		this.cubo = cubo;
	}


	public Cubo getCubo() {
		return cubo;
	}

	public void addDimensao(Dimensao dimensao){
		ChaveEstrangeira chaveEstrangeira = new ChaveEstrangeira(dimensao);
		chaveEstrangeira.setFato(this);
		getChaveEstrangeira().add(chaveEstrangeira);
	}
	
	public Dimensao getdimensao(int idDimensao){
		for (ChaveEstrangeira chaveEstrangeira : getChaveEstrangeira()) {
			Dimensao dimensao = chaveEstrangeira.getDimensao();
			if(dimensao.getId() == idDimensao){
				return dimensao;
			}
		}
		return null;
	}
	
	
	public int getQuantidadeDimensao(){
		return getChaveEstrangeira().size();
	}
	
	public int getIdMaxDimensao(){
		//TODO Esse método deve deixar de ser usado
		return 5000;//idCount;
	}
	
	public boolean contemDimensao(int idDimensao){
		/*if (getChaveEstrangeira().containsKey(new Integer(i)) == true){
			return true;
		}else {
			return false;
		}*/
		
		return getdimensao(Integer.valueOf(idDimensao)) != null ;
	}
	
	/*public boolean isEmptys(){
		return a_ChaveEstrangeira.isEmpty();
	}*/
	
	public void addLigacao(int idDimensao, Ligacao ligacao){
		//getChaveEstrangeira().get( new Integer(idDimensao) ).addLigacao(ligacao);
		getChaveEstrangeiraPelaDimensao(idDimensao).addLigacao(ligacao);
	}
	
	public ChaveEstrangeira getChaveEstrangeiraPelaDimensao(int idDimensao){
		for (ChaveEstrangeira chaveEstrangeira : getChaveEstrangeira()) {
			Dimensao dimensao = chaveEstrangeira.getDimensao();
			if(dimensao.getId() == idDimensao){
				return chaveEstrangeira;
			}
		}
		//Dimensao dimensao = getChaveEstrangeira().get( new Integer(i) ).getDimensao();
		return null;//dimensao;
	}
	
	public String imprimir(PrintStream p, String print){
		print = print + "\n" + "FATO: "; 
		
		print = super.imprimir(p,print);
		
		print += "\nChaveEstrangeira: \n";
		for (ChaveEstrangeira chaveEstrangeira : getChaveEstrangeira()) {
			print += chaveEstrangeira.imprimir(p, print) + "\n";
		}
		
		print = print+"\n\n"+"  DIMENSAO:"+"\n\n";
		for (ChaveEstrangeira chaveEstrangeira : getChaveEstrangeira()) {
			try{
				Dimensao dimensao = chaveEstrangeira.getDimensao();
				print = dimensao.imprimir(p,print) + "\n";
			} catch (NullPointerException e){
				
			}
		}
		return print;
	}

	public void setChaveEstrangeira(List<ChaveEstrangeira> chaveEstrangeira) {
		this.chaveEstrangeira = chaveEstrangeira;
	}

	public List<ChaveEstrangeira> getChaveEstrangeira() {
		return chaveEstrangeira;
	}
}

