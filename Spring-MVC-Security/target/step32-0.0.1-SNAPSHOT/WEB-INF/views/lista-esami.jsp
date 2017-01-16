<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table>
	<caption>I tuoi Esami</caption>
	<tr>
		<th>Descriziome</th>
		<th>Data</th>
		<th>Superato</th>
		<th></th>
	</tr>
	<c:forEach items="${esami}" var="esame">
	<tr>
		<td>${esame.desc}</td>
		<td>${esame.data}</td>
		<td>${esame.superato}</td>
		<td><a type="button" href="/step32/delete-esame?id=${esame.id}">Delete</a></td>
	</tr>
	</c:forEach>
</table>

<div><a type="button" href="/step32/add-esame">Add</a></div>

</body>
</html>