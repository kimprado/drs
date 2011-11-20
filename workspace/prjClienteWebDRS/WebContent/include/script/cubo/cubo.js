function novoCubo(){
	var form = document.forms[0];
	form.action = "cubo.do?metodo=formIncluirCubo";
	form.submit();
}

function alterarCubo(){
	var form = document.forms[0];
	form.action = "cubo.do?metodo=formAlterarCubo";
	form.submit();
}

function visualizarCubo(){
	var form = document.forms[0];
	form.action = "cubo.do?metodo=formVisualizarCubo";
	form.submit();
}