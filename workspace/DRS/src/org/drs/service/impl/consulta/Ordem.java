package org.drs.service.impl.consulta;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

@SuppressWarnings("hiding")
public class Ordem<String, Object>  extends HashMap<String, Object>{

	private static final long serialVersionUID = 1L;
	
	Consulta consulta;
	
	Comparator<Object> comparador;

	public Ordem(Consulta consulta) {
		this.consulta = consulta;
		comparador = getComparadorNovo();
	}
	
	
	public Comparator<Object> getComparador() {
		return comparador;
	}

	public void setComparador(Comparator<Object> comparador) {
		this.comparador = comparador;
	}
	
	public void add(Campo campo ,String key ) {
		
		/*
		 * Caso o Objeto não seja parte da lista de ordenação
		 */
		if( get(key) == null ){
			
			System.out.println("Objeto sendo pincluído pois ainda não faz parte da lista de ordenação de nomes");
			//Campo campo =  campos;
			ordenarPrimeiraPosicao();
			
			//System.out.println("Posição pretendida: " + cp.getMostrarPosicao() + " - id:" + cp.getNome());
			if (campo.getMostrarPosicao() < 1 ){
				campo.setMostrarPosicao( (size() + 1) );
			}
			liberarPosicao( campo.getMostrarPosicao() );
			
			//System.out.println("\n\nCampo adicionado na ordem de seleção: " + cp.getMostrarPosicao() + "\nkey: " + key + "\n");
			super.put(key, (Object)campo);
		}
		else{
			System.out.println("Objeto ja faz parte da lista e não foi novamente incluído");
		}
	}
	
	public Object[] getCamposOrdenadosToArray() {
		/*
		TreeSet<Campo> campos = new TreeSet<Campo>(comparador);
		System.out.println("\n\nvalores: " + values().size());
		campos.addAll((Collection<Campo>)values());
		
		System.out.println("\nTamanho do TreeSet: " + campos.size());
		
		System.out.println("\n\nArrays: " +campos.toArray().length);
		return campos.toArray();
		*/
		
		return (Object[])getCamposOrdenados().toArray();
	}
	 
	public TreeSet<Object> getCamposOrdenados(){
		comparador = getComparadorNovo();
		//System.out.println("Meu novo Comparador: " + comparador);
		TreeSet<Object> campos = new TreeSet<Object>(comparador);
		//System.out.println("\n\nvalores: " + values().size());
		
		for(Iterator i = this.values().iterator(); i.hasNext();){
			//Campo ob = i.next();
			campos.add((Object) i.next());
			//System.out.println("Adicionado (" + ((org.drs.service.impl.consulta.Campo)campos.last()).getNome() + ")na ordem "+ ((org.drs.service.impl.consulta.Campo)campos.last()).getMostrarPosicao());
		}
		
		//System.out.println("\nTamanho do TreeSet: " + campos.size() +"\n");
		
		
		
		return campos;
	}
	
	public void ordenarPrimeiraPosicao(){
		//System.out.println("entrei errado");
	}
	
	public void liberarPosicao(int posicao){
		/*
		posicao--;
		for(Iterator i = this.values().iterator(); i.hasNext();){
			org.drs.service.impl.consulta.Campo campo = ((org.drs.service.impl.consulta.Campo)i.next());
			
			if(campo.getMostrarPosicao() > posicao){
				campo.setMostrarPosicao( campo.getMostrarPosicao() + 1);
			}
		}
		*/
	}
	
	public Comparator<Object> getComparadorNovo(){
		return null;
	}
	
	
}
