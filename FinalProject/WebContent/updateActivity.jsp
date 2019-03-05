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
<p>Activity can be only legs chest or back<p/>
	<form method="post" action="http://localhost:8080/FinalProject/controller/Activity/update">
		<table>
			<tbody>
				<tr>
					<td>Activity name:</td>
					<td><input type="text" name="activityName" size="50" /></td>
				</tr>
		
				<tr>
					<td>Number of sets:</td>
					<td><input type="text" name="numberOfSets" size="50" /></td>
				</tr>
				
				<tr>
					<td>Number of repeats in each set:</td>
					<td><input type="text" name="numberOfRepeat" size="50" /></td>
				</tr>
				
				<tr>
					<td>
						<input type=submit />
					</td>
				</tr>
				
			</tbody>
		</table>
	</form>
</body>
</html>