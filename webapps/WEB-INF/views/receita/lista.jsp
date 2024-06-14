<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@	taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Receitas FJ21 Spring</title>
</head>
<body>
	<h1>Controle de Receita</h1>
	<h3>Listagem de Receita</h3>
	<a href="ok" style="color: blue">HOME</a>
	<hr>
	<table>
		<tr>
			<th>Id</th>
			<th>&nbsp;Descricao</th>
			<th>&nbsp;Data</th>
			<th>&nbsp;Hora</th>
			<th colspan="2">Operacao</th>
		</tr>
		<c:forEach items="${receitas}" var="receita">
			<tr>
				<td>${receita.consultaCodConsulta}</td>
				<td>&nbsp;${receita.descricao}</td>
				<td>&nbsp;<fmt:formatDate value="${receita.data.time}" pattern="dd/MM/yyyy"/></td>
				<td>&nbsp;${receita.hora}</td>

				<td>&nbsp;<a href="mostraReceita?consultaCodConsulta=${receita.consultaCodConsulta}">Alterar</a></td>
				<td>&nbsp;<a href="removeReceita?consultaCodConsulta=${receita.consultaCodConsulta}">Remover</a></td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<br />
	<a href="novaReceita">Nova Receita</a>
</body>
</html>
