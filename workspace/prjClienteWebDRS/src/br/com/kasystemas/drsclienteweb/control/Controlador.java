package br.com.kasystemas.drsclienteweb.control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.globus.drs.stubs.Cube.CriarConsulta;
import org.globus.drs.stubs.Cube.DRSPortType;
import org.globus.drs.stubs.Cube.service.DRSServiceAddressingLocator;
 

/**
 * Servlet implementation class Controlador
 */
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter out = response.getWriter();
		//out.println("oi, oi. Bem-Vindo ao Grid");
		
		try {


			RequestDispatcher rq = request.getRequestDispatcher("index.jsp");
			rq.forward(request, response);
			
			} catch (Exception e) {
				e.printStackTrace();
				//out.println("DEU ER0ROR");
			}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
