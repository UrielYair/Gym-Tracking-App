<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dates of workouts from the last X Days</title>
</head>
<body>

<%
String userName = (String) session.getAttribute("userName");
int amountOfDays = Integer.parseInt(request.getAttribute("amount").toString());
com.hit.reports.Reports.getWorkOutsInPastXDays(amountOfDays, userName);
%>

</body>
</html>