 <%@ page import="java.util.*,java.io.BufferedReader,java.io.FileReader,org.apache.axis.message.addressing.Address,org.apache.axis.message.addressing.EndpointReferenceType,org.globus.cube.stubs.Cube.*, org.globus.cube.stubs.Cube.service.*,org.globus.index.stubs.Cube.*,org.globus.index.stubs.Cube.service.*,org.globus.drs.stubs.Cube.*,org.globus.drs.stubs.Cube.bindings.*,org.globus.drs.stubs.Cube.service.*" %>

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


<%
Integer a_mysession = (Integer)session.getAttribute( "a_mysession" );

if (a_mysession == null){
	pageContext.forward("/tree/index.jsp");
}

%>


<HTML>
<head>
</head>
<BODY bgcolor=#E5EDFF>
<%= new Date() %> <h3><center>Requisição</center></h3>

<%
		try {
			Enumeration contar = request.getParameterNames(); // somente para saber a qtd de parâmetros passados

			int count=0; //contador PADRÃO
			while(contar.hasMoreElements()){
				contar.nextElement();
				count++;
			}
			int enviados = count;

			String[] opcoesE = new String[count]; // Responsável por armazenar os parâmetros iniciados com a Letra "E".

			Enumeration e = request.getParameterNames();
			
			count=0;
			int countElementeE=0; // Essencial para determinar a qtd de comparações escolhidas
			char elementeE = new String("E").charAt(0);
			while(e.hasMoreElements()){
				String parametro = (String)e.nextElement();
				out.println("parametro: "+ parametro+"<br>");
				if (elementeE == parametro.charAt(0) ){ // Testa se o parâmetro atual se inicia com "E".
					opcoesE[countElementeE] = parametro; // Atribui o valor
					countElementeE++;
					
				}

				count++;
			}

			Opcoes[] op = new Opcoes[countElementeE];
			for(int i=0; i < countElementeE ; i++){  // Ordenação das opcões
				for(int j=i+1; j< countElementeE; j++){
					if (Integer.parseInt(String.copyValueOf(opcoesE[i].toCharArray(), 1, opcoesE[i].length()-1)) > Integer.parseInt(String.copyValueOf(opcoesE[j].toCharArray(), 1, opcoesE[j].length()-1))  ){
						String aux = opcoesE[i];
						opcoesE[i] = opcoesE[j];
						opcoesE[j] = aux;
					}
				}
			}

			if ( (request.getParameter("E1") != null) || (request.getParameter("G1") != null) || (request.getParameter("I1") != null) ){

				op[0] = new Opcoes( Integer.parseInt(request.getParameter("G1")), Integer.parseInt(request.getParameter("E1")), request.getParameter("I1") );

			}else{
				op = new Opcoes[0];
			}
			
			out.println("countElementeE: "+ countElementeE);
			for(int i=1; i < countElementeE ; i++){
				String indiceAtual = opcoesE[i].copyValueOf(opcoesE[i].toCharArray(), 1, opcoesE[i].length()-1);
				if ( (request.getParameter("E"+indiceAtual) != null) || (request.getParameter("G"+indiceAtual) != null) || (request.getParameter("I"+indiceAtual) != null) ){
					out.println("Ho eu aqui");
					op[i] = new Opcoes(Integer.parseInt(request.getParameter("G"+indiceAtual)),Integer.parseInt(request.getParameter("E"+indiceAtual)),request.getParameter("I"+indiceAtual));
				}
			}

			out.println("<br><br>");
			
			DRSServiceAddressingLocator locatorDRS = new DRSServiceAddressingLocator();
			EndpointReferenceType endpointDRS = new EndpointReferenceType();
			endpointDRS.setAddress(new Address(getServicURI("drsservice"))); //DRS
			DRSPortType drs = locatorDRS.getDRSPortTypePort(endpointDRS);

			boolean mostrar;
			boolean agrupar;
			boolean ordenar;
			int ordenarDirecao = 0;
			
			///*
			out.println("Vou verificar mostrar e agrupar");
			
			out.println("<br>mostrar: "+request.getParameter("mostrar"));
			if(request.getParameter("mostrar") != null && request.getParameter("mostrar").equals(new String("1"))){
				//if (request.getParameter("mostrar").equals(new String("s"))){
				mostrar = true;
				//}
			}else {
				mostrar = false;
			}
			
			out.println("<br>agrupar: "+request.getParameter("agrupar"));
			if(request.getParameter("agrupar") != null && request.getParameter("agrupar").equals(new String("1"))){
				agrupar = true;
			}else {
				agrupar = false;
			}
			
			out.println("<br>ordenar: "+request.getParameter("ordenar"));
			if(request.getParameter("ordenar") != null && request.getParameter("ordenar").equals(new String("1"))){
				ordenar = true;
			}else {
				ordenar = false;
			}//*/
			
			if( request.getParameter("ordenardirecao") != null  ){
				ordenarDirecao = Integer.valueOf( request.getParameter("ordenardirecao") ); 
			}
			
			System.out.println("Ordenar direcao é: " + ordenarDirecao);
			
			//AgSel[] opAgSelect = new AgSel[0];
			//*
			String[] opcoesM = new String[5];
			e = request.getParameterNames();

			int countElementeM=0; // Essencial para determinar a qtd de Agrupamentos M
			char elementeM = new String("M").charAt(0);

			while(e.hasMoreElements()){
				String parametro = (String)e.nextElement();

				if (elementeM == parametro.charAt(0) ){ // Testa se o parâmetro atual se inicia com "M".
					opcoesM[countElementeM] = parametro; // Atribui o valor
					countElementeM++;
				}
			}

			AgSel[] opAgSelect = new AgSel[countElementeM];
			for(int i=0; i < countElementeM ; i++){  // Ordenação das opcões
				for(int j=i+1; j< countElementeM; j++){
				//out.print("oi: "+String.copyValueOf(opcoesM[i].toCharArray(), 1, opcoesM[i].length()-1));
					if (Integer.parseInt(String.copyValueOf(opcoesM[i].toCharArray(), 1, opcoesM[i].length()-1)) > Integer.parseInt(String.copyValueOf(opcoesM[j].toCharArray(), 1, opcoesM[j].length()-1))  ){
						String aux = opcoesM[i];
						opcoesM[i] = opcoesM[j];
						opcoesM[j] = aux;

					}
				}
			}

			if ( request.getParameter("M1") != null ){
				opAgSelect[0] = new AgSel( Integer.parseInt(request.getParameter("M1")));
			}else{
				opAgSelect = new AgSel[0];
			}
			for(int i=1; i < countElementeM ; i++){
				String indiceAtual = opcoesM[i].copyValueOf(opcoesM[i].toCharArray(), 1, opcoesM[i].length()-1);
				if ( request.getParameter("M"+indiceAtual) != null ){
					opAgSelect[i] = new AgSel(Integer.parseInt(request.getParameter("M"+indiceAtual)));
				}
			}//*/


			//INICIO AGREGAÇÃO HAVING

			String[] opcoesP = new String[enviados];

			e = request.getParameterNames();

			int countElementeP=0; // Essencial para determinar a qtd de Agrupamentos M
			char elementeP = new String("P").charAt(0);

			while(e.hasMoreElements()){
				String parametro = (String)e.nextElement();

				if (elementeP == parametro.charAt(0) ){ // Testa se o parâmetro atual se inicia com "M".
					opcoesP[countElementeP] = parametro; // Atribui o valor
					countElementeP++;
				}
			}

			AgHav[] opAgHaving = new AgHav[countElementeP];
			for(int i=0; i < countElementeP ; i++){  // Ordenação das opcões
				for(int j=i+1; j< countElementeP; j++){
				//out.print("oi: "+String.copyValueOf(opcoesM[i].toCharArray(), 1, opcoesM[i].length()-1));
					if (Integer.parseInt(String.copyValueOf(opcoesP[i].toCharArray(), 1, opcoesP[i].length()-1)) > Integer.parseInt(String.copyValueOf(opcoesP[j].toCharArray(), 1, opcoesP[j].length()-1))  ){
						String aux = opcoesP[i];
						opcoesP[i] = opcoesP[j];
						opcoesP[j] = aux;

					}
				}
			}

			if ( (request.getParameter("P1") != null) || (request.getParameter("Q1") != null) || (request.getParameter("R1") != null) || (request.getParameter("S1") != null) ){
				opAgHaving[0] = new AgHav(Integer.parseInt(request.getParameter("Q1")),Integer.parseInt(request.getParameter("R1")), Integer.parseInt(request.getParameter("P1")), request.getParameter("S1") );
			}else{
				opAgHaving = new AgHav[0];
			}

			for(int i=1; i < countElementeP ; i++){
				String indiceAtual = opcoesP[i].copyValueOf(opcoesP[i].toCharArray(), 1, opcoesP[i].length()-1);
				if ( (request.getParameter("P"+indiceAtual) != null) || (request.getParameter("Q"+indiceAtual) != null) || (request.getParameter("R"+indiceAtual) != null) || (request.getParameter("S"+indiceAtual) != null) ){
					opAgHaving[i] = new AgHav( Integer.parseInt(request.getParameter("Q"+indiceAtual)), Integer.parseInt(request.getParameter("R"+indiceAtual)) , Integer.parseInt(request.getParameter("P"+indiceAtual)), request.getParameter("S"+indiceAtual) );
				}
			}

			//FIM AGREGAÇÃO HAVING
			
			//Atributo att = new Atributo(opAgHaving,opAgSelect,agrupar,Integer.parseInt(request.getParameter("field")),null,a_mysession.intValue(),Integer.parseInt(request.getParameter("cubeIndexEntry")),Integer.parseInt(request.getParameter("cube")),null,mostrar,op,Integer.parseInt(request.getParameter("table")),null,request.getParameter( "cubeURI" ));// Atributo para ser adicionado ao DRS
			Atributo at = new Atributo();
			at.setCampo(Integer.parseInt(request.getParameter("field")));
			at.setConsulta(a_mysession.intValue());
			at.setCubeEntry(Integer.parseInt(request.getParameter("cubeIndexEntry")));
			at.setCubo(Integer.parseInt(request.getParameter( "cube" )));
			at.setTabela(Integer.parseInt(request.getParameter("table")));
			at.setUri(request.getParameter( "cubeURI" ));
			at.setAgHav(opAgHaving);
			at.setAgSel(opAgSelect);
			at.setAgrupar(agrupar);
			at.setMostrar(mostrar);
			at.setOrdenar(ordenar);
			at.setOrdenarPosicao(-1);
			at.setOrdenarDirecao(ordenarDirecao);
			at.setOpcoes(op);
			at.setMostrarPosicao(-1);
			
			//out.println("<br>Op "+op.length);
			//out.print("opAgSel: "+opAgSelect.length);
			//out.print("op: "+op.length);
			
			if ( (mostrar == false & agrupar == false & ordenar == false) && op.length == 0 && opAgSelect.length == 0 ){
				drs.removeCampo(new RemoveCampo(at));
				out.println("<br><br><b>Removi o atributo</b><br>");
				//out.println("<script>alert('Operação realizada: remoção')</script>");
			}else {
				if(drs.addCampo(new AddCampo(at)) == true){
					out.println("<br><br><b>Adicionei o atributo</b><br>");
					//out.println("<script>alert('Operação realizada: inclusão')</script>");
				}else {
					//out.println("<br>Não pude adicionar o atributo<br>");
				}//*/
			}
			//out.println("<script>window.location='resumo.jsp?cube=" + request.getParameter( "cube" ) + "&cubeIndexEntry=" + request.getParameter("cubeIndexEntry") +"'</script>");
			out.println("<script>top.opresumo.location='resumo.jsp?cube=" + request.getParameter( "cube" ) + "&cubeIndexEntry=" + request.getParameter("cubeIndexEntry") +"'</script>");
			//out.println("<script>parent.document.getElementById('opresumo').window.location.replace('pg/resumo.jsp?cube=" + request.getParameter( "cube" ) + "')</script>");
			//out.println("<script>parent.document.getElementById('opresumo').src='pg/resumo.jsp?cube=" + request.getParameter( "cube" ) + "'</script>");
			//out.println("<script>parent.document.getElementById('opresumo').window.location.reload()</script>");

		} catch (Exception e) {
			e.printStackTrace();
			out.println("<br>Deu Erro");
			out.println("<br>"+e.getMessage());
		}
%>

</BODY>
</HTML>