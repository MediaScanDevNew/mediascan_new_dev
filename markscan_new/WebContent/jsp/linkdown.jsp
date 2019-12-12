

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
	<h2>Update Link Status.</h2>
	<hr>

	<form action="linktakedownUpdate" method="post">
		<textarea rows="10" cols="50" id='invlink' name='invlink'></textarea>
		<br> <input type="submit" value="Update Links"
			style="margin-left: 400px;">
	</form>
	<hr>
	<h2 style="color: red;">
		<s:property value="browser_name" />
	</h2>
	<h3>paste IDs followed by "comma" "," and you can add
		up to 300 (maximum) IDs.</h3>
		
</body>
</html>