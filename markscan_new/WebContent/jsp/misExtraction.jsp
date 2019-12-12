<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%-- <%@taglib prefix="display" uri="http://displaytag.sf.net"%> --%>
<%@include file="header.jsp"%>
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
			$.getJSON('ajaxAction1', {
				ctype : country
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

	$(document).ready(function() { // for tv show name 
		$('#clientname').change(function(event) {
			var country = $("select#clientname").val(); // country = define value of selected option
			//	alert(country);
			$.getJSON('ajaxAction1', {
				ctype : country
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
</script>
</head>
<body>
	<div style="margin-left: 170px;">
		<s:form method="post" action="getReport">
			<table>

				<s:select id="projecttype" name="projecttype" list="listData"
					listKey="id" listValue="name" headerKey="0"
					headerValue="Select Project Type" label="Select Project"
					cssStyle="width: 170px;" />

				<s:select id="clientname" name="clientname" list="{'Select Client'}"
					label="Select Client" cssStyle="width: 170px;" />
				<s:select id="propertyName" name="propertyName"
					list="{'Select property'}" label="Select property"
					cssStyle="width: 170px;" />

				<s:select label="Select Data Type" headerKey=""
					headerValue="Select Data Type"
					list="#{'0':'Infringing Link', '1':'Source Link'}" name="datatype"
					value="2" cssStyle="width: 170px;" />

				<s:select label="Notification status" headerKey=""
					headerValue="Select Notification status"
					list="#{'0':'Google', '1':'DMC'}" name="notification_status"
					value="2" cssStyle="width: 170px;" />
				<s:select id="usertype" name="usertype" list="usrData" listKey="id"
					listValue="name" headerKey="0" headerValue="Select User"
					label="Select User" cssStyle="width: 170px;" />
				<s:textfield label="start Date" name="startdate"
					cssStyle="width: 170px;"></s:textfield>
				<s:textfield label="end Date" name="enddate"
					cssStyle="width: 170px;"></s:textfield>
				<s:submit value="go"></s:submit>

				</s:form>

				</div>



				<display:table id="dashboardData" name="dashboardData" pagesize="10"
					cellpadding="5px;" cellspacing="5px;"
					style="margin-left:17px;margin-top:20px;" requestURI=""
					export="true">
					<display:column property="projectName" title="Project Name" />
					<display:column property="clientName" title="Client Name" />
					<display:column property="propertName" title="Property Name" />
					<display:column property="userName" title="User Name" />
					<display:column property="link" title="Link" />
					<display:column property="link001" title="Link Logger" />
					<display:column property="domainName" title="Domain Name" />

					<display:setProperty name="export.excel.filename"
						value="Infringing_source_Details.xls" />
					<display:setProperty name="export.csv.filename"
						value="Infringing_source_Details.csv" />
				</display:table>
</body>
</html>