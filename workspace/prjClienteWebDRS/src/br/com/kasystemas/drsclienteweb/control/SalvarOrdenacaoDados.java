package br.com.kasystemas.drsclienteweb.control;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.types.URI.MalformedURIException;
import org.globus.drs.stubs.Cube.Atributo;
import org.globus.drs.stubs.Cube.SalvarOrdemCampos;
import org.globus.drs.stubs.Cube.SalvarOrdemDados;



public class SalvarOrdenacaoDados extends SalvarOrdem {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void setAtributoPropriedadeOrdenacao(Atributo at, int valor){
		at.setOrdenarPosicao(valor);
	}
	
	@Override
	public void enviarOrdem(Atributo[] atributos) throws MalformedURIException, ServiceException, RemoteException{
		obterDRSPorttype().salvarOrdemDados(new SalvarOrdemDados(atributos)) ;
		//System.out.println("Vou salvar quando implementado");
	}
	
	public String getParametroResumoRetorno(){
		return "&ordemdados=true";
	}
}
