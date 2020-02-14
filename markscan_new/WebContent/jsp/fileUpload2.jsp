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


		<div>


			<p style="color: red">File size is not greater then 10 MB</p>
			<p>Single upload for file upload infringing / source</p>

			<p>File design type is</p>
			<table class="a">
				<tr>
					<td>Source/ Infringing Link</td>
					<td>Project Id</td>
					<td>Note1</td>
					<td>Note2</td>

					<td>Domain name</td>
					<td>Link type<br> Live = 1, Recorded = 2, Youtube = 3,
						Mobile = 4 , InfoHash = 5
					</td>
					<td>DATA TYPE<br> System Manual = 1, Manual = 2
					</td>
					<td>Infringing Link</td>
				</tr>
			</table>
		</div>

		<br>
		<s:form action="uploadAction_old" method="POST"
			enctype="multipart/form-data">
			<input type="hidden" id="redirect" name="redirect" value="<%=request.getParameter("rr")%>">
			
			<s:hidden name="uid" id="uid" value="25"></s:hidden>
			<%-- <s:radio list="#{'0':'Infringing Upload','1':'Source Upload'}" label="Link Type" name="linklogger" ></s:radio> --%>
			<s:file name="uploadFile" label="Choose File" size="40" />
			<s:submit value="Upload" name="submit" />
		</s:form>
	</center>
</body>