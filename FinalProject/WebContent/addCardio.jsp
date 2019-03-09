<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Cardio activity</title>
</head>
<body>
<h1>Add Cardio activity</h1>
<form method="post" action="http://localhost:8080/FinalProject/controller/Activity/addCardio">
	<table>
		<tbody>
			<tr>
				<td>Duration:</td>
				<td><input type="text" name="duration" size="50" /></td>
			</tr>

			<tr>
				<td><input type=submit /></td>
			</tr>
		</tbody>
	</table>
</form>

<p>${message}</p>

</body>
</html>