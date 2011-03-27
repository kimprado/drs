package org.cube.service.impl.control;

import javax.persistence.EntityManager;

public class PersistirAlterar implements Persistir {

	@Override
	public void persistir(EntityManager em, Object entity) {
		em.merge(entity);
	}

}