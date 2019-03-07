<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Get activity</title>
</head>
<body>
<h1>Get activity</h1>
<p>Activity can be only legs chest or back<p/>
<form method="post" action="http://localhost:8080/FinalProject/controller/Activity/get">
	<table>
		<tbody>
			<tr>
				<td>Exercise Name:</td>
				<td>
					<select name="exercise_name">
						<option>back</option>
						<option>legs</option>
						<option>chest</option>
					</select>
			</tr>
	
			<tr>
				<td>Date of the exercise:</td>
				<td><input type="text" name="exerciseDate" size="50" /></td>
			</tr>
		
			<tr>
				<td><input type=submit /></td>
			</tr>
		</tbody>
	</table>
</form>
</body>
</html>