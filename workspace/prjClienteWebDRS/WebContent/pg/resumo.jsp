<%@ page import="java.util.*,java.io.BufferedReader,java.io.FileReader,org.globus.drs.stubs.Cube.*,org.globus.drs.stubs.Cube.service.*,org.globus.drs.stubs.Cube.bindings.*,org.apache.axis.message.addressing.Address,org.apache.axis.message.addressing.EndpointReferenceType" %>

<%!
	//Integer a_mysession;

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
%>
	
<%

String cubeIndexEntry = request.getParameter( "cubeIndexEntry" );

Integer a_mysession = (Integer)session.getAttribute( "a_mysession" );

if (a_mysession == null){
	pageContext.forward("/tree/index.jsp");
}



DRSServiceAddressingLocator locator = new DRSServiceAddressingLocator();

try {


	//if( request.getParameter("cube") != null){
	String serviceURI = getServicURI("drsservice"); //"http://eingrid002.unigranrio.br:8443/wsrf/services/cube/DRS";
	// Create endpoint reference to service
	EndpointReferenceType endpoint = new EndpointReferenceType();
	endpoint.setAddress(new Address(serviceURI));
	// Get PortType
	DRSPortType drs = locator.getDRSPortTypePort(endpoint);
	request.setAttribute("porttypedrs",drs);

%>


<html>
<head>
<script>
//alert('sessão <%= session.getAttribute( "a_mysession" ) +" e cubo "+ request.getParameter("cube") %> em Resumo');
</script>
<style>

body, table {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10px;
	color: #000000;
}

.menu {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight: normal;
	color: #000033;
	background-color: #FFFFFF;
	border-right: 1px solid #000000;
	border-top: 1px solid #000000;
	border-bottom: 1px solid #000000;
	padding: 5px;
	cursor: hand;
}

.menu-sel {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 14px;
	font-weight: bold;
	color: #000033;
	background-color: #CCCCCC;
	border-right: 1px solid #000000;
	border-top: 1px solid #000000;
	padding: 5px;
	cursor: hand;
}

.tb-conteudo {
	border-right: 1px solid #000000;
	border-bottom: 1px solid #000000;
}

.conteudo {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10px;
	font-weight: normal;
	color: #000033;
	background-color: #CCCCCC;
	padding-left: 10px;
	padding-top: 15px;
	padding-right: 10px;
	padding-bottom: 15px;
	
	width: 98%
	height: 460px;
}
</style>

<script language="JavaScript">

	function stAba(menu,conteudo)
	{
		this.menu = menu;
		this.conteudo = conteudo;
	}

	var arAbas = new Array();
	arAbas[0] = new stAba('td_resumo','div_resumo');
	arAbas[1] = new stAba('td_ordem_campos','div_ordem_campos');
	arAbas[2] = new stAba('td_ordem_dados','div_ordem_dados');

	function AlternarAbas(menu,conteudo)
	{
		for (i=0;i<arAbas.length;i++)
		{
			m = document.getElementById(arAbas[i].menu);
			m.className = 'menu';
			c = document.getElementById(arAbas[i].conteudo);
			c.style.display = 'none';
		}
		m = document.getElementById(menu);
		m.className = 'menu-sel';
		c = document.getElementById(conteudo);
		c.style.display = '';
	}


	//Atualizar tamanho das aba
	function redimensionarTamanhoAba(aba,objetoComparacao)
	{
		//alert("Reconfigurando...");
		var abadiv = document.getElementById(aba);
		var objcomparacao = document.getElementById(objetoComparacao);
		abadiv.style.height  = (objcomparacao.offsetHeight) + 30;
		//alert("Reconfigurado!");
	}	
</script>




<title></title>

<script type="text/javascript">

function removerElemento(cube,table,field)
{
	//alert(cube+' '+table+' '+field);
	top.opresumo.location='resumo.jsp?cube=' + cube + '&table=' + table + '&field='+ field +'&remover=s';
	top.options.atualizarRemover(cube,table,field);
}

function mostrarOpcoesAtributo(cuboEntry,cubo,tabela,campo,campoNome)
{
//alert('oi');
//alert(cubo+" "+tabela+" "+campo+" "+campoNome);
//alert("options.jsp?cube=" + cubo + "&table=" + tabela + "&field=" + campo + "&fieldName=" + campoNome);
top.options.location="options.jsp?cube=" + cubo + "&cubeIndexEntry="+ cuboEntry + "&table=" + tabela + "&field=" + campo + "&fieldName=" + campoNome;
}

function carregar()
{
	var ordemCampos = <%= request.getParameter("ordemcampos") %>;
	var ordemDados  = <%= request.getParameter("ordemdados") %>;

	if(ordemCampos == true){
		AlternarAbas('td_ordem_campos','div_ordem_campos');
		redimensionarTamanhoAba('div_ordem_campos', 'DragContainerOrdenarCampos');
		//alert("Operação realizada com sucesso");
	}
	else if(ordemDados == true){
		AlternarAbas('td_ordem_dados','div_ordem_dados');
		redimensionarTamanhoAba('div_ordem_dados', 'DragContainerOrdenarDados')
		//alert("Operação realizada com sucesso");
	}
	else{
		AlternarAbas('td_resumo','div_resumo');
	}

	configurarContainerOrdemCampos(); //Configura o div de drag-drop ordem campos
	configurarContainerOrdemDados();  //Configura o div de drag-drop ordem dados

	//alert('Meu container: \n' + elementoContainer.offsetHeight + "\ne\n:" + elementoContainer.style.height );
	
	//elementoContainer.parentNode.style.height = (elementoContainer.offsetHeight + 50);
}
</script>


<script type="text/javascript" src="resumo/drag_drop.js"></script>
<link rel="stylesheet" type="text/css" href="resumo/drag_drop.css">


</head>

<body bgcolor=#aaaaa onLoad="carregar();">

<center>

<table width="99%"  cellspacing="0" cellpadding="0"
border="0" style="border-left: 1px solid #000000; ">
	<tr>
		
		<td height="20" width="150" class="menu" id="td_resumo"
		onClick="AlternarAbas('td_resumo','div_resumo')">
			Resumo
		</td>
		
		<td height="20" width="150" class="menu" id="td_ordem_campos"
		onClick="AlternarAbas('td_ordem_campos','div_ordem_campos'); redimensionarTamanhoAba('div_ordem_campos', 'DragContainerOrdenarCampos')">
			Ordem Campos
		</td>
		
		<td height="20" width="150" class="menu" id="td_ordem_dados"
		onClick="AlternarAbas('td_ordem_dados','div_ordem_dados'); redimensionarTamanhoAba('div_ordem_dados', 'DragContainerOrdenarDados')">
			Ordem Dados
		</td>
		
		<td width="660" style="border-bottom: 1px solid #000000;">
			&nbsp;
		<td>
		
	</tr>
	<tr>
		<td  class="tb-conteudo" colspan="4">
		
			<div id="div_resumo" class="conteudo" style="display: none;">
				<jsp:include page="resumo/resumo_detalhes.jsp"></jsp:include>
			</div>
			
			<div id="div_ordem_campos" class="conteudo" style="display: none">
				<jsp:include page="resumo/resumoOrdemCampos.jsp"></jsp:include>
			</div>
			
			<div id="div_ordem_dados" class="conteudo" style="display: none">
				<jsp:include page="resumo/resumoOrdemDados.jsp"></jsp:include>
			</div>
		</td>
	</tr>
</table>

</center>

</body>
</html>

<%



	//}
	//System.out.println("teste");
	} catch (Exception e) {
	e.printStackTrace();
	out.println("Ocorreu um erro: '" + e.getMessage() + "'");
	}

%>