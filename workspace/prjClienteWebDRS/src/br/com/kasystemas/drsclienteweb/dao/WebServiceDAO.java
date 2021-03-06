package br.com.kasystemas.drsclienteweb.dao;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.globus.cube.stubs.Cube.CubePortType;
import org.globus.cube.stubs.Cube.service.CubeServiceAddressingLocator;

public class WebServiceDAO {
	
	public static String CUBESERVICEURI = "cubeservice";
	
	public static String getServiceURI(String servico){
		try{
		FileReader reader = new FileReader(System.getenv("CUBO_CONF"));
		BufferedReader leitor = new BufferedReader(reader,1*1024*1024);
		String linha = null;
		while( leitor.ready()  ) {
			linha = leitor.readLine();
			if (linha.toCharArray().length > 0){
				if(servico.equalsIgnoreCase( String.copyValueOf(linha.toCharArray(),0,servico.length()))){
					return String.copyValueOf(linha.toCharArray(),servico.length()+1,linha.length() - servico.length()-1) ;
				}
			}
		}
		leitor.close();
		reader.close();
		}catch (Exception e){ e.printStackTrace();}
		return null;
	}
	
}
