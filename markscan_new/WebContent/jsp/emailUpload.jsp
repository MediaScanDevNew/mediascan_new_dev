<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="header.jsp"%>
<html>
<head>
<style>
table.a {
	border-collapse: collapse;
	border: 1px solid black;
}

 td {
	border: 1px solid black;
}

table.wwFormTable {
	border-collapse: collapse;
	border: 1px solid black;
}

 td {
	border: 1px solid black;
}
</style>
</head>

<body><center>
<h1>Email Upload</h1>
<br>
<div>
	<table><tr>
	<td>ID</td>
	<td>URL</td>
	<td>Domain Name</td>
	<td>Project ID</td>
	<td>Email</td>
	<td>Email Type<br>1--> mobile application link<br>2-->Web Link<br>3-->Advance Caution Notice</td>
	<td>Renotification ID</td>
	<td>ISP</td>
	
	</tr></table>
</div>
<s:form action="eamiluploadAction" method="POST"
	enctype="multipart/form-data">
		<s:hidden name="uid" id ="uid" value="25"></s:hidden>
		<%-- <s:radio list="#{'0':'Infringing Upload','1':'Source Upload'}" label="Link Type" name="linklogger" ></s:radio> --%>
	<s:file name="uploadFile" label="Choose File" size="40" />
	<s:submit value="Upload" name="submit" />
</s:form>
</center>
</body>