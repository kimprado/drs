package org.cube.service.impl.control;

import javax.persistence.EntityManager;

import org.cube.service.impl.modelo.Cubo;

public class PersistirCubo {
	
	private Persistir persistir;

	public PersistirCubo(EntityManager em, Persistir persistirEstrategia, Cubo cubo ) {
		this.persistir = persistirEstrategia;
		persistir.persistir(em, cubo);
	}
	
}
