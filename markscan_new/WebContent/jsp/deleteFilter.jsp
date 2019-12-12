<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<%@include file="header.jsp"%>
<%-- <s:set name="theme" value="'simple'" scope="page" /> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reporting</title>
<style type="text/css">

/* for hiding the page banner */
.pagebanner {
	display: none;
}
/* for customizing page links */
.pagelinks {
	color: maroon;
	margin: 20px 0px 20px 170px;
}
/* for shifting all the Export options*/
.exportlinks {
	margin: 20px 0px 20px 30px;
}
/* For changing the spaces between export link */
.export {
	margin-left: 30px;
}
/* For Table css */
table {
	border: 1px solid #666;
	width: 60%;
	margin: 20px 0 20px 0px;
	border-collapse: collapse;
}

table, th, td {
	border: 1px solid black;
}
/* For odd and even row decoration */
tr.odd {
	background-color: #f4f4f4
}

tr.tableRowEven, tr.even {
	background-color: #CCCCCC
}
/* Css for table elements */
th, td {
	padding: 2px 4px 2px 4px;
	text-align: left;
	vertical-align: top;
}

thead tr {
	background-color: #999999;
}
/* For changing the background colour while sorting */
th.sorted {
	background-color: #CCCCCC;
}

th.sorted a, th.sortable a {
	background-position: right;
	display: block;
	width: 100%;
}

th a:hover {
	text-decoration: underline;
	color: black;
}

th a, th a:visited {
	color: black;
}

table.three {
	
}

tr.as {
	border: solid 1px;
}
</style>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() { // for client name 
		$('#projecttype').change(function(event) {
			var country = $("select#projecttype").val(); // country = define value of selected option
			//	alert(country);
			$.getJSON('ajaxAction0', {
				ptype : country
			}, function(jsonResponse) {
				$('#ajaxResponse').text(jsonResponse.dummyMsg);
				var select = $('#clientname');
				select.find('option').remove();
				$.each(jsonResponse.stateMap, function(key, value) {
					$('<option>').val(key).text(value).appendTo(select);
				});
			});
		});
	});

	$(document).ready(function() { // for tv show name 
		$('#clientname').change(function(event) {
			var country = $("select#clientname").val(); // country = define value of selected option
			//	alert(country);
			var ptypee = $("select#projecttype").val();
			//alert(ptype);
			$.getJSON('ajaxAction1', {
				ctype : country,
				ptype : ptypee
			}, function(jsonResponse) {
				$('#ajaxResponse').text(jsonResponse.dummyMsg);
				var select = $('#propertyName');
				select.find('option').remove();
				$.each(jsonResponse.stateMap, function(key, value) {
					$('<option>').val(key).text(value).appendTo(select);
				});
			});
		});
	});

	$(document).ready(function() { // for keyphrase
		$('#propertyName').change(function(event) {
			//var country = $("select#propertyName").val(); // country = define value of selected option

			var sel = document.getElementById("propertyName");
			var value = sel.options[sel.selectedIndex].value; // or sel.value
			var text = sel.options[sel.selectedIndex].text;
			alert(value + "...choose project Name...  " + text);
			document.getElementById('pvalue').value = text;

		});
	});
</script>
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/pjstyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/jquery-1.12.4.js"></script>
<script src="js/jquery-ui.js"></script>

<link rel="stylesheet" href="css/default.css" type="text/css">

