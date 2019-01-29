<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Login</h1>
<form method="post" action="http://localhost:8080/FinalProject/controller/User/login">

	User Id: <input type="text" name="userId" />
	<br/>
	Password: <input type="text" name="password" />
	<br/>
	<input type=submit />

</form>
</body>
</html>