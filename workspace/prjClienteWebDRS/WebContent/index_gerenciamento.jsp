<%@ page import="java.util.*,java.io.BufferedReader,java.io.FileReader,org.apache.axis.message.addressing.Address,org.apache.axis.message.addressing.EndpointReferenceType,org.globus.cube.stubs.Cube.*,org.globus.drs.stubs.Cube.service.*,org.globus.drs.stubs.Cube.bindings.*,org.globus.drs.stubs.Cube.*,org.globus.cube.stubs.Cube.service.* ,org.globus.index.stubs.Cube.*,org.globus.index.stubs.Cube.service.*" 
language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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
DRSServiceAddressingLocator locator = new DRSServiceAddressingLocator();

try {

	//out.println(getServicURI("drsservice"));
	//out.println(System.getenv("CUBO_CONF"));

	String serviceURI = getServicURI("drsservice"); //DRS


	// Create endpoint reference to service
	EndpointReferenceType endpoint = new EndpointReferenceType();
	endpoint.setAddress(new Address(serviceURI));
	// Get PortType
	DRSPortType drs = locator.getDRSPortTypePort(endpoint);

	Integer a_mysession = (Integer)session.getAttribute( "a_mysession" );

	if (a_mysession == null){
		session.setAttribute( "a_mysession", new Integer(drs.criarConsulta(new CriarConsulta())) );
	}


	} catch (Exception e) {
		e.printStackTrace();
		out.println("DEU ER0ROR");
	}
 %>
<HTML>

<head>
<title>DRS - Data Report Service (Cliente Web)</title>
<script>
	//alert('sua consulta para esta sessão, (<%= session.getAttribute( "a_mysession" ) %>) ');
</script>
</head>

 <frameset rows="40px,94%,00%" border=0>

	<frame src="include/jsp/header.html" name="drs" NORESIZE MARGINHEIGHT=0 SCROLLING=NO>

 	<frameset cols="75%,35%">

 		<frameset rows="100%" border=0>
 			<frameset cols="30%,70%">
				<frame src="gerenciamento/cubes.jsp" name="cubes" NORESIZE SCROLLING=auto>
				<frame src="gerenciamento/boasVindas.html" name="tree" NORESIZE MARGINHEIGHT=1 SCROLLING=auto>
			</frameset>

			
		</frameset>

		<frame src="pg/optionsInicio.html" id="options" name="options" NORESIZE MARGINHEIGHT=0 SCROLLING=auto>

	</frameset>

	<frame id="ascr" name="ascr" NORESIZE MARGINHEIGHT=0 SCROLLING=no>

</frameset>

</HTML>