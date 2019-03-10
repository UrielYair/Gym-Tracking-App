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

<form method="post" action="http://localhost:8080/FinalProject/controller/Navigation/goTo">
<button type = "button" name = "logoutButton" onclick="window.location.href='http://localhost:8080/FinalProject/controller/User/logout'">Logout</button>
<button type="submit" name ="button" value = "home">Home</button>
</form>
<br/>
<br/>
<form method="post" action="http://localhost:8080/FinalProject/controller/Navigation/goTo">
<button type="submit" name ="button" value = "addActivity">Add Exercise</button>
<br/>
<br/>
<button type="submit" name ="button" value = "deleteActivity">Delete Exercise</button>
<br/>
<br/>
<button type="submit" name ="button" value = "updateActivity">Update Exercise</button>
</form>

<br/>
<br/>
<form method="post" action="http://localhost:8080/FinalProject/controller/Reports/getAllMyActivities">
<button type="submit">Get all my activities</button>
</form>

</body>
</html>