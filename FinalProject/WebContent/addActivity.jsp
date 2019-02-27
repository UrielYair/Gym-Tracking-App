<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Add activity</h1>
<p>Activity can be only legs chest or back<p/>
<form method="post" action="http://localhost:8080/FinalProject/controller/Activity/add">

	Exercise name: <input type="text" name="exercise_name" />
	<br/>
	Amount of sets: <input type="text" name="amount_of_sets" />
	<br/>
	Repeats: <input type="text" name="repeats" />
	<br/>
	Weight: <input type="text" name="weight" />
	<br/>
	Duration: <input type="text" name="duration" />
	<br/>
	Type: <input type="text" name="type" />
	<br/>
	<input type=submit />

</form>
</body>
</html>