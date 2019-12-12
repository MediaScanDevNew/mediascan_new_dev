<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="/js/jquery-1.6.4.js"></script>
<style type="text/css">
.txtbox {
	border: none;
	width: 100%;
}

input {
	font-size: 17px;
	height: 30px;
}

/* table {
	background: none repeat scroll 0 0 #abcdef;
	border: 1px solid #000;
} */
table {
    border-collapse: collapse;
}

table, th, td {
    border: 1px solid black;
}
tr.addedRows{
  border: 1px solid black;
  border-collapse: collapse;
}
</style>
</head>
<body>
	<fieldset>
		<legend>Project Setup</legend>

		<table rules="all" style="background: #fff;">
			<tr>
				<td colspan="5"><span
					style="font: normal 12px agency, arial; color: blue; text-decoration: underline; cursor: pointer;"
					onclick="addMoreRows(this.form);"> Add More </span></td>
			</tr>
			<tr>
				<th style="font-size: 16px;width: 200px; ">Key Phrase</th>
				<th style="font-size: 16px;width: 200px;">Pipe</th>
				<th style="font-size: 16px;width: 200px;">Machine</th>
				<th style="font-size: 16px;width: 200px;">Task Priority</th>
				<th style="font-size: 16px;">Action</th>
			
			</tr>
			<!-- <tr id="rowId">
				<td><select name="keyphrase" style="width: 200px;"><option
							value="1">Watch Online Full Movie</option>
						<option value="2">Download Movie Online</option></select></td>
				<td><select name="pipe" style="width: 200px;"><option
							value="1">Google</option>
						<option value="2">Yahoo</option></select></td>
				<td><select name="machine" style="width: 200px;"><option
							value="1">First Machine</option>
						<option value="2">Second Machine</option></select></td>
				<td><select name="keyphrase" style="width: 200px;"><option
							value="1">First</option>
						<option value="2">Second</option></select></td>
				<td>
			</tr> -->
			<tbody id="addedRows"></tbody>
		</table>
		<!-- <div id="addedRows"></div> -->
	</fieldset>
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<script type="text/javascript">
		var rowCount = 1;
		function addMoreRows(frm) {
			rowCount++;
			var recRow = '<tr id="rowCount'+rowCount+'">'+
			'<td>'+
			'<select  name="keyphrase" style="width:  200px;">'+
			'<option value="1" >Watch Online Full Movie</option><option value="2">Download Movie Online</option>'+
			'</select></td>'+
			'<td><select name="pipe" style="width:  200px;">'+
			'<option value="1" >Google</option><option value="2">Yahoo</option></select></td>'+
			'<td><select name="machine" style="width:  200px;">'+
			'<option value="1" >First Machine</option><option value="2">Second Machine</option></select></td>'+
			'<td><select name="keyphrase" style="width:  200px;">'+
			'<option value="1" >First</option><option value="2">Second</option></select></td>'+
			'<td><a href="javascript:void(0);" onclick="removeRow('+ rowCount + ');">Delete</a></td></tr>';
			jQuery('#addedRows').append(recRow);
		}

		function removeRow(removeNum) {
			jQuery('#rowCount' + removeNum).remove();
		}
	</script>
</body>
</html>