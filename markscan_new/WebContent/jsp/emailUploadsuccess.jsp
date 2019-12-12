<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="header.jsp"%>
<html>
 <head>
 <style type="text/css">
table {
	border-collapse: collapse;
}

table, th, td {
	border: 1px solid black;
}
</style>
 </head>
 <body>
   <h1 style="color: red"><s:property value="statusUpdate"/></h1>
 <br>
   File Name : <s:property value="uploadFileFileName"/> <br>
   Content Type : <s:property value="uploadFileContentType"/> <br>
   Temp File Name : <s:property value="uploadFile"/>
   
   <div style="color: red" >wrong data  against below mentioned ID</div>
   <div>
	<table>
		<s:iterator value="errorCollection">
			<tr>
				<td ><s:property value="key" /></td>
				<td style="color:orange ; font:bold;"><s:property value="value" /></td>
			</tr>
			<br>

		</s:iterator>
		</table>
	</div>
	</body>
	</html>