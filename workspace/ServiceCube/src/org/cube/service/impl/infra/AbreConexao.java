package org.cube.service.impl.infra;

import javax.persistence.EntityManager;


public class AbreConexao {
	
	public static EntityManager abreConexao(){
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		return em;
	}
}
