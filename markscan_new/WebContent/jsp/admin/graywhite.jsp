<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="../header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<!-- <meta charset="utf-8">
 -->
<title>new user registartion from</title>
<style type="text/css">
table {
	border-collapse: collapse;
	margin-top: 10px;
	table-layout: fixed;
}

td {
	height: 100px;
	width: 300px;
	text-align: center;
}

table, td {
	border: 1px solid black;
}

tr.b {
	
}
</style>

<style>
.black_overlay {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 80);
}

.white_content {
	display: none;
	position: absolute;
	top: 15%;
	left: 25%;
	width: 50%;
	height: 55%;
	padding: 16px;
	border: 16px solid orange;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}
</style>

<!-- <link rel='stylesheet' href='css/js-form-validation.css' type='text/css' /> -->
<script src="js/sample-registration-form-validation.js"></script>
</head>
<body onload="document.registration.userid.focus();">

	<center>
		<table>
			<tr class="a">
				<td><a href="javascript:void(0)"
					onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'">
						<button type="button">Add WhiteList</button>
				</a>
					<div id="light" class="white_content">
						<a href="javascript:void(0)"
							onclick="document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'"
							style="margin-left: 620px;"><button type="button">Close</button></a>
						<h2 style="margin-top: -23px">This is the WhiteList contentttttttttt.</h2>
						<p></p>
						<p></p>
						<form action="addwlist">
						   <table>
						     <tr>
						       <td>
						       <s:select id="clientId" name="clientId"
							      list="listData" listKey="id" listValue="client_name" headerKey="0"
							      headerValue="Select Client Name" label="Select Client"
							      cssStyle="width: 170px;" />
						       
						       </td>
						     </tr>
						     <tr>
						        <td>
						          <input type="text" name="wlistData" style="width: 450px;"
									placeholder="Add New White List">
						        </td>
						     </tr>
						   </table>
						     
						   
						   
								
							
							<p>
								<input type="submit" value="Add WhiteList">
							</p>
						</form>
						<p>
							Enter only Domain here, example is mentioned below<br>
						<h3>hotstar.com</h3>

						<h3>google.com</h3>
						<br>
						<blink>
							<h2 style="color: red">Don't put any Link at here</h2>
						</blink>
						</p>
					</div>
					<div id="fade" class="black_overlay"></div></td>

				<td><a href="javascript:void(0)"
					onclick="document.getElementById('light1').style.display='block';document.getElementById('fade').style.display='block'"><button
							type="button">Add Social Media WhiteList</button></a>
					<div id="light1" class="white_content">
						<a href="javascript:void(0)"
							onclick="document.getElementById('light1').style.display='none';document.getElementById('fade').style.display='none'"
							style="margin-left: 620px;"><button type="button">Close</button></a>
						<h2 style="margin-top: -23px">This is the Social WhiteList
							content.</h2>
						<p></p>
						<p></p>
						<form action="addwlist2">
							<p>
								<input type="text" name="wlistData" style="width: 450px;"
									placeholder="Add New Social White List">
							</p>
							<p>
								<input type="submit" value="Add Social WhiteList">
							</p>
						</form>
						<p>
							Enter only full Whitelist Link here, example is mentioned below<br>
						<h3>https://twitter.com/terencehere/</h3>

						<h3>https://www.facebook.com/RaghavJuyal/</h3>
						<br>
						<blink>
							<h2 style="color: red">Don't put any Domain at here</h2>
						</blink>
						</p>
					</div>
					<div id="fade" class="black_overlay"></div></td>
				<td><a href="javascript:void(0)"
					onclick="document.getElementById('light2').style.display='block';document.getElementById('fade').style.display='block'"><button
							type="button">Add GrayList</button> </a>
					<div id="light2" class="white_content">
						<a href="javascript:void(0)"
							onclick="document.getElementById('light2').style.display='none';document.getElementById('fade').style.display='none'"
							style="margin-left: 620px;"><button type="button">Close</button></a>
						<h2 style="margin-top: -23px">This is the GrayList content.</h2>
						<p></p>
						<p></p>
						<form action="glist">
							<p>
								<input type="text" name="wlistData" style="width: 450px;"
									placeholder="Add New Gray List">
							</p>
							<p>
								<input type="submit" value="Add GrayList">
							</p>
						</form>
						<p>
							Enter only Gray list Domain here, example is mentioned below<br>
						<h3>zeenews.india.com</h3>

						<h3>thehindu.com</h3>
						<br>Basically news portals are coming under Graylist <br>
						<blink>
							<h2 style="color: red">Don't put any Link at here</h2>
						</blink>
						</p>
					</div>
					<div id="fade" class="black_overlay"></div></td>
			</tr>
			<tr class="b">
				<td><a href="javascript:void(0)"
					onclick="document.getElementById('light3').style.display='block';document.getElementById('fade').style.display='block'"><button
							type="button">Instagram 4 Whitelist</button> </a>
					<div id="light3" class="white_content">
						<a href="javascript:void(0)"
							onclick="document.getElementById('light3').style.display='none';document.getElementById('fade').style.display='none'"
							style="margin-left: 620px;"><button type="button">Close</button></a>
						<h2 style="margin-top: -23px">This is the Instagram White
							List content.</h2>
						<p></p>
						<p></p>
						<form action="wInsta">
							<p>
								<input type="text" name="wlistData" style="width: 450px;"
									placeholder="Add New Instagram in List">
							</p>
							<p>
								<input type="submit" value="Add Instagram">
							</p>
						</form>
						<p>
							Enter only Instagram Link upload here, example is mentioned below<br>
						<h3>https://www.instagram.com/p/Bd7TBH0hb2a/?taken-by=remodsouza</h3>

						<h3>https://www.instagram.com/p/BecaTXmgVAk/?taken-by=raghavjuyal</h3>
						<br>
						<blink>
							<h2 style="color: red">only instagram link upload at here</h2>
						</blink>
						</p>
					</div>
					<div id="fade" class="black_overlay"></div></td>


				<td><a href="javascript:void(0)"
					onclick="document.getElementById('light4').style.display='block';document.getElementById('fade').style.display='block'"><button
							type="button">YouTube Whitelist</button> </a>
					<div id="light4" class="white_content">
						<a href="javascript:void(0)"
							onclick="document.getElementById('light4').style.display='none';document.getElementById('fade').style.display='none'"
							style="margin-left: 620px;"><button type="button">Close</button></a>
						<h2 style="margin-top: -23px">This is the YouTube White List
							content.</h2>
						<p></p>
						<p></p>
						<form action="wYT">
							<p>
								<input type="text" name="wlistData" style="width: 450px;"
									placeholder="Add New Youtube Whitelist List">
							</p>
							<p>
								<input type="submit" value="Add YouTube">
							</p>
						</form>
						<p>
							Enter only channel id/Uploader id upload here, example is mentioned below<br>
						<h3>remodsouza</h3>

						<h3>raghavjuyal</h3>
						<br>
						<blink>
							<h2 style="color: red">only channel id/Uploader id at here</h2>
						</blink>
						</p>
					</div>
					<div id="fade" class="black_overlay"></div></td>
				<td></td>
			</tr>
		</table>
	</center>

	<br>
	<s:textfield value="%{msg}" cssStyle="color: red; width : 800px;"
		readonly="true"></s:textfield>
	<br>
	<hr>
	<h3 style="color: red;">
		<div id="box">we recommend Mozilla Firefox for best result...</div>
	</h3>
</body>
</html>
