<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*,java.io.BufferedReader,java.io.FileReader,org.globus.drs.stubs.Cube.*,org.globus.drs.stubs.Cube.service.*,org.globus.drs.stubs.Cube.bindings.*,org.apache.axis.message.addressing.Address,org.apache.axis.message.addressing.EndpointReferenceType"
    %>
<%!

	private String toStrLogico(int logico){
		switch (logico) {

		case 1:	return new String("e");
		case 2:	return new String("ou");
		case 3:	return new String("não");

		}
		return null;
	}

	private String toStrComparacao(int comparacao){
		switch (comparacao) {
		case 1:	return new String("=");
		case 2:	return new String("<");
		case 3:	return new String("<=");
		case 4:	return new String(">");
		case 5:	return new String(">=");
		case 6:	return new String("<>");

		}

		return null;
	}

	private String toTrueFalse(boolean booleano){
		if (booleano == true) {
			return new String("sim");
		}else{
			return new String("não");
		}

	}

	private String toStrAgregacao(String nome,int agregacao){
		switch (agregacao) {

		case 1:	return new String("Contar("+nome+")");
		case 2:	return new String("Somar("+nome+")");
		case 3:	return new String("Maior("+nome+")");
		case 4:	return new String("Menor("+nome+")");
		case 5:	return new String("Média("+nome+")");

		}
		return null;
	}

%>






<script type="text/javascript">


var elementoContainerOrdemDados = null; 

function getListaObjetosOrdemDadosDrag(){
	var lista = new Object();

	var itensDrag = 0;
	for(var i=0; i<elementoContainerOrdemDados.childNodes.length; i++){

		if(elementoContainerOrdemDados.childNodes[i].nodeName=='#text') continue;

		lista[itensDrag] = elementoContainerOrdemDados.childNodes[i];
		itensDrag++;
		lista.size = itensDrag;
	}
	return lista;
}

function getObjetoEnviadoParaOrdenacaoDados(obj){
	for(var i=0; i<obj.childNodes.length; i++){
		
		if(obj.childNodes[i].nodeName=='INPUT'){
			return obj.childNodes[i];
		}
	}
	return null;
}

function atualizarOrdemDadosObjetosDrag(){
	var lista = getListaObjetosOrdemDadosDrag();
	var itensDrag = 1;
	for(var i=0; i<lista.size ;i++){
		objetoEnviado = getObjetoEnviadoParaOrdenacaoDados(lista[i]);
		if(objetoEnviado != null){
			objetoEnviado.value = itensDrag;
			itensDrag++;
			//alert(objetoEnviado + ' ' + objetoEnviado.value);
		}
	}
}
/*
window.onload = function(){
	
	// Create our helper object that will show the item while dragging
	dragHelper = document.createElement('DIV');
	dragHelper.style.cssText = 'position:absolute;display:none;';

	CreateDragContainer(
		document.getElementById('DragContainer1')//,
		//document.getElementById('DragContainer2'),
		//document.getElementById('DragContainer3')
	);

	elementoContainerOrdemDados = document.getElementById('DragContainer1');
	
	document.body.appendChild(dragHelper);
	alert("oi");
}
*/

</script>


<form id="frm_salvarOrdemDados" name="frm_salvarOrdemDados" action="SalvarOrdenacaoDados?cube=<%= request.getParameter("cube") %>" method="post">

<div class="DragContainer" id="DragContainerOrdenarDados">

	
<%
//<div class="DragContainer" id="DragContainer1">

if ( request.getParameter("cube") != null ){
	
	String cubeIndexEntry = request.getParameter( "cubeIndexEntry" );
	Integer a_mysession = (Integer)session.getAttribute( "a_mysession" );
	DRSPortType drs = (DRSPortType)request.getAttribute("porttypedrs");
	ResumoResponse getresumo = drs.getResumo(new GetResumo(a_mysession.intValue(),Integer.parseInt(request.getParameter("cube")),-1));
	
	if (getresumo != null){
	
		Atributo[] atributos = getresumo.getResumoOrdemDados();
		boolean claroEscuro = true;
		//System.out.println("j atributos.length: " + atributos.length);
		if(atributos != null){
			for (int j=0; j < atributos.length; j++){
				
				Atributo atributo = atributos[j];
				%>
				
				<div class="DragBox" id="Item<%= j + 1 %>" overClass="OverDragBox" dragClass="DragDragBox">
				
					<%= atributo.getCampoNome().toUpperCase() %>
					<input type="hidden" name="<%= atributo.getIdObjeto() %>" id="<%= atributo.getIdObjeto() %>" value="<%= j + 1 %>"/>
				
				</div>
				
				<%		
				
			}//for
		}//if
	
	}//if
	
}//if

//</div>
%>

</div>

</form>

<div style="position:left; text-align: right">
	<input type="button" value="Salvar" onclick="salvarOrdenacaoDados()">
</div>

<script>

function configurarContainerOrdemDados(){

	//alert("oi");
	// Create our helper object that will show the item while dragging
	dragHelper = document.createElement('DIV');
	dragHelper.style.cssText = 'position:absolute;display:none;';

	//alert(document.getElementById('DragContainer1'));
	
	
	CreateDragContainer(document.getElementById('DragContainerOrdenarDados'));
	elementoContainerOrdemDados = document.getElementById('DragContainerOrdenarDados');
	
	//CreateDragContainer(document.getElementById('div_ordem_campos'));
	//elementoContainerOrdemDados = document.getElementById('div_ordem_campos');
	
	document.body.appendChild(dragHelper);

	//alert("Oi Fim");
}

function salvarOrdenacaoDados()
{
	var frm = document.getElementById('frm_salvarOrdemDados');
	atualizarOrdemDadosObjetosDrag();
	frm.submit();
}

function vars()
{
	var divv = DragDrops[0];
	//alert(lastTarget);
	alert(top);
	top.location='http://192.168.0.10:8080/prjClienteWebDRS/';
	
}

</script>



