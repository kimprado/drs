package br.com.kasystemas.drsclienteweb.control.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cube.service.impl.modelo.Cubo;

public class CuboAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	private static final String FORMULARIO_CUBO = "gerenciamento/cubo-form.jsp";
	private static final String PARAMETRO_METODO = "metodo";
	private static final String PARAMETRO_ACAO = "acao";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action(request, response);
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		if ( "formIncluirCubo".equals(request.getParameter(PARAMETRO_METODO)) ) {
			formIncluirCubo(request, response);
		} else if ( "formAlterarCubo".equals(request.getParameter(PARAMETRO_METODO)) ) {
			formAlterarCubo(request, response);
		} else if ( "formExcluirCubo".equals(request.getParameter(PARAMETRO_METODO)) ) {
			excluirCubo(request, response);
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void formIncluirCubo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(PARAMETRO_ACAO, "incluir");
		
		Cubo cubo = new Cubo();
		
		request.setAttribute("cubo", cubo);
		
		encaminhar(FORMULARIO_CUBO);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void formAlterarCubo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cubo cubo = obterCuboRequisicao(request);
		
		request.setAttribute(PARAMETRO_ACAO, "alterar");
		
		encaminhar(FORMULARIO_CUBO);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void excluirCubo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cubo obterCuboRequisicao = obterCuboRequisicao(request);
		
		encaminhar(FORMULARIO_CUBO);
	}
	
	/**
	 * 
	 * @param path
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void encaminhar(String path) throws ServletException, IOException {
		RequestDispatcher rq = request.getRequestDispatcher( path );
		rq.forward(request, response);
	}
	
	private String parametroRequisicao(String parameterName) {
		return request.getParameter( parameterName );
	}
	
	private Cubo obterCuboRequisicao(HttpServletRequest request) {
		Cubo cubo = new Cubo();
		cubo.setNome( parametroRequisicao("nome") );
		cubo.setConnectionUrl( parametroRequisicao("cubo.connectionUrl") );
		cubo.setConnectionUser( parametroRequisicao("cubo.connectionUser") );
		cubo.setConnectionPassword( parametroRequisicao("cubo.connectionPassword") );
		cubo.setDriver( parametroRequisicao("cubo.driver") );
		cubo.setRefresh( Long.parseLong(parametroRequisicao("cubo.refresh")) );
		
		return cubo;
	}
	
	
	
}
