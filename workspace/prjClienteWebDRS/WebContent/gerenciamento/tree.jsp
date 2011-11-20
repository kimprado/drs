<%@ page import="java.util.*,org.apache.axis.message.addressing.Address,org.apache.axis.message.addressing.EndpointReferenceType,org.globus.cube.stubs.Cube.*,org.globus.cube.stubs.Cube.service.*,org.globus.index.stubs.Cube.*,org.globus.index.stubs.Cube.service.*" %>

<HTML>

<head>
<script>
//alert('oi , <%= session.getAttribute( "a_mysession" ) %> em Tree');
</script>
<title></title>

<!-- The xtree script file -->
<script src="../include/script/xtreeToCube.js"></script>

<script>

var windowResultado;

function enviarOpcoes()
{
	//ResultadoConsulta.jsp
	
	var pg =  '<%= "resultado/escolherTipoResultado.jsp?cube="+request.getParameter( "cube" )+"" %>'; // página a tratar o envio dos dados;
	try{
		if (windowResultado != null && windowResultado.document != null ){
			windowResultado.location=pg;
		}
		else{
			windowResultado = window.open(pg,"_blank","toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, width=820, height=500");
		}
	} catch (e) {
		windowResultado = window.open(pg,"_blank","toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, width=820, height=500");
	}

	windowResultado.focus();
}
</script>


<!-- Modify this file to change the way the tree looks -->
<link type="text/css" rel="stylesheet" href="../include/style/xtree.css">
</head>

<BODY bgcolor=lightyellow >


<%
	CubeServiceAddressingLocator locator = new CubeServiceAddressingLocator();

		try {

				String serviceURICube = request.getParameter( "cubeURI" ); // captura a uri do do cubo. Não posso usar "cb.getUri()" pois este objeto retornaria 'null'. Necessário usar PortTypeCubeIndex.getCubeEntry
				String cubeIndexEntry = request.getParameter( "cubeIndexEntry" );

				// Create endpoint reference to service
				EndpointReferenceType endpointCube = new EndpointReferenceType();
				endpointCube.setAddress(new Address(serviceURICube));
				// Get PortType
				CubePortType cube = locator.getCubePortTypePort(endpointCube);

				CubeMetadataResponse cubeMD = cube.getCubeMetaData(Integer.parseInt(request.getParameter( "cube" )));


				FatoMetaData fatoMD = cubeMD.getFatoMetaData();

				out.println( "<table border='1' WIDTH='100%' CELLSPACING='2' CELLPADDING='6'>" );
				out.println( "<tr><td>");
				out.println( "<table border='0' >");
				out.println( "<tr><td ALIGN='left'><img src='images/cube001.png' Alt='Assunto' border='0'></td>" );
				out.println( "<td ALIGN='left'><font size=5 color=#303080>"+cubeMD.getName()+"</font></td></tr>" );

				out.println( "<tr><td></td>  <td colspan=1>" );

				out.println("<script>"); // início do script e do método
				//out.println("parent.document.getElementById(\"formularioFrame\").src=\"gerenciamento/formularioInicio.html\"");
				out.println("if (document.getElementById) {");
				out.println( "var fato = new WebFXTree('"+fatoMD.getName()+"');" ); // insere o fato(assunto) do Cubo

				if (fatoMD.getDimensaoMetaData() != null){
				DimensaoMetaData dimMD;
				for(int j=0; j < fatoMD.getDimensaoMetaData().length; j++){
					dimMD = fatoMD.getDimensaoMetaData(j);
					out.println( "var dimensao = new WebFXTreeItem('"+dimMD.getName()+"');" ); // insere o fato(assunto) do Cubo
					out.println( "fato.add(dimensao);" ); // adiciona o objeto dimensao ao fato
					for(int a=0; a < dimMD.getFieldMetaData().length; a++){
						FieldMetaData fdmd = dimMD.getFieldMetaData(a);
						if ( !(fdmd.isPrimaryKey()) & !(fdmd.isForeignKey()) & !fdmd.getName().toUpperCase().matches("ID_.*") ){ //Se Não Fizer parte da Chave
							out.println( "dimensao.add(new WebFXTreeItem('"+ fdmd.getName() +"',null,'formularioFrame'));" ); // adiciona o objeto fild(campos) ao fato
						}
					} // for atributo das dimensoes
				} // for dimensao
				}

				for(int j=0; j <cubeMD.getFatoMetaData().getFieldMetaData().length; j++){
					FieldMetaData fdmd = fatoMD.getFieldMetaData(j);
					if (!fdmd.isForeignKey() & !fdmd.isPrimaryKey() && !fdmd.getName().toUpperCase().matches("ID_.*") ){ //Se Não Fizer parte da Chave Estrangeira
						out.println( "fato.add(new WebFXTreeItem('"+ fdmd.getName() +"',null,'formularioFrame'));" ); // adiciona o objeto fild(campos) ao fato
					}
				}

				out.println("document.write(fato);} //if");  // exibe o objeto na página
				
				out.println("</script>");
				out.println("</td></tr></table>");
				out.println( "</td>");
				out.println( "</tr>");
				out.println( "</table>");

			out.println("<script>top.opresumo.location='resumo.jsp?cube=" + request.getParameter( "cube" ) + "&cubeIndexEntry="+ cubeIndexEntry + "'</script>");

		} catch (Exception e) {
			e.printStackTrace();
			out.println("erro na aplicação: " + e.getCause());
		}
%>
</BODY>
</HTML>