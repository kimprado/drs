package org.cube.service.impl.control;

import javax.persistence.EntityManager;

public class PersistirCriar implements Persistir {
	
	@Override
	public void persistir(EntityManager em, Object entity){
		em.persist(entity);
	}
}
