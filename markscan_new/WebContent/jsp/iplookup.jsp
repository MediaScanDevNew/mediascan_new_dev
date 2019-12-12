

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
	<h2>Ip Lookup</h2>
	<hr>

	<form action="getIPlookup" method="post">
		<textarea rows="10" cols="50" id='ip_add' name='ip_add'></textarea>
		<br> <input type="submit" value="Get IP Details "
			style="margin-left: 400px;">
	</form>
	<hr>
	<h2 style="color: red;">
		<s:property value="errMsg" />
	</h2>
	<h3>paste Ip address followed by "comma" "," and you can add
		up to 100 (maximum) IP Addresses.</h3>
</body>
</html>