<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<button type = "button" name = "logoutButton" onclick="window.location.href='http://localhost:8080/FinalProject/controller/User/logout'">Logout</button>
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

</body>
</html>