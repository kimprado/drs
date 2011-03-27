package br.com.kasystemas.drsclienteweb.control.mb;

import java.rmi.RemoteException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.kasystemas.drsclienteweb.dao.CubeServiceDAO;

@ManagedBean
@RequestScoped
//@ViewScoped
public class CuboMetaDataBean {
	
	//para testar o padr�o command implementado pela interface
	private static int count = 1;
	
	private String metaDadosCubo = "";
	private Date data = new Date();

	public void setMetaDadosCubo(String metaDadosCubo) {
		this.metaDadosCubo = metaDadosCubo;
	}

	public String getMetaDadosCubo() {
		return metaDadosCubo;
	}
	
	public void buscaMetaDadosCubo(){
		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("click: " + (count++)) ;
		//setMetaDadosCubo("N�o tinha NADA!!! isso n�o serve p/ nada: '" + getMetaDadosCubo() + "'");
		
		try {
			String metadadosSolicitados = new CubeServiceDAO().getPotType().printCube(6).getCube();
			setMetaDadosCubo(metadadosSolicitados);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			setMetaDadosCubo(e.getMessage());
		}
		
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getData() {
		return data;
	}
	
}
