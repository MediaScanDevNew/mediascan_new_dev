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

<body>
	<center>
	<h3>Playwire Single Link Convertion</h3>
		<s:form method="get" action="Single_playwire_convert">
			<s:textfield name="playwireSingleLink" label="Link for Convert"></s:textfield>
			<s:submit value="Convert Link"></s:submit>
		</s:form>
		<table>
			<tr>
				<td><s:property value="playwireSingleLink" /></td>
				<td><s:property value="playwireSingleConvertedLink" /></td>
			</tr>
		</table>



		<hr>
		<div>
			<div>
				<p style="color: red">File size is not greater then 10 MB</p>
				<h3>Bulk Playwire link conversion</h3>

				<p>File design type is</p>
				<table class="a">
					<tr>
						<td>playwire links</td>

					</tr>
				</table>
			</div>

			<br>
			<s:form action="playwireUploadAction" method="POST"
				enctype="multipart/form-data">
				<%-- <s:hidden name="uid" id ="uid" value="25"></s:hidden> --%>
				<%-- <s:radio list="#{'0':'Infringing Upload','1':'Source Upload'}" label="Link Type" name="linklogger" ></s:radio> --%>
				<s:file name="uploadFile" label="Choose File" size="40" />
				<s:submit value="Upload" name="submit" />
			</s:form>
		</div>
	</center>
</body>