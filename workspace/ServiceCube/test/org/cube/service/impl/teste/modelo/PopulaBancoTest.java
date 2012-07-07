package org.cube.service.impl.teste.modelo;

import javax.persistence.EntityManager;

import org.cube.service.impl.dao.DAO;
import org.cube.service.impl.dao.PersisteCuboServiceDAO;
import org.cube.service.impl.infra.AbreConexao;
import org.cube.service.impl.infra.FechaConexao;
import org.cube.service.impl.modelo.Atributo;
import org.cube.service.impl.modelo.Cubo;
import org.cube.service.impl.modelo.Dimensao;
import org.cube.service.impl.modelo.Fato;
import org.cube.service.impl.teste.control.GerenciaMetaDadosTest;
import org.junit.Test;

public class PopulaBancoTest {
	
	@Test
	public void criaNovoCuboTest() throws Exception {
		EntityManager em = AbreConexao.abreConexao();
		DAO<Cubo> dao = new DAO<Cubo>(em, Cubo.class);
		
		Cubo atributo = new Cubo();
		//atributo.setId(32);
		atributo.setNome("Vendas");
		atributo.setServer("eingrid005");
		/*atributo.setDecimal("2");
		atributo.setTipo("double");
		atributo.setTamanho("30");*/
		
		dao.adiciona(atributo);
		
		FechaConexao.fechaConexao(em);
		
	}
	
	@Test
	public void buscaCuboTest() throws Exception {
		EntityManager abreConexao = AbreConexao.abreConexao();
		DAO<Cubo> dao = new DAO<Cubo>(abreConexao, Cubo.class);
		
		Cubo cubo = dao.busca(1);
		
		//cubo = dao.busca(2);
		
		//System.out.println("cubo localizado: " + cubo.getId() + " " + cubo.getNome() + " " + cubo.getServer() );
		System.out.println(cubo.getNome());
		
		FechaConexao.fechaConexao(abreConexao);
		
	}
	
	@Test
	public void buscaCubosTest() throws Exception {
		//Com cache de segundo n�vel estas tr�s chamadas v�o precisar de apenas um consulta ao banco, 
		//	mesmo com o fechameto dos EntityManager(em) 
		buscaCuboTest();
		buscaCuboTest();
		buscaCuboTest();
		
	}

	@Test
	public void criaNovoFatoTest() throws Exception {
		EntityManager em = AbreConexao.abreConexao();
		//DAO<Fato> dao = new DAO<Fato>(em, Fato.class);
		
		Fato fato = new Fato("vendas");
		//fato.setId(18);
		//fato.setTesteNofato("teste do nome do fato no fato");
		//fato.setTesteNatabela("teste do nome da tabela no fato");
		//dao.adiciona(fato);
		
		em.persist(fato);
		
		FechaConexao.fechaConexao(em);
		
	}
	
	@Test
	public void buscaFatoTest() throws Exception {
		EntityManager abreConexao = AbreConexao.abreConexao();
		DAO<Fato> dao = new DAO<Fato>(abreConexao, Fato.class);
		
		Fato fato = dao.busca(1);
		
		System.out.println("fato localizado: " + fato.getId() + " " + fato.getNome());
		
		System.out.println("cubo do fato: " + fato.getCubo());
		
		FechaConexao.fechaConexao(abreConexao);
	}
	
	@Test
	public void criaNovaDimensaoTest() throws Exception {
		EntityManager em = AbreConexao.abreConexao();
		//DAO<Fato> dao = new DAO<Fato>(em, Fato.class);
		
		Dimensao dimensao = new Dimensao("tempo");
		//fato.setId(18);
		//fato.setTesteNofato("teste do nome do fato no fato");
		//fato.setTesteNatabela("teste do nome da tabela no fato");
		//dao.adiciona(fato);
		
		em.persist(dimensao);
		
		FechaConexao.fechaConexao(em);
		
	}
	
	@Test
	public void buscaDimensaoTest() throws Exception {
		EntityManager abreConexao = AbreConexao.abreConexao();
		DAO<Dimensao> dao = new DAO<Dimensao>(abreConexao, Dimensao.class);
		
		Dimensao dimensao = dao.busca(2);
		
		System.out.println("dimensao localizada: " + dimensao.getId() + " " + dimensao.getNome());
		
		FechaConexao.fechaConexao(abreConexao);
	}
	
