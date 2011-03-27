package br.com.kasystemas.drsclienteweb.control;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.types.URI.MalformedURIException;
import org.globus.drs.stubs.Cube.Atributo;
import org.globus.drs.stubs.Cube.SalvarOrdemCampos;



public class SalvarOrdemAtributo extends SalvarOrdem {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void setAtributoPropriedadeOrdenacao(Atributo at, int valor){
		at.setMostrarPosicao(valor);
	}
	
	@Override
	public void enviarOrdem(Atributo[] atributos) throws MalformedURIException, ServiceException, RemoteException{
		obterDRSPorttype().salvarOrdemCampos(new SalvarOrdemCampos(atributos)) ;
	}
	
	public String getParametroResumoRetorno(){
		return "&ordemcampos=true";
	}
}
