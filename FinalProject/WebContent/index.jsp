<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<p>${message}</p>

<h1>Welcome to My Gym</h1>
<form method="post" action="http://localhost:8080/FinalProject/controller/Navigation/goTo">
<button type="submit" name ="button" value = "register" >Register</button>
<button type="submit" name ="button" value = "login" >Login</button>
</form>
</body>
</html>