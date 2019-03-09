<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update activity</title>
</head>
<body>
<h1>Update activity</h1>

	<form method="post" action="http://localhost:8080/FinalProject/controller/Activity/update">
		<table>
			<tr>
				
				<td>Exercise Name:</td>
				<td>
					<select name="exercise_name">
						<option>abs</option>
						<option>back</option>
						<option>cardio</option>
						<option>chest</option>
						<option>legs</option>
						<option>shoulders</option>
					</select>
			</tr>
		
				<tr>
					<td>Exercise data (dd/MM/yyyy):</td>
					<td><input type="text" name="activityDate" size="50" /></td>
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
					<td>
						<input type=submit />
					</td>
			</tr>
				
			</tbody>
		</table>
	</form>
	
	<p>${message}</p>
	
</body>
</html>