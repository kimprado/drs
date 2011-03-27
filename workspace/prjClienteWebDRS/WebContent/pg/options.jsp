 <%@ page import="java.util.*,java.io.BufferedReader,java.io.FileReader,org.globus.drs.stubs.Cube.*,org.globus.drs.stubs.Cube.service.*,org.apache.axis.message.addressing.Address,org.apache.axis.message.addressing.EndpointReferenceType" %>
 
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

Integer a_mysession = (Integer)session.getAttribute( "a_mysession" );

if (a_mysession == null){
	pageContext.forward("/tree/");
}

try {

	if( request.getParameter("cube") != null && request.getParameter("table") != null){
	String serviceURI = getServicURI("drsservice"); //DRS;
	// Create endpoint reference to service
	EndpointReferenceType endpoint = new EndpointReferenceType();
	endpoint.setAddress(new Address(serviceURI));
	// Get PortType
	DRSPortType drs = locator.getDRSPortTypePort(endpoint);

%>

<html>
<head>
<title></title>



<script type="text/javascript">

function tdE()
{
	var td=document.createElement("TD");
	var t=document.createTextNode('e');
	td.appendChild(t);
	return td;
}

function tdOpLogicos(tipo,ind)
{

	var td=document.createElement("TD");
	var select=document.createElement("SELECT");
	select.id = tipo + ind;
	select.name = tipo + ind;

	var option1=document.createElement("OPTION");
	option1.appendChild(document.createTextNode('E'));
	option1.value = 1;
	select.appendChild(option1);

	var option2=document.createElement("OPTION");
	option2.appendChild(document.createTextNode('OU'));
	option2.value = 2;
	select.appendChild(option2);

	var option3=document.createElement("OPTION");
	option3.appendChild(document.createTextNode('NÃO'));
	option3.value = 3;
	select.appendChild(option3);


	td.appendChild(select);
	return td;
}

function tdOpLogicosAgregadoHavingLoad(tipo,ind,selecionado)
{

	var td=document.createElement("TD");
	var select=document.createElement("SELECT");
	select.id = tipo + ind;
	select.name = tipo + ind;

	var option1=document.createElement("OPTION");
	option1.appendChild(document.createTextNode('E'));
	option1.value = 1;
	select.appendChild(option1);

	var option2=document.createElement("OPTION");
	option2.appendChild(document.createTextNode('OU'));
	option2.value = 2;
	select.appendChild(option2);

	var option3=document.createElement("OPTION");
	option3.appendChild(document.createTextNode('NÃO'));
	option3.value = 3;
	select.appendChild(option3);

	select.selectedIndex = selecionado -1;
	td.appendChild(select);
	return td;
}

function tdOpAgregados(tipo,ind)
{

	var td=document.createElement("TD");
	var select=document.createElement("SELECT");
	select.id = tipo + ind;
	select.name = tipo + ind;

	var option1=document.createElement("OPTION");
	option1.appendChild(document.createTextNode('Contar'));
	option1.value = 1;
	select.appendChild(option1);

	var option2=document.createElement("OPTION");
	option2.appendChild(document.createTextNode('Somar'));
	option2.value = 2;
	select.appendChild(option2);

	var option3=document.createElement("OPTION");
	option3.appendChild(document.createTextNode('Maior'));
	option3.value = 3;
	select.appendChild(option3);

	var option4=document.createElement("OPTION");
	option4.appendChild(document.createTextNode('Menor'));
	option4.value = 4;
	select.appendChild(option4);

	var option5=document.createElement("OPTION");
	option5.appendChild(document.createTextNode('Média'));
	option5.value = 5;
	select.appendChild(option5);

	td.appendChild(select);
	return td;
}


function tdOpAgregadosLoad(tipo,ind,funcao)
{

	var td=document.createElement("TD");
	var select=document.createElement("SELECT");
	select.id = tipo + ind;
	select.name = tipo + ind;

	var option1=document.createElement("OPTION");
	option1.appendChild(document.createTextNode('Contar'));
	option1.value = 1;
	select.appendChild(option1);

	var option2=document.createElement("OPTION");
	option2.appendChild(document.createTextNode('Somar'));
	option2.value = 2;
	select.appendChild(option2);

	var option3=document.createElement("OPTION");
	option3.appendChild(document.createTextNode('Maior'));
	option3.value = 3;
	select.appendChild(option3);

	var option4=document.createElement("OPTION");
	option4.appendChild(document.createTextNode('Menor'));
	option4.value = 4;
	select.appendChild(option4);

	var option5=document.createElement("OPTION");
	option5.appendChild(document.createTextNode('Média'));
	option5.value = 5;
	select.appendChild(option5);

	select.selectedIndex = funcao -1;
	td.appendChild(select);
	return td;
}

function tdOpLogicosLoad(tipo,ind,selecionado)
{
	//alert(selecionado);
	var td=document.createElement("TD");
	var select=document.createElement("SELECT");
	select.id = tipo + ind;
	select.name = tipo + ind;

	var option1=document.createElement("OPTION");
	option1.appendChild(document.createTextNode('E'));
	option1.value = 1;
	select.appendChild(option1);

	var option2=document.createElement("OPTION");
	option2.appendChild(document.createTextNode('OU'));
	option2.value = 2;
	select.appendChild(option2);

	var option3=document.createElement("OPTION");
	option3.appendChild(document.createTextNode('NÃO'));
	option3.value = 3;
	select.appendChild(option3);


	select.selectedIndex = selecionado -1;
	td.appendChild(select);
	return td;
}

function tdOpRelacionais(ind)
{

	var td=document.createElement("TD");
	td.align="center";

	var select=document.createElement("SELECT");
	select.id = 'G'+ind;
	select.name = 'G'+ind;

	var option1=document.createElement("OPTION");
	option1.appendChild(document.createTextNode('='));
	option1.value = 1;
	select.appendChild(option1);

	var option2=document.createElement("OPTION");
	option2.appendChild(document.createTextNode('<'));
	option2.value = 2;
	select.appendChild(option2);

	var option3=document.createElement("OPTION");
	option3.appendChild(document.createTextNode('<='));
	option3.value = 3;
	select.appendChild(option3);

	var option4=document.createElement("OPTION");
	option4.appendChild(document.createTextNode('>'));
	option4.value = 4;
	select.appendChild(option4);

	var option5=document.createElement("OPTION");
	option5.appendChild(document.createTextNode('>='));
	option5.value = 5;
	select.appendChild(option5);

	var option6=document.createElement("OPTION");
	option6.appendChild(document.createTextNode('<>'));
	option6.value = 6;
	select.appendChild(option6);

	/*var option7=document.createElement("OPTION");
	option7.appendChild(document.createTextNode('entre'));
	select.appendChild(option7);*/

	td.appendChild(select);

	return td;
}


function tdOpRelacionaisLoad(ind,selecionado)
{

	var td=document.createElement("TD");
	td.align="center";

	var select=document.createElement("SELECT");
	select.id = 'G' + ind;
	select.name = 'G' + ind;

	var option1=document.createElement("OPTION");
	option1.appendChild(document.createTextNode('='));
	option1.value = 1;
	select.appendChild(option1);

	var option2=document.createElement("OPTION");
	option2.appendChild(document.createTextNode('<'));
	option2.value = 2;
	select.appendChild(option2);

	var option3=document.createElement("OPTION");
	option3.appendChild(document.createTextNode('<='));
	option3.value = 3;
	select.appendChild(option3);

	var option4=document.createElement("OPTION");
	option4.appendChild(document.createTextNode('>'));
	option4.value = 4;
	select.appendChild(option4);

	var option5=document.createElement("OPTION");
	option5.appendChild(document.createTextNode('>='));
	option5.value = 5;
	select.appendChild(option5);

	var option6=document.createElement("OPTION");
	option6.appendChild(document.createTextNode('<>'));
	option6.value = 6;
	select.appendChild(option6);


	select.selectedIndex = selecionado -1;
	td.appendChild(select);

	return td;
}

function tdOpRelacionaisHaving(ind)
{

	var td=document.createElement("TD");
	td.align="center";

	var select=document.createElement("SELECT");
	select.id = 'R' + ind;
	select.name = 'R' + ind;

	var option1=document.createElement("OPTION");
	option1.appendChild(document.createTextNode('='));
	option1.value = 1;
	select.appendChild(option1);

	var option2=document.createElement("OPTION");
	option2.appendChild(document.createTextNode('<'));
	option2.value = 2;
	select.appendChild(option2);

	var option3=document.createElement("OPTION");
	option3.appendChild(document.createTextNode('<='));
	option3.value = 3;
	select.appendChild(option3);

	var option4=document.createElement("OPTION");
	option4.appendChild(document.createTextNode('>'));
	option4.value = 4;
	select.appendChild(option4);

	var option5=document.createElement("OPTION");
	option5.appendChild(document.createTextNode('>='));
	option5.value = 5;
	select.appendChild(option5);

	var option6=document.createElement("OPTION");
	option6.appendChild(document.createTextNode('<>'));
	option6.value = 6;
	select.appendChild(option6);

	/*var option7=document.createElement("OPTION");
	option7.appendChild(document.createTextNode('entre'));
	select.appendChild(option7);*/

	td.appendChild(select);

	return td;
}

function tdOpRelacionaisHavingLoad(ind,selecionado)
{

	var td=document.createElement("TD");
	td.align="center";

	var select=document.createElement("SELECT");
	select.id = 'R' + ind;
	select.name = 'R' + ind;

	var option1=document.createElement("OPTION");
	option1.appendChild(document.createTextNode('='));
	option1.value = 1;
	select.appendChild(option1);

	var option2=document.createElement("OPTION");
	option2.appendChild(document.createTextNode('<'));
	option2.value = 2;
	select.appendChild(option2);

	var option3=document.createElement("OPTION");
	option3.appendChild(document.createTextNode('<='));
	option3.value = 3;
	select.appendChild(option3);

	var option4=document.createElement("OPTION");
	option4.appendChild(document.createTextNode('>'));
	option4.value = 4;
	select.appendChild(option4);

	var option5=document.createElement("OPTION");
	option5.appendChild(document.createTextNode('>='));
	option5.value = 5;
	select.appendChild(option5);

	var option6=document.createElement("OPTION");
	option6.appendChild(document.createTextNode('<>'));
	option6.value = 6;
	select.appendChild(option6);

	select.selectedIndex = selecionado -1;
	td.appendChild(select);

	return td;
}

function tdOpTextBox(ind)
{
	var td=document.createElement('TD');
	td.align='center';

	var textcaixa = document.createElement('INPUT');
	textcaixa.type = 'TEXT';
	textcaixa.id = 'I' + ind;
	textcaixa.name = 'I' + ind;
	textcaixa.size = 6;
	//textcaixa.value = '0';

	td.appendChild(textcaixa);

	return td;
}

function tdOpTextBoxLoad(ind,valor)
{
	var td=document.createElement('TD');
	td.align='center';

	var textcaixa = document.createElement('INPUT');
	textcaixa.type = 'TEXT';
	textcaixa.id = 'I' + ind;
	textcaixa.name = 'I' + ind;
	textcaixa.size = 6;
	textcaixa.value = valor;

	td.appendChild(textcaixa);

	return td;
}

function tdOpTextBoxAgregados(ind)
{
	var td=document.createElement('TD');
	td.align='center';

	var textcaixa = document.createElement('INPUT');
	textcaixa.type = 'TEXT';
	textcaixa.id = 'S' + ind;
	textcaixa.name = 'S' + ind;
	textcaixa.size = 6;
	//textcaixa.value = '0';

	td.appendChild(textcaixa);

	return td;
}

function tdOpTextBoxAgregadosLoad(ind,valor)
{
	var td=document.createElement('TD');
	td.align='center';

	var textcaixa = document.createElement('INPUT');
	textcaixa.type = 'TEXT';
	textcaixa.id = 'S' + ind;
	textcaixa.name = 'S' + ind;
	textcaixa.size = 6;
	textcaixa.value = valor;

	td.appendChild(textcaixa);

	return td;
}

function adicionarOp()
{

	var tableOp=document.getElementById('tableOp');
	var tbody = null;
	var tablebody = false;


	for (i=0; i< tableOP.childNodes.length; i++){
		if (tableOP.childNodes[i].nodeName=="TBODY"){
			tbody = tableOP.childNodes[i];
			tablebody = true;
			//i=tableOP.childNodes.length+2;
		} //if
	} //for */

	if (tablebody == false)
	{
		tbody = document.createElement("TBODY");
		tableOP.appendChild(tbody);
	} //if

	if (tbody){

		var TR=document.createElement("TR");
		TR.id = 'TR'+(tbody.childNodes.length+1);

		//tdOpLogicos tdOpRelacionais tdOpTextBox
		
		TR.appendChild(tdOpLogicos('E',tbody.childNodes.length+1));
		TR.appendChild(tdOpRelacionais(tbody.childNodes.length+1));
		TR.appendChild(tdOpTextBox(tbody.childNodes.length+1));

		tbody.appendChild(TR);

		var contador = document.getElementById('contadorOP').childNodes[0].childNodes[0];
		contador.nodeValue = tbody.childNodes.length;

	}
}

function adicionarAgruparSelect()
{
	var tableAgruparSelect=document.getElementById('tableAgSelect');
	var tbody = null;
	var tablebody = false;

	for (i=0; i< tableAgruparSelect.childNodes.length; i++){
		if (tableAgruparSelect.childNodes[i].nodeName=="TBODY"){
			tbody = tableAgruparSelect.childNodes[i];
			tablebody = true;
			//i=tableOP.childNodes.length+2;
		} //if
	} //for */

	if (tablebody == false)
	{
		tbody = document.createElement("TBODY");
		tableAgruparSelect.appendChild(tbody);
	} //if

	if (tbody){
		if(tbody.childNodes.length < 5){
			var TR=document.createElement("TR");
			TR.id = 'TR'+(tbody.childNodes.length+1);

			TR.appendChild(tdOpAgregados('M',tbody.childNodes.length+1));

			tbody.appendChild(TR);
		}

	}
}

function adicionarAgruparHaving()
{

	var tableAgruparHaving=document.getElementById('tableAgHaving');
	var tbody = null;
	var tablebody = false;

	for (i=0; i< tableAgruparHaving.childNodes.length; i++){
		if (tableAgruparHaving.childNodes[i].nodeName=="TBODY"){
			tbody = tableAgruparHaving.childNodes[i];
			tablebody = true;
			//i=tableOP.childNodes.length+2;
		} //if
	} //for */

	if (tablebody == false)
	{
		tbody = document.createElement("TBODY");
		tableAgruparHaving.appendChild(tbody);
	} //if

	if (tbody){

		var TR=document.createElement("TR");

		TR.id = 'TR'+(tbody.childNodes.length+1);

		//tdOpLogicos tdOpAgregados tdOpRelacionaisHaving tdOpTextBoxAgregados
		
		TR.appendChild(tdOpLogicos('P',tbody.childNodes.length+1));
		TR.appendChild(tdOpAgregados('Q',tbody.childNodes.length+1));
		TR.appendChild(tdOpRelacionaisHaving(tbody.childNodes.length+1));
		TR.appendChild(tdOpTextBoxAgregados(tbody.childNodes.length+1));
		tbody.appendChild(TR);

	}
}


function adicionarAgruparHavingLoad(logico,agregacao,comparacao,valor)
{
	var tableAgruparHaving=document.getElementById('tableAgHaving');
	var tbody = null;
	var tablebody = false;

	for (i=0; i< tableAgruparHaving.childNodes.length; i++){
		if (tableAgruparHaving.childNodes[i].nodeName=="TBODY"){
			tbody = tableAgruparHaving.childNodes[i];
			tablebody = true;
			//i=tableOP.childNodes.length+2;
		} //if
	} //for */

	if (tablebody == false)
	{
		tbody = document.createElement("TBODY");
		tableAgruparHaving.appendChild(tbody);
	} //if

	//alert("Comparação: "+comparacao);
	if (tbody){

		var TR=document.createElement("TR");

		TR.id = 'TR'+(tbody.childNodes.length+1);

		TR.appendChild(tdOpLogicosAgregadoHavingLoad('P',tbody.childNodes.length+1, logico));
		TR.appendChild(tdOpAgregadosLoad('Q',tbody.childNodes.length+1, agregacao));
		TR.appendChild(tdOpRelacionaisHavingLoad(tbody.childNodes.length+1, comparacao));
		TR.appendChild(tdOpTextBoxAgregadosLoad(tbody.childNodes.length+1, valor));
		tbody.appendChild(TR);

	}
}


function adicionarOpLoad(logico,comparacao,valor)
{
	//alert(logico);
	var tableOp=document.getElementById('tableOp');
	var tbody = null;
	var tablebody = false;


	for (i=0; i< tableOP.childNodes.length; i++){
		if (tableOP.childNodes[i].nodeName=="TBODY"){
			tbody = tableOP.childNodes[i];
			tablebody = true;
			//i=tableOP.childNodes.length+2;
		} //if
	} //for */

	if (tablebody == false)
	{
		tbody = document.createElement("TBODY");
		tableOP.appendChild(tbody);
	} //if

	if (tbody){

		var TR=document.createElement("TR");
		TR.id = 'TR'+(tbody.childNodes.length+1);


		TR.appendChild(tdOpLogicosLoad('E',tbody.childNodes.length+1,logico));
		TR.appendChild(tdOpRelacionaisLoad(tbody.childNodes.length+1,comparacao));
		TR.appendChild(tdOpTextBoxLoad(tbody.childNodes.length+1,valor));

		tbody.appendChild(TR);

		var contador = document.getElementById('contadorOP').childNodes[0].childNodes[0];
		contador.nodeValue = tbody.childNodes.length;

	}
}


function adicionarAgruparSelectLoad(funcao)
{

	var tableAgruparSelect=document.getElementById('tableAgSelect');
	var tbody = null;
	var tablebody = false;


	for (i=0; i< tableAgruparSelect.childNodes.length; i++){
		if (tableAgruparSelect.childNodes[i].nodeName=="TBODY"){
			tbody = tableAgruparSelect.childNodes[i];
			tablebody = true;

		} //if
	} //for */

	if (tablebody == false)
	{
		tbody = document.createElement("TBODY");
		tableAgruparSelect.appendChild(tbody);
	} //if

	if (tbody){

		if(tbody.childNodes.length < 5){
			var TR=document.createElement("TR");
			TR.id = 'TR'+(tbody.childNodes.length+1);

			TR.appendChild(tdOpAgregadosLoad('M',tbody.childNodes.length+1,funcao));

			tbody.appendChild(TR);
		}


	}
}


function removerOp()
{
	var tableOP=document.getElementById('tableOP'); //OP
	var tbody = null;

	for (i=0; i< tableOP.childNodes.length; i++){
		if (tableOP.childNodes[i].nodeName=="TBODY"){
			tbody = tableOP.childNodes[i];
			i=tableOP.childNodes.length+2;
		} //if
	} //for */

	if (tbody){
		var removerow = tbody.childNodes.length-1;
		tbody.deleteRow(removerow);

		var contador = document.getElementById('contadorOP').childNodes[0].childNodes[0];
		contador.nodeValue = tbody.childNodes.length;

		if ( removerow == (0) ) { tableOP.removeChild(tbody); } //if


	} //if
}

function removerAgruparSelect()
{
	var tableAgruparSelect=document.getElementById('tableAgSelect'); //OP
	var tbody = null;

	for (i=0; i< tableAgruparSelect.childNodes.length; i++){
		if (tableAgruparSelect.childNodes[i].nodeName=="TBODY"){
			tbody = tableAgruparSelect.childNodes[i];
			i=tableAgruparSelect.childNodes.length+2;
		} //if
	} //for */

	if (tbody){
		var removerow = tbody.childNodes.length-1;
		tbody.deleteRow(removerow);

		//var contador = document.getElementById('contadorOP').childNodes[0].childNodes[0];
		//contador.nodeValue = tbody.childNodes.length;

		if ( removerow == (0) ) { tableAgruparSelect.removeChild(tbody); } //if


	} //if
}

function removerAgruparHaving()
{
	var tableAgruparHaving=document.getElementById('tableAgHaving'); //OP
	var tbody = null;

	for (i=0; i< tableAgruparHaving.childNodes.length; i++){
		if (tableAgruparHaving.childNodes[i].nodeName=="TBODY"){
			tbody = tableAgruparHaving.childNodes[i];
			i=tableAgruparHaving.childNodes.length+2;
		} //if
	} //for */

	if (tbody){
		var removerow = tbody.childNodes.length-1;
		tbody.deleteRow(removerow);

		//var contador = document.getElementById('contadorOP').childNodes[0].childNodes[0];
		//contador.nodeValue = tbody.childNodes.length;

		if ( removerow == (0) ) { tableAgruparHaving.removeChild(tbody); } //if


	} //if
}

function enviarOpcoes()
{
	var form = document.getElementById('formOpcoes');
	var tableOp=document.getElementById('tableOp');
	var tbAgSel = document.getElementById('tableAgSelect');
	var tbAgHav = document.getElementById('tableAgHaving');

	var pg =  '<%= "enviarOpcoes.jsp?cube="+request.getParameter( "cube" )+"&cubeIndexEntry="+request.getParameter( "cubeIndexEntry" )+"&table="+request.getParameter("table")+"&field="+request.getParameter("field")+"&cubeURI="+ request.getParameter( "cubeURI" ) +"" %>'; // página a tratar o envio dos dados;
	var op = ""; // opções

	if (document.getElementById("mostrar").checked == true){
		op = '&mostrar=s'; //Sim
	}else{
		op = '&mostrar=n'; //Nao
	}

	if (document.getElementById("agrupar").checked == true){
		op = op + '&agrupar=s';
	}else{
		op = op + '&agrupar=n';
	}


	var tbody;
	var tbodyAgSel;
	var tbodyAgHav;

	if (form)
	{

		for (i=0; i< tableOP.childNodes.length; i++){
			if (tableOP.childNodes[i].nodeName=="TBODY"){
				tbody = tableOP.childNodes[i];
				//tablebody = true;
				i=tableOP.childNodes.length+2;
			} //if
		} //for */


		for (i=0; i< tbAgSel.childNodes.length; i++){
			if (tbAgSel.childNodes[i].nodeName=="TBODY"){
				tbodyAgSel = tbAgSel.childNodes[i];
				//tablebody = true;
				i=tbAgSel.childNodes.length+2;
			} //if
		} //for */


		for (i=0; i< tbAgHav.childNodes.length; i++){
			if (tbAgHav.childNodes[i].nodeName=="TBODY"){
				tbodyAgHav = tbAgHav.childNodes[i];
				i=tbAgHav.childNodes.length+2;
			} //if
		} //for */

//alert('oi1');
		if (tbody) {
			if ( tbody.childNodes.length > (0) ){
				op = op + '&E1=' + document.getElementById('E1').value;
				op = op + '&G1=' + document.getElementById('G1').value;
				op = op + '&I1=' + document.getElementById('I1').value;
			} // if

			for ( j=2; j < tbody.childNodes.length + 1 ; j++ ){
				op = op + '&E'+j+'=' + document.getElementById('E'+j).value;
				op = op + '&G'+j+'=' + document.getElementById('G'+j).value;
				op = op + '&I'+j+'=' + document.getElementById('I'+j).value;
			}

		} // if
//alert('oi2');
		if (tbodyAgSel) {
			if ( tbodyAgSel.childNodes.length > (0) ){
				op = op + '&M1=' + document.getElementById('M1').value;
			} // if

			for ( j=2; j < tbodyAgSel.childNodes.length + 1 ; j++ ){
				op = op + '&M'+j+'=' + document.getElementById('M'+j).value;
			}

		} // if

		if (tbodyAgHav) {
			if ( tbodyAgHav.childNodes.length > (0) ){
				op = op + '&P1=' + document.getElementById('P1').value;
				op = op + '&Q1=' + document.getElementById('Q1').value;
				op = op + '&R1=' + document.getElementById('R1').value;
				op = op + '&S1=' + document.getElementById('S1').value;
			} // if

			for ( j=2; j < tbodyAgHav.childNodes.length + 1 ; j++ ){
				op = op + '&P'+j+'=' + document.getElementById('P'+j).value;
				op = op + '&Q'+j+'=' + document.getElementById('Q'+j).value;
				op = op + '&R'+j+'=' + document.getElementById('R'+j).value;
				op = op + '&S'+j+'=' + document.getElementById('S'+j).value;
			}

		} // if

		form.action = pg;// +  op ;
		//alert('formOpcoes(form.action): \n'+form.action);
		//window.open(pg +  op,"_blank","toolbar=yes, location=yes, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes, width=300, height=200");
		form.submit();

	}
}

function removerOpcoes()
{

	limparOp();

	var form = document.getElementById('formOpcoes');
	var tableOp=document.getElementById('tableOp');

	var pg =  '<%= "enviarOpcoes.jsp?cube="+request.getParameter( "cube" )+"&cubeIndexEntry="+request.getParameter( "cubeIndexEntry" )+"&table="+request.getParameter("table")+"&field="+request.getParameter("field")+"&cubeURI="+ request.getParameter( "cubeURI" ) +"" %>'; // página a tratar o envio dos dados;

	var op = ""; // opções

	//document.getElementById("mostrar").checked = false;
	op = '&mostrar=n';

	//document.getElementById("agrupar").checked = false;
	op = op + '&agrupar=n';


	var tbody;

	if (form)
	{

		for (i=0; i< tableOP.childNodes.length; i++){
			if (tableOP.childNodes[i].nodeName=="TBODY"){
				tbody = tableOP.childNodes[i];
				tablebody = true;
				i=tableOP.childNodes.length+2;
			} //if
		} //for */

		if (tbody) {
			var contador = document.getElementById('contadorOP').childNodes[0].childNodes[0];
			//contador.nodeValue = 00;
			//tableOP.removeChild(tbody);
		} // if

		form.action = pg +  op ;
		//window.open(pg +  op,"_blank","toolbar=yes, location=yes, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes, width=300, height=200");
		form.submit();
	}
}

function limparOp()
{
	var tableOp=document.getElementById('tableOp');

	document.getElementById("mostrar").checked = false;
	document.getElementById("agrupar").checked = false;
	document.getElementById("ordenar").checked = false;
	
	var tbody;

	if (tableOP)
	{
		for (i=0; i< tableOP.childNodes.length; i++){
			if (tableOP.childNodes[i].nodeName=="TBODY"){
				tbody = tableOP.childNodes[i];
				tablebody = true;
				i=tableOP.childNodes.length+2;
			} //if
		} //for */
		if (tbody) {
			var contador = document.getElementById('contadorOP').childNodes[0].childNodes[0];
			contador.nodeValue = 00;
			tableOP.removeChild(tbody);
		} // if
	}


	var tableAgSelect = document.getElementById('tableAgSelect');
	var tbodyAgSelect;
	if (tableAgSelect)
	{
		for (i=0; i< tableAgSelect.childNodes.length; i++){
			if (tableAgSelect.childNodes[i].nodeName=="TBODY"){
				tbodyAgSelect = tableAgSelect.childNodes[i];
				tablebody = true;
				i=tableAgSelect.childNodes.length+2;
			} //if
		} //for */
		if (tbodyAgSelect) {

			tableAgSelect.removeChild(tbodyAgSelect);
		} // if
	}


	var tableAgruparHaving=document.getElementById('tableAgHaving'); //OP
	var tbodyAgHaving;// = null;

	for (i=0; i< tableAgruparHaving.childNodes.length; i++){
		if (tableAgruparHaving.childNodes[i].nodeName=="TBODY"){
			tbodyAgHaving = tableAgruparHaving.childNodes[i];
			i=tableAgruparHaving.childNodes.length+2;
		} //if
	} //for */

	if (tbodyAgHaving){
		tableAgruparHaving.removeChild(tbodyAgHaving);
	} //if


}

function atualizarRemover(cb,tb,fi)
{
<%
out.println( "var cube=" + request.getParameter("cube") );
out.println( "var table=" + request.getParameter("table") );
out.println( "var field=" + request.getParameter("field") );
%>

if (cb == cube && tb == table && fi == field)
{
	limparOp();
}

}


function load()
{

<%

//Atributo at = new Atributo(null,null,false,Integer.parseInt(request.getParameter("field")),null,a_mysession.intValue(),Integer.parseInt(request.getParameter("cubeIndexEntry")),Integer.parseInt(request.getParameter( "cube" )),null,false,null,Integer.parseInt(request.getParameter("table")),null,null);
Atributo at = new Atributo();
at.setCampo(Integer.parseInt(request.getParameter("field")));
at.setConsulta(a_mysession.intValue());
at.setCubeEntry(Integer.parseInt(request.getParameter("cubeIndexEntry")));
at.setCubo(Integer.parseInt(request.getParameter( "cube" )));
at.setTabela(Integer.parseInt(request.getParameter("table")));

//out.println("alert('"+ a_mysession.intValue() + "');");
GetCampoResponse getcampo = drs.getCampo(new GetCampo(at));
//out.println("alert('"+Integer.parseInt(request.getParameter("field"))+" "+Integer.parseInt(request.getParameter("table"))+" "+Integer.parseInt(request.getParameter("cube")) "');");

if (getcampo != null){
	Atributo atributo = getcampo.getAtributo();
	out.println("document.getElementById(\"mostrar\").checked = " + atributo.isMostrar() +";");
	out.println("document.getElementById(\"agrupar\").checked = " + atributo.isAgrupar() +";");
	out.println("document.getElementById(\"ordenar\").checked = " + atributo.isOrdenar() +";");
	out.println("document.getElementById(\"ordenardirecao\").selectedIndex = " + atributo.getOrdenarDirecao() +" - 1;");
	
	if (atributo.getOpcoes() != null){
		for (int i=0; i < atributo.getOpcoes().length; i++){
			Opcoes op = atributo.getOpcoes(i);
			out.println("adicionarOpLoad("+op.getLogico()+","+op.getComparacao()+",'"+op.getValor()+"');");//op.getLogico()
		}//for
	}//if

	if (atributo.getAgSel() != null){
		for (int i=0; i < atributo.getAgSel().length; i++){
			AgSel agsel = atributo.getAgSel(i);
			out.println("adicionarAgruparSelectLoad("+ agsel.getAgregacao() +");");
		}
	}

	if (atributo.getAgHav() != null){
		for (int i=0; i < atributo.getAgHav().length; i++){
			AgHav aghav = atributo.getAgHav(i);
			System.out.println("Having "+aghav.getComparacao());
			out.println("adicionarAgruparHavingLoad("+aghav.getLogico()+","+aghav.getAgregacao()+","+aghav.getComparacao()+",'"+aghav.getValor()+"');");
		}
	}

	//AgHav aghav = atributo.getAgHav(0);
	//out.println("adicionarAgruparHavingLoad(1,1,1);");

}//if


%>



}//try

</script>




</head>
<body bgcolor='#EAF3FF' onload='load()'>

<form METHOD='POST' id='formOpcoes' ACTION='contar_opcoes.jsp' target='ascr'>

<table id='table01' border='0' WIDTH='96%' CELLSPACING='10' CELLPADDING='5'>
<CAPTION><font size=3 color='#000000'><b>(<%= request.getParameter("fieldName").toUpperCase() %>)</b></font></CAPTION>
<br>

<!-- target='ascr' 

<form METHOD='POST' id='formOpcoes' ACTION='contar_opcoes.jsp' > -->

<tr>
<td colspan=1  bgcolor='#ADBDE7'>
<font size='2' color='black'><b>Condições</b></font>
<br>

<table border='0' id='tableMenu' RULES='NONE' bordercolor='black' bgcolor='#d5E8FF' WIDTH='100%' CELLSPACING='2' CELLPADDING='1'>
	<tr>
		
		
		
		<td WIDTH='80%' align='center'>
		<p id='contadorOP' style="display: none;"><font size='2' color='black'>00</font></p>
		</td>
		
		<td  WIDTH='10%' align='center'>
		
		<INPUT TYPE='button' VALUE='(-)' onclick="removerOp()">
		
		</td>
		
		<td WIDTH='10%' align='left'>
		
		<INPUT TYPE='button' VALUE='(+)' id='c' onclick="adicionarOp()">
		</td>
	
	</tr>

</table>


<table id='tableOP' border='1' RULES='NONE' bordercolor='black' bgcolor='#d5E8FF' WIDTH='100%' CELLSPACING='4' CELLPADDING='3'>

</table>

</td>
</tr>


<tr>
<td bgcolor='#ADBDE7'>


<table border='0' id='tbMostrar' CELLSPACING='0' CELLPADDING='2'>
	
	<tr>
		<td colspan=3>
		<INPUT TYPE="checkbox" ID="mostrar" NAME="mostrar" value="1"><font size='2' color='black'><b>Mostrar</b></font>
		</td>
	</tr>

	<tr bgcolor='#d5E8FF'>
	
		<td>
		</td>
		
		<td WIDTH='10%' align='center'>
		<INPUT TYPE='button' VALUE='(-)' onclick='removerAgruparSelect()'>
		</td>
		
		<td WIDTH='10%' align='left'>
		<INPUT TYPE='button' VALUE='(+)'  id='a' onclick='adicionarAgruparSelect()'>
		</td>
		
	</tr>
</table>


<table id='tableAgSelect' border='1' RULES='NONE' bordercolor='black' BGCOLOR='#d5E8FF' WIDTH='100%' CELLSPACING='4' CELLPADDING='3'>

</table>


</td>
</tr>



<tr>
<td bgcolor='#ADBDE7'>

<table border='0' id='tbAgrupar' CELLSPACING='0' CELLPADDING='2'>
	
	<tr>
		<td colspan=3>
		<INPUT TYPE="checkbox" ID="agrupar" NAME="agrupar" value="1"><font size='2' color='black'><b>Agrupar</b></font>
		</td>
	</tr>
	
	<tr bgcolor='#d5E8FF'>
	
		<td></td>
		
		<td WIDTH='10%' align='center'>
			<INPUT TYPE='button' VALUE='(-)' onclick="removerAgruparHaving()">
		</td>
		
		<td WIDTH='10%' align='left'>
			<INPUT TYPE='button' VALUE='(+)' id='b' onclick="adicionarAgruparHaving()">
		</td>
	
	</tr>

</table>


<table id='tableAgHaving' border='1' RULES='NONE' bordercolor='black' bgcolor='#d5E8FF' WIDTH='100%' CELLSPACING='3' CELLPADDING='1'>

</table>

</td>
</tr>


<tr>
<td bgcolor='#ADBDE7'>


<table border='0' id='tbOrdenar' CELLSPACING='0' CELLPADDING='2'>
	
	<tr>
		<td colspan=3>
			<INPUT TYPE="checkbox" ID="ordenar" NAME="ordenar" value="1"><font size='2' color='black'><b>Ordenar</b></font>
		</td>
	</tr>
	
	 
	<tr bgcolor='#d5E8FF'>
	
		<td>
		</td>
		
		
		<td WIDTH='10%' align='center'>
			<font size='2' color='black'><b></b></font>
		</td>
		
		<td WIDTH='10%' align='left'>
			<select id="ordenardirecao" name="ordenardirecao">
				<option value="1">Crescente</option>
				<option value="2">Decrescente</option>
			</select>
		</td>
		
		
	</tr>
	
</table>





</td>
</tr>




	<tr bgcolor='#ADBDE7'>
		<td align='center'>
		
			<table border='0' id='tableEnviar' border='0' RULES='NONE' bordercolor='black' bgcolor='#ADBDE7' WIDTH='98%' CELLSPACING='3' CELLPADDING='1'>
			
				<tr>
					<td  WIDTH='25%' align='center'>
					<INPUT TYPE='button' VALUE='OK' onclick="enviarOpcoes()">
					</td>
					
					<td WIDTH='45%' align='center'>
					<INPUT TYPE='button' VALUE='Limpar' onclick="limparOp();">
					</td>
					
					<td WIDTH='30%' align='center'>
					<INPUT TYPE='button' VALUE='Remover' onclick="removerOpcoes()">
					</td>
				</tr>
			
			</table>
		
		</td>
	</tr>
<!--  </form> -->
</table>

</form>

</body>
</html>

<%



	}
	} catch (Exception e) {
	e.printStackTrace();
	out.println("erro");
	}

%>
