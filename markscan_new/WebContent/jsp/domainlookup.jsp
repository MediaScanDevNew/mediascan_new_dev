

<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-us">


<head>


</head>
<body class="wide comments example">
	<br>
	<h2>Domain Lookup</h2>
	<hr>
	<div>
		<!-- action="getdomainlookup" -->
		<form method="post" action="getdomainlookup">
			<textarea rows="10" cols="50" id='ip_add' name='ip_add'></textarea>
			<br> <input type="submit" value="Get Domain Details "
				style="margin-left: 400px;">
		</form>
	</div>
	<hr>
	<h2>
		you can add maximum 30 domain at a time, followed by "comma" ","
		Separator. <br>This Process may take some time, wait for complete
		the process!!!
	</h2>

	<%--	<br><h2><s:property value="ip_add" /> Details</h2>
	<table>
		<s:if test="%{getMapping().isEmpty()}">
   Error
</s:if>
		<s:else>
			<s:iterator value="mapping">
				<tr>
					<td><s:property value="key" /></td>
					<td><s:property value="value" /></td>
				</tr>
			</s:iterator>
		</s:else>
	</table> --%>

</body>
</html>