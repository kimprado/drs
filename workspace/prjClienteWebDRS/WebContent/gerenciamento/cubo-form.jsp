<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>DRS - Cubo</title>
	
	<script src="include/script/cubo/cubo.js"></script>
	<script src="include/script/cadastro/cadastroGeral.js"></script>
	
	<script type="text/javascript">
		var mensagem = {
			tipo : "${mensagemRetorno.tipo}",
			erro : "${mensagemRetorno.erro}",
			mensagem : "${mensagemRetorno.mensagem}",
			funcao : "${mensagemRetorno.funcao}"
		};
		mensagemRetorno( mensagem );
		
		if ( mensagem.funcao && mensagem.funcao != "" ) {
			eval( "" + mensagem.funcao + "" ) ;
		}
	</script>
	
	
</head>
<body bgcolor=#E5EDFF>
	
	<c:if test="${acao eq 'incluir'}" >
		<c:set var="metodo" value="incluirCubo"/>
		<h4>&nbsp;Cubo > Incluir</h4>
	</c:if>
	
	<c:if test="${acao eq 'alterar'}" >
		<c:set var="metodo" value="alterarCubo"/>
		<h4>&nbsp;Cubo > Alterar</h4>
	</c:if>
	
	<c:if test="${acao eq 'visualizar'}" >
		<h4>&nbsp;Cubo > Visualizar</h4>
	</c:if>
	
	<c:if test="${camposReadonly eq true}" >
		<c:set var="readonly" value="readonly=\"readonly\""/>
	</c:if>
	
	<form action="cubo.do" method="post">
		<input type="hidden" name="cubo.id" value="${cubo.id}">
		<input type="hidden" name="metodo" value="${metodo}">
		<input type="hidden" name="acao" value="${metodo}">
		
		<table>
			<tr>
				<td>
					Nome
				</td>
				<td>
					<input type="text" id="cubo.nome" name="cubo.nome" value="${cubo.nome}" ${readonly}/>
				</td>
			</tr>
			
			<tr>
				<td>
					URL
				</td>
				<td>
					<input type="text" id="cubo.connectionUrl" name="cubo.connectionUrl" value="${cubo.connectionUrl}" ${readonly}/>
				</td>
			</tr>
			
			<tr>
				<td>
					Usuário
				</td>
				<td>
					<input type="text" id="cubo.connectionUser" name="cubo.connectionUser" value="${cubo.connectionUser}" ${readonly}/>
				</td>
			</tr>
			
			<tr>
				<td>
					Senha
				</td>
				<td>
					<input type="text" id="cubo.connectionPassword" name="cubo.connectionPassword" value="${cubo.connectionPassword}" ${readonly}/>
				</td>
			</tr>
			
			<tr>
				<td>
					Driver
				</td>
				<td>
					<input type="text" id="cubo.driver" name="cubo.driver" value="${cubo.driver}" ${readonly}/>
				</td>
			</tr>
			
			<tr>
				<td>
					Refresh
				</td>
				<td>
					<input type="text" id="cubo.refresh" name="cubo.refresh" value="${cubo.refresh}" ${readonly}/>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">			
					<c:if test="${ acao eq 'incluir' }">
						<input type="submit" value="Gravar">
						<input type="reset" value="Limpar">
					</c:if>
					
					<c:if test="${ acao eq 'alterar' }">
						<input type="submit" value="Gravar">
						<input type="button" value="Cancelar" onclick="visualizarCubo()">
					</c:if>
					
					<c:if test="${ acao eq 'visualizar' }">
						<input type="button" value="Novo" onclick="novoCubo()">
						<input type="button" value="Alterar" onclick="alterarCubo()">
					</c:if>
					
				</td>
			</tr>
			
		</table>
	
	</form>
	
</body>
</html>