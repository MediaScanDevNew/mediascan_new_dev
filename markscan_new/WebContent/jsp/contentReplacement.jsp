<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%-- <%@taglib prefix="display" uri="http://displaytag.sf.net"%> --%>
<%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("form").hide();
		$("#hide").click(function() {
			/* $("p").hide(); */
			$("form").show();
		});

	});
</script>
<style type="text/css">
table {
	border-collapse: collapse;
	margin-left: 150px;
}

table, th, td {
	border: 1px solid black;
}

th {
	background-color: orange;
	font-weight: bold;
}

.tdLabel {
	width: 150px;
	font-weight: bold;
}

input {
	width: 502px;
}
.tdone
{
	width: 502px;
}
</style>
</head>
<body>

	<p>
	<table style="background-color:rgb(200,200,200);">
		
		<tr>
			<td class="tdLabel">URL</td>
			<td class="tdone"><s:property value="apple" /></td>
		</tr>

		<tr>
			<td class="tdLabel">Domain</td>
			<td class="tdone"><s:property value="ptype_name" /></td>
		</tr>
		<tr>
			<td class="tdLabel">Infringing Link</td>
			<td class="tdone"><s:property value="uniqprojectName" /></td>
		</tr>
		<tr>
			<td class="tdLabel">User Name</td>
			<td class="tdone"><s:property value="propertyName_name" /></td>
		</tr>
		<tr>
			<td class="tdLabel">Client Name</td>
			<td class="tdone"><s:property value="clientname_name" /></td>
		</tr>
		<tr>
			<td class="tdLabel">Property Name</td>
			<td class="tdone"><s:property value="datatype_name" /></td>
		</tr>
		<tr>
			<td class="tdLabel">Created Date</td>
			<td class="tdone"><s:property value="startdate" /></td>
		</tr>
	</table>


	</p>

	<button id="hide" style="margin-left: 200px;">Edit</button>
	<br>
	<br>
	<form action="contentReplaceUpdate" method="post">
		<table >
			<tr>
				<s:textfield name="clientname" value="%{clientname}" readonly="true"
					label="Id" cssStyle="background-color: grey;"></s:textfield>
			</tr>
			<tr>
				<s:textfield name="apple" value="%{apple}" label="URL"></s:textfield>
			</tr>
			<tr>
				<s:textfield name="ptype_name" value="%{ptype_name}" label="Domain"></s:textfield>
			</tr>
			<tr>
			
				<s:if test="%{uniqprojectName==''}">
					<s:textfield name="uniqprojectName" value="%{uniqprojectName}"
						label="Infringing Link " readonly="true" cssStyle="background-color: grey;"></s:textfield>
				</s:if>
				<s:else>
					<s:textfield name="uniqprojectName" value="%{uniqprojectName}"
						label="Infringing Link "></s:textfield>
				</s:else>
			</tr>
			<tr>
				<s:textfield name="propertyName_name" value="%{propertyName_name}"
					label="User Name" readonly="true"
					cssStyle="background-color: grey;"></s:textfield>
			</tr>
			<tr>
				<s:textfield name="clientname_name" value="%{clientname_name}"
					label="Client Name" readonly="true"
					cssStyle="background-color: grey;"></s:textfield>
			</tr>
			<tr>
				<s:textfield name="datatype_name" value="%{datatype_name}"
					label="Property Name" readonly="true"
					cssStyle="background-color: grey;"></s:textfield>
			</tr>
			<tr>
				<s:textfield name="startdate" value="%{startdate}"
					label="Created Date" readonly="true"
					cssStyle="background-color: grey;"></s:textfield>
			</tr>
		</table>
		<br>
		
		<s:submit value="update" cssStyle="margin-right: 494px;width:60px;"></s:submit>

	</form>
</body>
</html>
