<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<%@include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

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

.tdone {
	width: 502px;
}
.a{
background-color: gray;
}
</style>
</head>
<body>
	<div style="margin-top: 20px; display: block;">
		<s:form method="post" action="e_content_replace">
			<s:textfield name="url" label="URL"></s:textfield>
			<s:submit value="search"></s:submit>
		</s:form>
	</div>

	<hr>

	<s:if test="url_listData.size() > 0">
		<table>
			<s:iterator value="url_listData">
				<tr>
					<td class="a">ID</td>
					<td><s:property value="id" /></td>
					<td class="a">URL</td>
					<td><s:property value="url_group" /></td>
					<td class="a">Email</td>
					<td><s:property value="email_address" /></td>
				</tr>

				<tr>
					<td class="a">Mail Send</td>
					<td>
					<%-- <s:property value="email_module"/> --%>
					<s:if test="%{email_module==1}">
	Send
</s:if> <s:else>
    Not Send
</s:else></td>
					<td class="a">Mail Send Time</td>
					<td><s:property value="date_time" /></td>
					<td class="a">Email Error</td>
					<td><s:property value="attachment_name" /></td>
				</tr>
				<tr>
					<td class="a">QC Perform</td>
					<td>
					 <%-- <s:property value="user_id"/> --%>
					<s:if test="%{user_id==1}">
					QC Done
				</s:if> <s:elseif test="%{user_id==9}">
				    Invalid Link
				</s:elseif> <s:elseif test="%{user_id==0}">
				    QC Pending
				</s:elseif></td>
					<td class="a">Project Name</td>
					<td><s:property value="project_name" /></td>
					<td class="a">Client Name</td>
					<td><s:property value="client_address" /></td>
				</tr>
				<tr>
					<td class="a">User(upload)</td>
					<td><s:property value="channel_name" /></td>
					<td class="a">Renotification ID</td>
					<td><s:property value="clientSubject" /></td>
					<td class="a"></td><td></td>
				</tr>
				<tr style="background-color: grey">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</s:iterator>


		</table>


	</s:if>


</body>
</html>