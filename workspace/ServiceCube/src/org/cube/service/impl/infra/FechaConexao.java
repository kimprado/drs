package org.cube.service.impl.infra;

import javax.persistence.EntityManager;

public class FechaConexao {
	
	public static void fechaConexao(EntityManager em) throws Exception {
		
		try {
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw e; 
		}
		finally {
			em.close();
		}
		
	}
}
