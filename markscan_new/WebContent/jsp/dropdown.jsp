<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	
$(document).ready(function() {
	var country = $("select#projecttype").val();  // country = define value of selected option
	alert(country);
	$.getJSON('ajaxAction0', {
		ptype : country
	}, function(jsonResponse) {
		$('#ajaxResponse').text(jsonResponse.dummyMsg);
		var select = $('#machine');
		select.find('option').remove();
		$.each(jsonResponse.machine, function(key, value) {
			$('<option>').val(key).text(value).appendTo(select);
		});
	});

});

</script>

</head>
<body>

	<s:form>
		<table>
			<tr>
				<td>
					<s:select id="projecttype" name="projecttype" list="listData"
				listKey="id" listValue="name"
				headerKey="-1" headerValue="Select Project Type" />
				
				</td>
				
				<td>
				
				
				</td>
			</tr>
		</table>

	</s:form>







	<form action="">
		<table>
			<tr>
				<td><select>
						<option value="volvo">Volvo</option>
						<option value="saab">Saab</option>
						<option value="mercedes">Mercedes</option>
						<option value="audi">Audi</option>
				</select></td>
				<td><select>
						<option value="volvo">Volvo</option>
						<option value="saab">Saab</option>
						<option value="mercedes">Mercedes</option>
						<option value="audi">Audi</option>
				</select></td>
				<td><select>
						<option value="volvo">Volvo</option>
						<option value="saab">Saab</option>
						<option value="mercedes">Mercedes</option>
						<option value="audi">Audi</option>
				</select></td>
				<td><select>
						<option value="volvo">Volvo</option>
						<option value="saab">Saab</option>
						<option value="mercedes">Mercedes</option>
						<option value="audi">Audi</option>
				</select></td>
				<td><select>
						<option value="volvo">Volvo</option>
						<option value="saab">Saab</option>
						<option value="mercedes">Mercedes</option>
						<option value="audi">Audi</option>
				</select></td>
				<td><select>
						<option value="volvo">Volvo</option>
						<option value="saab">Saab</option>
						<option value="mercedes">Mercedes</option>
						<option value="audi">Audi</option>
				</select></td>
				<td><input type="submit"></td>
			</tr>
		</table>
	</form>
</body>
</html>