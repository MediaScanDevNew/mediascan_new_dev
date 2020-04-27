
<%@include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script>
	function validateForm() {
		var x = document.forms["myForm"]["invlink"].value;
		var y = document.forms["myForm"]["reasn"].value;
		if (x == "") {
			alert("Link ID must be filled out");
			return false;
		}

		if (y == "") {
			alert("Reason must be filled out");
			return false;
		}
		if (y.length < 10) {
			alert("Reason must have 10 characters");
			return false;
		}
	}
</script>
</head>
<body>
	<fieldset>
		<legend>Mark Data Invalid</legend>
		<form name="myForm" action="invalid_marked"
			onsubmit="return validateForm()" method="post">
			<table>
				<tr>
					<td>Link ID</td>
					<td><textarea rows="4" cols="50" name="invlink"></textarea></td>
				</tr>
				<tr>
					<td>Reason</td>
					<td><textarea rows="4" cols="50" name="reasn"></textarea></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Invalid"></td>
				</tr>
			</table>
		</form>
	</fieldset>
	<textarea rows="4" cols="50" name="reasn"><s:property
			value="browser_name" /></textarea>
	<br> enter link id followed by Comma for mark link invalid
	<br> reason field is mandatory
</body>
</html>
