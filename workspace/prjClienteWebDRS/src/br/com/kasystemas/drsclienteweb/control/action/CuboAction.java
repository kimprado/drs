package br.com.kasystemas.drsclienteweb.control.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cube.service.impl.modelo.Cubo;

import br.com.kasystemas.drsclienteweb.control.action.MensagemRetorno.MensagemRetorno;
import br.com.kasystemas.drsclienteweb.dao.CubeServiceDAO;

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
		String metodo = request.getParameter(PARAMETRO_METODO);
		if ( "formIncluirCubo".equals(metodo) ) {
			formIncluirCubo(request, response);
		} else if ( "incluirCubo".equals(metodo) ) {
			incluirCubo(request, response);
		} else if ( "formAlterarCubo".equals(metodo) ) {
			formAlterarCubo(request, response);
		} else if ( "alterarCubo".equals(metodo) ) {
			alterarCubo(request, response);
		} else if ( "formVisualizarCubo".equals(metodo) ) {
			formVisualizarCubo(request, response);
		} else if ( "formExcluirCubo".equals(metodo) ) {
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
		Cubo cubo = new Cubo();
		
		request.setAttribute(PARAMETRO_ACAO, "incluir");
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
	private void incluirCubo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cubo cubo = obterCuboRequisicao(request);
		
		try {
			boolean incluirCubo = new CubeServiceDAO().incluirCubo(cubo);
			
			if ( incluirCubo ) {
				request.setAttribute("cubo", cubo);
				request.setAttribute(PARAMETRO_ACAO, "cubo");
				request.setAttribute("camposReadonly", true);
				request.setAttribute("mensagemRetorno", new MensagemRetorno("inclusao", false, "Cadastro realizado com sucesso.", "recarregarCubos()"));
			} else {
				request.setAttribute("cubo", cubo);
				request.setAttribute(PARAMETRO_ACAO, "incluir");
				request.setAttribute("mensagemRetorno", new MensagemRetorno("inclusao", true, "Erro ao incluir cubo."));
			}
		} catch (Exception e) {
			request.setAttribute("cubo", cubo);
			request.setAttribute(PARAMETRO_ACAO, "incluir");
			request.setAttribute("mensagemRetorno", new MensagemRetorno("inclusao", true, e.getMessage()));
		}
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
		Cubo cuboConsulta = new CubeServiceDAO().consultarCubo(cubo);
		
		request.setAttribute("cubo", cuboConsulta);
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
	private void alterarCubo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cubo cubo = obterCuboRequisicao(request);
		
		request.setAttribute("cubo", cubo);
		request.setAttribute(PARAMETRO_ACAO, "visualizar");
		request.setAttribute("camposReadonly", true);
		request.setAttribute("mensagemRetorno", new MensagemRetorno("alteracao", false, "Cadastro realizado com sucesso.", "recarregarCubos()"));
		
		encaminhar(FORMULARIO_CUBO);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void formVisualizarCubo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cubo cubo = obterCuboRequisicao(request);
		Cubo cuboConsulta = new CubeServiceDAO().consultarCubo(cubo);
		
		request.setAttribute("cubo", cuboConsulta);
		request.setAttribute(PARAMETRO_ACAO, "visualizar");
		request.setAttribute("camposReadonly", true);
		
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
		Cubo cubo = obterCuboRequisicao(request);
		
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
		
		String parametroId = parametroRequisicao("cubo.id");
		if ( !"".equals(parametroId.trim()) ) {
			cubo.setId( Integer.parseInt(parametroId) );
		}
		
		cubo.setNome( parametroRequisicao("cubo.nome") );
		cubo.setConnectionUrl( parametroRequisicao("cubo.connectionUrl") );
		cubo.setConnectionUser( parametroRequisicao("cubo.connectionUser") );
		cubo.setConnectionPassword( parametroRequisicao("cubo.connectionPassword") );
		cubo.setDriver( parametroRequisicao("cubo.driver") );
		
		String parametroRefresh = parametroRequisicao("cubo.refresh");
		if ( parametroRefresh != null && !"".equals(parametroRefresh.trim()) ) {
			cubo.setRefresh( Long.parseLong(parametroRefresh) );
		}
		
		return cubo;
	}
	
	
	
}
