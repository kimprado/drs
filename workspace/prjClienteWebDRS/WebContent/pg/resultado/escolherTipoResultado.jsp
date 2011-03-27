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
	//var form = document.getElementById('form');

	if(escolha != null){
		if (escolha == 'natela'){
			return "resultadoTela.jsp"; //aponta para jsp
		}
		else if (escolha == 'resultadoxml'){
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
</form>

<form action="">
	<input type="radio" name="resultado" id="natela" onclick="escolher('natela');" value="" />Exibir na tela
	<br>
	<input type="radio" name="resultado" id="resultadoxml" onclick="escolher('resultadoxml');" value="" />Salvar em XML
</form>

<br /><br />
<br /><br />
<input type="button" onclick="resultado()" value="OK">

</body>
</html>