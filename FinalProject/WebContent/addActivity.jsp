<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add activity</title>
</head>
<body>
<h1>Add activity</h1>
<p>Activity can be only legs chest or back<p/>
<form method="post" action="http://localhost:8080/FinalProject/controller/Activity/add">
	<table>
		<tbody>
			<tr>
				<td>Exercise name:</td>
				<td><input type="text" name="exercise_name" size="50" /></td>
			</tr>
			
			<tr>
				<td>Amount of sets:</td>
				<td><input type="text" name="amount_of_sets" size="50" /></td>
			</tr>
			
			<tr>
				<td>Repeats:</td>
				<td><input type="text" name="repeats" size="50" /></td>
			</tr>

			<tr>
				<td>Weight:</td>
				<td><input type="text" name="weight" size="50" /></td>
			</tr>
			
			<tr>
				<td>Duration:</td>
				<td><input type="text" name="duration" size="50" /></td>
			</tr>

			<tr>
				<td>Type:</td>
				<td><input type="text" name="type" size="50" /></td>
			</tr>

			<tr>
				<td><input type=submit /></td>
			</tr>
		</tbody>
	</table>
</form>
</body>
</html>