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

		$('select#usid').change(function() {
			var selectedText = $(this).find('option:selected').val();
			//alert(selectedText);
			$('input#userid').val(selectedText);
		});
	});
</script>

</head>
<body onload="document.registration.userid.focus();">

	<form name='registration' 
		method="post" action="grantPuser">
		<h1> User Access Form</h1>
		

		<table>
			<tr>
				<s:select id="usid" name="usid" list="usrData" listKey="id"
					listValue="name" headerKey="0" headerValue="Select User"
					label="Select User" cssStyle="width: 170px;" />

			</tr>
			<tr>

				<td><s:textfield name="userid" id="userid" value=""
						label="User Id" readonly="true"></s:textfield></td>
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

		<input type="submit" name="submit" value="Grant Privilege" />

	</form>
</body>
</html>
