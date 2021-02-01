<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Driver</title>
</head>
<body>
	<div style="margin:auto;padding:5px;width:425px;height:250px;">
		<div style="border:5px solid black;border-radius:25px;box-shadow:-10px 8px 8px gray;background-image: linear-gradient(to bottom right, blue, white, red);margin:auto;padding:5px;width:425px;height:250px;">
			<div style="width:100%;height:15%;">
				<div style="float:left;width:39%;height:100%;margin:auto;text-align:center;">
					<h4 style="padding:0;margin:0;color:white;">United States of America</h4>
				</div>
				<div style="float:right;width:59%;height:100%;margin:auto;TEXT-ALIGN:CENTER;">
					<h4>DRIVER LICENSE</h4>
				</div>
			</div>
			<div style="width:100%;height:70%;">
				<div style="border:2px solid black;border-radius:5px;float:left;width:29%;height:95%;margin:auto;text-align:center;background-color:lightgray;">
					<p>Image goes here</p>
				</div>
				<div style="float:right;width:69%;height:100%;margin:auto;">
					<div style="blue;float:left;width:49%;height:50%;margin:auto;font-size:.8em;font-weight:bold;">
						<p>DL: ${driver.getDriverId()}</p>
						<p>DOB: ${driver.getDOB()}</p>
						<p>Sex: ${driver.getSex()}</p>
					</div>
					<div style="float:left;width:49%;height:50%;margin:auto;font-size:.8em;">
						<p>Issue: ${driver.getIssueDate()}</p>
						<p>Expire: ${driver.getExpireDate()}</p>
					</div>
					<div style="float:left;width:100%;height:49%;margin:auto;">
						<p style="padding:0;padding-left:1em;margin:0;font-weight:bold;">${driver.getLastName()}</p>
						<p style="padding:0;padding-left:1em;margin:0;font-weight:bold;"><span>${driver.getFirstName()}</span> <span>${driver.getMiddleName()}</span></p>
						<p style="padding:0;margin:0;margin-top:2px;text-align:center;">${driver.getAddress()}</p>
						<p style="padding:0;margin:0;margin-top:2px;text-align:center;">
						<span>${driver.getCity()}</span>
						<span>${driver.getState()}</span>
						<span>${driver.getZip()}</span></p>
					</div>
				</div>
			</div>
			<div style="width:100%;height:15%;">
				<div style="float:left;font-family:Brush Script MT, Brush Script Std, cursive;margin:0;padding:0;left:0;position:relative;">
					<p style="margin:0;padding:0;">${driver.getFirstName()} ${driver.getLastName()}</p>
				</div>
			</div>
		</div>	
		<a href="/search" style="border:1px solid black;border-radius:3px;background-color:gray;margin-top:10px;padding:5px;color:black;float:right;">Back To Search</a>	
	</div>
		
	
</body>
</html>