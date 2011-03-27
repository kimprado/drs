<% // Importação de Classes Java %>

<%@ page import = "java.text.*" %>
<%@ page import = "java.util.*" %>

<% // Variáveis da conexão com o banco de dados %>


<%





//Parâmetros recuperados do Ajax
String nome = request.getParameter("sql");




%>


<%  
    out.println("Dados inseridos com Sucesso!!!");
%>



<%// Note que, após a inserção, a página não redireciona para nenhuma página.%>