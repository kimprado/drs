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
		return "";
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

		return "";
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
	
	public String getOrdenarDirecaoFormatado(boolean ordenar, int ordenarDirecao ){
		if(ordenar){
			switch (ordenarDirecao) {
				case 1:	return new String("sim - Crescente");
				case 2:	return new String("sim - Decrescente");
			}
			return "";
		}
		else{
			return "não";
		}
		
	}

%>

<table RULES='NONE' id='tbresumo' WIDTH='99%'  BORDER='0' CELLSPACING='1' CELLPADDING='1' bgcolor='#E5EDFF'>
	<tr>
	
		<td ><font STYLE='font-size:10pt' color='black'>Cubo</font></td>
		<td ><font STYLE='font-size:10pt' color='black'>Fato/Dimensão</font></td>
		<td ><font STYLE='font-size:10pt' color='black'>Atributo</font></td>
		<td ><font STYLE='font-size:10pt' color='black'>Mostrar</font></td>
		<td ><font STYLE='font-size:10pt' color='black'>Agrupar</font></td>
		<td ><font STYLE='font-size:10pt' color='black'>Ordenar</font></td>
		<td ><font STYLE='font-size:10pt' color='black'>Parâmetros</font></td>
		
	</tr>

<%

if ( request.getParameter("cube") != null ){

	//String cubeIndexEntry = request.getParameter( "cubeIndexEntry" );
	Integer a_mysession = (Integer)session.getAttribute( "a_mysession" );
	DRSPortType drs = (DRSPortType)request.getAttribute("porttypedrs");
	ResumoResponse getresumo = drs.getResumo(new GetResumo(a_mysession.intValue(),Integer.parseInt(request.getParameter("cube")),-1));
	
	if (getresumo != null){
	
		Atributo[] atributos = getresumo.getResumoAtributos();
		boolean claroEscuro = true;
		for (int j=0; j < atributos.length; j++){
			Atributo atributo = atributos[j];
	
			if (claroEscuro == true){
				out.println("<tr bgcolor='#ADD8E6'  onclick='mostrarOpcoesAtributo(" + atributo.getCubeEntry() + "," + atributo.getCubo() + "," + atributo.getTabela() + "," + atributo.getCampo() + ",\"" + atributo.getCampoNome() + "\")'>");
				claroEscuro = false;
			}else{
				out.println("<tr bgcolor='#C0EFFF'  onclick='mostrarOpcoesAtributo(" + atributo.getCubeEntry() + "," + atributo.getCubo() + "," + atributo.getTabela() + "," + atributo.getCampo() + ",\"" + atributo.getCampoNome() + "\")'>");
				claroEscuro = true;
			}
	
			out.println("<td><font face='arial' STYLE='font-size:9pt' color='black'>" + atributo.getCuboNome() + "</font></td>");
			out.println("<td align='center'><font face='arial' STYLE='font-size:9pt' color='black'>" + atributo.getTabelaNome() + "</font></td>");
			out.println("<td><font face='arial' STYLE='font-size:9pt' color='black'>" + atributo.getCampoNome() + "</font></td>");
			out.println("<td><font face='arial' STYLE='font-size:9pt' color='black'>");
			out.println( toTrueFalse(atributo.isMostrar()) + ( atributo.isMostrar() ? " - " + atributo.getMostrarPosicao() : "" ) );
	
			if (atributo.getAgSel() != null){
				for (int i=0; i < atributo.getAgSel().length; i++){
					AgSel agsel = atributo.getAgSel(i);
					//out.println("oi" + agsel.getAgregacao());
					out.println( "<br>" + toStrAgregacao(atributo.getCampoNome(),agsel.getAgregacao()) );
				}
			}
			out.println("</font></td>");
	
	
			out.println("<td><font face='arial' STYLE='font-size:9pt' color='black'>");
			out.println( toTrueFalse(atributo.isAgrupar()) );
			if (atributo.getAgHav() != null){
				for (int i=0; i < atributo.getAgHav().length; i++){
					AgHav aghav = atributo.getAgHav(i);
					//out.println("oi" + agsel.getAgregacao());
					out.println( "<br>" + toStrLogico(aghav.getLogico()) + " " + toStrAgregacao(atributo.getCampoNome(),aghav.getAgregacao()) + " "+toStrComparacao(aghav.getComparacao()) +" "+ aghav.getValor() );
				}
			}
			out.println("</font></td>");
			
			out.println("<td><font face='arial' STYLE='font-size:9pt' color='black'>" + getOrdenarDirecaoFormatado( atributo.isOrdenar() ,atributo.getOrdenarDirecao() ) + "</font></td>");
	
			out.println("<td><font face='arial' STYLE='font-size:9pt' color='black'>");
			if (atributo.getOpcoes() != null){
				//out.println("atributo.getOpcoes().length: "+atributo.getOpcoes().length);
				for (int i=0; i < atributo.getOpcoes().length; i++){
					Opcoes op = atributo.getOpcoes(i);
					out.println(toStrLogico(op.getLogico()) +" " + atributo.getCampoNome() + " "+toStrComparacao(op.getComparacao()) +" "+ op.getValor() + "<br>");
				}
			}
			out.println("</font></td>");
			//out.println("<td><input type=button value='remover' onclick='removerElemento(" + atributo.getCubo() + "," + atributo.getTabela() + "," + atributo.getCampo() + ")'></td>"); <td WIDTH='10%'>Ação</td>
	
			out.println("</tr>");
		}//for
	
	}//if
	
}//if
%>



</table>