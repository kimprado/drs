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
		 * Caso o Objeto n�o seja parte da lista de ordena��o
		 */
		if( get(key) == null ){
			
			System.out.println("\nObjeto sendo inclu�do pois ainda n�o faz parte da lista de Ordena��o de Dados\n");
			//Campo campo =  campos;
			ordenarPrimeiraPosicao();
			
			//System.out.println("Posi��o pretendida: " + cp.getMostrarPosicao() + " - id:" + cp.getNome());
			if (campo.getOrdenarPosicao() < 1 ){
				campo.setOrdenarPosicao( (size() + 1) );
			}
			liberarPosicao( campo.getOrdenarPosicao());
			
			//System.out.println("\n\nCampo adicionado na ordem de sele��o: " + cp.getMostrarPosicao() + "\nkey: " + key + "\n");
			super.put(key, (Object)campo);
		}
		else{
			System.out.println("\nObjeto j� faz parte da lista e n�o foi novamente inclu�do\n"); // Esse else � superfulo
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
