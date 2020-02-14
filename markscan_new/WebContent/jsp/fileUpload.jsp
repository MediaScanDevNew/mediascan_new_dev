<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="header.jsp"%>
<html>
<head>
<link href="report_table/bootstrap.min.css" rel="stylesheet">
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
					<td>Link type<br> Live = 1, Recorded = 2, Social Media = 3,<br>
						Mobile = 4 , InfoHash = 5, YouTube = 6,
					</td>
					<td>DATA TYPE<br> System Manual = 1,<br> Manual = 2
					</td>
					<td>File name<br> if file not available add 0(Zero)</td>
					<td>Infringing Link</td>
				</tr>
			</table>
		</div>
		<br>
		<s:form  method="POST"
			enctype="multipart/form-data" onsubmit="uploadSourceFile(this)">
			<input type="hidden" id="redirect" name="redirect" value="<%=request.getParameter("rr")%>">
			<label for="clientId" >Select Client:</label>&nbsp;&nbsp;
		      <select  id="clientId" name="clientId">
	                             
			    </select>
			    </br></br>
			<s:hidden name="uid" id="uid" value="25"></s:hidden>
			<%-- <s:radio list="#{'0':'Infringing Upload','1':'Source Upload'}" label="Link Type" name="linklogger" ></s:radio> --%>
			<s:file name="uploadFile"  label="Choose File" size="40" />
			<s:submit value="Upload" name="submit" />
		</s:form>
	</center>
	<script src="report_table/jquery.min.js"></script>
	<script src="js/custom/custom.js"></script>
</body>