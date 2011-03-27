package br.com.kasystemas.drsclienteweb.control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.axis.types.URI.MalformedURIException;
import org.globus.drs.stubs.Cube.Atributo;
import org.globus.drs.stubs.Cube.DRSPortType;
import org.globus.drs.stubs.Cube.service.DRSServiceAddressingLocator;

/**
 * Servlet implementation class SalvarOrdem
 */
public class SalvarOrdem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalvarOrdem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Enumeration en = request.getParameterNames();
		
		if(  ( (Integer)(request.getSession()).getAttribute( "a_mysession" ) ) != null){
		
			ArrayList<Atributo> atributos = new ArrayList<Atributo>( request.getParameterMap().size() );
			
			for(Enumeration en = request.getParameterNames(); en.hasMoreElements();){
				boolean inserir = true;
				
				String parameter = (String)en.nextElement();
				
				Atributo at = new Atributo();
				at.setIdObjeto(parameter);
				
				try{
					Integer.parseInt((String)request.getParameter((parameter)));
					(request.getSession()).getAttribute( "a_mysession" );
					
					//Método Sobrescrito pelos Filhos
					setAtributoPropriedadeOrdenacao(at, Integer.parseInt((String)request.getParameter((parameter))));
					
					at.setConsulta( (Integer)(request.getSession()).getAttribute( "a_mysession" ) );
					
				}catch(Exception e){
					inserir = false;
				}
				
				if(inserir){
					atributos.add(at);
				}
				
			}
			
			Atributo[] atributosEnvio = new Atributo[atributos.size()];
			for(int i = 0; i < atributosEnvio.length ; i++ ){
				atributosEnvio[i] = atributos.get(i);
				System.out.println(atributosEnvio[i].getIdObjeto() + " - " + atributosEnvio[i].getCuboNome() + " - " + atributosEnvio[i].getMostrarPosicao());
			}
			
			try {
				
				//Método Sobrescrito pelos Filhos
				enviarOrdem(atributosEnvio); 
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		//RequestDispatcher rq = request.getRequestDispatcher( getPaginaRetorno() );
		//rq.forward(request, response);
		RequestDispatcher rq = request.getRequestDispatcher("resumo.jsp?cube=" + request.getParameter("cube") + getParametroResumoRetorno());
		rq.forward(request, response);
		
	}
	
	/*
	 * Sobrescrito pelos filhos
	 */
	public String getParametroResumoRetorno(){
		return null;
	}
	
	public void setAtributoPropriedadeOrdenacao(Atributo at, int valor){
		//at.setOrdenarPosicao(valor);
	}
	
	public DRSPortType obterDRSPorttype() throws MalformedURIException, ServiceException{
		
		DRSServiceAddressingLocator locatorDRS = new DRSServiceAddressingLocator();
		EndpointReferenceType endpointDRS = new EndpointReferenceType();
		endpointDRS.setAddress(new Address(getServicURI("drsservice"))); //DRS
		DRSPortType drs = locatorDRS.getDRSPortTypePort(endpointDRS);
		return drs;
		
	}
	
	public void enviarOrdem(Atributo[] atributos) throws MalformedURIException, ServiceException, RemoteException{
		//obterDRSPorttype();
	}
	
	public String getServicURI(String procurar) {
		try {
			FileReader reader = new FileReader(System.getenv("CUBO_CONF"));
			BufferedReader leitor = new BufferedReader(reader, 1 * 1024 * 1024);
			String linha = null;
			while (leitor.ready()) {
				linha = leitor.readLine();
				if (linha.toCharArray().length > 0) {
					if (procurar.equalsIgnoreCase(String.copyValueOf(linha
							.toCharArray(), 0, procurar.length()))) {
						return String.copyValueOf(linha.toCharArray(), procurar
								.length() + 1, linha.length()
								- procurar.length() - 1);
					}
				}
			}
			leitor.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
