
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
table, th, td {
	border: 1px solid #666;
	width: 60%;
	margin: 20px 0 20px 0px;
	 border-collapse: collapse;
}
</style>

<style>
.box {
	display: none;
	width: 100%;
}

a:hover+.box, .box:hover {
	display: block;
	position: relative;
	z-index: 100;
}

#parent {
	display: flex;
}

#narrow {
	flex: 1;
	/* background: lightblue; */
	/* Just so it's visible */
}

#wide {
	/* Grow to rest of container */
	width: 250px;
	/* background: lightgreen; */
	/* Just so it's visible */
}
</style>





</head>
<body>

	<div id="parent">
		<div style="margin-left: 170px;" id="wide">
			<table>
				<tr>
					<th>Machine</th>
					<th>Machine Address</th>

				</tr>
				<s:iterator value="listData">
					<tr>
						<td><s:property value="name" /></td>
						<td><s:property value="ip_address" /></td>
						<%-- <s:if test="%{status==1}">
							<td bgcolor="yellow">Busy</td>
						</s:if>
						<s:else>
							<td>Idel</td>
						</s:else> --%>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div id="narrow">
			<table>
				<tr>
					<th>KeyPhrase</th>
					<th>User Name</th>
					<th>Machine</th>
					<th>Created Date</th>
					<!-- <th>Deleted status</th> -->
					<th>Google Status</th>
					<th>Yahoo Status</th>
					<th>Bing Status</th>
					

				</tr>
				<s:iterator value="ipaddressList">
					<tr>
						<td><s:property value="keyphrase" /></td>
						<td><s:property value="userName" /></td>
						<td><s:property value="machine" /></td>
						<td><s:property value="created_on" /></td>
						<%-- <td><s:property value="deleted" /></td> --%>
						<s:if test="%{google=='Complete'}">
							<td bgcolor="green"><s:property value="google" /></td>
						</s:if>
						<s:elseif test="%{google=='Pending'}">
							<td bgcolor="red"><s:property value="google" /></td>
						</s:elseif>
						<s:elseif test="%{google=='Running'}">
							<td bgcolor="orange"><s:property value="google" /></td>
						</s:elseif>

						<s:if test="%{yahoo=='Complete'}">
							<td bgcolor="green"><s:property value="yahoo" /></td>
						</s:if>
						<s:elseif test="%{yahoo=='Pending'}">
							<td bgcolor="red"><s:property value="yahoo" /></td>
						</s:elseif>
						<s:elseif test="%{yahoo=='Running'}">
							<td bgcolor="orange"><s:property value="yahoo" /></td>
						</s:elseif>
						<s:if test="%{bing=='Complete'}">
							<td bgcolor="green"><s:property value="bing" /></td>
						</s:if>
						<s:elseif test="%{bing=='Pending'}">
							<td bgcolor="red"><s:property value="bing" /></td>
						</s:elseif>
						<s:elseif test="%{bing=='Running'}">
							<td bgcolor="orange"><s:property value="bing" /></td>
						</s:elseif>
						<%-- <td><s:property value="google" /></td> --%>
						<%-- <td><s:property value="yahoo" /></td> --%>
						<%-- <td><s:property value="bing" /></td> --%>
						<%-- <td><input type="checkbox" name="id"
							id='<s:property value="id"/>' value='<s:property value="id"/>'>
						</td> --%>
					</tr>
				</s:iterator>
			</table>


		</div>
	</div>
</body>
</html>
