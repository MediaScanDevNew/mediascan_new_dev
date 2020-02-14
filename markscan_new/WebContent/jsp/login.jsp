<%-- <%@include file="/menu/header.jsp"%> --%>

<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Markscan - Sign in</title>
	<link type="text/css" rel="stylesheet" href="./css/common2.css">
	<link type="text/css" rel="stylesheet" href="./css/master2.css">
<script type="text/javascript">
	window.onload = function() {
		if (document.getElementById("signin_wrapper"))
			document.getElementById("username").focus();
	}
</script>
</head>

<body style="background: #f6f5ee;">
	<div id="signin_wrapper">
		<h3>
			Sign in to <a href="#" target="_top"><img src="./image/logo.png"
				title="Faviki"/></a>
		</h3>
		<form action="signin" method="post">
			<label class="big_i" for="username">Username:</label>
			<input class="big_i" name="user" id="username" maxlength="50"
				type="text" />
			<br> <label class="big_i" for="pass">Password:</label> <input
				class="big_i" value="" name="passwd" maxlength="15" type="password" />
			<br>

				<div class="remember_holder">
					<input id="remember" name="remember" checked="checked" value="yes"
						type="checkbox" /> <label for="remember" class="small">Remember
						me on this computer</label>
				</div> <input name="submit" value="Sign in" class="butt" type="submit">
		</form>




	</div>

	<%-- <script src="./js/widget" type="text/javascript"></script> --%>
	<script type="text/javascript">
		RPXNOW.overlay = true;
		RPXNOW.language_preference = 'en';
	</script>


</body>
</html>