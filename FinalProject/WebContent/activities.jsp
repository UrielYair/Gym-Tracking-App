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
<button type = "button" name = "addActivityButton" onclick="window.location.href='http://localhost:8080/FinalProject/controller/Activity/add'">Add Exercise</button>
<br/>
<br/>
<button type = "button" name = "deleteActivityButton" onclick="window.location.href='http://localhost:8080/FinalProject/controller/Activity/delete'">Delete Exercise</button>
<br/>
<br/>
<button type = "button" name = "updateActivityButton" onclick="window.location.href='http://localhost:8080/FinalProject/controller/Activity/update'">Update Exercise</button>

</body>
</html>