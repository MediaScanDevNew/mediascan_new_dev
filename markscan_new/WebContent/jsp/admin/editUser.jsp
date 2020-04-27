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
	/* $(document).ready(function() {

		$('select#usid').change(function() {
			var selectedText = $(this).find('option:selected').val();
			//alert(selectedText);
			$('input#userid').val(selectedText);
		});
	}); */
	/* $(document).ready(function() {

		$('select#stat').change(function() {
			var selectedText = $(this).find('option:selected').val();
			//alert(selectedText);
			$('input#status_one').val(selectedText);
		});
	}); */
</script>

</head>
<body>

	<form method="get" action="editUserDone">
		<h1>Update User Data</h1>


		<table>
			<tr>
				<s:select id="userid" name="userid" list="usrData" listKey="id"
					listValue="name" headerKey="0" headerValue="Select User"
					label="Select User" cssStyle="width: 170px;" />

			</tr>
			<%-- <tr>

				<td><s:textfield name="userid" id="userid" value=""
						label="User Id" readonly="true"></s:textfield></td>
			</tr> --%>
			<tr>
				<s:textfield name="passid" id="passid" placeholder="Password"
					label="Password"></s:textfield>
			</tr>
			<tr>
				<s:textfield name="email" id="email" placeholder="E mail"
					label="E mail"></s:textfield>
			</tr>


			<tr>
				<%-- <s:select label="Select Status" name="status" id="status"
					headerKey="-1" headerValue="Select Status"
					list="#{'0':'Inactive','1':'Active'}" cssStyle="width: 170px;" /> --%>
					
					<s:radio label="Status" name="status" list="#{'1':'Active','0':'Inactive'}"/>

			</tr>
			<tr>
				<s:radio label="Team" name="gender" list="#{'2':'Enforcement','1':'Detection'}"/>

			</tr>

		</table>

		<input type="submit" name="submit" value="Update.." />

	</form>
</body>
</html>
