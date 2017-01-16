<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:url var="url" value="/persona" />
<form:form action="${url}" method="post" modelAttribute="personaForm">
	<form:errors path="*" />
	<br>
	<label>Nome:</label> <form:input path="nome" />
	<br>
	<label>Cognome:</label> <form:input path="cognome" /> <form:errors path="cognome" />
	<br>
	<input type="submit" />
</form:form>

<div>Dati inseriti:</div>
<div>Nome: ${persona.nome}</div>
<div>Cognome: ${persona.cognome}</div>

</body>
</html>