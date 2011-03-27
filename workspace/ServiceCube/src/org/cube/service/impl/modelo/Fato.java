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
	@Transient
	private int idCount = 0; // registra os IDs das dimensões alocadas para o fato
	
	@OneToOne(fetch=FetchType.LAZY)
	private Cubo cubo;
	
	public Fato(){
		super();
	}
	
	public Fato(String nome){
		
		super(nome);
		
		//setChaveEstrangeira(new HashMap< Integer, ChaveEstrangeira>());
	}
	
	public void setCubo(Cubo cubo) {
		this.cubo = cubo;
	}


	public Cubo getCubo() {
		return cubo;
	}

	public void addDimensao(Dimensao dimensao){
		
		try{
			int i = dimensao.getId() + 1;
		} catch (NullPointerException e){
			dimensao.setId(idCount + 1);
		}
		
		if (dimensao.getId() > idCount){
			idCount = dimensao.getId();
		}
		else{
			idCount+=1;
		}
		
		getChaveEstrangeira().add(new ChaveEstrangeira(dimensao));
	}
	
	/*public void addDimensao(Dimensao dimensao, int iddimensao){
		if (iddimensao > idCount){
			idCount = iddimensao;
		}
		
		//getChaveEstrangeira().put(new Integer(iddimensao), new ChaveEstrangeira(dimensao));
	}*/
	
	public Dimensao getdimensao(int idDimensao){
		for (ChaveEstrangeira chaveEstrangeira : getChaveEstrangeira()) {
			Dimensao dimensao = chaveEstrangeira.getDimensao();
			if(dimensao.getId() == idDimensao){
				return dimensao;
			}
		}
		//Dimensao dimensao = getChaveEstrangeira().get( new Integer(i) ).getDimensao();
		return null;//dimensao;
	}
	
	
	public int getQuantidadeDimensao(){
		return getChaveEstrangeira().size();
	}
	
	public int getIdMaxDimensao(){
		return idCount;
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
		//for (int i=0;i < this.getQuantidadeDimensao();i++){
		for (int i=0;i < 5000;i++){
			Dimensao dm = this.getdimensao(i);
			try{
				print = (dm.imprimir(p,print)+"\n");
			} catch (NullPointerException e){
				
			}
			//p.println();
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

