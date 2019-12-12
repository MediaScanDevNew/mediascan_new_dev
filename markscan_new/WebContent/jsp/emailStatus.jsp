
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-us">
<head>
<title>Diplay data</title>

<link rel="stylesheet" href="css/mystyle.css" type="text/css"
	media="print, projection, screen" />
<script type="text/javascript" src="js/jquery-latest.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.pager.js"></script>
<script type="text/javascript" src="js/table_script.js"></script>
<script type="text/JavaScript">
	// FUNCTION CODE
	function gjCountAndRedirect(secounds, url) {

		$('#gj-counter-num').text(secounds);

		$('#gj-counter-box').show();

		var interval = setInterval(function() {

			secounds = secounds - 1;

			$('#gj-counter-num').text(secounds);

			if (secounds == 0) {

				clearInterval(interval);
				window.location = url;
				$('#gj-counter-box').hide();

			}

		}, 1000);

		$('#gj-counter-box').click(function() {
			clearInterval(interval);
			window.location = url;

		});
	}

	// USE EXAMPLE
	$(document).ready(function() {
		//call
		gjCountAndRedirect(10, document.URL);
	});
</script>
<style>
@
keyframes gjPulse { 0% {
	width: 90px;
	height: 90px;
}

25%
{
width
:
 
105
px
;

    
height
:
 
105
px
;

  
}
50%
{
width
:
 
130
px
;

    
height
:
 
130
px
;

  
}
75%
{
width
:
 
110
px
;

    
height
:
 
110
px
;

  
}
100%
{
width
:
 
90
px
;

    
height
:
 
90
px
;

  
}
}
#gj-counter-box {
	margin: auto;
	position: absolute;
	top: 0;
	left: 0;
	bottom: 0;
	right: 0;
	opacity: 0.2;
	width: 90px;
	height: 90px;
	background-color: rgb(183, 0, 0);
	border-radius: 50%;
	border: 6px solid white;
	visibility: none;
	display: none;
	animation: gjPulse 1s linear infinite;
}

#gj-counter-box:hover {
	opacity: 1;
	cursor: pointer;
}

#gj-counter-num {
	position: relative;
	text-align: center;
	margin: 0px;
	padding: 0px;
	top: 50%;
	transform: translate(0%, -50%);
	color: white;
}
</style>
</head>
<body>
	<table>
	<tr>
	<td>Email Status: </td><td><s:property value="invalidQuery" /></td>
	</tr>
	</table>
	<div id="gj-counter-box">
		<h1 id="gj-counter-num"></h1>
	</div>
</body>


</html>