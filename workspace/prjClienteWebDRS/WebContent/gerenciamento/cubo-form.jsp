<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DRS - Cubo</title>
</head>
<body bgcolor=#E5EDFF>

	<br>

	<table>
		<tr>
			<td>
				Nome
			</td>
			<td>
				<input type="text" id="cubo.nome" name="cubo.nome" value="${cubo.nome}" />
			</td>
		</tr>
		
		<tr>
			<td>
				URL
			</td>
			<td>
				<input type="text" id="cubo.connectionUrl" name="cubo.connectionUrl" value="${cubo.connectionUrl}" />
			</td>
		</tr>
		
		<tr>
			<td>
				Usuário
			</td>
			<td>
				<input type="text" id="cubo.connectionUser" name="cubo.connectionUser" value="${cubo.connectionUser}" />
			</td>
		</tr>
		
		<tr>
			<td>
				Senha
			</td>
			<td>
				<input type="text" id="cubo.connectionPassword" name="cubo.connectionPassword" value="${cubo.connectionPassword}" />
			</td>
		</tr>
		
		<tr>
			<td>
				Driver
			</td>
			<td>
				<input type="text" id="cubo.driver" name="cubo.driver" value="${cubo.driver}" />
			</td>
		</tr>
		
		<tr>
			<td>
				Refresh
			</td>
			<td>
				<input type="text" id="cubo.refresh" name="cubo.refresh" value="${cubo.refresh}" />
			</td>
		</tr>
		
	</table>
	
</body>
</html>