package org.cube.service.impl.control;

import javax.persistence.EntityManager;

/**
 * Define contrato de estrat�gia de persist�ncia.
 * @author kim
 *
 */
public interface Persistir {
	
	public void persistir(EntityManager em, Object entity);

}
