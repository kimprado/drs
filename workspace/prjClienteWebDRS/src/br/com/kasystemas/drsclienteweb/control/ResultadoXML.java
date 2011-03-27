package br.com.kasystemas.drsclienteweb.control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.globus.cube.stubs.Cube.CubePortType;
import org.globus.cube.stubs.Cube.ExecuteQuery;
import org.globus.cube.stubs.Cube.ExecuteQueryResponse;
import org.globus.cube.stubs.Cube.service.CubeServiceAddressingLocator;
import org.globus.drs.stubs.Cube.DRSPortType;
import org.globus.drs.stubs.Cube.service.DRSServiceAddressingLocator;

/**
 * Servlet implementation class ResultadoXML
 */
public class ResultadoXML extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultadoXML() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("oi get");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("oi post");
		this.exec(request, response);
		
	}
	
	public String getServicURI(String procurar){
		try{
		FileReader reader = new FileReader(System.getenv("CUBO_CONF"));
		BufferedReader leitor = new BufferedReader(reader,1*1024*1024);
		String linha = null;
		while( leitor.ready()  ) {
			linha = leitor.readLine();
			if (linha.toCharArray().length > 0){
				if(procurar.equalsIgnoreCase( String.copyValueOf(linha.toCharArray(),0,procurar.length()))){
					return String.copyValueOf(linha.toCharArray(),procurar.length()+1,linha.length() - procurar.length()-1) ;
				}
			}
		}
		leitor.close();
		reader.close();
		}catch (Exception e){ e.printStackTrace();}
		return null;
	}
	
	private void exec( HttpServletRequest request, HttpServletResponse response ){
		//System.out.println("oi exec");
		
		try {

			Integer a_mysession = (Integer) request.getSession().getAttribute( "a_mysession" );
			if (a_mysession == null){
			//	pageContext.forward("/tree/index.jsp");
			}

			DRSServiceAddressingLocator locatorDRS = new DRSServiceAddressingLocator();
			EndpointReferenceType endpointDRS = new EndpointReferenceType();
			//endpointDRS.setAddress(new Address("http://eingrid005.unigranrio.br:8443/wsrf/services/cube/DRS"));
			endpointDRS.setAddress(new Address(getServicURI("drsservice"))); //DRS;
			DRSPortType drs = locatorDRS.getDRSPortTypePort(endpointDRS);


			CubeServiceAddressingLocator locatorCube = new CubeServiceAddressingLocator();
			EndpointReferenceType endpointCube = new EndpointReferenceType();
			//String serviceURIcube = new String("http://eingrid002.unigranrio.br:8443/wsrf/services/cube/Cube");
			String serviceURIcube = new String(getServicURI("cubeservice")); //CUBE
			endpointCube.setAddress(new Address(serviceURIcube));

			CubePortType cube = locatorCube.getCubePortTypePort(endpointCube);


			String sql = drs.getSQL(a_mysession.intValue()); //passa o valor correspondente da consulta da sessão
			
			//System.out.println("\nConsulta Submetida:\n"+sql+"");
			
			
			if ( sql != null){ // Se a consulta pode ser formada

				ExecuteQueryResponse exquery = cube.executeQuery(new ExecuteQuery(Integer.parseInt(request.getParameter("cube")),sql));
				
				new ResultadoParaXml(request,response,sql).parserToXML(exquery);
				
			}
		
		} catch (Exception e) {
			
		}
		
		
	}

}