<!-- <link rel="stylesheet" type="text/css" href="css/ddstyle.css"> -->
<style type="text/css">
.Zebra_DatePicker {
	left: 102px;
	margin-top: 106px;
}
</style>
</head>
<body>
	<s:form method="post" action="deletefilterperform" theme="simple">
		<div style="margin-left: 170px;">

			<s:if test="uid=='null'">
				<meta http-equiv="Refresh" content="0;URL=sessionOut" />
			</s:if>


			<table>
				<tr>
					<td>Project Type</td>
					<td><s:select id="projecttype" name="projecttype"
							list="listData" listKey="id" listValue="name" headerKey="0"
							headerValue="Select Project Type" label="Select Project"
							cssStyle="width: 170px;" /></td>
				</tr>
				<tr>
					<td>Client Name</td>
					<td><s:select id="clientname" name="clientname"
							list="{'Select Client'}" label="Select Client"
							cssStyle="width: 170px;" /></td>
				</tr>
				<tr>
					<td>Project Name</td>
					<td><s:select id="propertyName" name="propertyName"
							list="{'Select Property'}" label="Select Property"
							cssStyle="width: 170px;" /></td>
				</tr>
				<%--Date  --%>
				<tr>
				<td>Start Date</td>
				<td><s:textfield  name="startdate" id="datepicker1"
				cssStyle="width: 170px;"></s:textfield></td>
				</tr>
				<tr>
				<td>End Date</td>
			<td><s:textfield  name="enddate" id="datepicker2"
				cssStyle="width: 170px;"></s:textfield></td>
			</tr>
				<%-- <tr>
					<td></td>
					<td><s:textfield name="oneField"></s:textfield></td>
				</tr> --%>
			</table>
		</div>
		<s:hidden name="pvalue" id="pvalue">
		</s:hidden>
		
		<div style="margin-left: 35px; margin-right: 35px;">
			<fieldset>
				<!-- <legend>Keyword based Manual Delete Filter</legend> -->
				
				<center>
				 
					<table>
						<tr class="as">Keyword based Manual Delete Filter
						</tr>
						<tr>
							<td><input type="text" name="one" value="Game" /></td>
							<td><input type="text" name="two" value="Login" /></td>
							<td><input type="text" name="three" value="Member" /></td>
							<td><input type="text" name="four" value="Privacy" /></td>
						</tr>
						<tr>
							<td><input type="text" name="five" value="Support" /></td>
							<td><input type="text" name="six" value="Register" /></td>
							<td><input type="text" name="seven" value="Profile" /></td>
							<td><input type="text" name="eight" value="Policy" /></td>
						</tr>
						<tr>
							<td><input type="text" name="nine" value="Search" /></td>
							<td><input type="text" name="ten" value="Copyright" /></td>
							<td><input type="text" name="eleven" value="Category" /></td>
							<td><input type="text" name="twelve" value="Message" /></td>
						</tr>
						<tr>
							<td><input type="text" name="thirteen" value="Comment" /></td>
							<td><input type="text" name="fourteen" value="Terms" /></td>
							<td><input type="text" name="fifteen" value="Tag" /></td>
							<td><input type="text" name="sixteen" value="News" /></td>
						</tr>
						<tr>
							<td><input type="text" name="seventeen" value="About" /></td>
							<td><input type="text" name="eighteen" value="Forum" /></td>
							<td><input type="text" name="nineteen" value="Image" /></td>
							<td><input type="text" name="twenty" value="Kbps" /></td>
						</tr>
						<tr>
							<td><input type="text" name="twentyone" /></td>
							<td><input type="text" name="twentytwo" /></td>
							<td><input type="text" name="twentythree" /></td>
							<td><input type="text" name="twentyfour" /></td>
						</tr>
						<!--  
						 <tr>
							<td><input type="text" name="twentyfive" /></td>
							<td><input type="text" name="twentysix" /></td>
							<td><input type="text" name="twentyseven" /></td>
							<td><input type="text" name="twentyeight" /></td>
						</tr> 
						-->
					</table>
				</center>
				

			</fieldset>
		</div>
		

		<div>
			<s:submit value="Export Data" cssStyle="margin-left: 788px;"></s:submit>
		</div>

	</s:form>

	<%-- <display:table id="masterCrawluurl" name="masterCrawluurl" pagesize="10" cellpadding="5px;"
		cellspacing="5px;" style="margin-left:17px;margin-top:20px;"
		requestURI="" export="true">
		<display:column property="id" title="ID" />
		<display:column property="crawle_url2" title="Link" />
		<display:column property="clientName" title="Client Name" />
		<display:column property="created_on" title="Date" />
		<display:column property="user_id" title="User ID" />
		<display:column property="project_id" title="Project ID" />
		<display:column property="link001" title="Link Logger Link" />
		<display:column property="domainName" title="Domain Name" />
		<display:column property="date__c" title="Date" />
		<display:column property="google_dmca_new" title="Google Notify" />
		<display:column property="note1" title="Note 1" />
		<display:column property="note2" title="Note 2" />
		<display:setProperty name="export.excel.filename"
			value="CrawlData.xls" />
		<display:setProperty name="export.csv.filename"
			value="Infringing_source_Details.csv" />
	</display:table> --%>
	<script >
	$(function() {
		$("#datepicker1").datepicker();
	});
	$(function() {
		$("#datepicker2").datepicker();
	});
	
	</script>
</body>
</html>