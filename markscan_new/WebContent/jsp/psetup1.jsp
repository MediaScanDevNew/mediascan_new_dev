

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
	width: 100%;
	margin: 20px 0 20px 0px;
}

table {
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
	padding: 10px 20px;
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

.si {
	width: 300px;
}

.ss {
	width: 50px;
}

.sm {
	width: 20px;
}

.so {
	width: 40px;
}

.txt {
	width: 350px;
	background-color: #A9A9A9;
}

.pp {
	background-color: #A9A9A9;
}

.chkbox {
	width: 20px;
}

.chkbx {
	width: 20px;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$('select#keyph11').change(function() {
			var selectedText = $(this).find('option:selected').text();
			//	alert(selectedText);
			$('input#keyph1').val(selectedText);
		});
	});
	$(document).ready(function() {
		$('select#keyph22').change(function() {
			var selectedText = $(this).find('option:selected').text();
			//	alert(selectedText);
			$('input#keyph2').val(selectedText);
		});
	});
	$(document).ready(function() {
		$('select#keyph33').change(function() {
			var selectedText = $(this).find('option:selected').text();
			//	alert(selectedText);
			$('input#keyph3').val(selectedText);
		});
	});
	$(document).ready(function() {
		$('select#keyph44').change(function() {
			var selectedText = $(this).find('option:selected').text();
			//	alert(selectedText);
			$('input#keyph4').val(selectedText);
		});
	});
	$(document).ready(function() {
		$('select#keyph55').change(function() {
			var selectedText = $(this).find('option:selected').text();
			//	alert(selectedText);
			$('input#keyph5').val(selectedText);
		});
	});
	$(document).ready(function() {
		$('select#keyph66').change(function() {
			var selectedText = $(this).find('option:selected').text();
			//	alert(selectedText);
			$('input#keyph6').val(selectedText);
		});
	});
	$(document).ready(function() {
		$('select#keyph77').change(function() {
			var selectedText = $(this).find('option:selected').text();
			//	alert(selectedText);
			$('input#keyph7').val(selectedText);
		});
	});
	$(document).ready(function() {
		$('select#keyph88').change(function() {
			var selectedText = $(this).find('option:selected').text();
			//	alert(selectedText);
			$('input#keyph8').val(selectedText);
		});
	});
	$(document).ready(function() {
		$('select#keyph99').change(function() {
			var selectedText = $(this).find('option:selected').text();
			//	alert(selectedText);
			$('input#keyph9').val(selectedText);
		});
	});
	$(document).ready(function() {
		$('select#keyph00').change(function() {
			var selectedText = $(this).find('option:selected').text();
			//	alert(selectedText);
			$('input#keyph0').val(selectedText);
		});
	});
</script>

