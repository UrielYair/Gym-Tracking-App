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

<button type = "button" name = "logoutButton" onclick="window.location.href='http://localhost:8080/FinalProject/controller/User/logout'">Logout</button>
<br/>
<br/>
<button type = "button" name = "acountManagementButton" onclick="window.location.href='http://localhost:8080/FinalProject/controller/Navigation/goToAcountManagement'">Acount Management</button>
<button type = "button" name = "ActivitiesButton" onclick="window.location.href='http://localhost:8080/FinalProject/controller/Navigation/goToActivities'">Activities</button>
</body>
</html>