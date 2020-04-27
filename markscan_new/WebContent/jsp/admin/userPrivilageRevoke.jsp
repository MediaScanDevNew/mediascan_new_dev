<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="../header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<title>new user previlage from</title>
<style type="text/css">
form {
	margin-left: 100px;
}
</style>


<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$('select#userid').change(function() {
			var selectedText = $(this).find('option:selected').text();
			//alert(selectedText);
			$('input#usrid').val(selectedText);
		});
	});
</script>
</head>
<body onload="document.registration.userid.focus();">

	<form name='registration' method="post" action="revokeP">
		<h1>User Access Revoke</h1>


		<table>
			<tr>
				<s:select id="userid" name="userid" list="usrData" listKey="id"
					listValue="name" headerKey="0" headerValue="Select User"
					label="Select User" cssStyle="width: 170px;" />

				<s:textfield name="usrid" id="usrid" value="" readonly="true"></s:textfield>

			</tr>

		</table>
		<input type="submit" name="submit" value="Get Detail" />
	</form>
	<form action="revokePDone" method="post">
		<table>
			<tr>
				<td>User Name</td>
				<td><s:property value="usrid" /> <!-- <input type="text"
					name="usrid" id="usrid">  --> <s:hidden name="userid"
						value="%{userid}" readonly="true"></s:hidden></td>
			</tr>
			<s:iterator value="usrModule" var="um">
				<tr>
					<td><s:property value="%{module_name}" /></td>
					<td><input type="checkbox" name="privilege" class="row_ck"
						id='<s:property value="id"/>' value='<s:property value="id"/>'>
					</td>
				</tr>
			</s:iterator>

		</table>

		<input type="submit" name="submit" value="Revoke Privilege" />

	</form>
</body>
</html>
