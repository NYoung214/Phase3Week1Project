<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Driver Search</title>
</head>
<body>
	<form action="/search" method="post">
		<h1>DRIVER DATABASE SEARCH</h1>
		<p style="color:blue;font-weight:bold;">${success}</p>
		<p>Driver ID:<input type="text" name="driverId" placeholder="1234567"/>
		
		<%
		boolean edit =false, search =false;
		if(!(request.getAttribute("option")==null)){
			String check = (String) request.getAttribute("option");
			switch(check){
			case "edit":
				edit = true;
				break;
			case "search":
				search = true;
				break;
			default:
				break;
			}
		}
		%>
		
		Search or Edit<select name="option">
		  <option value="edit" <% if(edit){out.println("SELECTED");} %>>EDIT</option>
		  <option value="search" <% if(search){out.println("SELECTED");} %>>SEARCH</option>
		</select>
		
		</p>
		<input type="submit" name="search" value="Submit"/>
		<a href="/add" style="border:1px solid black;border-radius:3px;background-color:gray;padding:5px;color:black;">Add A Driver</a>	
		<p style="color:red;font-weight:bold;">${errors}</p>
	</form>
</body>
</html>