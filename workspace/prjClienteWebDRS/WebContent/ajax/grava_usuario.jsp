<% // Importa��o de Classes Java %>

<%@ page import = "java.text.*" %>
<%@ page import = "java.util.*" %>

<% // Vari�veis da conex�o com o banco de dados %>


<%





//Par�metros recuperados do Ajax
String nome = request.getParameter("sql");




%>


<%  
    out.println("Dados inseridos com Sucesso!!!");
%>



<%// Note que, ap�s a inser��o, a p�gina n�o redireciona para nenhuma p�gina.%>