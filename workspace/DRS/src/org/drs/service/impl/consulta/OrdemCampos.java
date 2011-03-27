package org.drs.service.impl.consulta;

import java.util.Comparator;
import java.util.Iterator;

@SuppressWarnings("hiding")
public class OrdemCampos<String, Object> extends Ordem<String, Object>{
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public OrdemCampos(Consulta consulta) {
		super(consulta);
	}
	
	public void add(Campo campo ,String key ) {
		
		/*
		 * Caso o Objeto n�o seja parte da lista de ordena��o
		 */
		if( get(key) == null ){
			
			//System.out.println("Objeto sendo pinclu�do pois ainda n�o faz parte da lista de ordena��o de nomes");
			//Campo campo =  campos;
			ordenarPrimeiraPosicao();
			
			//System.out.println("Posi��o pretendida: " + cp.getMostrarPosicao() + " - id:" + cp.getNome());
			if (campo.getMostrarPosicao() < 1 ){
				campo.setMostrarPosicao( (size() + 1) );
			}
			liberarPosicao( campo.getMostrarPosicao() );
			
			//System.out.println("\n\nCampo adicionado na ordem de sele��o: " + cp.getMostrarPosicao() + "\nkey: " + key + "\n");
			super.put(key, (Object)campo);
		}
		else{
			//System.out.println("Objeto ja faz parte da lista e n�o foi novamente inclu�do");
		}
	}
	
	public void liberarPosicao(int posicao){
		posicao--;
		for(Iterator i = this.values().iterator(); i.hasNext();){
			org.drs.service.impl.consulta.Campo campo = ((org.drs.service.impl.consulta.Campo)i.next());
			
			
			if(campo.getMostrarPosicao() > posicao){
				campo.setMostrarPosicao( campo.getMostrarPosicao() + 1);
				System.out.println("campo liberado " + campo.getMostrarPosicao());
				System.out.println("");
			}
		}
	}
	
	public void ordenarPrimeiraPosicao(){
		
		int pos = 1;
		for(Iterator i = this.values().iterator(); i.hasNext();){
			org.drs.service.impl.consulta.Campo campo = ((org.drs.service.impl.consulta.Campo)i.next());
			System.out.println("alinhando da pos 1: "+campo.getMostrarPosicao());
			campo.setMostrarPosicao(pos);
			pos++;
		}
	}
	
	@Override
	public Comparator<Object> getComparadorNovo() {
		//System.out.println("\nNovoComparador\n");
		return new OrdemCampoOrdenador();
	}
	
	/*public void add(Campo campo ,String key ) {
		
		org.drs.service.impl.consulta.Campo cp =  (org.drs.service.impl.consulta.Campo)campo;
		if (cp.getMostrarPosicao() < 1 ){
			cp.setMostrarPosicao( (size() + 1) );
		}
		super.put(key, cp);
		//;
	}*/
	
}
