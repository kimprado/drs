package org.drs.service.impl.consulta;

import java.util.Comparator;


public class OrdemDadosOrdenador<Object> implements Comparator<Object>{
	
	public int compare(Object o1, Object o2){
		
		if(o1 instanceof Campo && o2 instanceof Campo)
		{
			Campo e1 = (Campo)o1;
			Campo e2 = (Campo)o2;
			return e1.getOrdenarPosicao() - e2.getOrdenarPosicao();
		}
		return -1;
	}
}