

<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-us">


<head>
<style type="text/css">
form {
	margin: auto auto auto 184px;
}

h2 {
	margin: auto;
}
</style>
</head>
<body class="wide comments example">
	<br>
	<h2>Whois Lookup</h2>
	<hr>

	<form action="getWhoislookup" method="post">
		<textarea rows="10" cols="50" id='ip_add' name='ip_add'></textarea>
		<br> <input type="submit" value="Get Whois Details "
			style="margin-left: 400px;">
	</form>
	<hr>
	<h2 style="color: red;">
		<s:property value="errMsg" />
	</h2>
	<h3>you can add maximum 15 domain at a time, followed by "comma" ","
		Separator. <br>This Process may take some time, wait for complete
		the process!!!</h3>
</body>
</html>