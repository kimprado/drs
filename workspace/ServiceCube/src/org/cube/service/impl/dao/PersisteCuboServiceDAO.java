package org.cube.service.impl.dao;

import javax.persistence.EntityManager;

import org.cube.service.impl.modelo.Atributo;
import org.cube.service.impl.modelo.ChaveEstrangeira;
import org.cube.service.impl.modelo.Cubo;
import org.cube.service.impl.modelo.Dimensao;
import org.cube.service.impl.modelo.Fato;
import org.cube.service.impl.modelo.Ligacao;

public class PersisteCuboServiceDAO {
	
	private final EntityManager em;
	
	public PersisteCuboServiceDAO(EntityManager em) {
		if (em == null) {
			throw new IllegalArgumentException();
		}
		this.em = em;
	}
	
	public void persisteCubo(Cubo cubo) {
		persiste(cubo);
		
		Fato fato = cubo.getFato();
		persiste(fato);
		
		for (Atributo atributo : fato.getAtributos()) {
			persiste(atributo);
		}
		
		persiste(fato.getChavePrimaria());
		/*for (Atributo atributo : fato.getChavePrimaria().getAtributos()) {
			persiste(atributo);
		}*/
		
		for (ChaveEstrangeira chaveEstrangeira : fato.getChaveEstrangeira()) {
			persiste(chaveEstrangeira);
			
			Dimensao dimensao = chaveEstrangeira.getDimensao();
			persiste(dimensao);
			
			for (Atributo atributo : dimensao.getAtributos()) {
				persiste(atributo);
			}
			
			persiste(dimensao.getChavePrimaria());
			/*for (Atributo atributo : dimensao.getChavePrimaria().getAtributos()) {
				persiste(atributo);
			}*/
			
			
			for (Ligacao ligacao : chaveEstrangeira.getLigacoes()) {
				persiste(ligacao);
				//persiste(ligacao.getAtributoDimensao());
				//persiste(ligacao.getAtributoFato());
			}
		}
		
		
		
	}
	
	private void persiste(Object entity) {
		em.persist(entity);
		//em.merge(entity);
	}
	
}