	@Test
	public void criaNovoAtributoTest() throws Exception {
		EntityManager em = AbreConexao.abreConexao();
		DAO<Atributo> dao = new DAO<Atributo>(em, Atributo.class);
		
		Atributo atributo = new Atributo();
		//atributo.setId(47);
		atributo.setNome("Pre�o");
		atributo.setDecimal("2");
		atributo.setTipo("double");
		atributo.setTamanho("30");
		
		dao.adiciona(atributo);
		//dao.altera(atributo);
		
		FechaConexao.fechaConexao(em);
		
	}
	
	@Test
	public void buscaAtributoTest() throws Exception {
		EntityManager abreConexao = AbreConexao.abreConexao();
		DAO<Atributo> dao = new DAO<Atributo>(abreConexao, Atributo.class);
		
		Atributo atributo = dao.busca(1);
		
		System.out.println("atributo localizado: " + atributo.getId() + " " + atributo.getName() + " " + atributo.getDecimal() + " " + atributo.getTamanho() + " " +  atributo.getTipo());
		//System.err.println("tabela do atributo: " + atributo.getTabela().GetQuantidadeAtributo());
		
		FechaConexao.fechaConexao(abreConexao);
	}
	
	@Test
	public void editarFatoAdicionarCuboTest() throws Exception {
		EntityManager em = AbreConexao.abreConexao();
		DAO<Fato> dao = new DAO<Fato>(em, Fato.class);
		
		Fato fato = dao.busca(1);
		
		System.out.println("fato localizado: " + fato.getId() + " " + fato.getNome());
		System.out.println("cubo do fato: " + fato.getCubo());
		
		Cubo cubo = new Cubo();
		cubo.setId(2);
		em.merge(cubo);
		cubo = em.find(Cubo.class, 1);
		em.detach(cubo);
		cubo = em.find(Cubo.class, 1);
		
		//Cubo cubo = em.find(Cubo.class, 32);
		
		//cubo.setFato(fato);
		fato.setCubo(cubo);
		
		//em.merge(cubo);
		em.merge(fato);
		
		System.out.println("fato localizado: " + fato.getId() + " " + fato.getNome());
		System.out.println("cubo do fato: " + fato.getCubo());
		
		FechaConexao.fechaConexao(em);
	}
	
	@Test
	public void carregaMetaDadosEPersisteCubo() throws Exception{
		EntityManager em = AbreConexao.abreConexao();

		Cubo cubo = new GerenciaMetaDadosTest().carregaMetaDadosDeCuboDinamicamente();
		
		PersisteCuboServiceDAO persisteCuboServiceDAO = new PersisteCuboServiceDAO(em);
		persisteCuboServiceDAO.persisteCubo(cubo);
		
		System.out.println("Cubo auto detectado e persistido:\n" + cubo.getNome());
		
		FechaConexao.fechaConexao(em);
	}
	
	@Test
	public void editarAtributoAdicionarFatoTest() throws Exception {
		EntityManager em = AbreConexao.abreConexao();
		DAO<Fato> dao = new DAO<Fato>(em, Fato.class);
		
		Fato fato = dao.busca(1);
		
		System.out.println("fato localizado: " + fato.getId() + " " + fato.getNome());
		//System.out.println("cubo do fato: " + fato.getCubo());
		
		DAO<Atributo> daoAtributo = new DAO<Atributo>(em, Atributo.class);
		Atributo atributo = daoAtributo.busca(3);
		//fato.Addatributo(atributo);
		atributo.setTabela(fato);
		
		System.out.println("fato localizado: " + fato.getId() + " " + fato.getNome());
		//System.out.println("cubo do fato: " + fato.getCubo());
		
		//Mesmo sem dao.altera() o hibernate faz o updade autom�tico.
		FechaConexao.fechaConexao(em);
	}
	
	@Test
	public final void removeCubo() throws Exception {
		EntityManager em = AbreConexao.abreConexao();
		try {
			
			DAO<Cubo> dao = new DAO<Cubo>(em, Cubo.class);
			
			Cubo cubo = dao.busca(3);
			
			dao.remove(cubo);
		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			FechaConexao.fechaConexao(em);
		}
	}
	
}
