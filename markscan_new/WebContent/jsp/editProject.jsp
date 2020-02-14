<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.Iterator"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Link check</title>
<script src="js/jquery.min.js"></script>


</head>
<script type="text/javascript">
	function closeSelf() {
		// do something

		window.close();

	}
</script>
<script >
	(function ($) {
		$.fn.blink = function (options) {
			var defaults = { delay: 500 };
			var options = $.extend(defaults, options);
			return $(this).each(function (idx, itm) {
				setInterval(function () {
					if ($(itm).css("visibility") === "visible") {
						$(itm).css('visibility', 'hidden');
					}
					else {
						$(itm).css('visibility', 'visible');
					}
				}, options.delay);
			});
		}
	} (jQuery))
	</script>

	<script>
		$(document).ready(function() {
			$('.blink').blink({delay: 1000});
		});
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
<body onBlur="window.focus()">
<%@ taglib uri="/struts-tags" prefix="s" %>
	<%
		String id = request.getParameter("id");

		String name = request.getParameter("name");
		String client_name = request.getParameter("client_name");
		String project_name = request.getParameter("project_name");
		String channel_name = request.getParameter("channel_name");
		/*String end_date = request.getParameter("end_date"); */
		String file_attach_link = request.getParameter("file_attach_link");
		String actual_hosted_site = request.getParameter("actual_hosted_site");
		String created_on = request.getParameter("created_on");
		String ttime = request.getParameter("ttime");
		String days =request.getParameter("telecast_days");
		
		String days1[]=new String[7];
		 days1 =days.split(",");
		
		ArrayList<String> al =new ArrayList<String>();
		
		String weekdays[] ={"sun","mon","tue","wed","thu","fri","sat"};
		al.addAll(Arrays.asList(weekdays));
		
	
		System.out.println(days1);
		
		/* out.print(id + "<br>");
		out.print(name + "<br>");
		out.print(client_name + "<br>");
		out.print(project_name + "<br>");
		out.print(start_date + "<br>");
		out.print(end_date + "<br>");
		out.print(file_attach_link + "<br>");
		out.print(actual_hosted_site + "<br>");
		out.print(created_on + "<br>"); */
	%>

	<div>
		

		<h2>Project Check/Update</h2>
<h3 style="color: red;" ><span class="blink"><h3 style="color: red;" >we recommend Mozilla Firefox for best
			result...</h3></span></h3>

		<s:form action="projectUpdate">
			<table>
				<tr>
					<td>ID</td>
					<td><input type="text" name="id" value="<%=id%>"
						style="background-color: gray;" readonly>
				</tr>
				<tr>
					<td>Project Type</td>
					<td><input type="text" name="name" value="<%=name%>"
						style="background-color: gray;" readonly>
				</tr>
				<tr>
					<td>Client Name</td>
					<td><input type="text" name="client_name" size="40"
						value="<%=client_name%>" style="background-color: gray;" readonly>
				</tr>
				<tr>
					<td>Project Name</td>
					<td><input type="text" name="project_name" size="40"
						value="<%=project_name%>">
				</tr>
				<%-- <tr>
					<td>Start Date</td>
					<td><input type="text" name="start_date"
						value="<%=start_date%>" style="background-color: gray;" readonly>
				</tr>
				<tr>
					<td>End Date</td>
					<td><input type="text" name="end_date" value="<%=end_date%>"
						style="background-color: gray;" readonly>
				</tr> --%>
				<tr>
					<td>File Attach Link</td>
					<td><input type="text" name="file_attach_link" size="40"
						value="<%=file_attach_link%>">
				</tr>
				<tr>
					<td>Actual Hosted Site</td>
					<td><input type="text" name="actual_hosted_site" size="40"
						value="<%=actual_hosted_site%>">
				</tr>
				<tr>
					<td>Channel Name</td>
					<td><input type="text" name="channel_name" size="40"
						value="<%=channel_name%>">
				</tr>
				<tr>
					<td>Created On</td>
					<td><input type="text" name="created_on"
						value="<%=created_on%>" style="background-color: gray;" readonly>
				</tr>
				<tr>
				 
<td style="width: 25%;"><h4>TV Content Only</h4></td>
</tr>
<tr><td>Telecast Time</td>

<td><input type="text"  name="telecastTime"  id="telecastTime1" value ="<%=ttime%>" /></td>
 
</tr>
<tr>
<td>Telecast Days </td>
<td>
<%
for(int i =0; i<days1.length; i++)
{
	String day=days1[i];
	System.out.println("************day***********"+day);
	al.remove(day.trim());
	System.out.println("************after remove al***********"+al);
	
	%>
	<input type="checkbox" name="days" value = "<%=days1[i]%>" checked ><%=days1[i]%>&nbsp;
<%	
}
System.out.println("*************al***********"+al);
for(String obj:al)
{
%>

<input type="checkbox" name="days" value = "<%=obj%>"/><%=obj%>&nbsp;
<%
}
%>
</td>
<!-- 
<td><input type="checkbox" name="days" value="sun" >Sun
&nbsp;<input type="checkbox" name="days" value="mon">Mon
&nbsp;<input type="checkbox" name="days" value="mon">Mon
&nbsp;<input type="checkbox" name="days" value="tue">Tue
&nbsp;<input type="checkbox" name="days" value="wed">Wed
</td>

</tr>
<tr>

</tr>
<tr>
<td></td>
<td>&nbsp;<input type="checkbox" name="days" value="mon">Mon
&nbsp;<input type="checkbox" name="days" value="tue">Tue
&nbsp;<input type="checkbox" name="days" value="wed">Wed
</td>
 -->
</tr>
					
<tr>
					
<td><input type="submit" onclick="closeSelf();"
value="Update Project" />
</td>
</tr>
</table>
</s:form>
</div>
	
</body>
</html>