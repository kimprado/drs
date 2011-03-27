package org.cube.service.impl.control;

import javax.persistence.EntityManager;

/**
 * Define contrato de estratégia de persistência.
 * @author kim
 *
 */
public interface Persistir {
	
	public void persistir(EntityManager em, Object entity);

}
