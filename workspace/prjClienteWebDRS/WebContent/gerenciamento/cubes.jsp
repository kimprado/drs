<%@ page import="java.util.*,java.io.BufferedReader,java.io.FileReader,org.apache.axis.message.addressing.Address,org.apache.axis.message.addressing.EndpointReferenceType,org.globus.cube.stubs.Cube.*,org.globus.cube.stubs.Cube.service.* ,org.globus.index.stubs.Cube.*,org.globus.index.stubs.Cube.service.*" %>
 
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
<title></title>
<script src="../include/script/xtreeToCube.js"></script>
<script type="text/javascript">
function selecionarCubo(posicao) {
	var cubo = cubos[posicao];
	var metadadados = "tree.jsp?cube="+cubo.cubo+"&cubeIndexEntry="+cubo.cuboIndex+"&cubeURI="+cubo.cuboURI;
	top.tree.location = metadadados;
	
	var formulario = "../cubo.do?metodo=formIncluirCubo";
	top.formularioFrame.location = formulario;
}

var cubos = new Array();

function adicionarCubo(pnome, pcubo, pcuboIndex, pcuboURI) {
	cubos.push( {
		nome: pnome,
		cubo: pcubo,
		cuboIndex: pcuboIndex,
		cuboURI: pcuboURI
	});
}

</script>

<link type="text/css" rel="stylesheet" href="../include/style/xtree.css">
</head>

<BODY bgcolor=#fad76d>

<% 
	CubeIndexServiceAddressingLocator locatorIndex = new CubeIndexServiceAddressingLocator();

		try {

			// Create endpoint reference to service
			EndpointReferenceType endpoint = new EndpointReferenceType();
			String serviceURI = getServicURI("cubeindexservice"); //cubeIndex
			endpoint.setAddress(new Address(serviceURI));

			// Get PortType
			CubeIndexPortType cubeIndex = locatorIndex.getCubeIndexPortTypePort(endpoint);

			CubeListResponse cubeList = cubeIndex.getCubeList(new GetCubeList());

			if (cubeList.getCubeEntry() != null){ //faz o teste da quantidade de entradas no �ndice
				out.println("<script>\n if (document.getElementById) {"); // in�cio do script e do m�todo
				out.println("var tree = new WebFXTree('Assuntos',null,null);"); // declara a "ra�z" da �rvore ou o "nodo raiz"
				
				out.println("tree.location = 'caraca isso � muita doidera';"); // declara a "ra�z" da �rvore ou o "nodo raiz"
				
				CubeServiceAddressingLocator locatorCube = new CubeServiceAddressingLocator();

				for (int i=0; i < cubeList.getCubeEntry().length; i++){  //respons�vel por fazer a ransi��o entre as entradas
					CubeEntry cb = cubeList.getCubeEntry(i); // as entradas da lista n�o possuem o EPR ou uri. Para obter a uri � preciso ir direto ao cubeIndex e "chamar" o m�todo getCubeEntry.
					//out.println( "var cube = new WebFXTreeItem('"+cb.getName()+"','tree.jsp?cube="+cb.getIndex()+"&cubeIndexEntry="+cb.getEntry()+"&cubeURI="+cb.getUri()+"','tree');" );
					out.println( "var cube = new WebFXTreeItem('"+cb.getName()+"',\"javascript:selecionarCubo('"+ i +"')\",null);" );
					out.println( "adicionarCubo('"+cb.getName()+"','"+cb.getIndex()+"','"+cb.getEntry()+"','"+cb.getUri()+"');" );
					out.println( "cube.icon = 'images/cube002.png';");
					out.println( "tree.add(cube);" );


				} // for
			out.println("document.write(tree);}</script>");  // exibe o objeto na p�gina

			} else{   //caso n�o haja entrada de cubo(s) no �ndice � mostrada uma entrada vazia
				out.println("<script>\nif (document.getElementById) {");
				out.println("var tree = new WebFXTree('Cubos');");
				out.println("tree.add(new WebFXTreeItem('N�o h� entradas de cubos'));");
				out.println("document.write(tree);}</script>");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
%>
</BODY>
</HTML>

