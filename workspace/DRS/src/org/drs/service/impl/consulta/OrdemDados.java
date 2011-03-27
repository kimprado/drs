package org.drs.service.impl.consulta;

import java.util.Comparator;
import java.util.Iterator;

@SuppressWarnings("hiding")
public class OrdemDados<String, Object> extends Ordem<String, Object>{
	
	private static final long serialVersionUID = 1L;

	public OrdemDados(Consulta consulta) {
		super(consulta);
		// TODO Auto-generated constructor stub
	}
	
	public void add(Campo campo ,String key ) {
		
		/*
		 * Caso o Objeto não seja parte da lista de ordenação
		 */
		if( get(key) == null ){
			
			System.out.println("\nObjeto sendo incluído pois ainda não faz parte da lista de Ordenação de Dados\n");
			//Campo campo =  campos;
			ordenarPrimeiraPosicao();
			
			//System.out.println("Posição pretendida: " + cp.getMostrarPosicao() + " - id:" + cp.getNome());
			if (campo.getOrdenarPosicao() < 1 ){
				campo.setOrdenarPosicao( (size() + 1) );
			}
			liberarPosicao( campo.getOrdenarPosicao());
			
			//System.out.println("\n\nCampo adicionado na ordem de seleção: " + cp.getMostrarPosicao() + "\nkey: " + key + "\n");
			super.put(key, (Object)campo);
		}
		else{
			System.out.println("\nObjeto já faz parte da lista e não foi novamente incluído\n"); // Esse else é superfulo
		}
	}
	
	public void liberarPosicao(int posicao){
		posicao--;
		for(Iterator i = this.values().iterator(); i.hasNext();){
			org.drs.service.impl.consulta.Campo campo = ((org.drs.service.impl.consulta.Campo)i.next());
			
			
			if(campo.getOrdenarPosicao() > posicao){
				campo.setOrdenarPosicao( campo.getOrdenarPosicao() + 1);
				System.out.println("campo liberado " + campo.getOrdenarPosicao());
				System.out.println("");
			}
		}
	}
	
	public void ordenarPrimeiraPosicao(){
		
		int pos = 1;
		for(Iterator i = this.values().iterator(); i.hasNext();){
			org.drs.service.impl.consulta.Campo campo = ((org.drs.service.impl.consulta.Campo)i.next());
			System.out.println("alinhando da pos 1: " + campo.getOrdenarPosicao() );
			campo.setOrdenarPosicao(pos);
			pos++;
		}
	}
	
	@Override
	public Comparator<Object> getComparadorNovo() {
		//System.out.println("\nNovoComparador\n");
		return new OrdemDadosOrdenador();
	}
	
}
