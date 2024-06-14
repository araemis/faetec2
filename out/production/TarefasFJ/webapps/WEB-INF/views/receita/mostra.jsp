<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Receitas FJ21 Spring</title>
<!-- <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css"> -->
<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
<!-- <script src="https://code.jquery.com/jquery-3.6.0.js"></script> -->
<!-- <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script> -->
<script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
</script>
</head>
<body>
	<h1>Controle de Receita</h1>
	<h3>Alterar receita - ${receita.consultaCodConsulta}</h3>
	<hr>
	<form action="alteraReceita" method="post">
		<input type="hidden" name="consultaCodConsulta" value="${receita.consultaCodConsulta}" />
		Descricao:<br />
		<textarea name="descricao" cols="100" rows="5">${receita.descricao}</textarea>
		<br />
		Data de finalizacao: <br />
		<input type="text" id="datepicker" name="data"
        value="<fmt:formatDate value="${receita.data.time}" pattern="dd/MM/yyyy" />" />
		<br />
		<input type="text" name="hora" value="${receita.hora}"/>
        <br/>
		<hr>
		<input type="submit" value="Alterar" />
		<br />
	</form>
</body>
</html>
