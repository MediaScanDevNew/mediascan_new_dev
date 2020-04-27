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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/default.css" type="text/css">

<!-- <link rel="stylesheet" type="text/css" href="css/ddstyle.css"> -->
<style type="text/css">
.Zebra_DatePicker {
	left: 102px;
	margin-top: 106px;
}
</style>
</head>
<body onload="form1.reset();" th>
	<s:form method="post" action="addKeyphraseSave" theme="simple">
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
					<td>Key Phrase</td>
					<td><s:textfield name="keyphrase"></s:textfield></td>
				</tr>
			</table>
		</div>

		<div>
			<s:submit value="Add Keyphrase"></s:submit>
		</div>

	</s:form>


</body>
</html>