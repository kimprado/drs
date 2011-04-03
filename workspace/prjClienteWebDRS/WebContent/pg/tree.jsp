<%@ page import="java.util.*,org.apache.axis.message.addressing.Address,org.apache.axis.message.addressing.EndpointReferenceType,org.globus.cube.stubs.Cube.*,org.globus.cube.stubs.Cube.service.*,org.globus.index.stubs.Cube.*,org.globus.index.stubs.Cube.service.*" %>

<HTML>

<head>
<script>
//alert('oi , <%= session.getAttribute( "a_mysession" ) %> em Tree');
</script>
<title></title>

<!-- The xtree script file -->
<script src="xtreeToCube.js"></script>

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
<link type="text/css" rel="stylesheet" href="xtree.css">
</head>

<BODY bgcolor=lightyellow >


<%
	CubeServiceAddressingLocator locator = new CubeServiceAddressingLocator();

		try {



			//if (cubeList.getCubeEntry() != null){ //faz o teste da quantidade de entradas no índice



					String serviceURICube = request.getParameter( "cubeURI" ); // captura a uri do do cubo. Não posso usar "cb.getUri()" pois este objeto retornaria 'null'. Necessário usar PortTypeCubeIndex.getCubeEntry
					String cubeIndexEntry = request.getParameter( "cubeIndexEntry" );

					// Create endpoint reference to service
					EndpointReferenceType endpointCube = new EndpointReferenceType();
					endpointCube.setAddress(new Address(serviceURICube));
					// Get PortType
					CubePortType cube = locator.getCubePortTypePort(endpointCube);

//out.println( "oi");
					CubeMetadataResponse cubeMD = cube.getCubeMetaData(Integer.parseInt(request.getParameter( "cube" )));


					FatoMetaData fatoMD = cubeMD.getFatoMetaData();

					%>

					<table border='2' WIDTH='100%' CELLSPACING='2' CELLPADDING='6'>
					<tr>
					<td>
					<form>
					<center><INPUT TYPE='button' VALUE='EXECUTAR CONSULTA' onclick="enviarOpcoes()"></center>
					</form>
					</td>
					</tr>
					</table>

					<%
					//out.println( "<form><Input type='button' value='EXECUTAR CONSULTA' onclick=\"enviarOpcoes()\"> </form>" );
					out.println( "<table border='2' WIDTH='100%' CELLSPACING='2' CELLPADDING='6'>" );
					out.println( "<tr><td>");
					out.println( "<table border='0' >");
					out.println( "<tr><td ALIGN='left'><img src='images/teste/cube001.png' Alt='Assunto' border='0'></td>" );
					out.println( "<td ALIGN='left'><font size=5 color=#303080>"+cubeMD.getName()+"</font></td></tr>" );


					//out.println("var tree = new WebFXTree('Cubos','/cube/index.jsp?cube=1',null);"); // declara a "raíz" da árvore ou o "nodo raiz"





					out.println( "<tr><td></td>  <td colspan=1>" );

					out.println("<script>"); // início do script e do método
					out.println("parent.document.getElementById(\"options\").src=\"pg/optionsInicio.html\"");
					out.println("if (document.getElementById) {");
					out.println( "var fato = new WebFXTree('"+fatoMD.getName()+"');" ); // insere o fato(assunto) do Cubo
					//out.println( "cube.add(fato);" ); // adiciona o objeto fato ao cubo


//cubeIndex.getCubeEntry(Integer.parseInt(request.getParameter( "cube" ))).getCubeEntry();
					if (fatoMD.getDimensaoMetaData() != null){
					DimensaoMetaData dimMD;
					for(int j=0; j < fatoMD.getDimensaoMetaData().length; j++){
						dimMD = fatoMD.getDimensaoMetaData(j);
						out.println( "var dimensao = new WebFXTreeItem('"+dimMD.getName()+"');" ); // insere o fato(assunto) do Cubo
						out.println( "fato.add(dimensao);" ); // adiciona o objeto dimensao ao fato
						for(int a=0; a < dimMD.getFieldMetaData().length; a++){
							FieldMetaData fdmd = dimMD.getFieldMetaData(a);
							if ( !(fdmd.isPrimaryKey()) & !(fdmd.isForeignKey()) ){ //Se Não Fizer parte da Chave
								out.println( "dimensao.add(new WebFXTreeItem('"+ fdmd.getName() +"','options.jsp?cube="+request.getParameter( "cube" )+"&table="+ dimMD.getKey() +"&field="+ fdmd.getKey() +"&fieldName="+ fdmd.getName() +"&cubeIndexEntry="+ cubeIndexEntry +"&cubeURI="+ serviceURICube +"','options'));" ); // adiciona o objeto fild(campos) ao fato
							}
						} // for atributo das dimensoes
					} // for dimensao
					}

					for(int j=0; j <cubeMD.getFatoMetaData().getFieldMetaData().length; j++){
						//if (fatoMD.getFieldMetaData(j).isForeignKey() != true){ //Se Não Fizer parte da Chave Estrangeira
						if (!fatoMD.getFieldMetaData(j).isForeignKey() & !fatoMD.getFieldMetaData(j).isPrimaryKey() ){ //Se Não Fizer parte da Chave Estrangeira
							out.println( "fato.add(new WebFXTreeItem('"+ fatoMD.getFieldMetaData(j).getName() +"','options.jsp?cube="+request.getParameter( "cube" )+"&table=-1&field="+ fatoMD.getFieldMetaData(j).getKey() +"&fieldName="+ fatoMD.getFieldMetaData(j).getName() +"&cubeIndexEntry="+ cubeIndexEntry +"&cubeURI="+ serviceURICube +"','options'));" ); // adiciona o objeto fild(campos) ao fato
						}
					}

				//} // for



				out.println("document.write(fato);} //if");  // exibe o objeto na página
				//out.println("parent.document.getElementById(\"options\").src=\"pg/optionsInicio.html\"");
				//out.println("alert('reiniciei as options')");
				//out.println("parent.document.getElementById(\"options\").write('Hello Worlds!') ");
				out.println("</script>");
				out.println("</td></tr></table>");
				out.println( "</td>");
				out.println( "</tr>");
				out.println( "</table>");


				/*<p>
				Returns the id of the selected item (if any)<br/>
				<input type="button" value="alert(tree.getSelected().id);" onclick="if (tree.getSelected()) { alert(tree.getSelected().id); }" style="width: 245px;">
				</p>*/



			/*} else{   //caso não haja entrada de cubo(s) no índice é mostrada uma entrada vazia
				out.println("<script>\nif (document.getElementById) {");
				out.println("var tree = new WebFXTree('Cubos');");
				out.println("tree.add(new WebFXTreeItem('Não há entradas de cubos'));");
				out.println("document.write(tree);}</script>");
			}*/

			%>

			<!--
			</SELECT> <P>
			<INPUT TYPE=SUBMIT VALUE="Pesquisar Cubo">
			</FORM>
			</TD>


			<TD>
			<TABLE border=0 width=450><TR ALIGN=CENTER><TD valign=top>
			-->

			<%

			/*
			CubeServiceAddressingLocator locatorCube = new CubeServiceAddressingLocator();
			CubeEntry cubeEntry = cubeIndex.getCubeEntry(Integer.parseInt(request.getParameter( "cube" ))).getCubeEntry();
			String serviceURICube = new String(cubeEntry.getUri()); //"http://eingrid002.unigranrio.br:8443/wsrf/services/cube/Cube";

			// Create endpoint reference to service
			EndpointReferenceType endpointCube = new EndpointReferenceType();
			endpointCube.setAddress(new Address(serviceURICube));
			// Get PortType
			CubePortType cube = locatorCube.getCubePortTypePort(endpointCube);
			CubeMetadataResponse cubeMD = cube.getCubeMetaData(cubeEntry.getIndex());
			FatoMetaData fatoMD = cubeMD.getFatoMetaData();


			out.println("FATO</TD></TR>");
			out.println("<TR ALIGN=LEFT><TD>"+fatoMD.getName()+"</TD></TR>");


			for(int j=0; j <cubeMD.getFatoMetaData().getFieldMetaData().length; j++){
				out.println("<TR ALIGN=RIGHT><TD>" + fatoMD.getFieldMetaData(j).getName() + "</TD></TR>");
			}

			out.println("<TR ALIGN=CENTER><TD valign=top>DIMENSÃO</TD></TR>");

			DimensaoMetaData dimMD;
			for(int j=0; j < fatoMD.getDimensaoMetaData().length; j++){
				dimMD = fatoMD.getDimensaoMetaData(j);
				out.println("<TR ALIGN=LEFT><TD>"+dimMD.getName()+"</TD></TR>");
				for(int i=0; i < dimMD.getFieldMetaData().length; i++){
					out.println("<TR ALIGN=RIGHT><TD>" + dimMD.getFieldMetaData(i).getName() + "</TD></TR>");
				}
			}
			*/


			%>

			<!--
			</TD>
			</TR>
			</TABLE>
			</TD>

			<TD border width=350>Critérios de Pesquisa:
			</TD>

			</TD>
			</TR>
			</TABLE>
			<p>
			 -->
		<%

			out.println("<script>top.opresumo.location='resumo.jsp?cube=" + request.getParameter( "cube" ) + "&cubeIndexEntry="+ cubeIndexEntry + "'</script>");
			/*out.println("<script>parent.document.getElementById('opresumo').window.location.reload()</script>");
			out.println("<script>parent.document.getElementById('opresumo').src='pg/resumo.jsp?cube=" + request.getParameter( "cube" ) + "'</script>");
			out.println("<script>parent.document.getElementById('opresumo').window.location.reload()</script>");*/

		} catch (Exception e) {
			e.printStackTrace();
			out.println("erro na aplicação: " + e.getCause());
		}
%>
</BODY>
</HTML>