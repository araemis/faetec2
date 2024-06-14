<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Receita FJ21 Spring</title>
</head>
<body>
	<h1>Controle de Receita</h1>
	<h3>Adicionar Receita</h3>
	<form:errors path="receita.descricao" cssStyle="color:red"/><br />
	<form action="adicionaReceita" method="post">
	<hr>
        Descricao:<br/>
        <textarea name="descricao" rows="5" cols="100"></textarea><br/>
        <hr>
        <input type="submit" value="Adicionar">
        <br/>
	</form>
</body>
</html>
