 <%@ page import="java.util.*,java.io.BufferedReader,java.io.FileReader,org.apache.axis.message.addressing.Address,org.apache.axis.message.addressing.EndpointReferenceType,org.globus.cube.stubs.Cube.service.*,org.globus.cube.stubs.Cube.*,org.globus.index.stubs.Cube.*,org.globus.index.stubs.Cube.service.*,org.globus.drs.stubs.Cube.*,org.globus.drs.stubs.Cube.bindings.*,org.globus.drs.stubs.Cube.service.*" %>

<%!

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


<HTML>
<head>
<title>DRS - Resultado da Consulta</title>
<script type="text/javascript">
//window.focus();
</script>
</head>
<BODY bgcolor='#FFFFFF'>

<%
		try {

			Integer a_mysession = (Integer)session.getAttribute( "a_mysession" );
			
			if (a_mysession == null){
			//	pageContext.forward("/tree/index.jsp");
			}

			DRSServiceAddressingLocator locatorDRS = new DRSServiceAddressingLocator();
			EndpointReferenceType endpointDRS = new EndpointReferenceType();
			//endpointDRS.setAddress(new Address("http://eingrid005.unigranrio.br:8443/wsrf/services/cube/DRS"));
			endpointDRS.setAddress(new Address(getServicURI("drsservice"))); //DRS;
			DRSPortType drs = locatorDRS.getDRSPortTypePort(endpointDRS);


			CubeServiceAddressingLocator locatorCube = new CubeServiceAddressingLocator();
			EndpointReferenceType endpointCube = new EndpointReferenceType();
			//String serviceURIcube = new String("http://eingrid002.unigranrio.br:8443/wsrf/services/cube/Cube");
			String serviceURIcube = new String(getServicURI("cubeservice")); //CUBE
			endpointCube.setAddress(new Address(serviceURIcube));

			CubePortType cube = locatorCube.getCubePortTypePort(endpointCube);


			String sql = drs.getSQL(a_mysession.intValue()); //passa o valor correspondente da consulta da sessão
			
			System.out.println("\nConsulta Submetida:\n"+sql+"");
			
			out.println("<TABLE id='tbPagina' WIDTH=780 BGCOLOR=#ffffff>");
			out.println("<tr><td ALIGN='CENTER'><H3>Resultado da Pesquisa</H3></td></tr>");
			//out.println("<tr><td></br><font size=3 color='blue'>SQL Elaborada: \"" + sql + "\"</font><br><br></td</tr>");


			if ( sql != null){ // Se a consulta pode ser formada
			System.out.println("meu parametro cube: " + request.getParameter("cube"));
			ExecuteQueryResponse exquery = cube.executeQuery(new ExecuteQuery(Integer.parseInt(request.getParameter("cube")),sql));

			String columns ="";// = new String(exquery.getResultColumnName(0));
			//out.println("<TABLE BORDER=3 CELLSPACING=1 CELLPADDING=6> <tr> <td ALIGN=LEFT BGCOLOR=#555555>");

			out.println("<tr><td><TABLE id='tbResultado' WIDTH=780 border=0 BGCOLOR=#ffffff CELLPADDING=1 CELLSPACING=1>");
			out.println("<tr>");
			for (int k=0; k < exquery.getResultColumnName().length; k++){
				//columns = columns+"    "+exquery.getResultColumnName(k);
				%>
				<td ALIGN=LEFT><b> <%= exquery.getResultColumnName(k).toUpperCase() %> <b></td>
				<%

			}
			out.println("</tr>\n");

			//out.println(columns);
			//out.println("</br>------------------------------------</br>");

			if (exquery.getColumnResponse() != null){ //Testa se o resultado possui alguma tupla

			for(int v=0;v < exquery.getColumnResponse(0).getColumn().length; v++){
				out.println("<tr>\n<td ALIGN=LEFT BGCOLOR=#FFFFFF>");

				if (! (exquery.getColumnResponse(0).getColumn(v) == null ))
					out.print(exquery.getColumnResponse(0).getColumn(v));
				else
					out.print("\nvazio");
				out.println("\n</td>");

				for(int u = 1; u < exquery.getResultColumnName().length; u++){
					out.println("\n<td ALIGN=LEFT BGCOLOR=#FFFFFF>");
					if (! (exquery.getColumnResponse(u).getColumn(v) == null ))
						out.print( exquery.getColumnResponse(u).getColumn(v));
					else
						out.print("\nvazio");
					out.println("\n</td>");
				}//for

				out.println("</tr>\n\n");
			}//for

			}else{ //if mostrar tuplas
				out.println("<tr><td><font size=2 color='#6C6797'>pesquisa sem resultado</font> </td></tr>");
			}


			out.println("</TABLE></td></tr>"); //tbResultado
			out.println("<tr><td></br><font size=2 color='blue'>SQL Elaborada: \"" + sql + "\"</font><br><br></td</tr>");
			out.println("</TABLE>"); //tbPagina
			//out.println("</br>\nSQL is: \"" + sql + "\"");
			
			//System.out.println("\nConsulta Submetida:\n"+sql+"");
			}else{ //if
				out.println("SQL gerada não pode ser processada");
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.println("erro");
			out.println(e.getMessage());
			out.println(e.getCause());
		}
%>

</BODY>
</HTML>