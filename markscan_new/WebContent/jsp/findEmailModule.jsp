
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
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/pjstyle.css">
<script src="js/jquery-1.12.4.js"></script>
<script src="js/jquery-ui-wt.js"></script>
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

.tdLabel {
	width: 150px;
}
</style>


<link rel="stylesheet" href="css/default.css" type="text/css">


</head>
<body>
	<h1>Select Module & Email for Mail Shoot</h1>

	<div style="margin-left: 170px;">


		<s:form method="post" action="findModuleEmaildetails">


			<s:select id="module_id" name="module_id" list="moduleListData"
				listKey="id" listValue="module" headerKey="0"
				headerValue="Select Module" label="Select Module"
				cssStyle="width: 170px;" />


			<s:select id="email_id" name="email_id" list="emailListData"
				listKey="id" listValue="mail" headerKey="0"
				headerValue="Select Email-ID" label="Select Email-id"
				cssStyle="width: 370px;" />

			<s:checkbox name="checkMe" value="true" label="Uncheck for Advance Coution Notice"
				onclick="document.getElementById('adv_c_notice').disabled=this.checked;" />


			<s:select id="adv_c_notice" name="adv_c_notice" list="moduleListData"
				listKey="id" listValue="module" headerKey="0"
				headerValue="Select Module" label="Select Module"
				cssStyle="width: 170px;" disabled="true"/> 

			<s:submit value="Find Details"></s:submit>

		</s:form>

	</div>

</body>
</html>
