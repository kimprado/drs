<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DRS - Escolha do Formato de Saída da Consulta</title>

<script type="text/javascript">
var escolha = null;

function escolher(selecionar){
	escolha = selecionar;
}

function resultado(){
	resultado = configurarTipoResultado();
	if(resultado != null){
		var formulario = document.getElementById('form');
		formulario.action = resultado;
		formulario.submit();
	}
	else{
		alert('Escolha uma opção');
	}
	
}

function configurarTipoResultado(){
	if(escolha != null){
		if (escolha == 'natela'){
			return "resultadoTela.jsp"; //aponta para jsp
		}
		else if (escolha == 'xml'){
			return "../../ResultadoXML"; //aponta para Servlet
		}
		else if (escolha == 'excel'){
			return "../../ResultadoXML"; //aponta para Servlet
		}
			
	}
	return null;
}

</script>


</head>


<body onLoad="">

<form id="form" name="form" action="" method="post">
	<input type="hidden" value="<%= request.getParameter( "cube" ) %>" name="cube" />


	<input type="radio" name="tipoResultado" id="" onclick="escolher('natela');" value="natela" />Exibir na tela
	<br>
	<input type="radio" name="tipoResultado" id="" onclick="escolher('xml');" value="xml" />Salvar em XML
	<br>
	<input type="radio" name="tipoResultado" id="" onclick="escolher('excel');" value="excel" />Salvar em Microsoft Excel
</form>
<!-- </form>

<form action=""> -->
<br /><br />
<br /><br />
<input type="button" onclick="resultado()" value="OK">

</body>
</html>