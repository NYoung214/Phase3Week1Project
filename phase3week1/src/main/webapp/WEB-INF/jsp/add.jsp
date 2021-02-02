<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Driver Search</title>
</head>
<body>
	<form action="/add" method="post">
		<div style="width:600px;height:auto;">
			<h1>DRIVER DATABASE ADD</h1>		
			<div style="width:49%;height:auto;float:left;">
				<p style="color:blue;font-weight:bold;">${success}</p>
				<p>Driver ID:<input type="text" name="driverId" placeholder="1234567" value="${driverId}"/></p>
				<p>First Name:<input type="text" name="firstName" placeholder="John" value="${firstName}"/></p>
				<p>Middle Name:<input type="text" name="middleName" placeholder="Jacob" value="${middleName}"/></p>
				<p>Last Name:<input type="text" name="lastName" placeholder="Doe" value="${lastName}"/></p>
				<p>Date of Birth:<input type="text" name="DOB" placeholder="1970-01-01" value="${DOB}"/></p>
				<p>Issue Date:<input type="text" name="issueDate" placeholder="1988-01-01" value="${issueDate}"/></p>
				<p>Expiration Date:<input type="text" name="expireDate" placeholder="1993-01-01" value="${expireDate}"/></p>
				<p>Address:<input type="text" name="address" placeholder="123 Sesame Street" value="${address}"/></p>
				<p>City:<input type="text" name="city" placeholder="Denver" value="${city}"/></p>
				<p>State:<input type="text" name="state" placeholder="CO" value="${state}"/></p>
				<p>ZIP Code:<input type="text" name="zip" placeholder="12345" value="${zip}"/></p>
					<%
					boolean male =false, female =false, other =false;
					if(!(request.getAttribute("sex")==null)){
						String check = (String) request.getAttribute("sex");
						switch(check){
						case "male":
							male = true;
							break;
						case "female":
							female = true;
							break;
						case "other":
							other = true;
							break;
						default:
							break;
						}
					}
					%>
				
				<p>Sex:<select name="sex">
				  <option value="male" <% if(male){out.println("SELECTED");} %>>Male</option>
				  <option value="female" <% if(female){out.println("SELECTED");} %>>Female</option>
				  <option value="other" <% if(other){out.println("SELECTED");} %>>Other</option>
				</select></p>
				<input type="hidden" name="operation" value="add"/>
				<input type="hidden" name="trueId" value=""/>
				<input type="submit" value="Add"/>
				<a href="/search" style="border:1px solid black;border-radius:3px;background-color:gray;padding:5px;color:black;">Back To Search</a>
			</div>
			<div style="width:49%;height:auto;float:right;">
				<c:forEach var="error" items="${errors}">
					<p style="color:red;font-weight:bold;">${error}</p>
				</c:forEach>
			</div>		
		</div>
	</form>

	
</body>
</html>