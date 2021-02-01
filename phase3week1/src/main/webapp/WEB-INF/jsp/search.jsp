<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Driver Add</title>
</head>
<body>
	<form action="/search" method="post">
		<h1>DRIVER DATABASE SEARCH</h1>
		<p>Driver ID:<input type="text" name="driverId" placeholder="1234567"/></p>
		<input type="submit" value="Search"/>
		<a href="/add" style="border:1px solid black;border-radius:3px;background-color:gray;padding:5px;color:black;">Add A Driver</a>	
		<p style="color:red;font-weight:bold;">${errors}</p>
	</form>
</body>
</html>