<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Get activity</h1>
<p>Activity can be only legs chest or back<p/>
<form method="post" action="http://localhost:8080/FinalProject/controller/Activity/get">

	Exercise name: <input type="text" name="exercise_name" />
	<br/>
	Date of the exercise: <input type="text" name="exerciseDate" />
	<br/>
	<input type=submit />

</form>
</body>
</html>