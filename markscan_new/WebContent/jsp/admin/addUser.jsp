<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="../header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<title>new user registartion from</title>
<style type="text/css">
form {
	margin-left: 80px;
}
</style>

<!-- <link rel='stylesheet' href='css/js-form-validation.css' type='text/css' /> -->
<script src="js/sample-registration-form-validation.js"></script>
</head>
<body onload="document.registration.userid.focus();">

	<form name='registration' onSubmit="return formValidation();"
		method="post" action="addUserPost">
		<h1>New User Registration Form</h1>
		<p>Use tab keys to move from one input field to the next.</p>



		<table>
			<tr>
				<td>User id:</td>
				<td><input type="text" name="usrid" size="12" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="text" name="passid" size="12" /></td>
			</tr>
			<tr>
				<td>User Name:</td>
				<td><input type="text" name="username" size="50" /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="text" name="email" size="50" /></td>
			</tr>
			<tr>
				<td>Status:</td>
				<td><li><input type="radio" name="status" value="1"
						checked /> <span>Active</span></li>
					<li><input type="radio" name="status" value="0" /> <span>Not
							Active</span></li></td>
			</tr>
			<tr>
				<td>Team:</td>
				<td><input type="radio" name="gender" value="2" checked>
					Enforcement <input type="radio" name="gender" value="1">
					Detection</td>
			</tr>
		</table>


		<input type="submit" name="submit" value="Create User" />

	</form>
</body>
</html>
