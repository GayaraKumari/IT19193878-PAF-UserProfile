<%@page import= "com.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Profile Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/User.js"></script>

</head>
<body>

<h1>Sign Up</h1>


	<form id="formUser" name="formUser" method="post" action="User.jsp">
 		Full Name:
		<input id="Name" name="Name" type="text" placeholder="Full Name" 
		 class="form-control form-control-sm">
		 
		<br> Email:
		<input id="Email" name="Email" type="email" placeholder="name@example.com"
 		class="form-control form-control-sm">
 		
		<br> Address:
		<input id="Address" name="Address" type="text" placeholder="Address"
 		class="form-control form-control-sm">
 		
		<br> User Name:
		<input id="Username" name="Username" type="text" placeholder="User Name"
 		class="form-control form-control-sm">
 		
		<br>Password:
		<input id="Password" name="Password" type="password" placeholder="Password"
 		class="form-control form-control-sm">
 		<br>
 
 
		<input id="btnSave" name="btnSave" type="button" value="Save"
 		class="btn btn-primary">
		<input type="hidden" id="hidUserIDSave" name="hidUserIDSave" value="">
	</form>

		
 		 <div id="alertSuccess" class="alert alert-success"></div>
 		<div id="alertError" class="alert alert-danger"></div>

<br>

<div id="divUserGrid">

<%
 User Obj = new User(); 
 out.print(Obj.readDetails()); 
%>

</div>





</body>
</html>