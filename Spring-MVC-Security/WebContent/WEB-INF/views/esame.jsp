<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@ include file="common/navigation.jspf"%>


<form:form method="post" commandName="esame">

	<form:label path="desc">Description:</form:label>
	<form:input path="desc" type="text" />
	<form:errors path="desc" font-color="red" />
	
	<form:label path="data">Data:</form:label>
	<form:input path="data" type="text" />
	<form:errors path="data" font-color="red" />
	
	<button type="submit">Add</button>
</form:form>

</body>
</html>