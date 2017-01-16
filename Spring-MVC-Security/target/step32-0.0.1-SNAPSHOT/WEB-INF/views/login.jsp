<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<p><font color="red">${errorMessage}</font></p>
<form action="/step32/login" method="POST">
	<div>Name: <input name="name" type="text" /></div>
	<div>Password: <input name="password" type="password" /></div>
	<div><input type="submit" value="Login" /></div>
</form>

</body>
</html>