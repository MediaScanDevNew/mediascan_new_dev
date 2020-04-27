<%@include file="img.jsp"%>
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang=''>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/styles.css">
<title></title>
<style type="text/css">
</style>
<noscript>
	<META HTTP-EQUIV="Refresh" CONTENT="0;URL=loginerrorNoJS.jsp">
</noscript>
<style>
/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
}

/* The Close Button */
.close {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.closeTwo {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
</style>

<script type="text/javascript">
	
</script>


<script language='JavaScript' type='text/JavaScript'>
	var ab = [];
	<s:iterator value="#session.module_id" var="myBean">
	ab.push('<s:property/>');
	</s:iterator>
	//	alert(ab);
	//	alert(4 in ab);

	function myFunction() {
		var userType = <s:property value="#session.isSuperUser" />
		var team = <s:property value="#session.team" />

		if (userType == "13") { //only for developer
		} else if (userType == "2") {
			document.getElementById("1").style.display = "none";
		} else if (userType == "1") {

			document.getElementById("11").style.display = "none";
			document.getElementById("3").style.display = "block";
			document.getElementById("14").style.display = "block";
			document.getElementById("13").style.display = "none";
			document.getElementById("16").style.display = "none";
			document.getElementById("17").style.display = "block";
			//document.getElementById("18").style.display = "none";
			//document.getElementById("19").style.display = "none";
			document.getElementById("20").style.display = "block";
			document.getElementById("24").style.display = "none";
			document.getElementById("22").style.display = "none";
			document.getElementById("25").style.display = "block";

			for (var i = 0; i < ab.length; ++i) {
				document.getElementById(ab[i]).style.display = "block";
			}

		}

		else {
			document.getElementById("3").style.display = "none";
			document.getElementById("9").style.display = "none";
			document.getElementById("10").style.display = "none";
			document.getElementById("11").style.display = "none";
			//	document.getElementById("12").style.display = "block";
			document.getElementById("13").style.display = "none";
			document.getElementById("14").style.display = "none";

			document.getElementById("16").style.display = "none";
			document.getElementById("17").style.display = "none";

			//	document.getElementById("20").style.display = "block";
			document.getElementById("21").style.display = "none";
			document.getElementById("22").style.display = "none";
			document.getElementById("25").style.display = "none";
			//	alert('start');
			for (var i = 0; i < ab.length; ++i) {

				document.getElementById(ab[i]).style.display = "block";
			}
			//	alert('complete');
		}
		if (team == "2") {
			//	alert('team');
			document.getElementById("10").style.display = "block";

			document.getElementById("11").style.display = "block";
			document.getElementById("12").style.display = "block";
			document.getElementById("13").style.display = "none";
			document.getElementById("16").style.display = "block";
			document.getElementById("20").style.display = "block";
			document.getElementById("21").style.display = "block";
			document.getElementById("22").style.display = "none";
			document.getElementById("23").style.display = "none";
			document.getElementById("24").style.display = "block";
			document.getElementById("25").style.display = "block";

		}

	}
</script>

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
	top: 25%;
	left: 25%;
	width: 50%;
	height: 50%;
	padding: 16px;
	border: 16px solid orange;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}
</style>


</head>

<body onload="myFunction()">

	<div id='cssmenu'>
		<ul>
			<li id="1"><a href='homepage'><span>Home</span></a></li>
			<!--  <li id="21"><a href='dashboard'><span>Dash Board</span></a></li>  -->

			<li class='active has-sub' id="21"><a href='#'><span>Dash
						Board</span></a>
				<ul>
					<li class='has-sub' id="23"><a href='dashboard'><span>
								Detection Team</span></a></li>
					<li class='has-sub' id="24"><a href='dashboardEnfo'><span>Enforcement
								Team</span></a></li>


				</ul></li>



			<li class='active has-sub'><a href='#'><span>Data/Report</span></a>
				<ul>
					<li class='has-sub' id="2"><a href='extractorAction'><span>
								Data Extraction</span></a></li>
					<li class='has-sub' id="3"><a href='zipextractorAction'><span>
								ScreenShot Extraction</span></a></li>
					<!-- <li class='has-sub' id="2"><a href='dataImport_old'><span>
								` Upload</span></a></li> -->

					<li class='has-sub'><a href='#'><span>IWL Engine </span></a>

						<ul>
							<li><a href="bl_dataQC">IWL Engine Data QC & Upload</a></li>

							<li><a href="bl_dataEXPORTpre">IWL Engine Data Export</a></li>
						</ul></li>


					<!-- <li><a href="bl_dataUPLOAD">BlackList Data Upload</a></li>  -->

					<li class='has-sub' id="3"><a href='#'><span>File
								Upload <!-- <img src="./image/source.gif" alt="new beta version"
								width="85" height="20"> -->
						</span></a>

						<ul>
							<li><a href="dataImport?rr=source">Source Upload</a></li>
							<li><a href="dataImport?rr=inf">Infringing Upload</a></li>
							<li><a href="dataImport?rr=dm">DailyMotion Upload</a></li>
							<!-- <li><a href="ytUpload">YouTube Upload</a></li>
							<li><a href="ftpImport">ScreenShot Upload</a></li> -->
						</ul></li>


					<li class='has-sub' id="4"><a href='projectDashboard'><span>Project
								DashBoard</span></a></li>
					<li class='has-sub' id="14"><a href='addNewProject'><span>Create
								New Project</span></a></li>
					<!-- 	<li class='has-sub' id="15"><a href='updateProjectPre'><span>Update
								Project</span></a></li>
				 -->
					<li class='has-sub' id="17"><a href='client_pre'><span>Client
								Create/ Update</span></a></li>
					<!-- 	<li class='has-sub' id="18"><a href='source_Status_pre'><span>Source
								Link status</span></a></li>
					<li class='has-sub' id="19"><a href='playwireUploadPre'><span>PlayWire
								Convert</span></a></li>
					 <li class='has-sub'><a href='linktakedown'><span>Update
								Link Status</span></a></li> -->
				</ul></li>
			<li class='active has-sub'><a href='#'><span>BOT
						Setup</span></a>
				<ul>
					<li class='has-sub' id="5"><a href='psetup'><span>BOT
								Setup</span></a></li>
					<li class='has-sub' id="6"><a href='addKeyphrase'><span>Add
								KeyPhrase </span></a></li>
					<!-- <li class='has-sub'><a href='#'><span>Key Phrase</span></a>
						<ul>
							<li><a href="#">Add Key Phrase</a></li>
							<li><a href="#">Update Key Phrase</a></li>
							<li><a href="#">DesiPlex Link</a></li>

						</ul></li> -->
					<li class='has-sub' id="7"><a href='deletefilter'><span>Delete
								Filter & Export Data</span></a></li>
								
					<li class='has-sub' id="8"><a href='#'><span>
					<!--  BOT Running Status-->
					</span></a></li>
					<li class='has-sub' id="9"><a href='#'><span>
					<!--  Empty BOT Queue-->
					</span></a></li>
					<li class='has-sub'><a href='#'><span>
					<!--  BlackList Sites add/delete -->
					</span></a></li>
								 
				</ul></li>
			<li class='active has-sub'><a href='#'><span>Data
						Verification</span></a>
				<ul>
					<li class='has-sub' id="10"><a href='pmqc'><span>Project
								Manager QC</span></a></li>
					<!-- <li class='has-sub' id="11"><a href='two?pageid=9'><span>OPS
								QC</span></a></li> -->
					<li class='has-sub' id="11"><a href='opsqc'><span>OPS
								Manager QC</span></a></li>

					<li class='has-sub' id="12"><a href='contentReplacePre'><span>Content
								Replacement</span></a></li>
					<li class='has-sub' id="20"><a
						href='EnforcementContentReplacement'><span>Content
								Replacement (Enforcement)</span></a></li>



					<li class='has-sub' id="25"><a href='graywhite'><span>Gray
								List/White List </span></a>
						<ul>
							<li><a href="whitelist">White List</a></li>
							<li><a href="whitelist2"> SM White List (Facebook,
									Twitter)</a></li>
							<li><a href="whiteInsta">SM White List (Instagram)</a></li>
							<li><a href="greylist">Gray List</a></li>
							<li><a href="whiteYT">YouTube White List</a></li>
						</ul></li>

					<!-- <li class='has-sub' id="250"><a
								href="greylist">Gray List</a></li>-->

				</ul></li>

			<li class='active has-sub' id="13"><a href='#'><span>Administration</span></a>
				<ul>
					<li class='has-sub'><a href='#'><span>User Account</span></a>
						<ul>
							<li><a
								href="adduser?userid=<s:property
							value='#session.uid' />">create
									New User</a></li>
							<li><a
								href="edituser?userid=<s:property
							value='#session.uid' />">Edit
									User Details</a></li>
						</ul></li>
					<li class='has-sub'><a href='#'><span>Tool
								Privilege</span></a>
						<ul>
							<li><a
								href="accessPuser?userid=<s:property
							value='#session.uid' />">Access
									Privilege</a></li>
							<li><a
								href="revokePuser?userid=<s:property
							value='#session.uid' />">Revoke
									Privilege</a></li>
						</ul></li>


					<!-- <li class='has-sub'><a href='dashboard'><span>DashBoard</span></a></li>  -->

				</ul></li>

			<li class='active has-sub' id="16"><a href='#'><span>Enforcement</span></a>
				<ul>

					<li class='has-sub'><a href='masterEmail'><span>Master
								Email</span></a></li>
					<!--  <li class='has-sub' ><a href='#'><span>Email Import</span></a></li> -->
					<!--<li class='has-sub' id="17"><a href='emailImport'><span>Email
								Upload</span></a></li> -->

					<li class='has-sub'><a href='gtracker'><span>Google
								Tracker</span></a></li>
					<li class='has-sub'><a href='invalidmark'><span>Mark
								Link Invalid </span></a></li>
					<li class='has-sub'><a href='emailImport'><span>Email
								Upload</span></a></li>
					<li class='has-sub'><a href='emailQC'><span>Email
								QC</span></a></li>
					<li class='has-sub'><a href='findModuleEmail'><span>Email
								Shoot</span></a></li>
					<li class='has-sub'><a href='emailStatus' target="_blank"><span>Email
								Shoot Status</span></a></li>

					<li class='has-sub'><a href='emailAddUpdate'><span>Email
								Add/Update</span></a></li>

					<li class='has-sub'><a href='#'><span>IP / Domain
								Lookup</span></a>
						<ul>
							<li><a href="ipLookup">IP Lookup</a></li>
							<li><a href="domainLookup">Domain Lookup</a></li>
							<li><a href="whois">Whois</a></li>
						</ul></li>


				</ul></li>


			<li class='active has-sub' id="22"><a href='#'><span>Black
						List Tool</span></a>
				<ul>
					<!-- 		<li class='has-sub'><a href='blacklistpre'><span>Black
								List Tool Setup</span></a></li>
					<li class='has-sub'><a href='exportblacklistpre'><span>Export
								Black List Tool Data</span></a></li> -->
				</ul></li>




			<li class='active has-sub' style="float: right"><a href='#'>Welcome:
					<span style="font-weight: bold;"> <!-- Mansi kain	 --> <s:property
							value="#session.user" /> <s:hidden name="uid" id="uid"
							value="#session.uid"></s:hidden> <s:if test="uid=='null'">
							<meta http-equiv="Refresh" content="0;URL=sessionOut" />
						</s:if> <s:elseif test="uid==">
							<meta http-equiv="Refresh" content="0;URL=sessionOut" />
						</s:elseif>
				</span>
			</a></li>
			<li class='active has-sub' style="float: right"><a href='logout'><span
					style="display: block; font-weight: bold; color: red;">Logout</span></a>

			</li>

		</ul>
	</div>



</body>
</html>
