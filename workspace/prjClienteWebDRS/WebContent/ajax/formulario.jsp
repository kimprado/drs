<html>
<head>
<title>Teste</title>
<script>
function verificacampos()
{
alert(document.getElementById("sql").value);
var sql = document.getElementById("sql").value;
if (sql == "")
{
    alert('É necessário digitar os campos Nome, Login e Senha corretamente');
    return false;
}
else
{
        salvar()

}

}
//esta é a função Ajax. Sem ela, nada acontece.
function openAjax() {

var ajax;

try{
    ajax = new XMLHttpRequest(); // XMLHttpRequest para Firefox, Safari, dentre outros.
}catch(ee){
    try{
        ajax = new ActiveXObject("Msxml2.XMLHTTP"); // Para o Internet Explorer
    }catch(e){
        try{
            ajax = new ActiveXObject("Microsoft.XMLHTTP"); // Para o Internet Explorer
        }catch(E){
            ajax = false;
        }
    }
}
return ajax;
}


function salvar()
{
        var sql = document.getElementById('sql').value; //Note que as variáveis são resgatadas pela função getElementById.
        
        
        var exibeResultado = document.getElementById('exibeResultado');

            var ajax = openAjax(); // Inicia o Ajax.
            ajax.open("GET", ""grava_usuario.jsp?sql="+sql, true); // Envia o termo da busca como uma querystring, nos possibilitando o filtro na busca.
            ajax.onreadystatechange = function()
            {
                if(ajax.readyState == 1) // Quando estiver carregando, exibe: carregando...
                {
                    exibeResultado.innerHTML = "Inserindo";
                }
                if(ajax.readyState == 4) // Quando estiver tudo pronto.
                {
                    if(ajax.status == 200)
                    {
                        var resultado = ajax.responseText;
                        exibeResultado.innerHTML = resultado;
                    }
                      else
                      {
                          exibeResultado.innerHTML = "Erro nas funções do Ajax";
                      }
                }
            }
            ajax.send(null); // submete
            document.getElementById("sql").value= "";//limpa os campos
            document.getElementById("sql").setFocus=true;
            exibeResultado.innerHTML = sql;

}



</script>



<link rel="stylesheet" type="text/css" href="tabelacss.css"/>
</head>

<body>

<table id="tabela_especial" align="center" width="900">
  <tr>
    <td>
      <table border="1" align="center" width="790">
        <tr>
          <td id="fundo_branco">New Brejos' PUserName_Senha_Administrador </td>
        </tr>
        <tr>
          <td align="left">
            <table align=center width="790">
              <tr>
                <td>
                <form  name="formgrava" action="grava_usuario.jsp">
            <table width="560">
              <tr>
                <td align="right">SQL:</td><td><input type="text" id="sql" size="30" name="sql" onclik="alert('oi')"></td>
              </tr>
              <tr><br></tr>
              <tr>
                <td align="left"><td><input type="button" size="30" name="gravar" id="gravar" value="Gravar" onclick="verificacampos()">
                
                <input type="button" size="30" name="cancelar" value="Cancelar">
              
              </tr>
               </form>
            </table></td>
          
          

        </tr>
          </td>
        </tr>
            </table><tr></tr>
          <table>
            <tr align="left">
              <td align="left"><font color="lightgray">Status: </font></td><td align="left"><div id="exibeResultado" align="center"><font color="lightgray">Em espera</font></div></td>
            </tr>
          </table>
          <tr><td align="right"><font color="#EBD8F5">Inserção usando AJAX</font></td></tr>
      </table>
      
    </td><br>
  </tr>
  

</table>


</body>
</html>