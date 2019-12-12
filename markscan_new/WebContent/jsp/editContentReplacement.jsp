<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Link check</title>
</head>
<script type="text/javascript">
	function closeSelf() {
		// do something

		window.close();

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


<body onBlur="window.focus()">
	<%
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String client_name = request.getParameter("client_name");
		String project_name = request.getParameter("project_name");
		String channel_name = request.getParameter("domainName");
		/*String end_date = request.getParameter("end_date"); */
		String file_attach_link = request.getParameter("link");
		String actual_hosted_site = request.getParameter("link001");
		String created_on = request.getParameter("created_on");
		/* out.print(id + "<br>");
		out.print(name + "<br>");
		out.print(client_name + "<br>");
		out.print(project_name + "<br>");
		out.print(channel_name + "<br>");
		
		out.print(file_attach_link + "<br>");
		out.print(actual_hosted_site + "<br>");
		out.print(created_on + "<br>"); */
	%>

	<div>
		<h2>Project Check/Update</h2>
		<form action="contentReplaceUpdate">
			<table>
				<tr>
					<td>ID</td>
					<td><input type="text" name="clientname" value="<%=id%>"
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
						readonly="readonly" style="background-color: gray;"
						value="<%=project_name%>">
				</tr>

				<tr>
					<td>URL</td>
					<td><input type="text" name="apple" size="40"
						value="<%=file_attach_link%>">
				</tr>
				<tr>
					<td>Infringing Link</td>
					<td>
						<%
							if (actual_hosted_site == "") {
								out.print(
										"<input type='text' name='uniqprojectName' style='background-color: gray;'  readonly size='40' value="
												+ actual_hosted_site + ">");
							} else {
								out.print("<input type='text' name='uniqprojectName' size='40' value=" + actual_hosted_site + ">");
							}
						%> <%--  <input type="text" name="actual_hosted_site" size="40"
						value="<%=actual_hosted_site%>"> --%>
				</tr>
				<tr>
					<td>Domain Name</td>
					<td><input type="text" name="ptype_name" size="40"
						value="<%=channel_name%>">
				</tr>
				<tr>
					<td>Created On</td>
					<td><input type="text" name="created_on"
						value="<%=created_on%>" style="background-color: gray;" readonly>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" onclick="closeSelf();" value="Update" />
			</table>
		</form>
	</div>
</body>
</html>