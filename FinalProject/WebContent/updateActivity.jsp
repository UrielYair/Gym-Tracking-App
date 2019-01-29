<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Update activity</h1>
<p>Activity can be only legs chest or back<p/>
<form method="post" action="http://localhost:8080/FinalProject/controller/Activity/update">

	Activity name: <input type="text" name="activityName" />
	<br/>
	Number of sets: <input type="text" name="numberOfSets" />
	<br/>
	Number of repeats in each set: <input type="text" name="numberOfRepeat" />
	<br/>
	<input type=submit />

</form>
</body>
</html>