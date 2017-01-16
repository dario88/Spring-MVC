<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!
	<spring:message code="message.hello"/>
	  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<P>  Remote Server URL: ${remoteServerUrl} </P>
</body>
</html>
