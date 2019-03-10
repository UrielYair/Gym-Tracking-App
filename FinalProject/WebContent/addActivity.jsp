<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add activity</title>
</head>
<body>
<form method="post" action="http://localhost:8080/FinalProject/controller/Navigation/goTo">
<button type = "button" name = "logoutButton" onclick="window.location.href='http://localhost:8080/FinalProject/controller/User/logout'">Logout</button>
<button type="submit" name ="button" value = "home">Home</button>
</form>
<br/>
<br/>
<h1>Add activity</h1>
<form method="post" action="http://localhost:8080/FinalProject/controller/Navigation/goTo">
<button type="submit" name ="button" value = "addAnaerobic" >Add Anaerobic activity</button>
<button type="submit" name ="button" value = "addCardio" >Add Cardio activity</button>
</form>
</body>
</html>