</head>
<body>
	<s:form method="get" action="keywrdStore" theme="simple">
		<s:hidden name="pid" value="%{propertyName}"></s:hidden>

		<div style="margin-left: 35px; margin-right: 35px;">
			<fieldset>
				<legend>Project Setup</legend>
				<table class="three">
					<tr>
						<th class="so">Key Phrase</th>
						<th class="si">Custom Key Phrase</th>
						<th class="ss">Pipe</th>
						<th class="ss">Machine</th>
						<th class="ss">Task Priority</th>
						<th class="chkbx">BLS**</th>
						<th class="chkbx">Action</th>
					</tr>
					<tr>
						<td><s:select id="keyph11" name="keyph11" list="keyextn"
								listKey="keyphrase" listValue="keyphrase" headerKey=""
								headerValue="Select Key Phrase" label="Select Key Phrase"
								cssStyle="width: 170px;" /></td>
						<td class="pp"><input type="text" name="keyph1" id="keyph1"
							class="txt"></td>
						<td><s:select id="mpipe1" name="mpipe1" list="pipe"
								listKey="id" listValue="name" headerKey="0"
								headerValue="Select Pipe" label="Select Pipe" /></td>
						<td><s:select id="machine1" name="machine1" list="machine"
								listKey="ip_address" listValue="name" headerKey=""
								headerValue="Select Machine" label="Select Machine" /></td>
						<td><s:select id="preority1" name="preority1" headerKey="-1"
								headerValue="Select Priority"
								list="#{'1':'1', '2':'2', '3':'3', '4':'4','5':'5', '6':'6', '7':'7', '8':'8','9':'9', '10':'10'}" /></td>
						<td><input type="checkbox" name="bls1" value="1"></td>
						<td><input type="checkbox" name="action1" value="1"><br></td>
					</tr>

					<tr>
						<td><s:select id="keyph22" name="keyph22" list="keyextn"
								listKey="keyphrase" listValue="keyphrase" headerKey=""
								headerValue="Select Key Phrase" label="Select Key Phrase"
								cssStyle="width: 170px;" /></td>
						<td class="pp"><input type="text" name="keyph2" id="keyph2"
							class="txt"></td>
						<td><s:select id="mpipe2" name="mpipe2" list="pipe"
								listKey="id" listValue="name" headerKey="0"
								headerValue="Select Pipe" label="Select Pipe" /></td>
						<td><s:select id="machine2" name="machine2" list="machine"
								listKey="ip_address" listValue="name" headerKey=""
								headerValue="Select Machine" label="Select Machine" /></td>
						<td><s:select id="preority2" name="preority2" headerKey="-1"
								headerValue="Select Priority"
								list="#{'1':'1', '2':'2', '3':'3', '4':'4','5':'5', '6':'6', '7':'7', '8':'8','9':'9', '10':'10', '11':'11', '12':'12','13':'13', '14':'14', '15':'15', '16':'16'}" /></td>
						<td><input type="checkbox" name="bls2" value="1"></td>
						<td><input type="checkbox" name="action2" value="1"></td>
					</tr>

					<tr>
						<td><s:select id="keyph33" name="keyph33" list="keyextn"
								listKey="keyphrase" listValue="keyphrase" headerKey=""
								headerValue="Select Key Phrase" label="Select Key Phrase"
								cssStyle="width: 170px;" /></td>
						<td class="pp"><input type="text" name="keyph3" id="keyph3"
							class="txt"></td>
						<td><s:select id="mpipe3" name="mpipe3" list="pipe"
								listKey="id" listValue="name" headerKey="0"
								headerValue="Select Pipe" label="Select Pipe" /></td>
						<td><s:select id="machine3" name="machine3" list="machine"
								listKey="ip_address" listValue="name" headerKey=""
								headerValue="Select Machine" label="Select Machine" /></td>
						<td><s:select id="preority3" name="preority3" headerKey="-1"
								headerValue="Select Priority"
								list="#{'1':'1', '2':'2', '3':'3', '4':'4','5':'5', '6':'6', '7':'7', '8':'8','9':'9', '10':'10', '11':'11', '12':'12','13':'13', '14':'14', '15':'15', '16':'16'}" /></td>
						<td><input type="checkbox" name="bls3" value="1"></td>
						<td><input type="checkbox" name="action3" value="1">
						</td>
					</tr>

					<tr>
						<td><s:select id="keyph44" name="keyph44" list="keyextn"
								listKey="keyphrase" listValue="keyphrase" headerKey=""
								headerValue="Select Key Phrase" label="Select Key Phrase"
								cssStyle="width: 170px;" /></td>
						<td class="pp"><input type="text" name="keyph4" id="keyph4"
							class="txt"></td>
						<td><s:select id="mpipe4" name="mpipe4" list="pipe"
								listKey="id" listValue="name" headerKey="0"
								headerValue="Select Pipe" label="Select Pipe" /></td>
						<td><s:select id="machine4" name="machine4" list="machine"
								listKey="ip_address" listValue="name" headerKey=""
								headerValue="Select Machine" label="Select Machine" /></td>
						<td><s:select id="preority4" name="preority4" headerKey="-1"
								headerValue="Select Priority"
								list="#{'1':'1', '2':'2', '3':'3', '4':'4','5':'5', '6':'6', '7':'7', '8':'8','9':'9', '10':'10', '11':'11', '12':'12','13':'13', '14':'14', '15':'15', '16':'16'}" /></td>
						<td><input type="checkbox" name="bls4" value="1"></td>
						<td><input type="checkbox" name="action4" value="1">
						</td>
					</tr>

					<tr>
						<td><s:select id="keyph55" name="keyph55" list="keyextn"
								listKey="keyphrase" listValue="keyphrase" headerKey=""
								headerValue="Select Key Phrase" label="Select Key Phrase"
								cssStyle="width: 170px;" /></td>
						<td class="pp"><input type="text" name="keyph5" id="keyph5"
							class="txt"></td>
						<td><s:select id="mpipe5" name="mpipe5" list="pipe"
								listKey="id" listValue="name" headerKey="0"
								headerValue="Select Pipe" label="Select Pipe" /></td>
						<td><s:select id="machine5" name="machine5" list="machine"
								listKey="ip_address" listValue="name" headerKey=""
								headerValue="Select Machine" label="Select Machine" /></td>
						<td><s:select id="preority5" name="preority5" headerKey="-1"
								headerValue="Select Priority"
								list="#{'1':'1', '2':'2', '3':'3', '4':'4','5':'5', '6':'6', '7':'7', '8':'8','9':'9', '10':'10', '11':'11', '12':'12','13':'13', '14':'14', '15':'15', '16':'16'}" /></td>
						<td><input type="checkbox" name="bls5" value="1"></td>
						<td><input type="checkbox" name="action5" value="1"><br>
						</td>
					</tr>

					<tr>
						<td><s:select id="keyph66" name="keyph66" list="keyextn"
								listKey="keyphrase" listValue="keyphrase" headerKey=""
								headerValue="Select Key Phrase" label="Select Key Phrase"
								cssStyle="width: 170px;" /></td>
						<td class="pp"><input type="text" name="keyph6" id="keyph6"
							class="txt"></td>
						<td><s:select id="mpipe6" name="mpipe6" list="pipe"
								listKey="id" listValue="name" headerKey="0"
								headerValue="Select Pipe" label="Select Pipe" /></td>
						<td><s:select id="machine6" name="machine6" list="machine"
								listKey="ip_address" listValue="name" headerKey=""
								headerValue="Select Machine" label="Select Machine" /></td>
						<td><s:select id="preority6" name="preority6" headerKey="-1"
								headerValue="Select Priority"
								list="#{'1':'1', '2':'2', '3':'3', '4':'4','5':'5', '6':'6', '7':'7', '8':'8','9':'9', '10':'10', '11':'11', '12':'12','13':'13', '14':'14', '15':'15', '16':'16'}" /></td>
						<td><input type="checkbox" name="bls6" value="1"></td>
						<td><input type="checkbox" name="action6" value="1">
						</td>
					</tr>

					<tr>
						<td><s:select id="keyph77" name="keyph77" list="keyextn"
								listKey="keyphrase" listValue="keyphrase" headerKey=""
								headerValue="Select Key Phrase" label="Select Key Phrase"
								cssStyle="width: 170px;" /></td>
						<td class="pp"><input type="text" name="keyph7" id="keyph7"
							class="txt"></td>
						<td><s:select id="mpipe7" name="mpipe7" list="pipe"
								listKey="id" listValue="name" headerKey="0"
								headerValue="Select Pipe" label="Select Pipe" /></td>
						<td><s:select id="machine7" name="machine7" list="machine"
								listKey="ip_address" listValue="name" headerKey=""
								headerValue="Select Machine" label="Select Machine" /></td>
						<td><s:select id="preority7" name="preority7" headerKey="-1"
								headerValue="Select Priority"
								list="#{'1':'1', '2':'2', '3':'3', '4':'4','5':'5', '6':'6', '7':'7', '8':'8','9':'9', '10':'10', '11':'11', '12':'12','13':'13', '14':'14', '15':'15', '16':'16'}" /></td>
						<td><input type="checkbox" name="bls7" value="1"></td>
						<td><input type="checkbox" name="action7" value="1">
						</td>
					</tr>

					<tr>
						<td><s:select id="keyph88" name="keyph88" list="keyextn"
								listKey="keyphrase" listValue="keyphrase" headerKey=""
								headerValue="Select Key Phrase" label="Select Key Phrase"
								cssStyle="width: 170px;" /></td>
						<td class="pp"><input type="text" name="keyph8" id="keyph8"
							class="txt"></td>
						<td><s:select id="mpipe8" name="mpipe8" list="pipe"
								listKey="id" listValue="name" headerKey="0"
								headerValue="Select Pipe" label="Select Pipe" /></td>
						<td><s:select id="machine8" name="machine8" list="machine"
								listKey="ip_address" listValue="name" headerKey=""
								headerValue="Select Machine" label="Select Machine" /></td>
						<td><s:select id="preority8" name="preority8" headerKey="-1"
								headerValue="Select Priority"
								list="#{'1':'1', '2':'2', '3':'3', '4':'4','5':'5', '6':'6', '7':'7', '8':'8','9':'9', '10':'10', '11':'11', '12':'12','13':'13', '14':'14', '15':'15', '16':'16'}" /></td>
						<td><input type="checkbox" name="bls8" value="1"></td>
						<td><input type="checkbox" name="action8" value="1">
						</td>
					</tr>

					<tr>
						<td><s:select id="keyph99" name="keyph99" list="keyextn"
								listKey="keyphrase" listValue="keyphrase" headerKey=""
								headerValue="Select Key Phrase" label="Select Key Phrase"
								cssStyle="width: 170px;" /></td>
						<td class="pp"><input type="text" name="keyph9" id="keyph9"
							class="txt"></td>
						<td><s:select id="mpipe9" name="mpipe9" list="pipe"
								listKey="id" listValue="name" headerKey="0"
								headerValue="Select Pipe" label="Select Pipe" /></td>
						<td><s:select id="machine9" name="machine9" list="machine"
								listKey="ip_address" listValue="name" headerKey=""
								headerValue="Select Machine" label="Select Machine" /></td>
						<td><s:select id="preority9" name="preority9" headerKey="-1"
								headerValue="Select Priority"
								list="#{'1':'1', '2':'2', '3':'3', '4':'4','5':'5', '6':'6', '7':'7', '8':'8','9':'9', '10':'10', '11':'11', '12':'12','13':'13', '14':'14', '15':'15', '16':'16'}" /></td>
						<td><input type="checkbox" name="bls9" value="1"></td>
						<td><input type="checkbox" name="action9" value="1">
						</td>
					</tr>

					<tr>
						<td><s:select id="keyph00" name="keyph00" list="keyextn"
								listKey="keyphrase" listValue="keyphrase" headerKey=""
								headerValue="Select Key Phrase" label="Select Key Phrase"
								cssStyle="width: 170px;" /></td>
						<td class="pp"><input type="text" name="keyph0" id="keyph0"
							class="txt"></td>
						<td><s:select id="mpipe0" name="mpipe0" list="pipe"
								listKey="id" listValue="name" headerKey="0"
								headerValue="Select Pipe" label="Select Pipe" /></td>
						<td><s:select id="machine0" name="machine0" list="machine"
								listKey="ip_address" listValue="name" headerKey=""
								headerValue="Select Machine" label="Select Machine" /></td>
						<td><s:select id="preority0" name="preority0" headerKey="-1"
								headerValue="Select Priority"
								list="#{'1':'1', '2':'2', '3':'3', '4':'4','5':'5', '6':'6', '7':'7', '8':'8','9':'9', '10':'10', '11':'11', '12':'12','13':'13', '14':'14', '15':'15', '16':'16'}" /></td>
						<td><input type="checkbox" name="bls10" value="1"></td>
						<td class="chkbox"><input type="checkbox" name="action0"
							value="1"></td>
					</tr>


				</table>
			</fieldset>
		</div>
		<div>
			<s:submit value="click for project setup"></s:submit>
		</div>

	</s:form>

	** BLS = BlackList Site





</body>
</html>

