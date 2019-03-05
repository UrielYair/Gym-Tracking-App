<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
<p>${message}</p>
<button type="button" name="logoutButton" onclick="window.location.href='http://localhost:8080/FinalProject/controller/User/logout'">Logout</button>
<form method="post" action="http://localhost:8080/FinalProject/controller/Navigation/goTo">
<br/>
<br/>
<button type="submit" name ="button" value ="acountManagement">Acount Management</button>
<button type="submit" name ="button" value ="activities">Activities</button>
</form>
</body>
</html>